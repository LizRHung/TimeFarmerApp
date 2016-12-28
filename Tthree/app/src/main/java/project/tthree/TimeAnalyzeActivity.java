package project.tthree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TimeAnalyzeActivity extends AppCompatActivity {

    private ArrayList<TimedoneRead> timelist;
    private ArrayList<ProjectRead> projectlist;
    List<String> project;
    List<String> projectdone;
    List<Integer> timedone;
    List<Integer> timetotal;
    List<Integer> projectcount;

    TextView txt_Tintor, txt_c1, txt_c2, txt_c3;

    String Tintor;
    String Nyear, Nmonth, Nday;
    String year, month, day;

    Integer ry, rm, rd, ny, nm, nd, daycount, averagework;
    Integer workcycle = 0, timecount = 0;
    Integer Moncount = 0, Tuecount = 0, Wedcount = 0, Thucount = 0, Fricount = 0, Satcount = 0, Suncount = 0, weekcount = 0;
    Integer h0 = 0, h1 = 0, h2 = 0, h3 = 0, h4 = 0, h5 = 0,
            h6 = 0, h7 = 0, h8 = 0, h9 = 0, h10 = 0, h11 = 0,
            h12 = 0, h13 = 0, h14 = 0, h15 = 0, h16 = 0, h17 = 0,
            h18 = 0, h19 = 0, h20 = 0, h21 = 0, h22 = 0, h23 = 0;

    SimpleDateFormat Gyear = new SimpleDateFormat("yyyy");
    SimpleDateFormat Gmonth = new SimpleDateFormat("MM");
    SimpleDateFormat Gday = new SimpleDateFormat("d");

    HorizontalBarChart chart;
    HorizontalBarChart chart2;
    PieChart chart3;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<BarEntry> BARENTRY2;
    ArrayList<Entry> PIEENTRY;
    ArrayList<String> BarEntryLabels;
    ArrayList<String> BarEntryLabels2;
    ArrayList<String> PieEntryLabels;
    BarDataSet Bardataset;
    BarDataSet Bardataset2;
    PieDataSet Piedataset;
    BarData BARDATA;
    BarData BARDATA2;
    PieData PIEDATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_analyze);

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

        txt_Tintor = (TextView) findViewById(R.id.txt_Tintro);
        txt_c1 = (TextView) findViewById(R.id.txt_c1);
        txt_c2 = (TextView) findViewById(R.id.txt_c2);
        txt_c3 = (TextView) findViewById(R.id.txt_c3);
        chart = (HorizontalBarChart) findViewById(R.id.chart1);
        chart2 = (HorizontalBarChart) findViewById(R.id.chart2);
        chart3 = (PieChart) findViewById(R.id.chart3);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TimeAnalyzeActivity.this);
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);

        PostResponseAsyncTask tintro = new PostResponseAsyncTask(TimeAnalyzeActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                timelist = new JsonConverter<TimedoneRead>().toArrayList(s, TimedoneRead.class);

                if (timelist == null){
                    txt_Tintor.setText("尚未完成任何時間循環");
                    txt_c1.setText(txt_c1.getText()+"尚未完成任何時間循環");
                    txt_c2.setText(txt_c2.getText()+"尚未完成任何時間循環");
                    txt_c3.setText(txt_c3.getText()+"尚未完成任何時間循環");
                }
                else{
                    //簡易分析
                    TimedoneRead date = timelist.get(0);
                    if(date != null){
                        year = date.year;
                        month = date.month;
                        day = date.day;
                    }

                    for (int i = 0; i<timelist.size(); i++){

                        workcycle = workcycle+1;
                        timecount = timecount + timelist.get(i).worktime;

                        if(timelist.get(i).week.contains("Mon")){
                            Moncount = Moncount+1;
                        }
                        else if (timelist.get(i).week.contains("Tue")){
                            Tuecount = Tuecount+1;
                        }
                        else if (timelist.get(i).week.contains("Wed")){
                            Wedcount = Wedcount+1;
                        }
                        else if (timelist.get(i).week.contains("Thu")){
                            Thucount = Thucount+1;
                        }
                        else if (timelist.get(i).week.contains("Fri")){
                            Fricount = Fricount+1;
                        }
                        else if (timelist.get(i).week.contains("Sat")){
                            Satcount = Satcount+1;
                        }
                        else if (timelist.get(i).week.contains("Sun")){
                            Suncount = Suncount+1;
                        }

                        if(timelist.get(i).hour.equals("01")){
                            h1 = h1 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("02")){
                            h2 = h2 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("03")){
                            h3 = h3 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("04")){
                            h4 = h4 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("05")){
                            h5 = h5 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("06")){
                            h6 = h6 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("07")){
                            h7 = h7 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("08")){
                            h8 = h8 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("09")){
                            h9 = h9 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("10")){
                            h10 = h10 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("11")){
                            h11 = h11 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("12")){
                            h12 = h12 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("13")){
                            h13 = h13 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("14")){
                            h14 = h14 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("15")){
                            h15 = h15 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("16")){
                            h16 = h16 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("17")){
                            h17 = h17 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("18")){
                            h18 = h18 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("19")){
                            h19 = h19 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("20")){
                            h20 = h20 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("21")){
                            h21 = h21 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("22")){
                            h22 = h22 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("23")){
                            h23 = h23 + timelist.get(i).worktime;
                        }
                        else if (timelist.get(i).hour.equals("00")){
                            h0 = h0 + timelist.get(i).worktime;
                        }

                    }

                    Nyear =Gyear.format(Calendar.getInstance().getTime());
                    Nmonth = Gmonth.format(Calendar.getInstance().getTime());
                    Nday = Gday.format(Calendar.getInstance().getTime());

                    ry = Integer.parseInt(year);
                    rm = Integer.parseInt(month);
                    rd = Integer.parseInt(day);
                    ny = Integer.parseInt(Nyear);
                    nm = Integer.parseInt(Nmonth);
                    nd = Integer.parseInt(Nday);

                    daycount = (ny*12*30)+(nm*30)+nd-(ry*12*30)-(rm*30)-rd+1;

                    if(daycount<7){
                        weekcount = 1;
                    }else {
                        weekcount = daycount / 7;
                    }

                    averagework = timecount / daycount;

                    Tintor = "從<font color=#00802b> "+year+" 年 "+month+" 月 "+day+"日</font> 開始 (約 <font color=#00802b>"+daycount+"</font> 天) , " +
                            "<br>總計已完成 <font color=#00802b>" +workcycle+"</font> 個循環 , 共計 <font color=#00802b>"+timecount+"</font> 分鐘 , " +
                            "<br>平均一天工作 <font color=#00802b>"+ averagework+"</font> 分鐘";

                    txt_Tintor.setText(Html.fromHtml(Tintor));

                    //圖表分析_星期一到日個別循環總數 / 週數
                    BARENTRY = new ArrayList<>();
                    BARENTRY.add(new BarEntry(Suncount/weekcount, 0)); //Sun
                    BARENTRY.add(new BarEntry(Satcount/weekcount, 1)); //Sat
                    BARENTRY.add(new BarEntry(Fricount/weekcount, 2)); //Fri
                    BARENTRY.add(new BarEntry(Thucount/weekcount, 3)); //Thr
                    BARENTRY.add(new BarEntry(Wedcount/weekcount, 4)); //Wed
                    BARENTRY.add(new BarEntry(Tuecount/weekcount, 5)); //Tue
                    BARENTRY.add(new BarEntry(Moncount/weekcount, 6)); //Mon

                    Bardataset = new BarDataSet(BARENTRY, "星期一到日個別循環總數 / 工作週數");
                    BarEntryLabels = new ArrayList<String>();
                    BarEntryLabels.add("星期日");
                    BarEntryLabels.add("星期六");
                    BarEntryLabels.add("星期五");
                    BarEntryLabels.add("星期四");
                    BarEntryLabels.add("星期三");
                    BarEntryLabels.add("星期二");
                    BarEntryLabels.add("星期一");


                    BARDATA = new BarData(BarEntryLabels, Bardataset);
                    chart.setData(BARDATA);
                    chart.setDescription("單位：循環");
                    chart.setNoDataText("No Chart Data"); // this is the top line
                    chart.setNoDataTextDescription("..."); // this is one line below the no-data-text
                    chart.invalidate();

                    int color = getResources().getColor(R.color.colorBar);
                    Bardataset.setColor(color);
                    chart.animateY(2000);

                    //圖表分析_24小時個別工作時間 / 工作天數
                    BARENTRY2 = new ArrayList<>();
                    BARENTRY2.add(new BarEntry(h0/daycount, 0));
                    BARENTRY2.add(new BarEntry(h1/daycount, 1));
                    BARENTRY2.add(new BarEntry(h2/daycount, 2));
                    BARENTRY2.add(new BarEntry(h3/daycount, 3));
                    BARENTRY2.add(new BarEntry(h4/daycount, 4));
                    BARENTRY2.add(new BarEntry(h5/daycount, 5));
                    BARENTRY2.add(new BarEntry(h6/daycount, 6));
                    BARENTRY2.add(new BarEntry(h7/daycount, 7));
                    BARENTRY2.add(new BarEntry(h8/daycount, 8));
                    BARENTRY2.add(new BarEntry(h9/daycount, 9));
                    BARENTRY2.add(new BarEntry(h10/daycount, 10));
                    BARENTRY2.add(new BarEntry(h11/daycount, 11));
                    BARENTRY2.add(new BarEntry(h12/daycount, 12));
                    BARENTRY2.add(new BarEntry(h13/daycount, 13));
                    BARENTRY2.add(new BarEntry(h14/daycount, 14));
                    BARENTRY2.add(new BarEntry(h15/daycount, 15));
                    BARENTRY2.add(new BarEntry(h16/daycount, 16));
                    BARENTRY2.add(new BarEntry(h17/daycount, 17));
                    BARENTRY2.add(new BarEntry(h18/daycount, 18));
                    BARENTRY2.add(new BarEntry(h19/daycount, 19));
                    BARENTRY2.add(new BarEntry(h20/daycount, 20));
                    BARENTRY2.add(new BarEntry(h21/daycount, 21));
                    BARENTRY2.add(new BarEntry(h22/daycount, 22));
                    BARENTRY2.add(new BarEntry(h23/daycount, 23));


                    Bardataset2 = new BarDataSet(BARENTRY2, "24小時個別工作時間 / 工作天數");
                    BarEntryLabels2 = new ArrayList<String>();
                    BarEntryLabels2.add("0");
                    BarEntryLabels2.add("1");
                    BarEntryLabels2.add("2");
                    BarEntryLabels2.add("3");
                    BarEntryLabels2.add("4");
                    BarEntryLabels2.add("5");
                    BarEntryLabels2.add("6");
                    BarEntryLabels2.add("7");
                    BarEntryLabels2.add("8");
                    BarEntryLabels2.add("9");
                    BarEntryLabels2.add("10");
                    BarEntryLabels2.add("11");
                    BarEntryLabels2.add("12");
                    BarEntryLabels2.add("13");
                    BarEntryLabels2.add("14");
                    BarEntryLabels2.add("15");
                    BarEntryLabels2.add("16");
                    BarEntryLabels2.add("17");
                    BarEntryLabels2.add("18");
                    BarEntryLabels2.add("19");
                    BarEntryLabels2.add("20");
                    BarEntryLabels2.add("21");
                    BarEntryLabels2.add("22");
                    BarEntryLabels2.add("23");

                    BARDATA2 = new BarData(BarEntryLabels2, Bardataset2);
                    chart2.setData(BARDATA2);
                    chart2.setDescription("單位：分鐘");
                    chart2.animateY(2000);


                    int color2 = getResources().getColor(R.color.colorBar2);
                    Bardataset2.setColor(color2);

                    //專案時間分配
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TimeAnalyzeActivity.this);
                    String id = preferences.getString("Id", "");

                    HashMap postData = new HashMap();
                    postData.put("txtID", id);

                    PostResponseAsyncTask projectR = new PostResponseAsyncTask(TimeAnalyzeActivity.this, postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            projectlist = new JsonConverter<ProjectRead>().toArrayList(s, ProjectRead.class);
                            project = new ArrayList<String>();
                            projectdone = new ArrayList<String>();
                            timedone = new ArrayList<Integer>();
                            timetotal = new ArrayList<Integer>();
                            projectcount = new ArrayList<Integer>();
                            PIEENTRY = new ArrayList<>();
                            PieEntryLabels = new ArrayList<String>();
                            int tcount = 0;
                            if (projectlist == null){
                                txt_c3.setText(txt_c3.getText()+"尚未任何設置專案");
                            }
                            else {
                                for(int y = 0; y<projectlist.size(); y++){
                                    project.add(projectlist.get(y).projectname);
                                }

                                for (int i = 0; i<timelist.size(); i++){
                                    projectdone.add(timelist.get(i).project);
                                }

                                for (int i = 0; i<timelist.size(); i++){
                                    timedone.add(timelist.get(i).worktime);
                                }

                                for (int i = 0; i<projectlist.size(); i++){
                                    for (int j = 0; j<projectdone.size(); j++){
                                        if(projectdone.get(j).equals(project.get(i))){
                                            tcount = tcount + Integer.valueOf(timedone.get(j));
                                        }

                                    }
                                    timetotal.add(tcount);
                                    tcount = 0;
                                }

                                for (int j = 0; j<projectlist.size(); j++){
                                    int pcount = Collections.frequency(projectdone, projectlist.get(j).projectname);

                                    projectcount.add(pcount);
                                }

                                for (int x = 0; x<projectlist.size(); x++){


                                    PIEENTRY.add(new Entry((float)timetotal.get(x)*100/timecount, x));

                                    Piedataset = new PieDataSet(PIEENTRY, "專案時間分配");

                                    PieEntryLabels.add(project.get(x));


                                }
                                PIEDATA = new PieData(PieEntryLabels, Piedataset);
                                chart3.setData(PIEDATA);
                                Piedataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                chart3.animateY(2000);
                                chart3.setDescription("單位：%");
                                chart3.setDescriptionTextSize(18);
                                chart3.getLegend().setEnabled(false);
                            }
                        }
                    });
                    projectR.execute("http://140.135.113.107/team/test/projectread.php");




                }

            }
        });
        tintro.execute("http://140.135.113.107/team/test/timedoneread.php");



    }



}
