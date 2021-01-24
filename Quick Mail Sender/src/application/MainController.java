package application;
import java.io.IOException;

import email.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	
	private ObservableList<String> hostlist = FXCollections.observableArrayList("Outlook", "Gmail");

	@FXML
	private Button Sendbtn;
	@FXML
	private TextField Fromtxtbx;
	@FXML
	private TextField Totxtbx;
	@FXML
	private PasswordField Passwordtxtbx;
	@FXML
	private ComboBox<String> Hostcmbx;
	@FXML
	private TextField Subjecttxtbx;
	@FXML
	private TextArea Bodytxtbx;
	
	/**
	 * When Send is clicked, this sends the email.
	 * @param ev
	 * @throws IOException 
	 */
	public void SendEmail(ActionEvent ev) throws IOException
	{
		Email from = new Email(Fromtxtbx.getText(), Passwordtxtbx.getText());
		Email to = new Email(Totxtbx.getText());
		new Send(from, to, Bodytxtbx.getText(), Hostcmbx.getValue(), Subjecttxtbx.getText());
	}
	
	/**
	 * initialize the ComboBox of the host
	 */
	@FXML
	private void initialize()
	{
		Hostcmbx.setValue("Outlook");
		Hostcmbx.setItems(hostlist);
	}


}
