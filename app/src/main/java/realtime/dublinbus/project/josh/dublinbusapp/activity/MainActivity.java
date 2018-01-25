package realtime.dublinbus.project.josh.dublinbusapp.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.R;
import realtime.dublinbus.project.josh.dublinbusapp.asynctask.FetchBusRouteInfo;
import realtime.dublinbus.project.josh.dublinbusapp.asynctask.FetchBusStopInfo;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusStop;
import realtime.dublinbus.project.josh.dublinbusapp.util.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mErrorMessageDisplay;
    public ProgressBar mLoadingIndicator;
    public ArrayAdapter<String> mRouteAdapter;
    public AutoCompleteTextView routeSearch;
    public  List<List<BusStop>> busStops = new ArrayList<>();
    private List<String> routes = new ArrayList<>();
    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRouteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, routes);
        routeSearch = findViewById(R.id.id_route);
        routeSearch.setAdapter(mRouteAdapter);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        makeApiSearchForBusRouteInfo();

        routeSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                makeApiSearchForBusStopInfo(mRouteAdapter.getItem(i));

                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                if(inputMethodManager != null)
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    //search for list of stops based on route
    private void makeApiSearchForBusStopInfo(String routeStop){
        URL searchUrl = NetworkUtils.buildUrlForStopInfo("routeinformation", routeStop);
        new FetchBusStopInfo(context).execute(searchUrl);
    }

    //Search for list of routes
    private void makeApiSearchForBusRouteInfo(){
        URL searchUrl = NetworkUtils.buildUrlForRouteInfo("routelistinformation");
        new FetchBusRouteInfo(context).execute(searchUrl);
    }
}
