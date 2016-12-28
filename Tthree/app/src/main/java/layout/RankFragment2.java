package layout;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

import project.tthree.R;
import project.tthree.RankRead;
import project.tthree.UserInformation;


public class RankFragment2 extends android.support.v4.app.Fragment {

    View v;

    private ArrayList<RankRead> ranklistP;
    private ArrayList<RankRead> ranklistF;
    private ArrayList<UserInformation> userlist;

    TextView txt_fscore;
    TextView f_s1, f_s2, f_s3, f_s4, f_s5, f_s6, f_s7, f_s8, f_s9, f_s10;
    TextView f_n1, f_n2, f_n3, f_n4, f_n5, f_n6, f_n7, f_n8, f_n9, f_n10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.rank_fragment2, container, false);

        txt_fscore = (TextView) v.findViewById(R.id.txt_fscore);
        f_s1 = (TextView) v.findViewById(R.id.f_s1);
        f_s2 = (TextView) v.findViewById(R.id.f_s2);
        f_s3 = (TextView) v.findViewById(R.id.f_s3);
        f_s4 = (TextView) v.findViewById(R.id.f_s4);
        f_s5 = (TextView) v.findViewById(R.id.f_s5);
        f_s6 = (TextView) v.findViewById(R.id.f_s6);
        f_s7 = (TextView) v.findViewById(R.id.f_s7);
        f_s8 = (TextView) v.findViewById(R.id.f_s8);
        f_s9 = (TextView) v.findViewById(R.id.f_s9);
        f_s10 = (TextView) v.findViewById(R.id.f_s10);
        f_n1 = (TextView) v.findViewById(R.id.f_n1);
        f_n2 = (TextView) v.findViewById(R.id.f_n2);
        f_n3 = (TextView) v.findViewById(R.id.f_n3);
        f_n4 = (TextView) v.findViewById(R.id.f_n4);
        f_n5 = (TextView) v.findViewById(R.id.f_n5);
        f_n6 = (TextView) v.findViewById(R.id.f_n6);
        f_n7 = (TextView) v.findViewById(R.id.f_n7);
        f_n8 = (TextView) v.findViewById(R.id.f_n8);
        f_n9 = (TextView) v.findViewById(R.id.f_n9);
        f_n10 = (TextView) v.findViewById(R.id.f_n10);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);

        PostResponseAsyncTask rankP = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                ranklistP = new JsonConverter<RankRead>().toArrayList(s, RankRead.class);

                if(ranklistP == null){
                    txt_fscore.setText(txt_fscore.getText()+"--");
                }
                else{
                    txt_fscore.setText(txt_fscore.getText()+String.valueOf(ranklistP.get(0).score)+" 分");
                }
            }
        });
        rankP.execute("http://140.135.113.107/team/test/rankreadP.php");

        PostResponseAsyncTask rankF = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                ranklistF = new JsonConverter<RankRead>().toArrayList(s, RankRead.class);

                if(ranklistF == null){
                    f_s1.setText("--");
                    f_s2.setText("--");
                    f_s3.setText("--");
                    f_s4.setText("--");
                    f_s5.setText("--");
                    f_s6.setText("--");
                    f_s7.setText("--");
                    f_s8.setText("--");
                    f_s9.setText("--");
                    f_s10.setText("--");
                    f_n1.setText("--");
                    f_n2.setText("--");
                    f_n3.setText("--");
                    f_n4.setText("--");
                    f_n5.setText("--");
                    f_n6.setText("--");
                    f_n7.setText("--");
                    f_n8.setText("--");
                    f_n9.setText("--");
                    f_n10.setText("--");
                }
                else{
                    if(ranklistF.size() == 1){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                    }
                    else if(ranklistF.size() == 2){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                    }
                    else if(ranklistF.size() == 3){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                    }
                    else if(ranklistF.size() == 4){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                    }
                    else if(ranklistF.size() == 5){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map4 = new HashMap();
                        map4.put("txtID", ranklistF.get(4).id);
                        PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n5.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname4.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                        f_s5.setText(String.valueOf(ranklistF.get(4).score));
                    }
                    else if(ranklistF.size() == 6){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map4 = new HashMap();
                        map4.put("txtID", ranklistF.get(4).id);
                        PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n5.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname4.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map5 = new HashMap();
                        map5.put("txtID", ranklistF.get(5).id);
                        PostResponseAsyncTask getname5 = new PostResponseAsyncTask(getActivity(), map5, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n6.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname5.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                        f_s5.setText(String.valueOf(ranklistF.get(4).score));
                        f_s6.setText(String.valueOf(ranklistF.get(5).score));
                    }
                    else if(ranklistF.size() == 7){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map4 = new HashMap();
                        map4.put("txtID", ranklistF.get(4).id);
                        PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n5.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname4.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map5 = new HashMap();
                        map5.put("txtID", ranklistF.get(5).id);
                        PostResponseAsyncTask getname5 = new PostResponseAsyncTask(getActivity(), map5, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n6.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname5.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map6 = new HashMap();
                        map6.put("txtID", ranklistF.get(6).id);
                        PostResponseAsyncTask getname6 = new PostResponseAsyncTask(getActivity(), map6, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n7.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname6.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                        f_s5.setText(String.valueOf(ranklistF.get(4).score));
                        f_s6.setText(String.valueOf(ranklistF.get(5).score));
                        f_s7.setText(String.valueOf(ranklistF.get(6).score));
                    }
                    else if(ranklistF.size() == 8){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map4 = new HashMap();
                        map4.put("txtID", ranklistF.get(4).id);
                        PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n5.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname4.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map5 = new HashMap();
                        map5.put("txtID", ranklistF.get(5).id);
                        PostResponseAsyncTask getname5 = new PostResponseAsyncTask(getActivity(), map5, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n6.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname5.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map6 = new HashMap();
                        map6.put("txtID", ranklistF.get(6).id);
                        PostResponseAsyncTask getname6 = new PostResponseAsyncTask(getActivity(), map6, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n7.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname6.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map7 = new HashMap();
                        map7.put("txtID", ranklistF.get(7).id);
                        PostResponseAsyncTask getname7 = new PostResponseAsyncTask(getActivity(), map7, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n8.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname7.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                        f_s5.setText(String.valueOf(ranklistF.get(4).score));
                        f_s6.setText(String.valueOf(ranklistF.get(5).score));
                        f_s7.setText(String.valueOf(ranklistF.get(6).score));
                        f_s8.setText(String.valueOf(ranklistF.get(7).score));
                    }
                    else if(ranklistF.size() == 9){
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map4 = new HashMap();
                        map4.put("txtID", ranklistF.get(4).id);
                        PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n5.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname4.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map5 = new HashMap();
                        map5.put("txtID", ranklistF.get(5).id);
                        PostResponseAsyncTask getname5 = new PostResponseAsyncTask(getActivity(), map5, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n6.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname5.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map6 = new HashMap();
                        map6.put("txtID", ranklistF.get(6).id);
                        PostResponseAsyncTask getname6 = new PostResponseAsyncTask(getActivity(), map6, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n7.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname6.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map7 = new HashMap();
                        map7.put("txtID", ranklistF.get(7).id);
                        PostResponseAsyncTask getname7 = new PostResponseAsyncTask(getActivity(), map7, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n8.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname7.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map8 = new HashMap();
                        map8.put("txtID", ranklistF.get(8).id);
                        PostResponseAsyncTask getname8 = new PostResponseAsyncTask(getActivity(), map8, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n9.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname8.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                        f_s5.setText(String.valueOf(ranklistF.get(4).score));
                        f_s6.setText(String.valueOf(ranklistF.get(5).score));
                        f_s7.setText(String.valueOf(ranklistF.get(6).score));
                        f_s8.setText(String.valueOf(ranklistF.get(7).score));
                        f_s9.setText(String.valueOf(ranklistF.get(8).score));
                    }
                    else{
                        HashMap map0 = new HashMap();
                        map0.put("txtID", ranklistF.get(0).id);
                        PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n1.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname0.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map1 = new HashMap();
                        map1.put("txtID", ranklistF.get(1).id);
                        PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n2.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname1.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map2 = new HashMap();
                        map2.put("txtID", ranklistF.get(2).id);
                        PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n3.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname2.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map3 = new HashMap();
                        map3.put("txtID", ranklistF.get(3).id);
                        PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n4.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname3.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map4 = new HashMap();
                        map4.put("txtID", ranklistF.get(4).id);
                        PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n5.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname4.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map5 = new HashMap();
                        map5.put("txtID", ranklistF.get(5).id);
                        PostResponseAsyncTask getname5 = new PostResponseAsyncTask(getActivity(), map5, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n6.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname5.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map6 = new HashMap();
                        map6.put("txtID", ranklistF.get(6).id);
                        PostResponseAsyncTask getname6 = new PostResponseAsyncTask(getActivity(), map6, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n7.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname6.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map7 = new HashMap();
                        map7.put("txtID", ranklistF.get(7).id);
                        PostResponseAsyncTask getname7 = new PostResponseAsyncTask(getActivity(), map7, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n8.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname7.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map8 = new HashMap();
                        map8.put("txtID", ranklistF.get(8).id);
                        PostResponseAsyncTask getname8 = new PostResponseAsyncTask(getActivity(), map8, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n9.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname8.execute("http://140.135.113.107/team/test/userread.php");
                        HashMap map9 = new HashMap();
                        map9.put("txtID", ranklistF.get(9).id);
                        PostResponseAsyncTask getname9 = new PostResponseAsyncTask(getActivity(), map9, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                                f_n10.setText(String.valueOf(userlist.get(0).username));
                            }
                        });
                        getname9.execute("http://140.135.113.107/team/test/userread.php");
                        f_s1.setText(String.valueOf(ranklistF.get(0).score));
                        f_s2.setText(String.valueOf(ranklistF.get(1).score));
                        f_s3.setText(String.valueOf(ranklistF.get(2).score));
                        f_s4.setText(String.valueOf(ranklistF.get(3).score));
                        f_s5.setText(String.valueOf(ranklistF.get(4).score));
                        f_s6.setText(String.valueOf(ranklistF.get(5).score));
                        f_s7.setText(String.valueOf(ranklistF.get(6).score));
                        f_s8.setText(String.valueOf(ranklistF.get(7).score));
                        f_s9.setText(String.valueOf(ranklistF.get(8).score));
                        f_s10.setText(String.valueOf(ranklistF.get(9).score));
                    }
                }
            }
        });
        rankF.execute("http://140.135.113.107/team/test/rankreadF.php");

        return v;
    }
}
