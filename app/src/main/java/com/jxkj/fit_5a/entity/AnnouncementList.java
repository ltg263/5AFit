package com.jxkj.fit_5a.entity;

import java.util.List;

public class AnnouncementList {

    /**
     * page : 1
     * pageSize : 10
     * total : 0
     * totalCount : 2
     * list : [{"id":"61023825d8d947031ce14698","title":"20200729测试内容","pushStatus":1,"createTime":1627535397810,"pushTime":0},{"id":"60b4bd6bd8d947633652a00d","title":"商户要不要做微信小程序？","pushStatus":1,"createTime":1622457707058,"pushTime":0}]
     */

    private int page;
    private int pageSize;
    private int total;
    private int totalCount;
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
         * id : 61023825d8d947031ce14698
         * title : 20200729测试内容
         * pushStatus : 1
         * createTime : 1627535397810
         * pushTime : 0
         */

        private String id;
        private String title;
        private String pushStatus;
        private long createTime;
        private String pushTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPushStatus() {
            return pushStatus;
        }

        public void setPushStatus(String pushStatus) {
            this.pushStatus = pushStatus;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getPushTime() {
            return pushTime;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }
    }
}
