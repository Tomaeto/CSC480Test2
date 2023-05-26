package gui;
//Adrian Faircloth
//12-6-22
//CSC480 Test 2
//Main display panel for book reordering program, displays JTable and has buttons for displaying info on selected entry
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import datamanage.PubManager;
import datamanage.PublisherBean;

public class SelectionTable extends JPanel{
	
	/**
	 * Constructor for SelectionTable, initializes table
	 * @throws SQLException
	 */
    public SelectionTable() throws SQLException {
        initializePanel();
    }

    /**
     * Method for adding SelectionTable to a JFrame and setting as visible
     * @throws SQLException
     */
    public static void showFrame() throws SQLException {
        JPanel panel = new SelectionTable();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTable Single Selection");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main method for running SelectionTable program
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			try {
				showFrame();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

    /**
     * Method for initializing the 2D data array from PublisherBean array, builds array of arrays of bean data for table
     * @param beans the array of PublisherBeans
     * @return the 2D Object array of publisher data
     */
    private Object[][] initializeData(PublisherBean[] beans) {
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        for (PublisherBean bean : beans) {
        	Object[] dataLine = {bean.getName(), bean.getPubID(), bean.getContact(), bean.getPhone()};
        	data.add(dataLine);
        }
        return data.toArray(new Object[data.size()][4]);

    }
    
    /**
     * Method for initializing panel, uses PubManager to get PublisherBeans, create table, and add buttons w/ ActionListeners
     * jbtnMail makes button display company URL and image
     * jbtnCall makes button display contact info and image
     * @throws SQLException
     */
    private void initializePanel() throws SQLException {
        this.setLayout(new BorderLayout());
        
        PubManager pm = new PubManager();
        PublisherBean[] beans = pm.getBeans();

        
        JTable table = new JTable(new PremiereLeagueTableModel(initializeData(beans)));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        
        JLabel selectedValue = new JLabel("Value selected: ");
        JPanel valuePane = new JPanel();
        String[] selectRow = new String[4];

        JButton jbtnMail = new JButton("Online");
        ActionListener mailListener = event -> {
           ImageIcon icon = null;
     	   String selectedVal = selectedValue.getText().substring(10);
     	   for (PublisherBean bean : beans) {
     		   if (bean.getName().equals(selectedVal) || bean.getContact().equals(selectedVal)) {
     			   icon = new ImageIcon(bean.getCompanyPhotoPath());
     		   	   break;
     		   }
     	   }
     	   selectedVal = selectedVal.replace(' ', '-');
     	   String url = selectedVal + ".com";
     	   JOptionPane.showMessageDialog(null, url, "Publisher URL", JOptionPane.INFORMATION_MESSAGE, icon);
         };
        jbtnMail.addActionListener(mailListener);
        
        JButton jbtnCall = new JButton("Call");
        ActionListener callListener = event -> {
    		String selectedVal = selectedValue.getText().substring(10);
    		String messageText = "";
    		ImageIcon photo = null;
        	for (PublisherBean bean : beans) {
        		if (bean.getName().equals(selectedVal) || bean.getContact().equals(selectedVal)) {
                	messageText = "Contact: " + bean.getContact() + "\n";
            		messageText += "Phone: " + bean.getPhone();
            		photo = new ImageIcon(bean.getContactPhotoPath());
            		break;
        		}
        	}
        	JOptionPane.showMessageDialog(null, messageText, "Company Contact", JOptionPane.INFORMATION_MESSAGE, photo);
        };
        jbtnCall.addActionListener(callListener);
        
        valuePane.add(selectedValue);
        valuePane.add(jbtnMail);
        valuePane.add(jbtnCall);
        
        this.add(valuePane, BorderLayout.SOUTH);
        JLabel bookReorder = new JLabel(new ImageIcon("data/books.png"));
        this.add(bookReorder, BorderLayout.NORTH);

        // Settings table selection mode. We can use the following
        // three constants to define the selection mode.
        //
        // - ListSelectionModel.SINGLE_SELECTION
        // - ListSelectionModel.SINGLE_INTERVAL_SELECTION
        // - ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(
        		new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
				        String selectedData = null;
				        int[] selectedRow = table.getSelectedRows();
				        int[] selectedCol = table.getSelectedColumns();
				        for (PublisherBean bean: beans) {
				        	if (bean.getName().equals(table.getValueAt(selectedRow[0],selectedCol[0]))) {
				        		selectedData = bean.getName();
				        		break;
				        	}
				        	else if (bean.getContact().equals(table.getValueAt(selectedRow[0],selectedCol[0]))) {
				        		selectedData = bean.getContact();
				        		break;
				        	}
				        }
				        selectedValue.setText("Selected: " + selectedData);
					}
        			
        		});
        JScrollPane pane = new JScrollPane(table);
        this.add(pane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(500, 100));
    }

    @SuppressWarnings("serial")
	static class PremiereLeagueTableModel extends AbstractTableModel {

    	public PremiereLeagueTableModel(Object[][] data) {
    		this.data = data;
    	}
    	
    	// TableModel's column names
        private String[] columnNames = {
                "Name", "PubID", "Contact", "Phone"
        };
        
        // TableModel's data
        private Object[][] data;
        
        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
        
        public String getColumnName(int column) {
        	return columnNames[column];
        }

    }
}