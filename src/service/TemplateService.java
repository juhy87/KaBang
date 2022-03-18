package service;

import common.Log;
import common.Template;

import java.util.HashMap;

public class TemplateService {

    public static Log log = Log.getInstance();

    public void writeData(Object obj, Template template){

        log.write(template.prefix + ": ");
        for(String cmd : template.cmds){
            String[] split = cmd.split("\\.");
            Object obj2 = obj;

            for(int i = 1; i< split.length; i++){
                if(obj2 == null){
                    log.write("? ");
                    break;
                }

                obj2 = ((HashMap<String, Object>) obj2).get(split[i]);
                if(obj2 instanceof String){
                    log.write((String) obj2+" ");
                }

            }

        }
        log.newLine();

    }
}
