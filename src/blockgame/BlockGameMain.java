/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame;

import blockgame.model.BlockGame;
import blockgame.model.DefaultObjectGenerator;
import blockgame.model.Person;
import blockgame.model.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jonas
 */
public class BlockGameMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        DefaultObjectGenerator dog = new DefaultObjectGenerator();
        dog.generateAllOverwrite();
        
        
        // model
        Person person = new Person(0, 0, "blockgame/textures/person.png");
        World world = new World(96, 48, 16);
        BlockGame model = new BlockGame(world, person);
        // view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBlockGame.fxml"));
        Parent root = loader.load();
        // controller
        FXMLBlockGameController controller = loader.getController();
        controller.setModel(model);
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(event -> System.exit(0)); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
