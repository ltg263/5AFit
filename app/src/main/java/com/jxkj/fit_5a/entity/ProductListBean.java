package com.jxkj.fit_5a.entity;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

public class ProductListBean {

    /**
     * list : [{"id":10,"parentTypeid":1,"typeId":2,"type":1,"name":"运动手环","subTitle":"123123","imgUrl":"https://haide.nbqichen.com/haide/upload/4DA914C1D0D291E19C222646443ACDD7.png","price":130,"disPrice":1200,"deductIntegral":130,"stock":10,"browseTotal":0,"saleTotal":0,"commentTotal":0,"score":0,"hasHot":0,"hasNew":0,"hasSku":0,"hasDel":0,"status":0,"createTime":"2020-09-28 10:16:48","skuList":[],"productDetailDTO":null,"specsLis":[],"details":"","imgs":"","commentList":[]},{"id":9,"parentTypeid":1,"typeId":2,"type":0,"name":"海德椭圆机E130","subTitle":"HEAD海德椭圆机，静音磁控不扰民，平衡脚垫稳固提高运动成绩。","imgUrl":"https://haide.nbqichen.com/haide/upload/CD7CDFF7FB7C0BC5579AD6CEF6C285B4.jpg","price":10,"disPrice":12,"deductIntegral":100,"stock":75,"browseTotal":0,"saleTotal":0,"commentTotal":0,"score":0,"hasHot":0,"hasNew":1,"hasSku":0,"hasDel":0,"status":0,"createTime":"2020-09-22 17:11:27","skuList":[],"productDetailDTO":null,"specsLis":[],"details":"","imgs":"","commentList":[]},{"id":5,"parentTypeid":1,"typeId":2,"type":2,"name":"海德动感单车S330","subTitle":"HEAD海德动感单车，智能互动高效燃脂，多种模式动感骑行。","imgUrl":"https://haide.nbqichen.com/haide/upload/114CF5D26DDD42F64648B9FAD2B46BF7.jpg","price":80,"disPrice":100,"deductIntegral":80,"stock":90,"browseTotal":0,"saleTotal":0,"commentTotal":0,"score":0,"hasHot":0,"hasNew":1,"hasSku":0,"hasDel":0,"status":1,"createTime":"2020-09-21 17:20:41","skuList":[],"productDetailDTO":null,"specsLis":[],"details":"","imgs":"","commentList":[]},{"id":4,"parentTypeid":1,"typeId":2,"type":2,"name":"海德智能健腹轮","subTitle":"欧洲同款!黑科技\"海德芯\" 科学健身快速出肌 。","imgUrl":"https://haide.nbqichen.com/haide/upload/D8842A55803A0A4C1CA5FE5D3160013D.jpg","price":80,"disPrice":100,"deductIntegral":10,"stock":100,"browseTotal":0,"saleTotal":0,"commentTotal":0,"score":0,"hasHot":1,"hasNew":0,"hasSku":1,"hasDel":0,"status":0,"createTime":"2020-09-21 17:19:17","skuList":[],"productDetailDTO":null,"specsLis":[],"details":"","imgs":"","commentList":[]},{"id":3,"parentTypeid":1,"typeId":3,"type":2,"name":"海德肌肉贴KT06","subTitle":"HEAD海德肌肉贴，有效支撑和稳定肌肉、韧带、关节等组织，刺激机体缓解运动损伤。","imgUrl":"https://haide.nbqichen.com/haide/upload/E8EDCB9DBF4027818B31AC4165189C04.jpg","price":150,"disPrice":300,"deductIntegral":50,"stock":22,"browseTotal":0,"saleTotal":0,"commentTotal":1,"score":0,"hasHot":1,"hasNew":0,"hasSku":1,"hasDel":0,"status":0,"createTime":"2020-09-21 17:07:36","skuList":[],"productDetailDTO":null,"specsLis":[],"details":"","imgs":"","commentList":[]}]
     * totalCount : 5
     */

    private String totalCount;
    private List<ListBean> list;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
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
         * id : 10
         * parentTypeid : 1
         * typeId : 2
         * type : 1
         * name : 运动手环
         * subTitle : 123123
         * imgUrl : https://haide.nbqichen.com/haide/upload/4DA914C1D0D291E19C222646443ACDD7.png
         * price : 130
         * disPrice : 1200
         * deductIntegral : 130
         * stock : 10
         * browseTotal : 0
         * saleTotal : 0
         * commentTotal : 0
         * score : 0
         * hasHot : 0
         * hasNew : 0
         * hasSku : 0
         * hasDel : 0
         * status : 0
         * createTime : 2020-09-28 10:16:48
         * skuList : []
         * productDetailDTO : null
         * specsLis : []
         * details :
         * imgs :
         * commentList : []
         */

        private String id;
        private String parentTypeid;
        private String typeId;
        private String type;
        private String name;
        private String subTitle;
        private String imgUrl;
        private String showImgUrl;
        private String price;
        private String disPrice;
        private String deductIntegral;
        private String stock;
        private String browseTotal;
        private String saleTotal;
        private String commentTotal;
        private String score;
        private String hasHot;
        private String hasNew;
        private String hasSku;
        private String hasDel;
        private String status;
        private String createTime;
        private String details;
        private String imgs;
        private List<?> skuList;
        private List<?> specsLis;
        private List<?> commentList;

        public void setShowImgUrl(String showImgUrl) {
            this.showImgUrl = showImgUrl;
        }

        public String getShowImgUrl() {
            return showImgUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentTypeid() {
            return parentTypeid;
        }

        public void setParentTypeid(String parentTypeid) {
            this.parentTypeid = parentTypeid;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPrice() {
            return StringUtil.getValue(price);
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDisPrice() {
            return disPrice;
        }

        public void setDisPrice(String disPrice) {
            this.disPrice = disPrice;
        }

        public String getDeductIntegral() {
            return StringUtil.getValue(deductIntegral);
        }

        public void setDeductIntegral(String deductIntegral) {
            this.deductIntegral = deductIntegral;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getBrowseTotal() {
            return browseTotal;
        }

        public void setBrowseTotal(String browseTotal) {
            this.browseTotal = browseTotal;
        }

        public String getSaleTotal() {
            return saleTotal;
        }

        public void setSaleTotal(String saleTotal) {
            this.saleTotal = saleTotal;
        }

        public String getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(String commentTotal) {
            this.commentTotal = commentTotal;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getHasHot() {
            return hasHot;
        }

        public void setHasHot(String hasHot) {
            this.hasHot = hasHot;
        }

        public String getHasNew() {
            return hasNew;
        }

        public void setHasNew(String hasNew) {
            this.hasNew = hasNew;
        }

        public String getHasSku() {
            return hasSku;
        }

        public void setHasSku(String hasSku) {
            this.hasSku = hasSku;
        }

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public List<?> getSkuList() {
            return skuList;
        }

        public void setSkuList(List<?> skuList) {
            this.skuList = skuList;
        }

        public List<?> getSpecsLis() {
            return specsLis;
        }

        public void setSpecsLis(List<?> specsLis) {
            this.specsLis = specsLis;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<?> commentList) {
            this.commentList = commentList;
        }
    }
}
