package roleframes;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import superclasses.RoleFrame;

public class StockFrame extends RoleFrame {
	private static final long serialVersionUID = 1L;
	
	public StockFrame() {
		showTitle("Stock frame / ");
		
		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);
		
		JMenuItem mntmAction1 = new JMenuItem("ActionSale1");
		mntmAction1.setActionCommand("ACTION1");
		mntmAction1.addActionListener(this);
		mnActions.add(mntmAction1);
		
		JMenuItem mntmAction2 = new JMenuItem("ActionSale2");
		mntmAction2.setActionCommand("ACTION2");
		mntmAction2.addActionListener(this);
		mnActions.add(mntmAction2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		switch (e.getActionCommand()) {
		case "ACTION1":
			main.Common.getCommonInstance().showErrorMessage(this, "ActionSale1");
			break;
		case "ACTION2":
			main.Common.getCommonInstance().showErrorMessage(this, "ActionSale2");
			break;
		default:
			break;
		}
	}
}
