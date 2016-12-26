package mixi.com.antforesthelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LauncherReceiver extends BroadcastReceiver {
    public LauncherReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("mixi","receiver start");
        MiXiAlarmManage alarmManger = MiXiAlarmManage.getAlarmManger();
        alarmManger.initWakeUp(context);
        alarmManger.wakeupScreen();
        startThirdApp(context);
    }

    public void startThirdApp(Context context){
        String packaName = "com.eg.android.AlipayGphone";
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packaName);
        context.startActivity(launchIntentForPackage);
    }
}
