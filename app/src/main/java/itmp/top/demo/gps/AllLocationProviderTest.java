package itmp.top.demo.gps;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import itmp.top.demo.R;


public class AllLocationProviderTest extends AppCompatActivity {

    private ListView criteriaLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_all_location_provider_test);

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getAllProviders();

        ListView listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, providers));
        setContentView(listView);

        criteriaLists = new ListView(this);
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        List<String> criterias = locationManager.getProviders(criteria, false);
        if(criterias.size() == 0){
            Toast.makeText(this, "No Criterias Data", Toast.LENGTH_SHORT).show();
        }
        for(String tmp:criterias)
        Log.v("lists", tmp);
        criteriaLists.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, criterias));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        menu.add(0, 100, 0, "Criteria Provider").setIcon(R.drawable.compass).setShowAsAction(MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case 0:
                showCriteriaDialog(this);
                break;
            default:
                break;
        }
        return true;
    }

    private void showCriteriaDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(getString(R.string.criteria))
                .setView(criteriaLists)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, null);
        builder.create()
                .show();
    }
}
