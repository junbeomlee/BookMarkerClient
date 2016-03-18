package com.example.leejunbeom.bookMarker.util.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jun on 16. 3. 16..
 */
public interface JsonBuilder {
     JSONObject  buildRequestData(JSONObject jsonObject, String s) throws JSONException;
}
