package mobymagic.com.javalagos.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import mobymagic.com.javalagos.data.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubUserService {

    /**
     * Get github users matching the criteria
     */
    @GET("users?per_page=15")
    Call<UserResponse> getUsers(@NonNull @Query("q") String query,
                                @Nullable @Query("access_token") String accessToken,
                                @Nullable @Query("sort") String sort,
                                @Nullable @Query("order") String sortOrder,
                                @Query("page") int page);
}
