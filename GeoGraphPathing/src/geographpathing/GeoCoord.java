package geographpathing;

import java.util.ArrayList;
import java.util.List;

public class GeoCoord {
	public GeoCoord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int distance(GeoCoord o){
		return 0;
	}
	
	public List<GeoCoord> radius(int r){
		ArrayList<GeoCoord> contents = new ArrayList<>();
		
		for(int r_x = x - r; r_x <= x + r; r_x++){
			for(int r_y = y - r; r_y <= y + r; r_y++){
				contents.add(new GeoCoord(r_x, r_y));
			}	
		}
		
		return contents;
	}
	
	@Override
	public int hashCode(){
		int tmp = y + ((x + 1) / 2);
        return (x + (int) Math.pow(tmp, 2));
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof GeoCoord){
			GeoCoord other = (GeoCoord) o;
			
			if(other.x == this.x && other.y == this.y){
				return true;
			}
		}
		
		return false;
	}
	
	public final int x;
	public final int y;
}
