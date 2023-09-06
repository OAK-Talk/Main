package one;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Singleton {

	private static Singleton instance;

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	public void nextScene(ActionEvent event, String name) throws IOException { // 액션 이벤트 시에 화면 넘기기
		Parent root = FXMLLoader.load(getClass().getResource(name));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void nextScene2(MouseEvent event, String name) throws IOException { // 마우스 이벤트(사진 클릭) 시에 화면 넘기기
		Parent root = FXMLLoader.load(getClass().getResource(name));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
