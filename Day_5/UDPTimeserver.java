package Day_5;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UDPTimeserver {

    public static void main(String[] args) throws IOException {
        final int PORT = 50091;
        final int BUFF_SIZE = 65535;
        DatagramSocket ds_rec = new DatagramSocket(PORT);

        System.out.println("Server is running on port " + PORT + "...");

        byte[] buffer_rec = new byte[BUFF_SIZE];

        while (true) {
            DatagramPacket receive_packet = new DatagramPacket(buffer_rec, buffer_rec.length);

            // Wait for client request
            ds_rec.receive(receive_packet);

            InetAddress client_ip = receive_packet.getAddress();
            int client_port = receive_packet.getPort();

            // Get current time
            ZonedDateTime t = ZonedDateTime.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a z");
            String reply = t.format(fmt);

            // Send time to client
            byte[] buffer_send = reply.getBytes();
            DatagramPacket send_packet = new DatagramPacket(buffer_send, buffer_send.length, client_ip, client_port);
            ds_rec.send(send_packet);

            System.out.println("Sent date and time to client: " + reply);
        }
    }
}