module it.unibz.wie2024.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens it.unibz.wie2024.demo1 to javafx.fxml;
    exports it.unibz.wie2024.demo1;
}