package app.wen.com.okhttpdemo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.wen.com.okhttpdemo.R;
import app.wen.com.okhttpdemo.http.HttpManager;
import app.wen.com.okhttpdemo.http.HttpRequester;
import app.wen.com.okhttpdemo.utils.ConUtils;
import app.wen.com.okhttpdemo.utils.FormFile;
import app.wen.com.okhttpdemo.view.ActionSheet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ActionSheet.MenuItemClickListener {
    private BootstrapCircleThumbnail imageview;
    private static final int SELECT_PHOTO = 0;// 选择一张照片时用到
    private static final int SELECT_CAMERO = 1;// 拍照片时用到
    /**
     * 图片路径
     */
    private String filePath;
    private Bitmap tmpBitmap;
    private String url = "";
    private Long l_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initContral();
    }

    private void initContral() {
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.send_pic).setOnClickListener(this);
        findViewById(R.id.glide_but).setOnClickListener(this);
        imageview = (BootstrapCircleThumbnail) findViewById(R.id.my_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                setTheme(R.style.ActionSheetStyleIOS7);
                showActionSheet();
                break;
            case R.id.send_pic:
                l_time = System.currentTimeMillis();
                String Str_time = Long.toString(l_time);
                if (filePath != null) {
                    start_okhttp(Str_time);
                    start_asyn(Str_time);
                } else {
                    Toast.makeText(MainActivity.this, "请选择照片", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.glide_but:
                Intent i = new Intent();
                i.setClass(MainActivity.this, GlideActivity.class);
                startActivity(i);
                break;

        }
    }

    public void start_okhttp(String str_time) {

        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            int data = fis.available();
            Log.e("111", "data 文件大小" + data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HttpManager.postAsyn2(url, new HttpManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e("111", "222==上传失败" + request.toString());
                }

                @Override
                public void onResponse(String response) {
                    Long time1 = System.currentTimeMillis();
                    long time_cha = time1 - l_time;
                    Log.e("111", "okhttp222时间差" + time_cha);
                    Log.e("111", "222==上传成功" + response);

                }
            }, file, "test1", new HttpManager.Param("time", str_time));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void showActionSheet() {
        ActionSheet menuView = new ActionSheet(this);
        menuView.setCancelButtonTitle("取消");// before
        // add
        // items
        menuView.addItems("本地照片", "拍照");
        menuView.setItemClickListener(this);
        menuView.setCancelableOnTouchMenuOutside(true);
        menuView.showMenu();
    }

    @Override
    public void onItemClick(int itemPosition) {
        switch (itemPosition) {
            case 0:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                break;
            case 1:
                filePath = null;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File mediaFile = ConUtils.getOutputMediaFile(this);
                filePath = mediaFile.getAbsolutePath();
                Uri fileUri = Uri.fromFile(mediaFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, SELECT_CAMERO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    if (selectedImage == null) {
                        return;
                    }
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    if (selectedImage != null && filePathColumn != null) {
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        if (cursor == null) {
                            return;
                        }
                        if (cursor.moveToFirst()) {
                            int columnIndex = cursor
                                    .getColumnIndex(filePathColumn[0]);
                            filePath = cursor.getString(columnIndex);
                            showImage(filePath);
                        }
                        cursor.close();
                    }
                }
                break;
            case SELECT_CAMERO:
                if (filePath != null) {
                    showImage(filePath);
                } else {
                    Toast.makeText(MainActivity.this, "未找到图片，请重新选取照片", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void showImage(String imagePath) {
        tmpBitmap = ConUtils.getBitmapConsiderExif(imagePath);
        imageview.setImageBitmap(tmpBitmap);
    }

    public void start_asyn(final String str_time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormFile formFile = new FormFile("image", ConUtils.File2byte(filePath), "noName");
                Map<String, String> params = new HashMap<String, String>();
                params.put("time", str_time);
                String result = HttpRequester.post(url, params, formFile);
                Log.e("111", "asyn返回的" + result);
                Long time1 = System.currentTimeMillis();
                Log.e("111", "asyn返回的时间差" + Long.toString(time1 - l_time));
            }
        }
        ).start();

    }


}
