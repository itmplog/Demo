package itmp.top.demo.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
