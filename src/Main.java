import common.Log;
import common.Template;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.UserParser;
import service.TemplateService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main {

    public static UserParser userParser = new UserParser();
    public static TemplateService templateService = new TemplateService();
    public static Log log = Log.getInstance();

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("[hello]");
        //1. data read
        FileInputStream fs = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
//        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        StringBuilder dataSb = new StringBuilder();
        String data;
        while((data = br.readLine()) != null){
            dataSb.append(data);
        }

        //2. data parsing

            JSONParser jsonParser = new JSONParser();
            Object parse = jsonParser.parse(dataSb.toString());
            HashMap<String, Object> hashMap= userParser.parse(parse);



    //        ArrayList<User> users = userParser.parse2(dataSb.toString());

            //3. command read
            fs = new FileInputStream("template2.txt");
            br = new BufferedReader(new InputStreamReader(fs));
            ArrayList<Template> templates = new ArrayList<>();
            String command;
            while((command = br.readLine()) != null){
                if("\\n".equals(command)){
                    break;
                }
                templates.add(new Template(command));
            }

            //4. write
            Iterator<String> keys = hashMap.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                Object o = hashMap.get(key);
                for(Template template : templates){
                    templateService.writeData(o, template);
                }
                log.newLine();
            }



            log.close();
            br.close();


    }
}
