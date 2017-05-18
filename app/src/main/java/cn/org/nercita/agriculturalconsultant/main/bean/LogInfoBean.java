package cn.org.nercita.agriculturalconsultant.main.bean;

/**
 * Created by fan on 2017/4/12.
 */

public class LogInfoBean {

    /**
     * state : 200
     * LoginUser : {"id":22,"userName":"13785314579","nickName":"hunter","password":"123456",
     * "createDate":1491966454000,"lastLoginDate":null,"isEnabled":"1","note":null,
     * "roles":"common","accountId":null,"photo":"","phone":"13785314579","bthTypeId":"1"}
     */

    private int state;
    /**
     * id : 22
     * userName : 13785314579
     * nickName : hunter
     * password : 123456
     * createDate : 1491966454000
     * lastLoginDate : null
     * isEnabled : 1
     * note : null
     * roles : common
     * accountId : null
     * photo :
     * phone : 13785314579
     * bthTypeId : 1
     */

    private LoginUserBean LoginUser;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public LoginUserBean getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(LoginUserBean LoginUser) {
        this.LoginUser = LoginUser;
    }

    public static class LoginUserBean {
        private int id;
        private String userName;
        private String nickName;
        private String password;
        private long createDate;
        private Object lastLoginDate;
        private String isEnabled;
        private Object note;
        private String roles;
        private int accountId;
        private String photo;
        private String phone;
        private String bthTypeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(Object lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public String getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(String isEnabled) {
            this.isEnabled = isEnabled;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBthTypeId() {
            return bthTypeId;
        }

        public void setBthTypeId(String bthTypeId) {
            this.bthTypeId = bthTypeId;
        }
    }
}
