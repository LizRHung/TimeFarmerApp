package project.tthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class UserEditActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userid;
    EditText username, userbirthday, usergender;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserEditActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        userid = (TextView) findViewById(R.id.userid);
        username = (EditText) findViewById(R.id.username);
        userbirthday = (EditText) findViewById(R.id.userbirthday);
        usergender = (EditText) findViewById(R.id.usergender);
        btn_update = (Button) findViewById(R.id.btn_update);

        String id = getIntent().getExtras().getString("id");
        if(id != null){
            userid.setText(id);
        }

        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        HashMap postData = new HashMap();

        String id = userid.getText().toString();
        String name = username.getText().toString();
        String birthday = userbirthday.getText().toString();
        String gender = usergender.getText().toString();

        postData.put("txtid",id);
        postData.put("txtusername",name);
        postData.put("txtbirthday",birthday);
        postData.put("txtgender",gender);

        PostResponseAsyncTask task = new PostResponseAsyncTask(UserEditActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Intent intent = new Intent(UserEditActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }
        });
        task.execute("http://140.135.113.107/team/test/userupdate.php");
    }
}
