import java.util.List;
import java.io.*;

public class TestDBScan {

	public static void main(String[] args) throws IOException {
		
        String file = args[0];
        double eps = Double.parseDouble(args[1]);
        int minPts = Integer.parseInt(args[2]);
        List<Point3D> sc = DBScan.read(file);
         DBScan liste= new DBScan(sc);

        liste.setEps(eps);
        liste.setMinPts(minPts);
        liste.findClusters();
        liste.save(file);                                         

	}

}
