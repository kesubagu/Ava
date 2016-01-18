


import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;



public class HelloAva {

    public static void main(String[] args) {
        ConfigurationManager cm;

        if (args.length > 0) {
            cm = new ConfigurationManager(args[0]);
        } else {
            cm = new ConfigurationManager(HelloAva.class.getResource("helloworld.config.xml"));
        }

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

        System.out.println("What next?");

        // loop the recognition until the programm exits.
        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText);
                
                if(resultText.contains("chrome")){
                	runProgram program = new runProgram();
                	program.executeChrome();
                } else if (resultText.contains("game")){
                	runProgram program = new runProgram();
                	program.executeCSS();
                } else if (resultText.contains("music")){
                	runProgram program = new runProgram();
                	program.executeSpotify();
                } 
                
                	
            } else {
                System.out.println("I can't hear what you said.\n");
            }
        }
    }
}