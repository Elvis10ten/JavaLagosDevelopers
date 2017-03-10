package mobymagic.com.javalagos.ui.userslist;

import android.os.Bundle;
import android.view.View;

public class CharmersFragment extends BaseUserListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(getNextPage());
    }

    @Override
    protected void loadData(int page) {
        mUserListPresenter.onCharmersRequested(getNextPage());
    }

}
