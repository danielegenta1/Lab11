package it.polito.tdp.bar;

import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController 
{
	Model model;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSimula(ActionEvent event) 
    {
    	model.simula();
    	txtResult.appendText("\nClienti soddisfatti: " + model.getSoddisfatti());
    	txtResult.appendText("\nClienti non soddisfatti: " + model.getInsoddisfatti());
    	
    }

	public void setModel(Model model) {
		this.model = model;
		
	}

}
