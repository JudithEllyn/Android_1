package hk.edu.cuhk.ie.iems5722.a1_1155162647;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在xml中获取btn1
        Button btn1 = findViewById(R.id.btn1);
//        Toolbar toolbar1 = findViewById(R.id.toolbar1);
//        toolbar1.setTitle("IEMS5722");
        //监听点击事件
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }

        });

    }
}