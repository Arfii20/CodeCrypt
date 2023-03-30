package Encryption;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    Stage window;
    EncryptionDecryption encDec;

    String enc = "";
    String dec = "";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.window = stage;
        FXMLLoader primaryXML = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader EncryptDecryptXML = new FXMLLoader(HelloApplication.class.getResource("Encrypt_or_Decrypt.fxml"));
        FXMLLoader encryptFirstXML = new FXMLLoader(HelloApplication.class.getResource("Encrypt_Customise.fxml"));
        FXMLLoader decryptFirstXML = new FXMLLoader(HelloApplication.class.getResource("Decrypt_Customise.fxml"));
        FXMLLoader encryptValueXML = new FXMLLoader(HelloApplication.class.getResource("EnterValueEncrypt.fxml"));
        FXMLLoader decryptValueXML = new FXMLLoader(HelloApplication.class.getResource("EnterValueDecrypt.fxml"));
        FXMLLoader encryptFinalXML = new FXMLLoader(HelloApplication.class.getResource("Encrypt_page.fxml"));
        FXMLLoader decryptFinalXML = new FXMLLoader(HelloApplication.class.getResource("Decrypt_page.fxml"));
        FXMLLoader ThankYouXML = new FXMLLoader(HelloApplication.class.getResource("ThankYouPage.fxml"));

        Scene primaryscene = new Scene(primaryXML.load(), 1280, 720);
        Scene EncryptDecryptScene = new Scene(EncryptDecryptXML.load(), 1280, 720);
        Scene encryptFirstScene = new Scene(encryptFirstXML.load(), 1280, 720);
        Scene decryptFirstScene = new Scene(decryptFirstXML.load(), 1280, 720);
        Scene encryptValueScene = new Scene(encryptValueXML.load(), 1280, 720);
        Scene decryptValueScene = new Scene(decryptValueXML.load(), 1280, 720);
        Scene encryptFinalScene = new Scene(encryptFinalXML.load(), 1280, 720);
        Scene decryptFinalScene = new Scene(decryptFinalXML.load(), 1280, 720);
        Scene ThankYouScene = new Scene(ThankYouXML.load(), 1280, 720);

        window.setTitle("Encryption");
        window.setScene(primaryscene);

        primaryscene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                window.setScene(EncryptDecryptScene);
            }
        });

        // From Encrypt of Decrypt Pages
        Button encryptButton = (Button) EncryptDecryptScene.lookup("#encryptFirstButton");
        encryptButton.setOnAction(actionEvent -> {
            window.setScene(encryptFirstScene);
        });

        Button decryptButton = (Button) EncryptDecryptScene.lookup("#decryptFirstButton");
        decryptButton.setOnAction(actionEvent -> {
            window.setScene(decryptFirstScene);
        });

        // --------------------------------------------------------------------------------

        // From the encryption page where you decide if you want to use unique number or not
        Button encryptBackButton = (Button) encryptFirstScene.lookup("#backFromContinueEncrypt");
        encryptBackButton.setOnAction(actionEvent -> {
            window.setScene(EncryptDecryptScene);
        });

        Button encryptValueButton = (Button) encryptFirstScene.lookup("#encryptContinue");
        TextField encryptUniqueTextField = (TextField) encryptFirstScene.lookup("#uniqueNumEnc");
        encryptValueButton.setOnAction(actionEvent -> {
            String randNum = encryptUniqueTextField.getText();
            if (randNum.equals("")){
                encDec = new EncryptionDecryption();
            }
            else {
                int num;
                try {
                    num = Integer.parseInt(randNum);
                }
                catch (NumberFormatException e) {
                    encryptUniqueTextField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: red;");
                    encryptUniqueTextField.clear();
                    encryptUniqueTextField.setPromptText("Sorry, Not an integer");
                    return;
                }
                encryptUniqueTextField.setStyle("-fx-border-color: -fx-box-border; -fx-prompt-text-fill: black;");
                encryptUniqueTextField.setPromptText("Leave Empty if you don't want to");
                encDec = new EncryptionDecryption(num);
            }
            encryptUniqueTextField.clear();
            window.setScene(encryptValueScene);
        });


        // From the decryption page where you decide if you used unique number or not
        Button decryptBackButton = (Button) decryptFirstScene.lookup("#backFromContinueDecrypt");
        decryptBackButton.setOnAction(actionEvent -> {
            window.setScene(EncryptDecryptScene);
        });

        Button decryptValueButton = (Button) decryptFirstScene.lookup("#decryptContinue");
        TextField decryptUniqueTextField = (TextField) decryptFirstScene.lookup("#uniqueNumDec");
        decryptValueButton.setOnAction(actionEvent -> {
            String randNum = decryptUniqueTextField.getText();
            if (randNum.equals("")){
                encDec = new EncryptionDecryption();
            }
            else {
                int num;
                try {
                    num = Integer.parseInt(randNum);
                }
                catch (NumberFormatException e) {
                    decryptUniqueTextField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: red;");
                    decryptUniqueTextField.clear();
                    decryptUniqueTextField.setPromptText("Sorry, Not an integer");
                    return;
                }
                decryptUniqueTextField.setStyle("-fx-border-color: -fx-box-border; -fx-prompt-text-fill: black;");
                decryptUniqueTextField.setPromptText("Leave Empty if you did not");
                encDec = new EncryptionDecryption(num);
            }
            decryptUniqueTextField.clear();
            window.setScene(decryptValueScene);
        });

        // ---------------------------------------------------------------------------------
        Label encryptTextValue = (Label) encryptFinalScene.lookup("#enterEncryptValue");
        Label decryptTextValue = (Label) decryptFinalScene.lookup("#enterDecryptValue");

        // From the page where you put in values for encryption
        Button encryptFinalBackButton = (Button) encryptValueScene.lookup("#backFromEncrypt");
        encryptFinalBackButton.setOnAction(actionEvent -> {
            window.setScene(decryptFirstScene);
        });

        Button encryptFinalButton = (Button) encryptValueScene.lookup("#encryptFinalButton");
        TextField encryptValueTextField = (TextField) encryptValueScene.lookup("#valueForEncrypt");
        encryptFinalButton.setOnAction(actionEvent -> {
            String valueToEncrypt = encryptValueTextField.getText();
            enc = encDec.getEncrypt(valueToEncrypt);
            encryptTextValue.setText(enc);
            encryptValueTextField.clear();
            window.setScene(encryptFinalScene);
        });


        // From the page where you put in values for decryption
        Button deryptFinalBackButton = (Button) decryptValueScene.lookup("#backFromDecrypt");
        deryptFinalBackButton.setOnAction(actionEvent -> {
            window.setScene(decryptFirstScene);
        });

        Button decryptFinalButton = (Button) decryptValueScene.lookup("#decryptFinalButton");
        TextField decryptValueTextField = (TextField) decryptValueScene.lookup("#valueForDecrypt");
        decryptFinalButton.setOnAction(actionEvent -> {
            String valueToDecrypt = decryptValueTextField.getText();
            if (valueToDecrypt.matches("\\d+")){
                decryptValueTextField.setStyle("-fx-border-color: -fx-box-border; -fx-prompt-text-fill: black;");
                dec = encDec.getDecrypt(valueToDecrypt);
                decryptTextValue.setText(dec);
                decryptValueTextField.clear();
                decryptValueTextField.setPromptText("Enter the whole number");
                window.setScene(decryptFinalScene);
            }
            else {
                decryptValueTextField.setStyle("-fx-border-color: red; -fx-prompt-text-fill: red;");
                decryptValueTextField.clear();
                decryptValueTextField.setPromptText("Invalid Input");
            }
        });

        // ---------------------------------------------------------------------------------

        Button encryptCopyButton = (Button) encryptFinalScene.lookup("#EncryptCopyToClipboard");
        encryptCopyButton.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(enc);
            clipboard.setContent(content);
        });
        Button encryptRetryYesButton = (Button) encryptFinalScene.lookup("#EncryptRetryYes");
        encryptRetryYesButton.setOnAction(actionEvent -> {
            window.setScene(EncryptDecryptScene);
        });
        Button encryptRetryNoButton = (Button) encryptFinalScene.lookup("#EncryptRetryNo");
        encryptRetryNoButton.setOnAction(actionEvent -> {
            window.setScene(ThankYouScene);
        });


        Button decryptCopyButton = (Button) decryptFinalScene.lookup("#DecryptCopyToClipboard");
        decryptCopyButton.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(dec);
            clipboard.setContent(content);
        });
        Button decryptRetryYesButton = (Button) decryptFinalScene.lookup("#DecryptRetryYes");
        decryptRetryYesButton.setOnAction(actionEvent -> {
            window.setScene(EncryptDecryptScene);
        });
        Button decryptRetryNoButton = (Button) decryptFinalScene.lookup("#DecryptRetryNo");
        decryptRetryNoButton.setOnAction(actionEvent -> {
            window.setScene(ThankYouScene);
        });

        // ---------------------------------------------------------------------------------

        Button closeThanksButton = (Button) ThankYouScene.lookup("#closeThanks");
        closeThanksButton.setOnAction(actionEvent -> {
            window.close();
        });

        window.show();
    }
}