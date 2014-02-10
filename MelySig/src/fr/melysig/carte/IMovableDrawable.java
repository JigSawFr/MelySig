/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;
import java.awt.Point;

/**
 *
 * @author Coonax
 * @author Poook
 */
public interface IMovableDrawable extends IDrawable{
    
    
    void setPosition(Point p);
    
    Point getPosition();
}
