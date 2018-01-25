package realtime.dublinbus.project.josh.dublinbusapp.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.activity.BusStopListActivity;
import realtime.dublinbus.project.josh.dublinbusapp.activity.MainActivity;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusStop;
import realtime.dublinbus.project.josh.dublinbusapp.util.JsonUtils;
import realtime.dublinbus.project.josh.dublinbusapp.util.NetworkUtils;

@SuppressLint("StaticFieldLeak")
public class FetchBusStopInfo extends AsyncTask<URL, Void, String> {

    private String TAG = getClass().getSimpleName();
    private MainActivity mainActivity;
    private Context mContext;

    public FetchBusStopInfo(Context context){
        mainActivity = (MainActivity)context;
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL searchURL = urls[0];
        Log.i(TAG, searchURL.toString());
        String busResults = null;

        try{
            busResults = NetworkUtils.getResponseFromHttpUrl(searchURL);
            Log.d(TAG, busResults);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return busResults;
    }

    @Override
    protected void onPostExecute(String busData) {
        super.onPostExecute(busData);

        mainActivity.mLoadingIndicator.setVisibility(View.INVISIBLE);

        if(busData != null && !busData.equals("")){
            try {
                List<List<BusStop>> busStops = JsonUtils.getBusStopStringsFromJson(busData);

                List<BusStop> directionOne = busStops.get(0);
                List<BusStop> directionTwo = busStops.get(1);

                Intent intent = new Intent(mContext, BusStopListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("List_Direction_One", (ArrayList<? extends Parcelable>)directionOne);
                bundle.putParcelableArrayList("List_Direction_Two", (ArrayList<? extends Parcelable>)directionTwo);
                intent.putExtras(bundle);
                mContext.startActivity(intent);


            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
        }
    }
}