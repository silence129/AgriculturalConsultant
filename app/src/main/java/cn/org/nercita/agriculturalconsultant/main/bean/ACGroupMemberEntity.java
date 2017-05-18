package cn.org.nercita.agriculturalconsultant.main.bean;




import java.io.Serializable;
import java.util.List;

import cn.org.nercita.agriculturalconsultant.utils.EaseCommonUtils;

/**
 * 描述：拉取成员个人信息实体类
 * @author GaoWenXu
 * @date 2017/4/12 8:45
 * @version v1.0.0
 */
public class ACGroupMemberEntity implements Serializable {


    /**
     * userPic : http://123.127.160.21:80/atemanage//resources/uploads/manager/201610/20161018105052_462.jpg
     * nickName : 王洋
     * imuserName : admin
     * userName : 0101234
     */
    private List<MembersBean> members;

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class MembersBean implements Serializable {
        private String userPic;
        private String nickName;
        private String imuserName;
        private String userName;
        private int roleid;
        private int accountid;

        public int getRoleid() {
            return roleid;
        }

        public void setRoleid(int roleid) {
            this.roleid = roleid;
        }

        public int getAccountid() {
            return accountid;
        }

        public void setAccountid(int accountid) {
            this.accountid = accountid;
        }

        /**
         * initial letter for nickname
         */
        protected String initialLetter;

        public MembersBean(){

        }

        public String getInitialLetter() {
            if(initialLetter == null){
                EaseCommonUtils.setUserInitialLetter(this);
            }
            return initialLetter;
        }

        public void setInitialLetter(String initialLetter) {
            this.initialLetter = initialLetter;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getImuserName() {
            return imuserName;
        }

        public void setImuserName(String imuserName) {
            this.imuserName = imuserName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MembersBean that = (MembersBean) o;
            if (imuserName != null ? !imuserName.equals(that.imuserName) : that.imuserName != null)
                return false;
            return true;
        }
    }
}
