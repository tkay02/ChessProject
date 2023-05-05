package src.ui_gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import src.interfaces.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MatchGUI extends BorderPane {

        
    private ScreenChangeHandler screenChanger;

    private Button b1, b2, b3, b4, b5, b6, b7;

    public MatchGUI(){
		HBox top = new HBox(50);
		HBox bottom = new HBox();
		Pane left = new VBox();
		Pane right = new VBox();
		Pane center = new Pane();
		this.setTop(top);
		this.setBottom(bottom);
		this.setRight(right);
		this.setLeft(left);
		this.setCenter(center);
		this.getStyleClass().add("backgroundA");

		this.setPadding(new Insets(5));
		BorderPane.setMargin(top, new Insets(5));
		BorderPane.setMargin(bottom, new Insets(5));
		BorderPane.setMargin(left, new Insets(5));
		BorderPane.setMargin(right, new Insets(5));
		BorderPane.setMargin(center, new Insets(5));


		//// TOP ////
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(20.0);
		top.getStyleClass().add("backgroundB");

		b1 = new Button("Load");
		b1.getStyleClass().add("buttonStyleA");
		b1.getStyleClass().add("buttonSizeA");;
		//b1.setOnAction(btnHandle);

		b2 = new Button("Save");
		b2.getStyleClass().add("buttonStyleA");
		b2.getStyleClass().add("buttonSizeA");
		//b2.setOnAction(btnHandle);

		b3 = new Button("Undo");
		b3.getStyleClass().add("buttonStyleA");
		b3.getStyleClass().add("buttonSizeA");
		//b3.setOnAction(btnHandle);

		b4 = new Button("Redo");
		b4.getStyleClass().add("buttonStyleA");
		b4.getStyleClass().add("buttonSizeA");
		//b4.setOnAction(btnHandle);

		b5 = new Button("Settings");
		b5.getStyleClass().add("buttonStyleA");
		b5.getStyleClass().add("buttonSizeA");
		//b5.setOnAction(btnHandle);

		top.getChildren().addAll(b1, b2, b3, b4, b5);

		//// BOTTOM ////
		bottom.setAlignment(Pos.CENTER);
		bottom.getStyleClass().add("backgroundB");

		//// LEFT ////
		left.getStyleClass().add("backgroundB");

		b6 = new Button("Show Moves");
		b6.getStyleClass().add("buttonStyleA");
		b6.getStyleClass().add("buttonSizeA");
		//b6.setOnAction(btnHandle);
		left.getChildren().add(b6);

		//// RIGHT ////
		right.getStyleClass().add("backgroundB");

		b7 = new Button("Exit to Main Menu");
		b7.getStyleClass().add("buttonStyleA");
		b7.getStyleClass().add("buttonSizeA");
		b7.setOnAction(btnHandle);
		right.getChildren().add(b7);

		//// CENTER ////
		center.getStyleClass().add("backgroundB");

    }

    public void setScreenChangeHandler(ScreenChangeHandler screen){
		this.screenChanger = screen;
	}

	EventHandler<ActionEvent> btnHandle = new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			if(screenChanger != null){
				Object o = event.getSource();

				if(o == b7){
				   screenChanger.changeScreen(ScreenFactory.Screen.MAIN_SCREEN);
				}
		
			}
		}
	};	
}

