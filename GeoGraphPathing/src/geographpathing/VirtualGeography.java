package geographpathing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class VirtualGeography {
	public VirtualGeography(int width, int height, int node_dist, double node_dist_variance, double neighborship_radius){
		this.width = width;
		this.height = height;
		this.node_dist = node_dist;
		this.node_dist_variance = node_dist_variance;
		this.neighborship_radius = neighborship_radius;
		
		rng = new Random(RNG_SEED); 
		
		try {
			generate();
		}catch(InvalidGenerateException ex){
			System.out.println("Could not generate geography: " + ex.message());
		}
	}
	
	public void generate() throws InvalidGenerateException {
		initializeStorage();
		createNodes();
		createEdges();
		printStats();
	}
	
	public Node[] getRandomNodes(int count){
		Node[] rand_nodes = new Node[count];
		
		for(int i = 0; i < rand_nodes.length; i++){
			rand_nodes[i] = node_list.get(rng.nextInt(node_list.size()));
		}
		
		return rand_nodes;
	}
	
	private void printStats(){
		System.out.println("Node count: " + getNodeCount());
	}
	
	private void createNodes() throws InvalidGenerateException {
		startTimer();
		int node_count = getNodeCount();
		
		// Check if we breach the maximum nodes setting
		if(node_count > MAX_NODES){
			throw new InvalidGenerateException("Maximum node count reached");
		}
		
		int counter = 0;
		while(nodes.size() < node_count){
			printProgress("Creating nodes", nodes.size(), node_count);
			GeoCoord coord = new GeoCoord(rng.nextInt(width), rng.nextInt(height));
			
			if(!nodes.containsKey(coord)){
				Node node = new Node(this, counter);
				node.setGeoCoord(coord);
				nodes.put(node.getGeoCoord(), node);
				node_list.add(node);
			}
			
			counter++;
		}
		
		printComplete();
	}
	
	private void createEdges(){
		startTimer();
		int counter = 0;
		for(int i = (node_list.size() - 1); i >= 0; i--) {
			printProgress("Creating edges", counter, nodes.size());
			Node node = node_list.get(i);
			
			for(GeoCoord near_coords : node.getGeoCoord().radius((int)(node_dist * neighborship_radius))){
				if(nodes.containsKey(near_coords)){
					Node near_node = nodes.get(near_coords);
					near_node.addNeighbor(node);
					node.addNeighbor(near_node);
				}
			}
			
			// Node is lonely, delete
			if(node.getNeighbors().isEmpty()){
				nodes.remove(node.getGeoCoord());
				node_list.remove(i);
			}
			
			counter++;
		}
		printComplete();
	}
	
	private void initializeStorage(){
		// Initialize the hashmap with a capacity
		nodes = new HashMap(getNodeCount());
		node_list = new ArrayList<>();
	}
	
	private int getNodeCount(){
		int avg_count_x = width / node_dist;
		int avg_count_y = height / node_dist;
		return avg_count_x * avg_count_y;
	}
	
	private int getRandDist(){
		int dist = node_dist;
		int abs_dist_variance = (int) (node_dist * node_dist_variance);
		dist += rng.nextInt(abs_dist_variance + 1) - (abs_dist_variance / 2);
		return dist;
	}
	
	private void startTimer(){
		timer_start = time();
	}
	
	private long timerElapsed(){
		return time() - timer_start;
	}
	
	private long time(){
		return System.currentTimeMillis();
	}
	
	private void printProgress(String process, int completed, int total){
		if((time() - last_progress_print) >= 250 || completed == total){
			int percent = (int)(100.0 * completed / total);
			System.out.println(process + ": " + percent + "%");
			last_progress_print = time();
		}
	}
	
	private void printComplete(){
		System.out.println("Done, " + timerElapsed() + "ms");
	}
	
	public static final int MAX_NODES = 10000000;
	public static final long RNG_SEED = 445566;
	
	private final int width; // Meters
	private final int height; // Meters
	private final int node_dist; // Average node distance, in meters
	private final double neighborship_radius; // Any nodes within node_dist * neighborship_radius become neighbors
	private final double node_dist_variance; // +/- Node distance variance, 0.0-1.0
	private final Random rng;
	private HashMap<GeoCoord, Node> nodes;
	private List<Node> node_list;
	private long timer_start;
	private long last_progress_print;
}
