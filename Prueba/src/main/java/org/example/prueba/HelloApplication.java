package org.example.prueba;
import com.sun.javafx.menu.MenuItemBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

public class HelloApplication extends Application {
    private TextField textField;

    @Override
    public void start(Stage fresh) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        textField = new TextField();
        textField.setPrefWidth(200);
        gridPane.add(textField, 0, 0, 4, 1);

        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button buttonDiv = new Button("/");

        gridPane.add(button7, 0, 1);
        gridPane.add(button8, 1, 1);
        gridPane.add(button9, 2, 1);
        gridPane.add(buttonDiv, 3, 1);

        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button buttonMult = new Button("*");

        gridPane.add(button4, 0, 2);
        gridPane.add(button5, 1, 2);
        gridPane.add(button6, 2, 2);
        gridPane.add(buttonMult, 3, 2);

        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button buttonRest = new Button("-");

        gridPane.add(button1, 0, 3);
        gridPane.add(button2, 1, 3);
        gridPane.add(button3, 2, 3);
        gridPane.add(buttonRest, 3, 3);

        Button button0 = new Button("0");
        Button buttonPunto = new Button(".");
        Button buttonSum = new Button("+");
        Button buttonIgual = new Button("=");

        gridPane.add(button0, 0, 4);
        gridPane.add(buttonPunto, 1, 4);
        gridPane.add(buttonSum, 2, 4);
        gridPane.add(buttonIgual, 3, 4);

        Scene scene = new Scene(gridPane, 250, 250);
        fresh.setScene(scene);
        fresh.show();
    }


    private double evaluarExpresion(String expresion) throws ScriptException {
        // Implementar la lógica para evaluar la expresión
        // Por ejemplo, utilizando la clase ScriptEngineManager
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        return ((Number) engine.eval(expresion)).doubleValue();
    }
    public static void main(String[] args) {
        launch();
    }
}