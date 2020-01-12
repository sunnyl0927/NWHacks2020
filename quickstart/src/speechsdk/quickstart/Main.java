
package speechsdk.quickstart;
import java.io.File;
import java.util.*;
import java.util.concurrent.Future;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import com.microsoft.cognitiveservices.speech.*;

/**
 * Quickstart: recognize speech using the Speech SDK for Java.
 */
public class Main extends JApplet{

    /**
     * @param args Arguments are ignored in this sample.
     */
    public static void main(String[] args) {
        // Replace below with your own subscription key
        String speechSubscriptionKey = "7c5588cc1bcf4fa788e96fb4b819ebd9";
        // Replace below with your own service region (e.g., "westus").
        String serviceRegion = "westus";
        SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
        assert(config != null);
        ArrayList<String> sentence = new ArrayList<String>();
        sentence.add("hello");
        sentence.add("this");
        sentence.add("is");
        sentence.add("a");
        sentence.add("test");
        
        System.out.print("Please enter a file to read: ");
        Scanner scan = new Scanner(System.in);
        File file = new File(scan.next());
        
        while(!sentence.isEmpty()) {
        	String word = sentence.get(0);
        	System.out.println("current word is:" + word);
        	if(voiceInput(config).toLowerCase().contains(word.toLowerCase())) {
        		System.out.println("Good job!");
        		sentence.remove(0);
        	}
        	else {
        		System.out.println("Incorrect, try again");
        	}
        }
        
    }
    
    public static String voiceInput(SpeechConfig config) {
        SpeechRecognizer reco = new SpeechRecognizer(config);
    	assert(reco != null);

        System.out.println("Say something...");
        
        // records audio for 15 seconds or until audio dims
        Future<SpeechRecognitionResult> task = reco.recognizeOnceAsync();
        assert(task != null);
        
        try {
	        SpeechRecognitionResult result = task.get();
	        assert(result != null);
	
	        if (result.getReason() == ResultReason.RecognizedSpeech) {
	            System.out.println("We recognized: " + result.getText());
	        }
	        else if (result.getReason() == ResultReason.NoMatch) {
	            System.out.println("NOMATCH: Speech could not be recognized.");
	        }
	        else if (result.getReason() == ResultReason.Canceled) {
	            CancellationDetails cancellation = CancellationDetails.fromResult(result);
	            System.out.println("CANCELED: Reason=" + cancellation.getReason());
	
	            if (cancellation.getReason() == CancellationReason.Error) {
	                System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
	                System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
	                System.out.println("CANCELED: Did you update the subscription info?");
	            }
	        }
	
	        reco.close();
	
	        return result.getText();
        
        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());

            assert(false);
            System.exit(1);
            return null;
        }
    }
}