package geographpathing;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public Node(VirtualGeography world, int id){
		this.world = world;
		this.id = id;
		this.neighbors = new ArrayList<>();
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Node){
			Node other = (Node) o;
			
			if(other.getID() == this.getID()){
				return true;
			}
		}
		
		return false;
	}
		
	public void setGeoCoord(GeoCoord coord){
		this.coord = coord;
	}
	
	public void addNeighbor(Node neighbor){
		if(!neighbors.contains(neighbor)){
			neighbors.add(neighbor);
		}
	}
	
	public List<Node> getNeighbors(){
		return neighbors;
	}
	
	public GeoCoord getGeoCoord(){
		return coord;
	}
	
	public int getID(){
		return id;
	}
	
	private GeoCoord coord;
	private final VirtualGeography world;
	private final int id;
	private final List<Node> neighbors;
}
