package itmp.top.demo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import itmp.top.demo.animation.AnimatorTest;
import itmp.top.demo.animation.MyAnimation;
import itmp.top.demo.canvas.CanvasTest;
import itmp.top.demo.canvas.HandDrawTest;
import itmp.top.demo.canvas.MatrixView;
import itmp.top.demo.canvas.MoveBack;
import itmp.top.demo.canvas.PinBall;
import itmp.top.demo.canvas.WrapTest;
import itmp.top.demo.images.BitmapTest;
import itmp.top.demo.images.ImageGrid;
import itmp.top.demo.images.PicPick;
import itmp.top.demo.intents.Demo001;

public class MainActivity extends AppCompatActivity {

    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_item);


       /*final PackageManager pm = getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = pm.queryIntentActivities(mainIntent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

        for (ResolveInfo temp : appList) {

            Log.v("my logs", "package and activity name = "
                    + temp.activityInfo.packageName + "    "
                    + temp.activityInfo.name);
        // get all MainActivity & Package Name

        }
        */
        PackageInfo packageInfo = null;

        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<String> classNames = new ArrayList<>();

        try {
            packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        for(ActivityInfo activityInfo : packageInfo.activities){
            //Log.v("info", activityInfo.toString());
            //mActivities = new ArrayList<ActivityInfo>(Arrays.asList(packageInfo.activities));
            if(activityInfo.parentActivityName != null){
                classNames.add(activityInfo.name);
                arrayList.add(activityInfo.name.substring(activityInfo.name.lastIndexOf('.') + 1));
            }
            Log.v("ac", activityInfo.name);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        listView.setAnimation(new MyAnimation(displayMetrics.widthPixels / 2,
                displayMetrics.heightPixels / 2, 4000));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), classNames.get(position));
                startActivity(intent);
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.MAGENTA);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
/*
    public static ArrayList<String> getActivities(Context ctx) {
        ArrayList<String> result = new ArrayList<String>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setPackage(ctx.getPackageName());

        for (ResolveInfo info : ctx.getPackageManager().queryIntentActivities(intent, 0)) {
            result.add(info.activityInfo.name);
        }
        List<ResolveInfo> resolveInfos = ctx.getPackageManager().queryIntentActivities(intent, 0);
        Log.v("ctx", resolveInfos.toString() + "   " + resolveInfos.size());
        return result;
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 100, 0, getString(R.string.demo001_sendmessage)).setIcon(android.R.drawable.ic_menu_mapmode);
        SubMenu subMenu =  menu.addSubMenu(0, 200, 0, getString(R.string.images)).setIcon(android.R.drawable.ic_menu_mapmode);
        subMenu.add(1, 201, 0, getString(R.string.imagegrid)).setIcon(R.drawable.plane);
        subMenu.add(1, 202, 0, getString(R.string.picpick)).setIcon(R.drawable.plane);
        subMenu.add(1, 203, 0, getString(R.string.bitmaptest)).setIcon(R.drawable.plane);
        /* move to subMenu
        menu.add(0, 2, 0, getString(R.string.imagegrid)).setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 3, 0, getString(R.string.picpick)).setIcon(android.R.drawable.ic_menu_mapmode);
        */
        SubMenu subMenu1 = menu.addSubMenu(0, 300, 0, getString(R.string.canvas));
        subMenu1.add(0, 2, 0, getString(R.string.canvastest)).setIcon(android.R.drawable.ic_menu_mapmode);
        subMenu1.add(0, 3, 0, getString(R.string.handdraw)).setIcon(android.R.drawable.ic_menu_mapmode);
        subMenu1.add(0, 4, 0, getString(R.string.pinball)).setIcon(android.R.drawable.ic_menu_mapmode);
        subMenu1.add(0, 5, 0, getString(R.string.matrixview)).setIcon(android.R.drawable.ic_menu_mapmode);
        subMenu1.add(0, 6, 0, getString(R.string.moveback)).setIcon(R.drawable.plane);
        subMenu1.add(0, 7, 0, getString(R.string.wraptest)).setIcon(R.drawable.plane);

        menu.add(0, 400, 0, getString(R.string.animatortest));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(MainActivity.this, Demo001.class);
                startActivity(intent);
                break;
            case 201:
                Intent intent1 = new Intent(MainActivity.this, ImageGrid.class);
                startActivity(intent1);
                break;
            case 202:
                Intent intent2 = new Intent(MainActivity.this, PicPick.class);
                startActivity(intent2);
                break;
            case 203:
                Intent intent3 = new Intent(MainActivity.this, BitmapTest.class);
                startActivity(intent3);
                break;
            case 2:
                Intent intent4 = new Intent(MainActivity.this, CanvasTest.class);
                startActivity(intent4);
                break;
            case 3:
                Intent intent5 = new Intent(MainActivity.this, HandDrawTest.class);
                startActivity(intent5);
                break;
            case 4:
                Intent intent6 = new Intent(MainActivity.this, PinBall.class);
                startActivity(intent6);
                break;
            case 5:
                Intent intent7 = new Intent(MainActivity.this, MatrixView.class);
                startActivity(intent7);
                break;
            case 6:
                Intent intent8 = new Intent(MainActivity.this, MoveBack.class);
                startActivity(intent8);
                break;
            case 7:
                Intent intent9 = new Intent(MainActivity.this, WrapTest.class);
                startActivity(intent9);
                break;
            case 400:
                Intent intent10 = new Intent(MainActivity.this, AnimatorTest.class);
                startActivity(intent10);
            default:
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
