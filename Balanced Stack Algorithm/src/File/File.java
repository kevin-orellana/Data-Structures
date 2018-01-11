/**
 * Created by kfo21 on 6/7/2017.
 */

package File;

import java.io.*;
import java.util.Scanner;

public class File {

    String file_number;
    String file_name = "";
    Scanner sc = new Scanner(System.in);
    FileReader fReader;
    BufferedReader bReader;

    public File() {
        this.openFile();
    }

    public String readData(){
        try {
            bReader = new BufferedReader(fReader);
            return bReader.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "empty string from readData()";
    }

    public void openFile(){
        try {

            file_number = sc.nextLine();
            file_name = "resc/hw1_set" + file_number + ".txt";

            System.out.println("Opening file: " + file_name + "...");
            fReader = new FileReader(file_name);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString(){
        return this.file_name;
    }

}
