module com.example.userinterfaceapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.userinterfaceapp to javafx.fxml;
    exports com.example.userinterfaceapp;
}