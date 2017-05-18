package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by fan on 2017/4/11.
 */

public class ExpertAnswerDesBen {

    /**
     * id : 36
     * title : 草莓根蚜了怎么办？
     * content : 草莓植株生长不良，叶片稀疏，皱缩卷曲变形。
     * accountname : 丰顺恒
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

    private QuestionBean question;
    /**
     * id : 599
     * reponsecontent : 1、建立无病留种田，选用适宜当地栽培的耐病品种。2、收获后注意清除病残体，集中深埋或烧毁，不要在田边堆放病残体。3
     * 、采用配方施肥技术，施用酵素菌沤制的堆肥，避免偏施过施氮肥，在坐瓜前开始喷施惠满丰液肥，每667m2320毫升，对水500倍，提高抗病性。4
     * 、科学地确定播种期，露地宜在日均温稳定在15℃以上，5厘米深处土温稳定在12℃以上时播种，即桃始花，种西瓜。
     * expertid : 267
     * questionid : 36
     * reponsedate : null
     * expertname : 顺义丰顺恒示范基地
     * expertpic : thumbnail/station/fsh.jpg
     * questiontile :
     * supporttime : 3
     * stationid : 1001
     * stationname : 北京小汤山现代农业示范基地
     * stationcode : BJCP1001002
     */

    private List<AnswersBean> answers;

    public QuestionBean getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class QuestionBean {
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

    public static class AnswersBean {
        private int id;
        private String reponsecontent;
        private int expertid;
        private int questionid;
        private Object reponsedate;
        private String expertname;
        private String expertpic;
        private String questiontile;
        private String supporttime;
        private int stationid;
        private String stationname;
        private String stationcode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReponsecontent() {
            return reponsecontent;
        }

        public void setReponsecontent(String reponsecontent) {
            this.reponsecontent = reponsecontent;
        }

        public int getExpertid() {
            return expertid;
        }

        public void setExpertid(int expertid) {
            this.expertid = expertid;
        }

        public int getQuestionid() {
            return questionid;
        }

        public void setQuestionid(int questionid) {
            this.questionid = questionid;
        }

        public Object getReponsedate() {
            return reponsedate;
        }

        public void setReponsedate(Object reponsedate) {
            this.reponsedate = reponsedate;
        }

        public String getExpertname() {
            return expertname;
        }

        public void setExpertname(String expertname) {
            this.expertname = expertname;
        }

        public String getExpertpic() {
            return expertpic;
        }

        public void setExpertpic(String expertpic) {
            this.expertpic = expertpic;
        }

        public String getQuestiontile() {
            return questiontile;
        }

        public void setQuestiontile(String questiontile) {
            this.questiontile = questiontile;
        }

        public String getSupporttime() {
            return supporttime;
        }

        public void setSupporttime(String supporttime) {
            this.supporttime = supporttime;
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
