package cn.org.nercita.agriculturalconsultant.view.address;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.org.nercita.agriculturalconsultant.utils.ProvinceDataHelper;

/**
 * Created by dell on 2016/11/4.
 */

public class AddressDao {
    private static final String TAG = "AddressDao";
    private ArrayList<CityInfoBean> listCounty;


    static Context context;
    List<CityInfoBean> listCity;
    private List<String> listTown = null;


    private static ProvinceDataHelper helper;

    public AddressDao(Context mContext) {
        this.context = mContext;
        helper = new ProvinceDataHelper(mContext);
        listCity = new ArrayList<CityInfoBean>();
        listCounty = new ArrayList<CityInfoBean>();
    }

    /**
    * author:范博文
    * date:2017/4/1 16:51
    * des: 根据省查询数据库 得到省下的市
    * @param province 省名
    * return: 省下的城市集合以及城市代码
    */
    public List<CityInfoBean> getCity(String province) {

        ArrayList<CityInfoBean> cities = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select city,code from xzqhNew WHERE province ='" + province +
                "' " + "and city is not null and town is null", null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex("city"));
                    if (!name.equals("县")) {
                        Log.e("dd", name + "");
                        String code = cur.getString(cur.getColumnIndex("code"));
                        Log.e(TAG, code);
                        CityInfoBean bean = new CityInfoBean(name, code);
                        cities.add(bean);
                    }
//                    ToastUtil.showLong(context, name);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            return cities;
        } else {
            return null;
        }
    }
    /**
     * author:范博文
     * date:2017/4/1 16:51
     * des: 根据省查询数据库 得到全国省
     * return: 省下的城市集合以及城市代码
     */
    public List<CityInfoBean> getProvince() {
        List<CityInfoBean> provinces = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select province,code from xzqhNew WHERE code = parentCode", null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    String code = cur.getString(cur.getColumnIndex("code"));
                    String name = cur.getString(cur.getColumnIndex("province"));
                    if (!code.equals("710000") && !code.equals("810000") && !code.equals
                            ("820000")) {
                        CityInfoBean bean = new CityInfoBean(name, code);
                        provinces.add(bean);
                    }
//                    ToastUtil.showLong(context, name);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            Log.e(TAG, "province.size()" + provinces.size());
            return provinces;
        } else {
            return null;
        }

    }


//    public void closeDatabase() {
//        this.database.close();
////    }

/*    public List<String> getCounty(String city) {
        if (listCounty != null) {
            listCounty.clear();
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select town from xzqhNew WHERE city ='" + city + "' GROUP BY " +
                "town ORDER BY id asc", null);
        if (cur != null) {
//            int NUM_CITY = cur.getCount();
//            ArrayList<AddressDB> taxicity = new ArrayList<AddressDB>(NUM_CITY);
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex("town"));
                    listCounty.add(name);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            return listCounty;
        } else {
            return null;
        }
    }*/
    /**
     * author:范博文
     * date:2017/4/1 16:51
     * des: 根据省查询数据库 得到市下的县集合
     * @param province 省名
     * @param city  省下的市
     * return: 县集合以及城市代码
     */
    public List<CityInfoBean> getCountyByProvinceAndCity(String city, String province) {
        if (listCounty != null) {
            listCounty.clear();
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select town,code from xzqhNew WHERE city ='" + city + "' And " +
                "province = '" + province + "' and town is not null", null);
        if (cur != null) {
//            int NUM_CITY = cur.getCount();
//            ArrayList<AddressDB> taxicity = new ArrayList<AddressDB>(NUM_CITY);
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex("town"));
                    Log.e("DD", name + "");
                    String code = cur.getString(cur.getColumnIndex("code"));
                    Log.e("DD", code);
                    CityInfoBean bean = new CityInfoBean(name, code);
                    listCounty.add(bean);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            return listCounty;
        } else {
            return null;
        }
    }
    /**
    * author:范博文
    * date:2017/3/30 14:13
    * des: 根据定位省市县查询code
    * param: province 省
     * city 市
     * town 县
    * return: code
    */
    public String getCityCode(String province,String city,String town){
        String citycode = null;
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select code from xzqhNew WHERE province ='"+province+"'and city = '"+city+"'and town='"+town+"'",null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                do {
                     citycode = cursor.getString(cursor.getColumnIndex("code"));
                }while (cursor.moveToNext());
            }
            cursor.close();
            readableDatabase.close();
        return citycode;
        }else {
            return null;
        }
    }

    /**
     * 获取直辖市中的县和区
     *
     * @param
     * @return
     */
    public List<String> getDistirctCounty(String province) {
        List<String> countys = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select county from xzqhNew WHERE province ='" + province + "' " +
                "GROUP BY county ORDER BY id asc", null);
        if (cur != null) {
//            int NUM_CITY = cur.getCount();
//            ArrayList<AddressDB> taxicity = new ArrayList<AddressDB>(NUM_CITY);
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex("county"));
                    countys.add(name);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            return countys;
        } else {
            return null;
        }
    }

    /**
     * 获取直辖市中的县和区
     *
     * @param
     * @return
     */
    public List<String> getTowns(String county) {
        List<String> towns = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select town from xzqhNew WHERE county ='" + county + "' ORDER " +
                "BY id asc", null);
        if (cur != null) {
//            int NUM_CITY = cur.getCount();
//            ArrayList<AddressDB> taxicity = new ArrayList<AddressDB>(NUM_CITY);
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex("town"));
                    towns.add(name);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            return towns;
        } else {
            return null;
        }
    }


    //select County from dict_position WHERE City='市辖区' and Province='北京市' GROUP BY County ORDER
    // BY id asc
   /* //获取市辖区的区
    public List<String> getDistrict(String city, String province) {
        listCounty.clear();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = db.rawQuery("select town from xzqhNew WHERE City ='" + city + "' AND " +
                "Province='" + province + "' GROUP BY town ORDER BY id asc", null);
        if (cur != null) {
//            int NUM_CITY = cur.getCount();
//            ArrayList<AddressDB> taxicity = new ArrayList<AddressDB>(NUM_CITY);
            if (cur.moveToFirst()) {
                do {
                    String name = cur.getString(cur.getColumnIndex("town"));
                    listCounty.add(name);
                } while (cur.moveToNext());
            }
            cur.close();
            db.close();
            return listCounty;
        } else {
            return null;
        }
    }*/
}
