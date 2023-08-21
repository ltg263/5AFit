package com.jxkj.fit_5a.entity;

import java.util.List;

public class OssInfoBean {

    /**
     * bucket :
     * dirUnits : [{"clearStyle":"","description":"","dir":"","thumbStyle":""}]
     * endpoint :
     * host :
     */

    private String bucket;
    private String endpoint;
    private String host;
    private List<DirUnitsBean> dirUnits;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<DirUnitsBean> getDirUnits() {
        return dirUnits;
    }

    public void setDirUnits(List<DirUnitsBean> dirUnits) {
        this.dirUnits = dirUnits;
    }

    public static class DirUnitsBean {
        /**
         * clearStyle :
         * description :
         * dir :
         * thumbStyle :
         */

        private String clearStyle;
        private String description;
        private String dir;
        private String thumbStyle;

        public String getClearStyle() {
            return clearStyle;
        }

        public void setClearStyle(String clearStyle) {
            this.clearStyle = clearStyle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getThumbStyle() {
            return thumbStyle;
        }

        public void setThumbStyle(String thumbStyle) {
            this.thumbStyle = thumbStyle;
        }
    }
}

