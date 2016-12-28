package layout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

import project.tthree.R;
import project.tthree.RankRead;
import project.tthree.UserInformation;

public class RankFragment1 extends android.support.v4.app.Fragment {

    View v;

    private ArrayList<RankRead> ranklistP;
    private ArrayList<RankRead> ranklistW;
    private ArrayList<UserInformation> userlist;

    TextView txt_wscore;
    TextView w_s1, w_s2, w_s3, w_s4, w_s5, w_s6, w_s7, w_s8, w_s9, w_s10;
    TextView w_n1, w_n2, w_n3, w_n4, w_n5, w_n6, w_n7, w_n8, w_n9, w_n10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.rank_fragment1, container, false);

        txt_wscore = (TextView) v.findViewById(R.id.txt_wscore);
        w_s1 = (TextView) v.findViewById(R.id.w_s1);
        w_s2 = (TextView) v.findViewById(R.id.w_s2);
        w_s3 = (TextView) v.findViewById(R.id.w_s3);
        w_s4 = (TextView) v.findViewById(R.id.w_s4);
        w_s5 = (TextView) v.findViewById(R.id.w_s5);
        w_s6 = (TextView) v.findViewById(R.id.w_s6);
        w_s7 = (TextView) v.findViewById(R.id.w_s7);
        w_s8 = (TextView) v.findViewById(R.id.w_s8);
        w_s9 = (TextView) v.findViewById(R.id.w_s9);
        w_s10 = (TextView) v.findViewById(R.id.w_s10);
        w_n1 = (TextView) v.findViewById(R.id.w_n1);
        w_n2 = (TextView) v.findViewById(R.id.w_n2);
        w_n3 = (TextView) v.findViewById(R.id.w_n3);
        w_n4 = (TextView) v.findViewById(R.id.w_n4);
        w_n5 = (TextView) v.findViewById(R.id.w_n5);
        w_n6 = (TextView) v.findViewById(R.id.w_n6);
        w_n7 = (TextView) v.findViewById(R.id.w_n7);
        w_n8 = (TextView) v.findViewById(R.id.w_n8);
        w_n9 = (TextView) v.findViewById(R.id.w_n9);
        w_n10 = (TextView) v.findViewById(R.id.w_n10);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);

        PostResponseAsyncTask rankP = new PostResponseAsyncTask(getActivity(), postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                ranklistP = new JsonConverter<RankRead>().toArrayList(s, RankRead.class);

                if(ranklistP == null){
                    txt_wscore.setText(txt_wscore.getText()+"--");
                }
                else{
                    txt_wscore.setText(txt_wscore.getText()+String.valueOf(ranklistP.get(0).score)+" 分");
                }
            }
        });
        rankP.execute("http://140.135.113.107/team/test/rankreadP.php");

        PostResponseAsyncTask rankW = new PostResponseAsyncTask(getActivity(), new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                ranklistW = new JsonConverter<RankRead>().toArrayList(s, RankRead.class);

                if(ranklistW == null){
                    w_s1.setText("--");
                    w_s2.setText("--");
                    w_s3.setText("--");
                    w_s4.setText("--");
                    w_s5.setText("--");
                    w_s6.setText("--");
                    w_s7.setText("--");
                    w_s8.setText("--");
                    w_s9.setText("--");
                    w_s10.setText("--");
                    w_n1.setText("--");
                    w_n2.setText("--");
                    w_n3.setText("--");
                    w_n4.setText("--");
                    w_n5.setText("--");
                    w_n6.setText("--");
                    w_n7.setText("--");
                    w_n8.setText("--");
                    w_n9.setText("--");
                    w_n10.setText("--");
                }
                else{
                    w_s1.setText(String.valueOf(ranklistW.get(0).score));
                    w_s2.setText(String.valueOf(ranklistW.get(1).score));
                    w_s3.setText(String.valueOf(ranklistW.get(2).score));
                    w_s4.setText(String.valueOf(ranklistW.get(3).score));
                    w_s5.setText(String.valueOf(ranklistW.get(4).score));
                    w_s6.setText(String.valueOf(ranklistW.get(5).score));
                    w_s7.setText(String.valueOf(ranklistW.get(6).score));
                    w_s8.setText(String.valueOf(ranklistW.get(7).score));
                    w_s9.setText(String.valueOf(ranklistW.get(8).score));
                    w_s10.setText(String.valueOf(ranklistW.get(9).score));


                    HashMap map0 = new HashMap();
                    map0.put("txtID", ranklistW.get(0).id);
                    PostResponseAsyncTask getname0 = new PostResponseAsyncTask(getActivity(), map0, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n1.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname0.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map1 = new HashMap();
                    map1.put("txtID", ranklistW.get(1).id);
                    PostResponseAsyncTask getname1 = new PostResponseAsyncTask(getActivity(), map1, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n2.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname1.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map2 = new HashMap();
                    map2.put("txtID", ranklistW.get(2).id);
                    PostResponseAsyncTask getname2 = new PostResponseAsyncTask(getActivity(), map2, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n3.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname2.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map3 = new HashMap();
                    map3.put("txtID", ranklistW.get(3).id);
                    PostResponseAsyncTask getname3 = new PostResponseAsyncTask(getActivity(), map3, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n4.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname3.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map4 = new HashMap();
                    map4.put("txtID", ranklistW.get(4).id);
                    PostResponseAsyncTask getname4 = new PostResponseAsyncTask(getActivity(), map4, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n5.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname4.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map5 = new HashMap();
                    map5.put("txtID", ranklistW.get(5).id);
                    PostResponseAsyncTask getname5 = new PostResponseAsyncTask(getActivity(), map5, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n6.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname5.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map6 = new HashMap();
                    map6.put("txtID", ranklistW.get(6).id);
                    PostResponseAsyncTask getname6 = new PostResponseAsyncTask(getActivity(), map6, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n7.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname6.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map7 = new HashMap();
                    map7.put("txtID", ranklistW.get(7).id);
                    PostResponseAsyncTask getname7 = new PostResponseAsyncTask(getActivity(), map7, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n8.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname7.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map8 = new HashMap();
                    map8.put("txtID", ranklistW.get(8).id);
                    PostResponseAsyncTask getname8 = new PostResponseAsyncTask(getActivity(), map8, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n9.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname8.execute("http://140.135.113.107/team/test/userread.php");

                    HashMap map9 = new HashMap();
                    map9.put("txtID", ranklistW.get(9).id);
                    PostResponseAsyncTask getname9 = new PostResponseAsyncTask(getActivity(), map9, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            userlist = new JsonConverter<UserInformation>().toArrayList(s, UserInformation.class);
                            w_n10.setText(String.valueOf(userlist.get(0).username));
                        }
                    });
                    getname9.execute("http://140.135.113.107/team/test/userread.php");
                }
            }
        });
        rankW.execute("http://140.135.113.107/team/test/rankreadW.php");

        return v;
    }



}
