package com.quangcao.bai2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Bai1Asyn extends AsyncTask<Void , Void , Void> {

    String url_API = "https://codelycodehanh.000webhostapp.com/api/bai2.php";
    Context context;
    TextView tv_kq;
    String name , mark;
    String kq;

    public Bai1Asyn(Context context, TextView tv_kq, String name, String mark) {
        this.context = context;
        this.tv_kq = tv_kq;
        this.name = name;
        this.mark = mark;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        url_API += "?name=" + this.name + "&mark=" + this.mark;
        try {
            URL url = new URL(url_API); // lấy đường dẫn
            // tạo bộ đệm để đọc dữ liệu
            BufferedReader bufferedReader =
                    new BufferedReader( new InputStreamReader( url.openConnection().getInputStream() ) );
            // biến lưu dữ liệu
            String line = "";
            // đối tượng lưu trữ kết quả
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null ){
                stringBuilder.append(line + "\n"); // đưa dòng đọc được vào stringBuilder
            }

            kq = stringBuilder.toString(); // chuyển kết quả thành chuỗi

        } catch (Exception ex){
            tv_kq.setText( ex.getMessage() );
            Log.e("------------- Error " , ex.toString() );
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);

        tv_kq.setText(kq);
    }

}
