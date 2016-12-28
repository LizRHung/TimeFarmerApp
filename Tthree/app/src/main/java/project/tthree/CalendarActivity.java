package project.tthree;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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

        //calendar
        TextView yLabel = (TextView) findViewById(R.id.yLabel);
        TextView mLabel = (TextView) findViewById(R.id.mLabel);
        TextView dLabel = (TextView) findViewById(R.id.dLabel);
        TextView eLabel = (TextView) findViewById(R.id.eLabel);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E", Locale.CHINESE);
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/", 0);

        yLabel.setText(values[0]);
        mLabel.setText(values[1]);
        dLabel.setText(values[2]);
        eLabel.setText(values[3]);

        //btn_addcalendar
        Button btn_addcalendar = (Button) findViewById(R.id.btn_addcalendar);
        btn_addcalendar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, "專案");
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "會議室");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "專案會議");

                GregorianCalendar calDate = new GregorianCalendar(2016, 10, 27);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                startActivity(calIntent);
            }
        });

    }

}
