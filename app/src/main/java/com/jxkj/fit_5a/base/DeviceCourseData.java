package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceCourseData {

    /**
     * list : [{"id":34,"deviceTypeId":0,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":35,"deviceTypeId":16,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":36,"deviceTypeId":48,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":37,"deviceTypeId":80,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":38,"deviceTypeId":112,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":39,"deviceTypeId":113,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":40,"deviceTypeId":128,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":41,"deviceTypeId":180,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":42,"deviceTypeId":200,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4},{"id":43,"deviceTypeId":230,"level":1,"name":"111","imgUrl":"","Stringroduct":"支持脚踏车，飞轮车。5AFit app需要接入您的器材","stage":2,"maxMultiple":1,"details":{"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]},"status":1,"type":4}]
     * totalCount : 10
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
         * id : 34
         * deviceTypeId : 0
         * level : 1
         * name : 111
         * imgUrl :
         * introduct : 支持脚踏车，飞轮车。5AFit app需要接入您的器材
         * stage : 2
         * maxMultiple : 1
         * details : {"resistances":[{"value":1},{"value":2},{"value":3},{"value":5},{"value":4},{"value":6},{"value":10},{"value":4},{"value":2},{"value":1}]}
         * status : 1
         * type : 4
         */

        private String id;
        private String deviceTypeId;
        private String level;
        private String name;
        private String imgUrl;
        private String introduct;
        private String stage;
        private String maxMultiple;
        private DetailsBean details;
        private String status;
        private String type;

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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getIntroduct() {
            return introduct;
        }

        public void setIntroduct(String introduct) {
            this.introduct = introduct;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getMaxMultiple() {
            return maxMultiple;
        }

        public void setMaxMultiple(String maxMultiple) {
            this.maxMultiple = maxMultiple;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
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

        public static class DetailsBean {
            private List<ResistancesBean> resistances;

            public List<ResistancesBean> getResistances() {
                return resistances;
            }

            public void setResistances(List<ResistancesBean> resistances) {
                this.resistances = resistances;
            }

            public static class ResistancesBean {
                /**
                 * value : 1
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
