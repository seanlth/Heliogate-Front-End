package com.example.vaadintest2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.LinkedList;
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
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractField;

public class DocEditor extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	private String[]resultsParameters;
	private String[][]results;
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
	private Button simpleUserButton;
	@AutoGenerated
	private Button advancedUserButton;
	@AutoGenerated
	private Label dateValidStateLabel;
	@AutoGenerated
	private Label APMReadyStateLabel;
	@AutoGenerated
	private Label UserModalityLabel;
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
	private ComboBox comboBox_4;
	@AutoGenerated
	private ComboBox comboBox_5;
	private Table parameterTable;
	private Label validDateRange;
	
	private Catalogue catalogues[];

	private LinkedList<TextField> parameterFields;
	private String parameters[];
	private String parametersData[][];

	private boolean isSimple = false;

	private String catalogueLocation = "C:\\catalogues.txt";

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

		//should only be run when new catalogues have been added
		try {
			//System.out.println(fetchCatalogueData(new Date(2014, 2, 2), new Date(2014, 2, 3), "cactus_soho_cme"));
			updataCatalogueData();
		} catch (Exception e) {
			System.out.println("ERROR");
		}

		catalogues = null;

		try {
			catalogues = queryCatalogueList();
		}
		catch (Exception e) {
			System.out.println("No catalogue data available");
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



		//              minSpeedField.setReadOnly(false);
		//              maxSpeedField.setReadOnly(false);
		//              speedStepField.setReadOnly(false);
		//              speedUncertaintyField.setReadOnly(false);
		//              minLongitudeField.setReadOnly(false);
		//              maxLongitudeField.setReadOnly(false);
		//              longitudeStepField.setReadOnly(false);
		//              minWidthField.setReadOnly(false);
		//              maxWidthField.setReadOnly(false);
		//              widthStepField.setReadOnly(false);

		parameterFields = new LinkedList<TextField>();

		//button_3.setIcon(new ExternalResource(""));
	}


	private boolean contains(int array[], int number) {
		for (int i = 0; i < array.length; i++) {
			if ( array[i] == number) {
				return true;
			}
		}
		return false;
	}

	//could be replaced by putting catalogue info in file
	private void updataCatalogueData() throws Exception
	{
		PrintWriter out = new PrintWriter(catalogueLocation);

		final URL catalogues = new URL("http://hec.helio-vo.eu/hec/hec_gui.php");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(catalogues.openStream()));

		String inputLine;

		int CMECataloguesIndices[] = new int[32];
		int indices = 0;


		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains("var cmearr=[")) {
				int nameIdIndex = inputLine.indexOf("var cmearr=[") + 13;
				for (int i = nameIdIndex; inputLine.charAt(i) != '\''; i+=3) {
					String index = "";
					for (; inputLine.charAt(i) != '\''; i++) {
						index += inputLine.charAt(i);
					}
					CMECataloguesIndices[indices++] = Integer.parseInt(index);
					if (inputLine.charAt(i+1) == ']') {
						break;
					}
				}
			}
		}
		in.close();
		in = new BufferedReader(new InputStreamReader(catalogues.openStream()));


		int line = 0;
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains("tr id=\"cat")) {
				int catNumber = inputLine.indexOf("tr id=\"cat")+10;
				String catNumberString = "";
				for (int j = catNumber; inputLine.charAt(j) != '\"'; j++) {
					catNumberString += inputLine.charAt(j);
				}
				if (contains(CMECataloguesIndices, Integer.parseInt(catNumberString))) {
					if (line > 31) {
						break;
					}
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
					for (int i = lastDateIndex; inputLine.charAt(i) != '<'; i++) {
						lastDate += inputLine.charAt(i);
					}
					String catalogue = nameId + "\n" + name + "\n" + firstDate + "\n" + lastDate;
					out.println(catalogue);
					line++;

				}

			}
		}

		in.close();
		out.close();   
	}

	private String fetchCatalogueData(Date firstDate, Date lastDate, String catalogueName) throws IOException
	{      
		String table = "";

		String year1 = "" + (firstDate.getYear() + 1900);
		String year2 = "" + (lastDate.getYear() + 1900);
		String month1 = "" + (firstDate.getMonth());
		String month2 = "" + (firstDate.getMonth());
		String day1 = "" + (firstDate.getDay());
		String day2 = "" + (firstDate.getDay());



		final URL catalogue = new URL("http://hec.helio-vo.eu/hec/hec_gui_fetch.php?y_from=" + year1 + "&mo_from=" + month1 + "&d_from="+ day1 + "&y_to=" + year2 + "&mo_to=" + month2 + "&d_to=" + day2 + "&radioremote=on&titlesearch2=&" + catalogueName + "=istable");
		BufferedReader inAddress = new BufferedReader(
				new InputStreamReader(catalogue.openStream()));
		String inputLine = "";
		inputLine = inAddress.readLine();

		String address = "http://hec.helio-vo.eu/hec/";

		while ((inputLine = inAddress.readLine()) != null) {                   
			if (inputLine.contains("Download as&nbsp;HELIO Service")) {
				int nameIdIndex = inputLine.indexOf("Download as&nbsp;HELIO Service")+40;
				for (int i = nameIdIndex; inputLine.charAt(i) != '"'; i++) {
					address += inputLine.charAt(i);
				}
			}
		}


		//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//        DocumentBuilder builder;
		//        try {
		//                      builder = factory.newDocumentBuilder();
		//              Document doc = builder.parse(url.getInputStream());
		//             
		//             
		//              TransformerFactory transform = TransformerFactory.newInstance();
		//              Transformer xform = transform.newTransformer();
		//              xform.transform(new DOMSource(doc), new StreamResult(System.out));
		//             
		//              } catch (Exception e) {
		//     
		//                      e.printStackTrace();
		//              }


		final URL tableURL = new URL(address);
		URLConnection url = tableURL.openConnection();
		url.setRequestProperty("User-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:27.0) Gecko/20100101 Firefox/27.0");

		//BufferedReader in = new BufferedReader(new InputStreamReader(tableURL.openStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(url.getInputStream()));

		int inputChar = in.read();

		int line = 0;
		boolean inTable = false;
		while (inputChar != -1) {
			table += (char)inputChar;
			inputChar = in.read();
		}

		in.close();
		return table;
	}



	private Catalogue[] queryCatalogueList() throws Exception
	{
		Catalogue catalogueData[] = new Catalogue[29]; //just hard coded for now last three catalogues seem to be empty
		BufferedReader br = new BufferedReader(new FileReader(catalogueLocation));
		String line = "";
		int lineNumber = 0;

		line = br.readLine();
		while (line != null) {
			String nameId = "";
			String name = "";
			String firstDate = "";
			String lastDate = "";
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					nameId = line;
					break;
				case 1:
					name = line;
					break;
				case 2:
					firstDate = line;
					break;
				case 3:
					lastDate = line;
					break;
				default:
					break;
				}      
				line = br.readLine();
			}


			int firstYear = Integer.parseInt("" + firstDate.charAt(0) + firstDate.charAt(1) + firstDate.charAt(2) + firstDate.charAt(3));
			int firstMonth = Integer.parseInt("" + firstDate.charAt(5) + firstDate.charAt(6));
			int firstDay = Integer.parseInt("" + firstDate.charAt(8) + firstDate.charAt(9));

			int lastYear = Integer.parseInt("" + lastDate.charAt(0) + lastDate.charAt(1) + lastDate.charAt(2) + lastDate.charAt(3));
			int lastMonth = Integer.parseInt("" + lastDate.charAt(5) + lastDate.charAt(6));
			int lastDay = Integer.parseInt("" + lastDate.charAt(8) + lastDate.charAt(9));

			catalogueData[lineNumber] = new Catalogue(nameId, name, new Date(firstYear-1900, firstMonth, firstDay), new Date(lastYear-1900, lastMonth, lastDay));
			lineNumber++;
			if (lineNumber > 28) {
				break; //ignore last two catalogues, appear to be empty
			}
		}
		br.close();

		return catalogueData;
	}


	private String catalogueGetNameID(String catalogueName) {
		for (int i = 0; i < catalogues.length; i++) {
			if ( catalogues[i].getName().equals(catalogueName)) {
				return catalogues[i].getNameId();
			}
		}
		return ""; //should never return here
	}

	private Catalogue getCatalogue(String catalogueName) {
		for (int i = 0; i < catalogues.length; i++) {
			if ( catalogues[i].getName().equals(catalogueName)) {
				return catalogues[i];
			}
		}
		return null; //should never return here
	}

	
	
	private void applyMainLayoutHooks() {

		//test APM is ready
		APMReadyTest.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {

				boolean APMReady = isAPMReady();
				if(APMReady) {
					APMReadyStateLabel.setValue("Ready");
					stage1();
				}
				else {
					APMReadyStateLabel.setValue("Not Ready");      
				}
			}
		});

		simpleUserButton.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				isSimple = true; 
				UserModalityLabel.setValue("Simple User");
				stage2();
			}
		});

		advancedUserButton.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				isSimple = false;
				UserModalityLabel.setValue("Advanced User");
				stage2();
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

		//find events
		findEventsButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (((Date)dateInputOne.getValue()).after(getCatalogue((String)cataloguesComboBox.getValue()).getFirstDate()) && ((Date)dateInputTwo.getValue()).before(getCatalogue((String)cataloguesComboBox.getValue()).getLastDate())) {
					String xml = "";
					try {
						xml = fetchCatalogueData((Date)dateInputOne.getValue(), (Date)dateInputTwo.getValue(), getCatalogue((String)cataloguesComboBox.getValue()).getNameId());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					VOParser parser = new VOParser();
					parser.parse(xml);

					parameters = parser.getParameterArray();
					//parametersData = parser.getAverageOfEvents();       
					parametersData = parser.getEventArray();                               

					stage4();
					validDateRange.setVisible(false);
					parameterTable.setEnabled(true);
					parameterTable.setImmediate(true);
				}
				else {
					validDateRange.setVisible(true);
					validDateRange.setEnabled(true);
					parameterTable.setEnabled(false);
					parameterTable.setVisible(false);
				}
			}
		});

		//set parameters
				setParametersButton.addListener(new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {

						double xPosition = 100.0; //start position of parameter list
						double yPosition = 72.0; //start position of parameter list

						for (int i = 0; i < parameterFields.size(); i++) {
							middle_tom_area.removeComponent(parameterFields.get(i));
						}
						//parameterFields.clear();
						
						parameterTable.removeAllItems();
						
						for (int i = 0; i < parameters.length; i++) {
							parameterTable.addContainerProperty(parameters[i], String.class, null);
						}
						
						
						
						for (int i = 0; i < parametersData.length; i++) {
							Object o[] = new Object[parameters.length];
							for (int j = 0; j < parameters.length; j++) {
								o[j] = parametersData[i][j];
							}
							parameterTable.addItem(i);
							for (int j = 0; j < parameters.length; j++) {
								parameterTable.getContainerProperty(i, parameters[j]).setValue(parametersData[i][j]);
							}
						}
						parameterTable.setVisible(true);
						parameterTable.setEditable(true);

//						for (int i = 0; i < parameters.length; i++) {
//							TextField textField = new TextField();         
//							textField.setCaption(parameters[i]);
//							textField.setImmediate(false);
//							textField.setWidth("100px");
//							textField.setHeight("-1px");
//							textField.setReadOnly(false);
//							textField.setValue(parametersData[0][i]);
//							middle_tom_area.addComponent(textField, "top:" + yPosition + "px;left:"+ xPosition  +"px");
//							xPosition += textField.getWidth() + 10;
//							if (xPosition > 540) {
//								xPosition = 100.0;
//								yPosition += 50;
//							}
//							parameterFields.add(textField);
//						}

						stage5();
					}
				});

		//set propogation model application
		button_5.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				stage6();
			}
		});



		//              setParametersButton.addListener(new Button.ClickListener() {
		//                      public void buttonClick(ClickEvent event) {
		//                              String data[] = findEvents();
		//                              minSpeedField.setValue(data[0]);
		//                              maxSpeedField.setValue(data[1]);
		//                              speedStepField.setValue(data[2]);
		//                              speedUncertaintyField.setValue(data[3]);
		//                              minLongitudeField.setValue(data[4]);
		//                              maxLongitudeField.setValue(data[5]);
		//                              longitudeStepField.setValue(data[6]);
		//                              minWidthField.setValue(data[7]);
		//                              maxWidthField.setValue(data[8]);
		//                              widthStepField.setValue(data[9]);
		//                      }
		//              });

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


	private void stage1(){

		advancedUserButton.setEnabled(true);
		simpleUserButton.setEnabled(true);

	}

	private void stage2(){
		dateInputOne.setEnabled(true);
		dateInputTwo.setEnabled(true);
		setTimeButton.setEnabled(true);
	}

	private void stage3(){
		if( !isSimple ){
			findEventsComboBox.setEnabled(true);
		}else{
			findEventsComboBox.setEnabled(false);  
		}
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
		if( !isSimple ){
			comboBox_4.setEnabled(true);
			comboBox_5.setEnabled(true);                   
		}

		//              minSpeedField.setEnabled(true);
		//              maxSpeedField.setEnabled(true);
		//              speedStepField.setEnabled(true);
		//              speedUncertaintyField.setEnabled(true);
		//              minLongitudeField.setEnabled(true);
		//              maxLongitudeField.setEnabled(true);
		//              longitudeStepField.setEnabled(true);
		//              minWidthField.setEnabled(true);
		//              maxWidthField.setEnabled(true);
		//              widthStepField.setEnabled(true);
		setParametersButton.setEnabled(true);
	}
	private void stage5(){
		if( !isSimple ){
			comboBox_7.setEnabled(true);
			comboBox_1.setEnabled(true);
			button_5.setEnabled(true);                     
		}else{
			stage6();
		}

	}
	private void stage6(){
		if( !isSimple ){
			comboBox_8.setEnabled(true);
			comboBox_10.setEnabled(true);                  
		}
		comboBox_9.setEnabled(true);
		button_6.setEnabled(true);
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
		absoluteLayout_2.addComponent(top_sean_area, "top:13.0px;left:0.0px;");

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
		comboBox_5.setEnabled(false);
		comboBox_5.setImmediate(false);
		comboBox_5.setWidth("210px");
		comboBox_5.setHeight("-1px");
		middle_tom_area.addComponent(comboBox_5, "top:19.0px;left:260.0px;");

		// comboBox_4
		comboBox_4 = new ComboBox();
		comboBox_4.setCaption("How do you want to define the parameters?");
		comboBox_4.setEnabled(false);
		comboBox_4.setImmediate(false);
		comboBox_4.setWidth("220px");
		comboBox_4.setHeight("-1px");
		middle_tom_area.addComponent(comboBox_4, "top:18.0px;left:20.0px;");

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
		setParametersButton.setEnabled(false);
		setParametersButton.setImmediate(true);
		setParametersButton.setWidth("-1px");
		setParametersButton.setHeight("-1px");
		middle_tom_area.addComponent(setParametersButton,
				"top:19.0px;left:594.0px;");
		
		
		// parameterTable
		parameterTable = new Table();
		parameterTable.setEnabled(false);
		parameterTable.setImmediate(false);
		parameterTable.setVisible(false);
		parameterTable.setWidth("728px");
		parameterTable.setHeight("175px");
		middle_tom_area.addComponent(parameterTable,
				"top:70.0px;left:60.0px;");

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
		setTimeButton.setEnabled(false);
		setTimeButton.setImmediate(true);
		setTimeButton.setWidth("100px");
		setTimeButton.setHeight("-1px");
		top_sean_area.addComponent(setTimeButton, "top:86.0px;left:220.0px;");

		// dateInputOne
		dateInputOne = new PopupDateField();
		dateInputOne.setEnabled(false);
		dateInputOne.setImmediate(false);
		dateInputOne.setWidth("-1px");
		dateInputOne.setHeight("-1px");
		dateInputOne.setResolution(1);
		top_sean_area.addComponent(dateInputOne, "top:86.0px;left:20.0px;");

		// dateInputTwo
		dateInputTwo = new PopupDateField();
		dateInputTwo.setEnabled(false);
		dateInputTwo.setImmediate(false);
		dateInputTwo.setWidth("-1px");
		dateInputTwo.setHeight("-1px");
		dateInputTwo.setResolution(1);
		top_sean_area.addComponent(dateInputTwo, "top:126.0px;left:20.0px;");

		// APMReadyTest
		APMReadyTest = new Button();
		APMReadyTest
		.setCaption("Test if the Advanced Propagation Model is ready to run");
		APMReadyTest.setImmediate(true);
		APMReadyTest.setWidth("360px");
		APMReadyTest.setHeight("-1px");
		top_sean_area.addComponent(APMReadyTest, "top:7.0px;left:20.0px;");

		// findEventsComboBox
		findEventsComboBox = new ComboBox();
		findEventsComboBox.setCaption("How do you want to find events ?");
		findEventsComboBox.setEnabled(false);
		findEventsComboBox.setImmediate(true);
		findEventsComboBox.setWidth("200px");
		findEventsComboBox.setHeight("-1px");
		findEventsComboBox.setInvalidAllowed(false);
		top_sean_area.addComponent(findEventsComboBox,
				"top:179.0px;left:20.0px;");

		// cataloguesComboBox
		cataloguesComboBox = new ComboBox();
		cataloguesComboBox
		.setCaption("Which HEC Catalogues do you want to use ?");
		cataloguesComboBox.setEnabled(false);
		cataloguesComboBox.setImmediate(false);
		cataloguesComboBox.setWidth("366px");
		cataloguesComboBox.setHeight("-1px");
		top_sean_area.addComponent(cataloguesComboBox,
				"top:180.0px;left:220.0px;");

		// workflowComboBox
		workflowComboBox = new ComboBox();
		workflowComboBox.setCaption("Which workflow do you want to use ?");
		workflowComboBox.setEnabled(false);
		workflowComboBox.setImmediate(false);
		workflowComboBox.setWidth("-1px");
		workflowComboBox.setHeight("-1px");
		top_sean_area.addComponent(workflowComboBox,
				"top:222.0px;left:220.0px;");

		// findEventsButton
		findEventsButton = new Button();
		findEventsButton.setCaption("Find Events");
		findEventsButton.setEnabled(false);
		findEventsButton.setImmediate(true);
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
				"top:16.0px;left:400.0px;");

		// dateValidStateLabel
		dateValidStateLabel = new Label();
		dateValidStateLabel.setImmediate(false);
		dateValidStateLabel.setWidth("-1px");
		dateValidStateLabel.setHeight("-1px");
		dateValidStateLabel.setValue("Invalid Data");
		top_sean_area.addComponent(dateValidStateLabel,
				"top:86.0px;left:340.0px;");

		// advancedUserButton
		advancedUserButton = new Button();
		advancedUserButton.setCaption("Advanced User");
		advancedUserButton.setEnabled(false);
		advancedUserButton.setImmediate(true);
		advancedUserButton.setWidth("-1px");
		advancedUserButton.setHeight("-1px");
		top_sean_area.addComponent(advancedUserButton,
				"top:47.0px;left:20.0px;");

		// simpleUserButton
		simpleUserButton = new Button();
		simpleUserButton.setCaption("Simple User");
		simpleUserButton.setEnabled(false);
		simpleUserButton.setImmediate(true);
		simpleUserButton.setWidth("-1px");
		simpleUserButton.setHeight("-1px");
		top_sean_area
		.addComponent(simpleUserButton, "top:47.0px;left:195.0px;");
		
		
		// APMReadyStateLabel
		//UserModalityLabel
		UserModalityLabel = new Label();
		UserModalityLabel.setImmediate(false);
		UserModalityLabel.setWidth("160px");
		UserModalityLabel.setHeight("26px");
		UserModalityLabel.setValue("");
		top_sean_area.addComponent(UserModalityLabel,
				"top:47.0px;left:330.0px;");
		
		// validDateRange
		validDateRange = new Label();
		validDateRange.setEnabled(false);
		validDateRange.setImmediate(false);
		validDateRange.setVisible(false);
		validDateRange.setWidth("-1px");
		validDateRange.setHeight("-1px");
		validDateRange.setValue("No events in date range");
		top_sean_area.addComponent(validDateRange,
				"top:187.0px;left:737.0px;");


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
		comboBox_7.setEnabled(false);
		comboBox_7.setImmediate(false);
		comboBox_7.setWidth("300px");
		comboBox_7.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_7, "top:36.0px;left:20.0px;");

		// comboBox_1
		comboBox_1 = new ComboBox();
		comboBox_1.setCaption("Which Workflow do you want to use?");
		comboBox_1.setEnabled(false);
		comboBox_1.setImmediate(false);
		comboBox_1.setWidth("-1px");
		comboBox_1.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_1, "top:36.0px;left:340.0px;");

		// comboBox_8
		comboBox_8 = new ComboBox();
		comboBox_8.setCaption("How do you want to rank your result");
		comboBox_8.setEnabled(false);
		comboBox_8.setImmediate(false);
		comboBox_8.setWidth("200px");
		comboBox_8.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_8, "top:86.0px;left:20.0px;");

		// comboBox_9
		comboBox_9 = new ComboBox();
		comboBox_9.setCaption("Which HEC Catalogues do you want to use ?");
		comboBox_9.setEnabled(false);
		comboBox_9.setImmediate(false);
		comboBox_9.setWidth("-1px");
		comboBox_9.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_9, "top:86.0px;left:240.0px;");

		// comboBox_10
		comboBox_10 = new ComboBox();
		comboBox_10.setCaption("Which workflow do you want to use ?");
		comboBox_10.setEnabled(false);
		comboBox_10.setImmediate(false);
		comboBox_10.setWidth("-1px");
		comboBox_10.setHeight("-1px");
		absoluteLayout_3.addComponent(comboBox_10, "top:126.0px;left:240.0px;");

		// button_5
		button_5 = new Button();
		button_5.setCaption("Set Propagation Model Application");
		button_5.setEnabled(false);
		button_5.setImmediate(true);
		button_5.setWidth("-1px");
		button_5.setHeight("-1px");
		absoluteLayout_3.addComponent(button_5, "top:36.0px;left:523.0px;");

		// button_6
		button_6 = new Button();
		button_6.setCaption("Run !");
		button_6.setEnabled(false);
		button_6.setImmediate(true);
		button_6.setWidth("-1px");
		button_6.setHeight("-1px");
		absoluteLayout_3.addComponent(button_6, "top:86.0px;left:523.0px;");

		return absoluteLayout_3;
	}
	private void runModel(String startTime, String startPosit, String startAngle, String startSpeed, String speedError) throws Exception{
		propagationModel theModel = new propagationModel(startTime, startPosit, startAngle, startSpeed, speedError);
		theModel.sendParameter();
		String resultsTable = theModel.getTable();

		VOParser theParser = new VOParser();
		theParser.parse(resultsTable);

		String[] tempParameters = theParser.getParameterArray();
		String[][] tempEvents = theParser.getEventArray();

		String[][] tempArray = new String[tempEvents.length + results.length][tempParameters.length];
		//rewrite results parameters
		for(int i = 0; i < tempParameters.length;i++){
			resultsParameters[i] = tempParameters[i];
		}
		//copy previous results
		int i;
		for(i = 0; i < results.length; i++){
			for(int j = 0; j < resultsParameters.length;j++){
				tempArray[i][j] = results[i][j];
			}
		}
		//add new results
		for(int temp = 0;temp<tempEvents.length;temp++){
			for(int j=0; j < resultsParameters.length;j++){
				tempArray[i][j] = tempEvents[temp][j];
			}
			i++;
		}
		//copy temp array to results array
		results = new String[tempArray.length][resultsParameters.length];
		for(int x = 0; x < tempArray.length;x++){
			for(int y = 0; y < resultsParameters.length;y++){
				results[x][y] = tempArray[x][y];
			}
		}
	}
	private void drawResults(){

	}
} 
