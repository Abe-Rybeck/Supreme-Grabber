package org.grabber.supremeGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Gui extends ApplicationWindow{
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text nameEntry;
	private Text phoneEntry;
	private Text addressEntry;
	private Text emailEntry;
	private Text zipEntry;
	private Text cardEntry;
	private Text ccvEntry;
	private Text monthEntry;
	private Text yearEntry;
	public String name;
	public String email;
	public String phone;
	public String address;
	public String zip;
	public String card;
	public String month;
	public String year;
	public String ccv;
	public String time;
	public String desc;
	public String size;
	static ArrayList<String> saveList;
	public boolean save = false;
	private Text sizeEntry;
	private Text timeEntry;

	public Gui() {
		super(null);
		setShellStyle(2128);
		createActions();
		addCoolBar(8388608);
		addMenuBar();
		addStatusLine();
	}





	protected Control createContents(Composite parent)
	{
		try
		{
			Scanner saveFile = new Scanner(new File("src//main/resources//saved.txt")).useDelimiter(",\\s*\\r\\n");
			String[] string = new String[0];
			String preCheck = saveFile.next();
			string = preCheck.split("@@");
			saveList = new ArrayList(Arrays.asList(string));
			saveFile.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Composite container = new Composite(parent, 0);
		container.setForeground(SWTResourceManager.getColor(1));
		container.setBackgroundMode(1);
		container.setBackground(SWTResourceManager.getColor(255, 0, 0));
		container.setLayout(null);
		Label label = new Label(container, 0);
		label.setBounds(67, 8, 0, 15);
		formToolkit.adapt(label, true, true);
		Label label_1 = new Label(container, 0);
		label_1.setBounds(113, 8, 0, 15);
		formToolkit.adapt(label_1, true, true);
		Label label_2 = new Label(container, 0);
		label_2.setBounds(156, 8, 0, 15);
		formToolkit.adapt(label_2, true, true);
		Label label_3 = new Label(container, 0);
		label_3.setBounds(179, 8, 0, 15);
		formToolkit.adapt(label_3, true, true);

		nameEntry = new Text(container, 2048);
		nameEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		nameEntry.setText((String)saveList.get(0));




		nameEntry.setBounds(126, 39, 139, 21);

		Label lblName = new Label(container, 0);
		lblName.setForeground(SWTResourceManager.getColor(1));
		lblName.setBounds(4, 42, 86, 25);
		lblName.setText("Full Name:");

		phoneEntry = new Text(container, 2048);
		phoneEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		phoneEntry.setText((String)saveList.get(2));
		phoneEntry.setBounds(126, 70, 139, 21);
		Label label_4 = new Label(container, 0);
		label_4.setBounds(236, 34, 0, 15);
		formToolkit.adapt(label_4, true, true);

		addressEntry = new Text(container, 2048);
		addressEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		addressEntry.setText((String)saveList.get(3));
		addressEntry.setBounds(126, 132, 139, 21);
		Label label_5 = new Label(container, 0);
		label_5.setBounds(236, 60, 0, 15);
		formToolkit.adapt(label_5, true, true);

		Label lblTel = new Label(container, 0);
		lblTel.setForeground(SWTResourceManager.getColor(1));
		lblTel.setBounds(4, 73, 86, 25);
		lblTel.setText("Phone:");
		Label label_6 = new Label(container, 0);
		label_6.setBounds(156, 83, 0, 15);
		formToolkit.adapt(label_6, true, true);
		Label label_7 = new Label(container, 0);
		label_7.setBounds(179, 83, 0, 15);
		formToolkit.adapt(label_7, true, true);
		Label label_8 = new Label(container, 0);
		label_8.setBounds(236, 83, 0, 15);
		formToolkit.adapt(label_8, true, true);
		Label label_9 = new Label(container, 0);
		label_9.setBounds(156, 103, 0, 15);
		formToolkit.adapt(label_9, true, true);
		Label label_10 = new Label(container, 0);
		label_10.setBounds(179, 103, 0, 15);
		formToolkit.adapt(label_10, true, true);
		Label label_11 = new Label(container, 0);
		label_11.setBounds(236, 103, 0, 15);
		formToolkit.adapt(label_11, true, true);

		Label lblMonth = new Label(container, 0);
		lblMonth.setForeground(SWTResourceManager.getColor(1));
		lblMonth.setBounds(4, 297, 39, 15);
		lblMonth.setText("Month:");

		emailEntry = formToolkit.createText(container, "New Text", 0);
		emailEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		emailEntry.setText((String)saveList.get(1));
		emailEntry.setBounds(126, 100, 139, 21);

		zipEntry = formToolkit.createText(container, "New Text", 0);
		zipEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		zipEntry.setText((String)saveList.get(4));
		zipEntry.setBounds(126, 170, 139, 21);

		Label label_12 = new Label(container, 0);
		label_12.setBounds(156, 149, 0, 15);
		formToolkit.adapt(label_12, true, true);

		Label label_13 = new Label(container, 0);
		label_13.setBounds(179, 149, 0, 15);
		formToolkit.adapt(label_13, true, true);

		Label label_14 = new Label(container, 0);
		label_14.setBounds(236, 149, 0, 15);
		formToolkit.adapt(label_14, true, true);

		Label lblCardNum = new Label(container, 0);
		lblCardNum.setForeground(SWTResourceManager.getColor(1));
		lblCardNum.setBounds(4, 266, 75, 15);
		lblCardNum.setText("Card Number:");

		Label label_15 = new Label(container, 0);
		label_15.setBounds(236, 169, 0, 15);
		formToolkit.adapt(label_15, true, true);

		Label label_16 = new Label(container, 0);
		label_16.setBounds(5, 192, 0, 15);
		formToolkit.adapt(label_16, true, true);

		Label label_17 = new Label(container, 0);
		label_17.setBounds(67, 192, 0, 15);
		formToolkit.adapt(label_17, true, true);

		cardEntry = formToolkit.createText(container, "New Text", 0);
		cardEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		cardEntry.setText((String)saveList.get(5));
		cardEntry.setBounds(126, 263, 144, 21);

		Label lblYear = new Label(container, 0);
		lblYear.setForeground(SWTResourceManager.getColor(1));
		lblYear.setBounds(4, 330, 26, 15);
		lblYear.setText("Year:");

		Label label_18 = new Label(container, 0);
		label_18.setBounds(67, 218, 0, 15);
		formToolkit.adapt(label_18, true, true);

		monthEntry = formToolkit.createText(container, "New Text", 0);
		monthEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		monthEntry.setText((String)saveList.get(6));
		monthEntry.setBounds(126, 294, 144, 21);

		Label lblEmail = new Label(container, 0);
		lblEmail.setForeground(SWTResourceManager.getColor(1));
		lblEmail.setBounds(4, 104, 86, 25);
		lblEmail.setText("E-Mail:");

		Label lblAddress = new Label(container, 0);
		lblAddress.setForeground(SWTResourceManager.getColor(1));
		lblAddress.setBounds(4, 135, 45, 25);
		lblAddress.setText("Address:");

		ccvEntry = formToolkit.createText(container, "New Text", 0);
		ccvEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		ccvEntry.setText((String)saveList.get(8));
		ccvEntry.setBounds(126, 361, 144, 21);

		CLabel lblZip = new CLabel(container, 0);
		lblZip.setForeground(SWTResourceManager.getColor(1));
		lblZip.setBounds(4, 166, 20, 25);
		lblZip.setText("Zip:");

		Text descEntry = new Text(container, 2048);
		descEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		descEntry.setText((String)saveList.get(9));
		descEntry.setBounds(373, 39, 119, 21);

		Label lblSize = new Label(container, 0);
		lblSize.setForeground(SWTResourceManager.getColor(1));
		lblSize.setBounds(281, 83, 39, 15);
		lblSize.setText("Size:");

		sizeEntry = new Text(container, 2048);
		sizeEntry.setForeground(SWTResourceManager.getColor(3));
		sizeEntry.setText((String)saveList.get(10));
		sizeEntry.setBounds(373, 82, 119, 21);
		formToolkit.adapt(sizeEntry, true, true);


		Button BtnReady = formToolkit.createButton(container, "Save and Run", 0);
		BtnReady.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				save = true;
				name = nameEntry.getText();
				email = emailEntry.getText();
				phone = phoneEntry.getText();
				address = addressEntry.getText();
				zip = zipEntry.getText();
				card = cardEntry.getText();
				month = monthEntry.getText();
				year = yearEntry.getText();
				ccv = ccvEntry.getText();
				time = timeEntry.getText();
				desc = descEntry.getText();
				size = sizeEntry.getText();
				close();

			}
		});
		BtnReady.setForeground(SWTResourceManager.getColor(0, 0, 0));
		BtnReady.setBackground(SWTResourceManager.getColor(1));
		BtnReady.setBounds(329, 341, 108, 51);

		Label lblCcv = new Label(container, 0);
		lblCcv.setForeground(SWTResourceManager.getColor(1));
		lblCcv.setBounds(4, 361, 26, 15);
		lblCcv.setText("CCV:");

		yearEntry = formToolkit.createText(container, "New Text", 0);
		yearEntry.setForeground(SWTResourceManager.getColor(0, 0, 0));
		yearEntry.setText((String)saveList.get(7));
		yearEntry.setBounds(126, 327, 144, 21);

		Label lblCreditCardInfo = new Label(container, 0);
		lblCreditCardInfo.setForeground(SWTResourceManager.getColor(1));
		lblCreditCardInfo.setFont(SWTResourceManager.getFont("Segoe UI", 12, 1));
		lblCreditCardInfo.setText("Credit Card Info:");
		lblCreditCardInfo.setBounds(4, 218, 136, 21);

		Label lblBillingInfo = new Label(container, 0);
		lblBillingInfo.setForeground(SWTResourceManager.getColor(1));
		lblBillingInfo.setText("Billing Info:");
		lblBillingInfo.setFont(SWTResourceManager.getFont("Segoe UI", 12, 1));
		lblBillingInfo.setBounds(4, 11, 108, 25);

		Label lblKeyWord = new Label(container, 0);
		lblKeyWord.setForeground(SWTResourceManager.getColor(1));
		lblKeyWord.setBounds(279, 42, 64, 15);
		lblKeyWord.setText("KeyWord(s):");

		Label lblCopyright = new Label(container, 0);
		lblCopyright.setFont(SWTResourceManager.getFont("Segoe UI Light", 9, 0));
		lblCopyright.setBounds(4, 398, 401, 75);
		lblCopyright.setText("Copyright (C) Abe Rybeck,\r\nAll Rights Reserved,\r\nUnauthorized copying of this software, via any medium is strictly prohibited.\r\n<Rybeck.Abraham@gmail.com>,\r\n 11/25/2017\r\n");

		Label lblTime = new Label(container, 0);
		lblTime.setForeground(SWTResourceManager.getColor(1));
		lblTime.setBounds(281, 281, 86, 15);
		lblTime.setText("run time:");

		timeEntry = new Text(container, 2048);
		timeEntry.setForeground(SWTResourceManager.getColor(3));
		timeEntry.setBounds(373, 278, 119, 21);
		timeEntry.setText((String)saveList.get(11));
		formToolkit.adapt(timeEntry, true, true);

		Label lblItemInfo = new Label(container, 0);
		lblItemInfo.setForeground(SWTResourceManager.getColor(1));
		lblItemInfo.setFont(SWTResourceManager.getFont("Segoe UI", 12, 1));
		lblItemInfo.setBounds(273, 11, 86, 22);
		lblItemInfo.setText("Item Info:");

		Label lblExSize = new Label(container, 0);
		lblExSize.setForeground(SWTResourceManager.getColor(1));
		lblExSize.setFont(SWTResourceManager.getFont("Segoe UI Light", 9, 2));
		lblExSize.setBounds(373, 103, 119, 15);
		lblExSize.setText("ex: Large, 9, 10.5, Multi");

		Label lblExTime = new Label(container, 0);
		lblExTime.setForeground(SWTResourceManager.getColor(1));
		lblExTime.setFont(SWTResourceManager.getFont("Segoe UI Light", 9, 2));
		lblExTime.setBounds(373, 305, 86, 15);
		lblExTime.setText("ex: 09:12, 14:59");

		Label lblExKey = new Label(container, 0);
		lblExKey.setForeground(SWTResourceManager.getColor(1));
		lblExKey.setFont(SWTResourceManager.getFont("Segoe UI Light", 9, 2));
		lblExKey.setBounds(372, 60, 108, 13);
		lblExKey.setText("ex: Puffy Jacket, Hat");

		Label label_19 = new Label(container, 514);
		label_19.setBounds(273, 39, 2, 343);
		formToolkit.adapt(label_19, true, true);

		Label label_21 = new Label(container, 258);
		label_21.setBounds(146, 230, 338, 2);
		formToolkit.adapt(label_21, true, true);
		return container;
	}

	private void createActions() {

	}

	protected Point getInitialSize()
	{
		return new Point(520, 539);
	}

	public String getName() {
		return name; 
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() { 
		return address; 
	}

	public String getPhone() {
		return phone;
	}

	public String getZip() {
		return zip; 
	}

	public String getCard() {
		return card;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getCcv() { 
		return ccv; 
	}

	public String getDesc() {
		return desc;
	}

	public String getSize() {
		return size; }

	public String getTime() {
		return time;
	}
}