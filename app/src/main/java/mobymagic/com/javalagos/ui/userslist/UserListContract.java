package mobymagic.com.javalagos.ui.userslist;

import java.util.List;

import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.ui.base.RemoteView;

interface UserListContract {

    interface ViewActions {

        void onHardWorkersRequested(int nextPage);
        void onNewbiesRequested(int nextPage);
        void onCharmersRequested(int nextPage);
        void onSearchRequested(String query, int nextPage);

    }

    interface UserListView extends RemoteView {

        void onUsersLoaded(List<User> users);

    }
}
