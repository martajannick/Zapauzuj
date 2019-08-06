package de.VocabularySummary.VocSummary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.*;
import java.lang.String;

public class Vocabulary {

    private String[] copyText(String Input){

        FileInputStream instream;
        FileOutputStream outstream;

        try {
            File infile = new File(Input);
            File outfile = new File("Working.txt");

            instream = new FileInputStream(infile);
            outstream = new FileOutputStream(outfile);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }

            //Closing the input/output file streams
            instream.close();
            outstream.close();

            // System.out.println("File copied successfully!!");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private String replaceOne() {

        try {
            String oneString = new String(Files.readAllBytes(Paths.get("Working.txt")), "UTF-8");
            String twoString = oneString.replaceAll(" <(.*?)>", "");

            //   System.out.println(twoString);
            return twoString;
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    private String[] stringToArray(String temp){

        String[] oneLines = temp.split("\\n");

        int k = 0;
        int len = oneLines.length;
        String[] twoLines = new String[len];
        String[] threeLines = new String[len];

        for(int i=0; i < len; i++) {
            if (oneLines[i].contains("UpponSite")) {
                twoLines[i-1]= "";
                k++;
                twoLines[i] = "";
                k++;
            }else{
                twoLines[i] = oneLines[i];
            }
        }

        for(int i=0; i < len; i++) {
            if ((twoLines[i].length()==1)) {
                threeLines[i] = "";
                //      System.out.println("remove: "+twoLines[i]);
                k++;
            }else{
                threeLines[i] = twoLines[i];
            }
        }

        String[] fourLines = new String[len-k];

        int j =0;
        for(int i=0; i < len; i++) {
            if (threeLines[i] == "") {

            }else{
                fourLines[j] = twoLines[i];
                j++;
            }
        }
        return fourLines;
    }

    private String[][][] expliciteArray(String[] fourLines){

        int lenFour = fourLines.length;
        int lenEl = 0;
        for(int i=0;i<lenFour;i++){
            if(fourLines[i].contains("---")) {
                lenEl++;
                //        System.out.println(fourLines[i]);
            }
        }

        System.out.println("lenEl:    "+lenEl);
        System.out.println("lenfour : "+lenFour);
        //   System.out.println("lenEl: ");

        String[] fiveLines = new String[lenFour]; //za duzo
        String[] aEl = new String[lenEl];
        String[] bEl = new String[lenEl];
        String[] cEl = new String[lenEl];
        int j=0;
        int k=0;
        //   fiveLines[0]="";
        //   fiveLines[1]="";


        for(int i=0;i<lenFour;i++){
            if(fourLines[i].contains("---")){
                j++;
                aEl[j-1]=fourLines[i];
                bEl[j-1]="";
            }else{
                bEl[j-1]=bEl[j-1].concat(" \n"+fourLines[i]);  //maybe n
            }
        }



        System.out.println(aEl[200]+bEl[200]);
        for(int i=0;i<lenFour;i++){
            //   System.out.println( (i+1)+"  "+fourLines[i]);
            //     System.out.println(fourLines[i]);

        }
        return null;
    }



    public static void main(String[] args ){
        Vocabulary myOb = new Vocabulary();

        myOb.copyText("Inputs/A1.txt"); //as an argument put the txtfile with path
        myOb.stringToArray(myOb.replaceOne());
        myOb.expliciteArray(myOb.stringToArray(myOb.replaceOne()));
    }


}
