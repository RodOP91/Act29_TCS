/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;
import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;


/**
 *
 * @author ferzo
 */
public class Ex_Server extends UnicastRemoteObject implements IServer{
    public JFrame frame;
    public static JButton iniciar;
    
    private  JLabel label;
    private JPanel pane;
    private JPanel cabeza;
    private JComboBox comboBox;
    private JButton botonBuscarRegistro;
    private JButton botonEliminarRegisto;
    private final int PORT = 3232;
    private List<ECImagen> imagenes = new ArrayList<>(10);
    private ArrayList<ECImagen> registros;
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
       this.registros = new ArrayList();
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
        frame = new JFrame("Examen Práctico TCS");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        iniciar = new JButton("Iniciar");
        pane = new JPanel();
        cabeza = new JPanel();
        cabeza.setVisible(true);
       cabeza.setSize(800,150);
        frame.add(cabeza, BorderLayout.NORTH);
        
        label = new JLabel();
        label.setText("Clientes: --");
       //frame.add(label, BorderLayout.NORTH);
        iniciar = new JButton("INICIAR");
        pane.setVisible(true);
        frame.add(pane, BorderLayout.CENTER);
        frame.add(iniciar, BorderLayout.SOUTH);
        frame.setVisible(true);
        
        
        this.comboBox = new JComboBox();
        this.botonBuscarRegistro = new JButton();
        this.botonBuscarRegistro.setText("Buscar registros");
        this.botonEliminarRegisto = new JButton();
        this.botonEliminarRegisto.setText("Eliminar registro");
        
        this.cabeza.add(label, BorderLayout.WEST);
        this.cabeza.add(this.comboBox, BorderLayout.CENTER);
        this.cabeza.add(this.botonBuscarRegistro, BorderLayout.EAST);
        this.cabeza.add(this.botonEliminarRegisto, BorderLayout.EAST);
        
        
        this.botonEliminarRegisto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Indice " + comboBox.getSelectedIndex());
                System.out.println("URL" );
                eliminarRegistro(registros.get(comboBox.getSelectedIndex()));
                llenarComboBox();
                
            }
        });
        
         this.botonBuscarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                llenarComboBox();

            }
        });
        
        iniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Se presiono el botón");
                label.setText("Clientes: " + clientes.size());
                if(!clientes.isEmpty()){
                    //Procesamiento de imágenes cuando sólo hay un cliente
                    if(clientes.size()==1){
                        try{
                            clientes.get(0).iniciaProcesamiento(imagenes);
                        }catch(Exception ex){
                            System.out.println("Error REMOTEEXCEPTION: " + ex.getMessage());
                        }
                         
                    }
                    
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
                 
                }
            }
        });
        
    }
    
    public void eliminarRegistro(ECImagen eci){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ex_InterfazPU");
        EntityManager em = emf.createEntityManager();
   
        em.getTransaction().begin();
        
        
        ECImagen imagen = em.find(ECImagen.class, eci.getIdimagen());
        
        imagen.setFechadescarga(null);
        imagen.setIdcliente(null);
        
        em.getTransaction().commit();

        em.close();
        emf.close();
        
    }
     
    public void guardarDatos(int id,int idCliente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ex_InterfazPU");
        EntityManager em = emf.createEntityManager();
   
        em.getTransaction().begin();
        
        ECImagen imagen = em.find(ECImagen.class, id);
        
        java.util.Date d = new java.util.Date();  
        java.sql.Date date2 = new java.sql.Date(d.getTime());
        imagen.setFechadescarga(date2);
        imagen.setIdcliente(idCliente);
        
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
    
    @Override
    public void notificarPorcentaje(int porcentaje, int idCliente, int idImagen) {
        JProgressBar barra = new JProgressBar();
        JLabel lcliente = new JLabel();
        barra.setMinimum(0);
        barra.setMaximum(100);
        barra.setStringPainted(true);
        pane.add(lcliente);
        pane.add(barra);
         
        lcliente.setText("Cliente " + idCliente);
        barra.setValue(porcentaje);
        frame.update(frame.getGraphics());
        frame.setVisible(true);
        
        guardarDatos(idImagen,idCliente);
        
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
    
    private void limpiarComboBox() {
        
        while (this.comboBox.getItemCount() != 0) {
            this.comboBox.removeItemAt(0);
            
        }
    }
    
    private void llenarComboBox() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ex_InterfazPU");
        EntityManager em = emf.createEntityManager();

        registros = new ArrayList();
        em.getTransaction().begin();
        for (int x = 1; x < 11; x++) {
            if (em.find(ECImagen.class, x).getIdcliente() != null) {
                ECImagen imagen = em.find(ECImagen.class, x);
                registros.add(imagen);
            }

        }

        System.out.println("Registros: "
                + registros.size());
        System.out.println("ComboBox: "
                + comboBox.getItemCount());
        limpiarComboBox();

        int i = 1;

        for (ECImagen eci : registros) {

            if (eci.getIdcliente() != null) {
                comboBox.addItem(i + " | ID: " + eci.getIdcliente()
                        + " Fecha: " + eci.getFechadescarga());
                i++;
            }

        }
    }
    
    
}
