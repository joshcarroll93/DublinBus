package realtime.dublinbus.project.josh.dublinbusapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BusInfo implements Parcelable {
    private String mBusTime;
    private String mBusRoute;

    public BusInfo(String busTime, String busRoute){
        mBusTime = busTime;
        mBusRoute = busRoute;

    }
    private BusInfo(Parcel parcel){
        mBusTime = parcel.readString();
        mBusRoute = parcel.readString();
    }

    public String getmBusTime() {
        return mBusTime;
    }
    public void setmBusTime(String mBusTime) {
        this.mBusTime = mBusTime;
    }

    public String getmBusRoute(){
        return mBusRoute;
    }
    public void setmBusRoute(String mBusRoute){
        this.mBusRoute = mBusRoute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mBusTime);
        parcel.writeString(mBusRoute);
    }
    public static final Parcelable.Creator<BusInfo> CREATOR = new Parcelable.Creator<BusInfo>() {
        @Override
        public BusInfo createFromParcel(Parcel parcel) {
            return new BusInfo(parcel);
        }

        @Override
        public BusInfo[] newArray(int i) {
            return new BusInfo[i];
        }
    };
}
