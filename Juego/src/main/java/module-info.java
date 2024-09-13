module org.example.juego {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires spring.beans;
    requires spring.context;

    opens org.example.juego to javafx.fxml;
    exports org.example.juego;
}