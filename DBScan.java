import java.util.*;
import java.io.*;
import java.util.Random;
import java.io.BufferedReader;
//class DBScan 
public class DBScan{
	//Variables
	private double Eps;
	private int NumberOfClusters, MinPts;
	private List<Point3D> ListPoint = new ArrayList<>();
	//Constructeur DBScan qui prend une liste de tous les points
	public DBScan(List<Point3D> ListPoint){
		this.ListPoint = ListPoint ;
	}
	// Eps set method
	
	public double setEps(double eps){
		Eps = eps;
		return Eps;
		
	}
	// MinPts set method
	public int setMinPts(int minPts){
		MinPts = minPts;
		return MinPts;
	}
	// methode findcluster qui execute l'algorithme DBScan
	public void findClusters(){
		
		int Noise = -1;
		//compteur cluster
		NumberOfClusters = 0;
		//boucle for qui parcours la liste de points point par point
		for(  Point3D P : ListPoint ){
			//verifier si le point est dejà traité si oui continuer
			if(P.getClusterLabel()!= 0){
				continue;
				
				
			}
			NearestNeighbors A = new NearestNeighbors(ListPoint);
			List<Point3D> N = new ArrayList<>() ;
			//trouver tous les points proches
			N = A.rangeQuery( P,Eps);
			//verifier la densité
			if(N.size()<MinPts){
				//assigné Noise à label
				P.setClusterLabel(Noise);
				

				continue;
			}
			// cluster label suivant
			
			++  NumberOfClusters;
			//le Label initial du point
			P.setClusterLabel(NumberOfClusters);
			Stackclass S = new Stackclass();
			//Neighbors to expand
			for(Point3D U : N){
				S.push(U) ;

			}
			
			
			while(!S.IsEmpty()){
				//Process point Q
                
				Point3D Q = (Point3D) S.pop();
				//Noise becomes border pt
				if(Q.getClusterLabel()==Noise  ){
					Q.setClusterLabel( NumberOfClusters);
					
				}
				//Previously processed
				if(Q.getClusterLabel()!= 0){
					continue;
					

				}
				//Label neighbor
				Q.setClusterLabel( NumberOfClusters);
				
				N = A.rangeQuery(Q,Eps);
				for(Point3D K : N){
					//Density check
					if(N.size()>=MinPts){
						//Add neighbors to stack
						S.push(K);
					}
				}
				
				

			}



		}
		}
	//NumberOfClusters get method
	public int getNumberOfClusters(){
		return NumberOfClusters;
	}
	//List of point get method
	public List<Point3D> getPoints(){
		return ListPoint;

	}
	
	// the read static method that accept a filename and returns a list of Point3D
	public static List<Point3D> read(String filename) throws IOException{
		
		List<Point3D> MyList = new ArrayList<>();
       BufferedReader br = null;
       String line = "";
       try {
        br = new BufferedReader(new FileReader(filename));
        int j=0;
        while ( (line = br.readLine()) != null ) { //we read line by line
        	if(j<2){
        		j++;

        		continue;
        	}
        	
        String[] Coordonate = line.split(",");
        Point3D xyz = new Point3D(Double.parseDouble(Coordonate[0]), Double.parseDouble(Coordonate[1]), Double.parseDouble(Coordonate[2]));
        MyList.add(xyz);
        }

    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
    catch (IOException e) {
        e.printStackTrace();
    } 
    finally {
        if (br != null) {
            try {
                br.close();
    } 
        catch (IOException e) {
            e.printStackTrace();
    }
    }
    }
    





    return MyList;


		

	}
	//the save method that saves all the points with their cluster label and associated RGB color
	
	public void save(String filename){
		//filename = "data_cluster_"+eps+"_"+minPts+"_"+NumberOfClusters+".csv";
		   PrintWriter printWriter =null; 

            try {
                printWriter = new PrintWriter(filename + "_clusters_" + Eps + "_" + MinPts +"_"+ NumberOfClusters + ".csv");
                String line1 = "x,y,z,C,R,G,B";
                printWriter.println(line1);
                double R,G,B;
                Random rd = new Random();
                for(int j=0;j<=NumberOfClusters;j++) {
                	R = rd.nextDouble();
                	G = rd.nextDouble();
                	B = rd.nextDouble();
                
                for(Point3D W : ListPoint) {
                	String [] Tab = new String[7];
                	String Wx,Wy,Wz,Wcluster,Wr,Wg,Wb;
                	Wx = String.valueOf(W.getX());
                	Wy = String.valueOf(W.getY());
                	Wz = String.valueOf(W.getZ());
                	Wcluster = String.valueOf(W.getClusterLabel());
                	if(j==0 && W.getClusterLabel()==0) {
                		Wr = String.valueOf(0.9);
                    	Wg =String.valueOf(0.6);
                    	Wb = String.valueOf(0.5);
                    	Tab [0]= Wx;
                    	Tab[1] = Wy;
                    	Tab[2] =Wz;
                    	Tab[3]  = Wcluster;
                    	Tab[4] =Wr;
                    	Tab[5] = Wg;
                    	Tab [6] = Wb;
                    	String line = Tab[0] + "," + Tab[1] + "," + Tab[2] + "," + Tab[3] + "," + Tab[4] + "," + Tab[5] + "," + Tab[6];
                		printWriter.println(line);
                		
                	}
                	else if(j!=0 && W.getClusterLabel()==j){
                		Wr = String.valueOf(R);
                    	Wg =String.valueOf(G);
                    	Wb = String.valueOf(B);
                    	Tab [0]= Wx;
                    	Tab[1] = Wy;
                    	Tab[2] =Wz;
                    	Tab[3]  = Wcluster;
                    	Tab[4] =Wr;
                    	Tab[5] = Wg;
                    	Tab [6] = Wb;
                    	String line = Tab[0] + "," + Tab[1] + "," + Tab[2] + "," + Tab[3] + "," + Tab[4] + "," + Tab[5] + "," + Tab[6];
                		printWriter.println(line);
                		
                	}
                	
                	
                	
                	

                	}
                }

                    
                }
                catch (FileNotFoundException e) {
                e.printStackTrace();
        } 
            catch (Exception e) {
                e.printStackTrace();
        }
            finally {
                if (printWriter != null) {
                    try {
                        printWriter.close();
        } 
                    catch (Exception e) {
                        e.printStackTrace(); 
                }
    }
        
        }

            } 
            

	

}
