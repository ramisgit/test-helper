package com.test.fx.browser;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class Browser {
	private static WebView webView = new WebView();
	private static WebEngine webEngine = webView.getEngine();
	public static WebView browse(String url) {
		webEngine.load(url);
		JSObject window = (JSObject) webView.getEngine().executeScript("window");
		window.setMember("jsCallBack", new JSCallBack());
		webView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        System.out.println("mouse click detected! " + mouseEvent.getSource());

		        System.out.println(mouseEvent.getX() + ", " + mouseEvent.getY());
		        System.out.println(mouseEvent.getSource().toString());
		        webEngine.executeScript("function changeColor(newColor) {"
		        		+ "var elem = document.elementFromPoint(" + (int)mouseEvent.getX() + "," +  (int)mouseEvent.getY() + ");"
		        		+ "elem.style.color = newColor; }"
		        );
		        webEngine.executeScript("changeColor('pink');");
		    
		    }
		});
		
		return webView;
	}
	//on click
}
