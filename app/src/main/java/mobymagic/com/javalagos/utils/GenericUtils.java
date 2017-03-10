package mobymagic.com.javalagos.utils;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;

public class GenericUtils {

    /**
     * Helper method that uses the new Html.fromHtml on Nougat and above devices while using
     * the deprecated one on Lower devices with API version lower than Nougat
     * @param text The html text
     * @return A spanned html text
     */
    public static @NonNull Spanned fromHtml(@NonNull String text) {
        if (VersionUtils.hasNougat()) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            //noinspection deprecation
            return Html.fromHtml(text);
        }
    }

}
