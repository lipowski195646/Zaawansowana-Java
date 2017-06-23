package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import datamanagers.RefAccountDM;
import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefAccount;
import main.Common;
import main.Constants;

public class RefAccountED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtLogin, txtPassword;
	private JComboBox<String> comboRole;	
	
	public RefAccountED(Entity ent) {
		super();

		setTitle("Add / edit account");
		setBounds(100, 100, 285, 195);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(12, 35, 34, 25);
		panelFields.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 65, 61, 25);
		panelFields.add(lblPassword);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(12, 95, 38, 25);
		panelFields.add(lblRole);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(80, 35, 87, 25);
		txtLogin.setColumns(10);
		panelFields.add(txtLogin);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(80, 65, 87, 25);
		txtPassword.setColumns(10);
		panelFields.add(txtPassword);
		
		comboRole = new JComboBox<String>(Constants.ROLES);
		comboRole.setBounds(80, 95, 187, 25);
		panelFields.add(comboRole);
		
		initFields(ent);
	}
	
	protected void initFields(Entity ent) {
		RefAccount account = (RefAccount) ent;
		String role = "";
		
        if (account != null) {
            id = account.getId();
            
            super.txtId.setText("" + id);
            txtLogin.setText(account.getLogin());
            txtPassword.setText(account.getPassword());
            role = account.getRole();
            
            if (id == 1) {
            	comboRole.setEnabled(false);
    		}
        }
        
        comboRole.setSelectedItem(role);
	}

	@Override
	protected Entity entityFromFields() {
		return 
			new RefAccount(
				id, 
				txtLogin.getText().trim(), 
				txtPassword.getText().trim(),
				(String) comboRole.getSelectedItem()
			);
	}
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		int id = ent.getId();
		String login = ((RefAccount) ent).getLogin();
		
		if (new RefAccountDM().isAccountExists(id, login)) {
	    	Common.getCommonInstance().showErrorMessage(this, "Account '" + login + "' already exists!");
	    	return false;
	    }

		return true;
	}

}