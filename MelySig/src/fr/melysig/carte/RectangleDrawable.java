/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.melysig.carte;

/**
 *
 * @author Coonax
 * @author Poook
 */

//import fr.melysig.vues.ConsultationVue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class RectangleDrawable extends FormDrawable{
    
//    private final ConsultationVue consultationVue;
    

	public RectangleDrawable(Color color, Point pos, Dimension dim) {
		super(color, pos, dim);
//		this.consultationVue = ConsultationVue.obtenirConsultation();
	}

        public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(rect.x,rect.y,rect.height,rect.width);
//                this.consultationVue.updateIHM(rect.x, rect.y);
//                this.x = rect.x;
//                this.y = rect.y;
                System.out.println("(" + rect.x + "," + rect.y + ")");
		g.setColor(c);
	} 
        
    }


