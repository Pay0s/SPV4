import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;


public class MainWindows extends JBDC{

	private JFrame frame1;
	private JTextField textFieldID;
	private JTextField textFieldNom;
	private JTextField textFieldQte;
	private JTextField textFieldDesc;
	private JTable table;
	DefaultTableModel model;
	

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		
		//while (Articles.next()) {
			//System.out.println("id:" + Articles.getInt("ID"));
		//}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindows window = new MainWindows();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 823, 535);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 789, 478);
		frame1.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 109, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 41, 109, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9 :");
		lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantit.setBounds(10, 76, 109, 21);
		panel.add(lblQuantit);
		
		JLabel lblDesc = new JLabel("Desc :");
		lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDesc.setBounds(10, 107, 102, 21);
		panel.add(lblDesc);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(129, 14, 145, 19);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(129, 45, 145, 19);
		panel.add(textFieldNom);
		
		textFieldQte = new JTextField();
		textFieldQte.setColumns(10);
		textFieldQte.setBounds(129, 80, 145, 19);
		panel.add(textFieldQte);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setColumns(10);
		textFieldDesc.setBounds(129, 111, 145, 19);
		panel.add(textFieldDesc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 10, 411, 458);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				
				int i = table.getSelectedRow();
				
				if (i >= 0 ) {
					
					textFieldID.setText(model.getValueAt(i, 0).toString());
					textFieldNom.setText(model.getValueAt(i, 1).toString());
					textFieldQte.setText(model.getValueAt(i, 2).toString());
					textFieldDesc.setText(model.getValueAt(i, 3).toString());
					
				} else {
					
					JOptionPane.showMessageDialog(null, "S�lectionnez une ligne");
				}
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		Object[] column = {"ID", "Nom", "Qte", "Desc"};
		final Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textFieldID.getText().equals("") || textFieldNom.getText().equals("") || textFieldQte.getText().equals("") || textFieldDesc.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Il faut remplir tout les champs");
					
				} else {
					
					
					try {
						JBDC.addArticle(textFieldNom.getText(), Integer.parseInt(textFieldQte.getText()), textFieldDesc.getText());
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					row[0] = textFieldID.getText();
					row[1] = textFieldNom.getText();
					row[2] = textFieldQte.getText();
					row[3] = textFieldDesc.getText();
					model.addRow(row);
					
					textFieldID.setText("");
					textFieldNom.setText("");
					textFieldQte.setText("");
					textFieldDesc.setText("");
				}
			}
		});
		btnAjouter.setBounds(37, 222, 109, 37);
		panel.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = table.getSelectedRow();
				model.setValueAt(textFieldID.getText(), i, 0);
				model.setValueAt(textFieldNom.getText(), i, 1);
				model.setValueAt(textFieldQte.getText(), i, 2);
				model.setValueAt(textFieldDesc.getText(), i, 3);
			}
		});
		btnModifier.setBounds(168, 222, 109, 37);
		panel.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = table.getSelectedRow();

				if( i >= 0 ) {
					
					model.removeRow(i);
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Il faut s�l�ctionner une ligne");
				}
			}
		});
		btnSupprimer.setBounds(37, 269, 240, 37);
		panel.add(btnSupprimer);
		
		// Cr�er des produits
		try {
			ResultSet Articles = JBDC.TableArticle();
			while ( Articles.next()) {
				row[0] = Articles.getInt("ID");
				row[1] = Articles.getString("Nom");
				row[2] = Articles.getInt("Qte");
				row[3] = Articles.getString("Description");
				model.addRow(row);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
