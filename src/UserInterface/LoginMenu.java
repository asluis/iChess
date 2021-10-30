package UserInterface;

import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class LoginMenu extends GridPane {
	private Label login;
	private Label username;
	private Label password;
	private Button loginConfirm;
	private TextField usernameField;
	private TextField passwordField;
	
	public LoginMenu() {
		login = new Label ("iChess login: ");
		username = new Label("Username: ");
		password = new Label("Password: ");
		loginConfirm = new Button("Login");
		
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		passwordField = new TextField();
		passwordField.setPromptText("Password");
		login.setFont(new Font("Arial", 40));
		username.setFont(new Font("Arial", 40));
		password.setFont(new Font("Arial", 40));
		loginConfirm.setPrefSize(150, 50);
		
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(10));
		
		add(login, 0, 0);
		add(username, 0, 2);
		add(password, 0, 3);
		add(usernameField, 2, 2);
		add(passwordField, 2, 3);
		add(loginConfirm, 1, 6);
	}
}
