module trabalhopoov {
    requires javafx.controls;
    requires javafx.fxml;

    opens trabalhopoov to javafx.fxml;
    exports trabalhopoov;
}
