package proyectoobserver;

import java.io.*;
import java.net.*;
import java.util.logging.*;
public class Servidor {
    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        boolean isRun = true;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(5000);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (isRun) {
                if (idSession == 2) {
                    isRun=false;
                }
                Socket cliente = ss.accept();
                System.out.println("Nueva conexión entrante: "+cliente);
                
                ServidorHilo servidorH = new ServidorHilo(cliente, idSession);
                servidorH.start();
                
                idSession++;
                
            
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
