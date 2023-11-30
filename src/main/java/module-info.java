module com.example.pb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pb to javafx.fxml;
    exports com.example.pb;
}