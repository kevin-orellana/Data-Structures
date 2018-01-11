
public class ProgProject3 {
	public static void main(String[] args) {
		String[] inputs = {
				"DBACGHJK",
				"DACBEFMLGHJK",
				"JABCDEFISRQPON",
				"MHSBWKYACTBYAZ",
				"BAPOSRUTYXZ",
				"DICTIONARY",
				"BINARYSEARCHTREE"

		};
		for (int k=0; k<inputs.length;k++) {
		BinarySearchTree<Visitor> mytree = new BinarySearchTree<Visitor>();
		for (int i =0 ; i< inputs[k].length(); i++) {
			Visitor v = new Visitor("C");
			v.vname = inputs[k].substring(i, i+1);
			mytree.add(v);
		}
		System.out.println("\n\n");
		System.out.println("Tree for input: " + inputs[k]);
			System.out.println("Height of Tree No. " + k + ": " + mytree.maxDepth());
			System.out.println("Most nodes in any level: " + mytree.getMaxWidth());
//			System.out.println("Left width is " + mytree.getLeftWidth());
		mytree.printTree();
		}
		
	}
	 
}
