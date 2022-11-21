import java.util.*;
public class NearestNeighbors{
	//List of point3D
	private List<Point3D> DB ;
	//constructor that take a list of point3D and initialize
	public NearestNeighbors(List<Point3D> DB){
		this.DB = DB;
	}
	/*rangeQuery method that takes a point3D and epsilon 
	and returns a list of all points close to it*/

	public List<Point3D> rangeQuery(Point3D Q,double eps){
		List<Point3D> N = new ArrayList<>();
		for(Point3D Point : DB){
			if(Q.distance(Point)<= eps){
				N.add(Point);
			}

		}
		return N;
	}
}