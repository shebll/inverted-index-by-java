public class DictEntry {
    int doc_freq=0;   //num of documents that contain term
    int term_freq=0; //number of times the term
    Poisting plist = null;
    DictEntry(int docid)
    {
        plist=new Poisting(docid);

    }
}
