package mobymagic.com.javalagos.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import mobymagic.com.javalagos.BuildConfig;

/**
 * A wrapper around {@link Log} that only logs to the logcat in debug mode.
 */
public class LogUtils {

    public static void d(@NonNull final String tag, @NonNull Object... messages) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, convertMessageArrayToString(messages));
        }
    }

    public static void v(@NonNull final String tag, @NonNull Object... messages) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, convertMessageArrayToString(messages));
        }
    }

    public static void i(@NonNull final String tag, @NonNull Object... messages) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, convertMessageArrayToString(messages));
        }
    }

    public static void w(@NonNull final String tag, @NonNull Object... messages) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, convertMessageArrayToString(messages));
        }
        //FirebaseCrash.log(convertMessageArrayToString(messages));
    }

    public static void e(@NonNull final String tag, @NonNull Object... messages) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, convertMessageArrayToString(messages));
        }
        //FirebaseCrash.log(convertMessageArrayToString(messages));
    }

    public static void e(@NonNull final String tag, @NonNull Throwable e) {
        e.printStackTrace();
        //FirebaseCrash.report(e);

        if (BuildConfig.DEBUG) {
            Log.e(tag, e.toString());
        }
    }

    private static String convertMessageArrayToString(@NonNull Object... messages) {
        //Concat all string into one
        String result = "";
        for(Object message : messages) {
            result += message;
        }
        return result;
    }
}
