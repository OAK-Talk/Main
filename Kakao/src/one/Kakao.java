package one;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Kakao extends Application {

	Scene scene;

	// 프로그램 실행 시 처음으로 실행하는 메소드
	@Override
	public void start(Stage stage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("FriendsList.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	public Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Kakao.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load(); 
	}



	public static void main(String[] args) {
		launch(args);
	}

}