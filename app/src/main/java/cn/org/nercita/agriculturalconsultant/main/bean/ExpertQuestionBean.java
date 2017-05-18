package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by fan on 2017/4/11.
 */

public class ExpertQuestionBean {

    /**
     * hasPrePage : true
     * hasNextPage : false
     * everyPage : 15
     * totalPage : 2
     * currentPage : 2
     * beginIndex : 15
     * totalCount : 26
     * data : null
     * datas : null
     */

    private PageBean page;
    /**
     * id : 35
     * title : 番茄叶锈病
     * content : 番茄叶锈病的处理方法？
     * accountname : 小汤山
     * accountid : 1
     * createdate : 1482829027000
     * aimname : null
     * aimid : 1
     * supportcount : 0
     * commenttime : 0
     * img : disease/1427879213671.jpg
     * stationid : 1001
     * stationname : 北京小汤山现代农业示范基地
     * stationcode : BJCP1001002
     */

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
        private int id;
        private String title;
        private String content;
        private String accountname;
        private int accountid;
        private String createdate;
        private Object aimname;
        private int aimid;
        private int supportcount;
        private int commenttime;
        private String img;
        private int stationid;
        private String stationname;
        private String stationcode;

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

        public String getAccountname() {
            return accountname;
        }

        public void setAccountname(String accountname) {
            this.accountname = accountname;
        }

        public int getAccountid() {
            return accountid;
        }

        public void setAccountid(int accountid) {
            this.accountid = accountid;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public Object getAimname() {
            return aimname;
        }

        public void setAimname(Object aimname) {
            this.aimname = aimname;
        }

        public int getAimid() {
            return aimid;
        }

        public void setAimid(int aimid) {
            this.aimid = aimid;
        }

        public int getSupportcount() {
            return supportcount;
        }

        public void setSupportcount(int supportcount) {
            this.supportcount = supportcount;
        }

        public int getCommenttime() {
            return commenttime;
        }

        public void setCommenttime(int commenttime) {
            this.commenttime = commenttime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getStationid() {
            return stationid;
        }

        public void setStationid(int stationid) {
            this.stationid = stationid;
        }

        public String getStationname() {
            return stationname;
        }

        public void setStationname(String stationname) {
            this.stationname = stationname;
        }

        public String getStationcode() {
            return stationcode;
        }

        public void setStationcode(String stationcode) {
            this.stationcode = stationcode;
        }
    }
}
