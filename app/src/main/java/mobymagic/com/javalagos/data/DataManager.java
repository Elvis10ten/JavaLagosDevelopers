package mobymagic.com.javalagos.data;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.data.model.UserResponse;
import mobymagic.com.javalagos.data.remote.GithubUserService;
import mobymagic.com.javalagos.data.remote.GithubUserServiceFactory;
import mobymagic.com.javalagos.data.remote.RemoteCallback;

/**
 * Api abstraction
 */
public class DataManager {

    private static DataManager sInstance;

    private final GithubUserService mGithubUserService;

    public static DataManager getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context);
        }
        return sInstance;
    }

    private DataManager(@NonNull Context context) {
        mGithubUserService = GithubUserServiceFactory.makeGithubUserService(context);
    }

    public void getCharactersList(RemoteCallback<UserResponse> listener) {
        mGithubUserService.getUsers("location:lagos language:java", null, null, null, null)
                .enqueue(listener);
    }
}