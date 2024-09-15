package pe.edu.upeu.Tres_raya.TERcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;
import pe.edu.upeu.Tres_raya.modelo.TERmodelo;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class juego_Controller {
    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Button btniniciar, btnanular;
    @FXML
    private Label nom_turno, punt_jugador1, punt_jugador2;
    @FXML
    private TextField nombrejugador1, nombrejugador2;
    @FXML
    private TableView<TERmodelo> tabla_puntajes;
    @FXML
    private TableColumn<TERmodelo, String> nom_partido, nom_jugador1, nom_jugador2, nom_ganador, puntuacion,control_estado;
    private String jugadorActual;
    private boolean juegoActivo;
    private int contadorTurnos;
    private List<TERmodelo> resultadosPartidas = new ArrayList<>();
    private ObservableList<TERmodelo> ob_modeloJ = FXCollections.observableArrayList();
    private List<Button> tablero;
    private int puntuacionJugador1 = 0;
    private int puntuacionJugador2 = 0;
    private int contadorPartidas = 1;
    private int indexEdit = -1;

    @FXML
    public void initialize() {
        tablero = new ArrayList<>();
        tablero.add(btn1);
        tablero.add(btn2);
        tablero.add(btn3);
        tablero.add(btn4);
        tablero.add(btn5);
        tablero.add(btn6);
        tablero.add(btn7);
        tablero.add(btn8);
        tablero.add(btn9);

        desactivar_Boton();
        funcion_tablaPunt();
    }

    private boolean metodo1_error() {
        if (nombrejugador1.getText().trim().isEmpty() || nombrejugador2.getText().trim().isEmpty()) {
            mostrar_error("¡¡Error!!", "Por favor, ingresa los nombres de ambos jugadores.");
            return false;
        }
        return true;
    }

    private void mostrar_error(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void iniciar_Juego() {
        if (metodo1_error()) {
            jugadorActual = "X";
            nom_turno.setText(nombrejugador1.getText());
            juegoActivo = true;
            contadorTurnos = 0;
            activar_Boton();
            borrar_datosTablero();

            puntuacionJugador1 = 0;
            puntuacionJugador2 = 0;
            punt_jugador1.setText(String.valueOf(puntuacionJugador1));
            punt_jugador2.setText(String.valueOf(puntuacionJugador2));

            tabla_puntajes.setDisable(false);

            btniniciar.setDisable(true);
            btnanular.setDisable(false);
            indexEdit = resultadosPartidas.size();
            mostrar_jugando();
        }
    }

    @FXML
    private void anular_Juego() {
        desactivar_Boton();
        juegoActivo = false;
        nom_turno.setText("");

        if (indexEdit >= 0) {
            TERmodelo partidaAnulada = resultadosPartidas.get(indexEdit);
            partidaAnulada.setEstado("Anulado");
            partidaAnulada.setGanador("");
            partidaAnulada.setPuntuacion(0);
            ob_modeloJ.set(indexEdit, partidaAnulada);
        } else {
            TERmodelo nuevaPartidaAnulada = new TERmodelo(
                    "Partida " + contadorPartidas,
                    nombrejugador1.getText(),
                    nombrejugador2.getText(),
                    "",
                    0,
                    "Anulado"
            );
            resultadosPartidas.add(nuevaPartidaAnulada);
            ob_modeloJ.add(nuevaPartidaAnulada);
        }

        tabla_puntajes.setItems(ob_modeloJ);

        btniniciar.setDisable(false);
        btnanular.setDisable(true);
        nombrejugador1.clear();
        nombrejugador2.clear();
        contadorPartidas++;
    }

    @FXML
    private void marcarCasilla(ActionEvent soly ) {
        Button saber_boton= (Button) soly.getSource();
        if (juegoActivo && saber_boton.getText().isEmpty()) {
            saber_boton.setText(jugadorActual);
            saber_boton.setDisable(true);
            contadorTurnos++;
            if (combinacion_ganadora()) {
                SumarPuntajes0();
                guardarEnTabla("Terminado");
                finJuego_nuevoJuego();
            } else if (contadorTurnos == 9) {
                guardarEnTabla("Empate");
                finJuego_nuevoJuego();
            } else {
                alternar_Turno();
            }
        }
    }

    private void alternar_Turno() {
        if (jugadorActual.equals("X")) {
            jugadorActual = "O";
            nom_turno.setText(nombrejugador2.getText() + " ( O )");
        } else {
            jugadorActual = "X";
            nom_turno.setText(nombrejugador1.getText() + " ( X )");
        }
    }

    private boolean combinacion_ganadora() {
        return (verificar_ganar(btn1, btn2, btn3) ||
                verificar_ganar(btn4, btn5, btn6) ||
                verificar_ganar(btn7, btn8, btn9) ||
                verificar_ganar(btn1, btn4, btn7) ||
                verificar_ganar(btn2, btn5, btn8) ||
                verificar_ganar(btn3, btn6, btn9) ||
                verificar_ganar(btn1, btn5, btn9) ||
                verificar_ganar(btn3, btn5, btn7));
    }

    private boolean verificar_ganar(Button botonFila1, Button botonFila2, Button botonFila3) {
        return (!botonFila1.getText().isEmpty() && botonFila1.getText().equals(botonFila2.getText()) && botonFila1.getText().equals(botonFila3.getText()));
    }

    private void desactivar_Boton() {
        for (Button btn : tablero) {
            btn.setDisable(true);
        }
    }
    private void activar_Boton() {
        for (Button btn : tablero) {
            btn.setDisable(false);
            btn.setText("");
        }
    }
    private void borrar_datosTablero() {
        for (Button btn : tablero) {
            btn.setText("");
        }
    }

    @FXML
    private void borrar_historial() {
        ob_modeloJ.clear();
        resultadosPartidas.clear();
        tabla_puntajes.setItems(ob_modeloJ);

        contadorPartidas = 1;
        nombrejugador1.clear();
        nombrejugador2.clear();
        nom_turno.setText("Ingresen sus nombres");

        desactivar_Boton();
        btniniciar.setDisable(true);
        btnanular.setDisable(true);
    }

    @FXML
    public void guardar_historial() {
        File guardar_historial = new File("E:\\Historial_partida_3_en_raya.txt");
        try {
            ObservableList<TERmodelo> datosGuardar = tabla_puntajes.getItems();
            FileWriter escribirDoc = new FileWriter(guardar_historial);
            escribirDoc.write("--------------------*********  HISTORIAL DE PARTIDAS  ********---------------------"+"\n");
            escribirDoc.write(" NOMBRE PARTIDO"+" | "+"JUGADOR 1"+" | "+"JUGADOR 2"+" | "+"NOMBRE JUGADOR"+" | "+"PUNTUACIÓN"+" | "+"ESTADO"+"\n");
            escribirDoc.write("\n");

            for (TERmodelo dd:datosGuardar){
                escribirDoc.write(" "+dd.getNombrePartida()+"  | ");
                escribirDoc.write(" "+dd.getNombreJugador1()+" | ");
                escribirDoc.write(" "+dd.getNombreJugador2()+" | ");
                escribirDoc.write(" "+dd.getGanador()+" | ");
                escribirDoc.write(" "+dd.getPuntuacion()+" | ");
                escribirDoc.write(" "+dd.getEstado()+"\n");
            }
            escribirDoc.close();
            System.out.println("Historial guardado en la carpeta E: Historial_partida_3_en_raya.txt ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
    }

    private void guardarEnTabla(String estadoPartida) {
        int f_verpuntuacionT;
        if (estadoPartida.equals("Empate")) {
            f_verpuntuacionT = 0;
        } else if (estadoPartida.equals("Anulado")) {
            f_verpuntuacionT = 0;
        } else {
            f_verpuntuacionT = 1;
        }

        String nombrePartida = (" Partida " +contadorPartidas );
        TERmodelo datos_fin = new TERmodelo(nombrePartida,nombrejugador1.getText(), nombrejugador2.getText(), estadoPartida.equals("Empate") ? "Empate" : (jugadorActual.equals("X") ? nombrejugador1.getText() : nombrejugador2.getText()), f_verpuntuacionT, estadoPartida);
        if (indexEdit >= 0) {
            resultadosPartidas.set(indexEdit, datos_fin);
            ob_modeloJ.set(indexEdit, datos_fin);
        } else {
            resultadosPartidas.add(datos_fin);
            ob_modeloJ.add(datos_fin);
        }
        contadorPartidas++;
        tabla_puntajes.setItems(ob_modeloJ);
        desactivar_Boton();
        btniniciar.setDisable(true);
        btnanular.setDisable(true);
        nombrejugador1.clear();
        nombrejugador2.clear();
    }

    private void SumarPuntajes0() {
        if (jugadorActual.equals("X")) {
            puntuacionJugador1++;
            punt_jugador1.setText(String.valueOf(puntuacionJugador1));
        } else {
            puntuacionJugador2++;
            punt_jugador2.setText(String.valueOf(puntuacionJugador2));
        }
    }

    @FXML
    private void finJuego_nuevoJuego() {
        desactivar_Boton();
        borrar_datosTablero();
        juegoActivo = false;

        nombrejugador1.clear();
        nombrejugador2.clear();
        nom_turno.setText("Ingresen sus nombres ");

        btniniciar.setDisable(true);
        btnanular.setDisable(true);

        nombrejugador1.textProperty().addListener((observable, oldValue, newValue) -> rellenar_nom_jugadores());
        nombrejugador2.textProperty().addListener((observable, oldValue, newValue) -> rellenar_nom_jugadores());
    }

    private void rellenar_nom_jugadores() {
        if (!nombrejugador1.getText().isEmpty() && !nombrejugador2.getText().isEmpty()) {
            btniniciar.setDisable(false);
            btnanular.setDisable(false);
        } else {
            btniniciar.setDisable(true);
            btnanular.setDisable(true);
        }
    }

    private void mostrar_jugando() {
        TERmodelo datos_jugando = new TERmodelo("Partida " + contadorPartidas, nombrejugador1.getText(), nombrejugador2.getText(), "Jugando..", 0, "Jugando...");
        resultadosPartidas.add(datos_jugando);
        ob_modeloJ.add(datos_jugando);
        tabla_puntajes.setItems(ob_modeloJ);
    }

    private void funcion_tablaPunt() {
        nom_partido.setCellValueFactory(new PropertyValueFactory<>("nombrePartida"));
        nom_jugador1.setCellValueFactory(new PropertyValueFactory<>("nombreJugador1"));
        nom_jugador2.setCellValueFactory(new PropertyValueFactory<>("nombreJugador2"));
        nom_ganador.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        puntuacion.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
        control_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
}
