module com.example.firstfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.firstfx to javafx.fxml;
    exports com.example.firstfx;
}