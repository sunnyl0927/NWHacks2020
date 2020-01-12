package speechsdk.quickstart;

import java.util.ArrayList;
import java.util.Scanner;

public class ChildrensBook {
    private ArrayList<String> words;
    private int index;
    private Scanner story;

    public ChildrensBook(Scanner file) {
        story = file;
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

        //System.out.println(fin);
        return fin.toLowerCase();
    }

    public ArrayList<String> nextSentence() {
        ArrayList<String> currentSentence = new ArrayList<>();
        if(story.hasNext()) {
            currentSentence.add(story.next());
            while(story.hasNext() && !story.next().contains(".")) {
                currentSentence.add(story.next());
            }
        }
        //System.out.println(currentSentence);
        return currentSentence;
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