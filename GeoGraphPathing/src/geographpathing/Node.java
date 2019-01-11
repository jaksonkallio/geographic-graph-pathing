package geographpathing;

public class Node {
	public Node(VirtualGeography world){
		this.world = world;
	}
	
	public void setGeoCoord(GeoCoord coord){
		this.coord = coord;
	}
	
	public GeoCoord getGeoCoord(){
		return coord;
	}
	
	private GeoCoord coord;
	private final VirtualGeography world;
}
