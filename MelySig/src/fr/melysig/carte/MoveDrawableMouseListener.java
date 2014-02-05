/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;

import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class MoveDrawableMouseListener extends JCanvasMouseAdapter {
	protected IMovableDrawable drawable;

	
	public MoveDrawableMouseListener(JCanvas canvas) {
		super(canvas);
	}

	
	public void mouseDragged(MouseEvent e) {
		if (drawable != null) {
			drawable.setPosition(e.getPoint());
			canvas.repaint();
		}
	}

	
	public void mousePressed(MouseEvent e) {
		List selectedDrawables = canvas.findDrawables(e.getPoint());
		if (selectedDrawables.size() == 0)
			return;
		drawable = (IMovableDrawable) selectedDrawables.get(0);
	}

	public void mouseReleased(MouseEvent e) {
		drawable = null;
	}
}
