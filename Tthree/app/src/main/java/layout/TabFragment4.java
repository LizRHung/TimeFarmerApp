package layout;

//設定頁面

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import project.tthree.CalendarActivity;
import project.tthree.ExplainActivity;
import project.tthree.FriendActivity;
import project.tthree.LoginActivity;
import project.tthree.R;
import project.tthree.RankActivity;
import project.tthree.SettingActivity;
import project.tthree.TimeAnalyzeActivity;
import project.tthree.UserActivity;


public class TabFragment4 extends Fragment implements AdapterView.OnItemClickListener {

    private View v;

    String[] member = {"會員資料","好友管理","排行競賽","行事曆","時間分析","設定","說明","登出"};
    int[] icons = {R.drawable.user, R.drawable.friend, R.drawable.rank,
                   R.drawable.calendar, R.drawable.timetable, R.drawable.settings,
                   R.drawable.help, R.drawable.logout};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_fragment_4, container, false);

        GridView grid = (GridView) v.findViewById(R.id.membergrid);
        IconAdapter gAdapter = new IconAdapter();
        grid.setAdapter(gAdapter);
        grid.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch ((int)l){
            case R.drawable.user:
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), UserActivity.class);
                startActivity(intent1);
                break;
            case R.drawable.friend:
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), FriendActivity.class);
                startActivity(intent2);
                break;
            case R.drawable.rank:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(), RankActivity.class);
                startActivity(intent3);
                break;
            case R.drawable.calendar:
                Intent intent4 = new Intent();
                intent4.setClass(getActivity(), CalendarActivity.class);
                startActivity(intent4);
                break;
            case R.drawable.timetable:
                Intent intent5 = new Intent();
                intent5.setClass(getActivity(), TimeAnalyzeActivity.class);
                startActivity(intent5);
                break;
            case R.drawable.settings:
                Intent intent6 = new Intent();
                intent6.setClass(getActivity(), SettingActivity.class);
                startActivity(intent6);
                break;
            case R.drawable.help:
                Intent intent7 = new Intent();
                intent7.setClass(getActivity(), ExplainActivity.class);
                startActivity(intent7);
                break;
            case R.drawable.logout:
                Intent intent8 = new Intent();
                intent8.setClass(getActivity(), LoginActivity.class);
                intent8.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent8);
                break;

        }
    }

    class IconAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return member.length;
        }
        @Override
        public Object getItem(int position){
            return member[position];
        }
        @Override
        public long getItemId(int position){
            return icons[position];
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            if(row == null){
                row = getActivity().getLayoutInflater().inflate(R.layout.item_row, null);
                ImageView image = (ImageView) row.findViewById(R.id.item_image);
                TextView text = (TextView) row.findViewById(R.id.item_text);
                image.setImageResource(icons[position]);
                text.setText(member[position]);
            }
            return row;
        }
    }


}
