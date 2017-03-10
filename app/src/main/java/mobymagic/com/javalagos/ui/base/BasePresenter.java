package mobymagic.com.javalagos.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BasePresenter<V> {

    private  @Nullable V mView;

    public BasePresenter(@NonNull V view) {
        attachView(view);
    }

    public void attachView(@NonNull V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    protected @Nullable V getView() {
        return mView;
    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected final boolean isViewAttached() {
        return mView != null;
    }

}