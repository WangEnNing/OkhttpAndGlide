package app.wen.com.okhttpdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

import app.wen.com.okhttpdemo.R;
import app.wen.com.okhttpdemo.adapter.ImageAdapter;
import app.wen.com.okhttpdemo.bean.PhotoBean;
import app.wen.com.okhttpdemo.config.Url;
import app.wen.com.okhttpdemo.http.HttpManager;
import app.wen.com.okhttpdemo.utils.GsonTools;

public class GlideActivity extends AppCompatActivity {
    private RecyclerView recycleview;
    private ImageAdapter imageadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initcontral();
        initdata();
    }

    private void initdata() {
        HttpManager.getAsyn(Url.BASE_URI, new HttpManager.StringCallback() {

            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(GlideActivity.this, "网络异常", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResponse(String response) {
                PhotoBean photobean = GsonTools.getPerson(response, PhotoBean.class);
                List<PhotoBean.ResultsEntity> data = photobean.getResults();
                imageadapter.setdata(data);
                recycleview.setAdapter(imageadapter);
            }
        });
    }

    private void initcontral() {
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        imageadapter = new ImageAdapter(this);
    }

}
