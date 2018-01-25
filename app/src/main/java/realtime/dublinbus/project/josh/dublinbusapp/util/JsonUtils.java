package realtime.dublinbus.project.josh.dublinbusapp.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.data.BusInfo;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusRoute;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusStop;

public class JsonUtils {
    private final static String DUBLIN_BUS_ORIGIN = "origin";
    private final static String DUBLIN_BUS_DESTINATION = "destination";
    private final static String DUBLIN_BUS_STOP_ID = "stopid";
    private final static String DUBLIN_BUS_FULL_NAME = "fullname";
    private final static String DUBLIN_BUS_LATITUDE = "latitude";
    private final static String DUBLIN_BUS_LONGITUDE = "longitude";
    private final static String DUBLIN_BUS_RESULTS = "results";
    private final static String DUBLIN_BUS_STOPS = "stops";
    private final static String DUBLIN_BUS_ROUTE = "route";
    private final static String DUBLIN_BUS_DUE_TIME = "duetime";

    private static String TAG = "JSON_UTILS";

    public static List<BusInfo> getBusRealTimeStringsFromJson(String realTimeInfo) throws JSONException {

        List<BusInfo> busInfoList = new ArrayList<>();

        JSONObject busInfoTime = new JSONObject(realTimeInfo);
        JSONArray busInfoArray = busInfoTime.getJSONArray(DUBLIN_BUS_RESULTS);

        for (int i = 0; i < busInfoArray.length(); i++){
            JSONObject resultsObject = busInfoArray.getJSONObject(i);

            String dueTime = resultsObject.getString(DUBLIN_BUS_DUE_TIME);
            String route = resultsObject.getString(DUBLIN_BUS_ROUTE);

            BusInfo busInfo = new BusInfo("", "");
            busInfo.setmBusTime(dueTime);
            busInfo.setmBusRoute(route);

            busInfoList.add(busInfo);
        }
        return busInfoList;
    }
    public static List<BusRoute> getBusRouteStringsFromJson(String busRouteJsonStr)throws JSONException{

        List<BusRoute> busRouteList = new ArrayList<>();

        JSONObject busRouteJson = new JSONObject(busRouteJsonStr);
        JSONArray busRoutesArray = busRouteJson.getJSONArray(DUBLIN_BUS_RESULTS);

        Log.d(TAG + " busRoutesArray", ""+busRoutesArray.length());

        for(int i = 0; i < busRoutesArray.length(); i++){
            JSONObject routeObject = busRoutesArray.getJSONObject(i);
            Log.d(TAG + "routeObject", routeObject.toString());

            String route = routeObject.getString(DUBLIN_BUS_ROUTE);

            Log.d(TAG, "route: "+route);
            BusRoute busRoute = new BusRoute("");
            busRoute.setmRoute(route);

            busRouteList.add(busRoute);
        }

        return busRouteList;
    }

    public static List<List<BusStop>> getBusStopStringsFromJson(String busStopsJsonStr)
            throws JSONException {

        JSONObject busRouteJson = new JSONObject(busStopsJsonStr);

        JSONArray busRouteArray = busRouteJson.getJSONArray(DUBLIN_BUS_RESULTS);

        JSONObject jsonResultOne = busRouteArray.getJSONObject(0);
        JSONObject jsonResultTwo = busRouteArray.getJSONObject(1);

        List<List<BusStop>> resultListOfList = new ArrayList<>();

        List<BusStop> resultOne = new ArrayList<>();
        List<BusStop> resultTwo = new ArrayList<>();

        for (int i = 0; i < busRouteArray.length(); i++) {

            JSONArray jaOne = jsonResultOne.getJSONArray(DUBLIN_BUS_STOPS);
            JSONArray jaTwo = jsonResultTwo.getJSONArray(DUBLIN_BUS_STOPS);

            Log.d(TAG + " 1", jaOne.toString());
            Log.d(TAG + " 2", jaTwo.toString());

            String originA = jsonResultOne.getString(DUBLIN_BUS_ORIGIN);
            String destinationA = jsonResultOne.getString(DUBLIN_BUS_DESTINATION);

            String originB = jsonResultTwo.getString(DUBLIN_BUS_ORIGIN);
            String destinationB = jsonResultTwo.getString(DUBLIN_BUS_DESTINATION);

            for (int j = 0; j <jaOne.length(); j++){
                JSONObject stops = jaOne.getJSONObject(j);
//                Log.d(TAG, stops.toString());

                int stopIdA = stops.getInt(DUBLIN_BUS_STOP_ID);
                String fullNameA = stops.getString(DUBLIN_BUS_FULL_NAME);
                Double latitudeA = stops.getDouble(DUBLIN_BUS_LATITUDE);
                Double longitudeA = stops.getDouble(DUBLIN_BUS_LONGITUDE);

                BusStop busStopDirectionA = new BusStop("", "", 0, "", 0.0, 0.0);
                busStopDirectionA.setmOrigin(originA);
                busStopDirectionA.setmDestinstion(destinationA);
                busStopDirectionA.setmStopId(stopIdA);
                busStopDirectionA.setmFullName(fullNameA);
                busStopDirectionA.setmLatitude(latitudeA);
                busStopDirectionA.setmLongitude(longitudeA);

                resultOne.add(busStopDirectionA);

            }

            for(int o = 0; o < jaTwo.length(); o++){
                JSONObject stopsB = jaTwo.getJSONObject(o);
//                Log.d(TAG, stopsB.toString());

                int stopIdB = stopsB.getInt(DUBLIN_BUS_STOP_ID);
                String fullNameB = stopsB.getString(DUBLIN_BUS_FULL_NAME);
                Double latitudeB = stopsB.getDouble(DUBLIN_BUS_LATITUDE);
                Double longitudeB = stopsB.getDouble(DUBLIN_BUS_LONGITUDE);

                BusStop busStopDirectionB = new BusStop("", "", 0, "", 0.0,0.0);
                busStopDirectionB.setmOrigin(originB);
                busStopDirectionB.setmDestinstion(destinationB);
                busStopDirectionB.setmStopId(stopIdB);
                busStopDirectionB.setmFullName(fullNameB);
                busStopDirectionB.setmLatitude(latitudeB);
                busStopDirectionB.setmLongitude(longitudeB);

                resultTwo.add(busStopDirectionB);

            }
        }

        resultListOfList.add(resultOne);
        resultListOfList.add(resultTwo);

        for(int i = 0; i< resultOne.size(); i ++){
            Log.d("LIST 1 = ", resultOne.get(i).getmDestinstion() + resultOne.get(i).getmStopId());
        }
        for(int i = 0; i< resultTwo.size(); i ++){
            Log.d("LIST 2 = ", resultTwo.get(i).getmDestinstion() + resultTwo.get(i).getmStopId());
        }

        Log.d(TAG, "LIST SIZE: "+resultListOfList.size());
        return resultListOfList;
    }
}
