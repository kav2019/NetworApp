package sample.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
 //       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Parent root =loader.load();
        primaryStage.setTitle("Massage");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Worker worker = new Worker();
        Controller controller = loader.getController();
        controller.setWorker(worker);
        worker.waitMsg(controller);


        primaryStage.setOnCloseRequest(windowEvent -> worker.close());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
