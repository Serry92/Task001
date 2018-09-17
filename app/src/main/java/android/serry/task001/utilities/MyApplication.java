package android.serry.task001.utilities;

import android.app.Application;

import org.jetbrains.annotations.Contract;

public class MyApplication extends Application {
    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Contract(pure = true)
    public static MyApplication getContext() {
        return mContext;
    }

}
