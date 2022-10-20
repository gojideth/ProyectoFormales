package models;

import java.util.ArrayList;

import javax.swing.JPanel;

public class BinaryTree implements Tree<String> {

	private BinaryTreeNode root;
	private ArrayList<String> usedSymbols;
	
	public BinaryTree() {
		usedSymbols = new ArrayList<>();
	}
	
	public void setSymbols(ArrayList<String> symbols) {
		usedSymbols = symbols;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void addElement(String element) {
		BinaryTreeNode newNode = new BinaryTreeNode(element);
		if (isEmpty()) {
			this.root = newNode;
		} else {
			addElement(newNode, root);
		}

	}

	@Override
	public String print() {
		StringBuilder treeAsAString = new StringBuilder("");
		print(treeAsAString, "", "", root);
		return treeAsAString.toString();

	}

	private void print(StringBuilder treeStringBuilder, String pointer, String identation, BinaryTreeNode rootNode) {
		if (rootNode != null) {
			treeStringBuilder.append(identation);
			treeStringBuilder.append(pointer);
			treeStringBuilder.append(rootNode.getValue());
			treeStringBuilder.append("\n");

			StringBuilder identationStringBuilder = new StringBuilder(identation);
			identationStringBuilder.append("|   ");
			String identationForBoth = identationStringBuilder.toString();
			String pointerToLeftElement = "|___";
			String pointerToRightElement = "|----";

			print(treeStringBuilder, pointerToRightElement, identationForBoth, rootNode.getRightNode());
			print(treeStringBuilder, pointerToLeftElement, identationForBoth, rootNode.getLeftNode());
		}

	}

	private void addElement(BinaryTreeNode newNode, BinaryTreeNode rootNode) {
		if (newNode.getLength() <= rootNode.getLength()) {
			if (rootNode.getLeftNode() == null) {
				rootNode.setLeftNode(newNode);
			} else {
				addElement(newNode, rootNode.getLeftNode());
			}
		} else {
			if (rootNode.getRightNode() == null) {
				rootNode.setRightNode(newNode);
			} else {
				addElement(newNode, rootNode.getRightNode());
			}
		}
	}

	@Override
	public boolean contains(String element) {
		return contains(element, root);
	}

	private boolean contains(String element, BinaryTreeNode rootNode) {
		if (rootNode != null) {
			if (element.equals(rootNode.getValue())) {
				return true;
			} else {
				rootNode = element.length() < rootNode.getLength() ? rootNode.getLeftNode() : rootNode.getRightNode();
				return contains(element, rootNode);
			}
		}
		return false;
	}

	@Override
	public String removeElement(String element) {
		if (root.getValue().equals(element)) {
			return removeNodeTwoSon(root);
		}else {
			return initRemove(root, null, element);
		}
	}

	private String initRemove(BinaryTreeNode node, BinaryTreeNode nodeFather, String element) {
		if (node == null) {
			return null;
		}else {
			if (node.getValue() == element) {
				return remove(node, nodeFather);
			}else if (element.length() > node.getLength()) {
				return initRemove(node.getRightNode(), node, element);
			}else {
				return initRemove(node.getLeftNode(), node, element);
			}
		}
	}
	private String remove(BinaryTreeNode node, BinaryTreeNode nodeFather) {
		if (node.getLeftNode() == null && node.getRightNode() == null) {
			return removeNodoSon(node, nodeFather, "none");
		}else if (node.getLeftNode() != null && node.getRightNode() == null) {
			return removeNodoSon(node, nodeFather,"left");
		}else if (node.getLeftNode() == null && node.getRightNode() != null) {
			return removeNodoSon(node, nodeFather, "right");
		}else {
			return removeNodeTwoSon(node);
		}
	}
	
	
	private String removeNodoSon(BinaryTreeNode nodeTree, BinaryTreeNode nodeFather, String positionSon) {
		String value = nodeTree.getValue();
		BinaryTreeNode aux = positionSon.equals("left")?nodeTree.getLeftNode():positionSon.equals("right")?nodeTree.getRightNode():null;
		if(nodeFather.getLeftNode() == nodeTree) {
			nodeFather.setLeftNode(aux);
		}else {
			nodeFather.setRightNode(aux);
		}
		return value;
	}
	
	
	private String removeNodeTwoSon(BinaryTreeNode node) {
		String value = node.getValue();
		if (root.getLeftNode() != null) {
			BinaryTreeNode avlTreeNode = searchBigger(node.getLeftNode());
			if (avlTreeNode == null) {
				BinaryTreeNode aux = node.getLeftNode();
				node.setElement(aux.getValue());
				node.setLeftNode(aux.getLeftNode());
			}else {
				node.setElement(avlTreeNode.getValue());
			}
		} else if (node.getRightNode() != null) {
			node = node.getRightNode();
		} else {
			node = null;
		}
		return value;
	}

	public BinaryTreeNode searchBigger(BinaryTreeNode node) {
		if (node.getRightNode() != null) {
			if (node.getRightNode().getRightNode() == null) {
				BinaryTreeNode aux = node.getRightNode();
				node.setRightNode(null);
				return aux;
			} else {
				return searchBigger(root.getRightNode());
			}
		}else {
			return null;
		}
	}
	
	public ArrayList<String> getPath(String word){
		ArrayList<String>path = new ArrayList<>();
		BinaryTreeNode actualNode = root;
		for (int i = 0; i < word.length(); i++) {
			path.add(actualNode.getValue());
			if (usedSymbols.contains(Character.toString(word.charAt(i)))) {
				if (i != word.length() - 1) {
					if (actualNode.getLeftNode() != null && actualNode.getRightNode() == null) {
						actualNode = actualNode.getLeftNode();
					} else if (actualNode.getLeftNode() == null && actualNode.getRightNode() != null) {
						actualNode = actualNode.getRightNode();
					} else if (actualNode.getLeftNode() != null && actualNode.getRightNode() != null) {
						actualNode = containsTerminalSymbol(actualNode.getRightNode().getValue())
								? actualNode.getRightNode()
								: actualNode.getLeftNode();
					} else {
						return path;
					}
				} else {
					if (actualNode.getLeftNode() != null && actualNode.getRightNode() == null) {
						actualNode = actualNode.getLeftNode();
					} else if (actualNode.getLeftNode() == null && actualNode.getRightNode() != null) {
						actualNode = actualNode.getRightNode();
					} else if (actualNode.getLeftNode() != null && actualNode.getRightNode() != null) {
						actualNode = !containsTerminalSymbol(actualNode.getRightNode().getValue())
								? actualNode.getRightNode()
								: actualNode.getLeftNode();
					} else {
						return path;
					}
					path.add(actualNode.getValue());
				} 
			}else {
				return path;
			}
		}
		return path;
	}
	
	public boolean containsTerminalSymbol(String word) {
		for (int j = 0; j < word.length(); j++) {
			if (word.charAt(j) >= 65 && word.charAt(j) <= 90) {
				return true;
			}
		}
		return false;
	}
	
	public BinaryTreeNode getNode() {
		return root;
	}
	

}
