package mountain;

import java.util.ArrayList;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Bergsmassiv extends Fractal{
	private Point a,b,c;
	private double dev;
	private ArrayList<Side> sideList;

	/** Creates an object that handles Koch's fractal. 
	 * @param length the length of the triangle side
	 */
	public Bergsmassiv(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		sideList = new ArrayList<Side>();
	}

	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Bergsmassiv";
	}	

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c, dev);
	}

	/* 
	 * Recursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if(order==0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}else{
			Point m1 = getMiddle(a,b, dev);
			Point m2 = getMiddle(b,c, dev);
			Point m3 = getMiddle(a,c, dev);
			
			
			fractalLine(turtle, order-1, a, m1, m3, dev/2);
			fractalLine(turtle, order-1, m1, b, m2, dev/2);
			fractalLine(turtle, order-1, m3, m2, c, dev/2);
			fractalLine(turtle, order-1, m2, m3, m1, dev/2);
		}
	}
	private Point getMiddle(Point p1, Point p2, double dev) {
	
		double push = RandomUtilities.randFunc(dev);
		Point temp;
		double tempX = p1.getX() + (p2.getX()-p1.getX())/2;
		double tempY = p1.getY() + (p2.getY()-p1.getY())/2;
		temp = new Point(tempX,tempY + push);
		Side sTemp = new Side(p1, p2, temp);
		for(Side side : sideList) {
			if(side.equals(sTemp)) {
				temp = side.getMid();
				sideList.remove(side);
				return temp;
			}
		}
		sideList.add(sTemp);
		return temp;
	}
	

}
