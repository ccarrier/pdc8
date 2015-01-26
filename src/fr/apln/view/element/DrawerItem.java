package fr.apln.view.element;

/**
 * Represent a navigation drawer item
 * @author Thomas Thiebaud
 *
 */
public class DrawerItem {
	 
    private int nameId;
    private int imgId;

    /**
     * Constructor
     * @param nameId Id of the name 
     * @param imgId Id of the image
     */
    public DrawerItem(int nameId, int imgId) {
          super();
          this.nameId = nameId;
          this.imgId = imgId;
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
	 * @param nameId New Id
	 */
	public void setNameId(int nameId) {
		this.nameId = nameId;
	}

	/**
	 * Get id of the image
	 * @return Id of the image
	 */
	public int getImgId() {
		return imgId;
	}

	/**
	 * Set id of the image
	 * @param imgId New id
	 */
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
}