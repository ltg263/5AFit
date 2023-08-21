package com.jxkj.fit_5a.entity;

import java.util.List;

public class CircleQueryJoinedBean {

    /**
     * list : [{"completedCount":0,"createTime":"","explain":"","id":0,"imgUrl":"","join":true,"memberCount":0,"momentCount":0,"name":""}]
     * page : 0
     * pageSize : 0
     * total : 0
     */

    private int page;
    private int pageSize;
    private int total;
    private List<ListBean> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {


        /**
         * completedCount : 0
         * createTimestamp : 0
         * deviceType : 0
         * explain :
         * hot : true
         * id : 0
         * imgUrl :
         * join : true
         * lastTimestamp : 0
         * memberCount : 0
         * momentCount : 0
         * name :
         */

        private int completedCount;
        private long createTimestamp;
        private int deviceType;
        private String explain;
        private boolean hot;
        private String id;
        private String imgUrl;
        private boolean join;
        private long lastTimestamp;
        private int memberCount;
        private int momentCount;
        private String name;

        public int getCompletedCount() {
            return completedCount;
        }

        public void setCompletedCount(int completedCount) {
            this.completedCount = completedCount;
        }

        public long getCreateTimestamp() {
            return createTimestamp;
        }

        public void setCreateTimestamp(long createTimestamp) {
            this.createTimestamp = createTimestamp;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public boolean isJoin() {
            return join;
        }

        public void setJoin(boolean join) {
            this.join = join;
        }

        public long getLastTimestamp() {
            return lastTimestamp;
        }

        public void setLastTimestamp(long lastTimestamp) {
            this.lastTimestamp = lastTimestamp;
        }

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public int getMomentCount() {
            return momentCount;
        }

        public void setMomentCount(int momentCount) {
            this.momentCount = momentCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
