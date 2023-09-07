package one;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ChatListController {
	Singleton sgt = Singleton.getInstance();
	@FXML
	private ListView<String> friendsList;
	ObservableList<String> listView = FXCollections.observableArrayList();

	public ChatListController() {
	}

	@FXML
	private void initialize() {
		getListView();
		friendsList.setItems(this.listView);
	}

	@FXML
	void cl_ChattingRoom(MouseEvent event) throws IOException {
		sgt.nextScene2(event, "ChatRoom.fxml");
	}

	@FXML
	void cl_ChatFix(MouseEvent event) throws IOException {
		int selectedIndex = this.friendsList.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {
			String selectedFriend = (String) this.listView.remove(selectedIndex);
			listView.add(0, selectedFriend);
			friendsList.setItems(this.listView);
			friendsList.getSelectionModel().clearSelection();
		}

	}

	@FXML
	void cl_BackToFRL(MouseEvent event) throws IOException {
		sgt.nextScene2(event, "FriendsList.fxml");
	}

	void getListView() {
		for (int i = 0; i < 10; ++i) {
			listView.add("" + i);
		}

	}
}
