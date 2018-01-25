package realtime.dublinbus.project.josh.dublinbusapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import realtime.dublinbus.project.josh.dublinbusapp.R;
import realtime.dublinbus.project.josh.dublinbusapp.adapter.RealTimeAdapter;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusInfo;

public class RealTimeInfoActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_info);

        RecyclerView mRealTimeRecyclerView = findViewById(R.id.id_real_time_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication());
        mRealTimeRecyclerView.setLayoutManager(layoutManager);

        if(getIntent().getParcelableArrayListExtra("list").size() > 0){
            ArrayList<BusInfo> list = getIntent().getParcelableArrayListExtra("list");
            Log.d(TAG, "list size: "+list.size());

            RealTimeAdapter realTimeAdapter = new RealTimeAdapter(getApplicationContext(), android.R.layout.simple_gallery_item, list);
            mRealTimeRecyclerView.setAdapter(realTimeAdapter);
            mRealTimeRecyclerView.setVisibility(View.VISIBLE);
        }else{

            TextView errorTxtV = findViewById(R.id.real_time_error_msg);
            errorTxtV.setText(getString(R.string.realTimeErrorMsg));
            errorTxtV.setVisibility(View.VISIBLE);
        }
    }
}
