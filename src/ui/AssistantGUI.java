package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Assistant;
import model.TypesOfUser;
import threads.TimeThread;


public class AssistantGUI {
	
	Assistant assistant;
	
	public AssistantGUI() {
		assistant = new Assistant();
		System.out.println(assistant.createUser("Admin", "123",TypesOfUser.STUDENT));
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
    		FXMLLoader st = new FXMLLoader(getClass().getResource("MainPane.fxml"));
    		st.setController(this);
    		Parent root = st.load();
    		Scene e = new Scene(root);
    		mainStage.setScene(e);
    		FXMLLoader nd = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
    		nd.setController(this);
    		Parent mainM = nd.load();
    		changingPane.getChildren().setAll(mainM);
    		time.start();
    		
    	}else {
    		Alert alertWarnings = new Alert(AlertType.WARNING);
	    	alertWarnings.setTitle("Error");
			alertWarnings.setHeaderText("Invalid user");
			alertWarnings.setContentText("Please check your username/password.");
			alertWarnings.show();
			
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
    
    //------------------------------------------------------ Main Pane/Menu ------------------------------------------------------
    

    @FXML
    private Label MAINPANEusernameLabel;

    @FXML
    private Label MAINPANEhourLabel;

    @FXML
    private BorderPane changingPane;

    @FXML
    void MAINPANElogOut(ActionEvent event) {

    }
    
    @FXML
    private Label MAINMENUbalanceLabel;

    @FXML
    private Button MAINMENUincomesBttn;
    
    private TimeThread time;
    
    public void MAINPANEupdateTime(String realTime) {
    	MAINPANEhourLabel.setText(realTime);
    }

    @FXML
    void MAINMENUincomesBttn(ActionEvent event) throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("IncomeList.fxml"));
    	x.setController(this);
    	Parent r = x.load();
    	changingPane.getChildren().setAll(r);
    }

    @FXML
    void MAINMENUoutlaysBttn(ActionEvent event) throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("OutlayList.fxml"));
    	x.setController(this);
    	Parent r = x.load();
    	changingPane.getChildren().setAll(r);
    }
    
    //------------------------------------------------------ Incomes List ------------------------------------------------------
    
    @FXML
    private TableView<?> INCOMELISTlistView;

    @FXML
    private TableColumn<?, ?> INCOMELISTnameCol;

    @FXML
    private TableColumn<?, ?> INCOMELISTamountCol;

    @FXML
    private TableColumn<?, ?> INCOMELISTtypeCol;

    @FXML
    private Button INCOMELISTSsearchBttn;

    @FXML
    private Button INCOMELISTdeleteBttn;

    @FXML
    void INCOMELISTbackBttn(ActionEvent event) {

    }

    @FXML
    void INCOMELISTcomboBox(ActionEvent event) {

    }

    @FXML
    void INCOMELISTsortBttn(ActionEvent event) {

    }
    
    //------------------------------------------------------ Outlay List ------------------------------------------------------
    
    @FXML
    private TableView<?> OUTLAYLISTlistView;

    @FXML
    private TableColumn<?, ?> OUTLAYLISTnameCol;

    @FXML
    private TableColumn<?, ?> OUTLAYLISTamountCol;

    @FXML
    private TableColumn<?, ?> OUTLAYLISTtypeCol;

    @FXML
    void OUTLAYLISTbackBttn(ActionEvent event) {

    }

    @FXML
    void OUTLAYLISTdeleteBttn(ActionEvent event) {

    }
    
    @FXML
    void OUTLAYLISTsearchBttn(ActionEvent event) {

    }

    @FXML
    void OUTLAYLISTselectedItem(MouseEvent event) {

    }

    @FXML
    void OUTLAYLISTsortBttn(ActionEvent event) {

    }

    @FXML
    void OUTLAYLISTtypeSortCB(ActionEvent event) {

    }

    //------------------------------------------------------ show windows  ------------------------------------------------------
    
    public void start() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	mainStage = new Stage();
    	Scene e = new Scene(root);
    	mainStage.setScene(e);
    	mainStage.show();
		changingPane = new BorderPane();
		time = new TimeThread(this);
	}
}
