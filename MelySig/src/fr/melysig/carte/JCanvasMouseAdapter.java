/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Coonax
 * @author Poook
 */
public class JCanvasMouseAdapter extends JCanvasMouseListener implements MouseMotionListener {
    	public JCanvasMouseAdapter(JCanvas canvas) {
		super(canvas);
		canvas.addMouseMotionListener(this);
		
	}

	public void mouseDragged(MouseEvent e) {
			
	}

	
	public void mouseMoved(MouseEvent e) {
			
	}
    
}
