package fr.apln.model;

/**
 * Tree bean
 * @author Thomas Thiebaud
 *
 */
public class Tree {
	private String id;
	private String code;
	private String name;
	private double height;
	private double trunkDiameter;
	private double crownDiameter;
	private double longitude;
	private double latitude;
	private String genre;
	private String species;
	private String type;
	
	/**
	 * Default constructor
	 */
	public Tree() {
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
	 * Get code
	 * @return Code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Set code
	 * @param code New code
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * Get height
	 * @return Height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Set height
	 * @param height New height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Get trunk diameter
	 * @return Trunk diameter
	 */
	public double getTrunkDiameter() {
		return trunkDiameter;
	}

	/**
	 * Set trunk diameter
	 * @param trunkDiameter New trunk diameter
	 */
	public void setTrunkDiameter(double trunkDiameter) {
		this.trunkDiameter = trunkDiameter;
	}

	/**
	 * Get crown diameter
	 * @return Crown diameter
	 */
	public double getCrownDiameter() {
		return crownDiameter;
	}

	/**
	 * Set crown diameter
	 * @param crownDiameter New crown diameter
	 */
	public void setCrownDiameter(double crownDiameter) {
		this.crownDiameter = crownDiameter;
	}

	/**
	 * Get longitude
	 * @return Longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Set longitude
	 * @param longitude New longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get latitude
	 * @return Latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Set latitude
	 * @param latitude New latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get genre
	 * @return Genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Set genre
	 * @param genre New genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Get species
	 * @return Species
	 */
	public String getSpecies() {
		return species;
	}

	/**
	 * Set species
	 * @param species New species
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * Get type
	 * @return Type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set type
	 * @param type New type
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return 	name + ";"
			+	height + ";"
			+	trunkDiameter + ";"
			+	crownDiameter + ";"
			+	genre + ";"
			+	species + ";"
			+	type + ";";
	}	
}
