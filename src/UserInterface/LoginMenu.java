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
	private TextField clientIP;
	Controller ctrl;
	
	public LoginMenu(Controller controller) {
		this.ctrl = controller;
		login = new Label ("iChess login: ");
		username = new Label("Username: ");
		password = new Label("Password: ");
		loginConfirm = new Button("Login");

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
		
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		passwordField = new TextField();
		passwordField.setPromptText("Password");
		login.setFont(new Font("Arial", 40));
		username.setFont(new Font("Arial", 40));
		password.setFont(new Font("Arial", 40));
		loginConfirm.setPrefSize(150, 50);

		clientIP = new TextField();
		clientIP.setPromptText("Enter Client IP");


		clientToggle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			clientIP.setVisible(true);
		});

		serverToggle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			clientIP.setVisible(false);
		});
		
		loginConfirm.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			String clientIPString = clientToggle.isSelected() ? clientIP.getText() : " ";
			String username = usernameField.getText();
			String password = passwordField.getText();

			if(username.length() >= 1 && password.length() >= 1 && User.userExists(ctrl.getDatastore(), username)
				&& clientIPString.length() > 0){

				User user = User.findUser(ctrl.getDatastore(), username);
				System.out.println("Adding " + user.getUsername() + " to controller");
				user.setClient(clientToggle.isSelected() ? true : false);
				user.setIP(clientIPString);
				ctrl.setCurrUser(user);
				ctrl.setScene(ctrl.chessBoard);
				ctrl.startGame();
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
		add(clientIP, 1, 11);
	}
}
