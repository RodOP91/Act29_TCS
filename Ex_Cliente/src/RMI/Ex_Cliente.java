/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author ferzo
 */
public class Ex_Cliente{
    
    private static final int PORT = 3232;
    private static final String NAMESERVICE = "ExamenServer";
    private static final String HOSTNAMESERVER = "localhost";
    
    private List<Imagen> imagenes;
    
    public static JFrame frame;
    public static JDesktopPane pane;
    public static IServer server;
    public static CallBackCliente cliente;
    
    public static int idCliente;
    
    public Ex_Cliente()throws RemoteException{
        rmi();
        interfazGrafica();
    }
    
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            public void run() {
                try {
                    new Ex_Cliente();
                } catch (RemoteException ex) {
                    System.out.println("Error en método main: " + ex.getMessage()); 
                }
            }
                
        });     
    }
     
     public void rmi(){
        try{
            cliente = new CallBackCliente();
            Registry registro = LocateRegistry.getRegistry(HOSTNAMESERVER, PORT);
            server = (IServer) registro.lookup(NAMESERVICE);
            idCliente = server.registrarCallbackCliente(cliente);
        }catch(RemoteException ex){
            System.out.println("Error en método RMI: " +  ex.getMessage());
        }catch(NotBoundException ex){
            System.out.println("Error NOTBOUNDEXCEPTION: " + ex.getMessage());
        }
     }
     
     public void interfazGrafica(){
        frame = new JFrame("Examen Práctico TCS");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        pane = new JDesktopPane();
        
        frame.setContentPane(pane);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
               int i = JOptionPane.showConfirmDialog(pane, "¿Salir de la aplicación?");
               if(i == 0) {
                   try {
                       server.deregistrarCallbackCliente(cliente);
                   } catch (RemoteException ex) {
                       Logger.getLogger(Ex_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
            
        });
     }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
     
     
     
}
