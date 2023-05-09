package src.ui_gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import src.ui_gui.ScreenFactory.Screen;
import src.ui_gui.screens.ColoramaGUI;

/**
 * Class represeting the settings menu of the GUI. Calls ColoramaGUI so the user may select the
 * color of the tiles.
 * 
 * @author Nolan Flinchum (25%), Thomas Kay (25%), Joseph Oladeji (25%), Levi Sweat (25%)
 * @version 5/9/2023
 */
public class SettingsGUI extends BorderPane{

	/**The button options of the menu**/
	Button b1, b2, whiteButton, blackButton;

	/**original color of the white squares **/
	static String whiteColor = "ffffff";

	/**original color of the black squares **/
	static String blackColor = "4b4b4b";
	
	/**field for changing the current screen */
	private ScreenChangeHandler screenChanger;

	/**
	 * Constructor for the settings menu. Creates the necessary labels and buttons for the menu to
	 * properly function.
	 */
	public SettingsGUI(){
		/**uses the same style as the mainMenu */
		this.getStyleClass().add("mainMenu");

		VBox top = new VBox(); //top of the border pane is a VBox
		VBox left = new VBox(); //left of the border pane is a VBox
		AnchorPane right = new AnchorPane(); //right of the borderpane is an AnchorPane

		//setting the top, right, and left of the borderpane to what's defined above
		this.setTop(top);
		this.setRight(right);
		this.setLeft(left);

		//TOP
		top.setAlignment(Pos.CENTER); //creates the settings label
		Label lbl = new Label("Settings");
		lbl.getStyleClass().add("labelB");
		lbl.setPadding(new Insets(40));
		top.getChildren().add(lbl);

		//sets the spacing and creates the labels for the left side
		left.setPadding(new Insets(0, 0, 0, 50)); 
		left.setSpacing(50);
		Label lb2 = new Label("Colors:");
		lb2.getStyleClass().add("labelB");
		Label lb3 = new Label("White Squares:   ");
		lb3.getStyleClass().add("labelB");
		Label lb4 = new Label("Black Squares:    ");
		lb4.getStyleClass().add("labelB");

		//buttons for displaying the color of the white & black squares
		whiteButton = new Button(); 
        whiteButton.getStyleClass().add("buttonWhiteSquares"); //color and style for white squares
		whiteButton.setStyle("-fx-background-color: #" + whiteColor);
		blackButton = new Button(); 
        blackButton.getStyleClass().add("buttonBlackSquares"); // color and style for black squares
		blackButton.setStyle("-fx-background-color: #" + blackColor);
		whiteButton.setOnAction(btnHandle); //when clicked, creates colorama dialog
		blackButton.setOnAction(btnHandle);

		//HBox for white colors
		HBox whiteHBox = new HBox();
		whiteHBox.setPadding(new Insets(0, 0, 0, 60));
		whiteHBox.getChildren().add(lb3);
		whiteHBox.getChildren().add(whiteButton);

		//HBox for black colors
		HBox blackHBox = new HBox();
		blackHBox.setPadding(new Insets(10, 0, 0, 60));
		blackHBox.getChildren().add(lb4);
		blackHBox.getChildren().add(blackButton);

		//Checkboxes and styling for undo/redo and showing the moves
		CheckBox cb1 = new CheckBox("Undo/Redo Moves");
		cb1.getStyleClass().add("labelB");
		cb1.setPadding(new Insets(100, 0, 40, 0));
		CheckBox cb2 = new CheckBox("Show Moves");
		cb2.getStyleClass().add("labelB");
		//cb2.setPadding(new Insets(40));

		//add the created elements to the left side of the borderpane
		left.getChildren().addAll(lb2, whiteHBox, blackHBox, cb1, cb2);

		//RIGHT
		b1 = new Button("Save"); //button for saving
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");
		b1.setOnAction(btnHandle); 

		b2 = new Button("Exit"); //button for exiting
		b2.getStyleClass().add("buttonStyleA");
		b2.getStyleClass().add("buttonSizeA");
		b2.setOnAction(btnHandle);

		VBox buttons = new VBox(); //add the buttons to a VBox
		buttons.getChildren().addAll(b1, b2);
		buttons.setSpacing(30);

		AnchorPane.setRightAnchor(buttons, 50.0); //add the buttons to the bottom right
		AnchorPane.setBottomAnchor(buttons, 50.0);
		right.getChildren().add(buttons);
	}
	
	/**
	 * Sets the screenChanger to whatever screen needs to be changed.
	 * 
	 * @param screen The incoming screen.
	 */
	public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}
	
	/**
	 * Handles changing screens when different buttons are pressed.
	 */
	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();

				//Exit button, returns to the previous screen
				if(o == b2){ 
					if(ScreenFactory.prevScreen == Screen.MATCH_SCREEN){
						ChessBoardGUI.paintSquares();
					}
					screenChanger.changeScreen(ScreenFactory.prevScreen);
				} 
				//Creates a new ColoramaGUI to change the color of the white squaress
				else if(o == whiteButton){
					ColoramaGUI colorama = new ColoramaGUI(whiteColor);
					colorama.showAndWait();
					whiteColor = colorama.getSelectedColor(); //updates button color
					whiteButton.setStyle("-fx-background-color: #" + whiteColor);
				}
				//Creates a new ColoramaGUI to change the color of the black squaress
				else if(o == blackButton){
					ColoramaGUI colorama = new ColoramaGUI(blackColor);
					colorama.showAndWait();
					blackColor = colorama.getSelectedColor(); //updates button color
					blackButton.setStyle("-fx-background-color: #" + blackColor);
				}
			}
		}
	};	

}