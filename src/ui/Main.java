package ui;


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
		
	AssistantGUI gui;
	
	public Main(){
		gui = new AssistantGUI();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		gui.start();
	}
}
