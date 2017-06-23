package editdialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import datamanagers.DocSalaryItemDM;
import datamanagers.RefUserDM;
import superclasses.EditDialog;
import superclasses.EditDialogDoc;
import superclasses.Entity;
import entities.DocSalaryItem;
import entities.RefUser;
import main.Common;
import main.FloatTextField;
import main.IntTextField;

public class DocSalaryItemED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private IntTextField txtWorkDays;
	private FloatTextField txtSalaryRef, txtSalaryCalc, txtPremiaRef, txtPremiaCalc, txtSumma;
	private JComboBox<Entity> comboUser;
	private boolean fillUserData = true;
	
	private int maxDaysAll;
	private int maxDaysWork;
	
	public DocSalaryItemED(Entity ent, EditDialogDoc parentDialog) {
		super(parentDialog);
		
		setTitle("Add / edit salary item");
		setBounds(100, 100, 325, 315);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(12, 35, 60, 25);
		panelFields.add(lblUser);
		
		JLabel lblSalaryRef = new JLabel("Salary (ref.):");
		lblSalaryRef.setBounds(12, 65, 70, 25);
		panelFields.add(lblSalaryRef);
		
		JLabel lblWorkDays = new JLabel("Work days:");
		lblWorkDays.setBounds(12, 95, 70, 25);
		panelFields.add(lblWorkDays);
		
		JLabel lblSalaryCalc = new JLabel("Salary:");
		lblSalaryCalc.setBounds(12, 125, 70, 25);
		panelFields.add(lblSalaryCalc);
		
		JLabel lblPremiaRef = new JLabel("Premia (%):");
		lblPremiaRef.setBounds(12, 155, 70, 25);
		panelFields.add(lblPremiaRef);
		
		JLabel lblPremiaCalc = new JLabel("Premia:");
		lblPremiaCalc.setBounds(12, 185, 70, 25);
		panelFields.add(lblPremiaCalc);
		
		JLabel lblSumma = new JLabel("Summa:");
		lblSumma.setBounds(12, 215, 70, 25);
		panelFields.add(lblSumma);
		
		txtId.setBounds(100, 5, 50, 25);
		
		comboUser = new JComboBox<Entity>();
		comboUser.setBounds(100, 35, 200, 25);
		panelFields.add(comboUser);
		
		comboUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RefUser user = (RefUser) comboUser.getSelectedItem();
				txtSalaryRef.setText("" + user.getSalary());
				txtPremiaRef.setText("" + user.getPremia());
				
				if (fillUserData) {
		            calcSumma();
				};
			}
		});
		
		txtSalaryRef = new FloatTextField();
		txtSalaryRef.setBounds(100, 65, 80, 25);
		txtSalaryRef.setEditable(false);
		panelFields.add(txtSalaryRef);
		
		txtWorkDays = new IntTextField();
		txtWorkDays.setBounds(100, 95, 80, 25);
		panelFields.add(txtWorkDays);
		
		txtWorkDays.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkWorkDays();
				calcSumma();
			}
		});
		txtWorkDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkWorkDays();
				calcSumma();
			}
		});
		
		txtSalaryCalc = new FloatTextField();
		txtSalaryCalc.setBounds(100, 125, 80, 25);
		txtSalaryCalc.setEditable(false);
		panelFields.add(txtSalaryCalc);
		
		txtPremiaRef = new FloatTextField();
		txtPremiaRef.setBounds(100, 155, 80, 25);
		panelFields.add(txtPremiaRef);
		
		txtPremiaRef.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				calcSumma();
			}
		});
		txtPremiaRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcSumma();
			}
		});
		
		txtPremiaCalc = new FloatTextField();
		txtPremiaCalc.setBounds(100, 185, 80, 25);
		txtPremiaCalc.setEditable(false);
		panelFields.add(txtPremiaCalc);
		
		txtSumma = new FloatTextField();
		txtSumma.setBounds(100, 215, 80, 25);
		txtSumma.setEditable(false);
		panelFields.add(txtSumma);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		DocSalaryItem salaryItem = (DocSalaryItem) ent;
		int userId = 0;
		
		DocSalaryED parentDialog = (DocSalaryED) super.parentDialog;
		maxDaysAll = parentDialog.txtDaysAll.getInt();
		maxDaysWork = parentDialog.txtDaysWork.getInt(); 
		
        if (salaryItem != null) {
            id = salaryItem.getId();
            userId = salaryItem.getUserId();
            
            super.txtId.setText("" + id);
            txtSalaryRef.setText("" + salaryItem.getSalaryRef());
            txtWorkDays.setText("" + salaryItem.getWorkDays());
            txtSalaryCalc.setText("" + salaryItem.getSalaryCalc());
            txtPremiaRef.setText("" + salaryItem.getPremiaRef());
            txtPremiaCalc.setText("" + salaryItem.getPremiaCalc());
            txtSumma.setText("" + salaryItem.getSumma());
            
            fillUserData = false;
        } else {
        	txtWorkDays.setText("" + maxDaysWork);
        }
        	
        super.setComboItems(comboUser, new RefUserDM(), userId);
        fillUserData = true;
    }
	
	@Override
	protected Entity entityFromFields() {
		RefUser user = (RefUser) comboUser.getSelectedItem();
		
		return
			new DocSalaryItem(
				id,
				user.getId(),
				user.getName(),
				user.getSurname(),
				txtSalaryRef.getFloat(),
				txtWorkDays.getInt(),
				txtSalaryCalc.getFloat(),
				txtPremiaRef.getFloat(),
				txtPremiaCalc.getFloat(),
				txtSumma.getFloat()
			);
	}
	
	/**
	 * Amount of the work days <= amount of the days
	 */
	private void checkWorkDays() {
		int workDays = txtWorkDays.getInt();
		workDays = Math.min(workDays, maxDaysAll);
		txtWorkDays.setText("" + workDays);
	}
	
	/**
	 * Calculate value for the summa field
	 */
	private void calcSumma() {
		int workDays = txtWorkDays.getInt();
		float summa = 0;
		float premia = 0;
		float salaryRef = txtSalaryRef.getFloat();
		float premiaRef = txtPremiaRef.getFloat();
		
		summa = Common.Round(salaryRef * workDays / maxDaysWork, 2);
		txtSalaryCalc.setText("" + summa);
		
		premia = Common.Round(summa * premiaRef / 100, 2); 
		txtPremiaCalc.setText("" + premia);
		
		summa += premia;
		txtSumma.setText("" + Common.Round(summa,2));
	}
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		DocSalaryItem salaryItem = (DocSalaryItem) ent;
		
		int salaryId = super.parentDialog.getId();
		int id = salaryItem.getId();
		int userId = salaryItem.getUserId();
		String userName = salaryItem.getUserName();
		
		if (new DocSalaryItemDM(salaryId).isUserSalaryExists(id, userId)) {
	    	Common.showErrorMessage(this, "Salary for '" + userName + "' is already calculated!");
	    	return false;
	    }

		return true;
	}
	
}