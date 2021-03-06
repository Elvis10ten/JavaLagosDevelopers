package mobymagic.com.javalagos.utils;

import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

public class ColorUtils {

    /**
     * Get the most populous swatch in the palette.
     * @param palette A palette
     * @return The most populous swatch or null
     */
    public static @Nullable Palette.Swatch getMostPopulousSwatch(@Nullable Palette palette) {
        Palette.Swatch mostPopulous = null;
        if (palette != null) {
            for (Palette.Swatch swatch : palette.getSwatches()) {
                if (mostPopulous == null || swatch.getPopulation() > mostPopulous.getPopulation()) {
                    mostPopulous = swatch;
                }
            }
        }
        return mostPopulous;
    }

}
