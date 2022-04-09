package br.edu.ifsp.lp3a5.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class JanelaStartStop extends JPanel implements ILogPanel {
	
	final static Logger logger = Logger.getLogger("logging.handler");
	
	private ComecaAction comeca = new ComecaAction();
	private AcabaAction acaba = new AcabaAction();
	private JButton button = new JButton(comeca);
	private JTextArea textArea = new JTextArea();
	private ICicloDeVida objGerenciavel;
	final JScrollPane scrollPane;

	public JanelaStartStop(ICicloDeVida objGerenciavel) {
		this.objGerenciavel = objGerenciavel;
		JPanel btnPanel = new JPanel();
		btnPanel.add(button);

		textArea = new javax.swing.JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setCaretColor(Color.GREEN);
		textArea.setForeground(Color.GREEN);
		textArea.setColumns(80);
		textArea.setRows(40);
		
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setViewportView(textArea);
		scrollPane.setAutoscrolls(true);

		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.PAGE_END);
	}

	public void showInfo(String data) {
		textArea.append(data);
		this.validate();
	}

	private class ComecaAction extends AbstractAction {
		public ComecaAction() {
			super("Start");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			objGerenciavel.inicia();
			button.setAction(acaba);
		}
	}

	private class AcabaAction extends AbstractAction {
		public AcabaAction() {
			super("Stop");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			objGerenciavel.acaba();
			button.setAction(comeca);
		}
	}
}