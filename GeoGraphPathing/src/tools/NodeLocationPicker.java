package tools;

import geographpathing.GeoCoord;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NodeLocationPicker {
	
	public NodeLocationPicker(){
		initializeUI();
	}
	
	public Pane getContainer(){
		return pane;
	}
	
	private void initializeUI(){
		Image image = new Image("map_images/umd-scale-map.png");
		ImageView image_view = new ImageView(image);
		ScrollPane image_holder = new ScrollPane();
		image_holder.setPrefWidth(500);
		image_holder.setPrefHeight(500);
		image_holder.setContent(image_view);
		pane.getChildren().add(image_holder);
	}
	
	Pane pane = new VBox();
	private List<GeoCoord> coords = new ArrayList<>();
}
