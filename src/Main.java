
import java.io.*;
import java.io.IOException;
import java.util.*;


    public class Main {

        public static void main(String args[])  throws IOException{
            Scanner input=new Scanner(System.in);
            InvertedIndex invertedIndex=new InvertedIndex();
            invertedIndex.createIndex();//create index
            invertedIndex.printPoistingList();
            System.out.println("Enter The word you want : ");
            String term=input.next();
            invertedIndex.SearchTerm(term);

        }
    }


