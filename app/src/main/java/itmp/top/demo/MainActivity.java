package itmp.top.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.jar.Manifest;

import itmp.top.demo.images.ImageGrid;
import itmp.top.demo.images.PicPick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, getString(R.string.demo001_sendmessage)).setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 2, 0, getString(R.string.imagegrid)).setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 3, 0, getString(R.string.picpick)).setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, 4, 0, "demo 004").setIcon(android.R.drawable.ic_menu_mapmode);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(MainActivity.this, Demo001.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent1 = new Intent(MainActivity.this, ImageGrid.class);
                startActivity(intent1);
                break;
            case 3:
                Intent intent2 = new Intent(MainActivity.this, PicPick.class);
                startActivity(intent2);
                break;
            case 4:
                break;

            default:
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
