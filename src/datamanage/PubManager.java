package datamanage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

//Adrian Faircloth
//12-6-22
//CSC480 Test 2
//Class for building array of PublisherBean Objects from database query
public class PubManager {

	ArrayList<PublisherBean> beans = new ArrayList<PublisherBean>();
	
	/**
	 * Constructor for PubManager, runs loadBeans() method
	 * @throws SQLException
	 */
	public PubManager() throws SQLException {
		loadBeans();
	}
	
	/**
	 * Method for querying database and loading result data into beans ArrayList
	 * @throws SQLException
	 */
	public void loadBeans() throws SQLException {
		
		SqlServerDbAccessor dba = new SqlServerDbAccessor();
		dba.setDbName("JLBookstore");
		dba.connectToDb();
		String query = "select p.PubID, Name, Contact, Phone, CompanyPhotoPath, ContactPhotoPath "
				+ "from Publisher p join StudTest.dbo.Test2 t2 "
				+ "on (p.PubID = t2.PubID)";
		ResultSet rset = dba.getConnection().createStatement().executeQuery(query);
		PublisherBean bean;
		
		while (rset.next()) {
			bean = new PublisherBean(rset.getInt(1), rset.getString(2), rset.getString(3), 
					rset.getString(4), rset.getString(5), rset.getString(6));
			beans.add(bean);
		}
	}
	
	/**
	 * Method for converting beans ArrayList into PublisherBean array and returning
	 * @return beanArr Array of PublisherBeans
	 */
	public PublisherBean[] getBeans() {
		PublisherBean[] beanArr = beans.toArray(new PublisherBean[beans.size()]);
		Arrays.sort(beanArr);
		return beanArr;
	}
	
	/**
	 * Main method for testing PubManager functionality
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		PubManager pm = new PubManager();
		PublisherBean[] beansArr = pm.getBeans();
		for (PublisherBean bean: beansArr) {
			System.out.println(bean.toString());
		}
	}
}
