package cn.org.nercita.agriculturalconsultant.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nercita on 2017/4/10.
 */
/**
 * 描述：群组信息实体类
 * @author GaoWenXu
 * @date 2017/4/10 12:52
 * @version v1.0.0
 */
public class ACGroupInfoBean {

    /**
     * result : [{"createtime":"2016-10-27 14:14:30","id":1,"ownerName":"王洋","detailhref":"http://123.127.160.21:80/atemanage/mobile/easemod/chatgroup/getGroupDetail.shtml?groupId=257626797930185128","chatgroupid":"257626797930185128","description":"番茄育种、品种选择、栽培技术","owneraccountid":69,"peoplenum":2,"groupname":"番茄种植交流群","pic":null,"industry":"种植","type":"蔬菜","ownerPic":"http://123.127.160.21:80/atemanage//resources/uploads/manager/201610/20161018105052_462.jpg"}]
     * pageNo : 1
     * isLastPage : true
     */

    private int pageNo;
    private boolean isLastPage;
    /**
     * createtime : 2016-10-27 14:14:30
     * id : 1
     * ownerName : 王洋
     * detailhref : http://123.127.160.21:80/atemanage/mobile/easemod/chatgroup/getGroupDetail.shtml?groupId=257626797930185128
     * chatgroupid : 257626797930185128
     * description : 番茄育种、品种选择、栽培技术
     * owneraccountid : 69
     * peoplenum : 2
     * groupname : 番茄种植交流群
     * pic : null
     * industry : 种植
     * type : 蔬菜
     * ownerPic : http://123.127.160.21:80/atemanage//resources/uploads/manager/201610/20161018105052_462.jpg
     */

    private List<ResultBean> result;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        private String createtime;
        private int id;
        private String ownerName;
        private String detailhref;
        private String chatgroupid;
        private String description;
        private int owneraccountid;
        private int peoplenum;
        private String groupname;
        private String pic;
        private String industry;
        private String type;
        private String ownerPic;

        public int getUnReadCountSize() {
            return unReadCountSize;
        }

        public void setUnReadCountSize(int unReadCountSize) {
            this.unReadCountSize = unReadCountSize;
        }

        private int unReadCountSize;



        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getDetailhref() {
            return detailhref;
        }

        public void setDetailhref(String detailhref) {
            this.detailhref = detailhref;
        }

        public String getChatgroupid() {
            return chatgroupid;
        }

        public void setChatgroupid(String chatgroupid) {
            this.chatgroupid = chatgroupid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getOwneraccountid() {
            return owneraccountid;
        }

        public void setOwneraccountid(int owneraccountid) {
            this.owneraccountid = owneraccountid;
        }

        public int getPeoplenum() {
            return peoplenum;
        }

        public void setPeoplenum(int peoplenum) {
            this.peoplenum = peoplenum;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOwnerPic() {
            return ownerPic;
        }

        public void setOwnerPic(String ownerPic) {
            this.ownerPic = ownerPic;
        }
    }
}
