/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JInternalFrame;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author ferzo
 */
public class FrameImagen extends JInternalFrame implements Runnable{
    private ECImagen imagen;
    private int numeroventana;

    public FrameImagen(ECImagen imagen, int numeroventana) {
        this.imagen = imagen;
        this.numeroventana = numeroventana;
    }
     public void run(){
         try {
            java.awt.Image awtImage = null;
            URL url = new URL(imagen.getUrl());
            awtImage = ImageIO.read(url);
            
            JLabel nombreLabel = new JLabel(imagen.getNombre());
            this.add(nombreLabel, BorderLayout.NORTH);
            
            ImageIcon imageIcon = new ImageIcon(awtImage);
            JLabel picLabel = new JLabel(imageIcon);
            
            // Guardar la imagen en Disco Duro
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), 
                    imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(awtImage, 0, 0, null);
            
            ImageIO.write(bufferedImage, "jpg", new File( imagen.getNombre() + ".jpg"));
            
            
            this.add(picLabel);
            setBounds(numeroventana * 20, numeroventana * 30, 350,350);
            setVisible(true);
            setClosable(true);
            
            
            
            Ex_Cliente.pane.add(this);
            
            Ex_Cliente.server.notificarPorcentaje(100, Ex_Cliente.idCliente, this.imagen.getIdimagen() );
            
            
        } catch (Exception ex) {

        }
     }
}
