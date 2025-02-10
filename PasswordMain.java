import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * Main UI class for the Password Checker application.
 * @author Angel Abrigo
 */
public class PasswordMain extends BorderPane {
    private Label passwordLabel, passwordALabel;
    private Label instruction1Label, instruction2Label, instruction3Label, instruction4Label;
    private Label instruction5Label, instruction6Label;
    private TextField passwordText, passwordAText;
    private Button checkPwdButton, exitButton, checkPwdsInFileButton;
    private DecimalFormat format = new DecimalFormat("#0.000");
    private Alert alert = new Alert(AlertType.INFORMATION);
    private PasswordCheckerUtility pwdChecker;

    /**
     * Constructor to set up the Password Checker UI.
     */
    public PasswordMain() {
        // Instructions panel
        VBox instructionPanel = new VBox();
        instruction1Label = new Label("Use the following rules when creating your passwords:");
        instruction2Label = new Label("\t1. Length must be greater than 6; a strong password contains at least 10 characters.");
        instruction3Label = new Label("\t2. Must contain at least one uppercase letter.");
        instruction4Label = new Label("\t3. Must contain at least one lowercase letter.");
        instruction5Label = new Label("\t4. Must contain at least one numeric character.");
        instruction6Label = new Label("\t5. May not have more than two of the same character in sequence.");

        instructionPanel.getChildren().addAll(instruction1Label, instruction2Label, instruction3Label,
                instruction4Label, instruction5Label, instruction6Label);
        instructionPanel.setAlignment(Pos.CENTER_LEFT);
        instructionPanel.setPadding(new Insets(10));

        // Password input panel
        HBox passwordInputPanel = new HBox();
        passwordLabel = new Label("Password:");
        passwordText = new TextField();
        passwordInputPanel.getChildren().addAll(passwordLabel, passwordText);
        passwordInputPanel.setAlignment(Pos.CENTER);
        passwordInputPanel.setPadding(new Insets(10));

        HBox passwordConfirmPanel = new HBox();
        passwordALabel = new Label("Re-type Password:");
        passwordAText = new TextField();
        passwordConfirmPanel.getChildren().addAll(passwordALabel, passwordAText);
        passwordConfirmPanel.setAlignment(Pos.CENTER);
        passwordConfirmPanel.setPadding(new Insets(10));

        VBox passwordPanel = new VBox(passwordInputPanel, passwordConfirmPanel);
        passwordPanel.setAlignment(Pos.CENTER);

        // Buttons panel
        checkPwdsInFileButton = new Button("Check Passwords in File");
        checkPwdsInFileButton.setTooltip(new Tooltip("Select to read passwords from a file"));
        checkPwdsInFileButton.setOnAction(event -> {
            try {
                readFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkPwdButton = new Button("Check Password");
        checkPwdButton.setTooltip(new Tooltip("Select to check a password."));
        checkPwdButton.setOnAction(event -> checkPassword());

        exitButton = new Button("Exit");
        exitButton.setTooltip(new Tooltip("Select to close the application"));
        exitButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        HBox buttonPanel = new HBox(10, checkPwdButton, checkPwdsInFileButton, exitButton);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(10));

        // Setting up the layout
        setTop(instructionPanel);
        setCenter(passwordPanel);
        setBottom(buttonPanel);
    }

    /**
     * Checks if the entered password meets the validity requirements.
     */
    public void checkPassword() {
        String passwordString = passwordText.getText();
        String passwordAString = passwordAText.getText();
        try {
            if (!PasswordCheckerUtility.comparePasswordsWithReturn(passwordString, passwordAString)) {
                throw new UnmatchedException();
            }

            if (PasswordCheckerUtility.isValidPassword(passwordString)) {
                if (PasswordCheckerUtility.isWeakPassword(passwordString)) {
                    alert.setContentText("Password is OK but weak.");
                } else {
                    alert.setContentText("Password is valid.");
                }
            } else {
                alert.setContentText("Password is not valid.");
            }
            alert.showAndWait();
        } catch (UnmatchedException ex) {
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception ex) {
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Reads a file and validates passwords from it.
     */
    public void readFile() {
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null) {
            ArrayList<String> passwords = new ArrayList<>();
            try (Scanner input = new Scanner(selectedFile)) {
                while (input.hasNext()) {
                    passwords.add(input.next());
                }
                ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);
                String results;
                String title;
                if (invalidPasswords.isEmpty()) {
                    results = "All passwords are valid!";
                    title = "Passwords";
                } else {
                    results = "Invalid Passwords:\n" + String.join("\n", invalidPasswords);
                    title = "Invalid Passwords";
                }
                JOptionPane.showMessageDialog(null, results, title, JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "File Error", JOptionPane.PLAIN_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Main method to launch the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        javafx.application.Application.launch(PasswordDriverFX.class, args);
    }
}
