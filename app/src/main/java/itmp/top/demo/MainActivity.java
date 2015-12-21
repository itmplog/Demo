package itmp.top.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "demo 001").setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 2, 0, "demo 002").setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 3, 0, "demo 003").setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 4, 0, "demo 004").setIcon(android.R.drawable.ic_menu_mapmode);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

            default:
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
