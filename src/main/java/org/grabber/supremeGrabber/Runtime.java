package org.grabber.supremeGrabber;

import java.io.IOException;
import java.text.ParseException;
import org.eclipse.swt.widgets.Display;

public class Runtime {
	public static void main(String[] args) throws InterruptedException, ParseException, IOException {

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
	}
}