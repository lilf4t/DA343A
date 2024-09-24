package L11.client;

import java.io.*;
import java.net.Socket;

public class CalcClientC implements CalcClient {
    private CalcController controller;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    public CalcClientC(String ip, int port) throws IOException {
        //TODO: Samma som i CalcClientB
        socket = new Socket("195.178.227.53", 723);

        socket.setSoTimeout(5000);

        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));


        //en tråd som lyssnar på svar från server, gör en ny klass som heter Listener
        new Listener().start();

    }

    public void setCalcController(CalcController controller) {
        this.controller = controller;
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

    public void newCalculation(String expression) throws IOException {
        //TODO
        // Räkneuppgiften ska inte överföras som en string (expression) utan som tre enskilda objekt
        // Först ska två double-objekt skickas som innehåller båda talen och sedan ett Char-objekt som innehåller operatorn.
        // T.ex. ska ”2 + 2” skickas som writeDouble(2), writeDouble(2), writeChar(“+”)
        // Ordningen är viktig eftersom servern förväntar sig först två double och sedan en char
        // För att dela upp expression-string kan expression.split(",") användas
        // https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)

        double nbr1=Double.NaN, nbr2=Double.NaN;
        char operation=' ';
        String[] parts = expression.split(",");
        try {
            nbr1 = Double.parseDouble(parts[0]);
            nbr2 = Double.parseDouble(parts[1]);
            operation = parts[2].charAt(0);
        } catch(Exception e) {
            controller.newResponse("Bad arguments: " + parts[0] + parts[2] +
                    parts[1]);
            return;
        }
        dos.writeDouble(nbr1);
        dos.writeDouble(nbr2);
        dos.writeChar(operation);
        dos.flush();

    }
}