import common.Log;
import entity.Template;
import enums.TemplateType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.TemplateService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static TemplateService templateService = new TemplateService();
    public static Log log = Log.getInstance();

    public static void main(String[] args) {

        String inputFilePath = "input2.txt";
        String templateFilePath = "template2_3.txt";
        if(args.length == 2){
            inputFilePath = args[0];
            templateFilePath = args[1];
        }

        try{
            //1. data read
            FileInputStream fs = new FileInputStream(inputFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            StringBuilder dataSb = new StringBuilder();
            String data;
            while((data = br.readLine()) != null){
                dataSb.append(data);
            }

            //2. data parsing
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(dataSb.toString());

            //3. template File read
            fs = new FileInputStream(templateFilePath);
            br = new BufferedReader(new InputStreamReader(fs));
            ArrayList<Template> templates = new ArrayList<>();
            String command;
            while((command = br.readLine()) != null){
                if("\\n".equals(command)){
                    break;
                }
                templates.add(new Template(command));
            }

            //4. outputFile.txt write
            for(int i = 0 ; i < jsonArray.size(); i++){
                Object obj1 = jsonArray.get(i);
                for(Template template : templates){

                    if(template.type == TemplateType.FOR){
                        obj1 = templateService.getObject(obj1, template);
                    }else if(template.type == TemplateType.END){
                        obj1 = jsonArray.get(i);
                    }else{
                        if(obj1 instanceof JSONObject){
                            templateService.writeData((JSONObject) obj1, template);
                        }else if(obj1 instanceof  JSONArray){
                            for(int j = 0 ; j < ((JSONArray)obj1).size(); j++ ){
                                Object obj2  = ((JSONArray) obj1).get(j);
                                templateService.writeData((JSONObject) obj2, template);
                            }

                        }
                    }
                }
                log.newLine();
            }

            log.close();
            br.close();

        }catch (ParseException e){
            System.out.println("Input Json Parsing Fail");
        }catch (IOException e){
            System.out.println("File IO Fail");
        }

    }
}
