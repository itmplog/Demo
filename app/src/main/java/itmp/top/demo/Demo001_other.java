package itmp.top.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;

public class Demo001_other extends AppCompatActivity {

    private TextView textView = null;
    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo001_other);

        textView = (TextView)findViewById(R.id.demo001_other_text);
        button = (Button)findViewById(R.id.demo001_other_button);

        textView.setText(getIntent().getStringExtra("message"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
