package mobymagic.com.javalagos.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

@SuppressWarnings("unused")
public class User extends RealmObject implements Parcelable {

    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("events_url")
    private String mEventsUrl;
    @SerializedName("followers_url")
    private String mFollowersUrl;
    @SerializedName("following_url")
    private String mFollowingUrl;
    @SerializedName("gists_url")
    private String mGistsUrl;
    @SerializedName("gravatar_id")
    private String mGravatarId;
    @SerializedName("html_url")
    private String mHtmlUrl;
    @SerializedName("id")
    private long mId;
    @SerializedName("login")
    private String mLogin;
    @SerializedName("organizations_url")
    private String mOrganizationsUrl;
    @SerializedName("received_events_url")
    private String mReceivedEventsUrl;
    @SerializedName("repos_url")
    private String mReposUrl;
    @SerializedName("score")
    private double mScore;
    @SerializedName("site_admin")
    private boolean mSiteAdmin;
    @SerializedName("starred_url")
    private String mStarredUrl;
    @SerializedName("subscriptions_url")
    private String mSubscriptionsUrl;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;

    public User() {}

    protected User(Parcel in) {
        mAvatarUrl = in.readString();
        mEventsUrl = in.readString();
        mFollowersUrl = in.readString();
        mFollowingUrl = in.readString();
        mGistsUrl = in.readString();
        mGravatarId = in.readString();
        mHtmlUrl = in.readString();
        mId = in.readLong();
        mLogin = in.readString();
        mOrganizationsUrl = in.readString();
        mReceivedEventsUrl = in.readString();
        mReposUrl = in.readString();
        mScore = in.readDouble();
        mSiteAdmin = in.readByte() != 0;
        mStarredUrl = in.readString();
        mSubscriptionsUrl = in.readString();
        mType = in.readString();
        mUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAvatarUrl);
        dest.writeString(mEventsUrl);
        dest.writeString(mFollowersUrl);
        dest.writeString(mFollowingUrl);
        dest.writeString(mGistsUrl);
        dest.writeString(mGravatarId);
        dest.writeString(mHtmlUrl);
        dest.writeLong(mId);
        dest.writeString(mLogin);
        dest.writeString(mOrganizationsUrl);
        dest.writeString(mReceivedEventsUrl);
        dest.writeString(mReposUrl);
        dest.writeDouble(mScore);
        dest.writeByte((byte) (mSiteAdmin ? 1 : 0));
        dest.writeString(mStarredUrl);
        dest.writeString(mSubscriptionsUrl);
        dest.writeString(mType);
        dest.writeString(mUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getEventsUrl() {
        return mEventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        mEventsUrl = eventsUrl;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        mFollowersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return mFollowingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        mFollowingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return mGistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        mGistsUrl = gistsUrl;
    }

    public String getGravatarId() {
        return mGravatarId;
    }

    public void setGravatarId(String gravatarId) {
        mGravatarId = gravatarId;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getOrganizationsUrl() {
        return mOrganizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        mOrganizationsUrl = organizationsUrl;
    }

    public String getReceivedEventsUrl() {
        return mReceivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        mReceivedEventsUrl = receivedEventsUrl;
    }

    public String getReposUrl() {
        return mReposUrl;
    }

    public void setReposUrl(String reposUrl) {
        mReposUrl = reposUrl;
    }

    public double getScore() {
        return mScore;
    }

    public void setScore(double score) {
        mScore = score;
    }

    public boolean getSiteAdmin() {
        return mSiteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        mSiteAdmin = siteAdmin;
    }

    public String getStarredUrl() {
        return mStarredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        mStarredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return mSubscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        mSubscriptionsUrl = subscriptionsUrl;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
