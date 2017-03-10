package mobymagic.com.javalagos.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

public interface RemoteView {

    void showProgress();
    void hideProgress();
    void showEmpty();
    void showError(@StringRes int errorMsgRes);
    Context getContext();

}