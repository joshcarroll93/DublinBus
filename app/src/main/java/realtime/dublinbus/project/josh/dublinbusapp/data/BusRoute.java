package realtime.dublinbus.project.josh.dublinbusapp.data;


import android.os.Parcel;
import android.os.Parcelable;

public class BusRoute implements Parcelable{
    private String mRoute;

    public BusRoute(String route){
        mRoute = route;
    }
    private BusRoute(Parcel parcel){
        mRoute = parcel.readString();
    }

    public String getmRoute() {
        return mRoute;
    }
    public void setmRoute(String mRoute) {
        this.mRoute = mRoute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mRoute);
    }
    public static final Parcelable.Creator<BusRoute> CREATOR = new Parcelable.Creator<BusRoute>() {
        @Override
        public BusRoute createFromParcel(Parcel parcel) {
            return new BusRoute(parcel);
        }

        @Override
        public BusRoute[] newArray(int i) {
            return new BusRoute[i];
        }
    };
}