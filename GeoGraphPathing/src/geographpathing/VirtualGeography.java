package geographpathing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class VirtualGeography {
	public VirtualGeography(int width, int height, int node_dist, double node_dist_variance, int neighbor_count){
		this.width = width;
		this.height = height;
		this.node_dist = node_dist;
		this.node_dist_variance = node_dist_variance;
		this.neighbor_count = neighbor_count;
		
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
	private final int neighbor_count; // Any nodes within node_dist * neighborship_radius become neighbors
	private final double node_dist_variance; // +/- Node distance variance, 0.0-1.0
	private final Random rng;
	private HashMap<GeoCoord, Node> nodes;
	private List<Node> node_list;
	private long timer_start;
	private long last_progress_print;
}
