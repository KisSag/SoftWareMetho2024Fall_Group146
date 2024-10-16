module com.project3.project3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.project3.project3 to javafx.fxml;
    exports com.project3.project3;
}