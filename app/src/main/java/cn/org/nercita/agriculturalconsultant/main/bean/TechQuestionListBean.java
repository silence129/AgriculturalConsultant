package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/11.
 * 农技问题列表数据
 */

public class TechQuestionListBean {
    /**
     * Page : {"hasPrePage":false,"hasNextPage":true,"everyPage":15,"totalPage":12,"currentPage":1,"beginIndex":0,"totalCount":169,"data":null,"datas":null}
     * list : [{"id":413,"accountId":384,"accountName":"","title":"叶片出现白点怎么办","content":"叶片出现白点怎么办","createTime":1491379968000,"pics":"/resources/uploads/tech_question/201704/20170405161248_333.jpg","bthTypeId":45,"keyWords":"","bthTypeName":""},{"id":412,"accountId":347,"accountName":null,"title":"玉米的最佳收获期是什么时候","content":"玉米的最佳收获期是什么时候","createTime":1490828952000,"pics":"","bthTypeId":32,"keyWords":null,"bthTypeName":""},{"id":411,"accountId":347,"accountName":null,"title":"玉米最佳收获时期是什么时候?","content":"玉米最佳收获时期是什么时候?","createTime":1490828817000,"pics":"/resources/uploads/tech_question/201703/20170330070657_59.jpg","bthTypeId":32,"keyWords":null,"bthTypeName":""},{"id":410,"accountId":313,"accountName":null,"title":"初生小牛拉稀怎么办？","content":"初生小牛拉稀怎么办？","createTime":1490777098000,"pics":"","bthTypeId":7,"keyWords":null,"bthTypeName":""},{"id":409,"accountId":313,"accountName":null,"title":"耳朵发紫，躯干也有，采食正常，一栏17头，其他的都正常，发病两天啦，请问该怎么治疗啊？","content":"耳朵发紫，躯干也有，采食正常，一栏17头，其他的都正常，发病两天啦，请问该怎么治疗啊？","createTime":1490770639000,"pics":"","bthTypeId":8,"keyWords":null,"bthTypeName":""},{"id":408,"accountId":313,"accountName":null,"title":"西红柿果实长到乒乓球大小时很容易从脐部裂开，果肉和种子外露，整个果实很硬，请问这是什么原因造成的，如何解决？","content":"西红柿果实长到乒乓球大小时很容易从脐部裂开，果肉和种子外露，整个果实很硬，请问这是什么原因造成的，如何解决？","createTime":1490770427000,"pics":"","bthTypeId":59,"keyWords":null,"bthTypeName":""},{"id":407,"accountId":313,"accountName":null,"title":"石灰质肥料是什么，怎么施肥？\n您好！我家是种植吊瓜的 最近田里的吊瓜出现了一种病毒 在网上收索了下感觉和黄瓜褐脉叶的症状很像！看到这个病是土壤钾偏高的原因 需要石灰质肥料中和 麻烦问一下什么是石灰质肥料 可以喷洒什么农药治疗？谢谢！","content":"石灰质肥料是什么，怎么施肥？\n您好！我家是种植吊瓜的 最近田里的吊瓜出现了一种病毒 在网上收索了下感觉和黄瓜褐脉叶的症状很像！看到这个病是土壤钾偏高的原因 需要石灰质肥料中和 麻烦问一下什么是石灰质肥料 可以喷洒什么农药治疗？谢谢！","createTime":1490769186000,"pics":"","bthTypeId":6,"keyWords":null,"bthTypeName":""},{"id":406,"accountId":347,"accountName":null,"title":"水稻稻瘟病的防治方法","content":"水稻稻瘟病的防治方法","createTime":1490669420000,"pics":"","bthTypeId":30,"keyWords":null,"bthTypeName":"其他类水产养殖"},{"id":405,"accountId":347,"accountName":null,"title":"水稻稻瘟病的防治方法","content":"水稻稻瘟病的防治方法","createTime":1490669366000,"pics":"","bthTypeId":30,"keyWords":null,"bthTypeName":"其他类水产养殖"},{"id":404,"accountId":313,"accountName":null,"title":"你好，请问奶牛养殖应该注意些什么","content":"你好，请问奶牛养殖应该注意些什么","createTime":1490616850000,"pics":"/resources/uploads/tech_question/201703/20170327201410_893.png","bthTypeId":7,"keyWords":null,"bthTypeName":""},{"id":403,"accountId":313,"accountName":null,"title":"你好，请问这是什么病？能帮我看一下吗","content":"你好，请问这是什么病？能帮我看一下吗","createTime":1490616763000,"pics":"/resources/uploads/tech_question/201703/20170327201243_243.jpg","bthTypeId":6,"keyWords":null,"bthTypeName":""},{"id":402,"accountId":313,"accountName":null,"title":"请问养牛用秸秆可行吗？","content":"请问养牛用秸秆可行吗？","createTime":1490612544000,"pics":"/resources/uploads/tech_question/201703/20170327190224_843.jpg","bthTypeId":7,"keyWords":null,"bthTypeName":""},{"id":401,"accountId":313,"accountName":null,"title":"养殖羊需要注意什么？","content":"养殖羊需要注意什么？","createTime":1490612459000,"pics":"/resources/uploads/tech_question/201703/20170327190059_318.jpeg","bthTypeId":7,"keyWords":null,"bthTypeName":""},{"id":400,"accountId":313,"accountName":null,"title":"养殖肉牛需要注意什么？","content":"养殖肉牛需要注意什么？","createTime":1490612356000,"pics":"/resources/uploads/tech_question/201703/20170327185916_741.jpg","bthTypeId":7,"keyWords":null,"bthTypeName":""},{"id":399,"accountId":313,"accountName":null,"title":"奶牛春季要注意什么？","content":"奶牛春季要注意什么？","createTime":1490608022000,"pics":"","bthTypeId":7,"keyWords":null,"bthTypeName":""}]
     */

    private PageBean Page;
    private List<ListBean> list;

    public PageBean getPage() {
        return Page;
    }

    public void setPage(PageBean Page) {
        this.Page = Page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class PageBean {
        /**
         * hasPrePage : false
         * hasNextPage : true
         * everyPage : 15
         * totalPage : 12
         * currentPage : 1
         * beginIndex : 0
         * totalCount : 169
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

    public static class ListBean {
        /**
         * id : 413
         * accountId : 384
         * accountName :
         * title : 叶片出现白点怎么办
         * content : 叶片出现白点怎么办
         * createTime : 1491379968000
         * pics : /resources/uploads/tech_question/201704/20170405161248_333.jpg
         * bthTypeId : 45
         * keyWords :
         * bthTypeName :
         */

        private int id;
        private int accountId;
        private String accountName;
        private String title;
        private String content;
        private long createTime;
        private String pics;
        private int bthTypeId;
        private String keyWords;
        private String bthTypeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public int getBthTypeId() {
            return bthTypeId;
        }

        public void setBthTypeId(int bthTypeId) {
            this.bthTypeId = bthTypeId;
        }

        public String getKeyWords() {
            return keyWords;
        }

        public void setKeyWords(String keyWords) {
            this.keyWords = keyWords;
        }

        public String getBthTypeName() {
            return bthTypeName;
        }

        public void setBthTypeName(String bthTypeName) {
            this.bthTypeName = bthTypeName;
        }
    }
}
