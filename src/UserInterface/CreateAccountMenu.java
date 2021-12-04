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
	private ToggleButton clientToggle;
	private ToggleButton serverToggle;
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

		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(10));
		Label userMode = new Label("Are you acting as the server or client?");
		ToggleGroup group = new ToggleGroup();
		clientToggle = new ToggleButton("Client");
		serverToggle = new ToggleButton("Server");
		clientToggle.setToggleGroup(group);
		serverToggle.setToggleGroup(group);

		clientToggle.setSelected(true);
		hbox.getChildren().addAll(userMode, clientToggle, serverToggle);
		
		createAccountPrompt.setFont(new Font("Arial", 40));
		username.setFont(new Font("Arial", 40));
		password.setFont(new Font("Arial", 40));
		confirmPassword.setFont(new Font("Arial", 40));
		createAccountConfirmation.setPrefSize(150, 50);

		createAccountConfirmation.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			String uName = usernameField.getText();
			String p1 = passwordField.getText();
			String p2 = confirmPasswordField.getText();
			boolean isClient = clientToggle.isSelected() ? true : false;

			// if both passwords are the same, something was entered as a username, and username doesn't exist
			if(p1.equals(p2) && uName.length() >= 1 && !User.userExists(ctrl.getDatastore(), uName)){
				String write = uName + "," + p1 + "," + "0" + "," + "0";
				ctrl.getDatastore().write(write);
				ctrl.setCurrUser(new User(uName, 0, 0, isClient));
				System.out.println("User created\n uName: " + uName + " password: " + p1 + "wins: " + 0 + " losses: " +
						"isClient: " + isClient);
				ctrl.setScene(ctrl.chessBoard);
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
		add(hbox, 1, 9);
		
	}
}
