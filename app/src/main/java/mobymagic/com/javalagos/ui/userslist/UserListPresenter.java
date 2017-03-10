package mobymagic.com.javalagos.ui.userslist;

import android.support.annotation.NonNull;

import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.data.DataManager;
import mobymagic.com.javalagos.data.model.UserResponse;
import mobymagic.com.javalagos.data.remote.RemoteCallback;
import mobymagic.com.javalagos.ui.base.BasePresenter;
import mobymagic.com.javalagos.utils.LogUtils;

public class UserListPresenter extends BasePresenter<UserListContract.UserListView> implements
        UserListContract.ViewActions {

    private static final String LOG_TAG = "UserListPresenter";

    private DataManager mDataManager;

    UserListPresenter(@NonNull UserListContract.UserListView view) {
        super(view);
        mDataManager = DataManager.getInstance(view.getContext());
    }

    @Override
    public void onHardWorkersRequested(int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.getHardworkers(mRemoteCallback, null, nextPage);
        }
    }

    @Override
    public void onNewbiesRequested(int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.getNewbies(mRemoteCallback, null, nextPage);
        }
    }

    @Override
    public void onCharmersRequested(int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.getCharmers(mRemoteCallback, null, nextPage);
        }
    }

    @Override
    public void onSearchRequested(String query, int nextPage) {
        if(getView() != null) {
            getView().showProgress();
            mDataManager.search(mRemoteCallback, null, nextPage, query);
        }
    }

    private RemoteCallback<UserResponse> mRemoteCallback = new RemoteCallback<UserResponse>() {
        @Override
        public void onSuccess(UserResponse response) {
            LogUtils.d(LOG_TAG, "On request success");
            if(getView() != null) {
                getView().hideProgress();
                if(response.getItems().isEmpty()) {
                    getView().showEmpty();
                } else {
                    getView().onUsersLoaded(response.getItems());
                }
            }
        }

        @Override
        public void onUnauthorized() {
            //TODO attempt request again with access token
        }

        @Override
        public void onFailed(Throwable throwable) {
            LogUtils.e(LOG_TAG, throwable);

            if(getView() != null) {
                getView().hideProgress();
                getView().showError(R.string.check_your_network_connection);
            }
        }
    };
}
