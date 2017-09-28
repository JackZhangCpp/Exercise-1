
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public final class EchoServer {

    public static void main(String[] args) throws Exception {
    	
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
        	
        	while (true) {
        		
                    Socket socket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(socket);
                    handler.start();
                    	
        	}
                
        }
        
    }
}

class ClientHandler extends Thread{
	Socket socket;
	ClientHandler(Socket socket)
	{
		this.socket = socket;
	
}
public void run()
{
	try {
	String userStr;
    String address = socket.getInetAddress().getHostAddress();
    System.out.printf("Client connected: %s%n", address);
    OutputStream os = socket.getOutputStream();
    PrintStream out = new PrintStream(os, true, "UTF-8");
    out.printf("Hi %s, thanks for connecting!%n", address);

    
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
   
    while((userStr=in.readLine()) != null) {
    	if(userStr.equals("exit"))
    	{
    		System.out.printf("Client disconnected: %s%n", address);
			socket.close();
			break;
		}
    	out.println(userStr); 
    	
    	
    }
	}catch(Exception e){return;}
}
}
	