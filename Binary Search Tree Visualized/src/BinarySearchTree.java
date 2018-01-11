//---------------------------------------------------------------------------
// BinarySearchTree.java          by Dale/Joyce/Weems               Chapter 7
//
// Defines all constructs for a reference-based BST.
// Supports three traversal orders Preorder, Postorder & Inorder ("natural")
//---------------------------------------------------------------------------

import java.util.*;   // Iterator, Comparator

public class BinarySearchTree<T> {
  protected BSTNode<T> root;      // reference to the root of this BST
  protected Comparator<T> comp;   // used for all comparisons
  protected boolean found;   // used by remove
    private int treeHeight = 10;
    String[][] treeArray;
  public BinarySearchTree() 
  // Precondition: T implements Comparable
  // Creates an empty BST object - uses the natural order of elements.
  {
    root = null;
    comp = new Comparator<T>()
    {
       public int compare(T element1, T element2)
       {
         return ((Comparable)element1).compareTo(element2);
       }
    };
  }

  public BinarySearchTree(Comparator<T> comp) 
  // Creates an empty BST object - uses Comparator comp for order
  // of elements.
  {
    root = null;
    this.comp = comp;
  }

  public boolean isFull()
  // Returns false; this link-based BST is never full.
  {
    return false;
  }

  public boolean isEmpty()
  // Returns true if this BST is empty; otherwise, returns false.
  {
    return (root == null);
  }

  public T min()
  // If this BST is empty, returns null;
  // otherwise returns the smallest element of the tree.
  {
    if (isEmpty())
       return null;
    else
    {
       BSTNode<T> node = root;
       while (node.getLeft() != null)
         node = node.getLeft();
       return node.getInfo();
    }
  }

  public T max()
  // If this BST is empty, returns null;
  // otherwise returns the largest element of the tree.
  {
    if (isEmpty())
       return null;
    else
    {
       BSTNode<T> node = root;
       while (node.getRight() != null)
         node = node.getRight();
       return node.getInfo();
    }
  }

  public int recSize(BSTNode<T> node)
  // Returns the number of elements in subtree rooted at node.
  {
    if (node == null)    
      return 0;
    else
      return 1 + recSize(node.getLeft()) + recSize(node.getRight());
  }

  public int size()
  // Returns the number of elements in this BST.
  {
    return recSize(root);
  }

  public int size2() throws Exception
  // Returns the number of elements in this BST.
  {
    int count = 0;
    if (root != null)
    {
      LinkedStack<BSTNode<T>> nodeStack = new LinkedStack<BSTNode<T>>();
      BSTNode<T> currNode;
      nodeStack.push(root);
      while (!nodeStack.isEmpty())
      {
        currNode = nodeStack.top();
        nodeStack.pop();
        count++;
        if (currNode.getLeft() != null)
          nodeStack.push(currNode.getLeft());
        if (currNode.getRight() != null)
          nodeStack.push(currNode.getRight());
      }
    }
    return count;
  }

  public boolean recContains(T target, BSTNode<T> node)
  // Returns true if the subtree rooted at node contains info i such that 
  // comp.compare(target, i) == 0; otherwise, returns false.
 {
    if (node == null)
      return false;       // target is not found
    else if (comp.compare(target, node.getInfo()) < 0)
      return recContains(target, node.getLeft());   // Search left subtree
    else if (comp.compare(target, node.getInfo()) > 0)
      return recContains(target, node.getRight());  // Search right subtree
    else
      return true;        // target is found
  }

  public boolean contains (T target)
  // Returns true if this BST contains a node with info i such that 
  // comp.compare(target, i) == 0; otherwise, returns false.
  {
    return recContains(target, root);
  }

  
  public T recGet(T target, BSTNode<T> node)
  // Returns info i from the subtree rooted at node such that 
  // comp.compare(target, i) == 0; if no such info exists, returns null.
  {
    if (node == null)
      return null;             // target is not found
    else if (comp.compare(target, node.getInfo()) < 0)
      return recGet(target, node.getLeft());         // get from left subtree
    else
    if (comp.compare(target, node.getInfo()) > 0)
      return recGet(target, node.getRight());        // get from right subtree
    else
      return node.getInfo();  // target is found
  }

  public T get(T target)
  // Returns info i from node of this BST where comp.compare(target, i) == 0;
  // if no such node exists, returns null.
  {
    return recGet(target, root);
  }
  
  

  public BSTNode<T> recAdd(T element, BSTNode<T> node)
  // Adds element to tree rooted at node; tree retains its BST property.
  {
    if (node == null)
      // Addition place found
      node = new BSTNode<T>(element);
    else if (comp.compare(element, node.getInfo()) <= 0)
      node.setLeft(recAdd(element, node.getLeft()));    // Add in left subtree
    else
      node.setRight(recAdd(element, node.getRight()));   // Add in right subtree
    return node;
  }

  public boolean add (T element)
  // Adds element to this BST. The tree retains its BST property.
  {
    root = recAdd(element, root);
    treeHeight = maxDepth()*6;

      return true;
  }


  public T getPredecessor(BSTNode<T> subtree)
  // Returns the information held in the rightmost node of subtree
  {
    BSTNode<T> temp = subtree;
    while (temp.getRight() != null)
      temp = temp.getRight();
    return temp.getInfo();
  }

  public BSTNode<T> removeNode(BSTNode<T> node)
  // Removes the information at node from the tree. 
  {
    T data;
    if (node.getLeft() == null)
      return node.getRight();
    else if (node.getRight() == null) 
      return node.getLeft();
    else
    {
      data = getPredecessor(node.getLeft());
      node.setInfo(data);
      node.setLeft(recRemove(data, node.getLeft()));  
      return node;
    }
  }

  public  BSTNode<T> recRemove(T target, BSTNode<T> node)
  // Removes element with info i from tree rooted at node such that
  // comp.compare(target, i) == 0 and returns true; 
  // if no such node exists, returns false. 
  {
    if (node == null)
      found = false;
    else if (comp.compare(target, node.getInfo()) < 0)
      node.setLeft(recRemove(target, node.getLeft()));
    else if (comp.compare(target, node.getInfo()) > 0)
      node.setRight(recRemove(target, node.getRight()));
    else  
    {
      node = removeNode(node);
      found = true;
    }
    return node;
  }

  public boolean remove (T target)
  // Removes a node with info i from tree such that comp.compare(target,i) == 0
  // and returns true; if no such node exists, returns false.
  {
    root = recRemove(target, root);
    return found;
  }

  public void preOrder(BSTNode<T> node, LinkedQueue<T> q)
  // Enqueues the elements from the subtree rooted at node into q in preOrder.
  {
    if (node != null)
    {
      q.enqueue(node.getInfo());
      preOrder(node.getLeft(), q);
      preOrder(node.getRight(), q);
    }
  }

  public void inOrder(BSTNode<T> node, LinkedQueue<T> q)
  // Enqueues the elements from the subtree rooted at node into q in inOrder.  
  {
    if (node != null)
    {
      inOrder(node.getLeft(), q);
      q.enqueue(node.getInfo());
      inOrder(node.getRight(), q);
    }
  }

  public void postOrder(BSTNode<T> node, LinkedQueue<T> q)
  // Enqueues the elements from the subtree rooted at node into q in postOrder.  
  {
    if (node != null)
    {
      postOrder(node.getLeft(), q);
      postOrder(node.getRight(), q);
      q.enqueue(node.getInfo());
    }
  }
  public void printTree()
  // Enqueues the elements from the subtree rooted at node into q in postOrder.  
  {
	  System.out.println("PRINTING TREE...");
	  treeHeight = maxDepth();
	  treeArray = new String[treeHeight*3][120];
	  putNode(root, 0, getLeftWidth() * 5, treeArray );
	  for (int i = 0; i < treeArray.length; i++){
	      for (int j = 0; j < treeArray[i].length; j++){
	          if (treeArray[i][j] == null){
                  System.out.printf("  ");
              }
              else{
                  System.out.printf(treeArray[i][j] + " ");
              }
          }
          System.out.println();
      }
  }

  public int maxDepth(){
      return recMaxDepth(root);
  }

  private int recMaxDepth(BSTNode<T> node){
      if (node == null)
          return 0;
      else {
          int leftTreeDepth = recMaxDepth(node.getLeft());
          int rightTreeDepth = recMaxDepth(node.getRight());

          if (leftTreeDepth > rightTreeDepth){
              return leftTreeDepth + 1;
          }
          else {
              return rightTreeDepth + 1;
          }
      }
  }

  public int getMaxWidth(){
      int height = maxDepth();
      int width = 0;
      for (int i = 0; i < height; i++){
          int currWidth = getWidth(root, i);
          if (currWidth > width){
              width = currWidth;
          }
          }
      return width;
      }

  private int getWidth(BSTNode<T> node, int level){
      if (node == null)
          return 0;
      else if (level == 1){
          return 1;
      }
      else if (level > 1){
          return getWidth(node.getLeft(), level - 1)
                  + getWidth(node.getRight(), level - 1);
      }
      return 0;
  }

  private int getLeftWidth(){

      //returns the breadth of the first left subtree
      int leftCount = 0;
      if (isEmpty())
          return 0;
      else
      {
          leftCount ++;
          BSTNode<T> node = root;
          while (node.getLeft() != null) {
              leftCount++;
              node = node.getLeft();
          }
          return leftCount;
      }
  }
  
  
  public int getRightWidth(){
      //returns the breadth of the first left subtree
      int rightCount = 0;
      if (isEmpty())
          return 0;
      else
      {
          rightCount ++;
          BSTNode<T> node = root;
          while (node.getLeft() != null) {
              rightCount++;
              node = node.getLeft();
          }
          return rightCount;
      }
  }


  public void putNode(BSTNode<T> node, int row, int column, String[][] stringArray){

      //places the node in string array
      if (treeArray[row][column] == null){
          treeArray[row][column] = ((Visitor) node.getInfo()).vname;

          if (node.getLeft() == null & node.getRight() == null){
              return;
          }
          if (node.getLeft() != null){
              if (comp.compare(node.getLeft().getInfo(), node.getInfo()) <= 0){ // left is less than equal to node
                  stringArray[row + 1][column - 1] = "/";
                  putNode(node.getLeft(), row + 2, column - 2, stringArray);
              }
              else { //left is greater than node
                  stringArray[row + 1][column + 1] = "\\";
                  putNode(node.getLeft(), row + 2, column - 2, stringArray);
              }

          }

          //recursively calls for left and right nodes - general recursive case
          if (node.getRight() != null){
              if (comp.compare(node.getInfo(), node.getRight().getInfo()) > 0){ //node is greater than node.Rightnode
                  stringArray[row + 1][column - 1] = "/";
                  putNode(node.getRight(), row + 2, column +2 , stringArray);
              }
              else {
                  stringArray[row + 1][column+1] = "\\";
                  putNode(node.getRight(), row + 2, column +2, stringArray);
              }
          }
          return; //exits the recursive call
      }
      else {
    	  int spaceToRight = 20;
//    	  Visitor v = new Visitor(treeArray[row][column]);
//    	  spaceToRight = getRightWidth()+ getLeftWidth();
//    	  spaceToRight = getRightWidth(treeArray[row][column]);
    	  System.out.println(spaceToRight + " is space tp right");
          String a = treeArray[row][column+spaceToRight];
          a += " " +( (Visitor) node.getInfo()).vname;
          treeArray[row][column+spaceToRight] = ( (Visitor) node.getInfo()).vname;
          if (node.getLeft() == null & node.getRight() == null){
              return;
          }
          if (node.getLeft() != null){
              if (comp.compare(node.getLeft().getInfo(), node.getInfo()) <= 0){ // left is less than equal to node
                  stringArray[row + 1 ][column - 1 + spaceToRight] = "/";
                  putNode(node.getLeft(), row + 2, column - 2 + spaceToRight, stringArray);
              }
              else { //left is greater than node
                  stringArray[row + 1][column + 1+spaceToRight] = "\\";
                  putNode(node.getLeft(), row + 2, column - 2 + spaceToRight, stringArray);
              }

          }

          //recursively calls for left and right nodes - general recursive case
          if (node.getRight() != null){
              if (comp.compare(node.getInfo(), node.getRight().getInfo()) > 0){ //node is greater than node.Rightnode
                  stringArray[row + 1][column - 1] = "/";
                  putNode(node.getRight(), row + 2, column +2 + spaceToRight , stringArray);
              }
              else {
                  stringArray[row + 1][column+1] = "\\";
                  putNode(node.getRight(), row + 2, column +2 + spaceToRight, stringArray);
              }
          }
          return; //exits the recursive call
      }
  }


}