package com.jxkj.fit_5a.entity;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

public class ProductDetailsBean {

    /**
     * id : 3
     * parentTypeid : 1
     * typeId : 3
     * type : 2
     * name : 海德肌肉贴KT06
     * subTitle : HEAD海德肌肉贴，有效支撑和稳定肌肉、韧带、关节等组织，刺激机体缓解运动损伤。
     * imgUrl : https://haide.nbqichen.com/haide/upload/E8EDCB9DBF4027818B31AC4165189C04.jpg
     * price : 150
     * disPrice : 300
     * deductIntegral : 50
     * stock : 22
     * browseTotal : 0
     * saleTotal : 0
     * commentTotal : 1
     * score : 0
     * hasHot : 1
     * hasNew : 0
     * hasSku : 1
     * hasDel : 0
     * status : 0
     * createTime : 2020-09-21 17:07:36
     * skuList : [{"id":8,"productId":3,"specIds":"18,20","specText":"颜色:黑色;容量:20ml","imgUrl":"https://haide.nbqichen.com/haide/upload/887C27F0CA135ADCF20EF8686D1162D4.png","name":"黑色 20ml","type":0,"price":100,"disPrice":150,"deductIntegral":120,"stock":11,"saleTotal":0,"commentCount":0,"hasDel":0,"status":1,"createTime":"2020-09-23 16:24:11","parentSpecids":"1,16"},{"id":3,"productId":3,"specIds":"14,20","specText":"颜色:浅蓝;容量:20ml","imgUrl":"https://haide.nbqichen.com/haide/upload/A983D95171DD78697704F798854A2D63.png","name":"浅蓝 20ml","type":0,"price":80,"disPrice":100,"deductIntegral":100,"stock":0,"saleTotal":0,"commentCount":0,"hasDel":0,"status":1,"createTime":"2020-09-21 17:19:17","parentSpecids":"1,16"},{"id":4,"productId":3,"specIds":"17,19","specText":"颜色:橘色;容量:10ml","imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"橘色 10ml","type":0,"price":160,"disPrice":200,"deductIntegral":200,"stock":11,"saleTotal":0,"commentCount":0,"hasDel":0,"status":1,"createTime":"2020-09-21 17:19:17","parentSpecids":"1,16"}]
     * productDetailDTO : null
     * specsLis : [{"id":1,"typeId":0,"parentId":0,"name":"颜色","hasDel":0,"children":[{"id":18,"typeId":0,"parentId":1,"name":"黑色","hasDel":0,"children":[]},{"id":17,"typeId":0,"parentId":1,"name":"橘色","hasDel":0,"children":[]},{"id":14,"typeId":0,"parentId":1,"name":"浅蓝","hasDel":0,"children":[]},{"id":7,"typeId":0,"parentId":1,"name":"紫","hasDel":0,"children":[]},{"id":6,"typeId":0,"parentId":1,"name":"白","hasDel":0,"children":[]}]},{"id":16,"typeId":0,"parentId":0,"name":"容量","hasDel":0,"children":[{"id":20,"typeId":0,"parentId":16,"name":"20ml","hasDel":0,"children":[]},{"id":19,"typeId":0,"parentId":16,"name":"10ml","hasDel":0,"children":[]}]}]
     * details : <p><img src="https://haide.nbqichen.com/haide/upload/BB1C4C8583C6CDCA5106018D0EC7E551.jpg"></p>
     * imgs : https://haide.nbqichen.com/haide/upload/83B6F11810C0DC88540741179EB131A2.jpg,https://haide.nbqichen.com/haide/upload/DB84B988277742D0E3CEC3C0D264D4C0.jpg,https://haide.nbqichen.com/haide/upload/03E8D7935EB1D3CB699B354B91E9A9B3.jpg
     * commentList : [{"id":1,"userId":1,"orderId":20,"productId":3,"skuId":0,"skuText":"","score":100,"detail":"121233","imgs":"https://haide.nbqichen.com/haide/upload/83B6F11810C0DC88540741179EB131A2.jpg","status":1,"createTime":"2020-09-27 10:59:28","serviceScore":0,"deliveryScore":0,"nickName":"","avatar":""}]
     */
    private int collectFlag;
    private String id;
    private String parentTypeid;
    private String typeId;
    private String type;
    private String name;
    private String subTitle;
    private String imgUrl;
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
    private Object productDetailDTO;
    private String details;
    private String imgs;
    private List<SkuListBean> skuList;
    private List<SpecsLisBean> specsLis;
    private List<CommentListBean.ListBean> commentList;

    public void setCollectFlag(int collectFlag) {
        this.collectFlag = collectFlag;
    }

    public int getCollectFlag() {
        return collectFlag;
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

    public Object getProductDetailDTO() {
        return productDetailDTO;
    }

    public void setProductDetailDTO(Object productDetailDTO) {
        this.productDetailDTO = productDetailDTO;
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

    public List<SkuListBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
        this.skuList = skuList;
    }

    public List<SpecsLisBean> getSpecsLis() {
        return specsLis;
    }

    public void setSpecsLis(List<SpecsLisBean> specsLis) {
        this.specsLis = specsLis;
    }

    public List<CommentListBean.ListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean.ListBean> commentList) {
        this.commentList = commentList;
    }

    public static class SkuListBean {
        /**
         * id : 8
         * productId : 3
         * specIds : 18,20
         * specText : 颜色:黑色;容量:20ml
         * imgUrl : https://haide.nbqichen.com/haide/upload/887C27F0CA135ADCF20EF8686D1162D4.png
         * name : 黑色 20ml
         * type : 0
         * price : 100
         * disPrice : 150
         * deductIntegral : 120
         * stock : 11
         * saleTotal : 0
         * commentCount : 0
         * hasDel : 0
         * status : 1
         * createTime : 2020-09-23 16:24:11
         * parentSpecids : 1,16
         */

        private String id;
        private String productId;
        private String specIds;
        private String specText;
        private String imgUrl;
        private String name;
        private String type;
        private String price;
        private String disPrice;
        private String deductIntegral;
        private String stock;
        private String saleTotal;
        private String commentCount;
        private String hasDel;
        private String status;
        private String createTime;
        private String parentSpecids;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getSpecIds() {
            return specIds;
        }

        public void setSpecIds(String specIds) {
            this.specIds = specIds;
        }

        public String getSpecText() {
            return specText;
        }

        public void setSpecText(String specText) {
            this.specText = specText;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getSaleTotal() {
            return saleTotal;
        }

        public void setSaleTotal(String saleTotal) {
            this.saleTotal = saleTotal;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
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

        public String getParentSpecids() {
            return parentSpecids;
        }

        public void setParentSpecids(String parentSpecids) {
            this.parentSpecids = parentSpecids;
        }
    }

    public static class SpecsLisBean {
        /**
         * id : 1
         * typeId : 0
         * parentId : 0
         * name : 颜色
         * hasDel : 0
         * children : [{"id":18,"typeId":0,"parentId":1,"name":"黑色","hasDel":0,"children":[]},{"id":17,"typeId":0,"parentId":1,"name":"橘色","hasDel":0,"children":[]},{"id":14,"typeId":0,"parentId":1,"name":"浅蓝","hasDel":0,"children":[]},{"id":7,"typeId":0,"parentId":1,"name":"紫","hasDel":0,"children":[]},{"id":6,"typeId":0,"parentId":1,"name":"白","hasDel":0,"children":[]}]
         */

        private String id;
        private String typeId;
        private String parentId;
        private String name;
        private String hasDel;
        private List<ChildrenBean> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
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

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 18
             * typeId : 0
             * parentId : 1
             * name : 黑色
             * hasDel : 0
             * children : []
             */

            private String id;
            private String typeId;
            private String parentId;
            private String name;
            private String hasDel;
            private List<?> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
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

            public String getHasDel() {
                return hasDel;
            }

            public void setHasDel(String hasDel) {
                this.hasDel = hasDel;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }

}
