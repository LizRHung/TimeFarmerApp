package project.tthree;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ExplainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();

        ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<ArrayList<HashMap<String, String>>>();

        HashMap<String, String> groupA = new HashMap<String, String>();
        groupA.put("group", "養成");
        HashMap<String, String> groupB = new HashMap<String, String>();
        groupB.put("group", "計時");
        HashMap<String, String> groupC = new HashMap<String, String>();
        groupC.put("group", "專案");
        HashMap<String, String> groupD = new HashMap<String, String>();
        groupD.put("group", "會員");

        groupData.add(groupA);
        groupData.add(groupB);
        groupData.add(groupC);
        groupData.add(groupD);

        ArrayList<HashMap<String, String>> childListA = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childAA = new HashMap<String, String>();
        HashMap<String, String> childAB = new HashMap<String, String>();
        HashMap<String, String> childAC = new HashMap<String, String>();
        childAA.put("name","點擊查看番茄盆栽成長狀況、番茄收穫數量。");
        childAB.put("name","番茄一小時成長一階段，經歷五階段獲得一顆番茄。");
        childAC.put("name","凡超過24小時未使用則進入死亡階段，每24小時下降一階段。");

        childListA.add(childAA);
        childListA.add(childAB);
        childListA.add(childAC);

        childData.add(childListA);

        ArrayList<HashMap<String, String>> childListB = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childBA = new HashMap<String, String>();
        HashMap<String, String> childBB = new HashMap<String, String>();
        childBA.put("name","選取欲進行專案或任務，按下開始進行計時。");
        childBB.put("name","時間累積影響番茄成長！");

        childListB.add(childBA);
        childListB.add(childBB);

        childData.add(childListB);

        ArrayList<HashMap<String, String>> childListC = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childCA = new HashMap<String, String>();
        childCA.put("name","建立專案、新增任務，讓自己要做的事情更加明確。");

        childListC.add(childCA);

        childData.add(childListC);

        ArrayList<HashMap<String, String>> childListD = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childDA = new HashMap<String, String>();
        childDA.put("name","包含會員資料、好友管理、排行競賽、行事曆、時間分析、時間設定等功能。");

        childListD.add(childDA);

        childData.add(childListD);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getApplicationContext(), groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "group" }, new int[] { android.R.id.text1 },
                childData, android.R.layout.simple_expandable_list_item_1,
                new String[] { "name", "group" }, new int[] {
                android.R.id.text1, android.R.id.text2 });

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.ExpandablelistView);
        listView.setAdapter(adapter);
    }

}
