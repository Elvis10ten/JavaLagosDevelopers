package mobymagic.com.javalagos.ui.home;

import mobymagic.com.javalagos.ui.base.RemoteView;

interface HomeContract {

    interface ViewActions {
        void onSearchRequested();
    }

    interface HomeView extends RemoteView {
        void viewSearch();
    }
}
