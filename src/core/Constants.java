package main;

import javax.swing.JOptionPane;

/**
 * Global costansts 
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public interface Constants {
	final int YES = JOptionPane.YES_OPTION;
	
	final String[] ROLES = {"Admin", "Sale", "Stock", "Manager", "Delivery"};
	final String ROLEFRAMES_PACKAGE = "roleframes.";
	final String PRINTFORMS_PACKAGE = "printforms.";
	
	final String[] UNITS = {"pc", "kg", "ton", "m", "l"};
	
	final String DATE_PATTERN = "dd.MM.yyyy";
	//final String DATE_PATTERN = "yyyy-MM-dd";
}
