package com.ycxy.ymh.litepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ycxy.ymh.litepal.bean.Person;

public class MainActivity extends AppCompatActivity {
private Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save = (Button) this.findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person man = new Person(1,"chenpan");
                man.save();
            }
        });
    }
}
