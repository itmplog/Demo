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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /*
    class SampleGridViewAdapter extends BaseAdapter {
        private final Context context;
        private final List<String> urls = new ArrayList<String>();

        public SampleGridViewAdapter(Context context) {
            this.context = context;

            // Ensure we get a different ordering of images on each run.
            Collections.addAll(urls, Data.URLS);
            Collections.shuffle(urls);

            // Triple up the list.
            ArrayList<String> copy = new ArrayList<String>(urls);
            urls.addAll(copy);
            urls.addAll(copy);
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            SquaredImageView view = (SquaredImageView) convertView;
            if (view == null) {
                view = new SquaredImageView(context);
                view.setScaleType(CENTER_CROP);
            }

            // Get the image URL for the current position.
            String url = getItem(position);

            // Trigger the download of the URL asynchronously into the image view.
            Picasso.with(context) //
                    .load(url) //
                    .placeholder(R.drawable.placeholder) //
                    .error(R.drawable.error) //
                    .fit() //
                    .tag(context) //
                    .into(view);

            return view;
        }

        @Override public int getCount() {
            return urls.size();
        }

        @Override public String getItem(int position) {
            return urls.get(position);
        }

        @Override public long getItemId(int position) {
            return position;
        }
        */
}
