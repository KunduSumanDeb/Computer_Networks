import java.io.*;
import java.net.*;
public class MathServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("Math Server is running...");

            while (true) { 
                Socket connectionSocket = serverSocket.accept();
                System.out.println("Client connected");

                DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                while (true) {
                    
                    outToClient.writeUTF("Select 1 to add, 2 to subtract, 3 to multiply, 4 to divide,5 to exit");

                    int choice = inFromClient.readInt();

                    if (choice == 5) {
                        System.out.println("Client exited");
                        break;
                    }
                    
                    if(choice > 5){
                        outToClient.writeUTF("Invalid Choice");
                        continue;
                    }

                    outToClient.writeUTF("Enter operands");

                    double a = inFromClient.readDouble();
                    double b = inFromClient.readDouble();
                    double result = 0.0;

                    switch (choice) {
                        case 1:
                            result = a + b;
                            break;
                        case 2:
                            result = a - b;
                            break;
                        case 3:
                            result = a * b;
                            break;
                        case 4:
                            if (b != 0) {
                                result =(double) a / b;
                            } 
                            else {
                                outToClient.writeUTF("Error: Division by zero");
                                continue;
                            }
                            break;
                        default:
                            outToClient.writeUTF("Invalid choice");
                            continue;
                    }
                    // Send result
                    outToClient.writeUTF("Result = " + result);
                }
                connectionSocket.close();
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }    
}
