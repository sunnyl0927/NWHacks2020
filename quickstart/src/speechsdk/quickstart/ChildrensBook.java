package speechsdk.quickstart;

import java.util.ArrayList;
import java.util.Scanner;

public class ChildrensBook {
    private ArrayList<String> words;
    private int index;

    public ChildrensBook(Scanner file) {
        index = -1;


        while (file.hasNext()) {
            String s = file.next();
            words.add(punctuationRemover(s));
        }
    }

    private String punctuationRemover(String s ) {
        String fin = "";

        for (int i  = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                fin += c;
            }
        }

        return fin.toLowerCase();
    }

    public String getNext() {
        index++;
        return words.get(index);
    }

    public String repeatWord() {
        return words.get(index);
    }

    public String getPrevious() {
        index--;
        return words.get(index);
    }

}