/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;
import java.awt.*;

/**
 *
 * @author Coonax
 * @author Poook
 */
public abstract class FormDrawable implements IMovableDrawable {
    
    protected Rectangle rect;
    protected Color color;

//    public double getX() {
//        return rect.x;
//    }
//
//    public double getY() {
//        return rect.y;
//    }
    
    
    public FormDrawable(Color color, Point pos, Dimension dim){
        this.color=color;
        this.rect=new Rectangle(pos,dim);
    }
    
    public abstract void draw(Graphics g);
    
    public Rectangle getRectangle(){
        return(Rectangle) rect.clone();
    }
    
    public Point getPosition(){
        Point p=  rect.getLocation();
        p.x = (p.x+ rect.width/2);
        p.y = (p.y+ rect.width/2);
        return p;
    }
    
    public void setPosition(Point p){
        rect.x = (p.x-rect.width/2);
        rect.y = (p.y-rect.height/2);
    }
    
    
}
