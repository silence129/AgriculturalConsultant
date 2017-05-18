package cn.org.nercita.agriculturalconsultant.main.me.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.me.adapter.PhotoAdapter;
import cn.org.nercita.agriculturalconsultant.utils.BitmapUtils;
import cn.org.nercita.agriculturalconsultant.utils.RecyclerItemClickListener;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Call;

import static cn.org.nercita.agriculturalconsultant.main.communicate.activity.NewTechGroupActivity.REQUEST_CODE_ASK_CALL_IMAGE;

/**
 * Created by 范博文 on 2017/4/10.
 * 上传记录
 */

public class UpDateRecoredActivity extends BaseActivity {
    private static final String TAG = UpDateRecoredActivity.class.getSimpleName();
    @Bind(R.id.updatecontent)
    EditText updatecontent;
    @Bind(R.id.updateimg)
    LinearLayout updateimg;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.photo_picker)
    RecyclerView photoPicker;
    @Bind(R.id.commit)
    Button commit;
    @Bind(R.id.title)
    TitleBar title;
    ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<String> photos;
    private PhotoAdapter photoAdapter;
    private String content;
    private SVProgressHUD svProgressHUD;
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

            }
        }
    };

    @Override
    protected int getContentView() {
        return R.layout.activity_updaterecored;
    }

    @Override
    protected void initData() {
        super.initData();
        //进度条
        svProgressHUD = new SVProgressHUD(UpDateRecoredActivity.this);
        photoAdapter = new PhotoAdapter(UpDateRecoredActivity.this, selectedPhotos);
        photoPicker.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        photoPicker.setAdapter(photoAdapter);
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void initWidget() {
        super.initWidget();
        photoPicker.addOnItemTouchListener(new RecyclerItemClickListener(UpDateRecoredActivity
                .this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PhotoPreview.builder()
                        .setPhotos(photos)
                        .setCurrentItem(position)
                        .start(UpDateRecoredActivity.this, PhotoPreview.REQUEST_CODE);
            }
        }));
    }

    @OnClick({R.id.updateimg, R.id.location, R.id.photo_picker, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            //上传图片
            case R.id.updateimg:
                //检查权限
                checkPromission();
                break;
            case R.id.location:
                break;
            case R.id.photo_picker:
                break;
            //提交
            case R.id.commit:
                content = updatecontent.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.showShort(UpDateRecoredActivity.this, "上传内容不能为空!");
                    return;
                }
                svProgressHUD.showWithStatus("上传中");
                commit();
                break;
        }
    }


    private void commit() {
        final PostFormBuilder post = OkHttpUtils.post();
        post.url(Constants.UPDATEFARMMESSAGE);
        if (selectedPhotos.size() > 0) {
            for (int i = 0; i < selectedPhotos.size(); i++) {
                Log.e(TAG,selectedPhotos.get(i));
                Uri uri = Uri.fromFile(new File(selectedPhotos.get(i)));
                File file = BitmapUtils.scal(uri);
                post.addFile("files", selectedPhotos.get(i), file);//相关图片
            }
        }
        post.addParams("content", content);
        post.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, e.getMessage() + "");
                svProgressHUD.dismiss();
                ToastUtil.show(getApplicationContext(), "网络连接失败，请稍后再试");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, response);
                if (response.contains("200")) {
                    ToastUtil.show(getApplicationContext(), "上传成功");
                    selectedPhotos.clear();
                    photoAdapter.notifyDataSetChanged();
                    updatecontent.setText("");
                    setResult(0);
                    finish();
                }else {
                    ToastUtil.show(getApplicationContext(), "上传失败，请稍后重试");
                }
                svProgressHUD.dismiss();


            }
        });


    }

    /**
     * author:范博文
     * date:2017/4/10 17:17
     * des: 动态申请权限
     * param:null
     * return:null
     */
    private void checkPromission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkSDCardPermisson = ContextCompat.checkSelfPermission(UpDateRecoredActivity
                    .this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int checkCameraPermission = ContextCompat.checkSelfPermission(UpDateRecoredActivity
                    .this, Manifest.permission.CAMERA);
            if (checkSDCardPermisson != PackageManager.PERMISSION_GRANTED ||
                    checkCameraPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UpDateRecoredActivity.this, new
                        String[]{Manifest.permission.CAMERA, Manifest.permission
                        .READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_CALL_IMAGE);
                return;
            } else {
                //上面已经写好的获取照片方法
                chooseImage();

            }
        } else {
            //上面已经写好的获取照片方法
            chooseImage();
        }
    }

    /**
     * author:范博文
     * date:2017/4/10 17:18
     * des: 申请权限回调
     * param: null
     * return: null
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_IMAGE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    chooseImage();
                } else {
                    Toast.makeText(UpDateRecoredActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    /**
     * 接收图片结果
     * 范博文 2017.4.10
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode ==
                PhotoPreview
                        .REQUEST_CODE)) {

            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                //装有选择图片的路径
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

    /**
     * author:范博文
     * date:2017/4/10 9:40
     * des: 从图库选择图片
     * param:null
     * return:null
     */
    public void chooseImage() {
        PhotoPicker.builder()
                .setPhotoCount(4)
                .setShowCamera(true)
                .setPreviewEnabled(false)
                .start(this);
    }
}
