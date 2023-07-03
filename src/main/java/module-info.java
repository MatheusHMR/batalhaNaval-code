module trabalhopoov {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens trabalhopoov to javafx.fxml;
    opens trabalhopoov.controllers.preparacao to javafx.fxml, javafx.graphics;
    exports trabalhopoov;
}
