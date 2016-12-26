package mixi.com.antforesthelper;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;


public class AlipayGphoneService  extends AccessibilityService{
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        CharSequence className = event.getClassName();
        if(className!= null){
            Log.e("mixi_Alipay",""+className);
        }

        //TODO 自动点击界面 开始规划
    }

    @Override
    public void onInterrupt() {

    }
}
