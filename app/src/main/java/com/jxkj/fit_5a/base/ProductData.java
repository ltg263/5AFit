package com.jxkj.fit_5a.base;

/**
 * author : LiuJie
 * date   : 2020/6/311:23
 */
public class ProductData {
    /**
     id	规格详情Id	int(11)
     parentId	上级Id	int(11)
     name	名称	String
     className	类别名称	Integer
     intro	商品介绍	String
     productType	商品类型： 1.现金商城 ;2.积分商城;	Byte
     point	兑换积分值	Integer
     hasSku	是否有规格：0,否; 1,是;	Byte
     marketPrice	市场价	Becimal
     costPrice	成本价	Becimal
     sellPrice	售价	Becimal
     amount	库存	Integer
     status	状态:1,正常2.已下架3.未上架	Byte
     salesVolume	销量
     */
    private int amount;
    private int brandId;
    private int classId;
    private String id;
    private String hasSku = "0";
    private int quantity = 1;
    private int popularity;
    private int productType;
    private String costPrice;
    private String salesVolume;
    private String sellPrice;
    private String productName;
    private String marketPrice;
    private String message;
    private String imgUrl;
    private String intro;
    private String point;
//    private List<SkuListData> skuList = new ArrayList<>();
    private boolean selse = false;
    private String specId = "";
    private String specText;

    public String getHasSku() {
        return hasSku;
    }

    public void setHasSku(String hasSku) {
        this.hasSku = hasSku;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecText() {
        return specText;
    }

    public void setSpecText(String specText) {
        this.specText = specText;
    }

    public boolean isSelse() {
        return selse;
    }

    public void setSelse(boolean selse) {
        this.selse = selse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
//
//    public List<SkuListData> getSkuList() {
//        return skuList;
//    }
//
//    public void setSkuList(List<SkuListData> skuList) {
//        this.skuList = skuList;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public String getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(String salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
