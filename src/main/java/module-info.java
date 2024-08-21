module it.unibz.pp2024.LudoKing {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires static lombok;
    requires java.desktop;

    opens it.unibz.pp2024.LudoKing to javafx.fxml;
    exports it.unibz.pp2024.LudoKing;
}
