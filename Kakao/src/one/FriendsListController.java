package one;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FriendsListController {

	Singleton sgt;

	public FriendsListController() {
		sgt = Singleton.getInstance();
	}

	@FXML
	private ListView<String> friendsList; // 친구 목록

	@FXML
	private Pane addFriends; // 친구 추가 창

	@FXML
	private Pane friendsListPane; // 친구 목록 창

	@FXML
	private ImageView clickedChat; // 채팅하기 클릭

	@FXML
	private ImageView clickedProfileView; // 프로필 보기 클릭

	@FXML
	private ImageView clickedRemoveFr; // 친구 삭제 클릭

	@FXML
	private Pane viewProfile; // 프로필 훔쳐보는 pane

	@FXML
	private Label lookUserId; // 훔쳐보는 사용자 아이디

	@FXML
	private Label lookUserMessage; // 훔쳐보는 사용자 상메

	@FXML
	private Label lookUserName; // 훔쳐보는 사용자 이름

	@FXML
	private ImageView lookUserProfile; // 훔쳐보는 사용자 프사

	private int selected; // 선택한 버튼

	private String selectedFriends;

	ObservableList<String> listView = FXCollections.observableArrayList(); // 친구 목록 넣어줄 fx 전용 리스트

	@FXML
	private void initialize() { // 화면 전환 되면 처음c으로 실행되는 것들
		getListView(); // DB에서 데이터를 받아와 listView 변수에 추가해준다.
		friendsList.setItems(listView); // 친구 목록 생성
	}

	@FXML
	void cl_ChatList(MouseEvent event) throws IOException { // 채팅 목록 클릭
		sgt.nextScene2(event, "ChatList.fxml"); // 채팅 목록 fxml파일로 수정 필요
	}

	@FXML
	void cl_Profile(MouseEvent event) throws IOException { // 내 정보 클릭
		sgt.nextScene2(event, "EditInfo.fxml");
	}

	@FXML
	void cl_AddFriend(MouseEvent event) { // 친구 추가 누를 시
		addFriends.setVisible(true);
	}

	@FXML
	void cl_One(MouseEvent event) { // 프로필 보기 선택
		clickedProfileView.setVisible(true);
		clickedChat.setVisible(false);
		clickedRemoveFr.setVisible(false);
		selected = 1;
	}

	@FXML
	void cl_Two(MouseEvent event) { // 채팅하기 클릭
		clickedProfileView.setVisible(false);
		clickedChat.setVisible(true);
		clickedRemoveFr.setVisible(false);
		selected = 2;
	}

	@FXML
	void cl_Three(MouseEvent event) { // 삭제하기 클릭
		clickedProfileView.setVisible(false);
		clickedChat.setVisible(false);
		clickedRemoveFr.setVisible(true);
		selected = 3;
	}

	@FXML
	void cl_Want(MouseEvent event) throws IOException {
		System.out.println(selectedFriends);
		String[] name = selectedFriends.split("\\(");
		String userName = name[0]; // 선택한 유저의 이름
		String[] id = name[1].split("\\)");
		String userId = id[0]; // 선택한 유저의 아이디

		if (selected == 0) { // 아무것도 안눌렀을 때

		} else if (selected == 1) { // 프로필 보기
			System.out.println("사용자 이름 : " + userName + " 사용자 id : " + userId);
			viewProfile.setVisible(true);
			String viewName = "";
			String viewId = "";
			String viewMessage = "";
			String imageURL = "";
			Image image = new Image(imageURL);

			lookUserName.setText(viewName);
			lookUserId.setText(viewId);
			lookUserMessage.setText(viewMessage);
			lookUserProfile.setImage(image);
			
		} else if (selected == 2) { // 채팅하기
			System.out.println("사용자 이름 : " + userName + " 사용자 id : " + userId);
			sgt.nextScene2(event, "ChatRoom.fxml");
		} else if (selected == 3) { // 삭제하기
			System.out.println("사용자 이름 : " + userName + " 사용자 id : " + userId);
			// db에서 삭제 요함
		}
	}

	@FXML
	void cl_listView(MouseEvent event) {
		if (friendsList.getSelectionModel().getSelectedItem() != null) {
			selectedFriends = friendsList.getSelectionModel().getSelectedItem();
		}
	}

	void getListView() { // DB에서 데이터를 받아와 listView 변수에 추가해준다.
		// DB에서 데이터 받아와서
		// listView.add(String 변수);로 추가해준다.
		for (int i = 0; i < 10; i++) { // 예시
			String fr = i + "(" + i + ")" + "  :  " + i;
			listView.add(fr);
		}

		// ex) 한효민(hyomin99) : 인생 쓰다 ㅋㅋ -> 이런식으로 친구 창 뜨게 할거임
	}

	/* 친구추가 누를시 뜨는 UI */

	@FXML
	private Label otherId; // 상대 아이디

	@FXML
	private Label otherMessage; // 상대 상메

	@FXML
	private Label otherName; // 상대 이름

	@FXML
	private ImageView otherProfile; // 상대 프사

	@FXML
	private TextField searchId; // 검색할 아이디

	@FXML
	void cl_AddFriendsList(MouseEvent event) { // 친구 검색후 추가 누를때
		// db친구 목록에도 저장해줘야함
		listView.add(otherName.getText());
	}

	@FXML
	void cl_searchId(MouseEvent event) { // 아이디 검색 누름 -> 이곳에 db에서 아이디에 해당하는 이름, 프사, 상메 가져와야함
		if (searchId.getText() == "") { // 아무것도 입력 안하고
			System.out.println("이게 맞다 ");
			// 아이디 입력하라는 이미지 나타나게 해야함
		} else if (true) { // 검색한 아이디와 일치하는 계정이 없을 시

		} else { // db에 있는 아이디일시
			searchId.setText(""); // 텍스트필드 비워줌

			String imageURL = "";
			String userId = "";
			String userName = "";
			String userMessage = "";
			Image image = new Image(imageURL);

			otherProfile.setImage(image);
			otherName.setText(userName);
			otherId.setText(userId);
			otherMessage.setText(userMessage);
		}

	}

	@FXML
	void cl_BackToFL(MouseEvent event) { // 뒤로가기 누를 시
		addFriends.setVisible(false);
	}

	/* 프로필 보기 눌렀을때 이벤트 처리 */

	@FXML
	void cl_Close(MouseEvent event) { // 닫기 누르면 프로필 훔쳐보기 끝남
		viewProfile.setVisible(false);
	}

}
