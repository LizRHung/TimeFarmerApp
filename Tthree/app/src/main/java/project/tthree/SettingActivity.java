package project.tthree;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {

    String workcycle = "";
    String shortbreak = "";
    String longbreak = "";
    String interval = "";
    String notice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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

        //spinner1
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1
                = ArrayAdapter.createFromResource(this, R.array.work_session,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        workcycle = "10";
                        break;
                    case 1:
                        workcycle = "15" ;
                        break;
                    case 2:
                        workcycle = "20";
                        break;
                    case 3:
                        workcycle = "25";
                        break;
                    case 4:
                        workcycle = "30";
                        break;
                    case 5:
                        workcycle = "35";
                        break;
                    case 6:
                        workcycle = "40";
                        break;
                    case 7:
                        workcycle = "45";
                        break;
                    case 8:
                        workcycle = "50";
                        break;
                    case 9:
                        workcycle = "55";
                        break;
                    case 10:
                        workcycle = "60";
                        break;
                    case 11:
                        workcycle = "75";
                        break;
                    case 12:
                        workcycle = "90";
                        break;
                    case 13:
                        workcycle = "120";
                        break;
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                String id = preferences.getString("Id", "");
                HashMap postData = new HashMap();
                postData.put("txtID", id);
                postData.put("txtCycle", workcycle);

                PostResponseAsyncTask task = new PostResponseAsyncTask(SettingActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                    }
                });
                task.execute("http://140.135.113.107/team/test/setting1.php");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner2
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2
                = ArrayAdapter.createFromResource(this, R.array.short_break,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(4);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                        case 0:
                            shortbreak = "1";
                            break;
                        case 1:
                            shortbreak = "2" ;
                            break;
                        case 2:
                            shortbreak = "3";
                            break;
                        case 3:
                            shortbreak = "4";
                            break;
                        case 4:
                            shortbreak = "5";
                            break;
                        case 5:
                            shortbreak = "6";
                            break;
                        case 6:
                            shortbreak = "7";
                            break;
                        case 7:
                            shortbreak = "8";
                            break;
                        case 8:
                            shortbreak = "9";
                            break;
                        case 9:
                            shortbreak = "10";
                            break;
                        case 10:
                            shortbreak = "15";
                            break;
                        case 11:
                            shortbreak = "20";
                            break;
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                String id = preferences.getString("Id", "");
                HashMap postData = new HashMap();
                postData.put("txtID", id);
                postData.put("txtShort", shortbreak);

                PostResponseAsyncTask task = new PostResponseAsyncTask(SettingActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                    }
                });
                task.execute("http://140.135.113.107/team/test/setting2.php");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner3
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3
                = ArrayAdapter.createFromResource(this, R.array.long_break,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setSelection(1);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        longbreak = "5";
                        break;
                    case 1:
                        longbreak = "10" ;
                        break;
                    case 2:
                        longbreak = "15";
                        break;
                    case 3:
                        longbreak = "20";
                        break;
                    case 4:
                        longbreak = "25";
                        break;
                    case 5:
                        longbreak = "30";
                        break;
                    case 6:
                        longbreak = "45";
                        break;
                    case 7:
                        longbreak = "60";
                        break;
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                String id = preferences.getString("Id", "");
                HashMap postData = new HashMap();
                postData.put("txtID", id);
                postData.put("txtLong", longbreak);

                PostResponseAsyncTask task = new PostResponseAsyncTask(SettingActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                    }
                });
                task.execute("http://140.135.113.107/team/test/setting3.php");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //spinner4
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4
                = ArrayAdapter.createFromResource(this, R.array.long_break_interval,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setSelection(0);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        interval = "2";
                        break;
                    case 1:
                        interval = "3" ;
                        break;
                    case 2:
                        interval = "4";
                        break;
                    case 3:
                        interval = "5";
                        break;
                    case 4:
                        interval = "6";
                        break;
                    case 5:
                        interval = "7";
                        break;
                    case 6:
                        interval = "8";
                        break;
                    case 7:
                        interval = "9";
                        break;
                    case 8:
                        interval = "10";
                        break;
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                String id = preferences.getString("Id", "");
                HashMap postData = new HashMap();
                postData.put("txtID", id);
                postData.put("txtInterval", interval);

                PostResponseAsyncTask task = new PostResponseAsyncTask(SettingActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                    }
                });
                task.execute("http://140.135.113.107/team/test/setting4.php");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //spinner5
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter5
                = ArrayAdapter.createFromResource(this, R.array.notifications,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
       // final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //   響鈴
                        notice = "0";
                       // audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        break;
                    case 1:
                        //   震動
                        notice = "1";
                      //  audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                        break;
                    case 2:
                        //   靜音
                        notice = "2";
                      //  audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        break;
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
                String id = preferences.getString("Id", "");
                HashMap postData = new HashMap();
                postData.put("txtID", id);
                postData.put("txtRemind", notice);

                PostResponseAsyncTask task = new PostResponseAsyncTask(SettingActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                    }
                });
                task.execute("http://140.135.113.107/team/test/setting5.php");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


}



