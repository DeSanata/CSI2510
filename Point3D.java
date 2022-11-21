import java.lang.Math.*;
import java.util.Random;
public class Point3D{
	//instance variables 
	Random l = new Random();
	private int ClusterLabel;
	private double x,y,z;
	//Constructor to initialize instances of variables 
	public Point3D(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		

	}
	//clusterLabel get method
	public int getClusterLabel(){
		return ClusterLabel;
	}
	//method to change the value of clusterLabel 
	public int setClusterLabel(int clusterLabel){
		ClusterLabel = clusterLabel;
		return ClusterLabel;
	}
	//method to get x of the Point3D 
	public double getX(){
		return x;
	}
	//method to get y of the Point3D 
	public double getY(){
		return y;
	}
	//method to get z of the Point3D 
	public double getZ(){
		return z;
	}
	
	//distance calculating method
	public double distance (Point3D pt){
		return Math.sqrt( Math.pow((this.x - pt.getX()),2) + Math.pow((this.y - pt.getY()),2) + Math.pow((this.z - pt.getZ()),2));
	}

}