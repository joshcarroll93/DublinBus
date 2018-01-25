package realtime.dublinbus.project.josh.dublinbusapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.R;
import realtime.dublinbus.project.josh.dublinbusapp.adapter.BusStopAdapter;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusStop;

public class BusStopListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    public BusStopAdapter mAdapter;
    private List<BusStop> busStopList;
    private String TAG = getClass().getSimpleName();

    public BusStopListFragment() {
        // Required empty public constructor
    }

    public static BusStopListFragment newInstance(Bundle args) {

        BusStopListFragment fragment = new BusStopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bus_stop_list, container, false);


        if(getArguments() != null){

            Log.d(TAG, "Arguments not null");

            if(getArguments().containsKey("list_direction_a")){
                busStopList = getArguments().getParcelableArrayList("list_direction_a");
            }
            else if(getArguments().containsKey("list_direction_b")){
                busStopList = getArguments().getParcelableArrayList("list_direction_b");
            }
        }
        else{
            Log.d(TAG, "Args null");
        }

        Log.d(TAG, "List size = "+busStopList.size());
        // Inflate the layout for this fragment
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new BusStopAdapter(getActivity(), busStopList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
