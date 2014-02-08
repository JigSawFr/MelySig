/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;
import fr.melysig.models.Lieux;
import fr.melysig.process.LieuProcess;
import fr.melysig.process.PointInteretProcess;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class SimpleMouseListener extends JCanvasMouseListener {
    private Lieux lieux;
    
    
    public SimpleMouseListener(JCanvas canvas, Lieux lieux){
        super(canvas);
        this.lieux = lieux;
        
        
    }
    
    protected void rightClickAction(MouseEvent e){
        List selectedDrawables = canvas.findDrawables(e.getPoint());
        if (selectedDrawables.size() == 0) return;
        IDrawable drawable = (IDrawable) selectedDrawables.get(0);
        Point p = e.getPoint();
        int confirm = JOptionPane.showConfirmDialog(canvas, "Voulez vous vraiment supprimer ce point?", "Confirmer suppression", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            LieuProcess.getInstance().setCurentPointInteret(lieux, p.x, p.y);
            LieuProcess.getInstance().supprimerCurentPointInteret(lieux);
        }
    }
    
    
    protected void leftClickAction(MouseEvent e){
        Point p = e.getPoint();
        IDrawable rect = canvas.createPoint(p.x, p.y, Color.BLUE);
        if (canvas.isFree(rect.getRectangle())) {
            String libelle = JOptionPane.showInputDialog(canvas, "Libellé point d'intéret" );
            if (libelle != null) {
                PointInteretProcess.getInstance().creerPointInteret(lieux, libelle, p.x, p.y);
            }
        }
        LieuProcess.getInstance().setCurentPointInteret(lieux, p.x, p.y);
        
    }
    
}
