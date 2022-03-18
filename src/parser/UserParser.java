package parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class UserParser {
    public HashMap<String, Object> parse(Object obj) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {

            if (obj instanceof JSONObject) {
                JSONObject jsonObject = ((JSONObject) obj);
                Iterator<String> keys = jsonObject.keySet().iterator();
                while (keys.hasNext()) {
                    String key = keys.next();
                    if (jsonObject.get(key) instanceof String) {
                        hashMap.put(key, jsonObject.get(key));
                    } else {
                        hashMap.put(key, parse(jsonObject.get(key)));
                    }
                }

            } else if (obj instanceof JSONArray) {
                for (int i = 0; i < ((JSONArray) obj).size(); i++) {
                    hashMap.put(i + "", parse(((JSONArray) obj).get(i)));

                }
            } else if( obj instanceof String){

            }

            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

}
