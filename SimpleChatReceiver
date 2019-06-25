import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

private boolean isRunning = true;

public Receiver(int listeningPort) throws IOException {

    Runnable receiverT = new Runnable() {
        public void run() {

            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(listeningPort);
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                while(isRunning) {
                    try {
                        System.out.println(in.readLine());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    };

    new Thread(receiverT).start();
}

public void stop(){
    isRunning = false;
}


}