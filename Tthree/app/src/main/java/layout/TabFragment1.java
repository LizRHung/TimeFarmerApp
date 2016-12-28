package layout;

//成長頁面

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;

import project.tthree.CollectRead;
import project.tthree.R;
import project.tthree.TimedoneRead;


public class TabFragment1 extends android.support.v4.app.Fragment implements View.OnClickListener {

    View v;

    private ArrayList<TimedoneRead> timelist;
    private ArrayList<CollectRead> collectlist;

    Integer timecount = 0;
    int hour = 0, min = 0, tomato = 0, stage = 0;
    int Lyear = 0, Lmonth = 0, Lday = 0, Lclock = 0;
    int Nyear = 0, Nmonth = 0, Nday = 0, Nclock = 0;
    int exithour;

    SimpleDateFormat Gyear = new SimpleDateFormat("yyyy");
    SimpleDateFormat Gmonth = new SimpleDateFormat("MM");
    SimpleDateFormat Gday = new SimpleDateFormat("d");
    SimpleDateFormat Gclock = new SimpleDateFormat("HH");

    TextView txt_collectiontime, txt_getstage, txt_tc;
    ImageView img_tgroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_fragment_1, container, false);

        txt_collectiontime = (TextView) v.findViewById(R.id.txt_collecttime);
        txt_getstage = (TextView) v.findViewById(R.id.txt_getstage);
        txt_tc = (TextView) v.findViewById(R.id.txt_tc);
        img_tgroup = (ImageView) v.findViewById(R.id.img_tgroup);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);

        final PostResponseAsyncTask time = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                timelist = new JsonConverter<TimedoneRead>().toArrayList(s, TimedoneRead.class);

                if(timelist == null){
                    txt_collectiontime.setText(txt_collectiontime.getText()+" "+hour+" 小時 "+min+" 分鐘");
                }
                else{
                    for (int i = 0; i<timelist.size(); i++){
                        timecount = timecount + timelist.get(i).worktime;
                    }

                    hour = timecount / 60;
                    min = timecount % 60;
                    txt_collectiontime.setText(txt_collectiontime.getText()+" "+hour+" 小時 "+min+" 分鐘");

                    Lyear = Integer.valueOf(timelist.get(timelist.size()-1).year);
                    Lmonth = Integer.valueOf(timelist.get(timelist.size()-1).month);
                    Lday = Integer.valueOf(timelist.get(timelist.size()-1).day);
                    Lclock = Integer.valueOf(timelist.get(timelist.size()-1).hour);

                    Nyear = Integer.valueOf(Gyear.format(Calendar.getInstance().getTime()));
                    Nmonth = Integer.valueOf(Gmonth.format(Calendar.getInstance().getTime())) ;
                    Nday = Integer.valueOf(Gday.format(Calendar.getInstance().getTime()));
                    Nclock = Integer.valueOf(Gclock.format(Calendar.getInstance().getTime()));

                    exithour = (Nyear*12*30*24 + Nmonth*30*24 + Nday*24 + Nclock) - (Lyear*12*30*24 + Lmonth*30*24 + Lday*24 + Lclock);

                }
            }
        });
        time.execute("http://140.135.113.107/team/test/timedoneread.php");

        PostResponseAsyncTask collect = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                collectlist = new JsonConverter<CollectRead>().toArrayList(s, CollectRead.class);

                if(collectlist == null){
                    tomato = 0;
                    stage = 0;
                }
                else {
                    for (int i = 0; i<collectlist.size(); i++){
                        tomato = collectlist.get(i).tomatoamount;
                        stage = collectlist.get(i).stage;
                    }
                }
            }
        });
        collect.execute("http://140.135.113.107/team/test/collectread.php");

        txt_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_tc.setText("\n"+String.valueOf(tomato));
            }
        });

        img_tgroup.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
       tomato = hour/5;
        txt_getstage.setVisibility(View.VISIBLE);
        if(exithour >= 24){
           //  Toast.makeText(getActivity(),"die",Toast.LENGTH_LONG).show();
            if (exithour>=24 && exithour<48){
                stage = 5;
                img_tgroup.setImageResource(R.drawable.g5);
                txt_getstage.setText("階段：花謝");
              //  Toast.makeText(getActivity(),"die5",Toast.LENGTH_LONG).show();
            }
            else if (exithour>=48 && exithour<72){
                stage = 6;
                img_tgroup.setImageResource(R.drawable.g6);
                txt_getstage.setText("階段：下垂");
            //   Toast.makeText(getActivity(),"die6",Toast.LENGTH_LONG).show();
            }
            else if (exithour>=72 && exithour<96){
                stage = 7;
                img_tgroup.setImageResource(R.drawable.g7);
                txt_getstage.setText("階段：變矮");
              //  Toast.makeText(getActivity(),"die7",Toast.LENGTH_LONG).show();
            }
            else if (exithour>=96 && exithour<120){
                stage = 8;
                img_tgroup.setImageResource(R.drawable.g8);
                txt_getstage.setText("階段：枯萎");
              //  Toast.makeText(getActivity(),"die8",Toast.LENGTH_LONG).show();
            }
            else if (exithour>=120){
                stage = 9;
                img_tgroup.setImageResource(R.drawable.g9);
                txt_getstage.setText("階段：爛種子");
             //   Toast.makeText(getActivity(),"die9",Toast.LENGTH_LONG).show();
            }
        }
        else if(exithour < 24){
            int sc = hour - (tomato*5);
            // Toast.makeText(getActivity(),"live",Toast.LENGTH_LONG).show();
            if(sc == 0){
                stage= 0;
                img_tgroup.setImageResource(R.drawable.g0);
                txt_getstage.setText("階段：種子");
             //   Toast.makeText(getActivity(),"live0",Toast.LENGTH_LONG).show();
            }
            else if(sc == 1){
                stage= 1;
                img_tgroup.setImageResource(R.drawable.g1);
                txt_getstage.setText("階段：發芽");
             //   Toast.makeText(getActivity(),"live1",Toast.LENGTH_LONG).show();
            }
            else if(sc  == 2){
                stage= 2;
                img_tgroup.setImageResource(R.drawable.g2);
                txt_getstage.setText("階段：花苞");
               // Toast.makeText(getActivity(),"live2",Toast.LENGTH_LONG).show();
            }
            else if(sc == 3){
                stage= 3;
                img_tgroup.setImageResource(R.drawable.g3);
                txt_getstage.setText("階段：開花");
              //  Toast.makeText(getActivity(),"live3",Toast.LENGTH_LONG).show();
            }
            else if(sc == 4){
                stage= 4;
                img_tgroup.setImageResource(R.drawable.g4);
                txt_getstage.setText("階段：結果");
              //  Toast.makeText(getActivity(),"live4",Toast.LENGTH_LONG).show();
            }
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = preferences.getString("Id", "");

        HashMap post = new HashMap();
        post.put("txtID", id);
        post.put("txtStage", String.valueOf(stage));
        post.put("txtTC", String.valueOf(tomato));

        PostResponseAsyncTask task = new PostResponseAsyncTask(getActivity(), post, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
            //  Toast.makeText(getActivity(),"update",Toast.LENGTH_LONG).show();
            }
        });
        task.execute("http://140.135.113.107/team/test/tomatocollect.php");

        HashMap hash = new HashMap();
        hash.put("txtID", id);
        hash.put("txttime", String.valueOf(hour*60+min));

        PostResponseAsyncTask rankT = new PostResponseAsyncTask(getActivity(), hash, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

            }
        });
        rankT.execute("http://140.135.113.107/team/test/rankT.php");

        HashMap hash2 = new HashMap();
        hash2.put("txtID", id);
        hash2.put("txtscore", String.valueOf(hour*60+min+tomato));

        PostResponseAsyncTask rankS = new PostResponseAsyncTask(getActivity(), hash2, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

            }
        });
        rankS.execute("http://140.135.113.107/team/test/rankS.php");
    }
}
