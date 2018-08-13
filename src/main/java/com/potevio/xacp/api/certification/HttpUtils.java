package com.potevio.xacp.api.certification;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

    public static String httpPostWithJSON(String url, Object o) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

        StringEntity entity = new StringEntity(JSONObject.toJSONString(o), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        System.out.println(respContent);
        return respContent;
    }

}
