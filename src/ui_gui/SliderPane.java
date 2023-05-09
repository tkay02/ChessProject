package src.ui_gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Class that creates the sliders within the ColoramaGUI. 
 * 
 * @author Dr.Scott, edited by Nolan Flinchum (25%), Thomas Kay (25%), Joseph Oladeji (25%),
 * Levi Sweat (25%)
 * @version 5/9/2023
 */
public class SliderPane extends VBox{
    
	/**The label of the control**/
	private Label title;
	
	/**The title above the value text field**/
	private Label valueTitle;
	
	/**The value of the control**/
	private TextField value;
	
	/**The slider control**/
	private Slider slider;
	
	/**The minimum slider value**/
	private int min;
	
	/**The maximum slider value**/
	private int max;

	/**Listener used to determine when slider changes */
	private SliderListener listener;
		
	
	/**
	 * Constructs a slider.
	 * 
	 * @param titleText The title of the slider.
	 * @param initVal The initial value of the slider.
	 * @param min The minimum value on the slider.
	 * @param max The maximum value on  the slider
	 */
	public SliderPane(String titleText, int initVal, int min , int max, SliderListener listener){
		
		//initializes the listener based on the input
		this.listener = listener;

		//minimum and maximum values of the slider
		this.min = min;  
		this.max = max;
		
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10.0);
	
		//Create child components
		title = new Label(titleText);
		valueTitle = new Label("value");
		value = new TextField(initVal+"");
		slider = new Slider();
		slider.setOrientation(Orientation.VERTICAL);
		
		//Add them to the layout
		this.getChildren().add(title);
		this.getChildren().add(valueTitle);
		this.getChildren().add(value);
		this.getChildren().add(slider);
		
		//Apply CSS Styles
		title.getStyleClass().add("sliderTitle");
		valueTitle.getStyleClass().add("valueTitle");
		value.getStyleClass().add("textInput");
		
		//Set up slider
		slider.setMin(min);
		slider.setMax(max);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(max);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);
		slider.setValue(initVal);

		slider.valueProperty().addListener(valueChangeListener);
		value.textProperty().addListener(textValueChangeListener);
	}//end constructor

	
	/**
	 * Sets the value of the slider.
	 *
	 * @param newValue new value of the slider
	 */
	public void set(int newValue){
		value.setText(newValue+""); //update textbox
		slider.setValue(newValue); //update slider value
		listener.sliderChanged(this, newValue); //update the lisener to the new value
	
	}//end set
	

	/**
	 * Return the selected value on the slider
	 */
	public int getValue(){
		return (int) slider.getValue();
	}//end getValue
	
	/**Change listener for when the slider is changed**/
	ChangeListener<Number> valueChangeListener = new ChangeListener<Number>(){

		@Override
		/**
		 * Updates the value of the slider when changed.
		 * 
		 * @param observable Observer that something changed
		 * @param oldValue For checking if the value of the slider actually changed
		 * @param newValue New value of the slider
		 */
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if(!oldValue.equals(newValue))
			   set(newValue.intValue());
	
		}//end changed
		
	};//end valueChangeListener
	
	/**The textView needs another listener because its value type is String.**/
	ChangeListener<String> textValueChangeListener = new ChangeListener<String>(){

		@Override
		/**
		 * Updates the value of the slider when slider's text box is changed.
		 * 
		 * @param observable Observer that something changed
		 * @param oldValue For checking if the value of the slider actually changed
		 * @param newValue New value of the slider
		 */
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			// TODO Auto-generated method stub
			if(!oldValue.equals(newValue)){
				try{
				  int n = Integer.parseInt(newValue);
				  set(n);
				}
				catch(NumberFormatException ex){
					//do nothing.
				}
		   
			}//end if	
		
		}	
	};
}

