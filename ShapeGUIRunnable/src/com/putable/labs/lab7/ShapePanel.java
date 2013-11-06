package com.putable.labs.lab7;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * A ShapePanel class that is a customized version of a JPanel that overrides the PaintComponent method and adds
 * a little of our own stuff to it
 */
public class ShapePanel extends JPanel {
	
	private List<Shape>shapes;
	private int dimen; 
	/**
	 * The enum {@link Shape} defines the 3 shapes that this {@link ShapePanel}
	 * can draw.
	 * 
	 * @author BKey
	 * 
	 */
	public enum Shape {
		SQUARE, CIRCLE, TRIANGLE;
	}
	/*
	 * Constructor for a ShapePanel which is a slightley customized JPanel
	 */
	public ShapePanel(List<Shape> shapes, int dimen){
		this.shapes = shapes; 
		this.dimen = dimen; 
	}

	/**
	 * The driver for this lab 7.
	 * 
	 * @param args
	 *            is an array of {@code Strings} which hold the names of shapes
	 *            to draw.
	 */
	public static void main(String[] args) {
			
		List<Shape> shapesToDraw = new ArrayList<Shape>();
		String currentShapeName = "";

		if (args.length <= 0)
			System.out
					.println("Please provide arguments as to what shape(s) (Square, Circle or Triangle) you would like to draw.");
		else {
			// fill the list
			for (int i = 0; i < args.length; i++) {
				// can't switch on Strings in Java < 1.7... Gah.
				currentShapeName = args[i];
				if (currentShapeName.equalsIgnoreCase("Circle"))
					shapesToDraw.add(Shape.CIRCLE);
				else if (currentShapeName.equalsIgnoreCase("Square"))
					shapesToDraw.add(Shape.SQUARE);
				else if (currentShapeName.equalsIgnoreCase("Triangle"))
					shapesToDraw.add(Shape.TRIANGLE);
				else {
					// I don't know what shape this is. Explode.
					System.out
							.println("This program only draws Squares, Circles or Triangles. Sorry.");
					System.exit(0);
				}
			}

			JFrame mainFrame = new JFrame("Shape Artist");
			int maxSize = 500;
			JPanel shapePanel = new ShapePanel(shapesToDraw, maxSize);

			shapePanel.setPreferredSize(new Dimension(maxSize, maxSize));

			// add the JPanel to the pane
			mainFrame.getContentPane().add(shapePanel, BorderLayout.CENTER);

			// clean up
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.pack();
			mainFrame.setResizable(false);
			mainFrame.setVisible(true);
		}
	}
	
	/**
	* Uses the {@code Graphics} object to draw each shape in the
	* {@code shapesToDraw} {@code ArrayList}, exactly once, at a random
	* size. More specifically, a Pseudo Random Number generator is used to
	* choose a random {@code int} between 0 and {@code maxSize} (which is the
	* maximum size of the {@code JFrame}). The value that the PRNG generates is
	* either:
	* 
	* The diameter, if drawing a {@link Shape.CIRCLE}
	* The length of a side, if drawing a {@link Shape.SQUARE}
	* The length of a side, if drawing an equilaterial {@link Shape.Triangle}
	* 
	* Each {@link Shape} should be drawn such that it exists in the center of
	* the window. That is, the center of each {@link Shape} is exactly
	* {@code getWidth()/2} for the x-coordinate and {@code getHeight()/2} for
	* the y-coordinate. 
	* All {@link Shape.CIRCLE}s should be {@code Color.RED}, all
	* {@link Shape.SQUARE}s should be {@code Color.BLUE} and all
	* {@link Shape.TRIANGLE}s should be {@code Color.GREEN}. 
	* 
	* NOTE: The height of an equilateral triangle is defined as
	* {@code 1/2*sqrt(3)*l} where {@code l} is the length of the side.
	* 
	* @param g
	*     the {@code Graphics} object in which you will use to draw the
	*     {@link Shape}s.
	*/
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < shapes.size(); i++){
			Random r = new Random();
			if(shapes.get(i) == Shape.CIRCLE){
				//not sure if just seeding with 500 works like i think 
				int diam = r.nextInt(500);
				g.setColor(Color.RED);
				g.drawOval((dimen-diam)/2, (dimen-diam)/2, diam, diam);	
			}
			if(shapes.get(i) == Shape.SQUARE){
				int len = r.nextInt(500);
				g.setColor(Color.BLUE);
				g.drawRect((dimen-len)/2, (dimen-len)/2, len, len);
			}
			if(shapes.get(i) == Shape.TRIANGLE){
				int l = r.nextInt(500);
				Point one = new Point(dimen/2,(int)(dimen-(Math.sqrt(3)/2)*l)/2);
				Point two = new Point((dimen-l)/2,(int)(dimen+(Math.sqrt(3)/2)*l)/2);
				Point three = new Point((dimen+l)/2,(int)(dimen+(Math.sqrt(3)/2)*l)/2);
				g.setColor(Color.GREEN);
				g.drawLine(one.x, one.y, two.x, two.y);
				g.drawLine(two.x, two.y, three.x, three.y);
				g.drawLine(three.x, three.y, one.x, one.y);
			}
			
		}
	}

}
