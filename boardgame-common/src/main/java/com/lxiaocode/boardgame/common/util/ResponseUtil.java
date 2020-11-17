package com.lxiaocode.boardgame.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author lixiaofeng
 * @date 2020/9/6 下午4:56
 * @blog http://www.lxiaocode.com/
 */
public class ResponseUtil {
    public static void send(HttpServletResponse response, Object data){

        JSONObject json = (JSONObject) JSON.toJSON(data);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.append(json.toString());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (out != null){
                out.close();
            }
        }
    }
}
