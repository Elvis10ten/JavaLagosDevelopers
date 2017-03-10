package mobymagic.com.javalagos.data.source;

import android.support.annotation.StringRes;

import java.util.List;

import mobymagic.com.javalagos.data.model.User;

public interface GithubUserDataListener {

    void onLoadStarted();
    void onLoadFailed(@StringRes int errorMsgRes);
    void onLoadSuccess(int page, List<User> user);
}
