package pe.edu.upeu.Tres_raya;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TresRayaApplication extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXtresRaya.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 900, 450);
		stage.setTitle("Juego3_en_raya.com.pe");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
	}


