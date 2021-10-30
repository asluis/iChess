package UserInterface;

import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
	
	public CreateAccountMenu() {
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
