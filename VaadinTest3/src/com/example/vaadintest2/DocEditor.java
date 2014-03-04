/*This is Andrew's Branch*/

package com.example.vaadintest2;


import java.util.Random;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DocEditor extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_3;
	@AutoGenerated
	private Button button_6;
	@AutoGenerated
	private Button button_5;
	@AutoGenerated
	private ComboBox comboBox_10;
	@AutoGenerated
	private ComboBox comboBox_9;
	@AutoGenerated
	private ComboBox comboBox_8;
	@AutoGenerated
	private ComboBox comboBox_1;
	@AutoGenerated
	private ComboBox comboBox_7;
	@AutoGenerated
	private AbsoluteLayout top_sean_area;
	@AutoGenerated
	private Label dateValidStateLabel;
	@AutoGenerated
	private Label APMReadyStateLabel;
	@AutoGenerated
	private Button button_3;
	@AutoGenerated
	private ComboBox comboBox_6;
	@AutoGenerated
	private ComboBox comboBox_3;
	@AutoGenerated
	private ComboBox comboBox_2;
	@AutoGenerated
	private Button APMReadyTest;
	@AutoGenerated
	private PopupDateField dateInputTwo;
	@AutoGenerated
	private PopupDateField dateInputOne;
	@AutoGenerated
	private Button setTimeButton;
	@AutoGenerated
	private AbsoluteLayout middle_tom_area;
	@AutoGenerated
	private Button button_4;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_1;
	@AutoGenerated
	private TextField textField_10;
	@AutoGenerated
	private TextField textField_9;
	@AutoGenerated
	private TextField textField_8;
	@AutoGenerated
	private TextField textField_5;
	@AutoGenerated
	private TextField textField_7;
	@AutoGenerated
	private TextField textField_6;
	@AutoGenerated
	private TextField textField_4;
	@AutoGenerated
	private TextField textField_11;
	@AutoGenerated
	private TextField textField_1;
	@AutoGenerated
	private TextField textField_3;
	@AutoGenerated
	private ComboBox comboBox_4;
	@AutoGenerated
	private ComboBox comboBox_5;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public DocEditor() {
		buildMainLayout();
		applyMainLayoutHooks();
		setCompositionRoot(mainLayout);
	}

	private void applyMainLayoutHooks() {
		
		//test APM is ready
		APMReadyTest.addListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
				Random r = new Random();
				boolean APMReady = r.nextBoolean();
				if(APMReady) {
					APMReadyStateLabel.setValue("ready");
				}
				else {
					APMReadyStateLabel.setValue("not ready");					
				}
			}
		});

		//check date
		setTimeButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				//TODO: write this
				if( true){
					dateValidStateLabel.setValue("valid date");					
				}else{
					dateValidStateLabel.setValue("invalid date");					
				}
				
			}
		});
		
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("Use a pre-defined workflow");
		mainLayout.setCaption("Min Speed");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("880px");
		mainLayout.setHeight("700px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("880px");
		setHeight("700px");
		
		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		mainLayout.addComponent(absoluteLayout_2);
		
		return mainLayout;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");
		absoluteLayout_2.setMargin(false);
		
		// middle_tom_area
		middle_tom_area = buildMiddle_tom_area();
		absoluteLayout_2.addComponent(middle_tom_area,
				"top:273.0px;left:0.0px;");
		
		// top_sean_area
		top_sean_area = buildTop_sean_area();
		absoluteLayout_2.addComponent(top_sean_area, "top:0.0px;left:0.0px;");
		
		// absoluteLayout_3
		absoluteLayout_3 = buildAbsoluteLayout_3();
		absoluteLayout_2.addComponent(absoluteLayout_3,
				"top:516.0px;left:0.0px;");
		
		return absoluteLayout_2;
	}

	@AutoGenerated
	private AbsoluteLayout buildMiddle_tom_area() {
		// common part: create layout
		middle_tom_area = new AbsoluteLayout();
		middle_tom_area.setImmediate(false);
		middle_tom_area.setWidth("880px");
		middle_tom_area.setHeight("240px");
		middle_tom_area.setMargin(false);
		
		// comboBox_5
		comboBox_5 = new ComboBox();
		comboBox_5.setCaption("Which Workflow do you want to use ?");
		comboBox_5.setImmediate(false);
		comboBox_5.setWidth("210px");
		comboBox_5.setHeight("-1px");
		middle_tom_area.addComponent(comboBox_5, "top:19.0px;left:260.0px;");
		
		// comboBox_4
		comboBox_4 = new ComboBox();
		comboBox_4.setCaption("How do you want to define the project ?");
		comboBox_4.setImmediate(false);
		comboBox_4.setWidth("220px");
		comboBox_4.setHeight("-1px");
		middle_tom_area.addComponent(comboBox_4, "top:18.0px;left:20.0px;");
		
		// textField_3
		textField_3 = new TextField();
		textField_3.setCaption("Speed Step");
		textField_3.setImmediate(false);
		textField_3.setWidth("-1px");
		textField_3.setHeight("-1px");
		middle_tom_area.addComponent(textField_3, "top:72.0px;left:483.0px;");
		
		// textField_1
		textField_1 = new TextField();
		textField_1.setCaption("Min Speed");
		textField_1.setImmediate(false);
		textField_1.setWidth("-1px");
		textField_1.setHeight("-1px");
		middle_tom_area.addComponent(textField_1, "top:72.0px;left:204.0px;");
		
		// textField_11
		textField_11 = new TextField();
		textField_11.setCaption("Max Speed");
		textField_11.setImmediate(false);
		textField_11.setWidth("-1px");
		textField_11.setHeight("-1px");
		middle_tom_area.addComponent(textField_11, "top:72.0px;left:344.0px;");
		
		// textField_4
		textField_4 = new TextField();
		textField_4.setCaption("Speed Uncertainty");
		textField_4.setImmediate(false);
		textField_4.setWidth("-1px");
		textField_4.setHeight("-1px");
		middle_tom_area.addComponent(textField_4, "top:119.0px;left:204.0px;");
		
		// textField_6
		textField_6 = new TextField();
		textField_6.setCaption("Max Longitude");
		textField_6.setImmediate(false);
		textField_6.setWidth("-1px");
		textField_6.setHeight("-1px");
		middle_tom_area.addComponent(textField_6, "top:164.0px;left:344.0px;");
		
		// textField_7
		textField_7 = new TextField();
		textField_7.setCaption("Longitude Step");
		textField_7.setImmediate(false);
		textField_7.setWidth("-1px");
		textField_7.setHeight("-1px");
		middle_tom_area.addComponent(textField_7, "top:164.0px;left:483.0px;");
		
		// textField_5
		textField_5 = new TextField();
		textField_5.setCaption("Min Longitude");
		textField_5.setImmediate(false);
		textField_5.setWidth("-1px");
		textField_5.setHeight("-1px");
		middle_tom_area.addComponent(textField_5, "top:164.0px;left:204.0px;");
		
		// textField_8
		textField_8 = new TextField();
		textField_8.setCaption("Min Width");
		textField_8.setImmediate(false);
		textField_8.setWidth("-1px");
		textField_8.setHeight("-1px");
		middle_tom_area.addComponent(textField_8, "top:210.0px;left:204.0px;");
		
		// textField_9
		textField_9 = new TextField();
		textField_9.setCaption("Max Width");
		textField_9.setImmediate(false);
		textField_9.setWidth("-1px");
		textField_9.setHeight("-1px");
		middle_tom_area.addComponent(textField_9, "top:210.0px;left:344.0px;");
		
		// textField_10
		textField_10 = new TextField();
		textField_10.setCaption("Width Step");
		textField_10.setImmediate(false);
		textField_10.setWidth("-1px");
		textField_10.setHeight("-1px");
		middle_tom_area.addComponent(textField_10, "top:210.0px;left:483.0px;");
		
		// absoluteLayout_1
		absoluteLayout_1 = new AbsoluteLayout();
		absoluteLayout_1.setImmediate(false);
		absoluteLayout_1.setWidth("100.0%");
		absoluteLayout_1.setHeight("500px");
		absoluteLayout_1.setMargin(false);
		middle_tom_area.addComponent(absoluteLayout_1,
				"top:243.0px;right:0.0px;left:0.0px;");
		
		// button_4
		button_4 = new Button();
		button_4.setCaption("Set Parameters");
		button_4.setImmediate(true);
		button_4.setWidth("-1px");
		button_4.setHeight("-1px");
		middle_tom_area.addComponent(button_4, "top:19.0px;left:594.0px;");
		
		return middle_tom_area;
	}

	@AutoGenerated
	private AbsoluteLayout buildTop_sean_area() {
		// common part: create layout
		top_sean_area = new AbsoluteLayout();
		top_sean_area.setImmediate(false);
		top_sean_area.setWidth("880px");
		top_sean_area.setHeight("260px");
		top_sean_area.setMargin(false);
		
		// setTimeButton
		setTimeButton = new Button();
		setTimeButton.setCaption("Set time");
		setTimeButton.setImmediate(true);
		setTimeButton.setWidth("100px");
		setTimeButton.setHeight("-1px");
		top_sean_area.addComponent(setTimeButton, "top:77.0px;left:220.0px;");
		
		// dateInputOne
		dateInputOne = new PopupDateField();
		dateInputOne.setImmediate(false);
		dateInputOne.setWidth("-1px");
		dateInputOne.setHeight("-1px");
		top_sean_area.addComponent(dateInputOne, "top:77.0px;left:20.0px;");
		
		// dateInputTwo
		dateInputTwo = new PopupDateField();
		dateInputTwo.setImmediate(false);
		dateInputTwo.setWidth("-1px");
		dateInputTwo.setHeight("-1px");
		top_sean_area.addComponent(dateInputTwo, "top:120.0px;left:20.0px;");
		
		// APMReadyTest
		APMReadyTest = new Button();
		APMReadyTest
				.setCaption("Test if the Advanced Propagation Model is ready to run");
		APMReadyTest.setImmediate(true);
		APMReadyTest.setWidth("360px");
		APMReadyTest.setHeight("-1px");
		top_sean_area.addComponent(APMReadyTest, "top:34.0px;left:20.0px;");
		
		// comboBox_2
		comboBox_2 = new ComboBox();
		comboBox_2.setCaption("How do you want to find events ?");
		comboBox_2.setImmediate(false);
		comboBox_2.setWidth("200px");
		comboBox_2.setHeight("-1px");
		top_sean_area.addComponent(comboBox_2, "top:179.0px;left:20.0px;");
		
		// comboBox_3
		comboBox_3 = new ComboBox();
		comboBox_3.setCaption("Which HEC Catalogues do you want to use ?");
		comboBox_3.setImmediate(false);
		comboBox_3.setWidth("-1px");
		comboBox_3.setHeight("-1px");
		top_sean_area.addComponent(comboBox_3, "top:180.0px;left:220.0px;");
		
		// comboBox_6
		comboBox_6 = new ComboBox();
		comboBox_6.setCaption("Which workflow do you want to use ?");
		comboBox_6.setImmediate(false);
		comboBox_6.setWidth("-1px");
		comboBox_6.setHeight("-1px");
		top_sean_area.addComponent(comboBox_6, "top:222.0px;left:220.0px;");
		
		// button_3
		button_3 = new Button();
		button_3.setCaption("Find Events");
		button_3.setImmediate(true);
		button_3.setWidth("-1px");
		button_3.setHeight("-1px");
		top_sean_area.addComponent(button_3, "top:179.0px;left:470.0px;");
		
		// APMReadyStateLabel
		APMReadyStateLabel = new Label();
		APMReadyStateLabel.setImmediate(false);
		APMReadyStateLabel.setWidth("160px");
		APMReadyStateLabel.setHeight("26px");
		top_sean_area.addComponent(APMReadyStateLabel,
				"top:34.0px;left:400.0px;");
		
		// label_1
		
		dateValidStateLabel = new Label();
		dateValidStateLabel.setImmediate(false);
		dateValidStateLabel.setWidth("-1px");
		dateValidStateLabel.setHeight("-1px");
		top_sean_area.addComponent(dateValidStateLabel, "top:80.0px;left:340.0px;");
		
		return top_sean_area;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_3() {
		// common part: create layout
		absoluteLayout_3 = new AbsoluteLayout();
		absoluteLayout_3.setImmediate(false);
		absoluteLayout_3.setWidth("100.0%");
		absoluteLayout_3.setHeight("200px");
		absoluteLayout_3.setMargin(false);
		
		// comboBox_7
		comboBox_7 = new ComboBox();
		comboBox_7
				.setCaption("How do you want to execute propagation parameters ?");
		comboBox_7.setImmediate(false);
		comboBox_7.setWidth("300px");
		comboBox_7.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_7, "top:36.0px;left:20.0px;");
		
		// comboBox_1
		comboBox_1 = new ComboBox();
		comboBox_1.setCaption("Which Workflow do you want ?");
		comboBox_1.setImmediate(false);
		comboBox_1.setWidth("-1px");
		comboBox_1.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_1, "top:36.0px;left:340.0px;");
		
		// comboBox_8
		comboBox_8 = new ComboBox();
		comboBox_8.setCaption("How do you want to rank your result");
		comboBox_8.setImmediate(false);
		comboBox_8.setWidth("200px");
		comboBox_8.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_8, "top:86.0px;left:20.0px;");
		
		// comboBox_9
		comboBox_9 = new ComboBox();
		comboBox_9.setCaption("Which HEC Catalogues do you want to use ?");
		comboBox_9.setImmediate(false);
		comboBox_9.setWidth("-1px");
		comboBox_9.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_9, "top:86.0px;left:240.0px;");
		
		// comboBox_10
		comboBox_10 = new ComboBox();
		comboBox_10.setCaption("Which workflow do you want to use ?");
		comboBox_10.setImmediate(false);
		comboBox_10.setWidth("-1px");
		comboBox_10.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_10, "top:126.0px;left:240.0px;");
		
		// button_5
		button_5 = new Button();
		button_5.setCaption("Set Propagation Model Application");
		button_5.setImmediate(true);
		button_5.setWidth("-1px");
		button_5.setHeight("-1px");
		absoluteLayout_3.addComponent(button_5, "top:36.0px;left:523.0px;");
		
		// button_6
		button_6 = new Button();
		button_6.setCaption("Run !");
		button_6.setImmediate(true);
		button_6.setWidth("-1px");
		button_6.setHeight("-1px");
		absoluteLayout_3.addComponent(button_6, "top:86.0px;left:523.0px;");
		
		return absoluteLayout_3;
	}

}
