package mobymagic.com.javalagos.ui.userslist;

import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.infinite.LoadMore;

import mobymagic.com.javalagos.R;

@Layout(R.layout.loading_more_layout)
class ProgressView {

    @View(R.id.pb)
    private ProgressBar mProgressBar;

    private LoadMoreListener mListener;

    ProgressView(@NonNull LoadMoreListener loadMoreListener) {
        mListener = loadMoreListener;
    }

    @LoadMore
    private void loadMore() {
        mListener.loadMore();
    }

    interface LoadMoreListener {
        void loadMore();
    }
}