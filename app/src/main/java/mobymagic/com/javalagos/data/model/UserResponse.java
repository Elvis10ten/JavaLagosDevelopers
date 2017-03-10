
package mobymagic.com.javalagos.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserResponse {

    @SerializedName("incomplete_results")
    private boolean mIncompleteResults;
    @SerializedName("items")
    private List<User> mUsers;
    @SerializedName("total_count")
    private long mTotalCount;

    public boolean getIncompleteResults() {
        return mIncompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        mIncompleteResults = incompleteResults;
    }

    public List<User> getItems() {
        return mUsers;
    }

    public void setItems(List<User> users) {
        mUsers = users;
    }

    public long getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(long totalCount) {
        mTotalCount = totalCount;
    }

}
