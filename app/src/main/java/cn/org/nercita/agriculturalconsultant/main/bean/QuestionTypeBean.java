package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 范博文 on 2017/4/7.
 */

public class QuestionTypeBean {



    /**
     * id : 1
     * name : 养殖技术
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
