
package observer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddiel
 */
public class ServerThread extends Thread {
    
    private static List<HiloCliente> listaCliente = new ArrayList<HiloCliente>();
    private ServerSocket server = null;
    private boolean isStoped = false;
    private Observer observer;
    
    
    public ServerThread(Observer observer) throws IOException {
        server = new ServerSocket(7000);
        this.observer = observer;
    }

    public static List<HiloCliente> getLstclientes(){
        return listaCliente;
    }
    
    public synchronized void stoped() {
        isStoped = true;
    }

    @Override
    public void run() {

        Socket cliente = null;

        while (!isStoped) {

            try {
                
                cliente = server.accept();
                HiloCliente hc = new HiloCliente(cliente);
                hc.addObserver(observer);
                listaCliente.add(hc);
                Thread hilo = new Thread(hc);
                hilo.start();
            
            } catch (IOException ex) {
                stoped();
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        super.run();
    }

}
