package mountain;

public class Side {
	private Point a, mid, b;

	public Side(Point a, Point b, Point mid) {
		this.a = a;
		this.b = b;
		this.mid = mid;
	}

	public boolean equals(Object obj) {
		
		if (obj instanceof Side) {
			Side p = (Side) obj;
			return (a.equals(p.a) && b.equals(p.b)) || (a.equals(p.b) && b.equals(p.a));
		}else{
			return false;
		}
	}
	public Point getMid(){
		return mid;
	}

}
