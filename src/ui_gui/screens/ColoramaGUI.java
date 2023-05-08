package src.ui_gui.screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
import src.ui_gui.SliderListener;

/**
 * This class provides an interface where users can change the color of their chess pieces.
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/8/2023
 */
public class ColoramaGUI extends Dialog<String> implements EventHandler<ActionEvent>, SliderListener{

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

	/**The slider panes**/
	private SliderPane red, green, blue;

	/**The singleton instance of this class**/
	private static ColoramaGUI instance;

	/**Create a singleton instance of a ColorChooser**/
	public static ColoramaGUI getInstance(){
		if (instance == null) instance = new ColoramaGUI("000000");
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
		setBackground(Integer.parseInt(strColor.substring(0, 2), 16), Integer.parseInt(strColor.substring(2, 4), 16), Integer.parseInt(strColor.substring(4, 6), 16));//set to black
		
		
		//Create Panel for sliders with centered layout
		HBox  sliders = new HBox();
		sliders.setSpacing(20.0);
		sliders.setAlignment(Pos.TOP_CENTER);

		//Construct 3 sliders for RGB 2nd param determines value
		red = new SliderPane("Red", Integer.parseInt(strColor.substring(0, 2), 16), MIN_INTEN, MAX_INTEN, this);
		green = new SliderPane("Green", Integer.parseInt(strColor.substring(2, 4), 16), MIN_INTEN, MAX_INTEN, this);
		blue = new SliderPane("Blue", Integer.parseInt(strColor.substring(4, 6), 16), MIN_INTEN, MAX_INTEN, this);

		sliders.getChildren().add(red);
		sliders.getChildren().add(green);
		sliders.getChildren().add(blue);

		ok = new Button("Select");

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		ok.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		
		sliders.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		color.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		
		//col, row, colspan, rowspan
		grid.add(color, 0,0,2,1);
		grid.add(sliders, 0, 1,2,1);
		grid.add(ok, 0,2,2,1);
		
		ok.setOnAction(this);


        color.setStyle("-fx-background-color: #" +  this.strColor);
        return grid;
    }

	public void sliderChanged(SliderPane s, int value){
		//check which slider its coming from, update the respective portion of the background color
		//rgb
		this.setBackground(red.getValue(), green.getValue(), blue.getValue());
	}

	public String intToHex(int value){
		String result = "";
		return result;
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

	public void setPaneColor(String newColor){
		this.color.setStyle("-fx-background-color: #" + newColor);
	}

	/**
	 * Set the background color of the  chooser
	 * @param r The red intensity 0..255
	 * @param g The blue intensity 0..255
	 * @param b The green intensity 0..255
	 */
	public void setBackground(int r, int g, int b){
		//Avoid invalid values
		if(r > 255 || g > 255 || b >255) return;
		
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
		//this.strColor = selectedColor;
		
		color.setStyle("-fx-background-color: #" + selectedColor);
		
		hexColor.setText("#" + selectedColor);
        
	}//end setBackground

	public String getSelectedColor(){
		return this.selectedColor;
	}

	public void setUndoRedo(){
		//TODOOOOOOOOOOOOOOOOOOOO
	}

    @Override
	public void handle(ActionEvent event) {
		
		if(event.getSource() == ok){
            setBackground(red.getValue(), green.getValue(), blue.getValue());
			ColoramaGUI.this.close();
		}
		else if(event.getSource() == cancel){
            ColoramaGUI.this.close();
        }
	}//end handle

}
