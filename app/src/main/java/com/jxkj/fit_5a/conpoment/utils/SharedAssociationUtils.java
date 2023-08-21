package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.entity.QueryPopularBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者： litongge
 * 时间： 2018/4/24 20:22
 * 邮箱；ltg263@126.com
 * 描述：SharedPreferences 的工具类
 */
public class SharedAssociationUtils {
    private static SharedAssociationUtils sharedUtils;
    public static String SHARED_PREFS_NAME = "SharedAssociationUtils";

    public static SharedAssociationUtils singleton() {
        if (sharedUtils == null) {
            sharedUtils = new SharedAssociationUtils(MainApplication.getContext(), SHARED_PREFS_NAME);
        }
        return sharedUtils;
    }

    private SharedPreferences mSharedPrefs;

    private SharedAssociationUtils(Context context, String sharedPrefsName) {
        mSharedPrefs = context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);
    }

    public void putSharedHistoryEquipment(QueryPopularBean lists) {
        List<QueryPopularBean> listNew = getSharedHistoryEquipment();
        if(listNew==null){
            listNew = new ArrayList<>();
        }
        listNew.add(lists);
        Editor editor = mSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listNew);
        editor.putString("String", json);
        editor.apply();
    }

    /**
     * 删除指定的下标
     * @param pos
     */
    public void updateSharedHistoryEquipmentUpdate(int pos) {
        List<QueryPopularBean> m = getSharedHistoryEquipment();
        m.remove(pos);
        List<QueryPopularBean> ls = new ArrayList<>(m);
        clear();
        for(int i = 0;i<ls.size();i++){
            putSharedHistoryEquipment(ls.get(i));
        }
    }

    /**
     * 删除指定的类型
     */
    public void updateSharedHistoryEquipmentUpdate_Type(int type) {
        List<QueryPopularBean> m = getSharedHistoryEquipment();
        Log.w("updateSharedH","m:"+m);
        if(m==null){
            return;
        }
        for(int i = m.size()-1;i>=0;i--){
            Log.w("updateSharedH","type:"+type);
            Log.w("updateSharedH","getContentType:"+m.get(i).getContentType());
            if(type == Integer.parseInt(m.get(i).getContentType())){
                m.remove(i);
            }
        }
        clear();
        List<QueryPopularBean> ls = new ArrayList<>(m);
        for(int i = 0;i<ls.size();i++){
            putSharedHistoryEquipment(ls.get(i));
        }
    }
    public List<QueryPopularBean> getSharedHistoryEquipment() {
        Gson gson = new Gson();
        String json = mSharedPrefs.getString("String", null);
        Type type = new TypeToken<List<QueryPopularBean>>() {
        }.getType();
        List<QueryPopularBean> arrayList = gson.fromJson(json, type);
        return arrayList;
    }

    public void clear() {
        Editor edit = mSharedPrefs.edit();
        edit.clear();
        edit.commit();
    }

    public void clear(String key) {
        Editor edit = mSharedPrefs.edit();
        edit.remove(key);
        edit.commit();
    }
}
