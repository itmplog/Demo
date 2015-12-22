package itmp.top.demo.images;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import itmp.top.demo.R;

public class ImageGrid extends AppCompatActivity {

    private GridView mGridView = null;
    private Cursor mImageCursor = null;
    private CursorAdapter mAdapter = null;
    private final String TAG = "grid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);



        ImageView imageView = (ImageView)findViewById(R.id.imageviewtest);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").resize(500, 500)
                .centerCrop().into(imageView);

    }
}
