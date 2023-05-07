package src.ui_gui.screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import src.ui_gui.SliderPane;

public class ColoramaGUI extends Dialog<String> implements EventHandler<ActionEvent>{

	/**Confirms a color choice**/
	private Button ok;
	
    /**The selected color as a hex value (RGB)**/
	String selectedColor;

	/**Cancels a color choice**/
	private Button cancel;
	
	/**The area on to which the color is shown**/
	StackPane color;

    /**String representing color of square */
    String strColor;
	
	/**The color text label**/
	Label hexColor;
	
	/**Maximum color intensity**/
	private final int MAX_INTEN = 255;
	
	/**Minimum color intensity**/
	private final int MIN_INTEN = 0;

	private SliderPane red, green, blue;


	/**The singleton instance of this class**/
	private static ColoramaGUI instance;

	/**Create a singleton instance of a ColorChooser**/
	public static ColoramaGUI getInstance(){
		if (instance == null) instance = new ColoramaGUI("#000000");
		return instance;
	}//end getInstance

    public ColoramaGUI(String strColor){
        super();
        this.setTitle("Change color of square");
        this.strColor = strColor;
        buildUI();        
        setPropertyBindings();
        setResultConverter();
    }

    private void setResultConverter() {
    }

    private void setPropertyBindings() {
    }

    private void buildUI() {
        Pane pane = makeBorderPane();
        getDialogPane().setContent(pane);
    }

    private Pane makeBorderPane() {
        GridPane grid = new GridPane();
		grid.setVgap(20);
        grid.setGridLinesVisible(true);

		//Grid contstraints for row
		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(40);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(50);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(10);
		
		//grid constraints for columns
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(50);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		
		//Appy constraints
		grid.getRowConstraints().addAll(row0, row1,row2);
		grid.getColumnConstraints().addAll(col0,col1);

        
		//Top panel for color
		color = new StackPane();
		color.getStyleClass().add("color");
		hexColor = new Label();
		hexColor.getStyleClass().add("color_text");
		color.getChildren().add(hexColor);
		setBackround(0, 0, 0);//set to black
		
		
		//Create Panel for sliders with centered layout
		HBox  sliders = new HBox();
		sliders.setSpacing(20.0);
		sliders.setAlignment(Pos.TOP_CENTER);

		//Construct 3 sliders for RGB 2nd param determines value
		red = new SliderPane("Red",55, MIN_INTEN, MAX_INTEN);
		green = new SliderPane("Green",13, MIN_INTEN, MAX_INTEN);
		blue = new SliderPane("Blue",23, MIN_INTEN, MAX_INTEN);

		sliders.getChildren().add(red);
		sliders.getChildren().add(green);
		sliders.getChildren().add(blue);

		ok = new Button("Select");

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		ok.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		//cancel.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		
		sliders.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		color.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		
		//col, row, colspan, rowspan
		grid.add(color, 0,0,2,1);
		grid.add(sliders, 0, 1,2,1);
		grid.add(ok, 0,2,2,1);
		
		ok.setOnAction(this);
		//cancel.setOnAction(this);


        // BorderPane bp = new BorderPane();

        // //Top part is a color button
        // Button button = new Button("");
        // HBox hbTop = new HBox();
        // hbTop.getChildren().add(button);
        // hbTop.setAlignment(Pos.TOP_CENTER);
        // hbTop.getStyleClass().add("HBoxDialogue");
        // bp.setTop(hbTop);

        // //Middle part is a HBox
        // HBox hb = new HBox();
        // bp.setCenter(hb); //set centeer of the border pane to an HBox

        // //makes the VBoxes
        // VBox vb1 = createVBox("Red");
        // VBox vb2 = createVBox("Green");
        // VBox vb3 = createVBox("Blue");

        // //adds vbox to surrounding hbox
        // hb.getChildren().add(vb1);
        // hb.getChildren().add(vb2);
        // hb.getChildren().add(vb3);


        // GridPane grid = new GridPane();
        // grid.setGridLinesVisible(true);
        // Button select = new Button("Select");
        // Button exit = new Button("Exit");
        // grid.add(select, 0, 0, 1, 1);
        // grid.add(exit, 1, 0, 1, 1);
        // ColumnConstraints c0 = new ColumnConstraints();
        // c0.setPercentWidth(50);
        // RowConstraints r0 = new RowConstraints();
        // r0.setPercentHeight(100);
        // bp.setBottom(grid);




        
        return grid;
    }

    public VBox createVBox(String label){
        VBox vb = new VBox();
        Label l1 = new Label(label);
        Slider s1 = new Slider();
        
        s1.setOrientation(Orientation.VERTICAL);
        s1.setMin(0);
        s1.setMax(255);
        s1.setShowTickLabels(true);
        s1.setShowTickMarks(true);
		s1.setMajorTickUnit(255);
		s1.setMinorTickCount(5);
		s1.setBlockIncrement(10);

        vb.getChildren().add(l1);
        vb.getChildren().add(s1);        
        return vb;
    }

	/**
	 * Set the background color of the  chooser
	 * @param r The red intensity 0..255
	 * @param g The blue intensity 0..255
	 * @param b The green intensity 0..255
	 */
	public String setBackround(int r, int g, int b){
		//Avoid invalid values
		if(r > 255 || g > 255 || b >255) return null;
		
		String hr = Integer.toHexString(r);
		String hg = Integer.toHexString(g);
		String hb = Integer.toHexString(b);
		
		if(r+g+b/3 > 127)
			hexColor.setTextFill(Color.BLACK);
		else
			hexColor.setTextFill(Color.WHITE);
		
		//Add preceeding 0 if only 1 char
		if(hr.length() == 1)
			hr = 0 + hr;	
		if(hg.length() == 1)
			hg = 0 + hg;	
		if(hb.length() == 1)
			hb = 0 + hb;
		
		selectedColor = hr+hg+hb;
		
		color.setStyle("-fx-background-color: #" + selectedColor);
		
		hexColor.setText("#" + selectedColor);
        
        return selectedColor;
		//System.out.println(col + " : " + hr + ", " + hg + ", " + hb);
	}//end setBackground

    @Override
	public void handle(ActionEvent event) {
		
		if(event.getSource() == ok){
            setBackround(red.getValue(), green.getValue(), blue.getValue());
		}
		else if(event.getSource() == cancel){
            ColoramaGUI.this.close();
        }
	}//end handle

}
