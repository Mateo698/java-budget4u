package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import exceptions.NotOnlyNumberException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import model.Assistant;
import model.Income;
import model.MoneyLender;
import model.Outlay;
import model.TypesOfUser;
import model.User;
import threads.TimeThread;


public class AssistantGUI {
	
	private Assistant assistant;
	
	private User localUser;
	private Stage mainStage;
	private Stage popupStage;
	
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
    		localUser = assistant.getUser(username);
    		showMainMenu();
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
    	showRegister();
    }
    
 
    //------------------------------------------------------ Register code ------------------------------------------------------
    
    @FXML
    private TextField REGISTERUsernameTxt;

    @FXML
    private PasswordField REGISTERPasswordTxt;

    @FXML
    private ComboBox<TypesOfUser> REGISTERTypeOfUser;

    
    
    //private Stage popupStage;
    
    
    @FXML
    void REGISTERRegister(ActionEvent event) throws IOException {
    	String name = REGISTERUsernameTxt.getText();
    	String pass = REGISTERPasswordTxt.getText();
    	TypesOfUser type = REGISTERTypeOfUser.getValue();
    	
    	if(name == "" || pass == "" || type == null) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("Missing information!");
			alert.setContentText("Please fill all the fields.");
			alert.showAndWait();			
    	}else {
        	boolean done = assistant.createUser(name, pass, type);
        	
    		if(done) {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Done");
    			alert.setHeaderText("User registred succesfully");
    			alert.setContentText("The user has been created.");
    			alert.showAndWait();	
    			showLogin();
    		}else {
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Error");
    			alert.setHeaderText("Something went wrong!");
    			alert.setContentText("Try with another name");
    			alert.showAndWait();
    		}
    	}
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
    	showIncomeList();
    }

    @FXML
    void MAINMENUoutlaysBttn(ActionEvent event) throws IOException {
    	showOutlayList();
    }
    
    //------------------------------------------------------ Incomes List ------------------------------------------------------
    
    
    
    @FXML
    private TableView<Income> INCOMELISTlistView;

    @FXML
    private TableColumn<Income, String> INCOMELISTnameCol;

    @FXML
    private TableColumn<Income, Long> INCOMELISTamountCol;

    @FXML
    private TableColumn<Income, String> INCOMELISTtypeCol;

    @FXML
    private ComboBox<String> INCOMELISTcomboBox;
    
    @FXML
    void INCOMELISTSaddBttn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddIncome.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	Scene e = new Scene(root);
    	popupStage.setScene(e);
    	popupStage.show();
    	ADDINCOMEbalanceLabel.setText(localUser.getMoney()+"");
    	ADDINCOMEinitComboBox();
    	mainStage.hide();
    }
    
    @FXML
    void INCOMELISTSsearchBttn(ActionEvent event) {

    }

    @FXML
    void INCOMELISTbackBttn(ActionEvent event) throws IOException {
    	showMainMenuNoTime();
    }

    @FXML
    void INCOMELISTdeleteBttn(ActionEvent event) {

    }

    @FXML
    void INCOMELISTselectedItem(MouseEvent event) {

    }

    @FXML
    void INCOMELISTsortBttn(ActionEvent event) {

    }
    
  //------------------------------------------------------ Add Income ------------------------------------------------------
    
    @FXML
    private TextField ADDINCOMEnameTxt;

    @FXML
    private TextField ADDINCOMEamountTxt;

    @FXML
    private ComboBox<String> ADDINCOMEtypeCb;

    @FXML
    private Pane ADDINCOMEregularPane;

    @FXML
    private DatePicker ADDINCOMEdatePickerRegular;

    @FXML
    private Pane ADDINCOMEloanPane;

    @FXML
    private DatePicker ADDINCOMEdatePickerLoan;

    @FXML
    private ComboBox<MoneyLender> ADDINCOMEmoneyLenderCB;

    @FXML
    private Pane ADDINCOMEirregularPane;

    @FXML
    private Label ADDINCOMEbalanceLabel;

    @FXML
    private TextArea ADDINCOMEpurposeTxt;
    

    @FXML
    void ADDINCOMEcancelBttn(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }

    @FXML
    void ADDINCOMEdoneBttn(ActionEvent event) throws IOException {
    	if(ADDINCOMEcheckFields()) {
    		String name = ADDINCOMEnameTxt.getText();
    		try {
				checkText(ADDINCOMEamountTxt.getText());
				long amount = Long.parseLong(ADDINCOMEamountTxt.getText());
				LocalDate current = LocalDate.now();
				Calendar currentDate = new GregorianCalendar(current.getYear(),current.getMonthValue(),current.getDayOfMonth());
				String selected = ADDINCOMEtypeCb.getSelectionModel().getSelectedItem();
				switch (selected) {
				case "Regular":
					LocalDate date = ADDINCOMEdatePickerRegular.getValue();
					Calendar regularDate = new GregorianCalendar(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
					
					assistant.createIncome(localUser, name, amount, currentDate, regularDate);
					
				break;
					
				case "Irregular":
					String purpose = ADDINCOMEpurposeTxt.getText();
					if(purpose.isEmpty()) {
						Alert alert = new Alert(AlertType.WARNING);
				    	alert.setTitle("Error");
						alert.setHeaderText("No purpose");
						alert.setContentText("Please type a purpose of the income.");
						alert.showAndWait();
					}else {
						assistant.createIncome(localUser, name, amount, currentDate, purpose);
					}
				break;
				
				case "Loan":
					if(localUser.getMoneyLenders() == null) {
						Alert alert = new Alert(AlertType.WARNING);
				    	alert.setTitle("Error");
						alert.setHeaderText("No money lenders aviable");
						alert.setContentText("Please add a Money Lender to create a new Loan");
						alert.showAndWait();
					}else{
						MoneyLender lender = ADDINCOMEmoneyLenderCB.getSelectionModel().getSelectedItem();
						LocalDate d = ADDINCOMEdatePickerLoan.getValue();
						Calendar c = new GregorianCalendar(d.getYear(),d.getMonthValue(),d.getDayOfMonth());
						assistant.createIncome(localUser, name, amount, currentDate, lender, c);
					}
				break;

				default:
					Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Error");
					alert.setHeaderText("No type selected");
					alert.setContentText("Please select a type of income.");
					alert.showAndWait();
					break;
				}
				popupStage.close();
				mainStage.show();
				showIncomeList();
			} catch (NotOnlyNumberException e) {
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
				alert.setHeaderText("Numbers only");
				alert.setContentText("Please type numbers only in the amount.");
				alert.showAndWait();
			}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("Missing information!");
			alert.setContentText("Please fill all the fields.");
			alert.showAndWait();	
    	}
    }
    
    private void ADDINCOMEconvertLenderCB() {
        ADDINCOMEmoneyLenderCB.setConverter(new StringConverter<MoneyLender>() {
            @Override
            public String toString(MoneyLender l) {
                return l.getName();
            }

            @Override
            public MoneyLender fromString(final String string) {
                return ADDINCOMEmoneyLenderCB.getItems().stream().filter(type -> type.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }
    
    public boolean checkText(String text) throws NotOnlyNumberException{
    	boolean textFound = false;
    	for (int i = 0; i < text.length(); i++) {
			if(text.charAt(i) < 48 || text.charAt(i) > 57) {
				textFound =  true;
			}
		}
    	if(textFound) {
    		throw new NotOnlyNumberException(text);
    	}else {
    		return textFound;
    	}
    }
    
    public boolean ADDINCOMEcheckFields() {
    	if(ADDINCOMEnameTxt.getText().isEmpty() && ADDINCOMEamountTxt.getText().isEmpty() && ADDINCOMEtypeCb.getSelectionModel().getSelectedItem() == null) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    public void ADDINCOMEinitComboBox() {
    	ADDINCOMEtypeCb.getItems().add("Regular");
    	ADDINCOMEtypeCb.getItems().add("Irregular");
    	ADDINCOMEtypeCb.getItems().add("Loan");
    	if(localUser.getMoneyLenders() != null) {
    		ObservableList<MoneyLender> mLList = FXCollections.observableList(localUser.getMoneyLenders());
        	ADDINCOMEmoneyLenderCB.getItems().setAll(mLList);
        	ADDINCOMEconvertLenderCB();
    	}
    }
    
    @FXML
    void ADDINCOMEcbSelected(ActionEvent event) {
    	String selected = ADDINCOMEtypeCb.getSelectionModel().getSelectedItem();
    	switch (selected) {
		case "Regular":
			ADDINCOMEloanPane.setVisible(false);
			ADDINCOMEirregularPane.setVisible(false);
			ADDINCOMEregularPane.setVisible(true);
			break;
		
		case "Irregular":
			ADDINCOMEloanPane.setVisible(false);
			ADDINCOMEirregularPane.setVisible(true);
			ADDINCOMEregularPane.setVisible(false);
			break;
			
		case "Loan":
			if(localUser.getMoneyLenders() == null) {
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
				alert.setHeaderText("No money lenders aviable");
				alert.setContentText("Please add a Money Lender to create a new Loan");
				alert.showAndWait();
			}else {
				ADDINCOMEloanPane.setVisible(true);
				ADDINCOMEirregularPane.setVisible(false);
				ADDINCOMEregularPane.setVisible(false);
			}
			break;

		default:
		}
    }
    
    //------------------------------------------------------ Outlay List ------------------------------------------------------
    
    @FXML
    private TableView<Outlay> OUTLAYLISTlistView;

    @FXML
    private TableColumn<Outlay, String> OUTLAYLISTnameCol;

    @FXML
    private TableColumn<Outlay, Long> OUTLAYLISTamountCol;

    @FXML
    private TableColumn<Outlay, String> OUTLAYLISTtypeCol;

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
    	popupStage = new Stage();
    	Scene e = new Scene(root);
    	mainStage.setScene(e);
    	mainStage.show();
		changingPane = new BorderPane();
		time = new TimeThread(this);
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				time.setStop();
			}
		});
	}
    
    private void showLogin() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	mainStage = new Stage();
    	Scene e = new Scene(root);
    	mainStage.setScene(e);
    }
    
    private void showMainMenuNoTime() throws IOException {
    	FXMLLoader st = new FXMLLoader(getClass().getResource("MainPane.fxml"));
		st.setController(this);
		Parent root = st.load();
		Scene e = new Scene(root);
		mainStage.setScene(e);
		FXMLLoader nd = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		nd.setController(this);
		Parent mainM = nd.load();
		changingPane.getChildren().setAll(mainM);
		MAINPANEusernameLabel.setText(localUser.getName());
		MAINMENUbalanceLabel.setText(localUser.getMoney()+"");
    }
    
    private void showMainMenu() throws IOException {
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
		MAINPANEusernameLabel.setText(localUser.getName());
		MAINMENUbalanceLabel.setText(localUser.getMoney()+"");
    }
    private void showRegister() throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("Register.fxml"));
    	x.setController(this);
    	Parent root = x.load();
    	Scene e = new Scene(root);
    	mainStage.setScene(e);

    	ObservableList<TypesOfUser> types  = FXCollections.observableArrayList();
    	types.addAll(TypesOfUser.GENERIC, TypesOfUser.STUDENT, TypesOfUser.EMPLOYEE);
    	REGISTERTypeOfUser.setItems(types);
    }
    
    private void showIncomeList() throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("IncomeList.fxml"));
    	x.setController(this);
    	Parent r = x.load();
    	changingPane.getChildren().setAll(r);
    	INCOMELISTnameCol.setCellValueFactory(new PropertyValueFactory<Income,String>("name"));
    	INCOMELISTamountCol.setCellValueFactory(new PropertyValueFactory<Income,Long>("amount"));
    	INCOMELISTtypeCol.setCellValueFactory(new PropertyValueFactory<Income,String>("type"));
    	if(localUser.getIncomes() != null) {
    		ObservableList<Income> incomesList = FXCollections.observableList(localUser.getIncomes());
    		INCOMELISTlistView.setItems(incomesList);
    	}
    }
    
    private void showOutlayList() throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("OutlayList.fxml"));
    	x.setController(this);
    	Parent r = x.load();
    	changingPane.getChildren().setAll(r);
    }
}
