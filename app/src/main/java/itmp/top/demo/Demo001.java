package itmp.top.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Demo001 extends AppCompatActivity {

    private Button button = null;
    private TextView textView = null;
    private EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo001);

        textView = (TextView)findViewById(R.id.demo001_text);
        editText = (EditText)findViewById(R.id.demo001_edit);
        button = (Button)findViewById(R.id.demo001_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = null;
                if((tmp = editText.getText().toString()).equals("")){
                    Toast.makeText(getApplicationContext(), "请输入要传递的值", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intend = new Intent(Demo001.this, Demo001_other.class);
                    //Bundle bundle = new Bundle();
                    intend.putExtra("message", tmp);
                    startActivity(intend);
                }
            }
        });

    }
}
