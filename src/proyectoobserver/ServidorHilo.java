package proyectoobserver;

import java.io.*;
import java.net.*;
import java.util.logging.*;
public class ServidorHilo extends Thread {
    private Socket socket;
    
    private int idSessio;
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
    }
    
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {    
        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader receptor = new BufferedReader(isr);
            
            PrintWriter emisor = new PrintWriter(socket.getOutputStream());
        String msg = receptor.readLine();
            System.out.println("El Cliente "+this.idSessio+" dice: " + msg);
            if (msg.equals("Hola H")) {
                emisor.println("Quetal Amigo :D y adios");
                emisor.flush();
            }else if(msg.equals("Hola M")){
                emisor.println("Quetal Amiga :D y adios");
                emisor.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        desconnectar();
    }
    
}
