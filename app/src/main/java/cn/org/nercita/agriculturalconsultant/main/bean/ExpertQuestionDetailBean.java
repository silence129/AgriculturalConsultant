package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 专家问题详情bean
 */

public class ExpertQuestionDetailBean {
    /**
     * question : {"id":2,"title":"番茄叶锈病有什么防治方法？","content":"如果大棚里番茄患上了叶锈病，怎么防治呢？","accountname":"小汤山","accountid":1,"createdate":"2016-12-27","aimname":"","aimid":1,"supportcount":0,"commenttime":1,"img":"disease/1427878041727.jpg","stationid":1001,"stationname":"北京小汤山现代农业示范基地","stationcode":"BJCP1001002"}
     * answers : [{"id":2,"reponsecontent":"1、在番茄生长及时中耕除草，平衡水肥，追肥要控制氮肥的施用量，增施磷钾肥。适时通风透光，有利于西红柿生长，提高抗病性。并及时降低田间湿度，提高植株的抗病能力。2、选择地势高、地下水位低，灌、排良好的地块及前茬未种过果菜类的地块作苗床，用葱、蒜地的土作为苗床土尤为理想。3、播种前，种籽用55℃温水浸种15分钟，催芽播种。灌足底水，落水播种，控制苗期灌水。及时间苗，分层上土，多通风透光，加强阴雨天管理，提高幼苗抗性。","expertid":138,"questionid":2,"reponsedate":"2016-01-06","expertname":"李明远","expertpic":"expert/1416448057725.jpg","questiontile":"白粉病","supporttime":"0","stationid":1001,"stationname":"北京小汤山现代农业示范基地","stationcode":"BJCP1001002"},{"id":294,"reponsecontent":"1、秋季翻耕棉田、菜田，冬季或早春灌水，消灭或压低越冬蛹数量。2、成虫发生朋，利用高压汞灯、杨树枝把、蜜源植物诱杀成虫。3、加强田间管理，结合整枝，及时打项，打叉，能有效地减少卵量。同时注意摘除虫果，捕拿幼虫，以压低虫口。4、菜田种植玉米诱集带，能减少棉铃虫在蔬菜上的产卵量。","expertid":68,"questionid":2,"reponsedate":null,"expertname":"顺义丰顺恒示范基地","expertpic":"thumbnail/station/fsh.jpg","questiontile":"","supporttime":"8","stationid":1001,"stationname":"北京小汤山现代农业示范基地","stationcode":"BJCP1001002"},{"id":1149,"reponsecontent":"症状：叶锈病主要发生在叶片，但也能侵害叶鞘，很少发生在茎秆或穗部。发病初期，受害叶片出现圆形或近圆形红褐色的夏孢子堆。夏孢子堆较小，一般在叶片正面不规则散生，极少能穿透叶片，待表皮破裂后，散出黄褐色粉状物。即夏孢子，后期在叶片背面和叶鞘上长出黑色阔椭圆形或长椭圆形、埋于表皮下的冬孢子堆。小麦叶锈病于小麦收获后就转移到自生麦苗上越夏，冬麦播种出土后叶锈菌又从自生麦苗上转移到冬小麦麦苗上。","expertid":67,"questionid":2,"reponsedate":null,"expertname":"熊本海","expertpic":"expert/1416450346634.jpg","questiontile":"","supporttime":"0","stationid":1001,"stationname":"北京小汤山现代农业示范基地","stationcode":"BJCP1001002"}]
     */

    private QuestionBean question;
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
        /**
         * id : 2
         * title : 番茄叶锈病有什么防治方法？
         * content : 如果大棚里番茄患上了叶锈病，怎么防治呢？
         * accountname : 小汤山
         * accountid : 1
         * createdate : 2016-12-27
         * aimname :
         * aimid : 1
         * supportcount : 0
         * commenttime : 1
         * img : disease/1427878041727.jpg
         * stationid : 1001
         * stationname : 北京小汤山现代农业示范基地
         * stationcode : BJCP1001002
         */

        private int id;
        private String title;
        private String content;
        private String accountname;
        private int accountid;
        private String createdate;
        private String aimname;
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

        public String getAimname() {
            return aimname;
        }

        public void setAimname(String aimname) {
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
        /**
         * id : 2
         * reponsecontent : 1、在番茄生长及时中耕除草，平衡水肥，追肥要控制氮肥的施用量，增施磷钾肥。适时通风透光，有利于西红柿生长，提高抗病性。并及时降低田间湿度，提高植株的抗病能力。2、选择地势高、地下水位低，灌、排良好的地块及前茬未种过果菜类的地块作苗床，用葱、蒜地的土作为苗床土尤为理想。3、播种前，种籽用55℃温水浸种15分钟，催芽播种。灌足底水，落水播种，控制苗期灌水。及时间苗，分层上土，多通风透光，加强阴雨天管理，提高幼苗抗性。
         * expertid : 138
         * questionid : 2
         * reponsedate : 2016-01-06
         * expertname : 李明远
         * expertpic : expert/1416448057725.jpg
         * questiontile : 白粉病
         * supporttime : 0
         * stationid : 1001
         * stationname : 北京小汤山现代农业示范基地
         * stationcode : BJCP1001002
         */

        private int id;
        private String reponsecontent;
        private int expertid;
        private int questionid;
        private String reponsedate;
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

        public String getReponsedate() {
            return reponsedate;
        }

        public void setReponsedate(String reponsedate) {
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
