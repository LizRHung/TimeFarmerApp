package project.tthree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "LoginActivity";
    final String TAG = this.getClass().getName();

    EditText input_userid, input_password;
    Button btn_login;
    TextView goregister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_userid = (EditText) findViewById(R.id.input_userid);
        input_password = (EditText) findViewById(R.id.input_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        goregister = (TextView) findViewById(R.id.goregister);

        goregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(this);







    }

    @Override
    public void onClick(View view) {

        HashMap postData = new HashMap();

        String id = input_userid.getText().toString();
        String password = input_password.getText().toString();

        postData.put("txtID", id);
        postData.put("txtPassword", password);

        SharedPreferences sharedPred = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPred.edit();
        editor.putString("Id", id);
        editor.putString("Password", password);
        editor.apply();

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(LoginActivity.this,postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.i(LOG, s);
                if(s.contains("success")){
                    Toast.makeText(LoginActivity.this, "Sucessfully Login", Toast.LENGTH_SHORT).show();




                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(in);

                }
                else{
                    Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });
        task1.execute("http://140.135.113.107/team/test/login.php");
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
        Toast.makeText(LoginActivity.this, "再按一次離開", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
                Log.d(TAG, "twice: "+twice);
            }
        }, 3000);
    }


}
