package cn.org.nercita.agriculturalconsultant.main.bean;

/**
 * Created by 梁兴胜 on 2017/4/13.
 * 响应结果
 */

public class ResponseBean {

    /**
     * status : 500
     * message : 提交信息不全，添加失败
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
