package mobymagic.com.javalagos.ui.home;

import android.support.annotation.NonNull;

import mobymagic.com.javalagos.ui.base.BasePresenter;

class HomePresenter extends BasePresenter<HomeContract.HomeView> implements
        HomeContract.ViewActions {

    HomePresenter(@NonNull HomeContract.HomeView view) {
        super(view);
    }

    @Override
    public void onSearchRequested() {
        if(getView() != null) {
            getView().viewSearch();
        }
    }
}
