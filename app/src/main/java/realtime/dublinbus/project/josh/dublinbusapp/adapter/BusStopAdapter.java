package realtime.dublinbus.project.josh.dublinbusapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.R;
import realtime.dublinbus.project.josh.dublinbusapp.asynctask.FetchRealTimeInfo;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusStop;
import realtime.dublinbus.project.josh.dublinbusapp.util.NetworkUtils;

public class BusStopAdapter extends RecyclerView.Adapter<BusStopAdapter.RouteListViewHolder>{

    private Context mContext;
    private List<BusStop> mBusRouteList;

    public BusStopAdapter(Context context, List<BusStop> busRouteList){
        mContext = context.getApplicationContext();
        mBusRouteList = busRouteList;
    }

    public void addItems(List<BusStop> busStops){
        if(mBusRouteList != null){
            mBusRouteList.clear();
            mBusRouteList.addAll(busStops);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public RouteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RouteListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_item , parent, false));
    }

    @Override
    public void onBindViewHolder(RouteListViewHolder holder, int position) {
        holder.stopId.setText(String.valueOf(mBusRouteList.get(position).getmStopId()));
        holder.stopName.setText(mBusRouteList.get(position).getmFullName());
    }

    @Override
    public int getItemCount() {

        if(mBusRouteList != null)
            return mBusRouteList.size();

        return  0;
    }

    class RouteListViewHolder extends RecyclerView.ViewHolder{
        private TextView stopId;
        private TextView stopName;

        private RouteListViewHolder(final View itemView){
            super(itemView);

            stopId = itemView.findViewById(R.id.bus_stop_id);
            stopName = itemView.findViewById(R.id.bus_stop_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    implement real time data search
                    URL url = NetworkUtils.buildUriForRealTimeInfo("realtimebusinformation", String.valueOf(mBusRouteList.get(getAdapterPosition()).getmStopId()));
                    new FetchRealTimeInfo(mContext).execute(url);
                }
            });
        }
    }
}

