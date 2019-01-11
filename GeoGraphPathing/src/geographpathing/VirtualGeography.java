package geographpathing;

import java.util.HashMap;
import java.util.Random;

public final class VirtualGeography {
	public VirtualGeography(int width, int height, int node_dist, double node_dist_variance){
		this.width = width;
		this.height = height;
		this.node_dist = node_dist;
		this.node_dist_variance = node_dist_variance;
		
		rng = new Random(); 
		
		generate();
	}
	
	public void generate(){
		int x = 0;
		int y = 0;
		
		// Initialize the hashmap with a capacity of 
		int avg_count_x = width / node_dist;
		int avg_count_y = height / node_dist;
		int default_capacity = Math.min(MAX_NODES, avg_count_x * avg_count_y);
		nodes = new HashMap(default_capacity);
		
		// We end the generation loop when we've exhausted our vertical space
		while(y <= height){
			// If we reach the end of the horizontal component, reset x and increment y
			if(x > width){
				x = 0;
				y += getRandDist();
				continue;
			}
			
			// Increment the x
			x += getRandDist();
			
			// Verify that we're inbounds
			if(x <= width){
				
			}
		}
	}
	
	private int getRandDist(){
		int dist = node_dist;
		int abs_dist_variance = (int) (node_dist * node_dist_variance);
		dist += rng.nextInt(abs_dist_variance + 1) - (abs_dist_variance / 2);
		return dist;
	}
	
	public static final int MAX_NODES = 1000000;
	public static final long RNG_SEED = 445566;
	
	private final int width; // Meters
	private final int height; // Meters
	private final int node_dist; // Average node distance, in meters
	private final double node_dist_variance; // +/- Node distance variance, 0.0-1.0
	private final Random rng;
	private HashMap<GeoCoord, Node> nodes;
}
