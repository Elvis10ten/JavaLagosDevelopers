package mobymagic.com.javalagos.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.ui.search.SearchActivity;
import mobymagic.com.javalagos.ui.userslist.CharmersFragment;
import mobymagic.com.javalagos.ui.userslist.HardWorkersFragment;
import mobymagic.com.javalagos.ui.userslist.NewbiesFragment;
import mobymagic.com.javalagos.utils.VersionUtils;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView {

    // region Views
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.search_cv)
    CardView mSearchCardView;
    // endregion

    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mHomePresenter = new HomePresenter(this);

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        openFirstTab();
    }

    private void openFirstTab() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_fl);
        if(fragment == null) {
            replaceContentFragment(new NewbiesFragment());
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }
    }

    private void replaceContentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content_fl, fragment, null)
                .commit();
    }

    // region Listeners
    @OnClick(R.id.search_cv)
    public void onSearchCardViewClicked(View view) {
        mHomePresenter.onSearchRequested();
    }
    // endregion

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_newbies:
                    replaceContentFragment(new NewbiesFragment());
                    return true;
                case R.id.navigation_hardworkers:
                    replaceContentFragment(new HardWorkersFragment());
                    return true;
                case R.id.navigation_charming:
                    replaceContentFragment(new CharmersFragment());
                    return true;
            }
            return false;
        }

    };

    // region HomeContract.HomeView Methods

    @Override
    public void showProgress() {
        //Unimplemented
    }

    @Override
    public void hideProgress() {
        //Unimplemented
    }

    @Override
    public void showEmpty() {
        //Unimplemented
    }

    @Override
    public void showError(@StringRes int errorMsgRes) {
        //Unimplemented
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void viewSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        Window window = getWindow();

        Resources resources = mSearchCardView.getResources();
        Pair<View, String> searchPair  = getPair(resources.getString(R.string.transition_search));

        ActivityOptionsCompat options = getActivityOptionsCompat(searchPair);

        //A lot for backward compatibility
        if(VersionUtils.hasLollipop() && options != null) {
            window.setExitTransition(null);
            ActivityCompat.startActivity(this, intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    // endregion

    @SuppressWarnings("unchecked")
    private @Nullable ActivityOptionsCompat getActivityOptionsCompat(Pair pair){
        ActivityOptionsCompat options = null;

        Pair<View, String> navigationBarPair  = getNavigationBarPair();
        Pair<View, String> statusBarPair = getStatusBarPair();

        if(pair!=null && statusBarPair!= null && navigationBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    pair, statusBarPair, navigationBarPair);
        } else if(pair != null && statusBarPair != null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    pair, statusBarPair);
        } else if(pair != null && navigationBarPair != null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    pair, navigationBarPair);
        }

        return options;
    }

    private @Nullable Pair<View, String> getStatusBarPair(){
        Pair<View, String> pair = null;
        if(VersionUtils.hasLollipop()) {
            View statusBar = ButterKnife.findById(this, android.R.id.statusBarBackground);
            if (statusBar != null) {
                pair = Pair.create(statusBar, statusBar.getTransitionName());
            }
        }
        return pair;
    }

    private @Nullable Pair<View, String> getNavigationBarPair(){
        Pair<View, String> pair = null;
        if(VersionUtils.hasLollipop()) {
            View navigationBar = ButterKnife.findById(this, android.R.id.navigationBarBackground);
            if (navigationBar != null) {
                pair = Pair.create(navigationBar, navigationBar.getTransitionName());
            }
        }
        return pair;
    }

    private @Nullable Pair<View, String> getPair(String transition){
        Pair<View, String> searchPair = null;
        if(mSearchCardView != null){
            searchPair = Pair.create((View) mSearchCardView, transition);
        }

        return searchPair;
    }
}
