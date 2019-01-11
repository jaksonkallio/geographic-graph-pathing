package geographpathing;

public class GeoCoord {
	public GeoCoord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int distance(GeoCoord o){
		return 0;
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
