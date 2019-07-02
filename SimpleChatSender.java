import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SimpleChatSender {

private boolean isRunning = true;
private volatile List<String> msgs;

public SimpleChatSender(String username, String targetIP, int targetPort) throws InterruptedException, UnknownHostException, IOException {
    msgs = Collections.synchronizedList(new ArrayList<String>());

    Runnable senderT = new Runnable() {
        public void run() {
            try {
                Socket socket = new Socket(targetIP, targetPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while(isRunning) {
                    synchronized(msgs){
                        Iterator<String> it = msgs.iterator();

                        while(it.hasNext()){
                            out.println(username + ": " + it.next());
                        }

                        // Clear messages to send
                        msgs.clear();
                    }
                }

                out.close();
                socket.close();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    };

    new Thread(senderT).start();
}

public void stop(){
    isRunning = false;
}

public void sendMessage(String msg){
    synchronized(msgs){
        msgs.add(msg);  
    }
}
}