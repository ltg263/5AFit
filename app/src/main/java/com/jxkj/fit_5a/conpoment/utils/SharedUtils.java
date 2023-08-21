package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;

/**
 * 作者： litongge
 * 时间： 2018/4/24 20:22
 * 邮箱；ltg263@126.com
 * 描述：SharedPreferences 的工具类
 */
public class SharedUtils {
	private static SharedUtils sharedUtils;
	public static String SHARED_PREFS_NAME = ConstValues.APPNAME_ENGLISH;

	public static SharedUtils singleton() {
		if (sharedUtils == null) {
			sharedUtils = new SharedUtils(MainApplication.getContext(), SHARED_PREFS_NAME);
		}
		return sharedUtils;
	}
	
	private SharedPreferences mSharedPrefs;

	private SharedUtils(Context context, String sharedPrefsName) {
		mSharedPrefs = context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);
	}

	public <T> void put(String key, T value) {
		put(new String[] { key }, new Object[] { value });
	}

	public <T> void put(String[] keys, T[] values) {
		if (values != null && keys != null) {
			Editor edit = mSharedPrefs.edit();
			for (int i = 0; i < values.length; i++) {
				T value = values[i];
				int index = i;
				if (index >= keys.length) {
					index = keys.length - 1;
				}
				String key = keys[index];
				Class<? extends Object> cls = value.getClass();
				if (cls == Integer.class || cls == int.class) {
					edit.putInt(key, (Integer) value);
				} else if (cls == Boolean.class || cls == boolean.class) {
					edit.putBoolean(key, (Boolean) value);
				} else if (cls == Float.class || cls == float.class) {
					edit.putFloat(key, (Float) value);
				} else if (cls == Long.class || cls == long.class) {
					edit.putLong(key, (Long) value);
				} else if (cls == String.class) {
					edit.putString(key, (String) value);
				}
			}
			edit.commit();
		}
	}

	public void clear() {
		Editor edit = mSharedPrefs.edit();
		edit.clear();
		edit.commit();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, T defValue) {
		Object result = null;
		if (defValue != null && key != null) {
			Class<? extends Object> cls = defValue.getClass();
			if (cls == Integer.class || cls == int.class) {
				result = mSharedPrefs.getInt(key, (Integer) defValue);
			} else if (cls == Boolean.class || cls == boolean.class) {
				result = mSharedPrefs.getBoolean(key, (Boolean) defValue);
			} else if (cls == Float.class || cls == float.class) {
				result = mSharedPrefs.getFloat(key, (Float) defValue);
			} else if (cls == Long.class || cls == long.class) {
				result = mSharedPrefs.getLong(key, (Long) defValue);
			} else if (cls == String.class) {
				result = mSharedPrefs.getString(key, (String) defValue);
			}
		}
		return (T) result;
	}

	public void clear(String key) {
		Editor edit = mSharedPrefs.edit();
		edit.remove(key);
		edit.commit();
	}
	public static String getToken(){
		Log.w("token","--->>:"+singleton().get(ConstValues.TOKEN,""));
		return singleton().get(ConstValues.TOKEN,"");
//		return "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHQiOiJ7XCJyZWxhdGlvblR5cGVcIjowLFwicmVsYXRpb25JZFwiOjAsXCJzeXNVc2VySWRcIjoxMDV9IiwidXNlckluZm8iOiJ7XCJhZ2VcIjozMSxcImF2YXRhclwiOlwiaHR0cHM6Ly81YS1maXQtb3NzLm5icWljaGVuLmNvbS91c2VyLzJmbVMzejBwMjlXbXd0d1h6cFVBLmpwZ1wiLFwiYmlydGhkYXlcIjo2MzExMjMyMDAwMDAsXCJjbGllbnRUeXBlXCI6NCxcImNyZWF0ZVRpbWVcIjoxNjE3ODcyMzA3MDAwLFwiZ2VuZGVyXCI6MSxcImhlaWdodFwiOjE2Mi4wMCxcImlkXCI6MTQ1LFwibGV2ZWxcIjowLFwibGV2ZWxOb1wiOlwiXCIsXCJsb2dpblR5cGVcIjowLFwibmlja05hbWVcIjpcIuivt-S_ruaUueaYteensF8xNTE2Njc3ODgwMFwiLFwib3BlbklkXCI6XCJvN3hHTTVlaV9wWTIycEpmYW5PZ21PQ2NMOGxvXCIsXCJzaWduXCI6XCJcIixcInN0YXR1c1wiOjEsXCJ1aW9uSWRcIjpcIm9kTE5mNm5HLWZod29zbmVNTk93al9mU2ZSeXNcIixcInVzZXJOb1wiOlwiMTUxNjY3Nzg4MDBcIixcIndlaWdodFwiOjY1LjQwfSIsInVzZXJfbmFtZSI6IntcInVzZXJuYW1lVHlwZVwiOjMsXCJ1c2VybmFtZVwiOlwib2RMTmY2bkctZmh3b3NuZU1OT3dqX2ZTZlJ5c1wifSIsInNjb3BlIjpbInJlYWQiXSwiZXhwIjoxOTMzODI3NjA0LCJqdGkiOiJfSFA4UUlIS3FNRUNzVWFQeU9KT3lWUFBRODgiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.b4VXPIc_L7SFxQIPSC6OHVQIYrUSAQ64GEMVqvoAicg";
	}
	public static int getUserId(){
		return singleton().get(ConstValues.USERID,0);
	}

}
