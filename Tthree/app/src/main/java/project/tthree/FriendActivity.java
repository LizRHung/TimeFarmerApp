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
import android.widget.EditText;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendActivity extends AppCompatActivity implements AsyncResponse {

    EditText ed_fid;
    private ListView lv_friend;
    private ArrayList<FriendRead> friendlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

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


        //ADD FRIEND
        LayoutInflater inflater = LayoutInflater.from(FriendActivity.this);
        final View v = inflater.inflate(R.layout.add_friend, null);

        ed_fid = (EditText) v.findViewById(R.id.ed_fid);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(FriendActivity.this);
        dialog.setView(v);
        dialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FriendActivity.this);
                String id = preferences.getString("Id", "");
                String fid = ed_fid.getText().toString();

                HashMap postData = new HashMap();
                postData.put("txtid", id);
                postData.put("txtfid", fid);

                PostResponseAsyncTask task = new PostResponseAsyncTask(FriendActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                      //  Toast.makeText(FriendActivity.this, s, Toast.LENGTH_LONG).show();
                        Intent refresh = new Intent(FriendActivity.this, FriendActivity.class);
                        startActivity(refresh);
                        FriendActivity.this.finish();
                    }
                });
                task.execute("http://140.135.113.107/team/test/friend.php");
            }
        });
        dialog.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent refresh = new Intent(FriendActivity.this, FriendActivity.class);
                startActivity(refresh);
                FriendActivity.this.finish();
            }
        });


        //fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_friend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


        //FRIEND LIST
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FriendActivity.this);
        String id = preferences.getString("Id", "");

        HashMap postData = new HashMap();
        postData.put("txtID", id);

        PostResponseAsyncTask friendread = new PostResponseAsyncTask(FriendActivity.this, postData, this);
        friendread.execute("http://140.135.113.107/team/test/friendread.php");
    }

    @Override
    public void processFinish(String s) {
        friendlist = new JsonConverter<FriendRead>().toArrayList(s, FriendRead.class);

        BindDictionary<FriendRead> dict = new BindDictionary<FriendRead>();
        dict.addStringField(R.id.txt_fid, new StringExtractor<FriendRead>() {
            @Override
            public String getStringValue(FriendRead item, int position) {
                return item.fid;
            }
        });

        FunDapter<FriendRead> adapter = new FunDapter<>(FriendActivity.this, friendlist, R.layout.layout_list_friend, dict);

        lv_friend = (ListView) findViewById(R.id.lv_friend);
        lv_friend.setAdapter(adapter);


        lv_friend.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeItemFromList(i);
                return true;
            }
        });
    }

    protected void removeItemFromList(int i){
        final int deletePosition = i;

        final AlertDialog.Builder alert = new AlertDialog.Builder(FriendActivity.this);

        alert.setTitle("刪除");
        alert.setMessage("確定要刪除這個好友嗎？");
        alert.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FriendRead selectfriend = friendlist.get(deletePosition);
                String id = null, fid = null;
                if(selectfriend != null){
                     id = selectfriend.id;
                     fid = selectfriend.fid;
                }
                HashMap postData = new HashMap();
                postData.put("txtID", id);
                postData.put("txtFID", fid);

                PostResponseAsyncTask deletefriend = new PostResponseAsyncTask(FriendActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        //Toast.makeText(FriendActivity.this, s, Toast.LENGTH_LONG).show();
                        Intent refresh = new Intent(FriendActivity.this, FriendActivity.class);
                        startActivity(refresh);
                        FriendActivity.this.finish();

                    }
                });
                deletefriend.execute("http://140.135.113.107/team/test/deletefriend.php");
            }
        });
        alert.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alert.show();
    }
}
