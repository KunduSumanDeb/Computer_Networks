package Day_5;
import java.io.*;
import java.net.*;

public class EchoClient {

    public static String packettostr(DatagramPacket p){
        return new String(p.getData(), 0, p.getLength());
    }

    public static void main(String[] args) throws IOException{
        final InetAddress SERVER_IP = InetAddress.getLocalHost();
        final int SERVER_PORT = 51091;
        final int BUFF_SIZE  = 65535;

        DatagramSocket ds = new DatagramSocket(); // OS picks port automatically
        byte[] buffer_rec = new byte[BUFF_SIZE];

        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Client ready. Type messages to send:");

        while(true){
            System.out.print("Client: ");
            String msg = kb.readLine();
            byte[] buffer_send = msg.getBytes();

            // send packet to server
            DatagramPacket send_packet = new DatagramPacket(buffer_send, buffer_send.length, SERVER_IP, SERVER_PORT);
            ds.send(send_packet);

            if(msg.equalsIgnoreCase("exit")){
                System.out.println("Exiting client...");
                break;
            }

            // receive server response
            DatagramPacket receive_packet = new DatagramPacket(buffer_rec, buffer_rec.length);
            ds.receive(receive_packet);
            String svr_reply = packettostr(receive_packet);
            System.out.println("Server: " + svr_reply);
        }

        ds.close();
    }
}
