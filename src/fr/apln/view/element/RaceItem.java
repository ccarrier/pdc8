package fr.apln.view.element;

/**
 * Represents a race item
 * @author Thomas Thiebaud
 *
 */
public class RaceItem {
	private int nameId;

	/**
	 * Constructor
	 * @param nameId Id of the name
	 */
    public RaceItem(int nameId) {
          super();
          this.nameId = nameId;
    }

    /**
     * Get id of the name
     * @return Id of the name
     */
	public int getNameId() {
		return nameId;
	}

	/**
	 * Set id of the name
	 * @param nameId New id
	 */
	public void setNameId(int nameId) {
		this.nameId = nameId;
	}
}
