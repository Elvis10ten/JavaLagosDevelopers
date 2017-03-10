package mobymagic.com.javalagos.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    public void getHardworkers(@NonNull RemoteCallback<UserResponse> listener,
                               @Nullable String accessToken, int page) {
        mGithubUserService.getUsers("location:lagos language:java", accessToken, "repositories", "desc", page)
                .enqueue(listener);
    }

    public void getNewbies(@NonNull RemoteCallback<UserResponse> listener,
                               @Nullable String accessToken, int page) {
        mGithubUserService.getUsers("location:lagos language:java", accessToken, "joined", "desc", page)
                .enqueue(listener);
    }

    public void getCharmers(@NonNull RemoteCallback<UserResponse> listener,
                               @Nullable String accessToken, int page) {
        mGithubUserService.getUsers("location:lagos language:java", accessToken, "followers", "desc", page)
                .enqueue(listener);
    }

    public void search(@NonNull RemoteCallback<UserResponse> listener,
                       @Nullable String accessToken, int page, @NonNull String searchQuery) {
        mGithubUserService.getUsers(searchQuery + " location:lagos language:java", accessToken,
                "repositories", "desc", page).enqueue(listener);
    }
}