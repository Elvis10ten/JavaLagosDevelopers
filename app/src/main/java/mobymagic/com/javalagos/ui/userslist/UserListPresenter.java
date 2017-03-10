package mobymagic.com.javalagos.ui.userslist;

import android.support.annotation.NonNull;

import java.util.List;

import mobymagic.com.javalagos.data.DataManager;
import mobymagic.com.javalagos.data.model.UserResponse;
import mobymagic.com.javalagos.data.remote.RemoteCallback;
import mobymagic.com.javalagos.ui.base.BasePresenter;

public class UserListPresenter extends BasePresenter<UserListContract.UserListView> implements
        UserListContract.ViewActions {

    private DataManager mDataManager;

    public UserListPresenter(@NonNull UserListContract.UserListView view) {
        super(view);
        mDataManager = DataManager.getInstance(view.getContext());
    }

    @Override
    public void onHardworkersRequested(int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.getCharactersList(new RemoteCallback<UserResponse>() {
                @Override
                public void onSuccess(UserResponse response) {
                    if(getView() != null) {
                        getView().hideProgress();
                        getView().onUsersLoaded(response.getItems());
                    }
                }

                @Override
                public void onUnauthorized() {

                }

                @Override
                public void onFailed(Throwable throwable) {

                }
            });
        }
    }

    @Override
    public void onNewbiesRequested(int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.getCharactersList(new RemoteCallback<UserResponse>() {
                @Override
                public void onSuccess(UserResponse response) {
                    if(getView() != null) {
                        getView().onUsersLoaded(response.getItems());
                    }
                }

                @Override
                public void onUnauthorized() {

                }

                @Override
                public void onFailed(Throwable throwable) {

                }
            });
        }
    }

    @Override
    public void onCharmersRequested(int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.getCharactersList(new RemoteCallback<UserResponse>() {
                @Override
                public void onSuccess(UserResponse response) {
                    if(getView() != null) {
                        getView().onUsersLoaded(response.getItems());
                    }
                }

                @Override
                public void onUnauthorized() {

                }

                @Override
                public void onFailed(Throwable throwable) {

                }
            });
        }
    }
}
