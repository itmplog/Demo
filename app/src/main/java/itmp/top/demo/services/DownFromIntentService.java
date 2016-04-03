package itmp.top.demo.services;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import itmp.top.demo.R;

public class DownFromIntentService extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private final static int DOWNLOAD_REQUEST_CODE = 0x111;
    private final static int IMAGE_REQUEST_CODE = 0x112;
    private final static String URL = "https://raw.githubusercontent.com/itmplog/android_utils/master/utils/generate_changelog.sh";
    private final static String IMAGE_URL = "https://lh3.googleusercontent.com/3VPA_VAuFZ9aGQOYk3Li0DW5iSvJFW5ufdezd8r0TGGnba6gPU1GA_ABmfbHQU9Z5SIw4dOZPVGrJc9Dq9CDrVNtOYKvfol0ch8rvjc=s660";

    //"https://www.gitignore.io/api/android";//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_down_from_intent_service);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        relativeLayout.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams textlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textView = new TextView(this);
        textView.setTextColor(Color.BLUE);
        textView.setBackgroundColor(Color.parseColor("#ffcc22dd"));
        textView.setLayoutParams(textlp);
        relativeLayout.addView(textView);
        //setContentView(textView);
        imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams1 = layoutParams;
        layoutParams1.addRule(RelativeLayout.BELOW, textView.getId());
        imageView.setLayoutParams(layoutParams1);
        relativeLayout.addView(imageView);

        FloatingActionButton floatingActionButton = new FloatingActionButton(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        lp.bottomMargin = 16;
        lp.rightMargin = 16;
        //lp.setMarginEnd(16);
        floatingActionButton.setLayoutParams(lp);
        //floatingActionButton.setBackground(getResources().getDrawable(R.mipmap.ic_launcher, getTheme()));
        floatingActionButton.setImageResource(R.mipmap.ic_launcher);
        floatingActionButton.setBackground(getResources().getDrawable(R.color.colorAccent, getTheme()));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                imageView.setBackground(null);
                PendingIntent pendingResult = createPendingResult(
                        DOWNLOAD_REQUEST_CODE, new Intent(), 0);
                Intent intent = new Intent(getApplicationContext(), DownloadIntentService.class);
                intent.putExtra(DownloadIntentService.URL_EXTRA, URL);
                intent.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingResult);
                startService(intent);

                PendingIntent pendingIntent = createPendingResult(
                        IMAGE_REQUEST_CODE, new Intent(), 0
                );
                Intent intent1 = new Intent(getApplicationContext(), DownloadIntentService.class);
                intent1.putExtra(DownloadIntentService.URL_EXTRA, IMAGE_URL);
                intent1.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingIntent);
                startService(intent1);
                Log.v(DownloadIntentService.TAG, "here");
            }
        });
        relativeLayout.addView(floatingActionButton);
        setContentView(relativeLayout);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == DownloadIntentService.RESULT_CODE) {
            switch (requestCode) {
                case DOWNLOAD_REQUEST_CODE:
                    if (data == null) {
                        new AlertDialog.Builder(this)
                                .setTitle("Null")
                                .setMessage("Null")
                                .create().show();
                    }
                    Log.v(DownloadIntentService.TAG, "  " + new String(data.getByteArrayExtra(DownloadIntentService.RSS_RESULT_EXTRA)));
                    textView.setText(new String(data.getByteArrayExtra(DownloadIntentService.RSS_RESULT_EXTRA)));
                    break;
                case IMAGE_REQUEST_CODE:
                    byte[] bytes = data.getByteArrayExtra(DownloadIntentService.RSS_RESULT_EXTRA);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,
                            0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                    //textView.setText(data.getStringExtra(DownloadIntentService.RSS_RESULT_EXTRA));

                    break;
            }
        }
    }
}
