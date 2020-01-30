package com.test.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import com.opencsv.CSVWriter;
import com.test.selenium.Action;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CSV_Handler {
	private File file;
	public CSV_Handler(String file_name){
		this.file = new File(file_name); 
	}
	public void load_csv(File file) {
		this.file = file;
	}
	public boolean save_file(LinkedList<String[]> data) {
		try { 
	        FileWriter outputfile = new FileWriter(file); 
	        CSVWriter writer = new CSVWriter(outputfile); 
	        for (int i = 0; i < data.size(); i++) {
	        	String[] new_array = data.get(i);
	        	writer.writeNext(new_array); 
	        }
	        writer.close();
	        return true;
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    }
		return false;
	}
	public static LinkedList<Action> extract_data_from_vbox(VBox vbox) {
		LinkedList<String[]> hm = new LinkedList<String[]>();
		ObservableList<Node> node_list = vbox.getChildren();
		
		LinkedList<Action> actionList = new LinkedList<Action>();
		
		for (int i = 0; i < node_list.size(); i++) {
			String current_key = "";
			String[] current_array = null;
			Node vbox_element = node_list.get(i);
			if (vbox_element instanceof HBox) {
				System.out.println("HBOX detected");
				ObservableList<Node> element_in_hbox_list = ((HBox) vbox_element).getChildren();
				current_array = new String[element_in_hbox_list.size()];
				
				ComboBox<String> actionElement = (ComboBox<String>) element_in_hbox_list.get(0);
				String action = actionElement.getSelectionModel().getSelectedItem();
				String param1 = null;
				String param2 = null;
				String param3 = null;
				
				for (int j = 1; j < element_in_hbox_list.size(); j++) {
					Node current_ele = element_in_hbox_list.get(j);
					
					if (current_ele instanceof ComboBox) {
						ComboBox<String> current_cb = (ComboBox<String>) current_ele;
						if (j == 1) {
							param1 = current_cb.getSelectionModel().getSelectedItem();
						} else if (j == 2){
							param2 = current_cb.getSelectionModel().getSelectedItem();
						} else if (j == 3) {
							param3 = current_cb.getSelectionModel().getSelectedItem();
						}
					} else if (current_ele instanceof TextField) {
						if (j == 1) {
							param1 = ((TextField) current_ele).getText();
						} else if (j == 2){
							param2 = ((TextField) current_ele).getText();
						} else if (j == 3) {
							param3 = ((TextField) current_ele).getText();
						}
	
					}
					
				}
				
				Action a = new Action(action, param1, param2, param3);
				actionList.add(a);
			} else {
				System.out.println("HBOX not detected");
			}
		}
		return actionList;
	}
//	public void read_and_execute_csv() {
//		try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
//	        String[] nextRecord; 
//		    while ((nextRecord = csvReader.readNext()) != null) {
//				LinkedList<String> ele_list = new LinkedList<String>();
//				for (String cell : nextRecord) {
//					if (!cell.isEmpty()) {
//						ele_list.add(cell);
//					}
//				}
//				execute_word(ele_list);
//			}
//		} catch (IOException e) {
//		    e.printStackTrace();
//		} 
//	}
	
}
