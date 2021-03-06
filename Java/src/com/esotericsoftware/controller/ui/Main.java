
package com.esotericsoftware.controller.ui;

import static com.esotericsoftware.minlog.Log.*;

import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.java.games.input.ControllerEnvironment;

import com.esotericsoftware.controller.ui.swing.UI;
import com.esotericsoftware.controller.util.MultiplexOutputStream;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.minlog.LogHandler;

/**
 * Main class for the UI.
 */
public class Main {
	public static void main (String[] args) throws Exception {
		FileOutputStream logOutput = new FileOutputStream("log.txt");
		System.setOut(new PrintStream(new MultiplexOutputStream(System.out, logOutput), true));
		System.setErr(new PrintStream(new MultiplexOutputStream(System.err, logOutput), true));

		Logger jinputLogger = Logger.getLogger(ControllerEnvironment.class.getName());
		jinputLogger.setUseParentHandlers(false);
		jinputLogger.setLevel(Level.ALL);
		jinputLogger.addHandler(new LogHandler("jinput"));

		Log.info(DateFormat.getTimeInstance(DateFormat.LONG).format(new Date()) + ", "
			+ DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
		if (INFO) {
			info("Controller v" + UI.version + ", Java " + System.getProperty("java.version") + " "
				+ System.getProperty("java.vendor") + " on " + System.getProperty("os.name") + " " + System.getProperty("os.version")
				+ " " + System.getProperty("os.arch"));
		}

		EventQueue.invokeLater(new Runnable() {
			public void run () {
				new UI();
			}
		});
	}
}
