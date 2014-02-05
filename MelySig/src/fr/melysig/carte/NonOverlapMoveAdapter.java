/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Coonax
 */
public class NonOverlapMoveAdapter extends MoveDrawableMouseListener {
    private Point initialLocation;
    
    public NonOverlapMoveAdapter (JCanvas canvas){
        super(canvas);
    }
    
    public void mouseReleased(MouseEvent e){
        if(drawable == null) return;
        if(!canvas.isAlone(drawable)){
            drawable.setPosition(initialLocation);
            System.out.println("Placement impossible, un point interet existe déjà à cette position.");
        }
        initialLocation =null;
        drawable=null;
        canvas.repaint();
        
    }
    
    public void mousePressed(MouseEvent e){
        super.mousePressed(e);
        if(drawable!=null){
            initialLocation=drawable.getPosition();
            
        }
    }
}
