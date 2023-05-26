package datamanage;
//Adrian Faircloth
//12-6-22
//CSC480 Test 2
//Bean for holding Publisher information
public class PublisherBean implements Comparable<PublisherBean>{
	private int pubID;
	private String name;
	private String contact;
	private String phone;
	private String companyPhotoPath;
	private String contactPhotoPath;
	
	/**
	 * Constructor for PublisherBean, params for each data field
	 * @param pubID
	 * @param name
	 * @param contact
	 * @param phone
	 * @param companyPhotoPath
	 * @param contactPhotoPath
	 */
	public PublisherBean(int pubID, String name, String contact,
						String phone, String companyPhotoPath, String contactPhotoPath) {
		this.pubID = pubID;
		this.name = name;
		this.contact = contact;
		this.phone = phone;
		this.companyPhotoPath = "data/" + companyPhotoPath;
		this.contactPhotoPath = "data/" + contactPhotoPath;
	}
	
	/**
	 * Getter for publisher ID
	 * @return pubID the publisher ID
	 */
	public int getPubID() {
		return pubID;
	}
	
	/**
	 * Getter for publisher name
	 * @return name the publisher name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for publisher contact
	 * @return contact the publisher contact
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Getter for publisher phone number
	 * @return phone the publisher phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Getter for the company photo path
	 * @return companyPhotoPath the company photo path
	 */
	public String getCompanyPhotoPath() {
		return companyPhotoPath;
	}
	
	/**
	 * Getter for the contact photo path
	 * @return contactPhotoPath the contact photo path
	 */
	public String getContactPhotoPath() {
		return contactPhotoPath;
	}
	
	/**
	 * Comparison method for comparing PublisherBeans by name in alphabetical order
	 * 0 means names are in the same place
	 * Negative means compBean comes before this bean
	 * Positive means this bean comes before compBean
	 * @return the difference in bean names
	 */
	@Override
	public int compareTo(PublisherBean compBean) {
		return this.name.compareToIgnoreCase(compBean.name);
	}
	
	/**
	 * toString method for PublisherBean, formats String with publisher data separated by |
	 * @return dataString the String of publisher data
	 */
	public String toString() {
		String dataString = pubID + "|" + name + "|" + contact + "|" + phone + "|" + companyPhotoPath + "|" + contactPhotoPath;
		return dataString;
	}

}
