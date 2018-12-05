package blockgame;

import blockgame.model.BlockGame;
import blockgame.View.BlockGameView;
import blockgame.model.Key;
import blockgame.thread.DayNight;
import blockgame.thread.Ticker;
import blockgame.thread.TimeThread;
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

/**
 * @author Souhaib and Jonas
 */
public class FXMLBlockGameController {
    private BlockGame model;
    
    @FXML
    private Button craftButton;
    
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
    private AnchorPane achtergrond;

    @FXML
    void initialize() {
        worldName.setFocusTraversable(false);
        loadWorld.setFocusTraversable(false);
        saveWorld.setFocusTraversable(false);
        grafischPaneel.setFocusTraversable(true);
        achtergrond.setFocusTraversable(false);
        craftButton.setFocusTraversable(false);
        
        
        grafischPaneel.setOnMouseClicked(this::geklikt);
        loadWorld.setOnMouseClicked(e -> loadWorld(worldName.getText()));
        saveWorld.setOnMouseClicked(e -> exportWorld(worldName.getText()));
        hoofdGrafischPaneel.setOnKeyPressed(this::keyPressed);
        hoofdGrafischPaneel.setOnKeyReleased(this::keyReleased);
        craftButton.setOnMouseClicked (e -> craftmenu());
    }
    
    /**
     * Sets the model
     * @param model The model used.
     */
    public void setModel(BlockGame model){
        this.model = model;
        view = model.getView();
        grafischPaneel.getChildren().add(view);
        grafischPaneel.setPrefWidth(model.getWorldSizeX()*model.getTextureResolution());
        grafischPaneel.setPrefHeight(model.getGUITopHeight() + model.getWorldSizeY()*model.getTextureResolution());
        achtergrond.setTranslateY(model.getGUITopHeight() + 16*model.getTextureResolution());
        achtergrond.setPrefWidth(model.getWorldSizeX()*model.getTextureResolution());
        achtergrond.setPrefHeight((model.getWorldSizeY()-16)*model.getTextureResolution());
        achtergrond.setBackground(new Background(new BackgroundFill(Color.rgb(139,69,19), CornerRadii.EMPTY, Insets.EMPTY)));
        
        DayNight d = new DayNight(this,true);
        Thread t = new Thread(d);
        t.setDaemon(true);
        t.start();
        
        TimeThread tt = new TimeThread(model,this);
        Thread tr = new Thread(tt);
        tr.setDaemon(true);
        tr.start();
       
        Ticker ticker = new Ticker(model);
        Thread tickerT = new Thread(ticker);
        tickerT.setDaemon(true);
        tickerT.start();
        
        update();
        
    }
    
    /**
     * Sends the coordinates of the left/right click to the model.
     * @param e The mousevent of the click.
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
     * Exports the currently loaded world.
     * @param name The name the world will be called.
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
     * Loads the world with name.
     * @param name The name of the world that should be loaded.
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
     * Updates the view.
     */
    public void update(){
        view.update();
    }
    
    /**
     * @param e The keyEvent.
     */
    public void keyPressed(KeyEvent e){
        switch (e.getCode()){
            case LEFT:
            case Q:
                model.addKeyBeingHeld(Key.LEFT);
                break;
            case RIGHT:
            case D:
                model.addKeyBeingHeld(Key.RIGHT);
                break;
            case UP:
            case Z:
            case SPACE:
                model.addKeyBeingHeld(Key.UP);
                break;
            case SHIFT:
                model.addKeyBeingHeld(Key.SPRINT);
                break;
            
        }
    }
    
    /**
     * @param e The keyEvent.
     */
    public void keyReleased(KeyEvent e){
        switch (e.getCode()){
            case LEFT:
            case Q:
                model.removeKeyBeingHeld(Key.LEFT);
                break;
            case RIGHT:
            case D:
                model.removeKeyBeingHeld(Key.RIGHT);
                break;
            case UP:
            case Z:
            case SPACE:
                model.removeKeyBeingHeld(Key.UP);
                break;
            case SHIFT:
                model.removeKeyBeingHeld(Key.SPRINT);
                break;
            
        }
        view.updatePerson();
    }
    
    public void craftmenu(){
        model.getCraft().setGeopend();
        if(model.getCraft().isGeopend()){
            craftButton.setText("Close Crafting");
        }
        else{
            craftButton.setText("Crafting");
        }
        update();
    }
    
    /**
     * Changes the background color
     * @param c The new color for the background.
     */
    public void setBackground(Color c) {
        grafischPaneel.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     *  Changes the label with the time.
     */
    public void updateTimeText() {
        timeLabel.setText("Time: " + model.getHour()+":"+model.getMin()+":"+model.getSec());
    }

}
