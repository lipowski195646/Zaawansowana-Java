package editdialogs;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialogDoc;
import superclasses.Entity;
import entities.DocSalary;
import listpanels.DocSalaryItemLP;
import main.Common;
import main.IntTextField;

public class DocSalaryED extends EditDialogDoc {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	protected JTextField txtPeriod;
	protected IntTextField txtDaysAll, txtDaysWork;
	
	public DocSalaryED(Entity ent) {
		super(100, 100, 600, 600, 95);
		
		setTitle("Add / edit salary");
		
		JLabel lblPeriod = new JLabel("Period:");
		lblPeriod.setBounds(210, 5, 80, 25);
		panelFields.add(lblPeriod);
		
		JLabel lblDaysAll = new JLabel("Days:");
		lblDaysAll.setBounds(210, 35, 80, 25);
		panelFields.add(lblDaysAll);
		
		JLabel lblDaysWork = new JLabel("work:");
		lblDaysWork.setBounds(345, 35, 80, 25);
		panelFields.add(lblDaysWork);
		
		txtPeriod = new JTextField();
		txtPeriod.setBounds(265, 5, 300, 25);
		txtPeriod.setColumns(10);
		panelFields.add(txtPeriod);
		
		txtDaysAll = new IntTextField();
		txtDaysAll.setBounds(265, 35, 70, 25);
		panelFields.add(txtDaysAll);
		
		txtDaysWork = new IntTextField();
		txtDaysWork.setBounds(390, 35, 70, 25);
		panelFields.add(txtDaysWork);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		DocSalary salary = (DocSalary) ent;
		
        if (salary != null) {
            id = salary.getId();
            
            super.txtId.setText("" + id);
            super.dpDate.setDate(salary.getDate());
            super.txtNumber.setText(salary.getNumber());
            txtPeriod.setText(salary.getPeriod());
            txtDaysAll.setText("" + salary.getDaysAll());
            txtDaysWork.setText("" + salary.getDaysWork());
            
            panelTable.removeAll();
            super.items = new DocSalaryItemLP(id, this);
            super.items.setReadOnly(super.readOnly);
            panelTable.add(super.items, BorderLayout.CENTER);
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return
			new DocSalary(
				id, 
				super.dpDate.getDate(), 
				super.txtNumber.getText().trim(),
				txtPeriod.getText(),
				txtDaysAll.getInt(),
				txtDaysWork.getInt(),
				0
			);
	}
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		DocSalary salary = (DocSalary) ent;
		
		if (salary.getDaysAll() == 0) {
	    	Common.showErrorMessage(this, "The number of calendar days is zero!");
	    	return false;
	    }
		
		if (salary.getDaysWork() == 0) {
	    	Common.showErrorMessage(this, "The number of working days is zero!");
	    	return false;
	    }

		return true;
	}
	
}