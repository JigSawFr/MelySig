/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;

import fr.melysig.models.Lieux;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class JCanvas extends JPanel{
    
//    private int x;
//    private int y;
//            
    
    private final List drawables = new LinkedList();
//    private String nomPointInteret;
//    private String descriptionPointInteret;
    
    
     public IDrawable createPoint(int x, int y) {
        Point p = new Point(x,y);
        Dimension dim = new Dimension(20,20);
        return new RectangleDrawable(Color.RED, p, dim);
    }       
            

//    public String getNomPointInteret() {
//        return nomPointInteret;
//    }
//    
//    public String getDescriptionPointInteret(){
//        return descriptionPointInteret;
//    }
    


    public void paint(Graphics g){
        try{

            BufferedImage img = ImageIO.read(new File("src/fr/melysig/images/Nancy.jpg"));
            g.drawImage(img, 0, 0, this);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        for(Iterator  iter = drawables.iterator(); iter.hasNext();){
            IDrawable d = (IDrawable) iter.next();
            d.draw(g);
        }
    }

    public void addDrawable (IDrawable d){

        /**
         * Permet l'ajout d'un unique point interet.
         */
        //drawables.clear();
        /**
         * Fin de fonction de suppression des Points interets.
         */
        
        drawables.add(d);
        System.out.println("Point Interet ajouté");
        repaint();
    }

    public void removeDrawable(IDrawable d){
        drawables.remove(d);
        System.out.println("Point Interet effacé.");
        repaint();
    }

    public void clear(){
        drawables.clear();
        repaint();
    }

    public List findDrawables(Point p) {
	List<IDrawable> l = new ArrayList<IDrawable>();
	for (Iterator iter = drawables.iterator(); iter.hasNext();) {
		IDrawable element = (IDrawable) iter.next();
		if(element.getRectangle().contains(p)){
			l.add(element);
		}
	}
	return l;
    }

    public boolean isFree(Rectangle rect){
        for(Iterator iter = drawables.iterator(); iter.hasNext();){
            IDrawable element = (IDrawable) iter.next();
            //System.out.println(element.getRectangle());
            if(element.getRectangle().intersects(rect)){
                return false;
            }
        } 
        return true;
    }

    public boolean isAlone (IDrawable drawable){
        Rectangle rect = drawable.getRectangle();
        for(Iterator iter = drawables.iterator(); iter.hasNext();){
            IDrawable element = (IDrawable) iter.next();
            //System.out.println(element.getRectangle());
            if(!element.equals(drawable) && element.getRectangle().intersects(rect)) {
                return false;
            }
        }
        return true;
    }


    
    
}
