package mm.com.sumyat.nurse;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;
import me.myatminsoe.mdetect.MDetect;
import mm.com.sumyat.nurse.helper.LocaleHelper;

/**
 * Created by sumyathtun on 6/30/18.
 */

public class NurseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MDetect.INSTANCE.init(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        if (BuildConfig.DEBUG) {
            final Fabric fabric = new Fabric.Builder(this)
                    .kits(new Crashlytics())
                    .debuggable(true)           // Enables Crashlytics debugger
                    .build();
            Fabric.with(fabric);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}
