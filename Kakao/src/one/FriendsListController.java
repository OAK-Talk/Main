package one;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FriendsListController {

	Singleton sgt;

	public FriendsListController() {
		sgt = Singleton.getInstance();
	}

	@FXML
	private ListView<String> friendsList; // 친구 목록

	ObservableList<String> listView = FXCollections.observableArrayList(); // 친구 목록 넣어줄 fx 전용 리스트

	@FXML
	private void initialize() { // 화면 전환 되면 처음c으로 실행되는 것들
		getListView(); // DB에서 데이터를 받아와 listView 변수에 추가해준다.
		friendsList.setItems(listView); // 친구 목록 생성
	}

	@FXML
	void cl_ChatList(MouseEvent event) throws IOException { // 채팅 목록 클릭
		sgt.nextScene2(event, "SignUp.fxml"); // 채팅 목록 fxml파일로 수정 필요
	}

	@FXML
	void cl_Profile(MouseEvent event) throws IOException { // 내 정보 클릭
		sgt.nextScene2(event, "SignUp.fxml"); // 내 프로필로 수정 필요
	}

	void getListView() { // DB에서 데이터를 받아와 listView 변수에 추가해준다.
		// DB에서 데이터 받아와서
		// listView.add(String 변수);로 추가해준다.
		for (int i = 0; i < 10; i++) { // 예시
			listView.add("" + i);
		}
	}
}