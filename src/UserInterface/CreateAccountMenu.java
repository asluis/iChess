package UserInterface;

import application.User;
import javafx.scene.layout.*;
import controller.Controller;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class CreateAccountMenu extends GridPane {
	private Label createAccountPrompt;
	private Label username;
	private Label password;
	private Label confirmPassword;
	private Button createAccountConfirmation;
	private TextField usernameField;
	private TextField passwordField;
	private TextField confirmPasswordField;
	Controller ctrl;
	
	public CreateAccountMenu(Controller controller) {
		this.ctrl = controller;
		createAccountPrompt = new Label("Create account: ");
		username = new Label("Username: ");
		password = new Label("Password: ");
		confirmPassword = new Label("Confirm Password: ");
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		passwordField = new TextField();
		passwordField.setPromptText("Password");
		confirmPasswordField = new TextField();
		confirmPasswordField.setPromptText("Re-enter password");
		createAccountConfirmation = new Button("Create Account");
		
		createAccountPrompt.setFont(new Font("Arial", 40));
		username.setFont(new Font("Arial", 40));
		password.setFont(new Font("Arial", 40));
		confirmPassword.setFont(new Font("Arial", 40));
		createAccountConfirmation.setPrefSize(150, 50);
		
		createAccountConfirmation.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			boolean isValid = false;
			String uName = usernameField.getText();
			String p1 = passwordField.getText();
			String p2 = confirmPasswordField.getText();


			// if both passwords are the same, something was entered as a username, and username doesn't exist
			if(p1.equals(p2) && uName.length() >= 1 && !User.userExists(ctrl.getDatastore(), uName)){
				String write = uName + "," + p1 + "," + "0" + "," + "0";
				ctrl.getDatastore().write(write);
				ctrl.setScene(new ChessBoardView(controller));
			}else{
				System.out.println("BAD"); // TODO: Create UI for notifying if error
			}
		});
		
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(10));
		
		add(createAccountPrompt, 0, 0);
		add(username, 0, 2);
		add(password,0, 3);
		add(confirmPassword, 0, 4);
		add(usernameField, 1, 2);
		add(passwordField, 1, 3);
		add(confirmPasswordField, 1, 4);
		add(createAccountConfirmation, 1, 6);
		
	}
}
