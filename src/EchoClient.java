import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintStream;
import java.io.OutputStream;
public final class EchoClient {

	public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
        
          String str;
          
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
            
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         System.out.print("<Client>: "); 
            BufferedReader svin= new BufferedReader(new InputStreamReader(System.in));
            
            
            while((str=svin.readLine())!=null) {
             out.println(str);
             if(str.equals("exit"))
             {break;}
             else{
             System.out.println("<Server> " + in.readLine());
             System.out.print("<Client>: "); 
             }
            }
            
        }
    }
}


