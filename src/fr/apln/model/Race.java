package fr.apln.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Race {
	private String id = "";
	private String name = "";
	private List<Tree> trees = new ArrayList<Tree>();
	private Map<User,Integer> results = new HashMap<User, Integer>();
	
	public Race() {
		
	}

	@Override
	public String toString() {
		return name;
	}
	
	public int treeSize() {
		return trees.size();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Tree> getTrees() {
		return this.trees;
	}
	
	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}
	
	public void addTree(Tree tree) {
		trees.add(tree);
	}
}
