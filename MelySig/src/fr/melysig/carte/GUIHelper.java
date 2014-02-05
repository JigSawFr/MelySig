/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class GUIHelper {
    
    public static void showOnFrame(JComponent component, String frameName){
        JFrame maFenetre = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };
        maFenetre.addWindowListener(wa);
        maFenetre.getContentPane().add(component);
        maFenetre.pack();
        maFenetre.setVisible(true);
        
    }
    
}
