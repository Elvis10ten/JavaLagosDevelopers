package mobymagic.com.javalagos.data.source;

import java.util.List;

import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.data.model.UserResponse;

public interface GithubUsersDataSourceContract {

    interface Repository {
        void fetchNewbies(int currentPage);
        void fetchHardworkers(int currentPage);
        void fetchCharmers(int currentPage);
    }

    interface LocalDateSource {
        List<User> getPopularMovies(int currentPage);
        void saveNewbies(List<User> movies);
        void saveHardworkers(List<User> users);
        void saveCharmers(List<User> users);
    }

    interface RemoteDateSource {
        //        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        List<UserResponse> getNewbies(int currentPage);
        List<UserResponse> getHardworkers(int currentPage);
        List<UserResponse> getCharmers(int currentPage);
    }
}