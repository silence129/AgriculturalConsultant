package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import okhttp3.Call;

/**
 * 描述：基类
 * @author GaoWenXu
 * @date 2017/4/11 10:27
 * @version v1.0.0
 */
public class ACBaseActivity extends AppCompatActivity {

    private static final String TAG = ACBaseActivity.class.getSimpleName();
    protected int mCurrentPage= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != -1){
            setContentView(getLayoutId());
        }else{
            throw new IllegalArgumentException("layout resource  can't be  -1 ");
        }

        ButterKnife.bind(this);
        init(savedInstanceState);
        initData();
    }

    protected void initData() {
        requestData(false);
    }

    /**
     *  请求数据
     * @param refresh
     */
    protected void requestData(boolean refresh) {
        String key =  getCacheKey();

        if (isReadCacheKey(refresh)){   // if true read cache
            String response = SPUtil.getString(this, getCacheKey(), "");
            if (!TextUtils.isEmpty(response)){
                readCacheData(response);
            }else {
                sendRequestData();
            }

        }else{
            sendRequestData();
        }
    }


    protected void readCacheData(String response) {}

    protected void sendRequestData() {}

    /**
     *  判断是否要读取缓存数据
     * @param refresh
     * @return
     */
    private boolean isReadCacheKey(boolean refresh) {
        String key = getCacheKey();
//        if (!NercitaDevice.hasInternet()){
//            return true;       // if there isn't net
//        }
        String cacheData = SPUtil.getString(this, key, "");
        if (!TextUtils.isEmpty(cacheData) && !refresh && mCurrentPage == 1){
            return true;
        }
        return false;
    }

    private String getCacheKey() {

        return new StringBuilder(getCacheKeyPrefix()).append("_").append(mCurrentPage).toString();
    }

    /**
     *  缓存前缀， 在需要缓存的页面返回一个缓存前缀的 key
     * @return
     */
    protected String getCacheKeyPrefix(){
        return "";
    }

    /**
     * 初始化方法
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState) {
    }

    protected StringCallback callback  = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG,"StudyPage  error :"+e.getMessage());
            onErrorTypeDo();
            if (mCurrentPage <= 1 ){
                mCurrentPage =  1;
            }else {
                mCurrentPage--;
            }
        }

        @Override
        public void onResponse(String response, int id) {
            executeDataBySubClazz(response);
            processCacheProblem(response);
        }
    };

    /**
     * 当网络请求错误的时候做一些操作
     */
    protected void onErrorTypeDo() {

    }

    /**
     *  处理缓存问题
     * @param response
     */
    protected void processCacheProblem(String response) {
        if (response != null){
            SPUtil.putString(this,getCacheKey(),response);
        }
    }

    protected void executeDataBySubClazz(String response) {

    }

    /**
     * 由子类返回具体的布局id
     * @return
     */
    protected int getLayoutId() {
        return -1;
    }
}
