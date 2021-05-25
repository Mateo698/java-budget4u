package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Assistant;
import model.TypesOfUser;


public class AssistantGUI {
	
	Assistant assistant;
	
	public AssistantGUI() {
		assistant = new Assistant();
		assistant.createUser("Pepe", "123",TypesOfUser.STUDENT);
	}
	
	//------------------------------------------------------ Login code ------------------------------------------------------
	@FXML
    private PasswordField LOGINPasswordTxt;

    @FXML
    private TextField LOGINUserTxt;

    @FXML
    void LOGINLogin(ActionEvent event) throws IOException {
    	String username = LOGINUserTxt.getText();
    	String pass = LOGINPasswordTxt.getText();
    	if(assistant.login(username, pass)) {
    		FXMLLoader st = new FXMLLoader(getClass().getResource("MainPane"));
    		st.setController(this);
    		Parent root = st.load();
    		Scene e = new Scene(root);
    		mainStage.setScene(e);
    		changingPane = new BorderPane();
    		FXMLLoader
    		changingPane.getChildren().setAll(null);
    		
    	}else {
    		
    	}
    }

    @FXML
    void LOGINRegister(ActionEvent event) throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("Register.fxml"));
    	x.setController(this);
    	Parent root = x.load();
    	Scene e = new Scene(root);
    	mainStage.setScene(e);
    }
    
 
    //------------------------------------------------------ Register code ------------------------------------------------------
    
    @FXML
    private TextField REGISTERUsernameTxt;

    @FXML
    private PasswordField REGISTERPasswordTxt;

    @FXML
    private ComboBox<?> REGISTERTypeOfUser;

    private Stage mainStage;
    
    private Stage popupStage;
    
    @FXML
    void REGISTERRegister(ActionEvent event) {
    	
    }
    
    //------------------------------------------------------ Main Pane ------------------------------------------------------
    

    @FXML
    private Label MAINPANEusernameLabel;

    @FXML
    private Label MAINPANEhourLabel;

    @FXML
    private BorderPane changingPane;

    @FXML
    void MAINPANElogOut(ActionEvent event) {

    }
    
    //------------------------------------------------------ xxxxx ------------------------------------------------------
    
    public void start() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	mainStage = new Stage();
    	Scene e = new Scene(root);
    	mainStage.setScene(e);
    	mainStage.show();
	}
}
