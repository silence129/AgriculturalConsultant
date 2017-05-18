package cn.org.nercita.agriculturalconsultant.view.address;

import android.content.Context;

import java.util.List;

/**
 * Created by cuiyonghong on 16/11/8.
 */

public class AddressDataEngine implements AddressDataInterface {

    private static AddressDataEngine engine;
    private AddressDao dataDao;

    private AddressDataEngine(Context context) {
        dataDao = new AddressDao(context);
    }

    public static AddressDataEngine getInstance(Context context) {
        synchronized (AddressDataEngine.class) {
            if (engine == null) {
                engine = new AddressDataEngine(context);
                return engine;
            }else {
                return engine;
            }
        }
    }

    @Override
    public void getProvincesDataList(AddressDataListener<CityInfoBean> mAddressReceiver) {
        List<CityInfoBean> province = dataDao.getProvince();
        mAddressReceiver.onDataArrived(province);
    }

    @Override
    public void getCitiesDataList(AddressDataListener<CityInfoBean> mAddressReceiver, String province) {
        List<CityInfoBean> city = dataDao.getCity(province);
        mAddressReceiver.onDataArrived(city);
    }

    @Override
    public void getCountyDataList(AddressDataListener<CityInfoBean> mAddressReceiver, String city,String province) {
        List<CityInfoBean> county = dataDao.getCountyByProvinceAndCity(city,province);
        mAddressReceiver.onDataArrived(county);
    }

    @Override
    public void getTownDataList(AddressDataListener<String> mAddressReceiver, String county) {
        List<String> towns = dataDao.getTowns(county);
        mAddressReceiver.onDataArrived(towns);
    }

    @Override
    public void getSpecialCountyList(AddressDataListener<String> mAddressReceiver, String province) {
        List<String> distirctCounty = dataDao.getDistirctCounty(province);
        mAddressReceiver.onDataArrived(distirctCounty);
    }


}
