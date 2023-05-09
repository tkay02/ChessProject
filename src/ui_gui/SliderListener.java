package src.ui_gui;
/**
 * This class is used to implement an observer pattern within ColoramaGUI
 * 
 * @author Nolan Flinchum, Thomas Kay, Joseph Oladeji, Levi Sweat
 * @version 5/9/2023
 */
public interface SliderListener {
    //updates background color in Colorama whenever a SliderPane class is changed
    public void sliderChanged(SliderPane s, int value);
}
