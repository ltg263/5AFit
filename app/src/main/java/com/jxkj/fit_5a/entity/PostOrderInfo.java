package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class PostOrderInfo implements Parcelable {

    /**
     * addressId : 0
     * agentId : 0
     * entityList : [{"agentId":0,"groupId":0,"id":0,"productId":0,"quantity":0,"shareFlag":0,"skuId":0,"spikeId":0}]
     * groupId : 0
     * StringegralFlag : 0
     * levelMessage :
     * orderType : 0
     * redId : 0
     * userId : 0
     */

    private String addressId;
    private String agentId;
    private String groupId;
    private String integralFlag;
    private String levelMessage;
    private String balanceFlag;
    private String orderType;
    private String redId;
    private String userId;

    private List<EntityListBean> entityList;

    public String getBalanceFlag() {
        return balanceFlag;
    }

    public void setBalanceFlag(String balanceFlag) {
        this.balanceFlag = balanceFlag;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIntegralFlag() {
        return integralFlag;
    }

    public void setIntegralFlag(String integralFlag) {
        this.integralFlag = integralFlag;
    }

    public String getLevelMessage() {
        return levelMessage;
    }

    public void setLevelMessage(String levelMessage) {
        this.levelMessage = levelMessage;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRedId() {
        return redId;
    }

    public void setRedId(String redId) {
        this.redId = redId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<EntityListBean> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<EntityListBean> entityList) {
        this.entityList = entityList;
    }

    public static class EntityListBean implements Parcelable {
        /**
         * agentId : 0
         * groupId : 0
         * id : 0
         * productId : 0
         * quantity : 0
         * shareFlag : 0
         * skuId : 0
         * spikeId : 0
         */

        private String agentId;
        private String groupId;
        private String id;
        private String productId;
        private String quantity;
        private String shareFlag;
        private String skuId;
        private String spikeId;

        private String cartId;

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getCartId() {
            return cartId;
        }
        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

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

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getShareFlag() {
            return shareFlag;
        }

        public void setShareFlag(String shareFlag) {
            this.shareFlag = shareFlag;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSpikeId() {
            return spikeId;
        }

        public void setSpikeId(String spikeId) {
            this.spikeId = spikeId;
        }

        @Override
        public String toString() {
            return "EntityListBean{" +
                    "agentId='" + agentId + '\'' +
                    ", groupId='" + groupId + '\'' +
                    ", id='" + id + '\'' +
                    ", productId='" + productId + '\'' +
                    ", cartId='" + cartId + '\'' +
                    ", quantity='" + quantity + '\'' +
                    ", shareFlag='" + shareFlag + '\'' +
                    ", skuId='" + skuId + '\'' +
                    ", spikeId='" + spikeId + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.agentId);
            dest.writeString(this.groupId);
            dest.writeString(this.id);
            dest.writeString(this.productId);
            dest.writeString(this.quantity);
            dest.writeString(this.shareFlag);
            dest.writeString(this.skuId);
            dest.writeString(this.spikeId);
            dest.writeString(this.cartId);
        }

        public void readFromParcel(Parcel source) {
            this.agentId = source.readString();
            this.groupId = source.readString();
            this.id = source.readString();
            this.productId = source.readString();
            this.quantity = source.readString();
            this.shareFlag = source.readString();
            this.skuId = source.readString();
            this.spikeId = source.readString();
            this.cartId = source.readString();
        }

        public EntityListBean() {
        }

        protected EntityListBean(Parcel in) {
            this.agentId = in.readString();
            this.groupId = in.readString();
            this.id = in.readString();
            this.productId = in.readString();
            this.quantity = in.readString();
            this.shareFlag = in.readString();
            this.skuId = in.readString();
            this.spikeId = in.readString();
            this.cartId = in.readString();
        }

        public static final Parcelable.Creator<EntityListBean> CREATOR = new Parcelable.Creator<EntityListBean>() {
            @Override
            public EntityListBean createFromParcel(Parcel source) {
                return new EntityListBean(source);
            }

            @Override
            public EntityListBean[] newArray(int size) {
                return new EntityListBean[size];
            }
        };
    }

    @Override
    public String toString() {
        return "PostOrderInfo{" +
                "addressId='" + addressId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", StringegralFlag='" + integralFlag + '\'' +
                ", levelMessage='" + levelMessage + '\'' +
                ", orderType='" + orderType + '\'' +
                ", redId='" + redId + '\'' +
                ", userId='" + userId + '\'' +
                ", entityList=" + entityList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addressId);
        dest.writeString(this.agentId);
        dest.writeString(this.groupId);
        dest.writeString(this.integralFlag);
        dest.writeString(this.levelMessage);
        dest.writeString(this.balanceFlag);
        dest.writeString(this.orderType);
        dest.writeString(this.redId);
        dest.writeString(this.userId);
        dest.writeTypedList(this.entityList);
    }

    public void readFromParcel(Parcel source) {
        this.addressId = source.readString();
        this.agentId = source.readString();
        this.groupId = source.readString();
        this.integralFlag = source.readString();
        this.levelMessage = source.readString();
        this.balanceFlag = source.readString();
        this.orderType = source.readString();
        this.redId = source.readString();
        this.userId = source.readString();
        this.entityList = source.createTypedArrayList(EntityListBean.CREATOR);
    }

    public PostOrderInfo() {
    }

    protected PostOrderInfo(Parcel in) {
        this.addressId = in.readString();
        this.agentId = in.readString();
        this.groupId = in.readString();
        this.integralFlag = in.readString();
        this.levelMessage = in.readString();
        this.balanceFlag = in.readString();
        this.orderType = in.readString();
        this.redId = in.readString();
        this.userId = in.readString();
        this.entityList = in.createTypedArrayList(EntityListBean.CREATOR);
    }

    public static final Parcelable.Creator<PostOrderInfo> CREATOR = new Parcelable.Creator<PostOrderInfo>() {
        @Override
        public PostOrderInfo createFromParcel(Parcel source) {
            return new PostOrderInfo(source);
        }

        @Override
        public PostOrderInfo[] newArray(int size) {
            return new PostOrderInfo[size];
        }
    };
}
