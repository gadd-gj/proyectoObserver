package observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloCliente extends Observable implements Runnable {

    private Socket cliente;
    private boolean isStoped = false;
    private BufferedReader receptor = null;
    private PrintWriter emisor = null;

    public HiloCliente(Socket cliente) {
        try {
            this.cliente = cliente;
            InputStreamReader isr = new InputStreamReader(cliente.getInputStream());
            receptor = new BufferedReader(isr);
            emisor = new PrintWriter(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void stoped() {
        isStoped = true;
    }

    public void escribir(String msg){
        emisor.write(msg);
        emisor.write("\n");
        emisor.flush();
    }
    
    @Override
    public void run() {

        while (!isStoped) {

            try {
                setChanged();
                //emisor.write("Hola mundo \n");
                //emisor.flush();
                notifyObservers("Valor");
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
