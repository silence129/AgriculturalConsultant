package cn.org.nercita.agriculturalconsultant.main.bean;

import java.util.List;

/**
 * Created by 梁兴胜 on 2017/4/14. 种植类型
 */

public class TypeListBean {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * title : 种植业
         * parentId : 0
         * typeId : 1
         * sort : 1
         * childList : [{"id":9,"title":"谷物种植","parentId":1,"typeId":1,"sort":"1","childList":[],"questionList":""},{"id":18,"title":"花卉种植","parentId":1,"typeId":1,"sort":"10","childList":[],"questionList":""},{"id":19,"title":"水果种植","parentId":1,"typeId":1,"sort":"11","childList":[],"questionList":null},{"id":20,"title":"中药材种植","parentId":1,"typeId":1,"sort":"12","childList":[],"questionList":null},{"id":21,"title":"其他作物种植","parentId":1,"typeId":1,"sort":"13","childList":[],"questionList":null},{"id":10,"title":"薯类种植","parentId":1,"typeId":1,"sort":"2","childList":[],"questionList":null},{"id":11,"title":"油料种植","parentId":1,"typeId":1,"sort":"3","childList":[],"questionList":null},{"id":12,"title":"豆类种植","parentId":1,"typeId":1,"sort":"4","childList":[],"questionList":null},{"id":13,"title":"棉花种植","parentId":1,"typeId":1,"sort":"5","childList":[],"questionList":null},{"id":14,"title":"麻类种植","parentId":1,"typeId":1,"sort":"6","childList":[],"questionList":null},{"id":15,"title":"糖料种植","parentId":1,"typeId":1,"sort":"7","childList":[],"questionList":null},{"id":16,"title":"烟草种植","parentId":1,"typeId":1,"sort":"8","childList":[],"questionList":null},{"id":17,"title":"蔬菜种植","parentId":1,"typeId":1,"sort":"9","childList":[],"questionList":null}]
         * questionList : null
         */

        private int id;
        private String title;
        private int parentId;
        private int typeId;
        private String sort;
        private Object questionList;
        private List<ChildListBean> childList;

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

        public List<ChildListBean> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildListBean> childList) {
            this.childList = childList;
        }

        public static class ChildListBean {
            /**
             * id : 9
             * title : 谷物种植
             * parentId : 1
             * typeId : 1
             * sort : 1
             * childList : []
             * questionList :
             */

            private int id;
            private String title;
            private int parentId;
            private int typeId;
            private String sort;
            private String questionList;
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

            public String getQuestionList() {
                return questionList;
            }

            public void setQuestionList(String questionList) {
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
}
