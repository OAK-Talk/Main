package one;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProfileController {

	@FXML
	private ImageView myProfile; // 내 프사

	@FXML
	private TextField myMessage; // 상태 메세지

	@FXML
	private TextField myName; // 내 이름

	Singleton sgt;

	public ProfileController() {
		sgt = Singleton.getInstance();
	}

	@FXML
	private void initialize() { // 화면 전환 되면 처음으로 실행되는 것들
		String name = ""; // db에서 받아와야함
		String message = ""; // db에서 받아와야함
		String imgLink = ""; // db에서 받아와야함
		myName.setText(name); // 이름 가져오기
		myMessage.setText(message); // 상메 가져오기
	}

	@FXML
	void cl_back(MouseEvent event) throws IOException { // 뒤로가기
		sgt.nextScene2(event, "FriendsList.fxml");
	}

	@FXML
	void cl_save(MouseEvent event) throws IOException { // 저장 클릭 -> db에 다시 저장해줘야 한다.
		String newName = myName.getText();
		String newMyMessage = myMessage.getText();
		System.out.println(newName + "이름 " + newMyMessage);
		sgt.nextScene2(event, "FriendsList.fxml");
	}

	@FXML
	void editProfile(MouseEvent event) { // 프사 변경
		// 사진 선택 창
		FileChooser fc = new FileChooser();
		fc.setTitle("이미지 선택");
		fc.setInitialDirectory(new File("C:/")); // default 디렉토리 설정
		// 선택한 파일 정보 추출
		// 확장자 제한
		ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
		// fc.getExtensionFilters().add(imgType);
		fc.getExtensionFilters().addAll(imgType);
		File selectedFile = fc.showOpenDialog(null);
		FileInputStream fis;

		try {
			fis = new FileInputStream(selectedFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			Image img = new Image(bis);
			//bis가 링크 같음
			myProfile.setImage(img);
			//이 부분에서 db에 이미지 보내줘야 함 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
