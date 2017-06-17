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
public class FloatTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Decorate, set initial value and restrictions 
	 */
	public FloatTextField() {
		super();
		setHorizontalAlignment(SwingConstants.RIGHT);
		setColumns(10);
		Common.setRestrictions(this, "float");
		this.setText("0.0");
		addListeners(this);
	}
	
	/**
	 * Get converted value of the field
	 * @return float value
	 */
	public float getFloat() {
		return Common.getCommonInstance().parseFloat(this.getText().trim());
	}
	
	/**
	 * For listeners - try to convert field the value to the numeric value
	 * @param res text field to link the listeners
	 */
	private void checkValue(FloatTextField res) {
		res.setText( "" + res.getFloat() );
	}
	
	private void addListeners(FloatTextField res) {
		
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