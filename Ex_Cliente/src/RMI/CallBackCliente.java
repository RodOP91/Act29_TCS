/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author ferzo
 */
public class CallBackCliente extends UnicastRemoteObject implements ICliente{
    public CallBackCliente() throws RemoteException{
        super();
        
    }
    
    @Override
    public void iniciaProcesamiento(List<ECImagen> imagenes)throws RemoteException{
        int x=1;
        for(ECImagen imagen : imagenes) {
            System.out.println("Procesando imagen: " +  x);
            new Thread(new FrameImagen(imagen, x++)).start();
        }
    }
}
