package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    private static Log instance;

    public BufferedWriter bw;

    private Log() {
        try{
            bw = new BufferedWriter(new FileWriter("output.txt"));
        }catch (IOException e){

        }
    }

    public static Log getInstance() {
        if(instance != null){
            return instance;

        }else{
            return instance = new Log();

         }
    }
    public void newLine(){
//        System.out.println();
        try{
            bw.newLine();

        }catch (IOException exception){

        }
    }

    public void write(String str){
//        System.out.print(str);

        try{
            bw.write(str);

        }catch (IOException exception){

        }

    }

    public void close()  {
        try{
            bw.close();

        }catch ( IOException exception){

        }
    }
}
