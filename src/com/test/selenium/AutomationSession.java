package com.test.selenium;

import java.util.LinkedList;
import com.test.utils.CSV_Handler;
import javafx.scene.layout.VBox;

public class AutomationSession implements Runnable {
	private BrowserManager manager;
	VBox vb_data;
	public AutomationSession(VBox vb_data, String browser) {
		this.vb_data = vb_data;
		this.manager = new BrowserManager(browser);
	}
	@Override
	public void run() {
		LinkedList<Action> listOfActions = CSV_Handler.extract_data_from_vbox(vb_data);
		for (Action action : listOfActions) {
			action.run(manager);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.QuitSession();
	}
}

