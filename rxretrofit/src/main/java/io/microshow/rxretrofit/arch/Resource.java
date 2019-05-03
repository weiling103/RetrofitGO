package io.microshow.rxretrofit.arch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static io.microshow.rxretrofit.arch.Resource.Status.ERROR;
import static io.microshow.rxretrofit.arch.Resource.Status.LOADING;
import static io.microshow.rxretrofit.arch.Resource.Status.SUCCESS;


public class Resource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    public final int code;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data, int code, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(SUCCESS, data, SUCCESS_CODE, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return error(SUCCESS_CODE, msg, data);
    }

    public static <T> Resource<T> error(int code, String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, code, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, LOADING_CODE, null);
    }

//    public JSONObject toJSONObject() {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("error", code);
//            jsonObject.put("err_msg", message);
//            jsonObject.put("data", new JSONObject(GsonUtil.toJson(data)));
//            JLog.d("Resource", jsonObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }
//
//    public JSONObject toJSONObject(Page page) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("error", code);
//            jsonObject.put("err_msg", message);
//            jsonObject.put("data", new JSONObject(GsonUtil.toJson(page.getData())));
//            jsonObject.put("pageinfo", new JSONObject(GsonUtil.toJson(RxHelper.convert(page))));
//            JLog.d("Resource", jsonObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = -1;
    public static final int LOADING_CODE = -2;

}
