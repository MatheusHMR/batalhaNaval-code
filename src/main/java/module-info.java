module trabalhopoov {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens trabalhopoov to javafx.fxml;
    opens trabalhopoov.controllers to javafx.fxml, javafx.graphics;
    opens trabalhopoov.model to javafx.base, javafx.fxml, javafx.graphics;
    exports trabalhopoov;
}
