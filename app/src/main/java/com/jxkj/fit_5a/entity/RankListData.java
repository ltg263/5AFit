package com.jxkj.fit_5a.entity;

import java.util.List;

public class RankListData {

    /**
     * list : [{"clearTime":"","createTime":"","id":0,"status":0,"type":0}]
     * totalCount : 0
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * clearTime :
         * createTime :
         * id : 0
         * status : 0
         * type : 0
         */

        private String clearTime;
        private String createTime;
        private String id;
        private String status;
        private String type;

        public String getClearTime() {
            return clearTime;
        }

        public void setClearTime(String clearTime) {
            this.clearTime = clearTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
