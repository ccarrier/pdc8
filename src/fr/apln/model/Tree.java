package fr.apln.model;

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
	
	public Tree() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getTrunkDiameter() {
		return trunkDiameter;
	}

	public void setTrunkDiameter(double trunkDiameter) {
		this.trunkDiameter = trunkDiameter;
	}

	public double getCrownDiameter() {
		return crownDiameter;
	}

	public void setCrownDiameter(double crownDiameter) {
		this.crownDiameter = crownDiameter;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getType() {
		return type;
	}

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
