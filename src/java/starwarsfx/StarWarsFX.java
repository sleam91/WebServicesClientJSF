package starwarsfx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StarWarsFX extends Application {

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("The Planets of Star Wars");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StarWarsFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
