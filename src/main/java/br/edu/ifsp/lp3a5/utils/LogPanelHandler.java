package br.edu.ifsp.lp3a5.utils;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;


/**
 * http://www.java2s.com/Code/Java/Language-Basics/WindowHandlerdisplaylogmessageinawindowJFrame.htm
 */
public class LogPanelHandler extends Handler {
	private ILogPanel logPanel;

	private SimpleFormatter formatter = new SimpleFormatter();

	private static LogPanelHandler handler = null;
	
	static {
	      System.setProperty("java.util.logging.SimpleFormatter.format",
	              "[%1$tF %1$tT] [%4$-7s] %5$s %n");
	}

	private LogPanelHandler(ILogPanel panel) {
		final LogManager manager = LogManager.getLogManager();
		final String className = this.getClass().getName();
		final String level = manager.getProperty(className + ".level");
		this.setLevel(level != null ? Level.parse(level) : Level.INFO);
		this.setFormatter(formatter);
		this.logPanel = panel;
	}

	public static synchronized LogPanelHandler getInstance(ILogPanel panel) {
		if (handler == null) {
			handler = new LogPanelHandler( panel );
		}
		return handler;
	}

	public synchronized void publish(LogRecord record) {
		String message = null;
		if (!isLoggable(record))
			return;
		message = getFormatter().format(record);
		logPanel.showInfo(message);
	}

	public void close() {
	}

	public void flush() {
	}
}
