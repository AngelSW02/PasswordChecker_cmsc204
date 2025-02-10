import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the Password Checker JavaFX application.
 * @author Angel Abrigo
 */
public class PasswordDriverFX extends Application {

    /**
     * Launches the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the primary stage and initializes the main scene.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        // Initialize the main UI container
        PasswordMain mainPane = new PasswordMain();

        // Create a scene with the main UI layout
        Scene scene = new Scene(mainPane, 550, 350);

        // Configure the stage settings
        stage.setScene(scene);
        stage.setTitle("Password Checker");
        stage.show();
    }
}
