package com.quangcao.bai2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Bai2PostAsync extends AsyncTask<Void , Void , Void> {

//    String url_API = "https://codelycodehanh.000webhostapp.com/api/bai2_post.php";
    String url_API = "https://batdongsanabc.000webhostapp.com/mob403/demo2_api_post.php";

    Context context;
    TextView tv_kq;
    String canh;
    String kq;

    ProgressDialog progressDialog; // Theo dõi quá trình đọc dữ liệu

    public Bai2PostAsync(Context context, TextView tv_kq, String canh) {
        this.context = context;
        this.tv_kq = tv_kq;
        this.canh = canh;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            // 1. Lấy đường dẫn
            URL url = new URL(url_API);
            // 2. Xử lí tham số POST
            String parmas = "canh=" + URLEncoder.encode(canh,"utf-8"); // mã hóa tham số
            // 3. Mở kết nối
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 4. Set tham số cho POST
            httpURLConnection.setDoOutput(true); // có lấy output
            httpURLConnection.setRequestMethod("POST"); // xác định method POST
            // gửi dữ liệu vừa nhập lên Server
            httpURLConnection.setFixedLengthStreamingMode( parmas.getBytes().length );
            // xác định kiểu thuộc tính
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // Lấy về output và tham số
            PrintWriter printWriter = new PrintWriter( httpURLConnection.getOutputStream() );
            printWriter.print( parmas );
            printWriter.close();
            // đọc dữ liệu
            String line = "";
            BufferedReader bufferedReader =
                    new BufferedReader( new InputStreamReader( httpURLConnection.getInputStream() ) );
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null ){
                stringBuilder.append(line + "\n"); // đưa dòng đọc được vào stringBuilder
            }

            kq = stringBuilder.toString(); // chuyển kết quả thành chuỗi
            httpURLConnection.disconnect();


        } catch (Exception ex){
            tv_kq.setText( ex.getMessage() );
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);

        tv_kq.setText(kq);

        if(progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Đang tính toán...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
