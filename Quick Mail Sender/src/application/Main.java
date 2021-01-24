package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
			arg0.setScene(scene);
			arg0.setTitle("Quick Mail Sender");
			arg0.setResizable(false);
			arg0.show();
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	
	
}
