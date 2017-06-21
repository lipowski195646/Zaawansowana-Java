package roleframes;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import superclasses.RoleFrame;
import main.Common;

public class AdminFrame extends RoleFrame {
	private static final long serialVersionUID = 1L;
	private static final String USERS = "USERS";
	private static final String CLIENTS = "CLIENTS";
	private static final String SELLERS = "SELLERS";
	private static final String STORES = "STORES";
	private static final String PRODUCTS = "PRODUCTS";
	private static final String INVOICES = "INVOICES";
	private static final String SALES = "SALES";
	private static final String SALARIES = "SALARIES";
	private static final String ACCOUNTS = "ACCOUNTS";
	private static final String COMPANIES = "COMPANIES";
	private static final String VIEW_USERS = "VIEW_USERS";
	private static final String VIEW_INVOICES = "VIEW_INVOICES";
	private static final String REPORT_SALARIES = "REPORT_SALARIES";
	private static final String REPORT_USERS = "REPORT_USERS";
	
	public AdminFrame() {
		showTitle("");
		
		JMenu mnReferences = new JMenu("References");
		menuBar.add(mnReferences);
		
		JMenuItem mntmRefUsers = new JMenuItem("Users list");
		mntmRefUsers.setActionCommand(USERS);
		mntmRefUsers.addActionListener(this);
		mnReferences.add(mntmRefUsers);
		
		JMenuItem mntmRefClients = new JMenuItem("Clients list");
		mntmRefClients.setActionCommand(CLIENTS);
		mntmRefClients.addActionListener(this);
		mnReferences.add(mntmRefClients);
		
		JMenuItem mntmRefSellers = new JMenuItem("Sellers list");
		mntmRefSellers.setActionCommand(SELLERS);
		mntmRefSellers.addActionListener(this);
		mnReferences.add(mntmRefSellers);
		
		JMenuItem mntmRefStores = new JMenuItem("Stores list");
		mntmRefStores.setActionCommand(STORES);
		mntmRefStores.addActionListener(this);
		mnReferences.add(mntmRefStores);
		
		mnReferences.addSeparator();
		
		JMenuItem mntmRefProducts = new JMenuItem("Products list");
		mntmRefProducts.setActionCommand(PRODUCTS);
		mntmRefProducts.addActionListener(this);
		mnReferences.add(mntmRefProducts);
		
		JMenu mnDocs  = new JMenu("Documents");
		menuBar.add(mnDocs);
		
		JMenuItem mntmDocInvoices = new JMenuItem("Invoices");
		mntmDocInvoices.setActionCommand(INVOICES);
		mntmDocInvoices.addActionListener(this);
		mnDocs.add(mntmDocInvoices);
		
		JMenuItem mntmDocSales = new JMenuItem("Sales");
		mntmDocSales.setActionCommand(SALES);
		mntmDocSales.addActionListener(this);
		mnDocs.add(mntmDocSales);
		
		mnDocs.addSeparator();
		
		JMenuItem mntmDocSalaries = new JMenuItem("Salaries");
		mntmDocSalaries.setActionCommand(SALARIES);
		mntmDocSalaries.addActionListener(this);
		mnDocs.add(mntmDocSalaries);
		
		JMenu mnViews = new JMenu("Views");
		menuBar.add(mnViews);
		
		JMenuItem mntmViewUsers = new JMenuItem("View users");
		mntmViewUsers.setActionCommand(VIEW_USERS);
		mntmViewUsers.addActionListener(this);
		mnViews.add(mntmViewUsers);
		
		mnViews.addSeparator();
		
		JMenuItem mntmViewInvoices = new JMenuItem("View invoices");
		mntmViewInvoices.setActionCommand(VIEW_INVOICES);
		mntmViewInvoices.addActionListener(this);
		mnViews.add(mntmViewInvoices);
		
		JMenu mnAdmin = new JMenu("Administration");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmAdminAccounts = new JMenuItem("Accounts list");
		mntmAdminAccounts.setActionCommand(ACCOUNTS);
		mntmAdminAccounts.addActionListener(this);
		mnAdmin.add(mntmAdminAccounts);
		
		JMenuItem mntmRefCompanies = new JMenuItem("Companies");
		mntmRefCompanies.setActionCommand(COMPANIES);
		mntmRefCompanies.addActionListener(this);
		mnAdmin.add(mntmRefCompanies);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmReportUsers = new JMenuItem("Users");
		mntmReportUsers.setActionCommand(REPORT_USERS);
		mntmReportUsers.addActionListener(this);
		mnReports.add(mntmReportUsers);
		
		mnReports.addSeparator();
		
		JMenuItem mntmReportSalaries = new JMenuItem("Salaries");
		mntmReportSalaries.setActionCommand(REPORT_SALARIES);
		mntmReportSalaries.addActionListener(this);
		mnReports.add(mntmReportSalaries);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		switch (e.getActionCommand()) {
		case USERS:
			super.showListPanel(new listpanels.RefUserLP());
			break;
		case CLIENTS:
			super.showListPanel(new listpanels.RefClientLP());
			break;
		case SELLERS:
			super.showListPanel(new listpanels.RefSellerLP());
			break;
		case STORES:
			super.showListPanel(new listpanels.RefStoreLP());
			break;
		case PRODUCTS:
			super.showListPanel(new listpanels.RefProductLP());
			break;
		case INVOICES:
			super.showListPanel(new listpanels.DocInvoiceLP());
			break;
		case SALES:
			super.showListPanel(new listpanels.DocSaleLP());
			break;
		case SALARIES:
			super.showListPanel(new listpanels.DocSalaryLP());
			break;
		case ACCOUNTS:
			Common.makeFrame(new listpanels.RefAccountLP(300), "Work with accounts");
			break;
		case COMPANIES:
			listpanels.RefCompanyLP clp = new listpanels.RefCompanyLP(1000);
			clp.disableButton("Add");
			clp.disableButton("Delete");
			Common.makeFrame(clp, "Work with companies");
			break;
		case VIEW_USERS:
			listpanels.RefUserLP ulp = new listpanels.RefUserLP();
			ulp.setReadOnly(true);
			super.showListPanel(ulp);
			break;
		case VIEW_INVOICES:
			listpanels.DocInvoiceLP dlp = new listpanels.DocInvoiceLP();
			dlp.setReadOnly(true);
			super.showListPanel(dlp);
			break;
		case REPORT_USERS:
			new reports.UsersReport();
			break;
		case REPORT_SALARIES:
			new reports.SalariesReport();
			break;
		default:
			break;
		}
	}
}
