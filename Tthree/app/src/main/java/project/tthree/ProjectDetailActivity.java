package project.tthree;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectDetailActivity extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemClickListener {

    TextView txt_pdname, txt_pdtime, txt_pttype;
    Button btn_delete;

    EditText edt_tname, edt_ttime, edt_tholder;
    Spinner sp_ttype;

    private ArrayList<TaskRead> tasklist;
    private ListView lv_projectdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

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

        //ADD_TASK
        LayoutInflater inflater = LayoutInflater.from(ProjectDetailActivity.this);
        final View v = inflater.inflate(R.layout.add_task, null);

        edt_tname = (EditText) v.findViewById(R.id.edt_tname);
        edt_ttime = (EditText) v.findViewById(R.id.edt_ttime);
        edt_tholder = (EditText) v.findViewById(R.id.edt_tholder);
        sp_ttype = (Spinner) v.findViewById(R.id.sp_ttype);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProjectDetailActivity.this, R.array.time_choose, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_ttype.setAdapter(adapter);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(ProjectDetailActivity.this);
        dialog.setTitle(R.string.add_task);
        dialog.setView(v);
        dialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProjectDetailActivity.this);
                String id = preferences.getString("Id", "");
                String projectname = txt_pdname.getText().toString();
                String taskname = edt_tname.getText().toString();
                String tasktime = edt_ttime.getText().toString();
                String taskholder = edt_tholder.getText().toString();
                String timetype = "";

                if(sp_ttype.getSelectedItemPosition() == 0){
                    timetype = "min";
                }
                else if(sp_ttype.getSelectedItemPosition() == 1){
                    timetype = "hour";
                }
                else if(sp_ttype.getSelectedItemPosition() == 2){
                    timetype = "day";
                }
                else if (sp_ttype.getSelectedItemPosition() == 3){
                    timetype = "month";
                }

                HashMap postData = new HashMap();
                postData.put("txtid", id);
                postData.put("txtpname", projectname);
                postData.put("txttname", taskname);
                postData.put("txtttime", tasktime);
                postData.put("txtttype", timetype);
                postData.put("txttholder", taskholder);

                PostResponseAsyncTask task = new PostResponseAsyncTask(ProjectDetailActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                      //  Toast.makeText(ProjectDetailActivity.this, s, Toast.LENGTH_LONG).show();
                        ProjectDetailActivity.this.finish();

                    }
                });
                task.execute("http://140.135.113.107/team/test/task.php");

            }
        });
        dialog.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProjectDetailActivity.this.finish();
            }
        });


        //fab
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_addtask);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });



        //PROJECT DETAIL CONTENT
        ProjectRead project = (ProjectRead) getIntent().getSerializableExtra("project");

        txt_pdname = (TextView) findViewById(R.id.txt_pdname);
        txt_pdtime = (TextView) findViewById(R.id.txt_pdtime);
        txt_pttype = (TextView) findViewById(R.id.txt_pttype);

        if(project != null){
            txt_pdname.setText(project.projectname);
            txt_pdtime.setText(project.projecttime);
            txt_pttype.setText(project.timetype);
         }


        //DELETE PROJECT
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProjectDetailActivity.this);
                String id = preferences.getString("Id", "");
                String pn = txt_pdname.getText().toString();

                HashMap postData1 = new HashMap();
                postData1.put("txtID", id);
                postData1.put("txtPN", pn);

                PostResponseAsyncTask projectdelete = new PostResponseAsyncTask(ProjectDetailActivity.this, postData1, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        ProjectDetailActivity.this.finish();
                        Intent in = new Intent(ProjectDetailActivity.this, MainActivity.class);
                        startActivity(in);
                    }
                });
                projectdelete.execute("http://140.135.113.107/team/test/deleteproject.php");

            }
        });



        //TASK LIST
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProjectDetailActivity.this);
        String id = preferences.getString("Id", "");
        String pn = txt_pdname.getText().toString();

        HashMap postData = new HashMap();
        postData.put("txtID", id);
        postData.put("txtPN", pn);

        PostResponseAsyncTask taskread = new PostResponseAsyncTask(ProjectDetailActivity.this, postData, this);
        taskread.execute("http://140.135.113.107/team/test/taskread.php");
    }

    @Override
    public void processFinish(String s) {
        tasklist = new JsonConverter<TaskRead>().toArrayList(s, TaskRead.class);

        BindDictionary<TaskRead> dict = new BindDictionary<TaskRead>();
        dict.addStringField(R.id.txt_tn, new StringExtractor<TaskRead>() {
            @Override
            public String getStringValue(TaskRead item, int position) {
                return item.taskname;
            }
        });
        dict.addStringField(R.id.txt_tt, new StringExtractor<TaskRead>() {
            @Override
            public String getStringValue(TaskRead item, int position) {
                return item.tasktime;
            }
        });
        dict.addStringField(R.id.txt_tty, new StringExtractor<TaskRead>() {
            @Override
            public String getStringValue(TaskRead item, int position) {
                return item.timetype;
            }
        });
        dict.addStringField(R.id.txt_th, new StringExtractor<TaskRead>() {
            @Override
            public String getStringValue(TaskRead item, int position) {
                return item.taskholder;
            }
        });

        FunDapter<TaskRead> adapter = new FunDapter<>(ProjectDetailActivity.this, tasklist, R.layout.layout_list_task, dict);

        lv_projectdetail = (ListView) findViewById(R.id.lv_projectdetail);
        lv_projectdetail.setAdapter(adapter);
        lv_projectdetail.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        TaskRead selectTask = tasklist.get(i);
        Intent intent = new Intent(ProjectDetailActivity.this, TaskDetailActivity.class);
        intent.putExtra("task", (Serializable) selectTask);
        startActivity(intent);
        ProjectDetailActivity.this.finish();
    }
}
