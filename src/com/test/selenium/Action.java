package com.test.selenium;

public class Action {
	private String param1;
	private String param2;
	private String param3;
	private String action;
	
	
	public Action(String action, String param1, String param2, String param3) {
		super();
		this.action = action;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
	}
	
	
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}


	@Override
	public String toString() {
		return "Action [param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + ", action=" + action + "]";
	}
	
	public void run(BrowserManager manager) {
		switch (action) {
		case "Click":
			manager.Click(param1);
		break;
		case "Navigate":
			if (param1.length() != 0) {
				manager.Navigate(param1);
			} else {
				System.out.println("Invalid url to navigate to");
			}
			
		break;
		case "Maximise Window":
			manager.MaximiseWindow();
		break;
		case "Back":
			manager.Back();
		break;
		case "getElement_Text":
			System.out.println(manager.getElement_String(param2));
		break;
	}
	}
	
	
}
