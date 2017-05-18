package cn.org.nercita.agriculturalconsultant.view.address;

/**
 * Created by fan on 2017/3/29.
 */

public class CityInfoBean {
    public String name;
    public String code;

    public CityInfoBean(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
