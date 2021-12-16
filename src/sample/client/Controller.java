package sample.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ListView clients;
    @FXML
    private TextField imputText;
    @FXML
    private TextArea chatArea;

    private ObservableList<String> clientsList = FXCollections.observableArrayList(
            "Клиент 1",
            "Клиент 2",
            "Клиент 3"
    );

    @FXML
    public void initialize(){
        clients.setItems(clientsList);
    }



    @FXML
    public void sendMsg(){
        String message = imputText.getText();
        if(message.isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка ввода");
            alert.setHeaderText("Нельзя отправить пустое сообщение");
            alert.showAndWait();
        }else {
            addMsgChat(message);
        }
        imputText.clear();
    }

    private void addMsgChat(String message) {
        chatArea.appendText(message + "\n");
    }
}
