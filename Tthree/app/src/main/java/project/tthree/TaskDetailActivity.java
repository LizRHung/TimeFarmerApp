package project.tthree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class TaskDetailActivity extends AppCompatActivity {

    TextView txt_PN, txt_TTY, txt_TN;
    EditText ed_TH, ed_TT;
    Button btn_D, btn_U;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

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

        //TASK DETAIL CONTENT
        TaskRead taskRead = (TaskRead) getIntent().getSerializableExtra("task");

        txt_PN = (TextView) findViewById(R.id.txt_PN);
        txt_TTY = (TextView) findViewById(R.id.txt_TTY);
        txt_TN = (TextView) findViewById(R.id.txt_TN);
        ed_TH = (EditText) findViewById(R.id.ed_TH);
        ed_TT = (EditText) findViewById(R.id.ed_TT);

        if(taskRead != null){
            txt_PN.setText(taskRead.projectname);
            txt_TTY.setText(taskRead.timetype);
            txt_TN.setText(taskRead.taskname);
            ed_TH.setText(taskRead.taskholder);
            ed_TT.setText(taskRead.tasktime);

        }

        //UPDATE TASK
        btn_U = (Button) findViewById(R.id.btn_U);
        btn_U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap postData = new HashMap();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TaskDetailActivity.this);
                String id = preferences.getString("Id", "");
                String pn = txt_PN.getText().toString();
                String tn = txt_TN.getText().toString();
                String th = ed_TH.getText().toString();
                String tt = ed_TT.getText().toString();

                postData.put("txtid", id);
                postData.put("txtpn", pn);
                postData.put("txttn", tn);
                postData.put("txtth", th);
                postData.put("txttt", tt);

                PostResponseAsyncTask task = new PostResponseAsyncTask(TaskDetailActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                     //   Toast.makeText(TaskDetailActivity.this, s, Toast.LENGTH_LONG).show();
                        TaskDetailActivity.this.finish();
                    }
                });
                task.execute("http://140.135.113.107/team/test/updatetask.php");
            }
        });

        //DELETE TASK
        btn_D = (Button) findViewById(R.id.btn_D);
        btn_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap postData = new HashMap();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TaskDetailActivity.this);
                String id = preferences.getString("Id", "");
                String pn = txt_PN.getText().toString();
                String tn = txt_TN.getText().toString();

                postData.put("txtID", id);
                postData.put("txtPN", pn);
                postData.put("txtTN", tn);

                PostResponseAsyncTask task = new PostResponseAsyncTask(TaskDetailActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        TaskDetailActivity.this.finish();
                    }
                });
                task.execute("http://140.135.113.107/team/test/deletetask.php");
            }
        });


    }

}
