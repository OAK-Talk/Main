package one;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
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
	private TextField chatField;
	@FXML
	private ImageView imgView1, imgView2, imgView3, imgView4;
	int cnt=0;

	@FXML
	private void cl_back(MouseEvent event) {
		// 이벤트 처리 로직을 여기에 추가합니다.
	}

	@FXML
	private void cl_outroom(MouseEvent event) {
		// 이벤트 처리 로직을 여기에 추가합니다.
	}

	@FXML
	private void cl_chooseImage(MouseEvent event) {
		fileChoose();
	}

	@FXML
	private void handleKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			sendMessage();
		}
	}

	@FXML
	private void cl_send(MouseEvent event) {
		sendMessage();
	}

	public void initialize() {
		// TextField에서 Enter 키 눌렀을 때 이벤트 처리
		chatField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				sendMessage();
			}
		});
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
		// fc.getExtensionFilters().add(imgType);
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
