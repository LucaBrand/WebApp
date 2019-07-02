import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SimpleChatMain {

private static boolean isRunning = true;
private static SimpleChatSender sender;
private static SimpleChatReceiver receiver;

public static void main(String[] args) throws IOException {

    if(args.length < 3){
        showUsage();
    }

    try {
        receiver = new SimpleChatReceiver(Integer.parseInt(args[1]));
        sender = new SimpleChatSender(args[0], args[2], Integer.parseInt(args[3]));
    } catch (InterruptedException e) {
        showUsage();
    }

    // Read user input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Chat started. Type '\\exit' to quit.");

    while(isRunning) {
        String input = br.readLine();

        if(input.equals("\\exit")){
            receiver.stop();
            sender.stop();
            isRunning = false;
        } else {
            sender.sendMessage(input);
        }
    }   
}

static void showUsage(){
    System.out.println("Usage: java SimpleChat.java listening_port target_IP target_port");
    System.exit(1);
}

}
