package cn.org.nercita.agriculturalconsultant.view.address;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;


/**
 * Created by cuiyonghong on 16/11/8.
 */

public class AddressDialogEngine implements AdapterView.OnItemClickListener {
    private static final int INDEX_TAB_PROVINCE = 0;
    private static final int INDEX_TAB_CITY = 1;
    private static final int INDEX_TAB_COUNTY = 2;
    private static final int INDEX_TAB_STREET = 3;
    private static final int INDEX_INVALID = -1;
    private static final String TAG = AddressDialogEngine.class.getSimpleName();
    @Bind(R.id.textViewProvince)
    TextView mProvince;
    @Bind(R.id.textViewCity)
    TextView mCity;
    @Bind(R.id.textViewCounty)
    TextView mCounty;
  /*  @InjectView(R.id.textViewStreet)
    TextView mStreet;*/
    @Bind(R.id.layout_tab)
    LinearLayout layoutTab;
    @Bind(R.id.indicator)
    View mIndicator;
    @Bind(R.id.listView)
    ListView mListView;
    @Bind(R.id.tv_save_city)
    TextView mSaveCity;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    private Context mContext;
    private View addressView;

    private int tabIndex = INDEX_TAB_PROVINCE;
    private int provinceIndex = INDEX_INVALID;
    private int cityIndex = INDEX_INVALID;
    private int countyIndex = INDEX_INVALID;
    private int townIndex = INDEX_INVALID;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private CountyAdapter countyAdapter;
    private TownsAdapter townsAdapter;
    private static final String DEFAULT_STR = "请选择";

    public  String[] provinces = new String[]{
            "北京市", "上海市", "重庆市", "天津市",
            "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省",
            "内蒙古自治区", "宁夏回族自治区", "广西壮族自治区", "西藏自治区", "新疆维吾尔自治区",
            /*"香港", "澳门","台湾"*/
    };
    private ArrayList<CityInfoBean> provinces1;
    private ArrayList<CityInfoBean> cities = new ArrayList<>();
    private ArrayList<CityInfoBean> counties = new ArrayList<>();
    private ArrayList<String> towns = new ArrayList<>();
    private String code = "";

    public AddressDialogEngine(Context context) {
        this.mContext = context;
        initView();
        initAdapters();
        getInitDatas();
    }

    private void getInitDatas() {
        List<String> strings = Arrays.asList(provinces);
//        provinces1 = new ArrayList<>(strings);
        mListView.setAdapter(provinceAdapter);
        if (provinces1==null){
            retrieveProvinceWith();
            provinceAdapter.setProvinceList(provinces1);
        }
        updateProgressVisibility();
    }

    private void initAdapters() {
        provinceAdapter = new ProvinceAdapter();
        cityAdapter = new CityAdapter();
        countyAdapter = new CountyAdapter();
        townsAdapter = new TownsAdapter();
    }

    private void updateProgressVisibility() {
        ListAdapter adapter = mListView.getAdapter();
        int itemCount = adapter.getCount();
        mProgressBar.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }

    private void updateTabsVisibility() {
        mProvince.setVisibility(notEmpty(provinces1) ? View.VISIBLE : View.GONE);
        mCity.setVisibility(notEmpty(cities) ? View.VISIBLE : View.GONE);
        mCounty.setVisibility(notEmpty(counties) ? View.VISIBLE : View.GONE);
//        mStreet.setVisibility(notEmpty(towns) ? View.VISIBLE : View.GONE);

        mProvince.setEnabled(tabIndex != INDEX_TAB_PROVINCE);
        mCity.setEnabled(tabIndex != INDEX_TAB_CITY);
        mCounty.setEnabled(tabIndex != INDEX_TAB_COUNTY);
//        mStreet.setEnabled(tabIndex != INDEX_TAB_STREET);
    }
    public boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }


    public boolean notEmpty(List list) {
        return list != null && list.size() > 0;
    }

    /**
     * 初始化view 数据
     */
    private void initView() {
        addressView = View.inflate(mContext, R.layout.address_selector, null);
        ButterKnife.bind(this, addressView);
        mProvince.setOnClickListener(new OnProvinceTabClickListener());
        mCity.setOnClickListener(new OnCityTabClickListener());
        mCounty.setOnClickListener(new OnCountyTabClickListener());
//        mStreet.setOnClickListener(new OnStreetTabClickListener());
        mListView.setOnItemClickListener(this);
        updateIndicator();
    }

    private void updateIndicator() {
        addressView.post(new Runnable() {
            @Override
            public void run() {
                switch (tabIndex) {
                    case INDEX_TAB_PROVINCE:
                        buildIndicatorAnimatorTowards(mProvince).start();
                        break;
                    case INDEX_TAB_CITY:
                        buildIndicatorAnimatorTowards(mCity).start();
                        break;
                    case INDEX_TAB_COUNTY:
                        buildIndicatorAnimatorTowards(mCounty).start();
                        break;
                  /*  case INDEX_TAB_STREET:
                        buildIndicatorAnimatorTowards(mStreet).start();
                        break;*/
                }
            }
        });
    }

    private class OnProvinceTabClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            tabIndex = INDEX_TAB_PROVINCE;
            mListView.setAdapter(provinceAdapter);

            if (provinceIndex != INDEX_INVALID) {
                mListView.setSelection(provinceIndex);
            }

            updateTabsVisibility();
            updateIndicator();
        }
    }

    private class OnCityTabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            tabIndex = INDEX_TAB_CITY;
            mListView.setAdapter(cityAdapter);

            if (cityIndex != INDEX_INVALID) {
                mListView.setSelection(cityIndex);
            }

            updateTabsVisibility();
            updateIndicator();
        }
    }

    private class OnCountyTabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            tabIndex = INDEX_TAB_COUNTY;
            mListView.setAdapter(countyAdapter);

            if (countyIndex != INDEX_INVALID) {
                mListView.setSelection(countyIndex);
            }

            updateTabsVisibility();
            updateIndicator();
        }
    }

    private class OnStreetTabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            tabIndex = INDEX_TAB_STREET;
            mListView.setAdapter(townsAdapter);

            if (townIndex != INDEX_INVALID) {
                mListView.setSelection(townIndex);
            }

            updateTabsVisibility();
            updateIndicator();
        }
    }

    private AnimatorSet buildIndicatorAnimatorTowards(TextView tab) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mIndicator, "X", mIndicator.getX(), tab.getX());

        final ViewGroup.LayoutParams params = mIndicator.getLayoutParams();
        ValueAnimator widthAnimator = ValueAnimator.ofInt(params.width, tab.getMeasuredWidth());
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.width = (int) animation.getAnimatedValue();
                mIndicator.setLayoutParams(params);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.playTogether(xAnimator, widthAnimator);

        return set;
    }

    /**
     * 返回view 供dialog 显示
     *
     * @return
     */
    public View getContentView() {
        return addressView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (tabIndex) {
            case INDEX_TAB_PROVINCE:
                CityInfoBean item = provinceAdapter.getItem(position);
                mProvince.setText(item.getName());
                mCity.setText("请选择");
                mCounty.setText("请选择");
                cities.clear();
                cityAdapter.setCitiesList(cities);
                counties.clear();
                countyAdapter.setCountysList(counties);
                towns.clear();
                townsAdapter.setTownsList(towns);
                code = item.getCode();
                // 更新已选中项
                this.provinceIndex = position;
                this.cityIndex = INDEX_INVALID;
                this.countyIndex = INDEX_INVALID;
                this.townIndex = INDEX_INVALID;

                // 更新选中效果
                provinceAdapter.notifyDataSetChanged();

                retrieveCitiesWith(item.getName());
                break;

            case INDEX_TAB_CITY:
                CityInfoBean city = cityAdapter.getItem(position);
                mCity.setText(city.getName());
                mCounty.setText("请选择");
                counties.clear();
                countyAdapter.setCountysList(counties);
                towns.clear();
                townsAdapter.setTownsList(towns);
                this.cityIndex = position;
                this.countyIndex = INDEX_INVALID;
                this.townIndex = INDEX_INVALID;
                code = city.getCode();
                cityAdapter.notifyDataSetChanged();
                retrieveCountiesWith(city.getName(),mProvince.getText().toString());

                break;

            case INDEX_TAB_COUNTY:
                CityInfoBean county = countyAdapter.getItem(position);

                mCounty.setText(county.getName());
                townsAdapter.setTownsList(towns);
                this.countyIndex = position;
                code = county.getCode();
                countyAdapter.notifyDataSetChanged();
                break;
            case INDEX_TAB_STREET:
                String town = (String) townsAdapter.getItem(position);
                this.townIndex = position;
                townsAdapter.notifyDataSetChanged();

                break;
        }
        updateTabsVisibility();
        updateIndicator();

    }

    private void retrieveStreetsWith(String county) {
        mProgressBar.setVisibility(View.VISIBLE);

        AddressDataEngine.getInstance(mContext).getTownDataList(new AddressDataInterface.AddressDataListener<String>() {
            @Override
            public void onDataArrived(List<String> datas) {
                Log.e(TAG,"datas : "+datas.size());
                if (datas != null && datas.size() > 0){
                    towns.clear();
                    towns.addAll(datas);
                    townsAdapter.setTownsList(towns);
                    tabIndex = INDEX_TAB_STREET;
                    mListView.setAdapter(townsAdapter);
                    update();
                }else {
                    updateProgressVisibility();
                }
            }
        },county);


    }

    private void retrieveCountiesWith(String city,String province) {
        mProgressBar.setVisibility(View.VISIBLE);

        AddressDataEngine.getInstance(mContext).getCountyDataList(new AddressDataInterface.AddressDataListener<CityInfoBean>() {
            @Override
            public void onDataArrived(List<CityInfoBean> datas) {
                if (datas != null && datas.size() >0 ){
                    counties.clear();
                    counties.addAll(datas);
                    mListView.setAdapter(countyAdapter);
                    countyAdapter.setCountysList(counties);
                    tabIndex = INDEX_TAB_COUNTY;
                    update();
                }else {
                    updateProgressVisibility();
                }
            }
        },city,province);

    }

//    private void callbackInternal() {
//       if (listener != null) {
//            String province = provinces == null || provinceIndex == INDEX_INVALID ? null : provinces.get(provinceIndex);
//            City city = cities == null || cityIndex == INDEX_INVALID ? null : cities.get(cityIndex);
//            County county = counties == null || countyIndex == INDEX_INVALID ? null : counties.get(countyIndex);
//            Street street = streets == null || streetIndex == INDEX_INVALID ? null : streets.get(streetIndex);
//
//            listener.onAddressSelected(province, city, county, street);
//        }
//    }
    @OnClick({R.id.tv_save_city})
    public void onClick(View v){
        int itemId = v.getId();
        switch (itemId) {
            case R.id.tv_save_city:
                if (saveItemClick != null){
                    String province = mProvince.getText().toString().trim();
                    String city = mCity.getText().toString().trim();
                    String county = mCounty.getText().toString().trim();

//                    String town = mStreet.getText().toString().trim();
                    province = TextUtils.equals(DEFAULT_STR,province) ? "" : province;
                    city = TextUtils.equals(DEFAULT_STR,city) ? "" : city;
                    county = TextUtils.equals(DEFAULT_STR,county) ? "" : county;
//                    town = TextUtils.equals(DEFAULT_STR,town) ? "" : town;
                    saveItemClick.onSaveItemClick(province,city,county,code);

                }
                break;
        }
    }
    private void retrieveProvinceWith(){
        mProgressBar.setVisibility(View.VISIBLE);
        AddressDataEngine.getInstance(mContext).getProvincesDataList(new AddressDataInterface.AddressDataListener<CityInfoBean>() {
            @Override
            public void onDataArrived(List<CityInfoBean> datas) {
                if (datas!=null&&datas.size()>0){
                    provinces1 = (ArrayList<CityInfoBean>) datas;
                }else {
                    updateProgressVisibility();
                }
            }
        });
    }
    private void retrieveCitiesWith(String item) {
        mProgressBar.setVisibility(View.VISIBLE);
            AddressDataEngine.getInstance(mContext).getCitiesDataList(new AddressDataInterface.AddressDataListener<CityInfoBean>() {
                @Override
                public void onDataArrived(List<CityInfoBean> datas) {
                    if (datas != null && datas.size() >0){
                        cities.clear();
                        cities.addAll(datas);
                        cityAdapter.setCitiesList(cities);
                        tabIndex = INDEX_TAB_CITY;

                        mListView.setAdapter(cityAdapter);
                        update();
                    }else {
                        updateProgressVisibility();
                    }
                }
            },item);
//        }

    }

    private void update() {
        updateTabsVisibility();
        updateProgressVisibility();
        updateIndicator();
    }

    class ProvinceAdapter extends BaseAdapter {

        private List<CityInfoBean> provinceList = new ArrayList<>();

        @Override
        public int getCount() {
            return provinceList.size();
        }

        @Override
        public CityInfoBean getItem(int position) {
            return provinceList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);

                holder = new Holder();
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.imageViewCheckMark = (ImageView) convertView.findViewById(R.id.imageViewCheckMark);

                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            String item = getItem(position).getName();
            holder.textView.setText(item);
            boolean checked = provinceIndex != INDEX_INVALID && TextUtils.equals(provinceList.get(provinceIndex).getName(), item);
            holder.textView.setEnabled(!checked);
            holder.imageViewCheckMark.setVisibility(checked ? View.VISIBLE : View.GONE);

            return convertView;
        }

        class Holder {
            TextView textView;
            ImageView imageViewCheckMark;
        }

        public void setProvinceList(List<CityInfoBean> dataList){
            if (dataList != null){
                provinceList.clear();
                provinceList.addAll(dataList);
                notifyDataSetChanged();
            }
        }
    }

    private class CityAdapter extends BaseAdapter {

        private List<CityInfoBean> cities = new ArrayList<>();
        @Override
        public int getCount() {
            return cities.size();
        }

        @Override
        public CityInfoBean getItem(int position) {
            return cities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          Holder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);

                holder = new Holder();
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.imageViewCheckMark = (ImageView) convertView.findViewById(R.id.imageViewCheckMark);

                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            String item = getItem(position).getName();
            holder.textView.setText(item);

            boolean checked = cityIndex != INDEX_INVALID && TextUtils.equals(cities.get(cityIndex).getName(), item);
            holder.textView.setEnabled(!checked);
            holder.imageViewCheckMark.setVisibility(checked ? View.VISIBLE : View.GONE);

            return convertView;
        }

        class Holder {
            TextView textView;
            ImageView imageViewCheckMark;
        }

        public void setCitiesList(List<CityInfoBean> dataList){
            if (dataList != null){
                cities.clear();
                cities.addAll(dataList);
                notifyDataSetChanged();
            }
        }
    }

    private class CountyAdapter extends BaseAdapter{

        private List<CityInfoBean> countys = new ArrayList<>();

        @Override
        public int getCount() {
            return countys.size();
        }

        @Override
        public CityInfoBean getItem(int position) {
            return countys.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);

                holder = new Holder();
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.imageViewCheckMark = (ImageView) convertView.findViewById(R.id.imageViewCheckMark);

                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            String item = (String) getItem(position).getName();
            holder.textView.setText(item);

            boolean checked = countyIndex != INDEX_INVALID && TextUtils.equals(countys.get(countyIndex).getName(),item);
            holder.textView.setEnabled(!checked);
            holder.imageViewCheckMark.setVisibility(checked ? View.VISIBLE : View.GONE);

            return convertView;
        }

        class Holder {
            TextView textView;
            ImageView imageViewCheckMark;
        }

        public void setCountysList(List<CityInfoBean> dataList){
            if (dataList != null){
                countys.clear();
                countys.addAll(dataList);
                notifyDataSetChanged();
            }
        }
    }

    private class TownsAdapter extends BaseAdapter{

        private List<String> towns = new ArrayList<>();

        @Override
        public int getCount() {
            return towns.size();
        }

        @Override
        public Object getItem(int position) {
            return towns.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);

                holder = new Holder();
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.imageViewCheckMark = (ImageView) convertView.findViewById(R.id.imageViewCheckMark);

                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            String item = (String) getItem(position);
            holder.textView.setText(item);
            boolean checked = townIndex != INDEX_INVALID && TextUtils.equals(towns.get(townIndex),item);
            holder.textView.setEnabled(!checked);
            holder.imageViewCheckMark.setVisibility(checked ? View.VISIBLE : View.GONE);

            return convertView;
        }

        class Holder {
            TextView textView;
            ImageView imageViewCheckMark;
        }

        public void setTownsList(List<String> dataList){
            if (dataList != null){
                towns.clear();
                towns.addAll(dataList);
                notifyDataSetChanged();
            }
        }
    }

    public OnSaveItemClickListener getSaveItemClick() {
        return saveItemClick;
    }

    public void setSaveItemClick(OnSaveItemClickListener saveItemClick) {
        this.saveItemClick = saveItemClick;
    }

    private OnSaveItemClickListener saveItemClick;

    public static interface OnSaveItemClickListener{
        void onSaveItemClick(String province, String city, String county, String code);
    }
}
