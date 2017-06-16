package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import superclasses.Dialog;
import superclasses.RoleFrame;
import entities.RefAccount;
import entities.RefUser;
import datamanagers.RefAccountDM;
import datamanagers.RefCompanyDM;
import datamanagers.RefUserDM;

/**
 * Show login dialog and set common appliaction variables (after login) 
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public class LoginDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
    private JPanel contentPane;
    
    /**
     * Handler for dialog fields and buttons
     * @return key listener
     */
    private KeyListener exitOnEsc() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		        	System.exit(0);
			}
		};
	}
	
	public LoginDialog() {
		super();
		
		setTitle("Login");
		setBounds(100, 100, 242, 157);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLogin.setBounds(12, 23, 46, 14);
		panelFields.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setBounds(12, 52, 73, 14);
		panelFields.add(lblPassword);
		
		JTextField editLogin = new JTextField();
		editLogin.addKeyListener(exitOnEsc());
		editLogin.setBounds(95, 21, 122, 20);
		editLogin.setText("admin");
		panelFields.add(editLogin);
				
		JPasswordField editPassword = new JPasswordField();
		editPassword.addKeyListener(exitOnEsc());
		editPassword.setBounds(95, 50, 122, 20);
		editPassword.setText("123");
		panelFields.add(editPassword);
		
		super.btnOK.addKeyListener(exitOnEsc());

		super.btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login = editLogin.getText();
				String password = new String(editPassword.getPassword());
				
				RefAccount account = new RefAccountDM().getAccountByLoginPassword(login, password);
				
				if (account != null) {
					dispose();
					
					RefUser user = new RefUserDM().getUserByAccount(account.getId());
					
					Common.setRegisteredAccount(account);
					Common.setRegisteredUser(user);
					Common.setRegisteredCompany(new RefCompanyDM().getCompany());
					
					// class reflection - select role interface form
					String frameClass = Constants.ROLEFRAMES_PACKAGE + Common.getRegisteredAccount().getRole() + "Frame";
					try {
						Class<?> frame = Class.forName(frameClass);
						RoleFrame fr = (RoleFrame) frame.newInstance();
						Common.showFrame(fr);
					} catch (ClassNotFoundException e) {
						Common.showErrorMessage(null, "Class <" + frameClass + "> is not found!");
					} catch (InstantiationException | IllegalAccessException e) {
						Common.showErrorMessage(null, "Error creating object of class <" + frameClass + ">!");
					}
				} else {
					Common.showErrorMessage(contentPane, "Invalid user name or password!");
				}
			}
		});
		
		super.btnCancel.addKeyListener(exitOnEsc());
		
		super.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}