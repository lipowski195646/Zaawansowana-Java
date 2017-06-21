package editdialogs;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import datamanagers.RefUserDM;
import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefCompany;
import entities.RefUser;
import main.DatePicker;

public class RefCompanyED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtName, txtFullname, txtCode, txtAddress, txtPhones, txtEmails;
	private JComboBox<Entity> comboBoss, comboAcc;
	private DatePicker dpRegistered;
	
	public RefCompanyED(Entity ent) {
		super();
		
		setTitle("Add / edit company");
		setBounds(100, 100, 510, 380);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 35, 80, 25);
		panelFields.add(lblName);
		
		JLabel lblFullname = new JLabel("Fullname:");
		lblFullname.setBounds(12, 65, 80, 25);
		panelFields.add(lblFullname);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setBounds(12, 95, 80, 25);
		panelFields.add(lblCode);
		
		JLabel lblRegistered = new JLabel("Registered:");
		lblRegistered.setBounds(12, 125, 80, 25);
		panelFields.add(lblRegistered);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(12, 155, 80, 25);
		panelFields.add(lblAddress);
		
		JLabel lblPhones = new JLabel("Phones:");
		lblPhones.setBounds(12, 185, 80, 25);
		panelFields.add(lblPhones);
		
		JLabel lblEmails = new JLabel("Emails:");
		lblEmails.setBounds(12, 215, 80, 25);
		panelFields.add(lblEmails);
		
		JLabel lblBoss = new JLabel("Boss:");
		lblBoss.setBounds(12, 245, 80, 25);
		panelFields.add(lblBoss);
		
		JLabel lblAccountant = new JLabel("Accountant:");
		lblAccountant.setBounds(12, 275, 80, 25);
		panelFields.add(lblAccountant);
		
		super.txtId.setBounds(90, 5, 80, 25);
		
		txtName = new JTextField();
		txtName.setBounds(90, 35, 200, 25);
		txtName.setColumns(10);
		panelFields.add(txtName);
		
		txtFullname = new JTextField();
		txtFullname.setBounds(90, 65, 200, 25);
		txtFullname.setColumns(10);
		panelFields.add(txtFullname);
		
		txtCode = new JTextField();
		txtCode.setBounds(90, 95, 100, 25);
		txtCode.setColumns(10);
		panelFields.add(txtCode);
		
		dpRegistered = new DatePicker();
		dpRegistered.setBounds(90, 124, 150, 27);
		panelFields.add(dpRegistered);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(90, 155, 400, 25);
		txtAddress.setColumns(10);
		panelFields.add(txtAddress);
		
		txtPhones = new JTextField();
		txtPhones.setBounds(90, 185, 400, 25);
		txtPhones.setColumns(10);
		panelFields.add(txtPhones);
		
		txtEmails = new JTextField();
		txtEmails.setBounds(90, 215, 400, 25);
		txtEmails.setColumns(10);
		panelFields.add(txtEmails);
		
		comboBoss = new JComboBox<Entity>();
		comboBoss.setBounds(90, 245, 200, 25);
		panelFields.add(comboBoss);
		
		comboAcc = new JComboBox<Entity>();
		comboAcc.setBounds(90, 275, 200, 25);
		panelFields.add(comboAcc);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		RefCompany company = (RefCompany) ent;
		int bossId = 0;
		int accId = 0;
		
        if (company != null) {
            id = company.getId();
            bossId = company.getBossId();
            accId = company.getAccId();
            
            super.txtId.setText("" + id);
            txtName.setText(company.getName());
            txtFullname.setText(company.getSurname());
            txtCode.setText(company.getCode());
            dpRegistered.setDate(company.getRegistered());
            txtAddress.setText(company.getAddress());
            txtPhones.setText(company.getPhones());
            txtEmails.setText(company.getEmails());
        }
        	
        super.setComboItems(comboBoss, new RefUserDM(), bossId);
        super.setComboItems(comboAcc, new RefUserDM(), accId);
    }
	
	@Override
	protected Entity entityFromFields() {
		RefUser boss = (RefUser) comboBoss.getSelectedItem();
		RefUser acc = (RefUser) comboAcc.getSelectedItem();
		
		return
			new RefCompany(
				id, 
				txtName.getText().trim(),
				txtFullname.getText().trim(),
				txtCode.getText().trim(),
				dpRegistered.getDate(),
				txtAddress.getText().trim(),
				txtPhones.getText().trim(),
				txtEmails.getText().trim(),
				boss.getId(),
				boss.getName(),
				boss.getSurname(),
				acc.getId(),
				acc.getName(),
				acc.getSurname()
			);
	}
	
}