package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import exceptions.ExistingUserException;
import exceptions.NegativeAmountException;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import model.Assistant;
import model.Balance;
import model.ExtraordinaryOutlay;
import model.HomeOutlay;
import model.Income;
import model.IncomeNameComparator;
import model.IrregularIncome;
import model.Loan;
import model.MoneyLender;
import model.OrdinaryOutlay;
import model.Outlay;
import model.OutlayNameComparator;
import model.RegularIncome;
import model.TypesOfUser;
import model.User;
import threads.AnimationThread;
import threads.TimeThread;


public class AssistantGUI {
	
	private Assistant assistant;
	
	private User localUser;
	private Stage mainStage;
	private Stage popupStage;
	private Income editIncomeIndex;
	private Outlay editOutlayIndex;
	private int[] boundsOne;
	private int[] boundsTwo;
	 
	boolean registeredNewUSer = false;
	public AssistantGUI(){
		assistant = new Assistant();
		assistant.createUser("Admin", "123",TypesOfUser.GENERIC);
		editIncomeIndex = null;
	}
	
	//------------------------------------------------------ LOGIN CODE ------------------------------------------------------
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
    		checker();
    		if(registeredNewUSer) {
    			showMainMenuNoTime();
    		}else {
    			showMainMenu();
    		}
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
    
 
    //------------------------------------------------------ REGISTER CODE ------------------------------------------------------
    
    @FXML
    private TextField REGISTERUsernameTxt;

    @FXML
    private PasswordField REGISTERPasswordTxt;

    @FXML
    private ComboBox<TypesOfUser> REGISTERTypeOfUser;

    
    
    @FXML
    void REGISTERRegister(ActionEvent event) throws IOException, ExistingUserException {
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
        	
        	try {
    			checkUsers(done);
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Done");
    			alert.setHeaderText("User registred succesfully");
    			alert.setContentText("The user has been created.");
    			alert.show();	
    			showLogin();
    		}catch(ExistingUserException ex) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Error!");
    			alert.setHeaderText("The user cann't registeres.");
    			alert.setContentText("The user has a registered name, try again.");
    			alert.showAndWait();	
    		}
    	}
    }
    
    private void checkUsers(boolean done) throws ExistingUserException {
    	if(!done) {
    		throw new ExistingUserException();
    	}
    }
    
    //------------------------------------------------------ MAIN MENU ------------------------------------------------------
    

    @FXML
    private Label MAINPANEusernameLabel;

    @FXML
    private Label MAINPANEhourLabel;

    @FXML
    private BorderPane changingPane;
    
    @FXML
    private Circle MAINMENUball1;

    @FXML
    private Circle MAINMENUball2;    

    @FXML
    void MAINPANElogOut(ActionEvent event) throws IOException {
    	showLogin();
    }
    
    
    
    @FXML
    private Label MAINMENUbalanceLabel;

    @FXML
    private Button MAINMENUincomesBttn;
    
    private TimeThread time;
    
    private AnimationThread ballOne;
    
    private AnimationThread ballTwo;
    
    public void MAINPANEupdateTime(String realTime) {
    	MAINPANEhourLabel.setText(realTime);
    }
    
    @FXML
    void MAINMENUbalanceBttn(ActionEvent event) throws IOException {
    	showBalanceList();
    }

    @FXML
    void MAINMENUincomesBttn(ActionEvent event) throws IOException {
    	showIncomeList();
    }

    @FXML
    void MAINMENUoutlaysBttn(ActionEvent event) throws IOException {
    	showOutlayList();
    }
    
    public void MAINMENUupdateBall(int x, int figure) {
    	switch(figure) {
    		case 1:	MAINMENUball1.setLayoutX(x);
    			break;
    		case 2: MAINMENUball2.setLayoutX(x);
    			break;
    	}
    }
    
    //------------------------------------------------------ INCOME LIST ------------------------------------------------------
    
    
    
    @FXML
    private TableView<Income> INCOMELISTlistView;

    @FXML
    private TableColumn<Income, String> INCOMELISTnameCol;

    @FXML
    private TableColumn<Income, Long> INCOMELISTamountCol;

    @FXML
    private TableColumn<Income, String> INCOMELISTtypeCol;
    
    @FXML
    private TableColumn<Income, String> INCOMELISTdateCol;

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
    void INCOMELISTSsearchBttn(ActionEvent event) throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("SearchIncome.fxml"));
    	x.setController(this);
    	Parent root = x.load();
    	Scene e = new Scene(root);
    	popupStage.setScene(e);
    	SEARCHINCOMEnameCol.setCellValueFactory(new PropertyValueFactory<Income,String>("name"));
		SEARCHINCOMEamountCol.setCellValueFactory(new PropertyValueFactory<Income,Long>("amount"));
		SEARCHINCOMEtypeCol.setCellValueFactory(new PropertyValueFactory<Income,String>("type"));
    	popupStage.show();
    	mainStage.hide();
    }

    @FXML
    void INCOMELISTbackBttn(ActionEvent event) throws IOException {
    	showMainMenuNoTime();
    }

    @FXML
    void INCOMELISTdeleteBttn(ActionEvent event) {
    	if(INCOMELISTlistView.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("No item selected.");
			alert.setContentText("Please select an item if you want to delete it.");
			alert.showAndWait();
    	}else {
    		Income delete = INCOMELISTlistView.getSelectionModel().getSelectedItem();
    		localUser.removeIncome(delete);
    		ADDINCOMErefreshTable();
    	}
    }
    
    public void ADDINCOMErefreshTable() {
    	INCOMELISTnameCol.setCellValueFactory(new PropertyValueFactory<Income,String>("name"));
    	INCOMELISTamountCol.setCellValueFactory(new PropertyValueFactory<Income,Long>("amount"));
    	INCOMELISTtypeCol.setCellValueFactory(new PropertyValueFactory<Income,String>("type"));
    	if(localUser.getIncomes() != null) {
    		ObservableList<Income> incomesList = FXCollections.observableList(localUser.getIncomes());
    		INCOMELISTlistView.setItems(incomesList);
    	}
    }

    @FXML
    void INCOMELISTselectedItem(MouseEvent event) throws IOException {
    	if(event.getClickCount() == 2) {
    		Income selected = INCOMELISTlistView.getSelectionModel().getSelectedItem();
    		editIncomeIndex = INCOMELISTlistView.getSelectionModel().getSelectedItem();
    		FXMLLoader x = new FXMLLoader(getClass().getResource("EditIncome.fxml"));
    		x.setController(this);
    		Parent root = x.load();
    		Scene e = new Scene(root);
    		popupStage.setScene(e);
    		
    		EDITINCOMEnameTxt.setText(selected.getName());
    		EDITINCOMEamountTxt.setText(selected.getAmount()+"");
    		
    		
    		if(selected instanceof RegularIncome) {
    			EDITINCOMEtypeLabel.setText("Regular");
    			EDITINCOMEregularPane.setVisible(true);
    			RegularIncome realIncome = (RegularIncome) selected;
    			LocalDate date = LocalDate.of(realIncome.getMonthlyDate().get(Calendar.YEAR),realIncome.getMonthlyDate().get(Calendar.MONTH) ,realIncome.getMonthlyDate().get(Calendar.DAY_OF_MONTH));
    			EDITINCOMEregularDate.setValue(date);
    		}else if(selected instanceof IrregularIncome) {
    			EDITINCOMEtypeLabel.setText("Irregular");
    			EDITINCOMEirregularPane.setVisible(true);
    			IrregularIncome realIncome = (IrregularIncome) selected;
    			EDITINCOMEpurposeTxt.setText(realIncome.getPurpose());
    		}else {
    			EDITINCOMEtypeLabel.setText("Loan");
    			EDITINCOMEloanPane.setVisible(true);
    			Loan realIncome = (Loan) selected;
    			LocalDate date = LocalDate.of(realIncome.getPayDate().get(Calendar.YEAR),realIncome.getPayDate().get(Calendar.MONTH), realIncome.getPayDate().get(Calendar.DAY_OF_MONTH));
    			EDITINCOMEloanDate.setValue(date);
    			ObservableList<MoneyLender> mLList = FXCollections.observableList(localUser.getMoneyLenders());
            	ADDINCOMEmoneyLenderCB.getItems().setAll(mLList);
    			EDITINCOMElenderCB.getItems().setAll(mLList);
    			EDITINCOMEconvertLenderCB();
    		}
    		
    		EDITINCOMEbalanceLabel.setText(localUser.getMoney()+"");
    		popupStage.show();
    		mainStage.hide();
    	}
    }

    @FXML
    void INCOMELISTsortBttn(ActionEvent event) {
    	if(INCOMELISTcomboBox.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("No sort category selected.");
			alert.setContentText("Please select a category to sort the list.");
			alert.showAndWait();
    	}else {
    		String selectedCategory = INCOMELISTcomboBox.getSelectionModel().getSelectedItem();
    		switch (selectedCategory) {
			case "Name":
				ArrayList<Income> nameSorted = localUser.getIncomeNameSorted();
				if(nameSorted != null) {
					ObservableList<Income> incomesList = FXCollections.observableList(nameSorted);
		    		INCOMELISTlistView.setItems(incomesList);
		    		INCOMELISTlistView.refresh();
				}else {
					Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Error");
					alert.setHeaderText("No items to be sorted.");
					alert.setContentText("Please add incomes to be sorted before sorting.");
					alert.showAndWait();
				}
				break;

			case "Amount":
				ArrayList<Income> amountSorted = localUser.getIncomeAmountSorted();
				if(amountSorted != null) {
					ObservableList<Income> incomesList = FXCollections.observableList(amountSorted);
		    		INCOMELISTlistView.setItems(incomesList);
		    		INCOMELISTlistView.refresh();
				}else{
					Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Error");
			    	alert.setHeaderText("No items to be sorted.");
					alert.setContentText("Please add incomes to be sorted before sorting.");
					alert.showAndWait();
				}
				break;
				
			case "Date":
				ArrayList<Income> dateSorted = localUser.getIncomeDateSorted();
				if(dateSorted != null) {
					ObservableList<Income> incomesList = FXCollections.observableList(dateSorted);
		    		INCOMELISTlistView.setItems(incomesList);
		    		INCOMELISTlistView.refresh();
				}else {
					Alert alert = new Alert(AlertType.WARNING);
			    	alert.setTitle("Error");
			    	alert.setHeaderText("No items to be sorted.");
					alert.setContentText("Please add incomes to be sorted before sorting.");
					alert.showAndWait();
				}
				break;
				
			default:
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
		    	alert.setHeaderText("No category selected.");
				alert.setContentText("Please select.");
				alert.showAndWait();
				break;
			}
    	}
    	
    	
    }
    
    @FXML
    void INCOMELISTmLbttn(ActionEvent event) throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("AddML.fxml"));
    	x.setController(this);
    	Parent root = x.load();
    	Scene e = new Scene(root);
    	popupStage.setScene(e);
    	popupStage.show();
    	mainStage.hide();
    }
    
    @FXML
    private TextField ADDMLnametxt;

    @FXML
    private TextField ADDMLlastTxt;

    @FXML
    private TextField ADDMLphone;

    @FXML
    void ADDMLback(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }

    @FXML
    void ADDMLdone(ActionEvent event) {
    	localUser.addML(ADDMLnametxt.getText(), ADDMLlastTxt.getText(), ADDMLphone.getText());
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Done");
    	alert.setHeaderText("Added successfully.");
		alert.setContentText("Going back to list.");
		alert.showAndWait();
		popupStage.close();
		mainStage.show();
    }
    
  //------------------------------------------------------ ADD INCOME ------------------------------------------------------
    
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
				if(selected != null) {
					
				}
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
			} catch (NullPointerException n) {
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
				alert.setHeaderText("No type selected");
				alert.setContentText("Please select a type of income.");
				alert.showAndWait();
			} catch (NegativeAmountException n) {
				Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
				alert.setHeaderText("Invalid amount");
				alert.setContentText("Please type only positive amounts.");
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
    
    public boolean checkText(String text) throws NotOnlyNumberException, NegativeAmountException{
    	boolean textFound = false;
    	for (int i = 0; i < text.length(); i++) {
			if(text.charAt(i) < 48 || text.charAt(i) > 57) {
				textFound =  true;
			}
		}
    	if(textFound) {
    		throw new NotOnlyNumberException(text);
    	}else {
    		long amount = Long.parseLong(text);
    		if(amount < 0) {
    			throw new NegativeAmountException(amount);
    		}
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
    //------------------------------------------------------ EDIT INCOME ------------------------------------------------------
    @FXML
    private TextField EDITINCOMEnameTxt;

    @FXML
    private TextField EDITINCOMEamountTxt;
    
    @FXML
    private Label EDITINCOMEtypeLabel;

    @FXML
    private Pane EDITINCOMEregularPane;

    @FXML
    private DatePicker EDITINCOMEregularDate;

    @FXML
    private Pane EDITINCOMEloanPane;

    @FXML
    private DatePicker EDITINCOMEloanDate;

    @FXML
    private ComboBox<MoneyLender> EDITINCOMElenderCB;

    @FXML
    private Pane EDITINCOMEirregularPane;

    @FXML
    private TextArea EDITINCOMEpurposeTxt;

    @FXML
    private Label EDITINCOMEbalanceLabel;

    @FXML
    public void EDITINCOMEcancelBttn(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }

    @FXML
    public void EDITINCOMEdoneBttn(ActionEvent event) {
    	if(EDITINCOMEcheckFields()) {
    		if(editIncomeIndex instanceof RegularIncome) {
    			LocalDate d = EDITINCOMEregularDate.getValue();
    			Calendar c = new GregorianCalendar(d.getYear(),d.getMonthValue(),d.getDayOfMonth());
    			RegularIncome newRegular = new RegularIncome(EDITINCOMEnameTxt.getText(), Long.parseLong(EDITINCOMEamountTxt.getText()), editIncomeIndex.getCreationDate(), c);
    			localUser.editIncome(editIncomeIndex, newRegular);
        	}else if(editIncomeIndex instanceof IrregularIncome) {
        		IrregularIncome newIr = new IrregularIncome(EDITINCOMEnameTxt.getText(),Long.parseLong(EDITINCOMEamountTxt.getText()) , editIncomeIndex.getCreationDate(), EDITINCOMEpurposeTxt.getText());
        		localUser.editIncome(editIncomeIndex, newIr);
        	}else {
        		LocalDate d = EDITINCOMEloanDate.getValue();
        		Calendar c = new GregorianCalendar(d.getYear(),d.getMonthValue(),d.getDayOfMonth());
        		MoneyLender lender = EDITINCOMElenderCB.getSelectionModel().getSelectedItem();
        		Loan newLoan = new Loan(EDITINCOMEnameTxt.getText(),Long.parseLong(EDITINCOMEamountTxt.getText()),editIncomeIndex.getCreationDate(),c,lender);
        		localUser.editIncome(editIncomeIndex, newLoan);
        	}
    		popupStage.close();
    		mainStage.show();
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("Empty fields.");
			alert.setContentText("Please fill all the fields.");
			alert.showAndWait();
    	}
    }
    
    private void EDITINCOMEconvertLenderCB() {
        EDITINCOMElenderCB.setConverter(new StringConverter<MoneyLender>() {
            @Override
            public String toString(MoneyLender l) {
                return l.getName();
            }

            @Override
            public MoneyLender fromString(final String string) {
                return EDITINCOMElenderCB.getItems().stream().filter(type -> type.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    
    public boolean EDITINCOMEcheckFields() {
    	if(EDITINCOMEnameTxt.getText().isEmpty() && EDITINCOMEamountTxt.getText().isEmpty()) {
    		return false;
    	}else {
    		if(editIncomeIndex instanceof IrregularIncome) {
    			if(EDITINCOMEpurposeTxt.getText().isEmpty()) {
    				return false;
    			}else {
    				return true;
    			}
    		}else {
    			return true;
    		}	
    	}
    }
    
    //------------------------------------------------------ SEARCH INCOME ------------------------------------------------------
    
    @FXML
    private TextField SEARCHINCOMEnameTxt;


    @FXML
    private TableView<Income> SEARCHINCOMEtable;

    @FXML
    private TableColumn<Income, String> SEARCHINCOMEnameCol;

    @FXML
    private TableColumn<Income, Long> SEARCHINCOMEamountCol;

    @FXML
    private TableColumn<Income, String> SEARCHINCOMEtypeCol;

    @FXML
    void SEARCHINCOMEcancelBttn(ActionEvent event) {
    	
    }
    
    @FXML
    void SEARCHINCOMEsearchBttn(ActionEvent event) {
    	ArrayList<Income> list = localUser.getIncomeNameSorted();
    	if(list != null) {
    		int index = Collections.binarySearch(list, new Income(SEARCHINCOMEnameTxt.getText(), 0, null),new IncomeNameComparator());
    		if(index >= 0) {
    			ArrayList<Income> oneItem = new ArrayList<Income>();
    			oneItem.add(list.get(index));
    			ObservableList<Income> incomesList = FXCollections.observableList(oneItem);
    			SEARCHINCOMEtable.setItems(incomesList);
    			SEARCHINCOMEtable.refresh();
    		}else {
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Error");
    			alert.setHeaderText("No income found.");
    			alert.setContentText("There is not any item with that name.");
    			alert.showAndWait();
    		}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("No incomes found.");
			alert.setContentText("There are not any incomes yet.");
			alert.showAndWait();
    	}
    }

    @FXML
    public void SEARCHINCOMEdoneBttn(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }
    
    
    //------------------------------------------------------OUTLAY LIST ------------------------------------------------------
    
    @FXML
    private TableView<Outlay> OUTLAYLISTlistView;

    @FXML
    private TableColumn<Outlay, String> OUTLAYLISTnameCol;

    @FXML
    private TableColumn<Outlay, Long> OUTLAYLISTamountCol;

    @FXML
    private TableColumn<Outlay, String> OUTLAYLISTtypeCol;

    @FXML
    private TableColumn<Outlay, String> OUTLAYLISTdateCol;
    
    @FXML
    private ComboBox<String> OUTLAYLISTtypeSort;
    
    @FXML
    void OUTLAYLISTbackBttn(ActionEvent event) throws IOException {
    	showMainMenuNoTime();
    }

    @FXML
    void OUTLAYLISTdeleteBttn(ActionEvent event) throws IOException {
    	if(OUTLAYLISTlistView.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("No item selected.");
			alert.setContentText("Please select an item if you want to delete it.");
			alert.showAndWait();
    	}else {
    		Outlay delete = OUTLAYLISTlistView.getSelectionModel().getSelectedItem();
    		localUser.removeOutlay(delete);
    		showOutlayList();
    	}
    }
    
    @FXML
    void OUTLAYLISTsearchBttn(ActionEvent event) throws IOException {
    	showSearchOutlay();
    }

    @FXML
    void OUTLAYLISTselectedItem(MouseEvent event) throws IOException {
    	if(event.getClickCount() == 2) {
	    	Outlay choosed = OUTLAYLISTlistView.getSelectionModel().getSelectedItem();
	    		    	
	    	if(choosed != null) {
	    		editOutlayIndex = choosed;
	    		String name = choosed.getName();
	    		long value = choosed.getAmount();
	    		String type = choosed.getType();
	    		
	    		showEditOutlay(name, value, type, null, null);
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Warning!");
	    		alert.setHeaderText("Invalid selection");
	    		alert.setContentText("Try again with a valid outlay");
	    	}
    	}
    }

    @FXML
    void OUTLAYLISTsortBttn(ActionEvent event) {
    	String sortType = OUTLAYLISTtypeSort.getValue();
    	if(!sortType.equals("")) {
    		switch (sortType) {
				case "Name":
					ArrayList<Outlay> nameSorted = localUser.getOutlayNameSorted();
					if(nameSorted != null) {
						ObservableList<Outlay> outlaysList = FXCollections.observableList(nameSorted);
			    		OUTLAYLISTlistView.setItems(outlaysList);
			    		OUTLAYLISTlistView.refresh();
					}else {
						Alert alert = new Alert(AlertType.WARNING);
				    	alert.setTitle("Error");
						alert.setHeaderText("No items to be sorted.");
						alert.setContentText("Please add incomes to be sorted before sorting.");
						alert.showAndWait();
					}
					break;
	
				case "Amount":
					ArrayList<Outlay> amountSorted = localUser.getOutlayAmountSorted();
					if(amountSorted != null) {
						ObservableList<Outlay> outlaysList = FXCollections.observableList(amountSorted);
			    		OUTLAYLISTlistView.setItems(outlaysList);
			    		OUTLAYLISTlistView.refresh();
					}else{
						Alert alert = new Alert(AlertType.WARNING);
				    	alert.setTitle("Error");
				    	alert.setHeaderText("No items to be sorted.");
						alert.setContentText("Please add incomes to be sorted before sorting.");
						alert.showAndWait();
					}
					break;
					
				case "Date":
					ArrayList<Income> dateSorted = localUser.getIncomeDateSorted();
					if(dateSorted != null) {
						ObservableList<Income> incomesList = FXCollections.observableList(dateSorted);
			    		INCOMELISTlistView.setItems(incomesList);
			    		INCOMELISTlistView.refresh();
					}else {
						Alert alert = new Alert(AlertType.WARNING);
				    	alert.setTitle("Error");
				    	alert.setHeaderText("No items to be sorted.");
						alert.setContentText("Please add incomes to be sorted before sorting.");
						alert.showAndWait();
					}
					break;
    		}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Wrong choose");
    		alert.setHeaderText("Try again");
    		alert.setContentText("You haven't choosed a sort type yet.");
    		alert.showAndWait();
    	}    	
    }
    


    @FXML
    void OUTLAYLISTaddBttn(ActionEvent event) throws IOException {
    	showAddOutlay();
    }
    
    //------------------------------------------------------ ADD OUTLAY ------------------------------------------------------
    
    @FXML
    private TextField ADDOUTLAYnameTxt;

    @FXML
    private TextField ADDOUTLAYamountTxt;

    @FXML
    private ComboBox<String> ADDOUTLAYtype;

    @FXML
    private Pane ADDOUTLAYregularPane;

    @FXML
    private DatePicker ADDOUTLAYregularDate;

    @FXML
    private Pane ADDOUTLAYirregularPane;

    @FXML
    private TextArea ADDOUTLAYpurposeTxt;

    @FXML
    private Label ADDOUTLAYbalanceLabel;

    @FXML
    void ADDOULATYdoneBttn(ActionEvent event) throws NotOnlyNumberException {
    	String name = ADDOUTLAYnameTxt.getText();
    	
    	try {
    		checkText(ADDOUTLAYamountTxt.getText());
    		long amount = Long.parseLong(ADDOUTLAYamountTxt.getText());
	    	
	    	LocalDate current = LocalDate.now(); 
	    	Calendar cd = new GregorianCalendar(current.getYear(), current.getMonthValue(), current.getDayOfMonth());  	
	    	String choosed = ADDOUTLAYtype.getValue();
	    	
	    	LocalDate payDate;
	    	Calendar pd;
	    	String reason;
	    	
	    	if(name == "" || ADDOUTLAYamountTxt.getText() == "" || choosed == null) {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error!");
	    		alert.setHeaderText("Missing information!");
	    		alert.setContentText("Please, fill all the fields.");
	    		alert.showAndWait();
	    	}else {   				    	
		    	switch(choosed) {
		    		case "Regular":
		    			if(ADDOUTLAYregularDate.getValue() !=null) {
		    				payDate = ADDOUTLAYregularDate.getValue();
			    			pd = new GregorianCalendar(payDate.getYear(), payDate.getMonthValue(), payDate.getDayOfMonth());
			    			assistant.createOutlay(localUser, name, amount, cd, pd);
		    			}
		    			break;
		    		
		    		case "Irregular":    			
		    			reason = ADDOUTLAYpurposeTxt.getText();	
		    			assistant.createOutlay(localUser, name, amount, cd, reason, 1);
		    			break;
		    		
		    		case "Home":		    			
		    			reason = ADDOUTLAYpurposeTxt.getText();	
			    		assistant.createOutlay(localUser, name, amount, cd, reason, 2);
			    		break;
		    	}
	    	}
	    	popupStage.close();
	    	mainStage.show();
	    	refreshOutlayList();
    	}catch(NotOnlyNumberException e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error!");
    		alert.setHeaderText("Wrong amount");
    		alert.setContentText("Please fill the amount field only with numbers.");
    		alert.showAndWait();
    	}catch (NegativeAmountException n) {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("Invalid amount");
			alert.setContentText("Please type only positive amounts.");
			alert.showAndWait();
    	}
    	
    }

    @FXML
    void ADDOUTLAYcancelBttn(ActionEvent event) throws IOException {
    	popupStage.close();
    	mainStage.show();
    }
    
    @FXML
    void ADDOUTLAYtypeM(ActionEvent event) {
    	String selected = ADDOUTLAYtype.getValue();
    	if(selected.equals("Regular")) {
    		ADDOUTLAYregularPane.setVisible(true);
    		ADDOUTLAYirregularPane.setVisible(false);
    	}else if(selected.equals("Irregular")) {
    		ADDOUTLAYregularPane.setVisible(false);
    		ADDOUTLAYirregularPane.setVisible(true);
    	}else if(selected.equals("Home")){
    		ADDOUTLAYregularPane.setVisible(false);
    		ADDOUTLAYirregularPane.setVisible(true);
    	}
    }
    
    //------------------------------------------------------ EDIT OUTLAY ------------------------------------------------------
    
    @FXML
    private TextField EDITOUTLAYnameTxt;

    @FXML
    private TextField EDITOUTLAYamountTxt;

    @FXML
    private ComboBox<String> EDITOUTLAYtype;

    @FXML
    private Pane EDITOUTLAYregularPane;

    @FXML
    private DatePicker EDITOUTLAYregularDate;

    @FXML
    private Pane EDITOUTLAYirregularPane;

    @FXML
    private TextArea EDITOUTLAYpurposeTxt;

    @FXML
    private Label EDITOUTLAYbalanceLabel;

    @FXML
    void EDITOULATYdoneBttn(ActionEvent event){
    	boolean filled = false;
    	
    	String newName = EDITOUTLAYnameTxt.getText();
    	String type = EDITOUTLAYtype.getValue();
    	String valueS = EDITOUTLAYamountTxt.getText();
    	String purpose = EDITOUTLAYpurposeTxt.getText();
    	Calendar pD = null;
    	
    	if(newName != "" && type != "" && valueS != "") {
    		if(EDITOUTLAYirregularPane.isVisible() && EDITOUTLAYpurposeTxt.getText() != "") {
    			filled = true;
    		}else if(EDITOUTLAYregularPane.isVisible()){
    			LocalDate date = EDITOUTLAYregularDate.getValue();
    			pD = new GregorianCalendar(date.getDayOfYear(), date.getMonthValue(), date.getYear()); 
    			filled = true;
    		}

    	if(filled) {
    		
    		Calendar cD = editOutlayIndex.getCreationDate();
    		Outlay newOutlay;
    		
	    	long amount = Long.parseLong(EDITOUTLAYamountTxt.getText());
			if(editOutlayIndex instanceof OrdinaryOutlay) {
				newOutlay = new OrdinaryOutlay(newName, amount, cD, pD);
			}else if(editOutlayIndex instanceof ExtraordinaryOutlay) {
				newOutlay = new ExtraordinaryOutlay(newName, amount, cD, purpose);
			}else {
				newOutlay = new HomeOutlay(newName, amount, cD, purpose);
			}
			
			localUser.editOutlay(editOutlayIndex, newOutlay);
			popupStage.close();
			refreshOutlayList();
			mainStage.show();
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING);
		    	alert.setTitle("Error");
				alert.setHeaderText("Empty fields.");
				alert.setContentText("Please fill all the fields.");
				alert.showAndWait();
	    	}
    	}
    }

    @FXML
    void EDITOUTLAYcancelBttn(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }


    //------------------------------------------------------ SEARCH OUTLAY  ------------------------------------------------------
    
    @FXML
    private TextField SEARCHOUTLAYnameTxt;

    @FXML
    private TableView<Outlay> SEARCHOUTLAYtable;

    @FXML
    private TableColumn<Outlay, String> SEARCHOUTLAYnameCol;

    @FXML
    private TableColumn<Outlay, Long> SEARCHOUTLAYamountCol;

    @FXML
    private TableColumn<Outlay, String> SEARCHOUTLAYypeCol;

    @FXML
    void SEARCHOUTLAYcancelBttn(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }

    @FXML
    void SEARCHOUTLAYdoneBttn(ActionEvent event) {
    	popupStage.close();
    	mainStage.show();
    }

    @FXML
    void SEARCHOUTLAYsearchBttn(ActionEvent event) {
    	ArrayList<Outlay> list = localUser.getOutlayNameSorted();
    	if(list != null) {
    		int index = Collections.binarySearch(list, new Outlay(SEARCHOUTLAYnameTxt.getText(), 0, null),new OutlayNameComparator());
    		if(index >= 0) {
    			ArrayList<Outlay> oneItem = new ArrayList<Outlay>();
    			oneItem.add(list.get(index));
    			ObservableList<Outlay> outlaysList = FXCollections.observableList(oneItem);
    			SEARCHOUTLAYtable.setItems(outlaysList);
    			SEARCHOUTLAYtable.refresh();
    		}else {
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Error");
    			alert.setHeaderText("No outlay found.");
    			alert.setContentText("There is not any item with that name.");
    			alert.showAndWait();
    		}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Error");
			alert.setHeaderText("No outlays found.");
			alert.setContentText("There are not any incomes yet.");
			alert.showAndWait();
    	}
    }

    
    //------------------------------------------------------ MONEYLENDER ------------------------------------------------------
    
    @FXML
    private TextField MONEYLENDERNameTxt;

    @FXML
    private TextField MONEYLENDERLastNameTxt;

    @FXML
    private TextField MONEYLENDERPhoneTxt;

    @FXML
    void MONEYLENDERRBack(ActionEvent event) throws IOException {
    	showMainMenu();
    }

    @FXML
    void MONEYLENDERCreateLoaner(ActionEvent event) {
    	String name = MONEYLENDERNameTxt.getText();
    	String lastName = MONEYLENDERLastNameTxt.getText();
    	String phone = MONEYLENDERPhoneTxt.getText();
    	
    	if(name != "" && lastName != "" && phone != "") {
    		assistant.createMoneyLender(localUser, name, lastName, phone);
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error!");
    		alert.setHeaderText("Missing info");
    		alert.setContentText("Please fill all the fields.");
    		alert.showAndWait();
    	}
    }
    //------------------------------------------------------ BALANCE LIST ------------------------------------------------------
    
    @FXML
    private TableView<Balance> BALANCELISTtable;

    @FXML
    private TableColumn<Balance, String> BALANCELISTnameCol;

    @FXML
    private TableColumn<Balance, String> BALANCELISTdateCol;

    @FXML
    private TableColumn<Balance, Double> BALANCELISTincomesCol;

    @FXML
    private TableColumn<Balance, Double> BALANCELISToutCol;

    @FXML
    private TableColumn<Balance, Double> BALANCELISTloanCol;

    @FXML
    private TableColumn<Balance, Double> BALANCELISTtotalCol;

    @FXML
    void BALANCELISTbackBttn(ActionEvent event) throws IOException {
    	showMainMenuNoTime();
    }
    
    //------------------------------------------------------ SHOW WINDOWS ------------------------------------------------------
    
    private String fileName = "data/bounds.txt";
    
    private void checker() {
    	LocalDate d = LocalDate.now();
    	Calendar c = new GregorianCalendar(d.getYear(),d.getMonthValue(),d.getDayOfMonth());
    	if(localUser.getLastLogin() != c) {
    		localUser.dailyCheck(c);
    		localUser.setLastLogin(c);
    		int previousMonth = 0;
    		if(c.get(Calendar.MONTH) == 1) {
    			previousMonth = 12;
    		}else {
    			previousMonth = c.get(Calendar.MONTH)-1;
    		}
    		if(!localUser.checkMonthBalance(previousMonth)) {
    			localUser.checkBalance(previousMonth, c.get(Calendar.YEAR));
    		}
    	}
    }

    public void importData() throws IOException{
    	BufferedReader br = new BufferedReader(new FileReader(fileName));
    	String line = br.readLine();
    	while(line!=null){
    		String[] parts = line.split(" ");
    		int[] bounds = new int[parts.length];
    		for (int i = 0; i < bounds.length; i++) {
				bounds[i] = Integer.parseInt(parts[i]);
			}
    		
    		if(boundsOne == null) {
    			boundsOne = bounds;
    		}else {
    			if(boundsTwo == null) {
    				boundsTwo = bounds;
    			}else {
    				System.out.println("Shit");
    			}
    		}
    		line = br.readLine();
    	}
    	br.close();
    }

    public void start() throws IOException, ClassNotFoundException {
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
		importData();
		ballOne = new AnimationThread(boundsOne[0], boundsOne[1], boundsOne[2], this, boundsOne[3]);
		ballTwo = new AnimationThread(boundsTwo[0], boundsTwo[1], boundsTwo[2], this, boundsTwo[3]);
		
		//ballOne = new AnimationThread(20, 349, 349, this, 1);
		//ballTwo = new AnimationThread(513, 842, 513, this, 2);
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				time.setStop();
				ballOne.setStop();
				ballTwo.setStop();
				try {
					assistant.saveData();
				} catch (IOException e) {
					
				}
			}
		});
		popupStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				time.setStop();
				ballOne.setStop();
				ballTwo.setStop();
				try {
					assistant.saveData();
				} catch (IOException e) {
					
				}
			}
		});
		
		assistant.loadData();
	}
    
    private void showLogin() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
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
		ballOne.start();
		ballTwo.start();
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
    	INCOMELISTdateCol.setCellValueFactory(new PropertyValueFactory<Income,String>("date"));
    	if(localUser.getIncomes() != null) {
    		ObservableList<Income> incomesList = FXCollections.observableList(localUser.getIncomes());
    		INCOMELISTlistView.setItems(incomesList);
    	}
    	INCOMELISTcomboBox.getItems().add("Name");
    	INCOMELISTcomboBox.getItems().add("Amount");
    	INCOMELISTcomboBox.getItems().add("Date");
    }
    
    private void showOutlayList() throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("OutlayList.fxml"));
    	x.setController(this);
    	Parent r = x.load();
    	changingPane.getChildren().setAll(r);
    	refreshOutlayList();

    	OUTLAYLISTtypeSort.getItems().add("Name");
    	OUTLAYLISTtypeSort.getItems().add("Amount");
    	OUTLAYLISTtypeSort.getItems().add("Date");
    }
    
    private void refreshOutlayList() {
        OUTLAYLISTnameCol.setCellValueFactory(new PropertyValueFactory<Outlay, String>("name"));
        OUTLAYLISTamountCol.setCellValueFactory(new PropertyValueFactory<Outlay, Long>("amount"));
        OUTLAYLISTtypeCol.setCellValueFactory(new PropertyValueFactory<Outlay, String>("type"));
        OUTLAYLISTdateCol.setCellValueFactory(new PropertyValueFactory<Outlay, String>("date"));
        
        ArrayList<Outlay> outlaysList = localUser.getOutlays();
        if(outlaysList != null) {
        	ObservableList<Outlay> outlays = FXCollections.observableArrayList(outlaysList);
        	OUTLAYLISTlistView.setItems(outlays);
        }
    }
   /*
    *  private void showMoneyLender () throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("MoneyLender.fxml"));
    	x.setController(this);
    	Parent r = x.load();
    	changingPane.getChildren().setAll(r);
    }
    */
    
    private void showAddOutlay() throws IOException{ 	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOutlay.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	Scene e = new Scene(root);
    	popupStage.setScene(e);
    	popupStage.show();
    	mainStage.hide();
    	
    	ADDOUTLAYbalanceLabel.setText(localUser.getMoney() + "");
    	  	
    	ADDOUTLAYtype.getItems().add("Regular");
    	ADDOUTLAYtype.getItems().add("Irregular");
    	ADDOUTLAYtype.getItems().add("Home");
    }
    
    private void showEditOutlay(String name, long value, String option, Calendar pd, String reason) throws IOException{ 	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditOutlay.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	Scene e = new Scene(root);
    	popupStage.setScene(e);
    	popupStage.show();
    	mainStage.hide();
    	
    	EDITOUTLAYbalanceLabel.setText(localUser.getMoney() + "");
    	
    	EDITOUTLAYamountTxt.setText((value+"").substring(1));
    	EDITOUTLAYnameTxt.setText(name);
    	if(pd != null) {
    		//EDITOUTLAYregularDate;
    		EDITOUTLAYregularPane.setVisible(true);
    	}else if(reason != null){
    		EDITOUTLAYirregularPane.setVisible(true);
    		EDITOUTLAYpurposeTxt.setText(reason);
    	}
    	
    	String choosed = option;
    	EDITOUTLAYtype.setValue(choosed);
    	
    	if(choosed.equalsIgnoreCase("Regular")) {
    		EDITOUTLAYirregularPane.setVisible(false);
    		EDITOUTLAYregularPane.setVisible(true);
    	}else {
    		EDITOUTLAYregularPane.setVisible(false);
    		EDITOUTLAYirregularPane.setVisible(true);
    	}
    }
    
    private void showSearchOutlay() throws IOException {
   	    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchOutlay.fxml"));
    	loader.setController(this);
    	Parent root = loader.load();
    	Scene e = new Scene(root);
   	   	popupStage.setScene(e);
       	popupStage.show();
  	    mainStage.hide();
  	    
  	    try {
  	    	SEARCHOUTLAYnameCol.setCellValueFactory(new PropertyValueFactory<Outlay,String>("name"));
  	  	    SEARCHOUTLAYamountCol.setCellValueFactory(new PropertyValueFactory<Outlay,Long>("amount"));
  	  	    SEARCHOUTLAYypeCol.setCellValueFactory(new PropertyValueFactory<Outlay,String>("type"));
  	    }catch(NullPointerException ex) {
  	    	
  	    }
    }
    
    private void showBalanceList() throws IOException {
    	FXMLLoader x = new FXMLLoader(getClass().getResource("BalanceList.fxml"));
    	x.setController(this);
    	Parent root = x.load();
    	changingPane.getChildren().setAll(root);
    	
    	BALANCELISTnameCol.setCellValueFactory(new PropertyValueFactory<Balance,String>("name"));
    	BALANCELISTdateCol.setCellValueFactory(new PropertyValueFactory<Balance,String>("stringDate"));
    	BALANCELISTincomesCol.setCellValueFactory(new PropertyValueFactory<Balance,Double>("income"));
    	BALANCELISToutCol.setCellValueFactory(new PropertyValueFactory<Balance,Double>("outlay"));
    	BALANCELISTloanCol.setCellValueFactory(new PropertyValueFactory<Balance,Double>("loan"));
    	BALANCELISTtotalCol.setCellValueFactory(new PropertyValueFactory<Balance,Double>("total"));
    	ArrayList<Balance> balanceList = localUser.getBalances();
    	if(balanceList != null) {
    		ObservableList<Balance> obList = FXCollections.observableArrayList(balanceList);
    		BALANCELISTtable.setItems(obList);
    	}
    }
}
