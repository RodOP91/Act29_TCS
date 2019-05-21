/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @author ferzo
 */
public interface IServer extends Remote{
    
    /**
     * El cliente notifica el avance del procesamiento de imágenes al server
     * @param porcentaje
     * @param idCliente
     * @throws RemoteException 
     */
    public void notificarPorcentaje(int porcentaje, int idCliente, int idImagen) throws RemoteException;
    
    
    /**
     * Registra al cliente remoto
     * @param cliente
     * @return
     * @throws RemoteException 
     */
    public int registrarCallbackCliente(ICliente cliente)throws RemoteException;
    
    
    /**
     * Elimina al cliente en cuestión del registro una vez terminado el procesamiento
     * @param cliente
     * @throws RemoteException 
     */
    public void deregistrarCallbackCliente(ICliente cliente)throws RemoteException;
    
}
