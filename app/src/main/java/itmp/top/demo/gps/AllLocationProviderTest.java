package itmp.top.demo.gps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private final int REQ_CODE = 0x111;
    private String lastLocation;
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

        // Need permission here GPS

        checkGPSPermission(this);

        List<String> criterias = locationManager.getProviders(criteria, false);
        if(criterias.size() == 0){
            Toast.makeText(this, "No Criterias Data", Toast.LENGTH_SHORT).show();
        }

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if(location != null){
            StringBuilder sb = new StringBuilder();
            sb.append("实时的位置信息：\n");
            sb.append("经度：");
            sb.append(location.getLongitude());
            sb.append("\n纬度：");
            sb.append(location.getLatitude());
            sb.append("\n高度：");
            sb.append(location.getAltitude());
            sb.append("\n速度：");
            sb.append(location.getSpeed());
            sb.append("\n方向：");
            sb.append(location.getBearing());
            lastLocation = sb.toString();
        }
        for(String tmp:criterias)
        Log.v("lists", tmp);
        criteriaLists.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, criterias));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        menu.add(0, 100, 0, "Criteria Provider").setIcon(R.drawable.compass).setShowAsAction(MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        menu.add(1, 100, 0, "Last Location").setIcon(R.drawable.ic_arrow_upward_white_48px).setShowAsAction(MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        menu.add(2, 100, 0, "Test");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case 0:
                showCriteriaDialog(this);
                break;
            case 1:
                showLastLocation(this);
                break;
            case 2:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("hello")
                        .setMessage("world!");
                builder.create().show();
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
    private void showLastLocation(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(getString(R.string.criteria))
                .setMessage(lastLocation)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, null);
        builder.create()
                .show();
    }

    private void checkGPSPermission(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(AllLocationProviderTest.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQ_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQ_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Grant GPS Permission")
                            .setMessage("Need GPS Permission to get Data!!")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    checkGPSPermission(AllLocationProviderTest.this);
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    AllLocationProviderTest.this.finish();
                                }
                            });
                    builder.create()
                            .show();
                }
        }
    }
}
