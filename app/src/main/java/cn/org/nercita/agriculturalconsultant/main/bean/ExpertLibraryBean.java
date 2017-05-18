package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by fan on 2017/4/14.
 */

public class ExpertLibraryBean {

    /**
     * item : [{"id":441,"stationid":null,"orgid":null,"name":"王志辉","job":null,"degree":null,
     * "major":"","address":null,"email":null,"company":null,"experience":null,
     * "phonenumber":null,"birthday":null,"age":null,"description":null,
     * "photo":"expert/1492155798907.jpg","school":null,"note":null,"belief":null,
     * "reviewcount":null,"commentcount":null,"replycount":null,"province":null,"city":null,
     * "level":null,"majortype":null,"type":0,"revertcount":null,"villagecount":null,
     * "createdate":null,"visitscount":null,"direction":null,"achievement":null,
     * "researchproject":null,"thesis":null,"longitude":"","latitude":"","status":"1",
     * "ser_othername":null,"ser_position":null,"ser_worktime":null,"ser_behere":null,
     * "ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":442,
     * "stationid":null,"orgid":null,"name":"马吉利","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492156116616.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":443,
     * "stationid":null,"orgid":null,"name":"张胜爱","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492156248963.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":444,
     * "stationid":null,"orgid":null,"name":"何建兴","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492156351685.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":445,
     * "stationid":null,"orgid":null,"name":"李艳芬","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492156430237.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":446,
     * "stationid":null,"orgid":null,"name":"杜金钟","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492156663505.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":447,
     * "stationid":null,"orgid":null,"name":"孙美然","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492156664001.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":448,
     * "stationid":null,"orgid":null,"name":"崔爱珍","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492157309128.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":449,
     * "stationid":null,"orgid":null,"name":"李建波","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492157376757.JPG",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null},{"id":450,
     * "stationid":null,"orgid":null,"name":"赵志兰","job":null,"degree":null,"major":"",
     * "address":null,"email":null,"company":null,"experience":null,"phonenumber":null,
     * "birthday":null,"age":null,"description":null,"photo":"expert/1492157476089.jpg",
     * "school":null,"note":null,"belief":null,"reviewcount":null,"commentcount":null,
     * "replycount":null,"province":null,"city":null,"level":null,"majortype":null,"type":0,
     * "revertcount":null,"villagecount":null,"createdate":null,"visitscount":null,
     * "direction":null,"achievement":null,"researchproject":null,"thesis":null,"longitude":"",
     * "latitude":"","status":"0","ser_othername":null,"ser_position":null,"ser_worktime":null,
     * "ser_behere":null,"ser_unity":null,"ser_depart":null,"ser_gradu":null,"ser_amaku":null,
     * "ser_amaku_place":null,"ser_amaku_time":null,"ser_duty":null,"ser_duty_time":null,
     * "ser_dhun":null,"ser_dhun_time":null,"ser_feat":null,"ser_ser":null,"ser_sce":null,
     * "ser_man":null,"ser_pus":null,"ser_major":null,"atts":null,"bthtypeid":null}]
     * totalSize : 44
     */

    private int totalSize;
    /**
     * id : 441
     * stationid : null
     * orgid : null
     * name : 王志辉
     * job : null
     * degree : null
     * major :
     * address : null
     * email : null
     * company : null
     * experience : null
     * phonenumber : null
     * birthday : null
     * age : null
     * description : null
     * photo : expert/1492155798907.jpg
     * school : null
     * note : null
     * belief : null
     * reviewcount : null
     * commentcount : null
     * replycount : null
     * province : null
     * city : null
     * level : null
     * majortype : null
     * type : 0
     * revertcount : null
     * villagecount : null
     * createdate : null
     * visitscount : null
     * direction : null
     * achievement : null
     * researchproject : null
     * thesis : null
     * longitude :
     * latitude :
     * status : 1
     * ser_othername : null
     * ser_position : null
     * ser_worktime : null
     * ser_behere : null
     * ser_unity : null
     * ser_depart : null
     * ser_gradu : null
     * ser_amaku : null
     * ser_amaku_place : null
     * ser_amaku_time : null
     * ser_duty : null
     * ser_duty_time : null
     * ser_dhun : null
     * ser_dhun_time : null
     * ser_feat : null
     * ser_ser : null
     * ser_sce : null
     * ser_man : null
     * ser_pus : null
     * ser_major : null
     * atts : null
     * bthtypeid : null
     */

    private List<ItemBean> item;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        private int id;
        private Object stationid;
        private Object orgid;
        private String name;
        private Object job;
        private Object degree;
        private String major;
        private Object address;
        private Object email;
        private Object company;
        private Object experience;
        private Object phonenumber;
        private Object birthday;
        private Object age;
        private Object description;
        private String photo;
        private Object school;
        private Object note;
        private Object belief;
        private Object reviewcount;
        private Object commentcount;
        private Object replycount;
        private Object province;
        private Object city;
        private Object level;
        private Object majortype;
        private int type;
        private Object revertcount;
        private Object villagecount;
        private Object createdate;
        private Object visitscount;
        private Object direction;
        private Object achievement;
        private Object researchproject;
        private Object thesis;
        private String longitude;
        private String latitude;
        private String status;
        private Object ser_othername;
        private Object ser_position;
        private Object ser_worktime;
        private Object ser_behere;
        private Object ser_unity;
        private Object ser_depart;
        private Object ser_gradu;
        private Object ser_amaku;
        private Object ser_amaku_place;
        private Object ser_amaku_time;
        private Object ser_duty;
        private Object ser_duty_time;
        private Object ser_dhun;
        private Object ser_dhun_time;
        private Object ser_feat;
        private Object ser_ser;
        private Object ser_sce;
        private Object ser_man;
        private Object ser_pus;
        private Object ser_major;
        private Object atts;
        private Object bthtypeid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getStationid() {
            return stationid;
        }

        public void setStationid(Object stationid) {
            this.stationid = stationid;
        }

        public Object getOrgid() {
            return orgid;
        }

        public void setOrgid(Object orgid) {
            this.orgid = orgid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getJob() {
            return job;
        }

        public void setJob(Object job) {
            this.job = job;
        }

        public Object getDegree() {
            return degree;
        }

        public void setDegree(Object degree) {
            this.degree = degree;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getExperience() {
            return experience;
        }

        public void setExperience(Object experience) {
            this.experience = experience;
        }

        public Object getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(Object phonenumber) {
            this.phonenumber = phonenumber;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public Object getBelief() {
            return belief;
        }

        public void setBelief(Object belief) {
            this.belief = belief;
        }

        public Object getReviewcount() {
            return reviewcount;
        }

        public void setReviewcount(Object reviewcount) {
            this.reviewcount = reviewcount;
        }

        public Object getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(Object commentcount) {
            this.commentcount = commentcount;
        }

        public Object getReplycount() {
            return replycount;
        }

        public void setReplycount(Object replycount) {
            this.replycount = replycount;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getMajortype() {
            return majortype;
        }

        public void setMajortype(Object majortype) {
            this.majortype = majortype;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getRevertcount() {
            return revertcount;
        }

        public void setRevertcount(Object revertcount) {
            this.revertcount = revertcount;
        }

        public Object getVillagecount() {
            return villagecount;
        }

        public void setVillagecount(Object villagecount) {
            this.villagecount = villagecount;
        }

        public Object getCreatedate() {
            return createdate;
        }

        public void setCreatedate(Object createdate) {
            this.createdate = createdate;
        }

        public Object getVisitscount() {
            return visitscount;
        }

        public void setVisitscount(Object visitscount) {
            this.visitscount = visitscount;
        }

        public Object getDirection() {
            return direction;
        }

        public void setDirection(Object direction) {
            this.direction = direction;
        }

        public Object getAchievement() {
            return achievement;
        }

        public void setAchievement(Object achievement) {
            this.achievement = achievement;
        }

        public Object getResearchproject() {
            return researchproject;
        }

        public void setResearchproject(Object researchproject) {
            this.researchproject = researchproject;
        }

        public Object getThesis() {
            return thesis;
        }

        public void setThesis(Object thesis) {
            this.thesis = thesis;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getSer_othername() {
            return ser_othername;
        }

        public void setSer_othername(Object ser_othername) {
            this.ser_othername = ser_othername;
        }

        public Object getSer_position() {
            return ser_position;
        }

        public void setSer_position(Object ser_position) {
            this.ser_position = ser_position;
        }

        public Object getSer_worktime() {
            return ser_worktime;
        }

        public void setSer_worktime(Object ser_worktime) {
            this.ser_worktime = ser_worktime;
        }

        public Object getSer_behere() {
            return ser_behere;
        }

        public void setSer_behere(Object ser_behere) {
            this.ser_behere = ser_behere;
        }

        public Object getSer_unity() {
            return ser_unity;
        }

        public void setSer_unity(Object ser_unity) {
            this.ser_unity = ser_unity;
        }

        public Object getSer_depart() {
            return ser_depart;
        }

        public void setSer_depart(Object ser_depart) {
            this.ser_depart = ser_depart;
        }

        public Object getSer_gradu() {
            return ser_gradu;
        }

        public void setSer_gradu(Object ser_gradu) {
            this.ser_gradu = ser_gradu;
        }

        public Object getSer_amaku() {
            return ser_amaku;
        }

        public void setSer_amaku(Object ser_amaku) {
            this.ser_amaku = ser_amaku;
        }

        public Object getSer_amaku_place() {
            return ser_amaku_place;
        }

        public void setSer_amaku_place(Object ser_amaku_place) {
            this.ser_amaku_place = ser_amaku_place;
        }

        public Object getSer_amaku_time() {
            return ser_amaku_time;
        }

        public void setSer_amaku_time(Object ser_amaku_time) {
            this.ser_amaku_time = ser_amaku_time;
        }

        public Object getSer_duty() {
            return ser_duty;
        }

        public void setSer_duty(Object ser_duty) {
            this.ser_duty = ser_duty;
        }

        public Object getSer_duty_time() {
            return ser_duty_time;
        }

        public void setSer_duty_time(Object ser_duty_time) {
            this.ser_duty_time = ser_duty_time;
        }

        public Object getSer_dhun() {
            return ser_dhun;
        }

        public void setSer_dhun(Object ser_dhun) {
            this.ser_dhun = ser_dhun;
        }

        public Object getSer_dhun_time() {
            return ser_dhun_time;
        }

        public void setSer_dhun_time(Object ser_dhun_time) {
            this.ser_dhun_time = ser_dhun_time;
        }

        public Object getSer_feat() {
            return ser_feat;
        }

        public void setSer_feat(Object ser_feat) {
            this.ser_feat = ser_feat;
        }

        public Object getSer_ser() {
            return ser_ser;
        }

        public void setSer_ser(Object ser_ser) {
            this.ser_ser = ser_ser;
        }

        public Object getSer_sce() {
            return ser_sce;
        }

        public void setSer_sce(Object ser_sce) {
            this.ser_sce = ser_sce;
        }

        public Object getSer_man() {
            return ser_man;
        }

        public void setSer_man(Object ser_man) {
            this.ser_man = ser_man;
        }

        public Object getSer_pus() {
            return ser_pus;
        }

        public void setSer_pus(Object ser_pus) {
            this.ser_pus = ser_pus;
        }

        public Object getSer_major() {
            return ser_major;
        }

        public void setSer_major(Object ser_major) {
            this.ser_major = ser_major;
        }

        public Object getAtts() {
            return atts;
        }

        public void setAtts(Object atts) {
            this.atts = atts;
        }

        public Object getBthtypeid() {
            return bthtypeid;
        }

        public void setBthtypeid(Object bthtypeid) {
            this.bthtypeid = bthtypeid;
        }
    }
}
