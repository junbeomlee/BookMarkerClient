package com.example.leejunbeom.bookMarker.util.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jun on 16. 3. 16..
 */
public class JsonBuilder_impl implements JsonBuilder {

    @Override
    public JSONObject buildRequestData(JSONObject jsonObject, String code) throws JSONException {
        JSONObject requestData = new JSONObject();
        requestData.put("reqData",jsonObject);
        requestData.put("reqCode",code);
        return requestData;
    }
}
