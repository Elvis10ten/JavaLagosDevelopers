package mobymagic.com.javalagos.data.source;

import java.util.List;

import mobymagic.com.javalagos.data.model.UserResponse;
import mobymagic.com.javalagos.data.remote.GithubUserService;

public class GithubUserRemoteDataSource implements GithubUsersDataSourceContract.RemoteDateSource {

    private GithubUserService mGithubUserService;
    @Override
    public List<UserResponse> getNewbies(int currentPage) {
        return null;
    }

    @Override
    public List<UserResponse> getHardworkers(int currentPage) {
        return null;
    }

    @Override
    public List<UserResponse> getCharmers(int currentPage) {
        return null;
    }
}
