package mobymagic.com.javalagos.ui.search;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.ui.userslist.BaseUserListFragment;
import mobymagic.com.javalagos.ui.userslist.UserView;
import mobymagic.com.javalagos.utils.DisplayUtility;
import mobymagic.com.javalagos.utils.LogUtils;
import mobymagic.com.javalagos.utils.VersionUtils;

public class SearchFragment extends BaseUserListFragment {

    // region Views
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_et)
    EditText searchEditText;

    // endregion

    private Handler mHandler;

    // region Factory Methods
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public static SearchFragment newInstance(Bundle extras) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Constructors
    public SearchFragment() {
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mHandler = new Handler();

        //The pain of backward compat
        if(VersionUtils.hasLollipop()) {
            Transition sharedElementEnterTransition = getActivity().getWindow().getSharedElementEnterTransition();
            sharedElementEnterTransition.addListener(enterTransitionTransitionListener);
        } else {
            DisplayUtility.showKeyboard(getContext(), searchEditText);
            searchEditText.animate().alpha(1.0f).setDuration(300);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mUnBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHandler.removeCallbacks(mSearchRunnable);
                mHandler.postDelayed(mSearchRunnable, 500);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // endregion

    @Override
    protected void loadData(int page) {
        mUserListPresenter.onSearchRequested(searchEditText.getText().toString(), getNextPage());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                DisplayUtility.hideKeyboard(getContext(), searchEditText);
                getActivity().supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // region Listeners
    private Transition.TransitionListener enterTransitionTransitionListener = new Transition.TransitionListener() {
        @Override
        public void onTransitionStart(Transition transition) {}

        @Override
        public void onTransitionEnd(Transition transition) {
            DisplayUtility.showKeyboard(getContext(), searchEditText);
            searchEditText.animate().alpha(1.0f).setDuration(300);
        }

        @Override
        public void onTransitionCancel(Transition transition) {}

        @Override
        public void onTransitionPause(Transition transition) {}

        @Override
        public void onTransitionResume(Transition transition) {}
    };

    // endregion

    private final Runnable mSearchRunnable = new Runnable() {
        @Override
        public void run() {
            mPlaceHolderView.removeAllViews();
            setNextPage(1);
            loadData(getNextPage());
        }
    };
}