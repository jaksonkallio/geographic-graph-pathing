/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geographpathing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.NodeLocationPicker;

/**
 *
 * @author sabreok
 */
public class GeoGraphPathing extends Application {

	@Override
	public void start(Stage stage) {
		NodeLocationPicker node_location_picker = new NodeLocationPicker();
	
		stage.setTitle("Geographic Pathing Tool");
		Scene scene = new Scene(node_location_picker.getContainer());
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
		/*VirtualGeography world = new VirtualGeography();
		
		Node[] rand_nodes = world.getRandomNodes(2);
		System.out.println("Finding path from "+rand_nodes[0].getGeoCoord().toString()+" to "+rand_nodes[1].getGeoCoord().toString());
		Path path = rand_nodes[0].path(rand_nodes[1]);*/
	}
}
