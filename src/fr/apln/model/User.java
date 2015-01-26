package fr.apln.model;

/**
 * User bean
 * @author Thomas Thiebaud
 *
 */
public class User {
	private String id;
	private String name;
	
	/**
	 * Default constructor
	 */
	public User() {
		//Empty for the moment
	}

	/**
	 * Get id
	 * @return Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id
	 * @param id New id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get name
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * @param name New name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return id + " " + name;
	}
}
