package fr.apln.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Race bean
 * @author Thomas Thiebaud
 *
 */
public class Race {
	private String id = "";
	private String name = "";
	private List<Tree> trees = new ArrayList<Tree>();
	private int time;
	
	/**
	 * Default constructor
	 */
	public Race() {
		//Empty for the moment
	}

	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Get number of trees
	 * @return Number of trees
	 */
	public int treeSize() {
		return trees.size();
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
	 * @param name Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get trees
	 * @return Trees
	 */
	public List<Tree> getTrees() {
		return this.trees;
	}
	
	/**
	 * Set trees
	 * @param trees New list of trees
	 */
	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}
	
	/**
	 * Add a tree into tree list
	 * @param tree Tree to add
	 */
	public void addTree(Tree tree) {
		trees.add(tree);
	}

	/**
	 * Get time
	 * @return Time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Set time
	 * @param time New time
	 */
	public void setTime(int time) {
		this.time = time;
	}
}
