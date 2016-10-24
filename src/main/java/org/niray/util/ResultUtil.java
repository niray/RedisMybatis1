package org.niray.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ResultUtil {
    /**
     * 返回ExtJs规定的成功/失败状态
     *
     * @param flag 是否成功
     * @return json格式字符串
     */
    public static String getReturnMsg(final boolean flag) {
        return getReturnMsg(flag, flag ? "成功" : "失败");
    }

    /**
     * 返回ExtJs规定的成功/失败状态
     *
     * @param flag 是否成功
     * @param msg  返回的提示
     * @return json格式字符串
     */
    public static String getReturnMsg(final boolean flag, final String msg) {
        return flag ? "{\"success\":true, \"code\":0, \"msg\":\"" + msg + "\"}" : "{\"success\":false, \"code\":0, \"msg\":\"" + msg + "\"}";
    }

    /**
     * 返回ExtJs规定的成功/失败状态
     *
     * @param flag 是否成功
     * @param msg  返回的提示
     * @return json格式字符串
     */
    public static String getReturnMsg(final boolean flag, final int code, final String msg) {
        return flag ? "{\"success\":true, \"code\":" + code + ", \"msg\":\"" + msg + "\"}" : "{\"success\":false, \"code\":" + code + ", \"msg\":\"" + msg + "\"}";
    }

    public static void main(String args[]) {
        JSONObject _obj = new JSONObject();
        JSONArray arr = new JSONArray();
        _obj.put("type", 1);
        _obj.put("name", "现金");
        _obj.put("color", "#f8c626");
        arr.add(_obj);
        _obj.put("type", 2);
        _obj.put("name", "定期");
        _obj.put("color", "#c6494a");
        arr.add(_obj);
        _obj.put("type", 3);
        _obj.put("name", "股票");
        _obj.put("color", "#fb774c");
        arr.add(_obj);
        _obj.put("type", 4);
        _obj.put("name", "基金");
        _obj.put("color", "#8edfd1");
        arr.add(_obj);
        _obj.put("type", 5);
        _obj.put("name", "私募");
        _obj.put("color", "#69a9e2");
        arr.add(_obj);
        _obj.put("type", 6);
        _obj.put("name", "在途");
        _obj.put("color", "#f18482");
        arr.add(_obj);
        _obj.put("type", 7);
        _obj.put("name", "其他");
        _obj.put("color", "#f8a1e8");
        arr.add(_obj);
        System.out.println(arr.toString());
    }
}
