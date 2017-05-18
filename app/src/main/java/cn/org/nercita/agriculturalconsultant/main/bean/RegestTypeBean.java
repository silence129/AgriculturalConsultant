package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by fan on 2017/4/14.
 */

public class RegestTypeBean {

    /**
     * id : 1
     * title : 种植业
     * parentId : 0
     * typeId : 1
     * sort : 1
     * childList : []
     * questionList : null
     */

    private List<TypeIdBean> typeId;

    public List<TypeIdBean> getTypeId() {
        return typeId;
    }

    public void setTypeId(List<TypeIdBean> typeId) {
        this.typeId = typeId;
    }

    public static class TypeIdBean {
        private int id;
        private String title;
        private int parentId;
        private int typeId;
        private String sort;
        private Object questionList;
        private List<?> childList;

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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public Object getQuestionList() {
            return questionList;
        }

        public void setQuestionList(Object questionList) {
            this.questionList = questionList;
        }

        public List<?> getChildList() {
            return childList;
        }

        public void setChildList(List<?> childList) {
            this.childList = childList;
        }
    }
}
