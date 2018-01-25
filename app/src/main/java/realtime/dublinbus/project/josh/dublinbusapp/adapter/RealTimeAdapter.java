package realtime.dublinbus.project.josh.dublinbusapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.R;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusInfo;

public class RealTimeAdapter extends RecyclerView.Adapter<RealTimeAdapter.RealTimeViewHolder>{
    private Context mContext;
    private int mResource;
    private List<BusInfo> mRealTimeBusInfoList;


    public RealTimeAdapter(Context context, int resource, List<BusInfo> items){
        mRealTimeBusInfoList = items;
    }

    public void addItems(List<BusInfo> busStops){
        if(mRealTimeBusInfoList != null){
            mRealTimeBusInfoList.clear();
            mRealTimeBusInfoList.addAll(busStops);
        }
        this.notifyDataSetChanged();
    }
    @Override
    public RealTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RealTimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.real_time_item , parent, false));
    }

    @Override
    public void onBindViewHolder(RealTimeViewHolder holder, int position) {

        holder.route.setText(mRealTimeBusInfoList.get(position).getmBusRoute());
        holder.dueTime.setText(mRealTimeBusInfoList.get(position).getmBusTime());
        if(!mRealTimeBusInfoList.get(position).getmBusTime().equals("Due"))
            holder.dueTime.append(" mins");
    }

    @Override
    public int getItemCount() {
        if(mRealTimeBusInfoList != null)
            return mRealTimeBusInfoList.size();

        return 0;
    }

    class RealTimeViewHolder extends RecyclerView.ViewHolder{
        private TextView route;
        private TextView dueTime;

        public RealTimeViewHolder(final View itemView) {
            super(itemView);

            route = itemView.findViewById(R.id.id_route_number);
            dueTime = itemView.findViewById(R.id.id_due_time);
        }
    }
}
