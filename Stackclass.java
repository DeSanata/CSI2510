import java.util.Stack;
//My Stack class
public class Stackclass {
	private Stack<Point3D> Sstack;
	public Stackclass() {
		Sstack = new Stack<Point3D>();
		
	}
	public boolean IsEmpty() {
		return Sstack.isEmpty();
		
	}
	public void push(Point3D p) {
		Sstack.push(p);
		
	}
	public Point3D pop() {
		return Sstack.pop();
	}

}
