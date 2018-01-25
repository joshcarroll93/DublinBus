package realtime.dublinbus.project.josh.dublinbusapp.util;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    private static final String BASE_URL = "https://data.dublinked.ie/cgi-bin/rtpi";
    private static final String OPERATOR = "bac";
    private static final String QUERY_ROUTE_ID= "routeid";
    private static final String QUERY_OPERATOR = "operator";
    private static final String QUERY_STOP_ID = "stopid";

    public static URL buildUriForRealTimeInfo(String requestType, String stopId){

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(requestType)
                .appendQueryParameter(QUERY_STOP_ID, stopId)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildUrlForStopInfo(String requestType, String busRoute) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(requestType)
                .appendQueryParameter(QUERY_ROUTE_ID, busRoute)
                .appendQueryParameter(QUERY_OPERATOR, OPERATOR)
                .build();

        String TAG = "NETWORK_UTILS";
        Log.i(TAG, builtUri.toString());

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildUrlForRouteInfo(String requestType){

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(requestType)
                .appendQueryParameter(QUERY_OPERATOR, OPERATOR)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        String getRequest = "GET";

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(getRequest);
        urlConnection.connect();

        BufferedReader reader = null;
        String moviesJsonStr = null;

        try {

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            if (builder.length() == 0) {
                return null;
            }

            moviesJsonStr = builder.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            urlConnection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return moviesJsonStr;
    }
}
