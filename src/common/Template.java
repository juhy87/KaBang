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


                type= TemplateType.FOR;

            }
        }
    }

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
