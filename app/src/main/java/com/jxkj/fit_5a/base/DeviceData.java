package com.jxkj.fit_5a.base;

import java.util.List;

public class DeviceData {

    /**
     * list : [{"detail":"","deviceBrandId":0,"deviceTypeId":0,"id":0,"img":"","name":"","param":{"adjustIncline":true,"adjustLevel":true,"adjustSpeed":true,"attachParam":"","id":0,"inclineMax":0,"inclineMin":0,"levelMax":0,"levelMin":0,"pauseAble":true,"readHeartRateAble":true,"speedMax":0,"speedMin":0,"supportProgram":true},"subTitle":""}]
     * page : 0
     * pageSize : 0
     * total : 0
     * totalCount : 0
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
         * detail :
         * deviceBrandId : 0
         * deviceTypeId : 0
         * id : 0
         * img :
         * name :
         * param : {"adjustIncline":true,"adjustLevel":true,"adjustSpeed":true,"attachParam":"","id":0,"inclineMax":0,"inclineMin":0,"levelMax":0,"levelMin":0,"pauseAble":true,"readHeartRateAble":true,"speedMax":0,"speedMin":0,"supportProgram":true}
         * subTitle :
         */

        private String detail;
        private int deviceBrandId;
        private int deviceTypeId;
        private int id;
        private String img;
        private String name;
        private ParamBean param;
        private String subTitle;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getDeviceBrandId() {
            return deviceBrandId;
        }

        public void setDeviceBrandId(int deviceBrandId) {
            this.deviceBrandId = deviceBrandId;
        }

        public int getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(int deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ParamBean getParam() {
            return param;
        }

        public void setParam(ParamBean param) {
            this.param = param;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public static class ParamBean {
            /**
             * adjustIncline : true
             * adjustLevel : true
             * adjustSpeed : true
             * attachParam :
             * id : 0
             * inclineMax : 0
             * inclineMin : 0
             * levelMax : 0
             * levelMin : 0
             * pauseAble : true
             * readHeartRateAble : true
             * speedMax : 0
             * speedMin : 0
             * supportProgram : true
             */

            private boolean adjustIncline;
            private boolean adjustLevel;
            private boolean adjustSpeed;
            private String attachParam;
            private int id;
            private int inclineMax;
            private int inclineMin;
            private int levelMax;
            private int levelMin;
            private boolean pauseAble;
            private boolean readHeartRateAble;
            private int speedMax;
            private int speedMin;
            private boolean supportProgram;

            public boolean isAdjustIncline() {
                return adjustIncline;
            }

            public void setAdjustIncline(boolean adjustIncline) {
                this.adjustIncline = adjustIncline;
            }

            public boolean isAdjustLevel() {
                return adjustLevel;
            }

            public void setAdjustLevel(boolean adjustLevel) {
                this.adjustLevel = adjustLevel;
            }

            public boolean isAdjustSpeed() {
                return adjustSpeed;
            }

            public void setAdjustSpeed(boolean adjustSpeed) {
                this.adjustSpeed = adjustSpeed;
            }

            public String getAttachParam() {
                return attachParam;
            }

            public void setAttachParam(String attachParam) {
                this.attachParam = attachParam;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInclineMax() {
                return inclineMax;
            }

            public void setInclineMax(int inclineMax) {
                this.inclineMax = inclineMax;
            }

            public int getInclineMin() {
                return inclineMin;
            }

            public void setInclineMin(int inclineMin) {
                this.inclineMin = inclineMin;
            }

            public int getLevelMax() {
                return levelMax;
            }

            public void setLevelMax(int levelMax) {
                this.levelMax = levelMax;
            }

            public int getLevelMin() {
                return levelMin;
            }

            public void setLevelMin(int levelMin) {
                this.levelMin = levelMin;
            }

            public boolean isPauseAble() {
                return pauseAble;
            }

            public void setPauseAble(boolean pauseAble) {
                this.pauseAble = pauseAble;
            }

            public boolean isReadHeartRateAble() {
                return readHeartRateAble;
            }

            public void setReadHeartRateAble(boolean readHeartRateAble) {
                this.readHeartRateAble = readHeartRateAble;
            }

            public int getSpeedMax() {
                return speedMax;
            }

            public void setSpeedMax(int speedMax) {
                this.speedMax = speedMax;
            }

            public int getSpeedMin() {
                return speedMin;
            }

            public void setSpeedMin(int speedMin) {
                this.speedMin = speedMin;
            }

            public boolean isSupportProgram() {
                return supportProgram;
            }

            public void setSupportProgram(boolean supportProgram) {
                this.supportProgram = supportProgram;
            }
        }
    }
}
