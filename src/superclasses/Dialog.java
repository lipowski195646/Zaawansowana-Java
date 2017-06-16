package superclasses;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Abstract class - the base for the login dialog
 * and other abstract dialog classes
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class Dialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	protected JPanel panelFields, panelButtons;
	protected JButton btnOK, btnCancel; 
	
	/**
	 * Create skeleton for dialog window
	 * The subclasses have access to the panels and buttons 
	 */
	public Dialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		
		contentPane = (JPanel) getContentPane(); 
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelFields = new JPanel();
		panelFields.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelFields.setLayout(null);
		contentPane.add(panelFields, BorderLayout.CENTER);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		btnOK = new JButton("OK");
		panelButtons.add(btnOK);
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		
		getRootPane().setDefaultButton(btnOK);
	}
	
}