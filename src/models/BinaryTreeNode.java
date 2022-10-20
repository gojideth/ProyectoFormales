package models;

public class BinaryTreeNode {
	
	private String element;
	private BinaryTreeNode leftNode;
	private BinaryTreeNode rightNode;
	
	public BinaryTreeNode(String value) {
		this.element = value;	
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return element;
	}

	/**
	 * @return the leftNode
	 */
	public BinaryTreeNode getLeftNode() {
		return leftNode;
	}

	/**
	 * @return the rightNode
	 */
	public BinaryTreeNode getRightNode() {
		return rightNode;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(String element) {
		this.element = element;
	}

	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(BinaryTreeNode leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(BinaryTreeNode rightNode) {
		this.rightNode = rightNode;
	}
	
	public int getLength() {
		return element.length();
	}
	
	

}
