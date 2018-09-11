package bottomsheet.materialdesign.interra.com.bottomsheet;


public class PlayList{
    private int mPicture;
    private String mName;

    PlayList(int mPicture, String mName) {
        this.mName = mName;
        this.mPicture = mPicture;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmName() {
        return mName;
    }

    public void setmPicture(int mPicture) {
        this.mPicture = mPicture;
    }

    public int getmPicture() {
        return mPicture;
    }

}
