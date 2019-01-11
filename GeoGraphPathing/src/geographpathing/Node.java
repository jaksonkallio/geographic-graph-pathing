package geographpathing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

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
	
	public Path path(Node target){
		int max_check_size = 0;
		
		Path found_path = null;
		
		HashSet<Node> checked = new HashSet<>();
		PriorityQueue<PathCost> check = new PriorityQueue<>();
		
		List<Node> initial_node_list = new ArrayList<>();
		initial_node_list.add(this);
		Path initial_path = new Path(initial_node_list);
		check.add(new PathCost(initial_path, initial_path.last().getGeoCoord().distance(target.getGeoCoord())));
		checked.add(this);
		
		while(!check.isEmpty()){
			Path current_path = check.poll().path;
			
			// Found
			if(current_path.last().equals(target)){
				found_path = current_path;
				break;
			}
			
			// Not found
			for(Node neighbor : current_path.last().getNeighbors()){
				if(!checked.contains(neighbor)){
					// Add our check to the set of checked nodes
					checked.add(neighbor);
					List<Node> path_nodes = current_path.getNodes();
					path_nodes.add(neighbor);
					Path new_path = new Path(path_nodes);
					check.add(new PathCost(new_path, new_path.last().getGeoCoord().distance(target.getGeoCoord())));
				}
			}
			
			if(check.size() > max_check_size){
				max_check_size = check.size();
			}
		}
		
		System.out.println("Largest check queue size: " + max_check_size);
		System.out.println("Checked set size: " + checked.size());
		
		if(found_path != null){
			System.out.println("Path length: " + found_path.length());
		}else{
			System.out.println("No path found.");
		}
		
		return found_path;
	}
	
	private class PathCost implements Comparable<PathCost> {
		public PathCost(Path path, int cost) {
			this.path = path;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(PathCost o) {
			return this.cost - o.cost;
		}
		
		public final Path path;
		public final int cost;
	}
	
	private GeoCoord coord;
	private final VirtualGeography world;
	private final int id;
	private final List<Node> neighbors;
}
