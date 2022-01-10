module ru.twistru.imageconstruct {
    requires javafx.swing;
    requires javafx.fxml;
    requires javafx.controls;
    requires kotlin.stdlib;
    requires opencv;

    opens ru.twistru.imageconstruct to javafx.fxml;
    exports ru.twistru.imageconstruct;
}