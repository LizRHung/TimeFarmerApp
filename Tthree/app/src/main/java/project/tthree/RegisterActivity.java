package project.tthree;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "RegisterActivity";
    final String TAG = this.getClass().getName();

    EditText input_userid1, input_password1, input_username, input_birthday;
    RadioGroup rgroup1;
    RadioButton radio_male1, radio_female1;
    Button btn_regist1;
    TextView gologin;

    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_userid1 = (EditText) findViewById(R.id.input_userid1);
        input_password1 = (EditText) findViewById(R.id.input_password1);
        input_username = (EditText) findViewById(R.id.input_username);
        input_birthday = (EditText) findViewById(R.id.input_birthday);
        rgroup1 = (RadioGroup) findViewById(R.id.rgroup1);
        radio_male1 = (RadioButton) findViewById(R.id.radio_male1);
        radio_female1 = (RadioButton) findViewById(R.id.radio_female1);
        btn_regist1 = (Button) findViewById(R.id.btn_regist1);
        gologin = (TextView) findViewById(R.id.gologin);

        btn_regist1.setOnClickListener(this);

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {

        if(rgroup1.getCheckedRadioButtonId() == radio_male1.getId()){
            gender = "boy";
        }
        else if (rgroup1.getCheckedRadioButtonId() == radio_female1.getId()){
            gender = "girl";
        }
        HashMap postData = new HashMap();
        postData.put("txtid",input_userid1.getText().toString());
        postData.put("txtusername",input_username.getText().toString());
        postData.put("txtpassword",input_password1.getText().toString());
        postData.put("txtbirthday",input_birthday.getText().toString());
        postData.put("txtgender",gender);
        postData.put("mobile", "android");

        PostResponseAsyncTask taskInsert = new PostResponseAsyncTask(RegisterActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(LOG, s);
                if(s.contains("success")){
                    Toast.makeText(RegisterActivity.this, "註冊成功，請登入！",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(in);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "註冊失敗，請重試！", Toast.LENGTH_LONG).show();
                }
            }
        });
        taskInsert.execute("http://140.135.113.107/team/test/register.php");
    }

    boolean twice = false;
    @Override
    public void onBackPressed() {

        Log.d(TAG, "click");

        if(twice == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        twice = true;
        Log.d(TAG, "twice: "+twice);

        //  super.onBackPressed();
        Toast.makeText(RegisterActivity.this, "再按一次離開", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
                Log.d(TAG, "twice: "+twice);
            }
        }, 3000);
    }
}
