package com.test.fx;

import java.io.File;

import com.test.fx.browser.Browser;
import com.test.selenium.AutomationSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

public class MainController {
	@FXML VBox list_of_actions;
	@FXML static VBox log_box;
	@FXML AnchorPane web_view;
	@FXML TextField URLInput;
	@FXML ComboBox<String> browser;
	
	@FXML
	public void initialize() {
		browser.setItems(FXCollections.observableArrayList("Chrome", "Mozilla"));
	}
	ObservableList<String> li = FXCollections.observableArrayList("Click", "Navigate", 
			"Maximise Window", "Back", "getElement_Text");
	
	//private BrowserManager manager = new BrowserManager();
	private WebView wb = null;
	@SuppressWarnings("unchecked")
	public void add_action(ActionEvent event) {
		HBox container_box = new HBox();
		ComboBox<String> cb = new ComboBox<String>();
		container_box.setAlignment(Pos.CENTER);
		container_box.getChildren().add(cb);
		
		cb.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener() {
					public void changed(ObservableValue arg0, Object arg1, Object arg2) {
						if (container_box.getChildren().size() > 1) container_box.getChildren().remove(1);
						
	
							switch (arg2.toString()) {
							case "Navigate":
								TextField xpath_input_navigate = new TextField();
								xpath_input_navigate.setPromptText("url");
								container_box.getChildren().add(xpath_input_navigate);
							break;
							case "Click":
								TextField xpath_input_click = new TextField();
								xpath_input_click.setPromptText("xpath");
								container_box.getChildren().add(xpath_input_click);
							break;
							case "isElementPresent":
								TextField xpath_input_present = new TextField();
								xpath_input_present.setPromptText("xpath");
								container_box.getChildren().add(xpath_input_present);
							break;
							case "waitForElement":
								TextField xpath_input_wait = new TextField();
								xpath_input_wait.setPromptText("xpath");
								container_box.getChildren().add(xpath_input_wait);
							break;
							case "Maximise Window":
								
							break;
							case "getElement_Text":
								TextField xpath_input_text = new TextField();
								xpath_input_text.setPromptText("xpath");
								container_box.getChildren().add(xpath_input_text);
							break;
							
							case "Back":
								
							break;
								
						}
						}
						
					
				}
		);
		cb.getItems().addAll(li);
		list_of_actions.getChildren().add(container_box);
	}
	public void exit(ActionEvent event) {
		System.exit(0);
	}
	public void display_browser(ActionEvent event) {
		wb = Browser.browse(URLInput.getText());
		URLInput.setText(wb.getEngine().getLocation());
		web_view.getChildren().add(wb);
		
		
		
	}
	class Url_Change implements ChangeListener{
		@Override
		public void changed(ObservableValue arg0, Object arg1, Object arg2) {
			URLInput.setText(wb.getEngine().getLocation());
			
		}
	}
	public void open(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv Files", "*.csv"));
		fileChooser.setTitle("csv");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );
        File selectedFile = fileChooser.showOpenDialog(null);
		//manager.handler.load_csv(selectedFile);
	}
	public void save(ActionEvent event) {
		
	}
	public void launch(ActionEvent event) {
		System.out.println("should not be null - " + browser.getSelectionModel().getSelectedItem());
		Thread t1 = new Thread(new AutomationSession(list_of_actions, browser.getSelectionModel().getSelectedItem()));
		t1.start();
		
		
	}
	public static void log(String log_message) {
		Label lbl = new Label();
		lbl.setText(log_message);
		//log_box.getChildren().add(lbl);
	}
	
}
