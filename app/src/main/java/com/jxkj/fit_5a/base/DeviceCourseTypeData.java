package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceCourseTypeData {

    /**
     * list : [{"id":3,"deviceTypeId":0,"name":"有氧模式","sort":3,"introduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材"},{"id":1,"deviceTypeId":0,"name":"热身模式","sort":2,"introduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材"},{"id":2,"deviceTypeId":0,"name":"塑身模式","sort":1,"introduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材"},{"id":4,"deviceTypeId":0,"name":"减脂模式","sort":1,"introduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材"}]
     * totalCount : 4
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
         * id : 3
         * deviceTypeId : 0
         * name : 有氧模式
         * sort : 3
         * introduct : 支持脚踏车，飞轮车。5AFit app需要接入您的器材
         */

        private String id;
        private String deviceTypeId;
        private String name;
        private String sort;
        private String imgUrl;
        private String introduct;

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(String deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIntroduct() {
            return introduct;
        }

        public void setIntroduct(String introduct) {
            this.introduct = introduct;
        }
    }
}
