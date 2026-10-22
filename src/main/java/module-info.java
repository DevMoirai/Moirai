module com.moirai.moirai {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.moirai.moirai to javafx.fxml;
    exports com.moirai.moirai;
}