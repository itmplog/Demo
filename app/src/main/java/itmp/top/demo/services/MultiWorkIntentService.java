package itmp.top.demo.services;

/**
 * Created by hz on 2016/4/2.
 */
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MultiWorkIntentService extends IntentService {

    public MultiWorkIntentService(){
        super("Download");
        Log.i("DemoLog", "DownloadIntentService构造函数, Thread: " + Thread.currentThread().getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("DemoLog", "DownloadIntentService -> onCreate, Thread: " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DemoLog", "DownloadIntentService -> onStartCommand, Thread: " + Thread.currentThread().getName() + " , startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        HttpURLConnection conn = null;
        InputStream is = null;
        String blogUrl = intent.getStringExtra("url");
        String blogName = intent.getStringExtra("name");
        try{
            //下载指定的文件
            URL url = new URL(blogUrl);
            conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                //我们在此处得到所下载文章的输入流，可以将其以文件的形式写入到存储卡上面或
                //将其读取出文本显示在App中
                is = conn.getInputStream();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
            if(is != null){
                try {
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.i("DemoLog", "DownloadIntentService -> onHandleIntent, Thread: " + Thread.currentThread().getName() + ", 《" + blogName + "》下载完成");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DemoLog", "DownloadIntentService -> onDestroy, Thread: " + Thread.currentThread().getName());
    }
}
