package mobymagic.com.javalagos.ui.userslist;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.InfinitePlaceHolderView;
import com.mindorks.placeholderview.PlaceHolderViewBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.utils.ItemOffsetDecoration;
import mobymagic.com.javalagos.utils.LogUtils;
import mobymagic.com.javalagos.utils.VersionUtils;

public abstract class BaseUserListFragment extends Fragment implements UserListContract.UserListView, ProgressView.LoadMoreListener {

    private static final String LOG_TAG = "BaseUserListFragment";

    @BindView(R.id.phv)
    protected InfinitePlaceHolderView mPlaceHolderView;
    @BindView(R.id.empty_ll)
    LinearLayout mEmptyContainerView;
    @BindView(R.id.pb)
    ProgressBar mProgressView;
    @BindView(R.id.error_ll)
    LinearLayout mErrorContainerView;
    @BindView(R.id.error_tv)
    TextView mErrorTextView;

    protected Unbinder mUnBinder;
    protected UserListPresenter mUserListPresenter;
    private ProgressView mMoreProgress;

    private int mNextPage = 1;

    public BaseUserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserListPresenter = new UserListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_base_user_list, container, false);
        mUnBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LogUtils.d(LOG_TAG, "View created");
        mProgressView.setVisibility(View.GONE);
        mPlaceHolderView.getBuilder().setHasFixedSize(true);

        if (getResources().getBoolean(R.bool.is_landscape)) {
            setupGrid(mPlaceHolderView.getBuilder());
        }

        mMoreProgress = new ProgressView(this);
        mPlaceHolderView.setLoadMoreResolver(mMoreProgress);
    }

    private void setupGrid(PlaceHolderViewBuilder placeHolderViewBuilder) {
        LogUtils.d(LOG_TAG, "Using grids");
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        placeHolderViewBuilder.setLayoutManager(gridLayoutManager);

        //In grid mode, we don't want to use a static span count of like 2. We want the span count
        //To adjust and chop whatever space is available and divide equally
        mPlaceHolderView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        //Removing the listener once it triggers as we don't need it after
                        if (VersionUtils.hasJellyBean()) {
                            mPlaceHolderView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            mPlaceHolderView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }

                        //Calculate the new grid span count
                        int viewWidth = mPlaceHolderView.getMeasuredWidth();
                        float gridViewWidth = getActivity().getResources()
                                .getDimensionPixelSize(R.dimen.grid_width_regular);
                        int newSpanCount = (int) Math.floor(viewWidth / gridViewWidth);
                        //Can't be zero, else RecyclerView will throw an exception
                        if (newSpanCount == 0) {
                            newSpanCount = 1;
                        }

                        //Update the new span count
                        gridLayoutManager.setSpanCount(newSpanCount);
                        gridLayoutManager.requestLayout();
                    }
                });

        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),
                R.dimen.grid_spacing_regular);
        mPlaceHolderView.addItemDecoration(itemOffsetDecoration);
    }

    @Override
    public void showProgress() {
        LogUtils.d(LOG_TAG, "Showing progress");
        //Only want to show a progress when there is no item in the list because placeholderview
        // handles it after
        if(mPlaceHolderView.getViewCount() < 1) {
            mProgressView.setVisibility(View.VISIBLE);
            mErrorContainerView.setVisibility(View.GONE);
            mEmptyContainerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgress() {
        LogUtils.d(LOG_TAG, "Hiding progress");
        mProgressView.setVisibility(View.GONE);
        mErrorContainerView.setVisibility(View.GONE);
        mEmptyContainerView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        LogUtils.d(LOG_TAG, "Showing empty");
        if(mPlaceHolderView.getViewCount() < 1) {
            mProgressView.setVisibility(View.GONE);
            mErrorContainerView.setVisibility(View.GONE);
            mEmptyContainerView.setVisibility(View.VISIBLE);
        }
        //mPlaceHolderView.noMoreToLoad();
    }

    @Override
    public void showError(@StringRes int errorMsgRes) {
        LogUtils.d(LOG_TAG, "Showing error");
        if(mPlaceHolderView.getViewCount() < 1) {
            mErrorTextView.setText(errorMsgRes);
            mProgressView.setVisibility(View.GONE);
            mErrorContainerView.setVisibility(View.VISIBLE);
            mEmptyContainerView.setVisibility(View.GONE);
        } else {
            Toast.makeText(getActivity(), R.string.check_your_network_connection, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        LogUtils.d(LOG_TAG, "Hot jellof rice served");
        for (User user : users) {
            mPlaceHolderView.addView(new UserView(getActivity(), user));
        }

        setNextPage((getNextPage() + 1));
        mPlaceHolderView.loadingDone();
    }

    @OnClick(R.id.retry_btn)
    public void onClick() {
        LogUtils.d(LOG_TAG, "Retrying");
        loadData(getNextPage());
    }

    protected abstract void loadData(int page);

    @Override
    public void loadMore() {
        loadData(getNextPage());
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int nextPage) {
        this.mNextPage = nextPage;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        mUserListPresenter.detachView();
    }
}
