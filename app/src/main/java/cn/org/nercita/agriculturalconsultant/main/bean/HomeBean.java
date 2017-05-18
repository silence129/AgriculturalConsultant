package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/14.
 * 首页数据
 */

public class HomeBean {
    private List<LawsBean> laws;
    private List<PriceBean> price;

    public List<LawsBean> getLaws() {
        return laws;
    }

    public void setLaws(List<LawsBean> laws) {
        this.laws = laws;
    }

    public List<PriceBean> getPrice() {
        return price;
    }

    public void setPrice(List<PriceBean> price) {
        this.price = price;
    }

    public static class LawsBean {
        /**
         * id : 38
         * href : http://192.168.16.23:8888/bthscreen/mobile/laws/detial?id=38
         * time : 2017-04-06
         * title : 上半年主要农作物病虫害发生趋势
         */

        private int id;
        private String href;
        private String time;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class PriceBean {
        /**
         * market : 石家庄桥西蔬菜批发市场
         * priceUnit : 元/公斤
         * price : 3.8
         * id : 119830
         * sort : 蔬菜
         * time : 1490838962000
         * productName : 尖椒
         */

        private String market;
        private String priceUnit;
        private double price;
        private int id;
        private String sort;
        private long time;
        private String productName;

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getPriceUnit() {
            return priceUnit;
        }

        public void setPriceUnit(String priceUnit) {
            this.priceUnit = priceUnit;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
