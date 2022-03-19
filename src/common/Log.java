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

        try{
            System.out.println();
            bw.newLine();

        }catch (IOException exception){

        }
    }

    public void write(String str){

        try{
            System.out.print(str);
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
