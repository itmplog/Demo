package itmp.top.demo.images;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OptionalDataException;

import itmp.top.demo.R;

public class BitmapTest extends AppCompatActivity {

    private String[] images = null;
    private AssetManager assetManager = null;
    private int currentImg = 0;
    private ImageView image = null;
    private Button prev = null;
    private Button next = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_test);

        image = (ImageView)findViewById(R.id.imageview);
        prev = (Button)findViewById(R.id.prev);
        next = (Button)findViewById(R.id.next);
        try {
            assetManager = getAssets();
            images = assetManager.list("");
        }catch(IOException e){
            e.printStackTrace();
        }

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentImg == images.length){
                    Toast.makeText(getApplicationContext(), "No pics", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(currentImg > images.length){
                    currentImg = 0;
                }

                while(!images[currentImg].endsWith(".png") && !images[currentImg].endsWith(".jpg")
                        && !images[currentImg].endsWith(".gif")){
                    currentImg--;
                    if(currentImg < 0){
                        currentImg = 0;
                    }
                }
                InputStream assetFile = null;
                try{
                    assetFile = assetManager.open(images[currentImg]);

                }catch(IOException e){
                    e.printStackTrace();
                }
                BitmapDrawable bitmapDrawable = (BitmapDrawable)image.getDrawable();
                if(bitmapDrawable != null
                        && !bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }

                image.setImageBitmap(BitmapFactory.decodeStream(assetFile));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentImg == images.length){
                    Toast.makeText(getApplicationContext(), "No pics", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(currentImg >= images.length){
                    currentImg = 0;
                }

                while(!images[currentImg].endsWith(".png")
                        && !images[currentImg].endsWith(".jpg")
                        && !images[currentImg].endsWith(".gif")){
                    currentImg++;
                    if(currentImg >= images.length){
                        currentImg = 0;
                    }
                }
                InputStream assetFile = null;
                try{
                    assetFile = assetManager.open(images[currentImg]);
                }catch (IOException e){
                    e.printStackTrace();
                }

                BitmapDrawable bitmapDrawable = (BitmapDrawable)image.getDrawable();
                if(bitmapDrawable != null
                    && !bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }
                image.setImageBitmap(BitmapFactory.decodeStream(assetFile));
            }
        });

    }
}
