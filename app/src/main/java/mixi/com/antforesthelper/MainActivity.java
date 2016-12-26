package mixi.com.antforesthelper;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoLuncher();
    }

    /**
     * 自动解锁屏幕
     */
    private void autoUnLockScreen(){
        MiXiAlarmManage alarmManage = MiXiAlarmManage.getAlarmManger();
        alarmManage.initWakeUp(getApplicationContext());
        alarmManage.wakeupScreen();
    }


    /**
     * 自动启动app
     */
    private void autoLuncher() {
        MiXiAlarmManage alarmManage = MiXiAlarmManage.getAlarmManger();
        alarmManage.init(getApplicationContext());
        Intent intent = new Intent(this,LauncherReceiver.class);
        intent.setAction("start");
        PendingIntent pintent = PendingIntent.getBroadcast(this,0,intent,0);
        try {
            Date pData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-12-26 13:42:30");
            alarmManage.set(pData.getTime(),pintent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
