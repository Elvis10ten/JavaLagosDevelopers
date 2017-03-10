package mobymagic.com.javalagos.ui.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.intrusoft.library.FrissonView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.utils.GenericUtils;

public class UserDetailActivity extends AppCompatActivity {

    public static final String ARG_USER = "ARG_USER";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.user_avatar_fv)
    FrissonView mUserAvatarView;
    @BindView(R.id.username_tv)
    TextView mUserNameTextView;
    @BindView(R.id.user_profile_url_tv)
    TextView mUserProfileUrlTextView;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mUser = getIntent().getParcelableExtra(ARG_USER);
        showUserDetails();
    }

    private void showUserDetails() {
        mUserNameTextView.setText(mUser.getLogin());

        mUserProfileUrlTextView.setText(GenericUtils.fromHtml("<a href=\"" + mUser.getHtmlUrl() +
                "\">" + mUser.getHtmlUrl() + "</a>"));
        mUserProfileUrlTextView.setMovementMethod(LinkMovementMethod.getInstance());

        Picasso.with(this).load(mUser.getAvatarUrl()).placeholder(R.drawable.default_user_avatar)
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

    @OnClick(R.id.share_btn)
    public void onShareBtnClicked() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.title_awesome_developer));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                getString(R.string.share_intent_text, mUser.getLogin(), mUser.getHtmlUrl()));
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.title_share_profile_using)));
    }
}
