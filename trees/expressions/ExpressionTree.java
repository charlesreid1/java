import java.util.*;
/** 
 * Expression Tree.
 */
public class ExpressionTree { 






	// Main method

	public static void main(String[] args) { 
		//String expr = "(3+4)/(4+3)";
		String expr = "2-((3+4)/(4+3))";
		ExpressionTree t = new ExpressionTree();		
		t.parse(expr);
		
	}





	///////////////////////////////////




	/** Expression Tree Node.
	 *
	 * Could make this an <E>, 
	 * or distinguish internal/external,
	 * operator and nummber nodes,
	 * but will make these all expressions.
	 *
	 * Could make this process Strings, 
	 * but will assume everything is single-digit,
	 * single chars only.
	 */
	class TreeNode {
		Character element;
		boolean is_operator, is_operand, is_numeric;
		TreeNode left, right;
		public TreeNode(Character e) { 
			this.element = e;
			if(isOperator(e)) {
				this.is_operator = true;
			} else {
				this.is_operand = true;
            }

			if(isNumeric(e)) { 
				this.is_numeric = true; 
			}
		}
		public void setElement(char v) { this.element = v; }
		public void setLeft(TreeNode newl) { this.left = newl; }
		public void setRight(TreeNode newr) { this.right = newr; }
		public boolean is_operator(){ return is_operator; }
		public boolean is_operand(){ return is_operand; }
		public boolean is_numeric(){ return is_numeric; }
	}



	/** Utility stack node class.
	 */
	class StackNode {
		TreeNode treeNode;
		StackNode next;
		/** stack node constructor - 
		 * specify an expression tree node to store */
		public StackNode(TreeNode tn) { 
			this.treeNode = tn;
			this.next = null;
		}
	}

	/** Utility stack field. */
	private static StackNode top;

	/** Utility stack method - push - SECRET. */
	private void push(TreeNode ptr) { 
		// top is the utility pointer above.
		if(top == null) { 
			top = new StackNode(ptr);
		} else {
			StackNode nn = new StackNode(ptr);
			nn.next = top;
			top = nn;
		}
	}

	/** Utility stack method - pop - SECRET. */
	private TreeNode pop() {
		StackNode oldtop = top;
		top = top.next;
		oldtop.next = null;
		return oldtop.treeNode;
	}

	private TreeNode peek() { 
		return top.treeNode;
	}


	/////////////////////////////////////////////////




	// ExpressionTree class

	// Need a pointer to the tree root
	TreeNode root;

	// Methods:
	// - isDigit(char c)
	// - isOperator(char c)
	// - pop()
	// - push()
	// - attach nodes
	//
	// - insert():
	//		- if digit: n = new node, push n
	//		- if operator: n = new node, n.left=pop, n.right=pop, push n
	//
	//
	// nodes, not trees.
	//
	public ExpressionTree() {
		root = null;
	}
	
	public void parse(String expression) { 

		// Initialize the input queue
		Queue<Character> qin = new LinkedList<Character>();
		for(char c : expression.toCharArray()) {
			Character mychar = new Character(c);
			qin.add(mychar);
		}

		//// So far so good:
		//System.out.println(qin);

		// Algorithm:
		//
		// while there are tokens in the queue:
		//		if token is a number:
		//			add as new (left) child in output expression tree
		//			or, 
		//			add as right child if parent already exists in expression tree
		//
		//		if token is operator:
		//			add operator as parent of output expression tree
		//
		//		if token is (,
		//			push onto operator stack.
		//
		//		if token is ),
		//			while operator @ top of stack not (,
		//				pop operators from top of stack onto output queue
		//			pop left bracket from stack
		// clean up:
		//		pop the remaining ))s


		// We are building a "tree"
		// by assembling TreeNodes by hand
		Stack<TreeNode> st = new Stack<TreeNode>();

		while(!qin.isEmpty()){ 

			// Examine the input queue 
			// one char at a time.
			Character c = qin.remove();

			if(isNumeric(c)) { 

				// Character is numeric:
				// make a single-node expression tree.
				TreeNode node = new TreeNode(c);

				// Peek and check if there is 
				// an operator on the stack.
				if(st.size()>0) { 

					TreeNode pk = st.peek();

					if(pk.is_operator()) {
						// If so, pop it.
						pk = st.pop();
						// Add the new numeric character 
						// node as the right child
						pk.right = node;

						// Push the operator (parent) onto the stack.
						st.push(pk);

						// Good to go
					} else {
						throw new IllegalArgumentException("Malformed input: number/expression and number/expression without operator");
					}

				} else {
					// Empty stack
					st.push(node);
				}


			} else if(isOperator(c)) {

				// check if expression already on stack

				TreeNode node = new TreeNode(c);

				// Operator becomes parent of output expression tree
				node.left = st.pop();
				
				// Don't know what right is yet...
				st.push(node);

			} else if(c.equals('(')) {
				// Just continue
				System.out.println("open (");

			} else if(c.equals(')')) {
				// ends an expression
				System.out.println("close )");

			}
		}
		this.root = st.pop();

	}

	public String toString() {
		String res = subtreeToString(this.root,0);
	}

	private String subtreeToString(TreeNode subtree, int depth) { 
		// if this is a leaf node,
		// return the string only 
		String middle = subtree.getElement();
		// needs way to get number of children
		// deja vu tree stuff -
		// do we have a way to pass in a position?
		// no, nodes only. quick,
		// method to count number of children.
		// nchildren = 0;
		// if(left!=null) nchildren++; 
		// if(right!=null) nchildren++; 
		//
		//
		// else,
		String left = "(";
		String middle = subtreeToString(left);
		String middle = subtree.getElement();
		String middle = subtreeToString(right);
		String right = ")";
	}


	public static boolean isNumeric(Character c) { 
		try { 
			double d = Double.parseDouble(c.toString()); 
		} catch(NumberFormatException nfe) {
			return false;  
		}  
		return true;  
	}

	public static boolean isOperator(Character c) { 
		Set<Character> s = new HashSet<Character>();
		s.add('+');
		s.add('-');
		s.add('*');
		s.add('/');
		s.add('%');
		return s.contains(c);
	}

}










