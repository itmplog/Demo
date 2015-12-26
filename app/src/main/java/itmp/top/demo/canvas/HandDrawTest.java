package itmp.top.demo.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import itmp.top.demo.R;

public class HandDrawTest extends AppCompatActivity {

    private HandDraw handDraw = null;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_draw_test);
        //setContentView(new HandDraw(this));
        handDraw = (HandDraw)findViewById(R.id.handdraw);

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
                handDraw.cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, strm);
                try {
                    strm.close();
                }catch (IOException e){
                    e.printStackTrace();
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
        subMenu.add(0, 101, 0, "Red");
        subMenu.add(0, 102, 0, "Green");
        subMenu.add(0, 103, 0, "Blue");
        SubMenu subMenu1 = menu.addSubMenu(1, 200, 0, "Width");
        subMenu1.add(1, 201, 0, "width_1");
        subMenu1.add(1, 203, 0, "width_3");
        subMenu1.add(1, 205, 0, "width_5");
        menu.add(2, 3, 0, "blur");
        menu.add(2, 4, 0, "emboss");
        return super.onCreateOptionsMenu(menu);
    }
}
