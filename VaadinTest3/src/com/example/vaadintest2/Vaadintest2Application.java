package com.example.vaadintest2;

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;


public class Vaadintest2Application extends Application {
	
	private SplitPanel horizontalSplit = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);

	DocEditor doc = new DocEditor();
	
	@Override
	public void init() {
		
		buildMainLayout();
		
	}
	
	private void buildMainLayout() {
		setMainWindow(new Window("Heliogate Catalogue Query"));
		VerticalLayout layout = new VerticalLayout();
		
		layout.addComponent(doc);
		getMainWindow().setContent(layout);
	}

}

