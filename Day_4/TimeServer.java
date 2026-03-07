import java.io.*;
import java.net.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
// import java.util.*;
// import java.util.Date;
public class TimeServer {
    private ServerSocket ss = null;
    private Socket s = null;
    // private BufferedReader br_sock = null;
    private PrintStream ps = null;
    
    public TimeServer(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Listening on port " + port);
            while(true) {
                s = ss.accept();
                System.out.println("Connected to client " + s.getInetAddress().getHostAddress() + ":" + s.getPort());
            
                // br_sock = new BufferedReader(new InputStreamReader(s.getInputStream()));
                ps = new PrintStream(s.getOutputStream());
            
                ZonedDateTime tm = ZonedDateTime.now();
                DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a z");
                ps.println("Time is " + tm.format(fm));
                
                s.close();    
            }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
//        Date dt = new Date();
//        System.out.println(dt.toString());
        /*TimeServer svr = */ new TimeServer(35000);        
    }    
}