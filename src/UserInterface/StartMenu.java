package UserInterface;

import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class StartMenu extends VBox {
	private Button login;
	private Button signup;
	private Label iChess;
	
	public StartMenu() {
		iChess = new Label("iChess");
		iChess.setFont(new Font("Arial", 70));
		
		login = new Button("Login");
		signup = new Button("Create Account");
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