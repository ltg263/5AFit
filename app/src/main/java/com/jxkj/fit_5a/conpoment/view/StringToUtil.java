package com.jxkj.fit_5a.conpoment.view;

import com.jxkj.fit_5a.entity.AdListData;

import java.util.List;

public class StringToUtil {
        public static String getStringAll(List<AdListData.ListBean> list){
        String result="";
        for(int i=0;i<list.size();i++){
            result=result+list.get(i).getTitle()+"      ";
        }
        return result;
    }
    public static String getString(List<AdListData.ListBean> list){
        String result="";
        if(list.size()>0){
            result=result+list.get(0).getTitle();
        }
        return result;
    }

}
