package mobymagic.com.javalagos.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class WirelessUtils {

    private static final String LOG_TAG = "WirelessUtils";

    /**
     * Checks if the device is currently connected to the internet. Note this method is not
     * a guarantee that network calls will succeed, it only tells if the device data connection is on
     * or the device is connected to a wifi. The only way to truly confirm is to make a network call
     * @param context Any context
     * @return true if the device is connected to the internet, false otherwise
     */
    public static boolean isConnected(@NonNull Context context) {
        LogUtils.v(LOG_TAG, "isConnected called");
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Checks if the user is connected to a wifi network
     * @param context Any context
     * @return true if the user is connected to a wifi network, false otherwise
     */
    public static boolean isConnectedToWifi(@NonNull Context context) {
        LogUtils.d(LOG_TAG, "isConnectedToWifi called");
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
                && activeNetwork.isConnectedOrConnecting();
    }

}
