package Day_5;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTimeClient {

    public static void main(String[] args) throws IOException {
        final InetAddress IP = InetAddress.getLocalHost();
        final int SVR_PORT = 50091;
        final int CLIENT_PORT = 50092;
        final int BUFF_SIZE = 65535;

        DatagramSocket ds = new DatagramSocket(CLIENT_PORT);

        // Send a dummy request to server
        String msg = "time";
        byte[] buffer_send = msg.getBytes();
        DatagramPacket send_packet = new DatagramPacket(buffer_send, buffer_send.length, IP, SVR_PORT);
        ds.send(send_packet);

        // Receive server response
        byte[] buffer_rec = new byte[BUFF_SIZE];
        DatagramPacket receive_packet = new DatagramPacket(buffer_rec, buffer_rec.length);
        ds.receive(receive_packet);

        String svr_reply = new String(receive_packet.getData(), 0, receive_packet.getLength());
        System.out.println("Server time: " + svr_reply);
        System.out.println("Client closing...");

        ds.close(); // client exits immediately
    }
}