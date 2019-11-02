// Varun Chaudhari
// UCF ID: 4831096

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateCounter
{
   private Map<String, Integer> wordCounter;

   public DuplicateCounter(){
       wordCounter = new HashMap<String,Integer>();
   }

   public void count(String filename){
       //System.out.println("Trying to read input file " + filename);
       Scanner infile = null;
       try {
           infile = new Scanner(new File(filename));
       } catch (FileNotFoundException e) {
           System.out.println(e.getMessage());
           return;
       }
       infile.useDelimiter("([0-9]|[.,!?:;'\"-]|\\s)+"); //use the delimiting characters digits, spaces or punctuation marks to extract words
       while(infile.hasNext()) {
           String word = infile.next().toLowerCase();
           Integer count = wordCounter.get(word); //get the count from map
           if(count == null)
               count = 1;
           else
               count = count + 1;
           wordCounter.put(word, count);
       }
       infile.close();
   }

   public void write(String filename){
       //System.out.println("Writing to file " + filename);
       try {
           PrintWriter outfile = new PrintWriter(new File(filename));
           for(String k : wordCounter.keySet()) {
               outfile.println(k + " " + wordCounter.get(k));
           }
           outfile.close();
           //System.out.println("Please refresh project and check output file " + filename);
       } catch (FileNotFoundException e) {
           System.out.println(e.getMessage());
       }
   }

}

//----------------------------------------------------------

//Application.java

public class Application {
   public static void main(String[] args) {
       String inFilename = "problem2.txt";
       String outFilename = "unique_word_counts.txt";
       DuplicateCounter dc = new DuplicateCounter();
       dc.count(inFilename);
       dc.write(outFilename);
   }
}
