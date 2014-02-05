/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class SimpleMouseListener extends JCanvasMouseListener {
    
    public SimpleMouseListener(JCanvas canvas){
        super(canvas);
    }
    
    protected void rightClickAction(MouseEvent e){
        List selectedDrawables = canvas.findDrawables(e.getPoint());
        if (selectedDrawables.size() == 0) return;
        IDrawable drawable = (IDrawable) selectedDrawables.get(0);
        canvas.removeDrawable(drawable);
    }
    
    protected void leftClickAction(MouseEvent e){
        Point p = e.getPoint();
        IDrawable rect = createDrawable(e);
        if (canvas.isFree(rect.getRectangle())) {
            canvas.addDrawable(rect);
        }
    }
    
    private IDrawable createDrawable(MouseEvent e) {
        Point p = e.getPoint();
        Dimension dim = new Dimension(20,20);
        return new RectangleDrawable(Color.RED, p, dim);
    }
}