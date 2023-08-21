package com.jxkj.fit_5a.entity;

import java.util.List;

public class AddressModel {

    /**
     * list : [{"id":6,"userId":0,"provinceId":330000,"cityId":330200,"districtId":330203,"regions":"浙江省宁波市海曙区","location":"Sdfsfsdf steward as ","acceptName":"fff","mobile":"17621682180","isDefult":1,"hasDel":0,"createTime":"2020-12-31 11:58:43"},{"id":5,"userId":1,"provinceId":330000,"cityId":330300,"districtId":330302,"regions":"浙江省 温州市 鹿城区","location":"鄞州信息科技孵化园408-409-410-411-412-412","acceptName":"狗不是真的人","mobile":"15168531988","isDefult":0,"hasDel":0,"createTime":"2020-09-24 10:11:48"},{"id":4,"userId":1,"provinceId":330000,"cityId":330100,"districtId":330102,"regions":"浙江省 杭州市 上城区","location":"鄞州信息科技孵化园408","acceptName":"呆2","mobile":"15167131988","isDefult":1,"hasDel":0,"createTime":"2020-09-24 10:08:41"}]
     * totalCount : 3
     */

    private String totalCount;
    private List<AddressData> list;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<AddressData> getList() {
        return list;
    }

    public void setList(List<AddressData> list) {
        this.list = list;
    }
}
