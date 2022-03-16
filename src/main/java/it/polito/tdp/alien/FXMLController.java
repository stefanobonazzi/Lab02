package it.polito.tdp.alien;

import java.util.*;

import it.polito.tdp.alien.model.Dizionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Dizionario model;
	
    @FXML
    private TextField txtParole;

    @FXML
    private TextArea txtResult;

    @FXML
    void doClear(ActionEvent event) {
    	txtParole.clear();
    	txtResult.clear();;
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	String s[] = txtParole.getText().toLowerCase().split(" ");
    	boolean wildcard = false;
    	
    	if(s.length > 2)
    		return;
    	
    	if(s.length == 2) {
    		if(!s[0].matches("[a-zA-Z]+") || !s[1].matches("[a-zA-Z]+")) {
    			txtResult.setText("Non matchato");
    			return;
    		}
 
    		model.addParola(s[0], s[1]);
    		
    	} else {
    		if(s[0].contains("?"))
    			wildcard = true;
    		
    		List<String> l = null;
    		
    		try {
    			l = model.translate(s[0], wildcard);
    		} catch (NullPointerException npe) {
    			txtResult.setText("Nessun riferimento nel dizionario.\n");
    			return;
    		}
    		
    		for(String ss: l)
    			txtResult.appendText(ss + "\n");	
    	}	
    }

	public void setModel(Dizionario model) {
		this.model = model;
	}

}
