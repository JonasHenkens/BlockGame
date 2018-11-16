package blockgame;

import blockgame.model.BlockGame;
import blockgame.View.BlockGameView;
import blockgame.model.Time;
import blockgame.thread.DayNight;
import blockgame.thread.TimeTread;
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
    private Time time=new Time();
    
    @FXML
    private AnchorPane hoofdGrafischPaneel;
    
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
    private Label timeLabel;

    @FXML
    void initialize() {
        worldName.setFocusTraversable(false);
        loadWorld.setFocusTraversable(false);
        saveWorld.setFocusTraversable(false);
        grafischPaneel.setFocusTraversable(true);
        
        
        grafischPaneel.setOnMouseClicked(this::geklikt);
        loadWorld.setOnMouseClicked(e -> loadWorld(worldName.getText()));
        saveWorld.setOnMouseClicked(e -> exportWorld(worldName.getText()));
        hoofdGrafischPaneel.setOnKeyPressed(this::movePerson);

    }
    
    /**
     * Sets the model
     * @param model the model used
     */
    public void setModel(BlockGame model){
        this.model = model;
        view = model.getView();
        grafischPaneel.getChildren().add(view);
        grafischPaneel.setPrefWidth(model.getWorldSizeX()*model.getTextureResolution());
        grafischPaneel.setPrefHeight(model.getGUITopHeight() + model.getWorldSizeY()*model.getTextureResolution());
        
        DayNight d = new DayNight(this,true);
        Thread t = new Thread(d);
        t.setDaemon(true);
        t.start();
        
        TimeTread tt = new TimeTread(time,this);
        Thread tr = new Thread(tt);
        tr.setDaemon(true);
        tr.start();
       
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
        label.setText("");
        if(name.equals("")){
            label.setText("Geef een naam in!");
        }
        else{
            model.exportWorld(name);
        }
        grafischPaneel.requestFocus();
        
    }
    
    /**
     * load the world with name
     * @param name the name of the world that should be loaded
     */
    public void loadWorld(String name){
        label.setText("");
        if(name.equals("")){
            label.setText("Geef een naam in!");
        }
        else{
            model.loadWorld(name);
            update();
        }
        grafischPaneel.requestFocus();
    }
    
    /**
     * Updates the view
     */
    public void update(){
        view.update();
        model.updateGui();
        grafischPaneel.requestFocus();
    }
    
    /**
     * moves the person according to the key pressed
     * @param e 
     */
    public void movePerson(KeyEvent e){
        switch (e.getCode()){
            case LEFT:
            case Q:
                model.changePersonSpeed(-16 - model.getPersonVx(), 0);
                break;
            case RIGHT:
            case D:
                model.changePersonSpeed(16 - model.getPersonVx(), 0);
                break;
            case UP:
            case Z:
            case SPACE:
                if(model.getPersonVy() == 0.0){
                    model.changePersonSpeed(0, -7);
                }
                break;
            
        }
        view.updatePerson();
    }
    
    public void setBackground(Color k) {
        grafischPaneel.setBackground(new Background(new BackgroundFill(k, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void updateTimeText() {
        timeLabel.setText(time.toString());
    }
    
}
