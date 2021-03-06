package service;

import common.Log;
import entity.Template;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TemplateService {

    public static Log log = Log.getInstance();

    public Object getObject(Object obj, Template template) {
        String cmd = template.cmd;
        String[] split = cmd.split("\\.");

        //기저사례
        if(split.length == 1){
            return obj;
        }

        for(int i = 1;  i< split.length; i++){
            if(obj == null){
                return null;
            }

            if(obj instanceof JSONObject){
                obj = ((JSONObject) obj).getOrDefault(split[i], null);
            }else if(obj instanceof JSONArray) {
                if (((JSONArray) obj).size() == 0) {
                    obj = null;
                } else {
                    obj = ((JSONArray) obj).get(Integer.parseInt(split[i]));
                }
            }
        }
        return obj;
    }

    public void writeData(JSONObject obj, Template template) {

        log.write(template.prefix + ": ");
        for(String cmd : template.cmds){
            String[] split = cmd.split("\\.");
            Object obj2 = obj;

            //기저사례
            if(split.length == 1 && obj2 instanceof String){
                log.write(((String) obj2)+" ");
                continue;
            }

            for(int i = 1; i< split.length; i++){

                if(obj2 instanceof JSONObject){
                    obj2 = ((JSONObject) obj2).getOrDefault(split[i], "? ");
                }else if(obj2 instanceof JSONArray) {
                    if (((JSONArray) obj2).size() == 0) {
                        obj2 = "?";
                    } else {
                        obj2 = ((JSONArray) obj2).get(Integer.parseInt(split[i]));
                    }


                }

                if(obj2 instanceof String){
                    log.write(((String) obj2)+" ");
                    break;
                }

            }

        }
        log.newLine();

    }

}
