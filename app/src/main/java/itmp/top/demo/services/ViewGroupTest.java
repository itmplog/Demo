package itmp.top.demo.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;


import itmp.top.demo.R;

/**
 * Created by hz on 2016/4/2.
 */
public class ViewGroupTest extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_from_intent_service);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
                if(msg.what == 0x111){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                synchronized (mSwipeRefreshLayout) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(5000);
                                Message message = new Message();
                                message.what = 0x111;
                                handler.sendMessage(message);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }
}
