package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by fan on 2016/12/24.
 */

public class BasePlotInfo {
    private String name;
    private List<BaseInfo.StationFactoresBean> list;
    public BasePlotInfo(String name, List<BaseInfo.StationFactoresBean> list) {
        this.name = name;
        this.list = list;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseInfo.StationFactoresBean> getList() {
        return list;
    }

    public void setList(List<BaseInfo.StationFactoresBean> list) {
        this.list = list;
    }
}
