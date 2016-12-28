package layout;

//計時頁面

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import project.tthree.ProjectRead;
import project.tthree.R;
import project.tthree.SettingRead;
import project.tthree.TaskRead;

import static android.content.Context.AUDIO_SERVICE;


public class TabFragment2 extends Fragment implements View.OnClickListener{

    private View v;

    private ArrayList<SettingRead> settinglist;
    private ArrayList<SettingRead> settinglist2;
    private ArrayList<ProjectRead> projectlist;
    private ArrayList<TaskRead> tasklist;
    private ArrayList<TaskRead> tasklist2;

    private CountDownTimer countDownTimer;

    private boolean timeStarted = false;
    private boolean breaknow = true;
    private boolean work = true;

    private Button btn_tstart;
    private ImageButton ibtn_refresh;
    public TextView txt_time, txt_txholder, txt_taskdetail, txt_txtime, txt_tasktime, txt_txdt;
    public Spinner sp_project , sp_task;

    private final long interval = 1 * 1000;
    int session = 0;

    Integer cycle = null;
    Integer workcycle = null;

    String projectname;
    String taskname;

    SimpleDateFormat Gyear = new SimpleDateFormat("yyyy");
    SimpleDateFormat Gmonth = new SimpleDateFormat("MM");
    SimpleDateFormat Gday = new SimpleDateFormat("d");
    SimpleDateFormat Gweek = new SimpleDateFormat("EEE", Locale.US);
    SimpleDateFormat Ghour = new SimpleDateFormat("HH");

    String year, month, day, week, hour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.tab_fragment_2, container, false);

        txt_time = (TextView) v.findViewById(R.id.txt_time);
        txt_txholder = (TextView) v.findViewById(R.id.txt_txholder);
        txt_taskdetail = (TextView) v.findViewById(R.id.txt_taskdetail);
        txt_txtime = (TextView) v.findViewById(R.id.txt_txtime);
        txt_tasktime = (TextView) v.findViewById(R.id.txt_tasktime);
        txt_txdt = (TextView) v.findViewById(R.id.txt_txdt);
        btn_tstart = (Button) v.findViewById(R.id.btn_tstart);
        ibtn_refresh = (ImageButton) v.findViewById(R.id.ibtn_refresh);
        sp_project = (Spinner) v.findViewById(R.id.sp_project);
        sp_task = (Spinner) v.findViewById(R.id.sp_task);



        //refresh
        ibtn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(TabFragment2.this).attach(TabFragment2.this).commit();
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);


        //Get Setting Time
        PostResponseAsyncTask timeread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                settinglist = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                SettingRead settime = settinglist.get(0);
                if(settime != null){
                    workcycle = settime.workcycle;
                    cycle = settime.breakcycle;
                }

                int startTime = workcycle * 60 * 1000;
                //   int startTime = 1 * 60 * 1000;    //for test

                countDownTimer = new CountDownTimerActivity(startTime, interval);

                int seconds = startTime / 1000;
                int minutes = seconds / 60;
                seconds = seconds % 60;
                txt_time.setText(txt_time.getText() + String.valueOf(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
            }
        });
        timeread.execute("http://140.135.113.107/team/test/timeread.php");

       //sp_project
        PostResponseAsyncTask projectread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                projectlist = new JsonConverter<ProjectRead>().toArrayList(s, ProjectRead.class);
                List<String> project = new ArrayList<String>();

                if(projectlist == null){
                    project.add("Go Add");
                }
                else{
                    for (int i = 0; i<projectlist.size(); i++){
                        project.add(projectlist.get(i).projectname);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, project);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_project.setAdapter(adapter);
                sp_project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       // Toast.makeText(getActivity(), adapterView.getItemAtPosition(i).toString() + "Selected", Toast.LENGTH_LONG).show();
                        projectname = adapterView.getItemAtPosition(i).toString();
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        String id = preferences.getString("Id", "");

                        HashMap postData = new HashMap();
                        postData.put("txtID", id);
                        postData.put("txtPN", projectname);

                        PostResponseAsyncTask taskread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                tasklist = new JsonConverter<TaskRead>().toArrayList(s, TaskRead.class);
                                List<String> task = new ArrayList<String>();
                                if(tasklist == null){
                                    task.add("Go Add");
                                }
                                else{
                                    for(int i = 0; i<tasklist.size(); i++){
                                        task.add(tasklist.get(i).taskname);
                                    }
                                }

                                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, task);
                                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_task.setAdapter(adapter1);
                                sp_task.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                       // Toast.makeText(getActivity(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                        String id = preferences.getString("Id", "");
                                        taskname = adapterView.getItemAtPosition(i).toString();

                                        HashMap postData = new HashMap();
                                        postData.put("txtID", id);
                                        postData.put("txtPN", projectname);
                                        postData.put("txtTN", taskname);

                                        PostResponseAsyncTask taskdetail = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                                            @Override
                                            public void processFinish(String s) {
                                                tasklist2 = new JsonConverter<TaskRead>().toArrayList(s, TaskRead.class);
                                                if(tasklist2 == null){
                                                    txt_txholder.setText("");
                                                    txt_taskdetail.setText("");
                                                    txt_txtime.setText("");
                                                    txt_tasktime.setText("");
                                                    txt_txdt.setText("");
                                                }else{
                                                    TaskRead taskread = tasklist2.get(0);
                                                    String task = taskread.taskholder;
                                                    String time = taskread.tasktime;
                                                    String type = taskread.timetype;
                                                    txt_txholder.setText("任務負責：");
                                                    txt_taskdetail.setText(task);
                                                    txt_txtime.setText("預估時間：");
                                                    txt_tasktime.setText(time);
                                                    txt_txdt.setText(type);
                                                }


                                            }
                                        });
                                        taskdetail.execute("http://140.135.113.107/team/test/taskdetailread.php");

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });


                            }
                        });
                        taskread.execute("http://140.135.113.107/team/test/taskread.php");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
        projectread.execute("http://140.135.113.107/team/test/projectread.php");


        //btn_tstart
        btn_tstart.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(!timeStarted){
            countDownTimer.start();
            timeStarted = true;
            btn_tstart.setText("停止");
            sp_project.setEnabled(false);
            sp_task.setEnabled(false);
        }
        else{
            countDownTimer.cancel();
            timeStarted = false;
            btn_tstart.setText("開始");
            sp_project.setEnabled(true);
            sp_task.setEnabled(true);
        }
    }


    public class CountDownTimerActivity extends CountDownTimer{
        public CountDownTimerActivity(int startTime, long intervla){
            super(startTime, intervla);
        }

        @Override
        public void onFinish(){
            txt_time.setText("Time's up");

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String id = preferences.getString("Id", "");

            HashMap postData = new HashMap();
            postData.put("txtID", id);

            PostResponseAsyncTask task = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    settinglist2 = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                    final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.sound);
                    if(settinglist2 == null){
                        mp.start();
                    }
                    else{
                        if(settinglist2.get(0).remind == 0){
                            //響鈴
                            mp.start();
                        }
                        else if(settinglist2.get(0).remind == 1){
                            //震動
                            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(new long[]{100, 1000, 100, 1000, 100, 1000}, -1);
                        }
                        else if (settinglist2.get(0).remind == 2){
                            //靜音
                            AudioManager audioManager=(AudioManager) getActivity().getSystemService(AUDIO_SERVICE);
                            audioManager.setRingerMode(audioManager.RINGER_MODE_SILENT);
                        }
                    }
                }
            });
            task.execute("http://140.135.113.107/team/test/timeread.php");

            LayoutInflater inflater1 = LayoutInflater.from(getActivity());
            final View v1 = inflater1.inflate(R.layout.timeup, null);


            TextView txt_breaktype = (TextView) v1.findViewById(R.id.txt_breaktype);
            Button btn_break = (Button) v1.findViewById(R.id.btn_break);
            Button btn_skip = (Button) v1.findViewById(R.id.btn_skip);

            //dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Break");
            builder.setView(v1);
            final AlertDialog dialog = builder.create();

            if(work == true){
             /*   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String id = preferences.getString("Id", "");*/

                year = Gyear.format(Calendar.getInstance().getTime());
                month = Gmonth.format(Calendar.getInstance().getTime());
                day = Gday.format(Calendar.getInstance().getTime());
                week = Gweek.format(Calendar.getInstance().getTime());
                hour = Ghour.format(Calendar.getInstance().getTime());
                //test_D.setText(year +"/"+month+"/"+day+"/"+ week+"/"+hour);
                String worktime = workcycle.toString();

                HashMap map = new HashMap();
                map.put("txtid", id);
                map.put("txtworktime", worktime);
                map.put("txtproject", projectname);
                map.put("txttask", taskname);
                map.put("txtyear", year);
                map.put("txtmonth", month);
                map.put("txtday", day);
                map.put("txtweek", week);
                map.put("txthour", hour);

                PostResponseAsyncTask timedone = new PostResponseAsyncTask(getActivity(), map, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                       // Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                    }
                });
                timedone.execute("http://140.135.113.107/team/test/timedone.php");
            }

            if(breaknow == true){

                if(session < cycle){
                    //short break  -->
                    txt_breaktype.setText("SHORT BREAK");
                    btn_break.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sp_project.setEnabled(true);
                            sp_task.setEnabled(true);
                            work = false;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            String id = preferences.getString("Id", "");

                            HashMap postData = new HashMap();
                            postData.put("txtID", id);

                            PostResponseAsyncTask timeread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    settinglist = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                                    SettingRead settime = settinglist.get(0);
                                    Integer shortbreak = null;
                                    if(settime != null){
                                        shortbreak = settime.shortbreak;
                                    }

                                    int starttime = shortbreak * 60 * 1000;
                                    countDownTimer = new CountDownTimerActivity(starttime, interval);

                                    int seconds = starttime / 1000;
                                    int minutes = seconds / 60;
                                    seconds = seconds % 60;
                                    txt_time.setText(String.valueOf(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
                                    countDownTimer.start();
                                }
                            });
                            timeread.execute("http://140.135.113.107/team/test/timeread.php");
                            dialog.dismiss();
                            timeStarted = true;
                            btn_tstart.setText("停止");
                            breaknow = false;
                        }
                    });
                    btn_skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sp_project.setEnabled(true);
                            sp_task.setEnabled(true);
                            work = true;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            String id = preferences.getString("Id", "");

                            HashMap postData = new HashMap();
                            postData.put("txtID", id);

                            PostResponseAsyncTask timeread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    settinglist = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                                    SettingRead settime = settinglist.get(0);
                                    if(settime != null){
                                        workcycle = settime.workcycle;
                                    }

                                    int startTime = workcycle * 60 * 1000;
                                    //    int startTime = 1 * 60 * 1000;   //for test
                                    countDownTimer = new CountDownTimerActivity(startTime, interval);

                                    int seconds = startTime / 1000;
                                    int minutes = seconds / 60;
                                    seconds = seconds % 60;
                                    txt_time.setText(String.valueOf(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
                                }
                            });
                            timeread.execute("http://140.135.113.107/team/test/timeread.php");
                            dialog.dismiss();
                            timeStarted = false;
                            btn_tstart.setText("開始");
                            breaknow = true;
                        }
                    });
                    //shortbreak <--
                    session = session + 1;
                }
                else if (session == cycle){
                    //long break  -->
                    txt_breaktype.setText("Long BREAK");
                    btn_break.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sp_project.setEnabled(true);
                            sp_task.setEnabled(true);
                            work = false;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            String id = preferences.getString("Id", "");

                            HashMap postData = new HashMap();
                            postData.put("txtID", id);

                            PostResponseAsyncTask timeread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    settinglist = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                                    SettingRead settime = settinglist.get(0);
                                    Integer longbreak = null;
                                    if(settime != null){
                                        longbreak = settime.longbreak;
                                    }

                                    int starttime = longbreak * 60 * 1000;
                                    countDownTimer = new CountDownTimerActivity(starttime, interval);

                                    int seconds = starttime / 1000;
                                    int minutes = seconds / 60;
                                    seconds = seconds % 60;
                                    txt_time.setText(String.valueOf(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
                                    countDownTimer.start();
                                }
                            });
                            timeread.execute("http://140.135.113.107/team/test/timeread.php");
                            dialog.dismiss();
                            timeStarted = true;
                            btn_tstart.setText("停止");
                            breaknow = false;
                        }
                    });
                    btn_skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sp_project.setEnabled(true);
                            sp_task.setEnabled(true);
                            work = true;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            String id = preferences.getString("Id", "");

                            HashMap postData = new HashMap();
                            postData.put("txtID", id);

                            PostResponseAsyncTask timeread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                                @Override
                                public void processFinish(String s) {
                                    settinglist = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                                    SettingRead settime = settinglist.get(0);
                                    if(settime != null){
                                        workcycle = settime.workcycle;
                                    }

                                    int startTime = workcycle * 60 * 1000;
                                    // int startTime = 1 * 60 * 1000;   //for test
                                    countDownTimer = new CountDownTimerActivity(startTime, interval);

                                    int seconds = startTime / 1000;
                                    int minutes = seconds / 60;
                                    seconds = seconds % 60;
                                    txt_time.setText(String.valueOf(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
                                }
                            });
                            timeread.execute("http://140.135.113.107/team/test/timeread.php");
                            dialog.dismiss();
                            timeStarted = false;
                            btn_tstart.setText("開始");
                            breaknow = true;
                        }
                    });
                    //longbreak <--

                    session = 0;
                }
                dialog.show();
               // breaknow = false;
            }
            else if (breaknow == false){
                work = true;
             /*   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String id = preferences.getString("Id", "");

                HashMap postData = new HashMap();
                postData.put("txtID", id);*/

                PostResponseAsyncTask timeread = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        settinglist = new JsonConverter<SettingRead>().toArrayList(s, SettingRead.class);
                        SettingRead settime = settinglist.get(0);
                        if(settime != null){
                            workcycle = settime.workcycle;
                        }

                        int startTime = workcycle * 60 * 1000;
                        //   int startTime = 1 * 60 * 1000;   //for test
                        countDownTimer = new CountDownTimerActivity(startTime, interval);

                        int seconds = startTime / 1000;
                        int minutes = seconds / 60;
                        seconds = seconds % 60;
                        txt_time.setText(String.valueOf(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)));
                    }
                });
                timeread.execute("http://140.135.113.107/team/test/timeread.php");
                timeStarted = false;
                btn_tstart.setText("開始");
                breaknow = true;
            }
        }

        @Override
        public void onTick(long millisUntilFinished){
            int seconds = (int) (millisUntilFinished / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            txt_time.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
        }
    }
}
