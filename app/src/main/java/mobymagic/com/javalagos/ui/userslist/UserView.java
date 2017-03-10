package mobymagic.com.javalagos.ui.userslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.squareup.picasso.Picasso;

import mobymagic.com.javalagos.R;
import mobymagic.com.javalagos.data.model.User;
import mobymagic.com.javalagos.ui.detail.UserDetailActivity;
import mobymagic.com.javalagos.utils.ColorUtils;

@NonReusable
@Layout(R.layout.item_user)
class UserView {

    @View(R.id.user_avatar_iv)
    private ImageView mUserAvatarView;
    @View(R.id.username_tv)
    private TextView mUserNameTextView;
    @View(R.id.user_meta_tv)
    private TextView mUserMetaTextView;
    @View(R.id.ibtn_more_options)
    private ImageButton mMoreOptionsBtn;
    @View(R.id.content_ll)
    private android.view.View mTextContainerView;

    private User mUser;
    private Context mContext;
    private boolean mIsLandscape;

    UserView(@NonNull Context context, @NonNull User user) {
        mContext = context;
        mUser = user;
        mIsLandscape = mContext.getResources().getBoolean(R.bool.is_landscape);
    }

    @Resolve
    private void onResolved() {
        mUserNameTextView.setText(mUser.getLogin());
        mUserMetaTextView.setText(String.valueOf(mUser.getId()));

        if(mIsLandscape) {
            mTextContainerView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mUserMetaTextView.setTextColor(Color.WHITE);
            mUserNameTextView.setTextColor(Color.WHITE);
            mMoreOptionsBtn.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

            Picasso.with(mContext).load(mUser.getAvatarUrl())
                    .placeholder(R.drawable.default_user_avatar).into(mUserAvatarView,
                    PicassoPalette.with(mUser.getAvatarUrl(), mUserAvatarView)
                            .intoCallBack(new PicassoPalette.CallBack() {
                                @Override
                                public void onPaletteLoaded(Palette palette) {
                                    updateGridTextContainerColor(ColorUtils.getMostPopulousSwatch(palette));
                                }
                            }));
        } else {
            Picasso.with(mContext).load(mUser.getAvatarUrl())
                    .placeholder(R.drawable.default_user_avatar).into(mUserAvatarView);
        }
    }

    private void updateGridTextContainerColor(@Nullable Palette.Swatch swatch) {
        if(swatch != null) {
            mTextContainerView.setBackgroundColor(swatch.getRgb());
            mUserMetaTextView.setTextColor(swatch.getBodyTextColor());
            mUserNameTextView.setTextColor(swatch.getTitleTextColor());
            mMoreOptionsBtn.setColorFilter(swatch.getTitleTextColor(), PorterDuff.Mode.MULTIPLY);
        }
    }

    @Click(R.id.user_item_ll)
    private void onUserClicked() {
        Intent intent = new Intent(mContext, UserDetailActivity.class);
        intent.putExtra(UserDetailActivity.ARG_USER, mUser);
        mContext.startActivity(intent);
    }
}