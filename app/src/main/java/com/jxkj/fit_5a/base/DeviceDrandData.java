package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceDrandData {

    /**
     * list : [{"id":4,"name":"设备品牌一","img":"","hasDel":0},{"id":3,"name":"设备品牌二","img":"","hasDel":0},{"id":1,"name":"设备品牌三","img":"asd","hasDel":0}]
     * totalCount : 3
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
         * name : 设备品牌一
         * img :
         * hasDel : 0
         */

        private int id;
        private String name;
        private String img;
        private int hasDel;
        private boolean isSelect = false;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getHasDel() {
            return hasDel;
        }

        public void setHasDel(int hasDel) {
            this.hasDel = hasDel;
        }
    }
}
