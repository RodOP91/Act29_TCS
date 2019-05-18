/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ferzo
 */
public interface ICliente extends Remote{
    /**
     * Metodo que inicia el procesamiento de imagenes, lo llama el server
     * @param imagenes 
     */
    public void iniciaProcesamiento(List<ECImagen> imagenes) throws RemoteException;
    
}
