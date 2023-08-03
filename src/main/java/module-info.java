module elementarium {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens elementarium to javafx.fxml;
    exports elementarium;
    exports controller;
    exports play;
    opens play to javafx.fxml;
    opens controller to javafx.fxml;
}