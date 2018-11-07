package blockgame;

import blockgame.model.BlockGame;
import blockgame.View.BlockGameView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class FXMLBlockGameController {
    private BlockGame model;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane grafischPaneel;
    private BlockGameView view;
    
    @FXML
    private Button loadWorld;

    @FXML
    private Button saveWorld;

    @FXML
    private TextField worldName;
    
    @FXML
    private Label label;

    @FXML
    void initialize() {
        worldName.setFocusTraversable(false);
        grafischPaneel.setOnMouseClicked(this::geklikt);
        grafischPaneel.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        loadWorld.setOnMouseClicked(e -> loadWorld(worldName.getText()));
        saveWorld.setOnMouseClicked(e -> exportWorld(worldName.getText()));
        grafischPaneel.setOnKeyPressed(this::movePerson);

    }
    
    /**
     * Sets the model
     * @param model the model used
     */
    public void setModel(BlockGame model){
        this.model = model;
        view = new BlockGameView(model);
        grafischPaneel.getChildren().add(view);
        grafischPaneel.setPrefWidth(model.getWorld().getSizeX()*model.getWorld().getTextureResolution());
        grafischPaneel.setPrefHeight(model.getGuiTop().getHeight() + model.getWorld().getSizeY()*model.getWorld().getTextureResolution());
        update();
        
    }
    
    /**
     * Sends the coordinates of the left/right click to the model
     * @param e the mousevent of the click
     */
    public void geklikt(MouseEvent e){
        double x = e.getX();
        double y = e.getY();
        
        if(e.getButton().toString().equals("PRIMARY")){
            model.leftClick(x, y);
        }
        else if(e.getButton().toString().equals("SECONDARY")){
            model.rightClick(x, y);
        }
        
        update();
    }
    
    /**
     * exports the currently loaded world
     * @param name the name the world will be called
     */
    public void exportWorld(String name){
        grafischPaneel.requestFocus();
        label.setText("");
        if(name.equals("")){
            label.setText("Geef een naam in!");
        }
        else{
            model.exportWorld(name);
        }
        
    }
    
    /**
     * load the world with name
     * @param name the name of the world that should be loaded
     */
    public void loadWorld(String name){
        grafischPaneel.requestFocus();
        label.setText("");
        if(name.equals("")){
            label.setText("Geef een naam in!");
        }
        else{
            model.loadWorld(name);
            update();
            System.out.println("hmm");
        }
    }
    
    /**
     * Updates the view
     */
    public void update(){
        view.update();
    }
    
    /**
     * moves the person according to the key pressed
     * @param e 
     */
    public void movePerson(KeyEvent e){
        switch (e.getCode()){
            case LEFT:
            case Q:
                System.out.println("a");
                model.movePerson(-1, 0);
                break;
            case RIGHT:
            case D:
                System.out.println("b");
                model.movePerson(1, 0);
                break;
            case UP:
            case Z:
            case SPACE:
                model.jump(1);
                break;
            
        }
        view.updatePerson();
    }
    
    
}
