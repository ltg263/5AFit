package com.jxkj.fit_5a.base;

import java.util.List;

public class HelpListData {
    /**
     * list : [{"id":2,"type":2,"deviceType":2,"brand":4,"deviceId":2,"title":"帮助问题二","detailType":1,"detail":"","status":0,"createTime":"2020-10-14 11:50:00"},{"id":1,"type":1,"deviceType":0,"brand":0,"deviceId":0,"title":"帮助问题一","detailType":1,"detail":"<p>123134<\/p>","status":1,"createTime":"2020-10-13 08:50:11"}]
     * totalCount : 2
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
         * id : 2
         * type : 2
         * deviceType : 2
         * brand : 4
         * deviceId : 2
         * title : 帮助问题二
         * detailType : 1
         * detail :
         * status : 0
         * createTime : 2020-10-14 11:50:00
         */

        private int id;
        private int type;
        private int deviceType;
        private int brand;
        private int deviceId;
        private String title;
        private int detailType;
        private String detail;
        private int status;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public int getBrand() {
            return brand;
        }

        public void setBrand(int brand) {
            this.brand = brand;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDetailType() {
            return detailType;
        }

        public void setDetailType(int detailType) {
            this.detailType = detailType;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
