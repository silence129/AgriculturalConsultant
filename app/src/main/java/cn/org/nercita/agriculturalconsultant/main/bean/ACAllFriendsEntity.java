package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * 描述：所有好友实体类
 * @author GaoWenXu
 * @date 2017/4/11 10:28
 * @version v1.0.0
 */
public class ACAllFriendsEntity {


    /**
     * result : [{"userPic":null,"nickName":"测试","roleid":30001,"imuserName":"18911398019","userName":"18911398019","accountid":99},{"userPic":null,"nickName":"测试号","roleid":30001,"imuserName":"18258365836","userName":"18258365836","accountid":a100},{"userPic":null,"nickName":"大大","roleid":30001,"imuserName":"18311408018","userName":"18311408018","accountid":101},{"userPic":null,"nickName":"打电话","roleid":30001,"imuserName":"18211885515","userName":"18211885515","accountid":102},{"userPic":null,"nickName":"崔崔","roleid":30001,"imuserName":"18311408017","userName":"18311408017","accountid":103}]
     * pageNo : 1
     * isLastPage : true
     */

    private int pageNo;
    private boolean isLastPage;
    /**
     * userPic : null
     * nickName : 测试
     * roleid : 30001
     * imuserName : 18911398019
     * userName : 18911398019
     * accountid : 99
     */

    private List<ACGroupMemberEntity.MembersBean> result;

    public List<ACGroupMemberEntity.MembersBean> getResult() {
        return result;
    }

    public void setResult(List<ACGroupMemberEntity.MembersBean> result) {
        this.result = result;
    }

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
}
