module com.example.encryption {
    requires javafx.controls;
    requires javafx.fxml;


    opens Encryption to javafx.fxml;
    exports Encryption;
}