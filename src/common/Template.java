package common;

import enums.TemplateType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {
    public String prefix;
    public List<String> cmds;
    public TemplateType type;
    public String cmd;

    public Template(String cmd){
        if(cmd.contains(":")){
            String[] split = cmd.split(":");

            this.prefix = split[0];
            cmds = allMathes(split[1]);
            type = TemplateType.NORMAL;
        }else{

            //<? endfor ?>
            if(cmd.startsWith("<? endfor ?>")){
                type= TemplateType.END;

            //<? for ADDR in USER.info.addrs ?>
            }else if(cmd.startsWith("<? for")){

                this.cmd = forMathe(cmd);
                type= TemplateType.FOR;

            }
        }
    }

    public String forMathe(String s){
        String matche = null;
        Matcher m = Pattern.compile("<\\?\\s*for.*?in(.*)?\\?>")
                .matcher(s);
        if (m.find()) {
            matche = m.group(1)
                    .trim();


        }

        return matche;
    }

    /**
     * Name: <?=USER.info.name.given ?> <?=USER.info.name.family?>\n
     * @param s
     * @return : [ "USER.info.name.given", "USER.info.name.family", ...]
     */
    public List<String> allMathes(String s){
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("<\\?=.*?\\?>")
                .matcher(s);
        while (m.find()) {
            String s1 = m.group()
                    .replace("<?=", "")
                    .replace("?>","")
                    .trim();

            allMatches.add(s1);
        }

        return allMatches;
    }

}
