package realtime.dublinbus.project.josh.dublinbusapp.activity;

import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import realtime.dublinbus.project.josh.dublinbusapp.R;
import realtime.dublinbus.project.josh.dublinbusapp.data.BusStop;
import realtime.dublinbus.project.josh.dublinbusapp.fragment.BusStopListFragment;

public class BusStopListActivity extends AppCompatActivity {

    private RelativeLayout buttonLayout;
    private Button directionA;
    private Button directionB;
    List<BusStop> routeA;
    List<BusStop>routeB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_list);

        buttonLayout = findViewById(R.id.directional_buttons_layout);
        directionA = findViewById(R.id.direction_a);
        directionB = findViewById(R.id.direction_b);

        routeA =  getIntent().getParcelableArrayListExtra("List_Direction_One");
        routeB = getIntent().getParcelableArrayListExtra("List_Direction_Two");

        String towardsDirA = getString(R.string.towards ) + routeA.get(0).getmDestinstion();
        String towardDirB = getString(R.string.towards) + routeB.get(0).getmDestinstion();

        directionA.setText(towardsDirA);
        directionB.setText(towardDirB);

        directionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list_direction_a", (ArrayList<? extends Parcelable>)routeA);

                BusStopListFragment fragment = BusStopListFragment.newInstance(bundle);

                commitTransaction(fragment);
            }
        });

        directionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list_direction_b", (ArrayList<? extends Parcelable>)routeB);

                BusStopListFragment fragment = BusStopListFragment.newInstance(bundle);

                commitTransaction(fragment);
            }
        });

    }

    public void commitTransaction(BusStopListFragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "BUS_STOP_LIST_FRAGMENT");
        transaction.addToBackStack("BusStopListFragment");
        transaction.commit();
    }
}
