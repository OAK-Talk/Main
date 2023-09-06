package one;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class LoginController {

	@FXML
	private TextField newId; // 새로운 아이디

	@FXML
	private TextField newNickName; // 새로운 닉네임

	@FXML
	private PasswordField newPw;// 새로운 비밀번호

	@FXML
	private PasswordField PW_Check; // 비밀번호 확인

	@FXML
	private ImageView notInput;

	private Singleton sgt; // 싱글톤

	private Boolean chId; // 아이디 체크

	Image image;

	public LoginController() {
		sgt = Singleton.getInstance();
		chId = false;
	}

	@FXML
	void cl_IDCheck(MouseEvent event) { // 중복된 아이디 처리
		String id = newId.getText(); // 입력한 아이디 가져옴

		Boolean exist = true;
		if (exist) { // db에서 있으면 true 없으면 false로 가져옴
			image = new Image(getClass().getResource("/image/중복된 아이디.png").toExternalForm());
			notInput.setImage(image);
			failCreating(notInput);
		} else {
			newId.setEditable(false); // 사용 가능하면 수정 불가
			chId = true; // 아이디 중복 없음
		}
	}

	@FXML
	void cl_BackToLogin(MouseEvent event) throws IOException {
		sgt.nextScene2(event, "safdsfds"); // 뒤로가기 누를 시 다시 로그인 페이지로
		chId = false;
	}

	@FXML
	void cl_Submit(MouseEvent event) throws IOException {
		String pw = newPw.getText();
		String pw2 = PW_Check.getText();
		if (chId == true && (newPw.equals(PW_Check))) { // 아이디 중복 없고 pw, pw2가 일치할때
			chId = false; 
			sgt.nextScene2(event, "FriendsList.fxml");
		} else if (pw.equals(pw2) == false) { // 비밀번호 서로 다름
			image = new Image(getClass().getResource("/image/비밀번호 확인.png").toExternalForm());
			notInput.setImage(image);
			failCreating(notInput);
		} else {
			image = new Image(getClass().getResource("/image/회원가입 불가.png").toExternalForm());
			notInput.setImage(image);
			failCreating(notInput);
			// 회원가입이 불가능 합니다.
		}
	}

	void failCreating(ImageView iv) { //나타났다 사라지는 UI
		iv.setVisible(true);
		FadeTransition fade = new FadeTransition(Duration.seconds(2), iv);
		fade.setFromValue(1.0); // 시작 투명도
		fade.setToValue(0.0); // 목표 투명도
		fade.setOnFinished(e -> iv.setVisible(false));
		fade.play();
	}

}
