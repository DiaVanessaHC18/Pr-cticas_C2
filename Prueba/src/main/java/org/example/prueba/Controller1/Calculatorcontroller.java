package org.example.prueba.Controller1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;



public class Calculatorcontroller implements Initializable {

    @FXML
    private TextField txtResultado;

    @FXML
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Button btnSuma, btnResta, btnMultiplicacion, btnDivision, btnIgual;

    private double numero1, numero2;
    private char operacion;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa los botones
        btn0.setOnAction(event -> {
            txtResultado.appendText("0");
        });
        btn1.setOnAction(event -> {
            txtResultado.appendText("1");
        });
        btn2.setOnAction(event -> {
            txtResultado.appendText("2");
        });
        btn3.setOnAction(event -> {
            txtResultado.appendText("3");
        });
        btn4.setOnAction(event -> {
            txtResultado.appendText("4");
        });
        btn5.setOnAction(event -> {
            txtResultado.appendText("5");
        });
        btn6.setOnAction(event -> {
            txtResultado.appendText("6");
        });
        btn7.setOnAction(event -> {
            txtResultado.appendText("7");
        });
        btn8.setOnAction(event -> {
            txtResultado.appendText("8");
        });
        btn9.setOnAction(event -> {
            txtResultado.appendText("9");
        });

        btnIgual.setOnAction(event -> {
            numero2 = Double.parseDouble(txtResultado.getText());
            switch (operacion) {
                case '+':
                    txtResultado.setText(String.valueOf(numero1 + numero2));
                    break;
                case '-':
                    txtResultado.setText(String.valueOf(numero1 - numero2));
                    break;
                case '*':
                    txtResultado.setText(String.valueOf(numero1 * numero2));
                    break;
                case '/':
                    txtResultado.setText(String.valueOf(numero1 / numero2));
                    break;
            }
        });
    }

}