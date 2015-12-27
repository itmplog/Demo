package itmp.top.demo.canvas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.EmbossMaskFilter;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import itmp.top.demo.MainActivity;
import itmp.top.demo.R;

public class HandDrawTest extends AppCompatActivity {

    private HandDraw handDraw = null;
    private Button button = null;
    private Button clear = null;
    private EmbossMaskFilter embossMaskFilter = null;
    private BlurMaskFilter blur = null;
    private final int REQUIREPERMISSION_RTN = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_draw_test);
        //setContentView(new HandDraw(this));
        handDraw = (HandDraw)findViewById(R.id.handdraw);
        embossMaskFilter = new EmbossMaskFilter(new float[]{
                1.5f, 1.5f, 1.5f
        }, 0.6f, 6, 4.2f);
        blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);

        clear = (Button)findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handDraw.cacheBitmap = Bitmap.createBitmap(handDraw.canvasWidth, handDraw.canvasHeight, Bitmap.Config.ARGB_8888);
                handDraw.cacheCanvas = new Canvas();
                handDraw.cacheCanvas.setBitmap(handDraw.cacheBitmap);
                handDraw.invalidate();
            }
        });

        button = (Button)findViewById(R.id.saveCanvas);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File saveCanvas = new File(Environment.getExternalStorageDirectory() + File.separator + "saveCanvas.png");
                FileOutputStream strm = null;
                try{
                    strm = new FileOutputStream(saveCanvas);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                //Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "saveCanvas.png");
               /* Bitmap bitmap = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888);
                handDraw.cacheCanvas.drawBitmap(bitmap, 0, 0, null);
                bitmap.compress(Bitmap.CompressFormat.PNG, 80, strm); */
                if (ContextCompat.checkSelfPermission(HandDrawTest.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(HandDrawTest.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUIREPERMISSION_RTN);
                }else {
                    handDraw.cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, strm);
                    Toast.makeText(getApplicationContext(), "Save Success.", Toast.LENGTH_SHORT).show();
                    try {
                        strm.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = new MenuInflater(this);
        //inflater.inflate(R.menu.handdraw, menu);
        //menu.add(0, 1, 0,"Color");
        SubMenu subMenu = menu.addSubMenu(0, 100, 0, "Color");
        subMenu.add(0, 99, 0, "Black");
        subMenu.add(0, 101, 0, "Red");
        subMenu.add(0, 102, 0, "Green");
        subMenu.add(0, 103, 0, "Blue");
        SubMenu subMenu1 = menu.addSubMenu(1, 200, 0, "Width");
        subMenu1.add(1, 201, 0, "width_1");
        subMenu1.add(1, 203, 0, "width_3");
        subMenu1.add(1, 205, 0, "width_5");
        menu.add(2, 3, 0, "blur").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(2, 4, 0, "emboss").setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            case 99:
                handDraw.paint.setColor(Color.BLACK);
                item.setChecked(true);
                break;
            case 101:
                handDraw.paint.setColor(Color.RED);
                item.setChecked(true);
                break;
            case 102:
                handDraw.paint.setColor(Color.GREEN);
                item.setChecked(true);
                break;
            case 103:
                handDraw.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case 201:
                handDraw.paint.setStrokeWidth(8);
                break;
            case 203:
                handDraw.paint.setStrokeWidth(16);
                break;
            case 205:
                handDraw.paint.setStrokeWidth(24);
                break;
            case 3:
                handDraw.paint.setMaskFilter(blur);
                break;
            case 4:
                handDraw.paint.setMaskFilter(embossMaskFilter);
                break;
        }

        //return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUIREPERMISSION_RTN:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    File saveCanvas = new File(Environment.getExternalStorageDirectory() + File.separator + "saveCanvas.png");
                    FileOutputStream strm = null;
                    try{
                        strm = new FileOutputStream(saveCanvas);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    handDraw.cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, strm);
                    Toast.makeText(getApplicationContext(), "Save Success.", Toast.LENGTH_SHORT).show();
                    try {
                        strm.close();
                    }catch (IOException e){e.printStackTrace();}
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Need Permission to save file", Toast.LENGTH_SHORT).show();
                    return;
                }
            default:
                Toast.makeText(getApplicationContext(), "Need Permission to save file", Toast.LENGTH_SHORT).show();
                return;
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
