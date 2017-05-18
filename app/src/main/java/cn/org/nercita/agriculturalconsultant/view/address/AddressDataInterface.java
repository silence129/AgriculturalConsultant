package cn.org.nercita.agriculturalconsultant.view.address;

import java.util.List;

/**
 * Created by cuiyonghong on 16/11/8.
 */

public interface AddressDataInterface {

    void getProvincesDataList(AddressDataListener<CityInfoBean> mAddressReceiver);
    void getCitiesDataList(AddressDataListener<CityInfoBean> mAddressReceiver, String province);
    void getCountyDataList(AddressDataListener<CityInfoBean> mAddressReceiver, String city, String
            province);
    void getTownDataList(AddressDataListener<String> mAddressReceiver, String county);
    void getSpecialCountyList(AddressDataListener<String> mAddressReceiver, String province);

    interface AddressDataListener<T>{
        void onDataArrived(List<T> datas);
    }
}
