import java.net.*;
import java.io.*;
public class TimeClient {
    private Socket s = null;
    private BufferedReader br_kb = null;
    private BufferedReader br_svr = null;
    private PrintStream ps = null;
    
    public TimeClient(String addr, int port) {
        try {
            s = new Socket(addr, port);
            System.out.println("Connected to " + addr + ":" + port);

            br_svr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            br_kb = new BufferedReader(new InputStreamReader(System.in));

            ps = new PrintStream(s.getOutputStream());
            // String msg = "";
            
            String svr_msg = br_svr.readLine();
            System.out.println("Server: " + svr_msg);
            
//            System.out.print("Enter messages:\n - ");
//            while (!(msg = br_kb.readLine()).equalsIgnoreCase("exit")) {
//                ps.println(msg);
//                String svr_msg = br_svr.readLine();
//                System.out.println("Server: " + svr_msg);
//                System.out.print(" - ");
//            }
//            
            System.out.println("Closing Client");
            s.close();
            br_kb.close();
            br_svr.close();
            ps.close();
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        /* TimeClient clnt =*/ new TimeClient("localhost", 35000);
    }
}

