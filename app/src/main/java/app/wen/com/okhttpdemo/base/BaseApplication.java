package app.wen.com.okhttpdemo.base;

import android.app.Application;

/**
 * Created by wangenning on 16/1/8.
 */
public class BaseApplication extends Application {
//    private RequestManager glideRequest;

    @Override
    public void onCreate() {
        super.onCreate();
//        glideRequest = Glide.with(getApplicationContext());
    }
}
