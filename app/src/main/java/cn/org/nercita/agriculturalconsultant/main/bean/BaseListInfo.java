package cn.org.nercita.agriculturalconsultant.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * author:范博文
 * date:2017/4/21 16:25
 * des:基地信息接口
 * param:null
 * return:null
 */
public class BaseListInfo implements Parcelable {

    /**
     * id : 1071
     * code : HBGC1001003
     * name : 藁城肥畾国农业智能生产信息系统
     * images : http://123.127.160.69:10006/iot/resources/uploads/station/feileiguo.png
     * linkname : 赵伟国
     * tel : 13831118188
     * address : 河北省石家庄市藁城区兴华路西段城子村南1500米路西
     * type : 1
     */

    private List<DataBean> data;

    protected BaseListInfo(Parcel in) {
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<BaseListInfo> CREATOR = new Creator<BaseListInfo>() {
        @Override
        public BaseListInfo createFromParcel(Parcel in) {
            return new BaseListInfo(in);
        }

        @Override
        public BaseListInfo[] newArray(int size) {
            return new BaseListInfo[size];
        }
    };

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(data);
    }

    public static class DataBean implements Parcelable {
        private int id;
        private String code;
        private String name;
        private String images;
        private String linkname;
        private String tel;
        private String address;
        private String type;
        private String bz;

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        protected DataBean(Parcel in) {
            id = in.readInt();
            code = in.readString();
            name = in.readString();
            images = in.readString();
            linkname = in.readString();
            tel = in.readString();
            address = in.readString();
            type = in.readString();
            bz = in.readString();

        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(code);
            parcel.writeString(name);
            parcel.writeString(images);
            parcel.writeString(linkname);
            parcel.writeString(tel);
            parcel.writeString(address);
            parcel.writeString(type);
            parcel.writeString(bz);
        }
    }
}