package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SpecListBaen implements Parcelable {
    /**
     * list : [{"id":1,"levelId":1,"duration":30,"unit":"月","price":20,"realPrice":19.8,"autoPrice":19,"sort":1,"status":1,"levelNo":"00000000000000000001","level":1,"name":"黄金会员","imgUrl":"","explain":"123"},{"id":2,"levelId":3,"duration":30,"unit":"月","price":30,"realPrice":49.8,"autoPrice":49,"sort":1,"status":1,"levelNo":"00000000000000000003","level":3,"name":"铂金会员","imgUrl":"https://haide.nbqichen.com/haide/upload/3DE4354621018751D624F336440D200E.jpg","explain":"1233210"}]
     * totalCount : 0
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

    public static class ListBean implements Parcelable {
        /**
         * id : 1
         * levelId : 1
         * duration : 30
         * unit : 月
         * price : 20
         * realPrice : 19.8
         * autoPrice : 19
         * sort : 1
         * status : 1
         * levelNo : 00000000000000000001
         * level : 1
         * name : 黄金会员
         * imgUrl :
         * explain : 123
         */

        private int id;
        private int levelId;
        private String duration;
        private String unit;
        private String price;
        private String realPrice;
        private String autoPrice;
        private String sort;
        private int status;
        private String levelNo;
        private String level;
        private String name;
        private String imgUrl;
        private String explain;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevelId() {
            return levelId;
        }

        public void setLevelId(int levelId) {
            this.levelId = levelId;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(String realPrice) {
            this.realPrice = realPrice;
        }

        public String getAutoPrice() {
            return autoPrice;
        }

        public void setAutoPrice(String autoPrice) {
            this.autoPrice = autoPrice;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLevelNo() {
            return levelNo;
        }

        public void setLevelNo(String levelNo) {
            this.levelNo = levelNo;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.levelId);
            dest.writeString(this.duration);
            dest.writeString(this.unit);
            dest.writeString(this.price);
            dest.writeString(this.realPrice);
            dest.writeString(this.autoPrice);
            dest.writeString(this.sort);
            dest.writeInt(this.status);
            dest.writeString(this.levelNo);
            dest.writeString(this.level);
            dest.writeString(this.name);
            dest.writeString(this.imgUrl);
            dest.writeString(this.explain);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.id = in.readInt();
            this.levelId = in.readInt();
            this.duration = in.readString();
            this.unit = in.readString();
            this.price = in.readString();
            this.realPrice = in.readString();
            this.autoPrice = in.readString();
            this.sort = in.readString();
            this.status = in.readInt();
            this.levelNo = in.readString();
            this.level = in.readString();
            this.name = in.readString();
            this.imgUrl = in.readString();
            this.explain = in.readString();
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalCount);
        dest.writeTypedList(this.list);
    }

    public SpecListBaen() {
    }

    protected SpecListBaen(Parcel in) {
        this.totalCount = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<SpecListBaen> CREATOR = new Parcelable.Creator<SpecListBaen>() {
        @Override
        public SpecListBaen createFromParcel(Parcel source) {
            return new SpecListBaen(source);
        }

        @Override
        public SpecListBaen[] newArray(int size) {
            return new SpecListBaen[size];
        }
    };
}
