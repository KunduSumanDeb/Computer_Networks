import java.io.*;
import java.net.*;
public class MathClient {
    public static void main(String argv[]) {
        try {
            Socket clientSocket = new Socket("localhost", 6789);
            System.out.println("Client running, connected to server");

            DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());                    
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());                    
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    
            while (true) {
                // Read menu
                String menu = inFromServer.readUTF();
                System.out.println(menu);

        
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(inFromUser.readLine());
                outToServer.writeInt(choice);

                if (choice == 5) {
                    System.out.println("Client exiting...");
                    break;
                }

                // Read operand prompt
                String msg = inFromServer.readUTF();
                System.out.println(msg);

                System.out.print("Enter first operand: ");
                double a = Double.parseDouble(inFromUser.readLine());

                System.out.print("Enter second operand: ");
                double b = Double.parseDouble(inFromUser.readLine());

                outToServer.writeDouble(a);
                outToServer.writeDouble(b);

                // Read result
                String result = inFromServer.readUTF();
                System.out.println(result);
                System.out.println();
            }

            clientSocket.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}