package layout;

//專案頁面

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import project.tthree.ProjectDetailActivity;
import project.tthree.ProjectRead;
import project.tthree.R;


public class TabFragment3 extends Fragment implements AsyncResponse, AdapterView.OnItemClickListener {

    private View v;

    final String LOG = "TabFragment3";
    private ArrayList<ProjectRead> projectlist;
    private ListView lvProject;

    String timetype = "";
    String projecttype = "";

    Spinner spinner6;
    EditText edt_projectname, edt_time;
    RadioGroup prgroup;
    RadioButton radio_personal, radio_team;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_fragment_3, container, false);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(getActivity(), postData, this);
        taskRead.execute("http://140.135.113.107/team/test/projectread.php");



        //ADD_PROJECT
        LayoutInflater inflater1 = LayoutInflater.from(getActivity());
        final View v1 = inflater1.inflate(R.layout.add_project,null);

        //add_project
        edt_projectname = (EditText)v1.findViewById(R.id.edt_projectname);
        edt_time = (EditText)v1.findViewById(R.id.edt_time);
        prgroup = (RadioGroup)v1.findViewById(R.id.prgroup);
        radio_personal = (RadioButton)v1.findViewById(R.id.radio_personal);
        radio_team = (RadioButton)v1.findViewById(R.id.radio_team);

        spinner6 = (Spinner)v1.findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter6
                = ArrayAdapter.createFromResource(getActivity(), R.array.time_choose,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        //AlertDialog
        final  AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.add_project);
        dialog.setView(v1);
        dialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String id = preferences.getString("Id", "");
                String projectname = edt_projectname.getText().toString();
                String projecttime = edt_time.getText().toString();


                if(spinner6.getSelectedItemPosition() == 0){
                    timetype = "min";
                }
                else if(spinner6.getSelectedItemPosition() == 1){
                    timetype = "hour";
                }
                else if(spinner6.getSelectedItemPosition() == 2){
                    timetype = "day";
                }
                else if(spinner6.getSelectedItemPosition() == 3){
                    timetype = "month";
                }

                if(prgroup.getCheckedRadioButtonId() == radio_personal.getId()){
                    projecttype = "personal";
                }
                else if(prgroup.getCheckedRadioButtonId() == radio_team.getId()){
                    projecttype = "team";
                }

                HashMap postData = new HashMap();
                postData.put("txtid", id);
                postData.put("txtname", projectname);
                postData.put("txttime", projecttime);
                postData.put("txttimetype", timetype);
                postData.put("txttype", projecttype);

                PostResponseAsyncTask task = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(TabFragment3.this).attach(TabFragment3.this).commit();

                    }
                });
                task.execute("http://140.135.113.107/team/test/project.php");

            }
        });
        dialog.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(TabFragment3.this).attach(TabFragment3.this).commit();
            }
        });

        //fab_add
        FloatingActionButton fab_add = (FloatingActionButton) v.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        return v;


    }

    @Override
    public void processFinish(String s) {
        projectlist = new JsonConverter<ProjectRead>().toArrayList(s, ProjectRead.class);

        BindDictionary<ProjectRead> dict = new BindDictionary<ProjectRead>();
        dict.addStringField(R.id.txt_pname, new StringExtractor<ProjectRead>() {
            @Override
            public String getStringValue(ProjectRead item, int position) {
                return item.projectname;
            }
        });

        dict.addStringField(R.id.txt_ptime, new StringExtractor<ProjectRead>() {
            @Override
            public String getStringValue(ProjectRead item, int position) {
                return item.projecttime;
            }
        });

        dict.addStringField(R.id.txt_pttype, new StringExtractor<ProjectRead>() {
            @Override
            public String getStringValue(ProjectRead item, int position) {
                return item.timetype;
            }
        });

        dict.addStringField(R.id.txt_ptype, new StringExtractor<ProjectRead>() {
            @Override
            public String getStringValue(ProjectRead item, int position) {
                return item.projecttype;
            }
        });

        FunDapter<ProjectRead> adapter = new FunDapter<>(getActivity(), projectlist, R.layout.layout_list_project, dict );

        lvProject = (ListView)v.findViewById(R.id.lvProject);
        lvProject.setAdapter(adapter);
        lvProject.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProjectRead selectedProject = projectlist.get(position);
        Intent in = new Intent(getActivity(), ProjectDetailActivity.class);
        in.putExtra("project", (Serializable) selectedProject);
        startActivity(in);

    }
}
