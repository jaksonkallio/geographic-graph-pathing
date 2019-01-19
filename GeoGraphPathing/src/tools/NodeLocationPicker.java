package tools;

import geographpathing.GeoCoord;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

public class NodeLocationPicker {
	
    public NodeLocationPicker(){
        initializeUI();
    }

    public Pane getContainer(){
        return pane;
    }
    
    private void readImage(){
        File f = null;
        String file_url = "./src/map_images/duluth.jpg";

        try{
          f = new File(file_url);
          buf_img = ImageIO.read(f);
          placeDot(5, 5);
          updateImage();
        }catch(IOException e){
          System.out.println("Could not read image: " + e.getMessage());
        }
    }
    
    private void createNode(int x, int y){
        GeoCoord coord = new GeoCoord((int)(x * scale), (int)(y * scale));
        
        if(!coords.contains(coord)){
            coords.add(coord);
            placeDot(x, y);
            System.out.println("place");
        }
    }
    
    private List<GeoCoord> coordsInRange(int x, int y){
        return null;
    }
    
    private void placeDot(int x, int y){
        int r = 10;
        
        for(int x_i = Math.max(0, x - r); x_i < Math.min(buf_img.getWidth(), x + r); x_i++){
            for(int y_i = Math.max(0, y - r); y_i < Math.min(buf_img.getHeight(), y + r); y_i++){
                if(x_i == x && y_i == y){
                    buf_img.setRGB(x_i, y_i, calcRGBValue(255,0,0));
                }else{
                    buf_img.setRGB(x_i, y_i, calcRGBValue(0,255,0));
                }
            }
        }
        
        updateImage();
    }
    
    private int calcRGBValue(int r, int g, int b){
        return (255<<24) | (r<<16) | (g<<8) | b;
    }

    private void updateImage(){
        
    }
    
    private void initializeUI(){
        image_view = new ImageView();
        readImage();
        image_view.setImage(SwingFXUtils.toFXImage(buf_img, null));
        
        image_view.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if(e.getEventType() == MouseEvent.MOUSE_CLICKED){
                    createNode((int) e.getX(), (int) e.getY());
                }

            }
        });
        
        ScrollPane image_holder = new ScrollPane();
        image_holder.setPrefWidth(500);
        image_holder.setPrefHeight(500);
        image_holder.setContent(image_view);
        pane.getChildren().add(image_holder);
    }

    private Pane pane = new VBox();
    private Set<GeoCoord> coords = new HashSet<>();
    private BufferedImage buf_img;
    private Image map_image;
    ImageView image_view;
    private double scale; // 1 pixel = x meters
}
