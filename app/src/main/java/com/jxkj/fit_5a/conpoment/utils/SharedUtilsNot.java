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
public class SharedUtilsNot {
	private static SharedUtilsNot sharedUtils;

	public static SharedUtilsNot singletonNotClear() {
		if (sharedUtils == null) {
			sharedUtils = new SharedUtilsNot(MainApplication.getContext(), "5AFit_not");
		}
		return sharedUtils;
	}

	private SharedPreferences mSharedPrefs;

	private SharedUtilsNot(Context context, String sharedPrefsName) {
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
