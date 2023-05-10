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

	/**Boolean determining if moves need to be shown, false by default */
	public static boolean showMoves = false;

	/**Boolean determining if undo/redo is avaialble, false by default */
	public static boolean undoRedo = false;

	/**Original value of the whiteColor, updated if settings is saved*/
	private String saveWhiteColor;

	/**Original value of the blackColor, updated if settings is saved*/
	private String saveBlackColor;

	/**Original value of the showMoves, updated if settings is saved*/
	private boolean saveShowMoves;

	/**Original value of the undoRedo, updated if settings is saved*/
	private boolean saveUndoRedo = false;

	/**Checkboxes for showmoves and undoredo */
	CheckBox cb2, cb1;

	/**
	 * Constructor for the settings menu. Creates the necessary labels and buttons for the menu to
	 * properly function.
	 */
	public SettingsGUI(){
		/**uses the same style as the mainMenu */
		this.getStyleClass().add("mainMenu");

		//set the original values to be remember if settings is changed but not saved
		this.saveWhiteColor = whiteColor;
		this.saveBlackColor = blackColor;
		this.saveShowMoves = showMoves;
		this.saveUndoRedo = undoRedo;

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
		cb1 = new CheckBox("Undo/Redo Moves");
		cb1.getStyleClass().add("labelB");
		cb1.setPadding(new Insets(100, 0, 40, 0));
		cb2 = new CheckBox("Show Moves");
		cb2.getStyleClass().add("labelB");
		//listener for when the ShowMoves label is checked
		cb2.selectedProperty().addListener((obs, oldVal, newVal) -> {
        	this.showMoves = newVal; //newVal is true when checked, false when not checked
   		});
		cb1.selectedProperty().addListener((obs, oldVal, newVal) -> {
        	this.undoRedo = newVal; //newVal is true when checked, false when not checked
   		});
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
				if(o == b1){
					//saved versions are only updated if the setting is saved
					saveWhiteColor = whiteColor;
					saveBlackColor = blackColor;
					saveShowMoves = showMoves;
					saveUndoRedo = undoRedo;

				}
				//Exit button, returns to the previous screen
				else if(o == b2){ 
					//change these versions to the versions that only are updated if saved
					whiteColor = saveWhiteColor;
					blackColor = saveBlackColor;
					showMoves = saveShowMoves;
					undoRedo = saveUndoRedo;
					whiteButton.setStyle("-fx-background-color: #" + whiteColor);
					blackButton.setStyle("-fx-background-color: #" + blackColor);
					cb2.setSelected(showMoves);
					cb1.setSelected(undoRedo);

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