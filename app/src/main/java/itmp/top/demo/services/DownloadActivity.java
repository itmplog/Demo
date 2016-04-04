package itmp.top.demo.services;

/**
 * Created by hz on 2016/4/2.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DownloadActivity extends Activity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setOnClickListener(this);
        setContentView(button);
    }

    @Override
    public void onClick(View v) {
        List<String> list = new ArrayList<>();
        list.add("Android中Handler的使用;http://blog.csdn.net/iispring/article/details/47115879");
        list.add("深入源码解析Android中的Handler,Message,MessageQueue,Looper;http://blog.csdn.net/iispring/article/details/47180325");
        list.add("Android新线程中更新主线程UI中的View方法汇总;http://blog.csdn.net/iispring/article/details/47300819");
        list.add("Android中HandlerThread的使用及原理解析;http://blog.csdn.net/iispring/article/details/47320407");
        list.add("Android中Looper的quit方法和quitSafely方法;http://blog.csdn.net/iispring/article/details/47622705");

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            String[] splits = str.split(";");
            String name = splits[0];
            String url = splits[1];
            Intent intent = new Intent(this, MultiWorkIntentService.class);
            intent.putExtra("name", name);
            intent.putExtra("url", url);
            //启动IntentService
            startService(intent);
        }
    }
}
