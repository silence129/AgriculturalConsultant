package cn.org.nercita.agriculturalconsultant.main.service.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/13.
 * 服务首页数据
 */

public class ServiceHomeBean {

    private List<MarkNewBean> markNew;
    private List<DisplayBalanceBean> displayBalance;
    private List<FeatureBean> feature;

    public List<MarkNewBean> getMarkNew() {
        return markNew;
    }

    public void setMarkNew(List<MarkNewBean> markNew) {
        this.markNew = markNew;
    }

    public List<DisplayBalanceBean> getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(List<DisplayBalanceBean> displayBalance) {
        this.displayBalance = displayBalance;
    }

    public List<FeatureBean> getFeature() {
        return feature;
    }

    public void setFeature(List<FeatureBean> feature) {
        this.feature = feature;
    }

    public static class MarkNewBean {
        /**
         * id : 1403
         * title : 河北石家庄鸡蛋价格弱势企稳
         * content : “十一黄金周”过后，鸡蛋价格一直持续下降，从5元时代降至3元时代。不过，近段时间以来，鸡蛋价格终于企稳。近日，记者走访省会石家庄部分农贸市场及超市发现，每斤鸡蛋的零售价格多维持在3.5元-3.8元/斤。
         * createDate : 1463385025000
         * originalUrl : http://www.agronet.com.cn/News/1092670.html
         * imgSrc : uploads/trading/jidan.jpg
         * oldid : 323869
         */

        private int id;
        private String title;
        private String content;
        private long createDate;
        private String originalUrl;
        private String imgSrc;
        private int oldid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public String getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public int getOldid() {
            return oldid;
        }

        public void setOldid(int oldid) {
            this.oldid = oldid;
        }
    }

    public static class DisplayBalanceBean {
        /**
         * id : 36094
         * province : 河北省
         * title : 供应水产养殖专用黄腐酸
         * content : 万国黄腐酸中的多菌种微生物营养成分能够有效繁殖水分益生菌、促进水体“自肥”，分解有机质，补出水分中的有益微生物菌体，转化积累水中生物能量，减少水体污染。万国黄腐酸铜、黄腐酸钠、黄腐酸钾还能够有效络合水分中的重金属有害物质，消除有害微生物大量繁殖的空间，建立养殖水体内生态良性循环机制。此外，万国水产养殖专用黄腐酸盐还可以有效改良养殖动物消化和免疫系统，提高饲料转化率，减少粪便排除，增强养殖体免疫功能，保苗促长的作用。
         * cropType : null
         * icon : http://img4.agronet.com.cn/Users/100/151/352/201110201514283548.jpg
         * type : 1
         * value : 12吨
         * qty : null
         * price : null
         * tel : null
         * address : 河北石家庄市
         * unit : null
         * contact : null
         * demandID : 453390
         * createTime : 1482213349000
         * url : http://www.agronet.com.cn/Sell/1709301.html
         * totClass : 水产品
         * categories : 白鱼
         * isSubmit : 0
         * secClass : 水产
         */

        private int id;
        private String province;
        private String title;
        private String content;
        private Object cropType;
        private String icon;
        private int type;
        private String value;
        private Object qty;
        private Object price;
        private Object tel;
        private String address;
        private Object unit;
        private Object contact;
        private int demandID;
        private long createTime;
        private String url;
        private String totClass;
        private String categories;
        private String isSubmit;
        private String secClass;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getCropType() {
            return cropType;
        }

        public void setCropType(Object cropType) {
            this.cropType = cropType;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getQty() {
            return qty;
        }

        public void setQty(Object qty) {
            this.qty = qty;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public Object getContact() {
            return contact;
        }

        public void setContact(Object contact) {
            this.contact = contact;
        }

        public int getDemandID() {
            return demandID;
        }

        public void setDemandID(int demandID) {
            this.demandID = demandID;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTotClass() {
            return totClass;
        }

        public void setTotClass(String totClass) {
            this.totClass = totClass;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getIsSubmit() {
            return isSubmit;
        }

        public void setIsSubmit(String isSubmit) {
            this.isSubmit = isSubmit;
        }

        public String getSecClass() {
            return secClass;
        }

        public void setSecClass(String secClass) {
            this.secClass = secClass;
        }
    }

    public static class FeatureBean {
        /**
         * id : 9
         * title : 常安红梨
         * content : 常安镇黄家庄村生态红梨采摘园位于藁城市东南部15公里处，红梨面积近万亩，常安镇梨树种植面积3万多亩，是中国最大的“红梨之乡”。红梨品种有满天红、美人酥、红蜜、红冠王等，2002年该红梨系列品种参加了“中国烟台第四届国际果蔬博览会展销”，被香港业内人士称为“梨中之王”。2004年，红梨生态园通过了无公害基地认证，今年1月，“常安红”系列红梨又通过了农业部绿色果品认证。
         * pic : uploads/produce/cahl.png
         * unit : 河北省石家庄市藁城区增村镇冯辛庄
         * price : 10
         * priceUnit : 元/千克
         * buyUrl : https://item.taobao.com/item.htm?spm=a230r.1.14.16.o0aunO&id=525816703878&ns=1&abbucket=15#detail
         * province : 河北省
         * createTime : 1482132676000
         */

        private int id;
        private String title;
        private String content;
        private String pic;
        private String unit;
        private int price;
        private String priceUnit;
        private String buyUrl;
        private String province;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getPriceUnit() {
            return priceUnit;
        }

        public void setPriceUnit(String priceUnit) {
            this.priceUnit = priceUnit;
        }

        public String getBuyUrl() {
            return buyUrl;
        }

        public void setBuyUrl(String buyUrl) {
            this.buyUrl = buyUrl;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
