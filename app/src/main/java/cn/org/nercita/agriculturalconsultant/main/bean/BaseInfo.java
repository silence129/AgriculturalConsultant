package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by fan on 2016/12/23.
 */

public class BaseInfo {

    /**
     * code : HBGC1001002W001
     * name : A地块#1
     * displayName : 河北藁城金喜种植合作社-A地块#1
     * ipcameralist : [{"userName":"admin","password":"admin12345","address":"159h84v982.imwork
     * .net","httpPort":8011,"sdkPort":8014,"rtspPort":8012}]
     * stationFactores : [{"displayName":"开关量设备","factorId":"SWITCH","factorName":"开关量设备",
     * "dataType":"In","lastValue":"9286217","isValid":false,"date":1480778249000},
     * {"displayName":"空气温度","factorId":"ATC","factorName":"空气温度","dataType":"Text",
     * "lastValue":"0.0","isValid":false,"date":1480778249000},{"displayName":"空气湿度",
     * "factorId":"AHC","factorName":"空气湿度","dataType":"Text","lastValue":"0.0","isValid":false,
     * "date":1480778249000},{"displayName":"土壤温度","factorId":"STC","factorName":"土壤温度",
     * "dataType":"Text","lastValue":"0.0","isValid":false,"date":1480778249000},
     * {"displayName":"土壤含水量","factorId":"SWC","factorName":"含水量","dataType":"Text",
     * "lastValue":"0.0","isValid":false,"date":1480778249000},{"displayName":"光照强度",
     * "factorId":"LIGHT","factorName":"光照强度","dataType":"Text","lastValue":"0.0",
     * "isValid":false,"date":1480778249000},{"displayName":"二氧化碳浓度","factorId":"CO2",
     * "factorName":"二氧化碳1","dataType":"Text","lastValue":"2069.0","isValid":false,
     * "date":1480778249000},{"displayName":"高清视频","factorId":"STREAM","factorName":"视频流",
     * "dataType":"Stream,File","lastValue":null,"isValid":true,"date":null}]
     */

    private String code;
    private String name;
    private String displayName;
    /**
     * userName : admin
     * password : admin12345
     * address : 159h84v982.imwork.net
     * httpPort : 8011
     * sdkPort : 8014
     * rtspPort : 8012
     */

    private List<IpcameralistBean> ipcameralist;
    /**
     * displayName : 开关量设备
     * factorId : SWITCH
     * factorName : 开关量设备
     * dataType : In
     * lastValue : 9286217
     * isValid : false
     * date : 1480778249000
     */

    private List<StationFactoresBean> stationFactores;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<IpcameralistBean> getIpcameralist() {
        return ipcameralist;
    }

    public void setIpcameralist(List<IpcameralistBean> ipcameralist) {
        this.ipcameralist = ipcameralist;
    }

    public List<StationFactoresBean> getStationFactores() {
        return stationFactores;
    }

    public void setStationFactores(List<StationFactoresBean> stationFactores) {
        this.stationFactores = stationFactores;
    }

    public static class IpcameralistBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String userName;
        private String password;
        private String address;
        private boolean isLogin;

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }

        private int httpPort;
        private int sdkPort;
        private int rtspPort;
        private int accountId;
        private int channel;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getHttpPort() {
            return httpPort;
        }

        public void setHttpPort(int httpPort) {
            this.httpPort = httpPort;
        }

        public int getSdkPort() {
            return sdkPort;
        }

        public void setSdkPort(int sdkPort) {
            this.sdkPort = sdkPort;
        }

        public int getRtspPort() {
            return rtspPort;
        }

        public void setRtspPort(int rtspPort) {
            this.rtspPort = rtspPort;
        }
    }

    public static class StationFactoresBean {
        private String displayName;
        private String factorId;
        private String factorName;
        private String dataType;
        private String lastValue;
        private boolean isValid;
        private long date;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getFactorId() {
            return factorId;
        }

        public void setFactorId(String factorId) {
            this.factorId = factorId;
        }

        public String getFactorName() {
            return factorName;
        }

        public void setFactorName(String factorName) {
            this.factorName = factorName;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getLastValue() {
            return lastValue;
        }

        public void setLastValue(String lastValue) {
            this.lastValue = lastValue;
        }

        public boolean isIsValid() {
            return isValid;
        }

        public void setIsValid(boolean isValid) {
            this.isValid = isValid;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }
    }
}
