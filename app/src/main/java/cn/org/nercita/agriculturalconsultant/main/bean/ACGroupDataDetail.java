package cn.org.nercita.agriculturalconsultant.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cuiyonghong on 16/10/29.
 */
/**
 * 描述：群列表实体类
 * @author GaoWenXu
 * @date 2017/4/11 8:44
 * @version v1.0.0
 */
public class ACGroupDataDetail implements Serializable {


    /**
     * createtime : 2016-10-27 14:14:30
     * id : 1
     * memberIds : [{"imuserId":"admin"},{"imuserId":"user101"}]
     * ownerName : 王洋
     * chatgroupid : 257626797930185128
     * description : 番茄育种、品种选择、栽培技术
     * owneraccountid : 69
     * maxusers : 300
     * peoplenum : 2
     * groupname : 番茄种植交流群
     * pic : null
     * industry : 种植
     * type : 蔬菜
     * ownerPic : http://123.127.160.21:80/atemanage//resources/uploads/manager/201610/20161018105052_462.jpg
     * memberhref : http://123.127.160.21:80/atemanage/mobile/easemod/chatgroup/getGroupMembers.shtml?groupId=257626797930185128
     * blackhref : http://123.127.160.21:80/atemanage/mobile/easemod/chatgroup/getGroupBlacks.shtml?groupId=257626797930185128
     */

    private String createtime;
    private int id;
    private String ownerName;
    private String chatgroupid;
    private String description;
    private int owneraccountid;
    private int maxusers;
    private int peoplenum;
    private String groupname;
    private String pic;
    private String industry;
    private String type;
    private String ownerPic;
    private String memberhref;
    private String blackhref;
    /**
     * imuserId : admin
     */

    private List<MemberIdsBean> memberIds;

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

    public int getMaxusers() {
        return maxusers;
    }

    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
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

    public String getMemberhref() {
        return memberhref;
    }

    public void setMemberhref(String memberhref) {
        this.memberhref = memberhref;
    }

    public String getBlackhref() {
        return blackhref;
    }

    public void setBlackhref(String blackhref) {
        this.blackhref = blackhref;
    }

    public List<MemberIdsBean> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<MemberIdsBean> memberIds) {
        this.memberIds = memberIds;
    }

    public static class MemberIdsBean implements Serializable {
        private String imuserId;

        public String getImuserId() {
            return imuserId;
        }

        public void setImuserId(String imuserId) {
            this.imuserId = imuserId;
        }
    }
}
