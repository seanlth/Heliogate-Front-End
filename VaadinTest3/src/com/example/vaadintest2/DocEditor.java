
/*Master branch*/
//

package com.example.vaadintest2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
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
	private Button findEventsButton;
	@AutoGenerated
	private ComboBox workflowComboBox;
	@AutoGenerated
	private ComboBox cataloguesComboBox;
	@AutoGenerated
	private ComboBox findEventsComboBox;
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
	private Button setParametersButton;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_1;
	@AutoGenerated
	private TextField widthStepField;
	@AutoGenerated
	private TextField maxWidthField;
	@AutoGenerated
	private TextField minWidthField;
	@AutoGenerated
	private TextField minLongitudeField;
	@AutoGenerated
	private TextField longitudeStepField;
	@AutoGenerated
	private TextField maxLongitudeField;
	@AutoGenerated
	private TextField speedUncertaintyField;
	@AutoGenerated
	private TextField maxSpeedField;
	@AutoGenerated
	private TextField minSpeedField;
	@AutoGenerated
	private TextField speedStepField;
	@AutoGenerated
	private ComboBox comboBox_4;
	@AutoGenerated
	private ComboBox comboBox_5;
	private Catalogue catalogues[];
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
		
		dateInputOne.setLocale(Locale.UK);
		dateInputTwo.setLocale(Locale.UK);
		dateInputOne.setDateFormat("yyyy-MM-dd HH:mm:ss");
		dateInputTwo.setDateFormat("yyyy-MM-dd HH:mm:ss");
		
		findEventsComboBox.setNullSelectionAllowed(false);
		findEventsComboBox.setInvalidAllowed(false);
		findEventsComboBox.addItem("Query a specific HEC catalogue");
		findEventsComboBox.addItem("Use a workflow");
		findEventsComboBox.setValue(findEventsComboBox.getItemIds().iterator().next());
		
		catalogues = null;
		
		try {
			catalogues = queryCatalogueList();
		}
		catch (Exception e) {
			System.out.println("An error has occured");
		}
		
		cataloguesComboBox.setNullSelectionAllowed(false);
		cataloguesComboBox.setInvalidAllowed(false);
		if (catalogues != null) {
			for (int i = 0; i < catalogues.length; i++) {
				cataloguesComboBox.addItem(catalogues[i].getName());
			}
			cataloguesComboBox.setValue(cataloguesComboBox.getItemIds().iterator().next());
		}
		 
		workflowComboBox.setNullSelectionAllowed(false);
		workflowComboBox.setInvalidAllowed(false);
		workflowComboBox.addItem("Some workflow");
		workflowComboBox.setValue(workflowComboBox.getItemIds().iterator().next());
		
		
		
		minSpeedField.setReadOnly(false);
		maxSpeedField.setReadOnly(false);
		speedStepField.setReadOnly(false);
		speedUncertaintyField.setReadOnly(false);
		minLongitudeField.setReadOnly(false);
		maxLongitudeField.setReadOnly(false);
		longitudeStepField.setReadOnly(false);
		minWidthField.setReadOnly(false);
		maxWidthField.setReadOnly(false);
		widthStepField.setReadOnly(false);
		
		
		//button_3.setIcon(new ExternalResource(""));
	}
	
	//could be replaced by putting catalogue info in file
	private Catalogue[] queryCatalogueList() throws Exception
	{
		Catalogue catalogueData[] = new Catalogue[79]; //just hard coded for now
		
		final URL oracle = new URL("http://hec.helio-vo.eu/hec/hec_gui.php");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        int line = 0;
        while ((inputLine = in.readLine()) != null) {
        	if (inputLine.contains("tr id")) {
        		if (line > 0) {
        			String nameId = "";
        			String name = "";
        			String firstDate = "";
        			String lastDate = "";
        			int nameIdIndex = inputLine.indexOf("name")+6;
        			for (int i = nameIdIndex; inputLine.charAt(i) != '"'; i++) {
        				nameId += inputLine.charAt(i); 
        			}
        			int nameIndex = inputLine.indexOf("\"Catalogue\"")+12;
        			for (int i = nameIndex; inputLine.charAt(i) != '<'; i++) {
        				name += inputLine.charAt(i); 
        			}
        			int firstDateIndex = inputLine.indexOf("td class=\"From\"")+16;
        			for (int i = firstDateIndex; inputLine.charAt(i) != '<'; i++) {
        				firstDate += inputLine.charAt(i); 
        			}
        			int lastDateIndex = inputLine.indexOf("td class=\"To\"")+14;
        			for (int i = firstDateIndex; inputLine.charAt(i) != '<'; i++) {
        				lastDate += inputLine.charAt(i); 
        			}
        			int firstYear = Integer.parseInt("" + firstDate.charAt(0) + firstDate.charAt(1) + firstDate.charAt(2) + firstDate.charAt(3));
        			int firstMonth = Integer.parseInt("" + firstDate.charAt(5) + firstDate.charAt(6));
        			int firstDay = Integer.parseInt("" + firstDate.charAt(8) + firstDate.charAt(9));
        			
        			int lastYear = Integer.parseInt("" + lastDate.charAt(0) + lastDate.charAt(1) + lastDate.charAt(2) + firstDate.charAt(3));
        			int lastMonth = Integer.parseInt("" + lastDate.charAt(5) + lastDate.charAt(6));
        			int lastDay = Integer.parseInt("" + lastDate.charAt(8) + lastDate.charAt(9));
	
        			catalogueData[line-1] = new Catalogue(nameId, name, new Date(firstYear, firstMonth, firstDay), new Date(lastYear, lastMonth, lastDay)); 
        		}
        		line++;
        		if (line > 79) {
        			break;
        		}
        	}
        }
        	
        in.close();
		
		return catalogueData;
	}

	private void applyMainLayoutHooks() {
		
		//test APM is ready
		APMReadyTest.addListener(new Button.ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				
				boolean APMReady = isAPMReady();
				if(APMReady) {
					APMReadyStateLabel.setValue("Ready");
                    stage2();
				}
				else {
					APMReadyStateLabel.setValue("Not Ready");					
				}
			}
		});

		//check date
		setTimeButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Date date1 = null;
				Date date2 = null;

				//try casting the current date in field to date type
				try {
					date1 = (Date)dateInputOne.getValue();
					date2 = (Date)dateInputTwo.getValue();
					
					if (parseDate( date1, date2 )) {
						dateValidStateLabel.setValue("Valid Date");	
						stage3();
					} 
					else {
						dateValidStateLabel.setValue("Invalid Date");					
					}

				}
				catch (Exception e) {
					dateValidStateLabel.setValue("Invalid Date");					
				}
			}
		});
		
		findEventsButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
			}
		});
		//find events
		findEventsButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				stage4();
			}
		});
		//set parameters
		setParametersButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				stage5();
			}
		});
		//set propogation model application
		button_5.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				stage6();
			}
		});


		
		setParametersButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				String data[] = findEvents();
				minSpeedField.setValue(data[0]);
				maxSpeedField.setValue(data[1]);
				speedStepField.setValue(data[2]);
				speedUncertaintyField.setValue(data[3]);
				minLongitudeField.setValue(data[4]);
				maxLongitudeField.setValue(data[5]);
				longitudeStepField.setValue(data[6]);
				minWidthField.setValue(data[7]);
				maxWidthField.setValue(data[8]);
				widthStepField.setValue(data[9]);
			}
		});
		
	}
	
	
	private boolean parseDate(Date date1, Date date2) {
		
		//very handy
		if (date1.before(date2) ) {
			return true;
		}		
		return false;
    }

	
	private String[] findEvents()
	{
		String data[] = new String[10];
		Random rand = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = "";
			data[i] = String.valueOf(rand.nextFloat());
		}
		
		return data;
	}
	
	private boolean isAPMReady()
	{
		Random rand = new Random();
		return rand.nextBoolean();
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
		comboBox_5.setEnabled(false);
		comboBox_5.setWidth("210px");
		comboBox_5.setHeight("-1px");
		middle_tom_area.addComponent(comboBox_5, "top:19.0px;left:260.0px;");
		
		// comboBox_4
		comboBox_4 = new ComboBox();
		comboBox_4.setCaption("How do you want to define the parameters?");
		comboBox_4.setImmediate(false);
		comboBox_4.setEnabled(false);
		comboBox_4.setWidth("220px");
		comboBox_4.setHeight("-1px");
		middle_tom_area.addComponent(comboBox_4, "top:18.0px;left:20.0px;");
		

		// speedStepField
		speedStepField = new TextField();
		speedStepField.setCaption("Speed Step");
		speedStepField.setImmediate(false);
		speedStepField.setEnabled(false);
		speedStepField.setWidth("-1px");
		speedStepField.setHeight("-1px");
		middle_tom_area
				.addComponent(speedStepField, "top:72.0px;left:483.0px;");

		

		// minSpeedField
		minSpeedField = new TextField();
		minSpeedField.setCaption("Min Speed");
		minSpeedField.setImmediate(false);
		minSpeedField.setEnabled(false);
		minSpeedField.setWidth("-1px");
		minSpeedField.setHeight("-1px");
		middle_tom_area.addComponent(minSpeedField, "top:72.0px;left:204.0px;");

		
		// maxSpeedField
		maxSpeedField = new TextField();
		maxSpeedField.setCaption("Max Speed");
		maxSpeedField.setImmediate(false);
		maxSpeedField.setEnabled(false);
		maxSpeedField.setWidth("-1px");
		maxSpeedField.setHeight("-1px");
		middle_tom_area.addComponent(maxSpeedField, "top:72.0px;left:344.0px;");

		

		// speedUncertaintyField
		speedUncertaintyField = new TextField();
		speedUncertaintyField.setCaption("Speed Uncertainty");
		speedUncertaintyField.setImmediate(false);
		speedUncertaintyField.setEnabled(false);
		speedUncertaintyField.setWidth("-1px");
		speedUncertaintyField.setHeight("-1px");
		middle_tom_area.addComponent(speedUncertaintyField,
				"top:119.0px;left:204.0px;");

		

		// maxLongitudeField
		maxLongitudeField = new TextField();
		maxLongitudeField.setCaption("Max Longitude");
		maxLongitudeField.setImmediate(false);
		maxLongitudeField.setEnabled(false);
		maxLongitudeField.setWidth("-1px");
		maxLongitudeField.setHeight("-1px");
		middle_tom_area.addComponent(maxLongitudeField,
				"top:164.0px;left:344.0px;");

		

		// longitudeStepField
		longitudeStepField = new TextField();
		longitudeStepField.setCaption("Longitude Step");
		longitudeStepField.setImmediate(false);
		longitudeStepField.setEnabled(false);
		longitudeStepField.setWidth("-1px");
		longitudeStepField.setHeight("-1px");
		middle_tom_area.addComponent(longitudeStepField,
				"top:164.0px;left:483.0px;");

		

		// minLongitudeField
		minLongitudeField = new TextField();
		minLongitudeField.setCaption("Min Longitude");
		minLongitudeField.setImmediate(false);
		minLongitudeField.setEnabled(false);
		minLongitudeField.setWidth("-1px");
		minLongitudeField.setHeight("-1px");
		middle_tom_area.addComponent(minLongitudeField,
				"top:164.0px;left:204.0px;");

		

		// minWidthField
		minWidthField = new TextField();
		minWidthField.setCaption("Min Width");
		minWidthField.setImmediate(false);
		minWidthField.setEnabled(false);
		minWidthField.setWidth("-1px");
		minWidthField.setHeight("-1px");
		middle_tom_area
				.addComponent(minWidthField, "top:210.0px;left:204.0px;");

		

		// maxWidthField
		maxWidthField = new TextField();
		maxWidthField.setCaption("Max Width");
		maxWidthField.setImmediate(false);
		maxWidthField.setEnabled(false);
		maxWidthField.setWidth("-1px");
		maxWidthField.setHeight("-1px");
		middle_tom_area
				.addComponent(maxWidthField, "top:210.0px;left:344.0px;");
		

		// widthStepField
		widthStepField = new TextField();
		widthStepField.setCaption("Width Step");
		widthStepField.setImmediate(false);
		widthStepField.setEnabled(false);
		widthStepField.setWidth("-1px");
		widthStepField.setHeight("-1px");
		middle_tom_area.addComponent(widthStepField,
				"top:210.0px;left:483.0px;");

		
		// absoluteLayout_1
		absoluteLayout_1 = new AbsoluteLayout();
		absoluteLayout_1.setImmediate(false);
		absoluteLayout_1.setWidth("100.0%");
		absoluteLayout_1.setHeight("500px");
		absoluteLayout_1.setMargin(false);
		middle_tom_area.addComponent(absoluteLayout_1,
				"top:243.0px;right:0.0px;left:0.0px;");
		

		// setParametersButton
		setParametersButton = new Button();
		setParametersButton.setCaption("Set Parameters");
		setParametersButton.setImmediate(true);
		setParametersButton.setEnabled(false);
		setParametersButton.setWidth("-1px");
		setParametersButton.setHeight("-1px");
		middle_tom_area.addComponent(setParametersButton,
				"top:19.0px;left:594.0px;");
		
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
		setTimeButton.setEnabled(false);
		setTimeButton.setWidth("100px");
		setTimeButton.setHeight("-1px");
		top_sean_area.addComponent(setTimeButton, "top:77.0px;left:220.0px;");
		
		// dateInputOne
		dateInputOne = new PopupDateField();
		dateInputOne.setImmediate(false);
		dateInputOne.setEnabled(false);
		dateInputOne.setWidth("-1px");
		dateInputOne.setHeight("-1px");
		dateInputOne.setResolution(1);
		top_sean_area.addComponent(dateInputOne, "top:77.0px;left:20.0px;");
		
		// dateInputTwo
		dateInputTwo = new PopupDateField();
		dateInputTwo.setImmediate(false);
		dateInputTwo.setEnabled(false);
		dateInputTwo.setWidth("-1px");
		dateInputTwo.setHeight("-1px");
		dateInputTwo.setResolution(1);
		top_sean_area.addComponent(dateInputTwo, "top:120.0px;left:20.0px;");
		
		// APMReadyTest
		APMReadyTest = new Button();
		APMReadyTest.setCaption("Test if the Advanced Propagation Model is ready to run");
		APMReadyTest.setImmediate(true);
		APMReadyTest.setWidth("360px");
		APMReadyTest.setHeight("-1px");
		top_sean_area.addComponent(APMReadyTest, "top:34.0px;left:20.0px;");
		

		// findEventsComboBox
		findEventsComboBox = new ComboBox();
		findEventsComboBox.setCaption("How do you want to find events ?");
		findEventsComboBox.setImmediate(true);
		findEventsComboBox.setEnabled(false);
		findEventsComboBox.setWidth("200px");
		findEventsComboBox.setHeight("-1px");
		top_sean_area.addComponent(findEventsComboBox,
				"top:179.0px;left:20.0px;");

		

		// cataloguesComboBox
		cataloguesComboBox = new ComboBox();
		cataloguesComboBox
				.setCaption("Which HEC Catalogues do you want to use ?");
		cataloguesComboBox.setImmediate(false);
		cataloguesComboBox.setEnabled(false);
		cataloguesComboBox.setWidth("366px");
		cataloguesComboBox.setHeight("-1px");
		top_sean_area.addComponent(cataloguesComboBox,
				"top:180.0px;left:220.0px;");

		

		// workflowComboBox
		workflowComboBox = new ComboBox();
		workflowComboBox.setCaption("Which workflow do you want to use ?");
		workflowComboBox.setImmediate(false);
		workflowComboBox.setEnabled(false);
		workflowComboBox.setWidth("-1px");
		workflowComboBox.setHeight("-1px");
		top_sean_area.addComponent(workflowComboBox,
				"top:222.0px;left:220.0px;");

		

		// findEventsButton
		findEventsButton = new Button();
		findEventsButton.setCaption("Find Events");
		findEventsButton.setImmediate(true);
		findEventsButton.setEnabled(false);
		findEventsButton.setWidth("-1px");
		findEventsButton.setHeight("-1px");
		top_sean_area.addComponent(findEventsButton,
				"top:179.0px;left:607.0px;");

		
		// APMReadyStateLabel
		APMReadyStateLabel = new Label();
		APMReadyStateLabel.setImmediate(false);
		APMReadyStateLabel.setWidth("160px");
		APMReadyStateLabel.setHeight("26px");
		APMReadyStateLabel.setValue("Not Ready");
		top_sean_area.addComponent(APMReadyStateLabel,
				"top:34.0px;left:400.0px;");
		
		// dateValidStateLabel
		dateValidStateLabel = new Label();
		dateValidStateLabel.setImmediate(false);
		dateValidStateLabel.setWidth("-1px");
		dateValidStateLabel.setHeight("-1px");
		dateValidStateLabel.setValue("Invalid Data");
		top_sean_area.addComponent(dateValidStateLabel,
				"top:80.0px;left:340.0px;");
		
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
		comboBox_7.setCaption("How do you want to execute propagation parameters ?");
		comboBox_7.setImmediate(false);
		comboBox_7.setEnabled(false);
		comboBox_7.setWidth("300px");
		comboBox_7.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_7, "top:36.0px;left:20.0px;");
		
		// comboBox_1
		comboBox_1 = new ComboBox();
		comboBox_1.setCaption("Which Workflow do you want to use?");
		comboBox_1.setImmediate(false);
		comboBox_1.setEnabled(false);
		comboBox_1.setWidth("-1px");
		comboBox_1.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_1, "top:36.0px;left:340.0px;");
		
		// comboBox_8
		comboBox_8 = new ComboBox();
		comboBox_8.setCaption("How do you want to rank your result");
		comboBox_8.setImmediate(false);
		comboBox_8.setEnabled(false);
		comboBox_8.setWidth("200px");
		comboBox_8.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_8, "top:86.0px;left:20.0px;");
		
		// comboBox_9
		comboBox_9 = new ComboBox();
		comboBox_9.setCaption("Which HEC Catalogues do you want to use ?");
		comboBox_9.setImmediate(false);
		comboBox_9.setEnabled(false);
		comboBox_9.setWidth("-1px");
		comboBox_9.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_9, "top:86.0px;left:240.0px;");
		
		// comboBox_10
		comboBox_10 = new ComboBox();
		comboBox_10.setCaption("Which workflow do you want to use ?");
		comboBox_10.setImmediate(false);
		comboBox_10.setEnabled(false);
		comboBox_10.setWidth("-1px");
		comboBox_10.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_10, "top:126.0px;left:240.0px;");
		
		// button_5
		button_5 = new Button();
		button_5.setCaption("Set Propagation Model Application");
		button_5.setImmediate(true);
		button_5.setEnabled(false);
		button_5.setWidth("-1px");
		button_5.setHeight("-1px");
		absoluteLayout_3.addComponent(button_5, "top:36.0px;left:523.0px;");
		
		// button_6
		button_6 = new Button();
		button_6.setCaption("Run !");
		button_6.setImmediate(true);
		button_6.setEnabled(false);
		button_6.setWidth("-1px");
		button_6.setHeight("-1px");
		absoluteLayout_3.addComponent(button_6, "top:86.0px;left:523.0px;");
		
		return absoluteLayout_3;
	}
	private void stage2(){
		dateInputOne.setEnabled(true);
		dateInputTwo.setEnabled(true);
		setTimeButton.setEnabled(true);
	}
	private void stage3(){
		findEventsComboBox.setEnabled(true);
		cataloguesComboBox.setEnabled(true);
		workflowComboBox.setEnabled(false);
		findEventsButton.setEnabled(true);
		findEventsComboBox.addListener(new Property.ValueChangeListener() {        
            public void valueChange(ValueChangeEvent event) {
                if(findEventsComboBox.getValue() == "Query a specific HEC catalogue"){
                	cataloguesComboBox.setEnabled(true);
                	workflowComboBox.setEnabled(false);
                }else if(findEventsComboBox.getValue() == "Use a workflow"){
                	cataloguesComboBox.setEnabled(false);
                	workflowComboBox.setEnabled(true);
                }
            }
        });
	}
	private void stage4(){
		comboBox_4.setEnabled(true);
		comboBox_5.setEnabled(true);
		minSpeedField.setEnabled(true);
		maxSpeedField.setEnabled(true);
		speedStepField.setEnabled(true);
		speedUncertaintyField.setEnabled(true);
		minLongitudeField.setEnabled(true);
		maxLongitudeField.setEnabled(true);
		longitudeStepField.setEnabled(true);
		minWidthField.setEnabled(true);
		maxWidthField.setEnabled(true);
		widthStepField.setEnabled(true);
		setParametersButton.setEnabled(true);
	}
	private void stage5(){
		comboBox_7.setEnabled(true);
		comboBox_1.setEnabled(true);
		button_5.setEnabled(true);
	}
	private void stage6(){
		comboBox_8.setEnabled(true);
		comboBox_9.setEnabled(true);
		comboBox_10.setEnabled(true);
		button_6.setEnabled(true);
	}

}

