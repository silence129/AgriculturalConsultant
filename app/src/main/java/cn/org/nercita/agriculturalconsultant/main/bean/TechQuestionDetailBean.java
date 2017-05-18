package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 农技问答详情Bean
 */

public class TechQuestionDetailBean {

    /**
     * questionId : 412
     * question : {"id":412,"accountId":347,"accountName":"","title":"玉米的最佳收获期是什么时候","content":"玉米的最佳收获期是什么时候","createTime":1490828952000,"pics":"","bthTypeId":32,"keyWords":"","bthTypeName":""}
     * techReponselist : [{"id":525,"questionId":412,"content":"那得看你什么时候种的了？！或是你一年和几季！","createTime":1491388143000,"pics":null,"accountId":313,"accountName":"","parentId":0,"pointPraise":1},{"id":524,"questionId":412,"content":"图片看不到？","createTime":1491388101000,"pics":null,"accountId":313,"accountName":null,"parentId":0,"pointPraise":0}]
     * Page : {"hasPrePage":false,"hasNextPage":false,"everyPage":15,"totalPage":1,"currentPage":1,"beginIndex":0,"totalCount":2,"data":null,"datas":null}
     * message : 详情查询成功
     * status : 200
     */

    private int questionId;
    private QuestionBean question;
    private PageBean Page;
    private String message;
    private int status;
    private List<TechReponselistBean> techReponselist;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public QuestionBean getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }

    public PageBean getPage() {
        return Page;
    }

    public void setPage(PageBean Page) {
        this.Page = Page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TechReponselistBean> getTechReponselist() {
        return techReponselist;
    }

    public void setTechReponselist(List<TechReponselistBean> techReponselist) {
        this.techReponselist = techReponselist;
    }

    public static class QuestionBean {
        /**
         * id : 412
         * accountId : 347
         * accountName :
         * title : 玉米的最佳收获期是什么时候
         * content : 玉米的最佳收获期是什么时候
         * createTime : 1490828952000
         * pics :
         * bthTypeId : 32
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

    public static class PageBean {
        /**
         * hasPrePage : false
         * hasNextPage : false
         * everyPage : 15
         * totalPage : 1
         * currentPage : 1
         * beginIndex : 0
         * totalCount : 2
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

    public static class TechReponselistBean {
        /**
         * id : 525
         * questionId : 412
         * content : 那得看你什么时候种的了？！或是你一年和几季！
         * createTime : 1491388143000
         * pics : null
         * accountId : 313
         * accountName :
         * parentId : 0
         * pointPraise : 1
         */

        private int id;
        private int questionId;
        private String content;
        private long createTime;
        private Object pics;
        private int accountId;
        private String accountName;
        private int parentId;
        private int pointPraise;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
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

        public Object getPics() {
            return pics;
        }

        public void setPics(Object pics) {
            this.pics = pics;
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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getPointPraise() {
            return pointPraise;
        }

        public void setPointPraise(int pointPraise) {
            this.pointPraise = pointPraise;
        }
    }
}
