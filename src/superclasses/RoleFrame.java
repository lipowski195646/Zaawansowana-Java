package superclasses;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import main.Common;
import main.Constants;
import main.LoginDialog;

/**
 * Abstract class - the base for the interface forms
 * (package "roleframes")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class RoleFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	protected JMenuBar menuBar;
	protected JPanel contentPane, dataPanel;
	
	/**
	 * Create skeleton of the interface form
	 */
	public RoleFrame() {
		setResizable(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				closeFrame(arg0);
			}
		});
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1000, 700);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setActionCommand("LOGOUT");
		mntmLogout.addActionListener(this);
		mnFile.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame(e);
			}
		});
		mnFile.add(mntmExit);
		
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EtchedBorder());
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dataPanel = new JPanel();
		contentPane.add(dataPanel, BorderLayout.CENTER);
		dataPanel.setLayout(new BorderLayout(0, 0));
		
	}
	
	/**
	 * Action on exit
	 */
	private void closeFrame(java.awt.AWTEvent evt) {
		if (Common.getCommonInstance().showConfirmDialog(this, "You really want to exit?", "Exit") == Constants.YES) 
			System.exit(0);
    }
	
	/**
	 * Title of the form - [custom title] + company info + [user info] + account info 
	 * @param title custom title
	 * 
	 */
	protected void showTitle(String title) {
		//TODO: refaktor
	}
	
	/**
	 * Show the given panel inside the form
	 * @param listPanel panel to show
	 */
	protected void showListPanel() {
		//TODO: refaktor
		dataPanel.removeAll();
		SwingUtilities.updateComponentTreeUI(dataPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "LOGOUT":
			dispose();
			Common.getCommonInstance().showFrame(new LoginDialog());
			break;
		default:
			break;
		}
	}
}
