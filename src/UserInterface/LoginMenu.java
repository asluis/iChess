package UserInterface;

import application.User;
import javafx.scene.layout.*;
import controller.Controller;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class LoginMenu extends GridPane {
	private Label login;
	private Label username;
	private Label password;
	private Button loginConfirm;
	private TextField usernameField;
	private TextField passwordField;
	private ToggleButton clientToggle;
	private ToggleButton serverToggle;
	Controller ctrl;
	
	public LoginMenu(Controller controller) {
		this.ctrl = controller;
		login = new Label ("iChess login: ");
		username = new Label("Username: ");
		password = new Label("Password: ");
		loginConfirm = new Button("Login");

		HBox hbox = new HBox();
		Label userMode = new Label("Are you acting as the server or client?");
		ToggleGroup group = new ToggleGroup();
		clientToggle = new ToggleButton("Client");
		serverToggle = new ToggleButton("Server");
		clientToggle.setToggleGroup(group);
		serverToggle.setToggleGroup(group);

		clientToggle.setSelected(true);
		hbox.getChildren().addAll(userMode, clientToggle, serverToggle);

		
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		passwordField = new TextField();
		passwordField.setPromptText("Password");
		login.setFont(new Font("Arial", 40));
		username.setFont(new Font("Arial", 40));
		password.setFont(new Font("Arial", 40));
		loginConfirm.setPrefSize(150, 50);
		
		loginConfirm.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

			String username = usernameField.getText();
			String password = passwordField.getText();

			if(username.length() >= 1 && password.length() >= 1 && User.userExists(ctrl.getDatastore(), username)){

				User user = User.findUser(ctrl.getDatastore(), username);
				System.out.println("Adding " + user.getUsername() + " to controller");
				user.setClient(clientToggle.isSelected() ? true : false);
				ctrl.setCurrUser(user);
				ctrl.setScene(ctrl.chessBoard);
			}else{
				System.out.println("Bad input for login");
			}
		});
		
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
		add(hbox, 1, 7);
	}
}
