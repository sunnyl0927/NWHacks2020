package speechsdk.quickstart;
import java.io.File;
import java.util.Scanner;
// Imports text from a text file and allows user to iterate through each sentence
public class StoryBook {	
	// Current sentence to be read
	private String currentSentence;
	
	private Scanner input;
	
	// Creates a StoryBook object using text file story.txt
	public StoryBook() {
		File story = new File("story.txt");
		input = new Scanner("story.txt");
		nextSentence();
	}
	
	// sets String currentSentence to the next sentence in the story.
	// If no more sentences remain, returns an empty string
	public void nextSentence() {
		currentSentence = "";
		if(input.hasNext()) {
			String currentWord = input.next();
			while(input.hasNext() && !currentWord.contains(".")) {
				currentWord = input.next();
				currentSentence += currentWord;
			}
		}
	}
}
