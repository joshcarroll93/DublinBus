package realtime.dublinbus.project.josh.dublinbusapp.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.activity.RealTimeInfoActivity;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusInfo;
import realtime.dublinbus.project.josh.dublinbusapp.util.JsonUtils;
import realtime.dublinbus.project.josh.dublinbusapp.util.NetworkUtils;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

@SuppressLint("StaticFieldLeak")
public class FetchRealTimeInfo extends AsyncTask<URL, Void, String> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;

    public FetchRealTimeInfo(Context context){
        mContext= context.getApplicationContext();
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
    protected void onPostExecute(String realTimeInfo) {
        super.onPostExecute(realTimeInfo);

        Log.i(TAG, realTimeInfo);

        Intent intent = new Intent(mContext, RealTimeInfoActivity.class);

        if(realTimeInfo != null && !realTimeInfo.equals("")){

            try {
                List<BusInfo> busInfoList = JsonUtils.getBusRealTimeStringsFromJson(realTimeInfo);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>)busInfoList);
                intent.putExtras(bundle);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }

        }else{
            //no data to pass, handle in RealTimeInfoActivity.class
            mContext.startActivity(intent);
        }
    }
}

