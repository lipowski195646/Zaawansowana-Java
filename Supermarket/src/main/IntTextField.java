package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Text field with restrictions for dialogs
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public class IntTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Decorate, set initial value and restrictions
	 */
	public IntTextField() {
		super();
		setHorizontalAlignment(SwingConstants.RIGHT);
		setColumns(10);
		Common.setRestrictions(this, "int");
		this.setText("0");
		addListeners(this);
	}
	
	/**
	 * Get converted value of the field
	 * @return int value
	 */
	public int getInt() {
		return Common.getCommonInstance().parseInt(this.getText().trim());
	}
	
	/**
	 * For listeners - try to convert field the value to the numeric value
	 * @param res text field to link the listeners
	 */
	private void checkValue(IntTextField res) {
		res.setText( "" + res.getInt() );
	}
	
	private void addListeners(IntTextField res) {
		
		res.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				res.selectAll();
			}
		});
		
		res.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkValue(res);
			}
		});
		
		res.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkValue(res);
			}
		});
	
	}

}