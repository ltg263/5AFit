package com.jxkj.fit_5a.entity;

import java.util.List;

public class AdListData {
    /**
     * list : [{"id":1,"seat":1,"imgUrl":"https://haide.nbqichen.com/haide/upload/3E4AF99151356675D4565C313C6E7474.png","title":"asds","subTitle":"","sort":1,"provinceId":330000,"cityId":330200,"districtId":330203,"region":"","type":1,"content":"","status":1,"createTime":"2020-10-14 14:01:46"},{"id":2,"seat":1,"imgUrl":"https://haide.nbqichen.com/haide/upload/C8C1A44AA3608B431FE500B82F57ED5A.png","title":"12323","subTitle":"","sort":1,"provinceId":330000,"cityId":330200,"districtId":330204,"region":"","type":1,"content":"","status":1,"createTime":"2020-10-14 14:03:38"}]
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
         * id : 1
         * seat : 1
         * imgUrl : https://haide.nbqichen.com/haide/upload/3E4AF99151356675D4565C313C6E7474.png
         * title : asds
         * subTitle :
         * sort : 1
         * provinceId : 330000
         * cityId : 330200
         * districtId : 330203
         * region :
         * type : 1
         * content :
         * status : 1
         * createTime : 2020-10-14 14:01:46
         */

        private int id;
        private int seat;
        private String imgUrl;
        private String title;
        private String subTitle;
        private int sort;
        private int provinceId;
        private int cityId;
        private int districtId;
        private String region;
        private int type;
        private String content;
        private int status;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
