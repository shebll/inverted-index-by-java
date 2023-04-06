import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InvertedIndex {
    HashMap<String,DictEntry> termIndex;

    //constructor
    InvertedIndex()
    {
        termIndex=new HashMap<>();
    }
   public void createIndex() throws IOException {
        int documentId = 1;
        try {
        for(int i = 1; i <= 10 ; i++)
        {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("Document"+i+".txt"));
        String line ;
        while ((line = bufferedReader.readLine()) != null)
        {
           String [] words =line.split(" "); //(//W+)
            for(String word:words)
            {
                word = word.toLowerCase();
                if(!termIndex.containsKey(word))//search for term in inverted index
                {
                    termIndex.put(word, new DictEntry(documentId));
                    termIndex.get(word).term_freq++;
                    termIndex.get(word).doc_freq++;
                }
                else
                {
                    termIndex.get(word).term_freq++;
                    Poisting poistingList = termIndex.get(word).plist;

                    while(poistingList != null)
                    {
                        if(poistingList.docId == documentId)
                        {
                            break;
                        }
                        if(poistingList.next == null) {
                            poistingList.next = new Poisting(documentId);
                            termIndex.get(word).plist.documentfrequency++;
                            break;
                        }
                        poistingList=poistingList.next;
                    }
                }
            }
        }
            documentId++;
    }
    }
    catch(FileNotFoundException e) {
        e.printStackTrace();
    }
   }

   public void printPoistingList() {
       TreeMap<String,DictEntry>sortedDict=new TreeMap<>();
       sortedDict.putAll(termIndex);
       for(Map.Entry<String, DictEntry> term : sortedDict.entrySet())
        {
            Poisting poistlist = term.getValue().plist;
            System.out.print(term.getKey());
            System.out.print(" ------->   " +term.getValue().plist.documentfrequency  +"   {");
            while(poistlist!=null)
            { if(poistlist.next==null)
                {
                    System.out.print(poistlist.docId);
                    break;
                }
                System.out.print(poistlist.docId+",");
                poistlist=poistlist.next;
            }
            System.out.println("}");
       }
   }
    public void printDecFreTermFre()
    {
        TreeMap<String,DictEntry>sortedDict=new TreeMap<>();
        sortedDict.putAll(termIndex);
        for(Map.Entry<String, DictEntry> dictonary : sortedDict.entrySet())
        {
            System.out.println(dictonary.getKey() +"    "+dictonary.getValue().plist.documentfrequency+"      "+dictonary.getValue().term_freq);
        }
    }
    public void SearchTerm(String dictonary)
    {
        for(Map.Entry<String,DictEntry>term :termIndex.entrySet())
        {
            Poisting poistlist = term.getValue().plist;
            if(term.getKey().equals(dictonary))
            {
                System.out.print("The term : "+term.getKey()+" is exist in this documents");
                System.out.print(" -------> {");
                while(poistlist!=null)
                    { if(poistlist.next==null)
                        {
                            System.out.print(poistlist.docId);
                            break;
                        }
                            System.out.print(poistlist.docId+",");
                            poistlist=poistlist.next;
                    }
                System.out.println("}");
            }
        }
    }
}
