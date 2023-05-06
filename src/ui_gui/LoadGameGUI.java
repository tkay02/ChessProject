package src.ui_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.util.Callback;

public class LoadGameGUI extends Dialog<String> {

    private String load = "";

    public LoadGameGUI(String load) {
        super();
        this.setTitle("Load Game");
        buildUI();
        setResultConverter();
    }

    private void buildUI() {
		

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button button = (Button) getDialogPane().lookupButton(ButtonType.OK);
        button.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validateDialog()) {
                    event.consume();
                }
            }

            private boolean validateDialog() {
                if (load.isEmpty()) {
                    return false;
                }
                return true;
            }
        });
        getDialogPane().expandableContentProperty().set(new Button("Choose a file to load"));
        getDialogPane().setExpanded(true);
		
    }


    private void setResultConverter() {
        Callback<ButtonType, String> loadGameConverter = new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType guiBtn) {
                if (guiBtn == ButtonType.OK) {
                    return load;
                } else {
                    return null;
                }
            }
        };
        setResultConverter(loadGameConverter);
    }

    
}