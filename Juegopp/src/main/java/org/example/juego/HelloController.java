package org.example.juego;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

import org.example.juego.modelo.modeloJuego;
import org.example.juego.service.Interface_juego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloController {
    @Autowired
    public Interface_juego interfaceImJ;

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @FXML
    private Button btniniciar, btnanular;

    @FXML
    private Label nombreturno, punt_jugador1, punt_jugador2;

    @FXML
    private TextField nombrejugador1, nombrejugador2;

    @FXML
    private TableView<modeloJuego> tabla_puntajes;

    @FXML
    private TableColumn<modeloJuego, String> nom_partido, nom_jugador1, nom_jugador2, nom_ganador, puntuacion, cestado;

    private String jugadorActual;
    private boolean juegoActivo;
    private int contadorTurnos;
    private List<modeloJuego> resultadosPartidas = new ArrayList<>(); // Almacena todas las partidas
    private ObservableList<modeloJuego> ob_modeloJ = FXCollections.observableArrayList(); // Para la tabla


    private List<Button> botones;
    private int puntajeJugador1, puntajeJugador2;
    private int indexEdit = -1;

    @FXML
    public void initialize() {
        botones = new ArrayList<>();
        botones.add(btn1);
        botones.add(btn2);
        botones.add(btn3);
        botones.add(btn4);
        botones.add(btn5);
        botones.add(btn6);
        botones.add(btn7);
        botones.add(btn8);
        botones.add(btn9);

        desactivarBotones();

        for (Button btn : botones) {
            btn.setOnAction(e -> marcarCasilla(btn));
        }

    }

    @FXML
    private void iniciarJuego() {
        if (!nombrejugador1.getText().isEmpty() && !nombrejugador2.getText().isEmpty()) {
            jugadorActual = "X";
            nombreturno.setText(nombrejugador1.getText());
            juegoActivo = true;
            contadorTurnos = 0;
            activarBotones();
            btniniciar.setDisable(true);
            btnanular.setDisable(false);

        }
    }

    @FXML
    private void anularJuego() {
        desactivarBotones();
        limpiarBotones();
        juegoActivo = false;
        nombreturno.setText("");
        guardarResultado("Anulada");
        btniniciar.setDisable(false);
        btnanular.setDisable(true);
    }

    @FXML
    private void marcarCasilla(Button boton) {
        if (juegoActivo && boton.getText().isEmpty()) {
            boton.setText(jugadorActual);
            boton.setDisable(true);
            contadorTurnos++;
            if (verificarGanador()) {
                if (jugadorActual.equals("X")) {
                    puntajeJugador1++;
                    punt_jugador1.setText(String.valueOf(puntajeJugador1));
                    nombreturno.setText(nombrejugador1.getText() + "  ¡Ganaste!");

                } else {
                    puntajeJugador2++;
                    punt_jugador2.setText(String.valueOf(puntajeJugador2));
                    nombreturno.setText(nombrejugador2.getText() + "  ¡Ganaste!");
                }
                guardarResultado("Terminada");
                desactivarBotones();

                juegoActivo = false;
            } else if (contadorTurnos == 9) {
                nombreturno.setText("Empate");
                guardarResultado("Empate");
                juegoActivo = false;
            } else {
                cambiarTurno();
            }
        }
    }

    private void cambiarTurno() {
        if (jugadorActual.equals("X")) {
            jugadorActual = "O";
            nombreturno.setText(nombrejugador2.getText());
        } else {
            jugadorActual = "X";
            nombreturno.setText(nombrejugador1.getText());
        }
    }

    private boolean verificarGanador() {
        // Combinaciones ganadoras en una tabla de 3x3
        return (compararCasillas(btn1, btn2, btn3) || compararCasillas(btn4, btn5, btn6) || compararCasillas(btn7, btn8, btn9) || // Filas
                compararCasillas(btn1, btn4, btn7) || compararCasillas(btn2, btn5, btn8) || compararCasillas(btn3, btn6, btn9) || // Columnas
                compararCasillas(btn1, btn5, btn9) || compararCasillas(btn3, btn5, btn7)); // Diagonales
    }

    private boolean compararCasillas(Button b1, Button b2, Button b3) {
        return (!b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText()));
    }

    private void desactivarBotones() {
        for (Button btn : botones) {
            btn.setDisable(true);
        }
    }

    private void activarBotones() {
        for (Button btn : botones) {
            btn.setDisable(false);
            btn.setText("");
        }
    }

    private void limpiarBotones() {
        for (Button btn : botones) {
            btn.setText("");
        }
    }


    private void guardarResultado(String estadoPartida) {
        String ganador;
        int puntuacionJugador1 = 0;
        int puntuacionJugador2 = 0;

        if (estadoPartida.equals("Empate")) {
            ganador = "Empate";
        } else {
            ganador = jugadorActual.equals("X") ? nombrejugador1.getText() : nombrejugador2.getText();
            if (ganador.equals(nombrejugador1.getText())) {
                puntuacionJugador1 = 1;
            } else {
                puntuacionJugador2 = 1;
            }
        }

        // Crear el objeto modeloJuego con los datos de la partida
        modeloJuego resultado = new modeloJuego(
                "Partida " + (resultadosPartidas.size() + 1),
                nombrejugador1.getText(),
                nombrejugador2.getText(),
                ganador,
                puntuacionJugador1 + " - " + puntuacionJugador2,
                estadoPartida
        );

        // Guardar en la lista de resultados
        resultadosPartidas.add(resultado);

        // Actualizar la tabla con los nuevos datos
        ob_modeloJ.add(resultado);
        tabla_puntajes.setItems(ob_modeloJ);
    }


    public void crear_actuList() {
        List<modeloJuego> Soly = interfaceImJ.obtenerResultados();
        for (modeloJuego to : Soly) {
            System.out.println(to.toString());

            tabla_puntajes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            nom_jugador1.setCellValueFactory(new PropertyValueFactory<modeloJuego, String>("nombrePartida"));


        }
    }



    private void eliminarReg(modeloJuego bo) {
        System.out.println("Deleting: " + bo.getNombreJugador2());
        interfaceImJ.eliminarResultados(Integer.parseInt(String.valueOf(bo)));
        guardarResultado("Cancelado");
    }


    public void  listaOper(){
        List<modeloJuego> lista=interfaceImJ.obtenerResultados();
        for (modeloJuego to:lista) {
            System.out.println(to.toString());
        }
        tabla_puntajes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
// Vincular columnas con propiedades de CalcTO
        nom_jugador1.setCellValueFactory(new PropertyValueFactory<modeloJuego, String>("nombreJugador1"));
        nom_jugador1.setCellFactory(TextFieldTableCell.<modeloJuego>forTableColumn());
        nom_jugador2.setCellValueFactory(new PropertyValueFactory<modeloJuego, String>("nombreJugador2"));
        nom_jugador2.setCellFactory(TextFieldTableCell.<modeloJuego>forTableColumn());
        nom_partido.setCellValueFactory(new PropertyValueFactory<modeloJuego, String>("nombrePartida"));
        nom_partido.setCellFactory(TextFieldTableCell.<modeloJuego>forTableColumn());
        puntuacion.setCellValueFactory(new PropertyValueFactory<modeloJuego, String>("puntuacion"));
        puntuacion.setCellFactory(TextFieldTableCell.<modeloJuego>forTableColumn());
        cestado.setCellValueFactory(new PropertyValueFactory<modeloJuego, String>("estado"));
        cestado.setCellFactory(TextFieldTableCell.<modeloJuego>forTableColumn());

//
        tabla_puntajes.setItems(ob_modeloJ);}
}







