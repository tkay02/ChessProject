package src.ui_gui;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.controller.Chess;
import src.enums.ChessPieceType;
import src.enums.File;
import src.enums.GameColor;
import src.enums.Rank;
import src.model.Board;
import src.model.Piece;
import src.model.Position;

public class SmallTester extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//RulesGUI rules = new RulesGUI();
			//Pane root = rules.getRoot();
			ChessBoardGUI game = new ChessBoardGUI(new Chess());
			Scene scene = new Scene(game, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
