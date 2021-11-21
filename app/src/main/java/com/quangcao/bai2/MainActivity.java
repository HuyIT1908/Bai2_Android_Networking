package com.quangcao.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void bai1(View view){
        startActivity(new Intent(MainActivity.this , Bai1Activity.class));
    }

    public void bai2(View view){
        startActivity(new Intent(MainActivity.this , Bai2Activity.class));
    }

}