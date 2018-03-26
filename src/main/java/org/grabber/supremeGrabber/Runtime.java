package org.grabber.supremeGrabber;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.eclipse.swt.widgets.Display;

public class Runtime {
	public Runtime() {
	}

	public static void main(String[] args) throws InterruptedException, ParseException {
		String access = JOptionPane.showInputDialog("Enter provided Access Code");
		if (access == null) {
			////System.out.print("Exiting...");
		} else {
			String accessHash = Integer.toString(access.hashCode());
			try {
				////System.out.println("Attempting to contact verification Server...");
				URL url = new URL("http://www.pitt.edu/~alr203/supreme.htm");
				////System.out.println("Opening Scanner Stream...");
				Scanner accessScanner = new Scanner(url.openStream());
				@SuppressWarnings("unused")
				boolean verified = false;
				////System.out.println("Verifying access code...");
				if (accessScanner.next().equalsIgnoreCase(accessHash)) {
					verified = true;
					////System.out.println("Verified");
					Gui window = new Gui();
					window.setBlockOnOpen(true);
					window.open();
					Display.getCurrent().dispose();
					Backend Backend = new Backend(window.getName(), window.getEmail(), window.getPhone(),
							window.getAddress(), window.getZip(), window.getCard(), window.getMonth(), window.getYear(),
							window.getCcv(), window.getDesc(), window.getSize(), window.getTime());
					if (window.save) {
						Backend.RunGrabber();

					}
					else {
						////System.out.print("quit");
					}

				} else if (!accessScanner.next().equalsIgnoreCase(accessHash)) {
					////System.out.println("Invalid Credentials. Contact Rybeck.Abraham@gmail.com");
					accessScanner.close();
				} else {
					////System.out.println("the Program Quit unexpectedly");
					accessScanner.close();
				}
			} catch (IOException ex) {
				////System.out.println("unable to  contact verification Server. contact rybeck.abraham@gmail.com");
			}
		}
	}
}
