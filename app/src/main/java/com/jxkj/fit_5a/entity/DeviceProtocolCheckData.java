package com.jxkj.fit_5a.entity;

public class DeviceProtocolCheckData {

    /**
     * result : false
     * deviceBrandTypeModel : {"deviceBrand":{"id":56,"name":"HEAD FITNESS","protocolName":"iconsole","paramId":"55","img":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/device/1kL4w9LWwasXTRcNLcjteA.png"},"deviceType":{"id":5,"name":"跑步机","protocolName":"iconsole","paramId":"210","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png"},"deviceModel":null}
     */

    private boolean result;
    private DeviceBrandTypeModelBean deviceBrandTypeModel;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public DeviceBrandTypeModelBean getDeviceBrandTypeModel() {
        return deviceBrandTypeModel;
    }

    public void setDeviceBrandTypeModel(DeviceBrandTypeModelBean deviceBrandTypeModel) {
        this.deviceBrandTypeModel = deviceBrandTypeModel;
    }

    public static class DeviceBrandTypeModelBean {
        /**
         * deviceBrand : {"id":56,"name":"HEAD FITNESS","protocolName":"iconsole","paramId":"55","img":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/device/1kL4w9LWwasXTRcNLcjteA.png"}
         * deviceType : {"id":5,"name":"跑步机","protocolName":"iconsole","paramId":"210","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png"}
         * deviceModel : null
         */

        private DeviceBrandBean deviceBrand;
        private DeviceTypeBean deviceType;
        private Object deviceModel;

        public DeviceBrandBean getDeviceBrand() {
            return deviceBrand;
        }

        public void setDeviceBrand(DeviceBrandBean deviceBrand) {
            this.deviceBrand = deviceBrand;
        }

        public DeviceTypeBean getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(DeviceTypeBean deviceType) {
            this.deviceType = deviceType;
        }

        public Object getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(Object deviceModel) {
            this.deviceModel = deviceModel;
        }

        public static class DeviceBrandBean {
            /**
             * id : 56
             * name : HEAD FITNESS
             * protocolName : iconsole
             * paramId : 55
             * img : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/device/1kL4w9LWwasXTRcNLcjteA.png
             */

            private int id;
            private String name;
            private String protocolName;
            private String paramId;
            private String img;

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

            public String getProtocolName() {
                return protocolName;
            }

            public void setProtocolName(String protocolName) {
                this.protocolName = protocolName;
            }

            public String getParamId() {
                return paramId;
            }

            public void setParamId(String paramId) {
                this.paramId = paramId;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class DeviceTypeBean {
            /**
             * id : 5
             * name : 跑步机
             * protocolName : iconsole
             * paramId : 210
             * img : https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png
             */

            private int id;
            private String name;
            private String protocolName;
            private int paramId;
            private String img;

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

            public String getProtocolName() {
                return protocolName;
            }

            public void setProtocolName(String protocolName) {
                this.protocolName = protocolName;
            }

            public int getParamId() {
                return paramId;
            }

            public void setParamId(int paramId) {
                this.paramId = paramId;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
