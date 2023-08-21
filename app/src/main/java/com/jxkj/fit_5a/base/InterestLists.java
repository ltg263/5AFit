package com.jxkj.fit_5a.base;

import java.util.List;

public class InterestLists {
    /**
     * list : [{"id":4,"name":"梦幻西游","imgUrl":"https://haide.nbqichen.com/haide/upload/D343E0B0DBB8643B137E466A9ACBDE05.jpg","quantity":0,"status":1},{"id":3,"name":"穿越火线","imgUrl":"https://haide.nbqichen.com/haide/upload/9BDC76F945E779A3A92CAAE78E3EA8C6.jpg","quantity":0,"status":1},{"id":2,"name":"英雄联盟","imgUrl":"https://haide.nbqichen.com/haide/upload/75F11CC0583146CB74F8218E2E72F94C.jpg","quantity":0,"status":1},{"id":1,"name":"地下城与勇士","imgUrl":"https://haide.nbqichen.com/haide/upload/91BDD9EED04031A4F5110FF091CEEB51.jpg","quantity":0,"status":1}]
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
         * id : 4
         * name : 梦幻西游
         * imgUrl : https://haide.nbqichen.com/haide/upload/D343E0B0DBB8643B137E466A9ACBDE05.jpg
         * quantity : 0
         * status : 1
         */

        private int id;
        private String name;
        private String imgUrl;
        private int quantity;
        private int status;
        private boolean isSelect;

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public boolean isSelect() {
            return isSelect;
        }

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
