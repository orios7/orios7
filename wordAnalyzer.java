import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class wordAnalyzer {

    public static void main(String[] args) throws IOException {

        // Establishes connection to website.
        Document doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
        // Collects website data by tag.

        Elements title = doc.getElementsByTag("h1");
        Elements body = doc.getElementsByTag("p");

        //Sting builder to save parsed html code.
        StringBuilder str = new StringBuilder("");

        //for loop used to append poem words to StringBuilder.
        for (Element b : body) {

            str.append(b.text());
        }
        //for loop to append title to StringBuilder.
        for (Element t : title) {

            str.append(t.text());
        }

        //scanner used for parsing data stored in StringBuilder.
        Scanner fileWord = new Scanner(String.valueOf(str));

        //ArrayList to store each word occurrence.
        ArrayList<String> wordsArray = new ArrayList<String>();

        //ArrayList to store count of word occurrence.
        ArrayList<Integer> freqArray = new ArrayList<Integer>();

        //While loop to itereate to each word provided by scanner.
        while (fileWord.hasNext()) {

            // Delimiter used to parsing purposed and to remove some unneeded alphanumeric characters.
            fileWord.useDelimiter("\s|\n|\\—|\\.|\\!");

            //Sting to each word occurrence
            String word = fileWord.next();

            //String to store words with non-alphameric characters removed in uppercase.
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

            //If statement to search for word provided by the scanner in the wordsArray ArrayList.
            //If the word is found the corresponding index in the freqArray ArrayList is updated
            if (wordsArray.contains(cleanWord)) {
                int arrayIndex = wordsArray.indexOf(cleanWord);
                freqArray.set(arrayIndex, freqArray.get(arrayIndex) + 1);

            //If the word in not found in the wordsArray, it is added and corresponding freqArray is updated at the same index.
            } else {
                wordsArray.add(cleanWord);
                freqArray.add(1);
            }

        }
        //for loop used to remove empty spaces in both wordsArray and freqArray ArrayLists.
        for (int i = 0; i < wordsArray.size(); i++) {
            if (wordsArray.get(i).isEmpty()) {

                wordsArray.remove(i);
                freqArray.remove(i);

            }

        }

        //ArrayList used to store the values of both the wordsArray and freqArray ArrayLists to
        //facilitate sorting.
        ArrayList<sortA> so = new ArrayList<>();
        for (int i = 0; i < wordsArray.size(); i++) {
            so.add(new sortA(freqArray.get(i), wordsArray.get(i)));

        }

        // sort used to sort the so ArrayList in descending order.
        Collections.sort(so, Collections.reverseOrder());


        LinkedHashMap<String, Integer> set = new LinkedHashMap<>();
        HashMap<String, Integer> map = new HashMap <String, Integer>();

        System.out.println("");
        System.out.println("        WORD ANALYZER");
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("WORD" + "             " + "WORD FREQUENCY");
        System.out.println("-------------------------------");

        //for loop to display the first 20 values in the so ArrayList and LinkedHashSet.
        for (int i = 0; i < 20; i++) {  // i < so.size()   i < 20

            Object sum;
            Object words;

            sum = so.get(i).c;
            words = so.get(i).w;

            //set.add(words, sum);
            set.put((String) words, (Integer) sum);
            //map.put((String) words, (Integer) sum);

            System.out.printf("%-10s %20s\n", words, sum);

        }
        //Prints out LinkedHashMap
        System.out.println("");
        System.out.println("");
        System.out.println("Pairs: " + set);

        }
    }

// sort override to sort only by freqArray Arraylist data.
class sortA implements Comparable<sortA> {
    String w;
    int c;
    sortA(int c, String w) {
        //sortarr(int c) {
        this.c = c;
        this.w = w;
    }
    @Override
    public int compareTo(sortA obj) {
        if (this.c > obj.c)
            return 1;
        else if (this.c < obj.c)
            return -1;
        return 0;
    }

}