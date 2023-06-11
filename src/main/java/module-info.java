module com.cuhacay.movidle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.cuhacay.movidle to javafx.fxml;
    exports com.cuhacay.movidle;
}