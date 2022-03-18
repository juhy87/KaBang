package parser;

import entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
//    public ArrayList<User> parse2(String s){
//        JSONParser jsonParser = new JSONParser();
//        ArrayList<User> users = new ArrayList<>();
//        try{
//
//            JSONArray jsonArray = (JSONArray) jsonParser.parse(s);
//            for(int i=0;i< jsonArray.size();i++){
//                JSONObject jsonObj = (JSONObject)jsonArray.get(i);
//                users.add(new User(jsonObj));
//            }
//
//        }catch (ParseException e){
//            return null;
//        }
//        return users;
//    }
}
