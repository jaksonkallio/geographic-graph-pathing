/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geographpathing;

/**
 *
 * @author sabreok
 */
public class GeoGraphPathing {
	public static void main(String[] args) {
		VirtualGeography world = new VirtualGeography(1000, 1000, 10, 0.2, 2.0);
		
		Node[] rand_nodes = world.getRandomNodes(2);
		System.out.println("Finding path from "+rand_nodes[0].getGeoCoord().toString()+" to "+rand_nodes[1].getGeoCoord().toString());
		Path path = rand_nodes[0].path(rand_nodes[1]);
		System.out.println(path.toString());
	}
	
}
