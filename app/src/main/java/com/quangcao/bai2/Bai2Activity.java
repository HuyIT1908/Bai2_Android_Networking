//package com.quangcao.bai2;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class Bai2Activity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bai1);
//    }
//}
package com.quangcao.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai2Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME = "https://codelycodehanh.000webhostapp.com";

    EditText edt_nhap1 , edt_nhap2;
    TextView tv_ket_qua;
    Button btn_xu_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        setTitle("Dùng POST");

        edt_nhap1 = findViewById(R.id.edt_canh);

        tv_ket_qua = findViewById(R.id.tv_canh);
        btn_xu_ly = findViewById(R.id.btn_canh);

        btn_xu_ly.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = edt_nhap1.getText().toString();


        Bai2PostAsync bai2PostAsync = new Bai2PostAsync(Bai2Activity.this , tv_ket_qua , name);
        bai2PostAsync.execute();
    }
}