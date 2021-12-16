package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final int SERVER_PORT = 8089;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            System.out.println("Ожидание подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Подключение установлено!");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true){
                String massage = in.readUTF();
                System.out.println("Сообщение от пользователя: " + massage);

                if(massage.equals("/exit")){
                    System.out.println("Клиент отключился");
                    break;
                }
                out.writeUTF("Ответ сервера: " + massage.toUpperCase());
            }
        } catch (IOException e) {
            System.out.println("");
        }
    }

}
