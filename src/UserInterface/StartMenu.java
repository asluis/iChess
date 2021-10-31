package UserInterface;

import javafx.scene.layout.*;
import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class StartMenu extends VBox {
	private Button login;
	private Button signup;
	private Label iChess;
	private Controller ctrl;
	
	public StartMenu(Controller controller) {
		this.ctrl = controller;
		iChess = new Label("iChess");
		iChess.setFont(new Font("Arial", 70));
		
		login = new Button("Login");
		login.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> ctrl.setScene(new LoginMenu()));
		
		signup = new Button("Create Account");
		
		signup.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> ctrl.setScene(new CreateAccountMenu()));
		
		login.setPrefSize(300, 100);
		signup.setPrefSize(300, 100);
		
		setSpacing(50);
		setAlignment(Pos.CENTER);
		setPrefWidth(400);
		
		
		getChildren().add(iChess);
		getChildren().add(login);
		getChildren().add(signup);		
	}	
}