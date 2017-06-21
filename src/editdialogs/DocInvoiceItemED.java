package editdialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import datamanagers.RefProductDM;
import superclasses.EditDialog;
import superclasses.EditDialogDoc;
import superclasses.Entity;
import entities.DocInvoiceItem;
import entities.RefProduct;
import main.Common;
import main.FloatTextField;

public class DocInvoiceItemED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtUnit;
	private FloatTextField txtQuantity, txtPrice, txtSumma;
	private JComboBox<Entity> comboProduct;
	private boolean fillPrice = true;
	
	public DocInvoiceItemED(Entity ent, EditDialogDoc parentDialog) {
		super(parentDialog);
		
		setTitle("Add / edit invoice item");
		setBounds(100, 100, 325, 255);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setBounds(12, 35, 50, 25);
		panelFields.add(lblProduct);
		
		JLabel lblUnit = new JLabel("Unit:");
		lblUnit.setBounds(12, 65, 50, 25);
		panelFields.add(lblUnit);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(12, 95, 80, 25);
		panelFields.add(lblQuantity);
		
		JLabel lblPurchase = new JLabel("Price:");
		lblPurchase.setBounds(12, 125, 80, 25);
		panelFields.add(lblPurchase);
		
		JLabel lblSumma = new JLabel("Summa:");
		lblSumma.setBounds(12, 155, 80, 25);
		panelFields.add(lblSumma);
		
		comboProduct = new JComboBox<Entity>();
		comboProduct.setBounds(80, 35, 200, 25);
		panelFields.add(comboProduct);
		
		comboProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RefProduct product = (RefProduct) comboProduct.getSelectedItem();
				txtUnit.setText(product.getUnit());
				
				if (fillPrice) {
					txtPrice.setText("" + product.getPriceIn());
		            calcSumma();
				};
			}
		});
		
		txtUnit = new JTextField();
		txtUnit.setBounds(80, 65, 80, 25);
		txtUnit.setColumns(10);
		txtUnit.setHorizontalAlignment(SwingConstants.CENTER);
		txtUnit.setEditable(false);
		panelFields.add(txtUnit);
		
		txtQuantity = new FloatTextField();
		txtQuantity.setBounds(80, 95, 80, 25);
		panelFields.add(txtQuantity);
		
		txtQuantity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				calcSumma();
			}
		});
		txtQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcSumma();
			}
		});
		
		txtPrice = new FloatTextField();
		txtPrice.setBounds(80, 125, 80, 25);
		panelFields.add(txtPrice);
		
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				calcSumma();
			}
		});
		txtPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcSumma();
			}
		});
		
		txtSumma = new FloatTextField();
		txtSumma.setBounds(80, 155, 80, 25);
		txtSumma.setEditable(false);
		panelFields.add(txtSumma);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		DocInvoiceItem invoiceItem = (DocInvoiceItem) ent;
		int productId = 0;
		
        if (invoiceItem != null) {
            id = invoiceItem.getId();
            productId = invoiceItem.getProductId();
            
            super.txtId.setText("" + id);
            txtQuantity.setText("" + invoiceItem.getQuantity());
            txtPrice.setText("" + invoiceItem.getPrice());
            txtSumma.setText("" + invoiceItem.getSumma());
            
            fillPrice = false;
        }
        	
        super.setComboItems(comboProduct, new RefProductDM(), productId);
        fillPrice = true;
    }
	
	@Override
	protected Entity entityFromFields() {
		RefProduct product = (RefProduct) comboProduct.getSelectedItem();
		
		return
			new DocInvoiceItem(
				id,
				product.getId(),
				product.getName(),
				product.getUnit(),
				txtQuantity.getFloat(),
				txtPrice.getFloat(),
				txtSumma.getFloat()
			);
	}
	
	/**
	 * Calculate value for the summa field
	 */
	private void calcSumma() {
		float quantity = txtQuantity.getFloat();
		float price = txtPrice.getFloat();
		float summa = quantity * price;
		
		summa = Common.Round(summa, 2);
		
		txtQuantity.setText("" + quantity);
		txtPrice.setText("" + price);
		txtSumma.setText("" + summa);
	}
	
}