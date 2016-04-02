package itmp.top.demo.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hz on 2016/4/2.
 */
public class DownloadIntentService extends IntentService {

    public final static String TAG = DownloadIntentService.class.getSimpleName();
    public static final String PENDING_RESULT_EXTRA = "pending_result";
    public static final String URL_EXTRA = "url";
    public static final String RSS_RESULT_EXTRA = "url";

    public static final int RESULT_CODE = 0;

    //private IllustrativeRSSParser parser;

    public DownloadIntentService(){
        super(TAG);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    protected void onHandleIntent(Intent intent) {
        PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT_EXTRA);

        Log.v(TAG, "started");

        //String tmp = "";
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //BufferedReader bufferedReader = null;

        try {
            URLConnection urlConnection = new URL(intent.getStringExtra(URL_EXTRA)).openConnection();
            //bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //int length = urlConnection.getContentLength();

            //String read = null;

            int hasRead = 0;
            //while ((read = bufferedReader.readLine()) != null){

            while((hasRead = urlConnection.getInputStream().read(bytes)) > 0){
                byteArrayOutputStream.write(bytes, 0, hasRead);
                //tmp += read;
                //tmp += new String(bytes, 0, hasRead); //Base64.encodeToString(bytes, 0, hasRead, Base64.NO_PADDING);
            }

            //Bitmap bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
            Log.v(TAG, "tmp: " + new String(bytes));

            // save to file
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "aa.base");
            FileOutputStream fileOutputStream =  new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();

            Intent intent1 = new Intent();
            intent1.putExtra(RSS_RESULT_EXTRA, byteArrayOutputStream.toByteArray());//Base64.encodeToString(tmp.getBytes(), Base64.DEFAULT));

            reply.send(this, RESULT_CODE, intent1);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (PendingIntent.CanceledException e){
            e.printStackTrace();
        }
    }
}
