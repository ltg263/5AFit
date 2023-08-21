package com.jxkj.fit_5a.base;

import java.io.Serializable;
import java.util.List;

public class DeviceTypeData {

    /**
     * list : [{"id":230,"parentId":0,"name":"划船机","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GR"},{"id":210,"parentId":0,"name":"跑步机","brandIds":"","serviceUuid":"FE59","characteristicRead":"","characteristicWrite":"8EC90003-F315-4F60-9FB8-838830DAEA50","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GT"},{"id":200,"parentId":0,"name":"手动脚踏车/椭圆机","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GM"},{"id":180,"parentId":0,"name":"健腹轮","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"none"},{"id":128,"parentId":0,"name":"横向训练","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GL"},{"id":113,"parentId":0,"name":"电动弧跑","brandIds":"","serviceUuid":"FE59","characteristicRead":"","characteristicWrite":"8EC90003-F315-4F60-9FB8-838830DAEA50","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GCC"},{"id":112,"parentId":0,"name":"手动弧跑","brandIds":"","serviceUuid":"FE59","characteristicRead":"","characteristicWrite":"8EC90003-F315-4F60-9FB8-838830DAEA50","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"none"},{"id":80,"parentId":0,"name":"滑雪机","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GS"},{"id":48,"parentId":0,"name":"手动有段位的脚踏车/椭圆机","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GR"},{"id":0,"parentId":0,"name":"脚踏车/椭圆机","brandIds":"","serviceUuid":"49535343-FE7D-4AE5-8FA9-9FAFD205E455","characteristicRead":"49535343-8841-43F4-A8D4-ECBE34729BB3","characteristicWrite":"49535343-1E4D-4BD9-BA61-23C647249616","hasDel":0,"subTitle":"xxxxxx","img":"https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png","program":"GB"}]
     * totalCount : 11
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

    public static class ListBean implements Serializable {
        /**
         * id : 230
         * parentId : 0
         * name : 划船机
         * brandIds :
         * serviceUuid : 49535343-FE7D-4AE5-8FA9-9FAFD205E455
         * characteristicRead : 49535343-8841-43F4-A8D4-ECBE34729BB3
         * characteristicWrite : 49535343-1E4D-4BD9-BA61-23C647249616
         * hasDel : 0
         * subTitle : xxxxxx
         * img : https://haide.nbqichen.com/haide/upload/CEC73BA7C3B06E9A55B7982189236DD7.png
         * program : GR
         */
        private int id;


        public ListBean() {
        }

        public ListBean(String name) {
            this.name = name;
        }

        private String parentId;
        private String name;
        private String brandIds;
        private String serviceUuid;
        private String characteristicRead;
        private String characteristicWrite;
        private String hasDel;
        private String subTitle;
        private String img;
        private String program;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrandIds() {
            return brandIds;
        }

        public void setBrandIds(String brandIds) {
            this.brandIds = brandIds;
        }

        public String getServiceUuid() {
            return serviceUuid;
        }

        public void setServiceUuid(String serviceUuid) {
            this.serviceUuid = serviceUuid;
        }

        public String getCharacteristicRead() {
            return characteristicRead;
        }

        public void setCharacteristicRead(String characteristicRead) {
            this.characteristicRead = characteristicRead;
        }

        public String getCharacteristicWrite() {
            return characteristicWrite;
        }

        public void setCharacteristicWrite(String characteristicWrite) {
            this.characteristicWrite = characteristicWrite;
        }

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }
    }
}
