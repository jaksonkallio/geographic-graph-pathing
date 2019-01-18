package geographpathing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class VirtualGeography {
	public VirtualGeography(){
		rng = new Random(RNG_SEED);
		initializeStorage();
	}
	
	public Node[] getRandomNodes(int count){
		Node[] rand_nodes = new Node[count];
		
		for(int i = 0; i < rand_nodes.length; i++){
			rand_nodes[i] = node_list.get(rng.nextInt(node_list.size()));
		}
		
		return rand_nodes;
	}
	
	public Node getNodeAtGeoCoord(GeoCoord c){
		return node_map.get(c);
	}
	
	public void addNode(Node node){
		node_map.put(node.getGeoCoord(), node);
		node_list.add(node);
	}
	
	public void removeNode(Node node){
		node_map.remove(node.getGeoCoord());
		node_list.remove(node);
	}
	
	private void initializeStorage(){
		// Initialize the node map
		node_map = new HashMap<>();
		
		// Initialize node list
		node_list = new ArrayList<>();
	}
	
	public static final long RNG_SEED = 445566;
	
	private final Random rng;
	private HashMap<GeoCoord, Node> node_map;
	private List<Node> node_list;
}
