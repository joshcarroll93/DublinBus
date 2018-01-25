package realtime.dublinbus.project.josh.dublinbusapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BusStop implements Parcelable {

    private String mOrigin;
    private String mDestination;

    private int mStopId;
    private String mFullName;
    private Double mLatitude;
    private Double mLongitude;

    public BusStop(String origin, String destination, int stopId, String fullName, Double latitude, Double longitude){
        mOrigin = origin;
        mDestination = destination;
        mStopId = stopId;
        mFullName = fullName;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    private BusStop(Parcel parcel){
        mOrigin = parcel.readString();
        mDestination = parcel.readString();
        mStopId = parcel.readInt();
        mFullName = parcel.readString();
        mLatitude = parcel.readDouble();
        mLongitude = parcel.readDouble();
    }
    public String getmOrigin() {
        return mOrigin;
    }

    public void setmOrigin(String mOrigin) {
        this.mOrigin = mOrigin;
    }

    public String getmDestinstion() {
        return mDestination;
    }

    public void setmDestinstion(String mDestinstion) {
        this.mDestination = mDestinstion;
    }

    public int getmStopId() {
        return mStopId;
    }

    public void setmStopId(int mStopId) {
        this.mStopId = mStopId;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mOrigin);
        parcel.writeString(mDestination);
        parcel.writeInt(mStopId);
        parcel.writeString(mFullName);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
    }
    public static final Creator<BusStop> CREATOR = new Parcelable.Creator<BusStop>() {
        @Override
        public BusStop createFromParcel(Parcel parcel) {
            return new BusStop(parcel);
        }

        @Override
        public BusStop[] newArray(int i) {
            return new BusStop[i];
        }
    };
}