package mobymagic.com.javalagos.ui.detail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.intrusoft.library.FrissonView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.data.model.User;

public class UserDetailActivity extends AppCompatActivity {

    public static final String ARG_USER = "ARG_USER";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.user_avatar_fv)
    FrissonView mUserAvatarView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        User user = getIntent().getParcelableExtra(ARG_USER);
        Picasso.with(this).load(user.getAvatarUrl()).placeholder(R.drawable.default_user_avatar)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mUserAvatarView.setBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    @OnClick(R.id.fab)
    public void onClick() {
    }
}
