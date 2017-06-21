package editdialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import datamanagers.RefAccountDM;
import datamanagers.RefUserDM;
import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefAccount;
import entities.RefUser;
import main.Common;
import main.Constants;
import main.DatePicker;
import main.FloatTextField;

public class RefUserED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private RefUser user = null;
	private JTextField txtName, txtSurname, txtSocialId, txtState, txtAddress;
	private FloatTextField txtSalary, txtPremia;
	private JComboBox<Entity> comboAccount;
	private DatePicker dpDateIn, dpDateOut;
	private JCheckBox checkAccount;
	
	public RefUserED(Entity ent) {
		super();
		
		setTitle("Add / edit user");
		setBounds(100, 100, 370, 430);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 35, 60, 25);
		panelFields.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(12, 65, 60, 25);
		panelFields.add(lblSurname);
		
		JLabel lblSocialId = new JLabel("Social ID:");
		lblSocialId.setBounds(12, 95, 60, 25);
		panelFields.add(lblSocialId);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(12, 125, 80, 25);
		panelFields.add(lblState);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(12, 155, 80, 25);
		panelFields.add(lblSalary);
		
		JLabel lblPremia = new JLabel("Premia:");
		lblPremia.setBounds(12, 185, 80, 25);
		panelFields.add(lblPremia);
		
		JLabel lblDateIn = new JLabel("Hired:");
		lblDateIn.setBounds(12, 215, 80, 25);
		panelFields.add(lblDateIn);
		
		JLabel lblDateOut = new JLabel("Fired:");
		lblDateOut.setBounds(12, 245, 80, 25);
		panelFields.add(lblDateOut);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setBounds(12, 275, 80, 25);
		panelFields.add(lblAccount);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(12, 305, 80, 25);
		panelFields.add(lblAddress);
		
		super.txtId.setBounds(110, 5, 50, 25);
		
		txtName = new JTextField();
		txtName.setBounds(110, 35, 200, 25);
		txtName.setColumns(10);
		panelFields.add(txtName);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(110, 65, 200, 25);
		txtSurname.setColumns(10);
		panelFields.add(txtSurname);
		
		txtSocialId = new JTextField();
		txtSocialId.setBounds(110, 95, 100, 25);
		txtSocialId.setColumns(10);
		panelFields.add(txtSocialId);
		
		txtState = new JTextField();
		txtState.setBounds(110, 125, 200, 25);
		txtState.setColumns(10);
		panelFields.add(txtState);
		
		txtSalary = new FloatTextField();
		txtSalary.setBounds(110, 155, 80, 25);
		panelFields.add(txtSalary);
		
		txtPremia = new FloatTextField();
		txtPremia.setBounds(110, 185, 80, 25);
		panelFields.add(txtPremia);
		
		dpDateIn = new DatePicker();
		dpDateIn.setBounds(110, 214, 200, 27);
		panelFields.add(dpDateIn);
		
		JButton btnX1 = new JButton("X");
		btnX1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dpDateIn.getModel().setSelected(false);
			}
		});
		btnX1.setBounds(315, 214, 42, 26);
		panelFields.add(btnX1);
		
		dpDateOut = new DatePicker();
		dpDateOut.setBounds(110, 244, 200, 27);
		panelFields.add(dpDateOut);
		
		JButton btnX2 = new JButton("X");
		btnX2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dpDateOut.getModel().setSelected(false);
			}
		});
		btnX2.setBounds(315, 244, 42, 26);
		panelFields.add(btnX2);
		
		checkAccount = new JCheckBox("");
		checkAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				useAccount();
			}
		});
		checkAccount.setBounds(107, 275, 21, 25);
		panelFields.add(checkAccount);
		
		comboAccount = new JComboBox<Entity>();
		comboAccount.setBounds(130, 275, 180, 25);
		panelFields.add(comboAccount);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(12, 330, 345, 25);
		txtAddress.setColumns(10);
		panelFields.add(txtAddress);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		user = (RefUser) ent;
		
        if (user != null) {
            id = user.getId();
            
            super.txtId.setText("" + id);
            txtName.setText(user.getName());
            txtSurname.setText(user.getSurname());
            txtSocialId.setText(user.getSocialId());
            txtState.setText(user.getState());
            txtSalary.setText("" + user.getSalary());
            txtPremia.setText("" + user.getPremia());
            dpDateIn.setDate(user.getDateIn());
            dpDateOut.setDate(user.getDateOut());
            
            checkAccount.setSelected(user.getAccountId() != 0);
        }
        
        useAccount();
    }
	
	@Override
	protected Entity entityFromFields() {
		RefAccount account = (RefAccount) comboAccount.getSelectedItem();
		
		int accountId = 0;
		String accountLogin = "";
		
		if (checkAccount.isSelected()) {
			accountId = account.getId();
			accountLogin = account.getLogin();
		}
		
		return 
			new RefUser(
				id, 
				accountId, 
				accountLogin, 
				txtName.getText().trim(), 
				txtSurname.getText().trim(), 
				txtSocialId.getText().trim(), 
				txtState.getText().trim(),
				txtSalary.getFloat(),
				txtPremia.getFloat(),
				dpDateIn.getDate(), 
				dpDateOut.getDate(), 
				txtAddress.getText().trim()
			);
	}
	
	/**
	 * If set - user can login into application
	 */
	private void useAccount() {
		if (checkAccount.isSelected()) {
			int accountId = (user != null) ? user.getAccountId() : 0; 
			super.setComboItems(comboAccount, new RefAccountDM(), accountId);
	        comboAccount.setEnabled(true);
		} else {
			comboAccount.removeAllItems();
			comboAccount.setEnabled(false);
		}
	}
	
	@Override
	protected boolean extraSaveCheck(Entity ent) {
		RefUser user = (RefUser) ent;
		int accountId = user.getAccountId();
		
		if (accountId == 0) return true;
		
		int id = user.getId();
		String login = user.getLogin();
		
		if (new RefUserDM().isAccountInUse(id, accountId)) {
	    	Common.showErrorMessage(this, "Account '" + login + "' is already in use!");
	    	return false;
	    }

		return true;
	}
	
	@Override
	protected String printFormClassName() {
    	return Constants.PRINTFORMS_PACKAGE + "RefUserPF";
    }
	
}