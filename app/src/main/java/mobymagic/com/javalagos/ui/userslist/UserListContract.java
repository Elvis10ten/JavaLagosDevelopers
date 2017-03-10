package mobymagic.com.javalagos.ui.userslist;

import java.util.List;

import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.ui.base.RemoteView;

public interface UserListContract {

    interface ViewActions {

        void onHardworkersRequested(int nextPage);
        void onNewbiesRequested(int nextPage);
        void onCharmersRequested(int nextPage);
    }

    interface UserListView extends RemoteView {

        void onUsersLoaded(List<User> users);

    }
}
