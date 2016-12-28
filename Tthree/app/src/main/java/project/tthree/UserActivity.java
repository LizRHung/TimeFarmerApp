package project.tthree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class UserActivity extends AppCompatActivity implements AsyncResponse {

    TextView txt_userid, txt_username, txt_userbirthday, txt_usergender ;
    Button btn_edit;

    private ArrayList<UserInformation> userList;
    private ListView lvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        txt_userid = (TextView) findViewById(R.id.txt_userid);
        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_userbirthday = (TextView) findViewById(R.id.txt_userbirthday);
        txt_usergender = (TextView) findViewById(R.id.txt_usergender);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        HashMap postData = new HashMap();

        final String id = preferences.getString("Id", "");
        String password = preferences.getString("Password", "");

        postData.put("txtID", id);
        postData.put("txtPassword", password);

        PostResponseAsyncTask task = new PostResponseAsyncTask(UserActivity.this, postData, this) ;
        task.execute("http://140.135.113.107/team/test/member.php");

        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, UserEditActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void processFinish(String s) {

        userList = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);

        BindDictionary<UserInformation> dict = new BindDictionary<UserInformation>();
        dict.addStringField(R.id.txt_userid, new StringExtractor<UserInformation>() {
            @Override
            public String getStringValue(UserInformation user, int position) {
                return user.id;
            }
        });

        dict.addStringField(R.id.txt_username, new StringExtractor<UserInformation>() {
            @Override
            public String getStringValue(UserInformation user, int position) {
                return user.username;
            }
        });

        dict.addStringField(R.id.txt_userbirthday, new StringExtractor<UserInformation>() {
            @Override
            public String getStringValue(UserInformation user, int position) {
                return user.birthday;
            }
        });


        dict.addStringField(R.id.txt_usergender, new StringExtractor<UserInformation>() {
            @Override
            public String getStringValue(UserInformation user, int position) {
                return user.gender;
            }
        });

        FunDapter<UserInformation> adapter = new FunDapter<>(UserActivity.this, userList, R.layout.layout_list, dict);

        lvUser = (ListView) findViewById(R.id.lvUser);
        lvUser.setAdapter(adapter);
    }
}
