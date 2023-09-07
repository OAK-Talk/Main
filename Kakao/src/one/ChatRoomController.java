
package one;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ChatRoomController {

	@FXML
	private TextArea textArea;

	@FXML
	private TextField chatField; // 채팅 입력 공간

	@FXML
	private ImageView imgView1, imgView2, imgView3, imgView4; // 사진 보낼때 쓰는 이미지 뷰

	@FXML
	private ComboBox<String> combobox; // 이모티콘

	@FXML
	private Label notice; // 공지 라벨

	ObservableList<String> list = FXCollections.observableArrayList();
	int cnt = 0;
	Singleton sgt;

	public ChatRoomController() {
		sgt = Singleton.getInstance();
	}

	public void initialize() { // 씬전환 일어날 때 바로 실행되는 메소드
		combobox.getItems().addAll("항목 1", "항목 2", "항목 3"); // 이모티콘 추가할것
		combobox.setOnAction(this::comboChange);
		textArea.setEditable(false);
	}

	@FXML
	private void cl_back(MouseEvent event) throws IOException { // 채팅 목록으로 이동
		sgt.nextScene2(event, "ChatList.fxml");
	}

	@FXML
	private void cl_OutRoom(MouseEvent event) { // 채팅방 나가기
		// 이벤트 처리 로직을 여기에 추가합니다.
	}

	@FXML
	private void cl_chooseImage(MouseEvent event) { // 사진 전송
		fileChoose();
	}

	@FXML
	private void handleKeyPress(KeyEvent event) { // 엔터키 이벤트
		if (event.getCode() == KeyCode.ENTER) {
			sendMessage();
		}
	}

	@FXML
	private void cl_send(MouseEvent event) { // 보내기 클릭
		sendMessage();
	}

	@FXML
	private void comboChange(ActionEvent event) { // 이모티콘 관련
		chatField.appendText(combobox.getValue());
	}

	// 엔터 키를 눌렀을 때 호출되는 메서드
	private void sendMessage() {
		String message = chatField.getText().trim(); // 입력된 텍스트 가져오기
		if (!message.isEmpty()) {
			// TextArea에 텍스트 추가
			textArea.appendText(message + "\n");

			// 입력 필드 초기화
			chatField.clear();
		}
	}

	public void fileChoose() {
		// 사진 선택 창
		FileChooser fc = new FileChooser();
		fc.setTitle("이미지 선택");
		fc.setInitialDirectory(new File("C:/")); // default 디렉토리 설정
		// 선택한 파일 정보 추출
		// 확장자 제한
		ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
		fc.getExtensionFilters().add(imgType);
		ExtensionFilter txtType = new ExtensionFilter("text file", "*.txt", "*.doc");
		fc.getExtensionFilters().addAll(imgType, txtType);

		File selectedFile = fc.showOpenDialog(null); // showOpenDialog는 창을 띄우는데 어느 위치에 띄울건지 인자를 받고
		// 그리고 선택한 파일의 경로값을 반환한다.
		System.out.println(selectedFile); // 선택한 경로가 출력된다.
		cnt++;
		// 파일을 InputStream으로 읽어옴
		try {
			// 파일 읽어오기

			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			// 이미지 생성하기
			Image img = new Image(bis);
			System.out.println(img);
			System.out.println(bis);
			System.out.println(fis);
			// 이미지 띄우기
			if (cnt == 1) {
				imgView1.setImage(img);
			} else if (cnt == 2) {
				imgView2.setImage(img);
			} else if (cnt == 3) {
				imgView3.setImage(img);
			} else if (cnt == 4) {
				imgView4.setImage(img);
			} else if (cnt >= 5) {
				imgView1.setImage(img);
				imgView2.setImage(null);
				imgView3.setImage(null);
				imgView4.setImage(null);
				cnt = 1;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}