package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import entities.RefAccount;
import entities.RefCompany;
import entities.RefUser;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Common static methods
 * @author vdidukh
 * @since CORE_1
 * @id
 *
 */
public class Common {
	
	private static RefAccount registeredAccount = null;
	private static RefUser registeredUser = null;
	private static RefCompany registeredCompany = null;
	private static Common common = new Common();
	private Common() {}
	
	/**
	 * Show the frame on the center of the screen
	 * @param frame form or dialog to center 
	 */
	public void showFrame(Window frame) {
		int screenWidth = 0, screenHeight = 0;
		
		GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice graphicsDevice : screenDevices) {
            screenWidth = graphicsDevice.getDefaultConfiguration().getBounds().width;
            screenHeight = graphicsDevice.getDefaultConfiguration().getBounds().height;
        }
		
        Rectangle r = frame.getBounds();
		
		int frameWidth = r.width, frameHeight = r.height;
		int posX = (screenWidth - frameWidth) / 2;
		int posY = (screenHeight - frameHeight) / 2 - 40;
		
		frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		frame.setBounds(posX, posY, r.width, r.height);
		
		frame.setVisible(true);
	}
	
	/**
	 * Create frame for panel and show it
	 * @param panel panel to show
	 * @param title title of window
	 */
	public void makeFrame(JPanel panel, String title) {
		Rectangle r = panel.getBounds();
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(r.x, r.y, r.width, r.height);
		frame.add(panel);
		
		showFrame(frame);
	}
	
	/**
	 * Try to convert string variable to int value
	 * @param s string to convert
	 * @return int value or zero
	 */
	public int parseInt(String s) {
		int n = 0;
		try {
			n = Integer.parseInt(s);
		} catch (Exception e) {}
		return n;
	}
	
	/**
	 * Try to convert string variable to float value
	 * @param s string to convert
	 * @return float value or zero
	 */
	public float parseFloat(String s) {
		float f = 0;
		try {
			f = Float.parseFloat(s);
		} catch (Exception e) {}
		return f;
	}
	
	/**
	 * Information message
	 * @param cmp component to link message (or null)
	 * @param message text of the message
	 */
	public void showInfo(Component cmp, String message) {
		JOptionPane.showMessageDialog(cmp, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Error message
	 * @param cmp component to link message (or null)
	 * @param message text of the message
	 */
	public void showErrorMessage(Component cmp, String message) {
		JOptionPane.showMessageDialog(cmp, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Yes-No dialog
	 * @param cmp component to link message (or null)
	 * @param message text of the message
	 * @param title title of the message
	 * @return result of choice
	 */
	public int showConfirmDialog(Component cmp, String message, String title) {
		Object[] options = { "Yes", "No" };
        return JOptionPane.showOptionDialog(
        		cmp, message, title,
        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        		null, options, options[1]
        );
	}
	
	/**
	 * Round float value
	 * @param value float value to round
	 * @param digits number of digits
	 * @return rounded value
	 */
	public static float Round(float value, int digits) {
		return new BigDecimal(value).setScale(digits, RoundingMode.HALF_UP).floatValue();
	}
	
	/**
	 * For text fields with numeric values
	 * (block illegel symbols)
	 * @param filterType type of restriction (can or cannot input numbers with point)
	 * @return DocumentFilter to use with the text fields 
	 */
	private static DocumentFilter getTextFilter(String filterType) {
		final String bannedSymbols = filterType.toUpperCase().equals("FLOAT") ? "[^0123456789.]" : "[^0123456789]";
		
		return new DocumentFilter() {
		    @Override
		    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		        string = string.replaceAll(bannedSymbols, "");
		        super.insertString(fb, offset, string, attr);
		    }

		    @Override
		    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		        text = text.replaceAll(bannedSymbols, "");
		        super.replace(fb, offset, length, text, attrs);
		    }
		};
		
	}
	
	/**
	 * Set restrictions to the text fields
	 * @param field text field to set restriction 
	 * @param filterType type of restriction (can or cannot input numbers with point)
	 */
	protected static void setRestrictions(JTextField field, String filterType) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(getTextFilter(filterType));
	}
	
	/**
	 * Covert float value to string with decoration 
	 * @param f value to decorate
	 * @return string with decorated number
	 */
	public static String clip(float f) {
		String res = String.format("%.6f", f);
		
		for (int i = 0; i < 6; i++)
			if ( res.endsWith("0") )
				res = res.substring(0, res.length() - 1);
			
		if ( res.endsWith(",") )
			res = res.substring(0, res.length() - 1);
		
		res = res.replace(",", ".");
		res = res.replace(" ", "");
		res = res.equals("-0") ? "0" : res;
		
		return res;
	}

	public static RefAccount getRegisteredAccount() {
		return registeredAccount;
	}
	
	public static void setRegisteredAccount(RefAccount account) {
		registeredAccount = account;
	}
	
	public static RefUser getRegisteredUser() {
		return registeredUser;
	}

	public static void setRegisteredUser(RefUser registeredUser) {
		Common.registeredUser = registeredUser;
	}
	
	public static RefCompany getRegisteredCompany() {
		return registeredCompany;
	}
	
	public static void setRegisteredCompany(RefCompany company) {
		registeredCompany = company;
	}
	
	public static Common getCommonInstance() {
		return common;
	}

}