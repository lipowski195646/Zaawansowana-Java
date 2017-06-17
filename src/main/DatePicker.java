package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * Calendar for dialogs
 * (wrapper for JDatePickerImpl)
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public class DatePicker extends JDatePickerImpl {
	private static final long serialVersionUID = 1L;
	
	private static final Properties p = new Properties();
	
	/**
	 * Invoke the constructor of the external component
	 */
	public DatePicker() {
		super(getDatePanel(), getDateLabelFormatter());
	}
	
	/**
	 * Create panel of calendar
	 * @return panel of calendar
	 */
	private static JDatePanelImpl getDatePanel() {
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		return datePanel;
	}

	/**
	 * Get formatter of the date
	 * @return formatter of the date
	 */
	private static AbstractFormatter getDateLabelFormatter() {
		return new AbstractFormatter() {
			private static final long serialVersionUID = 1L;
			private SimpleDateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_PATTERN);

			@Override
			public Object stringToValue(String text) throws ParseException {
				return dateFormatter.parseObject(text);
			}

			@Override
			 public String valueToString(Object value) throws ParseException {
			     if (value != null) {
			         Calendar cal = (Calendar) value;
			         return dateFormatter.format(cal.getTime());
			     }
			     return "";
			}
		};
	}
	
	/**
	 * Set initial date
	 * @param dt date to set
	 */
	public void setDate(Date dt) {
		if (dt == null) return;
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        
        this.getModel().setSelected(true);
        this.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * Get selected date
	 * @return selected date
	 */
	public Date getDate() {
		UtilDateModel model = (UtilDateModel) this.getModel();
		if (!model.isSelected()) return null;

		GregorianCalendar calendar = new GregorianCalendar(model.getYear(), model.getMonth(), model.getDay());
		return calendar.getTime();
	}
	
	/**
	 * For set read only flag 
	 * @param enabled read only flag
	 */
	public void setEditable(boolean enabled) {
		this.getComponent(1).setEnabled(enabled);
	}

}