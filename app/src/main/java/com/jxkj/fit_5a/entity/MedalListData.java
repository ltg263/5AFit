package com.jxkj.fit_5a.entity;

import java.util.List;

public class MedalListData {

    /**
     * id : 1
     * name : 系统勋章
     * sort : 1111
     * medals : [{"medalId":3,"userId":0,"hasHave":false,"createTime":"","type":1,"name":"系统勋章2","imgUrl":"https://haide.nbqichen.com/haide/upload/85618D73DA5ADE0E0DC0DC16638309F6.png","imgUrlNo":"https://haide.nbqichen.com/haide/upload/6C10B69342B870EDB3FFE517980C67AD.png","explain":"系统勋章测试2","sort":2}]
     */

    private int id;
    private String name;
    private int sort;
    private List<MedalsBean> medals;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<MedalsBean> getMedals() {
        return medals;
    }

    public void setMedals(List<MedalsBean> medals) {
        this.medals = medals;
    }

    public static class MedalsBean {
        /**
         * medalId : 3
         * userId : 0
         * hasHave : false
         * createTime :
         * type : 1
         * name : 系统勋章2
         * imgUrl : https://haide.nbqichen.com/haide/upload/85618D73DA5ADE0E0DC0DC16638309F6.png
         * imgUrlNo : https://haide.nbqichen.com/haide/upload/6C10B69342B870EDB3FFE517980C67AD.png
         * explain : 系统勋章测试2
         * sort : 2
         */

        private int medalId;
        private int userId;
        private boolean hasHave;
        private String createTime;
        private int type;
        private String name;
        private String imgUrl;
        private String imgUrlNo;
        private String explain;
        private int sort;

        public int getMedalId() {
            return medalId;
        }

        public void setMedalId(int medalId) {
            this.medalId = medalId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public boolean isHasHave() {
            return hasHave;
        }

        public void setHasHave(boolean hasHave) {
            this.hasHave = hasHave;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getImgUrlNo() {
            return imgUrlNo;
        }

        public void setImgUrlNo(String imgUrlNo) {
            this.imgUrlNo = imgUrlNo;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
