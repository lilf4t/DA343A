package L11.client;

import java.io.*;
import java.net.Socket;

//Skillnaden mellan denna och A är:

public class CalcClientB implements CalcClient {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    private CalcController controller;

    public CalcClientB(String ip, int port) throws IOException {
        //TODO
        // Koppla upp mot servern (ip och port)
        // Använd Timeout 5000 ms
        // Använd en kombination av DataInputStream och BufferedInputStream för att läsa från sockets InputStream
        // Använd en kombination av DataOutputStream och BufferedOutputStream för att skriva i sockets OutputStream
        // Starta en tråd (t.ex. inre klass) som lyssnar på svar från servern (readUTF())

        socket = new Socket("195.178.227.53", 721);

        socket.setSoTimeout(5000);

        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));


        //en tråd som lyssnar på svar från server, gör en ny klass som heter Listener
        new Listener().start();

    }
    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }

    public void newCalculation(String expression) throws IOException {
        //TODO: Överför en sträng (expression) med writeUTF()
        dos.writeUTF(expression);
        dos.flush();
    }

    private class Listener extends Thread {
        public void run() {
            String response;
            try {
                while (true) {
                    response = dis.readUTF();
                    controller.newResponse(response);
                }
            } catch (IOException e) {
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
            controller.newResponse("Klient kopplar ner");
        }
    }
}