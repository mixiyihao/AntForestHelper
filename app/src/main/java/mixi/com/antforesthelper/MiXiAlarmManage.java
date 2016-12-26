package mixi.com.antforesthelper;


import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.PowerManager;

public class MiXiAlarmManage {
    private static MiXiAlarmManage mAlarmMnager = new MiXiAlarmManage();


    private AlarmManager mSystemService;
    private KeyguardManager mKeyguardManager;
    private PowerManager mPowerManager;

    public class NotInitException extends NullPointerException {
        public NotInitException() {
            super("AlarmManger 未初始化 请调用 init(Context)");
        }
    }


    public static synchronized MiXiAlarmManage getAlarmManger() {

        return mAlarmMnager;
    }

    public void init(Context context) {
        this.mSystemService = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

    }


    public void set(long time, PendingIntent intent) {
        if (this.mSystemService == null) {
            throw new NotInitException();
        }

        mSystemService.set(AlarmManager.RTC_WAKEUP, time, intent);

    }

    public void initWakeUp(Context context) {
        this.mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        this.mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

    }

    /**
     * 自动唤醒 注意：只有没有加密码的情况下 才可以使用
     */
    public void wakeupScreen() {
        PowerManager.WakeLock wakeLock = mPowerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "mixi tag");
        wakeLock.acquire();
        KeyguardManager.KeyguardLock unlok = this.mKeyguardManager.newKeyguardLock("unlok");
        unlok.disableKeyguard();
        wakeLock.release();

    }


}
