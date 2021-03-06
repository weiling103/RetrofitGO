package io.microshow.retrofitgo.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import io.microshow.retrofitgo.RetrofitClient;

/**
 * sp 缓存数据
 * Created by Super on 2019/12/25.
 */
public class CacheSpUtils {

    public static final String PREFS_NAME = "RETROFITGO_CACHE_DATAS";

    public static SharedPreferences getSharedPreferences() {
        return RetrofitClient.getInstance().getContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static <T> void saveCacheData(String cacheKey, T model) {
        if (!TextUtils.isEmpty(cacheKey) && model != null) {
            SharedPreferences mSharedPreferences = getSharedPreferences();
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString(cacheKey, new Gson().toJson(model));
            edit.commit();
        }
    }

    public static <T> T getCacheData(String cacheKey, Type typeOfT) {
        if (!TextUtils.isEmpty(cacheKey)) {
            String data = getSharedPreferences().getString(cacheKey, null);
            return !TextUtils.isEmpty(data) ? new Gson().fromJson(data, typeOfT) : null;
        } else {
            return null;
        }
    }

}
