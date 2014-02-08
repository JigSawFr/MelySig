/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;

import fr.melysig.models.Lieux;
import fr.melysig.models.PointsInterets;
import fr.melysig.process.LieuProcess;
import fr.melysig.process.PointInteretProcess;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class MoveDrawableMouseListener extends JCanvasMouseAdapter {
	protected IMovableDrawable drawable;
        protected Lieux lieu;
        protected boolean drag = false;

	
	public MoveDrawableMouseListener(JCanvas canvas, Lieux lieu) {
		super(canvas);
                this.lieu = lieu;
	}

	
	public void mouseDragged(MouseEvent e) {
		if (drawable != null) {
			drawable.setPosition(e.getPoint());
			canvas.repaint();
                        drag = true;
		}
	}

	
	public void mousePressed(MouseEvent e) {
		List selectedDrawables = canvas.findDrawables(e.getPoint());
		if (selectedDrawables.size() == 0)
			return;
                Point p = e.getPoint();
                LieuProcess.getInstance().setCurentPointInteret(lieu, p.x, p.y);
                selectedDrawables = canvas.findDrawables(e.getPoint());
		drawable = (IMovableDrawable) selectedDrawables.get(0);
               
	}

	public void mouseReleased(MouseEvent e) {
            if (drag) {    
                Point p = e.getPoint();
                PointsInterets pointCourant = lieu.getPointInteretCourant();
                pointCourant.setX(p.x);
                pointCourant.setY(p.y);
                PointInteretProcess.getInstance().mettreAjourPointInteret(lieu, pointCourant);
		drag = false;
            }
            drawable = null;
	}
}
