package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/13.
 * 最新价格列表数据
 */

public class NewPriceBean {
    /**
     * page : {"hasPrePage":false,"hasNextPage":true,"everyPage":15,"totalPage":7253,"currentPage":1,"beginIndex":0,"totalCount":108794,"data":null,"datas":null}
     * content : [{"id":119830,"province":"河北省","sort":"蔬菜","productName":"尖椒","price":3.8,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"jianjiao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1386,"dayTrend":"","dayChange":"","dayReal":""},{"id":119831,"province":"河北省","sort":"蔬菜","productName":"尖椒","price":3,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"jianjiao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1624,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119832,"province":"河北省","sort":"蔬菜","productName":"尖椒","price":4.6,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"jianjiao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1639,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119833,"province":"河北省","sort":"蔬菜","productName":"青椒","price":3.2,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"qingjiao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1647,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119834,"province":"河北省","sort":"蔬菜","productName":"青椒","price":2.4,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"qingjiao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1403,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119835,"province":"河北省","sort":"蔬菜","productName":"青椒","price":4,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"qingjiao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1345,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119836,"province":"河北省","sort":"蔬菜","productName":"菜花","price":1.8,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"caihua.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1550,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119837,"province":"河北省","sort":"蔬菜","productName":"菜花","price":1.4,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"caihua.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1665,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119838,"province":"河北省","sort":"蔬菜","productName":"菜花","price":2.2,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"caihua.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1347,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119839,"province":"河北省","sort":"蔬菜","productName":"生姜","price":4.8,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"shengjiang.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1536,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119840,"province":"河北省","sort":"蔬菜","productName":"生姜","price":4,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"shengjiang.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1543,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119841,"province":"河北省","sort":"蔬菜","productName":"生姜","price":5.6,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"shengjiang.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1536,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119842,"province":"河北省","sort":"蔬菜","productName":"茼蒿","price":3.2,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"tonghao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1534,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119843,"province":"河北省","sort":"蔬菜","productName":"茼蒿","price":2,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"tonghao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1559,"dayTrend":null,"dayChange":null,"dayReal":null},{"id":119844,"province":"河北省","sort":"蔬菜","productName":"茼蒿","price":4.4,"priceUnit":"元/公斤","issuanceTime":1490838962000,"getTime":1490838962000,"picname":"tonghao.jpg","marketName":"石家庄桥西蔬菜批发市场","marketId":1559,"dayTrend":null,"dayChange":null,"dayReal":null}]
     */

    private PageBean page;
    private List<ContentBean> content;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class PageBean {
        /**
         * hasPrePage : false
         * hasNextPage : true
         * everyPage : 15
         * totalPage : 7253
         * currentPage : 1
         * beginIndex : 0
         * totalCount : 108794
         * data : null
         * datas : null
         */

        private boolean hasPrePage;
        private boolean hasNextPage;
        private int everyPage;
        private int totalPage;
        private int currentPage;
        private int beginIndex;
        private int totalCount;
        private Object data;
        private Object datas;

        public boolean isHasPrePage() {
            return hasPrePage;
        }

        public void setHasPrePage(boolean hasPrePage) {
            this.hasPrePage = hasPrePage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getEveryPage() {
            return everyPage;
        }

        public void setEveryPage(int everyPage) {
            this.everyPage = everyPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getBeginIndex() {
            return beginIndex;
        }

        public void setBeginIndex(int beginIndex) {
            this.beginIndex = beginIndex;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getDatas() {
            return datas;
        }

        public void setDatas(Object datas) {
            this.datas = datas;
        }
    }

    public static class ContentBean {
        /**
         * id : 119830
         * province : 河北省
         * sort : 蔬菜
         * productName : 尖椒
         * price : 3.8
         * priceUnit : 元/公斤
         * issuanceTime : 1490838962000
         * getTime : 1490838962000
         * picname : jianjiao.jpg
         * marketName : 石家庄桥西蔬菜批发市场
         * marketId : 1386
         * dayTrend :
         * dayChange :
         * dayReal :
         */

        private int id;
        private String province;
        private String sort;
        private String productName;
        private double price;
        private String priceUnit;
        private long issuanceTime;
        private long getTime;
        private String picname;
        private String marketName;
        private int marketId;
        private String dayTrend;
        private String dayChange;
        private String dayReal;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPriceUnit() {
            return priceUnit;
        }

        public void setPriceUnit(String priceUnit) {
            this.priceUnit = priceUnit;
        }

        public long getIssuanceTime() {
            return issuanceTime;
        }

        public void setIssuanceTime(long issuanceTime) {
            this.issuanceTime = issuanceTime;
        }

        public long getGetTime() {
            return getTime;
        }

        public void setGetTime(long getTime) {
            this.getTime = getTime;
        }

        public String getPicname() {
            return picname;
        }

        public void setPicname(String picname) {
            this.picname = picname;
        }

        public String getMarketName() {
            return marketName;
        }

        public void setMarketName(String marketName) {
            this.marketName = marketName;
        }

        public int getMarketId() {
            return marketId;
        }

        public void setMarketId(int marketId) {
            this.marketId = marketId;
        }

        public String getDayTrend() {
            return dayTrend;
        }

        public void setDayTrend(String dayTrend) {
            this.dayTrend = dayTrend;
        }

        public String getDayChange() {
            return dayChange;
        }

        public void setDayChange(String dayChange) {
            this.dayChange = dayChange;
        }

        public String getDayReal() {
            return dayReal;
        }

        public void setDayReal(String dayReal) {
            this.dayReal = dayReal;
        }
    }
}
