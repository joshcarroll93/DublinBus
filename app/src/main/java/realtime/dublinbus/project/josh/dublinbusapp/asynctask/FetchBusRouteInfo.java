package realtime.dublinbus.project.josh.dublinbusapp.asynctask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.activity.MainActivity;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusRoute;
import realtime.dublinbus.project.josh.dublinbusapp.util.JsonUtils;
import realtime.dublinbus.project.josh.dublinbusapp.util.NetworkUtils;

@SuppressLint("StaticFieldLeak")
public class FetchBusRouteInfo extends AsyncTask<URL, Void, String> {

    private String TAG = getClass().getSimpleName();
    private MainActivity mainActivity;

    public FetchBusRouteInfo(Context context){
        mainActivity = (MainActivity)context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.mLoadingIndicator.setVisibility(View.VISIBLE);
        mainActivity.routeSearch.setVisibility(View.INVISIBLE);
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
        Log.d(TAG, "in onPostExecute");
        mainActivity.routeSearch.setVisibility(View.VISIBLE);
        mainActivity.mLoadingIndicator.setVisibility(View.INVISIBLE);

        if(busData != null && !busData.equals("")){
            try {
                List<BusRoute> busRoutes = JsonUtils.getBusRouteStringsFromJson(busData);
                List<String> routes = new ArrayList<>();

                for(int i = 0 ; i < busRoutes.size(); i++){
                    routes.add(busRoutes.get(i).getmRoute());
                }

                mainActivity.mRouteAdapter.addAll(routes);
                mainActivity.mRouteAdapter.notifyDataSetChanged();

            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
        }else{
//            mainActivity.showErrorMessage();
        }
    }
}
