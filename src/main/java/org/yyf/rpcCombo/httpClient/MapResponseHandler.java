package org.yyf.rpcCombo.httpClient;

import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tobi on 16-8-10.
 */
public class MapResponseHandler implements ResponseHandler<Map> {
    @Override
    public Map handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }
        Map map = JSONObject.parseObject(entity.getContent(), Map.class);
        return map;
    }
}
