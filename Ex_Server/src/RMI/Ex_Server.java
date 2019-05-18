/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import RMI.ECImagen;
/**
 *
 * @author ferzo
 */
public class Ex_Server extends UnicastRemoteObject implements IServer{
    
    public static JButton iniciar;
    private final int PORT = 3232;
    private List<ECImagen> imagenes = new ArrayList<>(10);
    private List<ICliente> clientes = new ArrayList<>();
    
    public Ex_Server() throws RemoteException{
        /*imagenes.add(new Imagen("TLOZ", "https://i.pinimg.com/originals/76/a9/67/76a967156646cf649d37a9a5f9a36acf.jpg"));
        //imagenes.add(new Imagen("MarioBros", "https://www.el-carabobeno.com/wp-content/uploads/2018/07/mario-bros.jpg"));
        imagenes.add(new Imagen("TheWitcher", "http://assets.vg247.com/current//2015/05/the_witcher_3_wild_hunt_guide_walkthrough.jpg"));
        imagenes.add(new Imagen("TheWitcher", "http://assets.vg247.com/current//2015/05/the_witcher_3_wild_hunt_guide_walkthrough.jpg"));
        imagenes.add(new Imagen("TheWitcher", "http://assets.vg247.com/current//2015/05/the_witcher_3_wild_hunt_guide_walkthrough.jpg"));
        imagenes.add(new Imagen("Skyrim", "https://i.blogs.es/14a384/1366_2000/1366_2000.jpg"));
        imagenes.add(new Imagen("LeagueOfLegends", "https://i2.sdpnoticias.com/sdpnoticias/2018/07/27/1920_league-of-legends_620x350.jpg"));
        imagenes.add(new Imagen("WorldOfWarcraft", "https://dlcompare-images.s3.eu-west-3.amazonaws.com/game_tetiere/upload/gameimage/file/37199.jpeg"));
        imagenes.add(new Imagen("Diablo", "https://blznav.akamaized.net/img/games/cards/card-diablo-immortal-27ce8fcd012c5f03.jpg"));
        imagenes.add(new Imagen("Overwatch", "https://blznav.akamaized.net/img/games/cards/card-overwatch-7eff92e1257149aa.jpg"));
        //imagenes.add(new Imagen("Warhammer40000", "https://cdn-images-1.medium.com/max/1600/1*Spc0rmQ9Xf5gYY_c8BUnGQ.jpeg"));
        imagenes.add(new Imagen("Stellaris", "https://hb.imgix.net/7797384a66a57b3baea9fb335092a4fbd49301bd.jpg?auto=compress,format&fit=crop&h=353&w=616&s=fb0ea697fa8e5c30180d5f76665de12c"));
        */        
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ex_InterfazPU");
        EntityManager em = emf.createEntityManager();
   
        em.getTransaction().begin();
        
        for(int x=1; x<11; x++){
            ECImagen imagen = em.find(ECImagen.class, x);
            imagenes.add(imagen);
        }
        
        em.getTransaction().commit();

        em.close();
        emf.close();
        
        interfazGrafica();
    }

    
    public static void main(String[] args) throws RemoteException{
        
        SwingUtilities.invokeLater(new Runnable(){
        
            public void run() {
                try {
                    (new Ex_Server()).iniciarServer();
                } catch (RemoteException ex) {
                    System.out.println("Error en " + ex.getMessage()); 
                }
            }
                
        });
        
    }
    
    public void iniciarServer(){
        try{
            String direccion = (InetAddress.getLocalHost()).toString();
            System.out.println("Iniciando servidor en " + direccion  +  ":" + PORT);
            Registry registro = LocateRegistry.createRegistry(PORT);
            registro.bind("ExamenServer",(IServer) this);
            System.out.println("Servidor iniciado");
            
        }catch(Exception ex){
            System.out.println("ERROR en : " + ex.getMessage());
        }
    }
    
    public void interfazGrafica(){
        JFrame frame = new JFrame("Examen Práctico TCS");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        iniciar = new JButton("Iniciar");
        frame.add(iniciar);
        frame.setVisible(true);
        
        iniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presiono el botón");
                if(!clientes.isEmpty()){
                    //Procesamiento de imágenes cuando sólo hay un cliente
                    if(clientes.size()==1){
                        try{
                            clientes.get(0).iniciaProcesamiento(imagenes);
                        }catch(Exception ex){
                            System.out.println("Error REMOTEEXCEPTION: " + ex.getMessage());
                        }
                         
                    }
                    //Procesamiento de imágenes cuando hay dos clientes
//                    if(clientes.size() ==2){
//                        try{
//                            List<Imagen> imgs = new ArrayList<>();
//                            List<Imagen> imgs2= new ArrayList<>();;
//                            
//                            for(int x =0; x<imagenes.size();x++){
//                                if(x<5){
//                                    imgs.add(imagenes.get(x));
//                                }else{
//                                    imgs2.add(imagenes.get(x));
//                                }
//                            }
//                            clientes.get(0).iniciaProcesamiento(imgs);
//                            clientes.get(1).iniciaProcesamiento(imgs2);
//                            /*clientes.get(0).iniciaProcesamiento((imgs = imagenes.subList(0,4)));
//                            clientes.get(1).iniciaProcesamiento((imgs = imagenes.subList(5,9)));*/
//                        }catch(Exception ex){
//                            System.out.println("Error al repartir: " + ex.getMessage());
//                        }
//                        
//                    }
                    
                    if(clientes.size() > 1 && clientes.size() <=10){
                        int contImagen = 0;
                        List<ECImagen> list = new ArrayList<>();
                        int contClientes = 0;
                        while(contClientes < clientes.size()){
                            list.add(imagenes.get(contImagen));
                            System.err.println("imagen" + contImagen);
                            try {
                                clientes.get(contClientes).iniciaProcesamiento(list);
                                contImagen++;
                                list.clear();
                                if(contImagen > 9){
                                    contClientes = 20;
                                }
                                contClientes++;
                                if(contClientes == clientes.size()){
                                    contClientes = 0;
                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(Ex_Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                    //Procesamiento de imágenes cuando hay más de dos clientes
                    /*if(clientes.size()>1 && clientes.size()<=10){
                        int sobrante = 10%clientes.size();
                        int cant = 10 /(clientes.size()-sobrante);
                        int indice=0;
                        List<Imagen> img =null;
                        for(int x =0; x < clientes.size(); x++){
                            img = new ArrayList<>();
                            for(int y =indice; y<cant; y++){
                                img.add(imagenes.get(y));                                
                            }
                            try{
                                clientes.get(x).iniciaProcesamiento(img);
                            }catch(RemoteException ex){
                                System.out.println("Error de REMOTEEXCEPTION en: " + ex.getMessage());
                            }                            
                        }
                        for(int x =0; x<sobrante;  x++){
                            img = new ArrayList<>();
                            img.add(imagenes.get(indice));
                            try{
                                clientes.get(x).iniciaProcesamiento(img);
                            }catch(RemoteException ex){
                                
                            }
                        }
                    }*/
                }
            }
        });
        
    }
    
    
    @Override
    public void notificarPorcentaje(int porcentaje, int idCliente) {
        
    }

    @Override
    public int registrarCallbackCliente(ICliente cliente) throws RemoteException{
        int idCliente=0;
        if(!clientes.contains(cliente)){
            clientes.add(cliente);
            idCliente = clientes.indexOf(cliente);
            
        }
        System.out.println("Se registró a un cliente : "+ idCliente);
        return idCliente;
    }

    @Override
    public void deregistrarCallbackCliente(ICliente cliente) throws RemoteException{
        System.out.println("Deregistrando al cliente: " + clientes.indexOf(cliente));
        clientes.remove(cliente);
        System.out.println("Deregistro terminado con éxito");
        
    }
    
}
