import java.util.*;
/** 
 * Expression Tree.
 *
 * This class processes parenthetical infix expressions like
 *
 * ((8-(4+3))/(4-3))*6
 *
 * This uses a left-to-right processing technique,
 * with particular actions corresponding to 
 * numbers, operators, or ().
 * It parses the expression and builds an ExpressionTree
 * to represent it.
 */
public class ExpressionTree { 


	// Main method

	public static void main(String[] args) { 

		
		Map<String,Integer> test = new TreeMap<String,Integer>();

		test.put("2+2",									4);
		test.put("(3+4)/(4+3)",							1);
		test.put("2-((3+4)/(4+3))",						1);
		test.put("((8-1)-(4-3))",						6);
		test.put("(8-1)-(4-3)",							6);
		test.put("((8-1)-(4-3))*(8+(4-1))",				66);
		test.put("((8-1)-(4-3))*((8-(4/(4+4)))+4)",		72);
		test.put("2+(2+(2+(2+(2+(2+2)))))",				14);
		test.put("(((((2+2)+2)+2)+2)+2)",				12);
		test.put("4*((8-1)-(4-3))",						24);

		for(String expr : test.keySet()) { 
			System.out.println("About to parse the expression:");
			System.out.println(expr);

			ExpressionTree t = new ExpressionTree();		
			t.parse(expr);
			System.out.println(t);
		}

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
		public int nChildren() { 
			int nc = 0;
			if(left!=null) nc++;
			if(right!=null) nc++;
			return nc;
		}
		public String toString() { 
			StringBuffer sb = new StringBuffer();
			if(this.left!=null) { 
				sb.append("(");
				sb.append(this.left.element.toString());
				sb.append(")-- ");
			}
			sb.append(this.element.toString());
			if(this.right!=null) { 
				sb.append(" --(");
				sb.append(this.right.element.toString());
				sb.append(")");
			}
			return sb.toString();
		}
		public Character getElement() { return this.element; }
		public void setElement(char v) { this.element = v; }
		public void setLeft(TreeNode newl) { this.left = newl; }
		public void setRight(TreeNode newr) { this.right = newr; }
		public boolean is_operator(){ return is_operator; }
		public boolean is_operand(){ return is_operand; }
		public boolean is_numeric(){ return is_numeric; }
	}



	///////** Utility stack node class.
	////// */
	//////class StackNode {
	//////	TreeNode treeNode;
	//////	StackNode next;
	//////	/** stack node constructor - 
	//////	 * specify an expression tree node to store */
	//////	public StackNode(TreeNode tn) { 
	//////		this.treeNode = tn;
	//////		this.next = null;
	//////	}
	//////}

	///////** Utility stack field. */
	//////private static StackNode top;

	///////** Utility stack method - push - SECRET. */
	//////private void push(TreeNode ptr) { 
	//////	// top is the utility pointer above.
	//////	if(top == null) { 
	//////		top = new StackNode(ptr);
	//////	} else {
	//////		StackNode nn = new StackNode(ptr);
	//////		nn.next = top;
	//////		top = nn;
	//////	}
	//////}

	///////** Utility stack method - pop - SECRET. */
	//////private TreeNode pop() {
	//////	StackNode oldtop = top;
	//////	top = top.next;
	//////	oldtop.next = null;
	//////	return oldtop.treeNode;
	//////}

	//////private TreeNode peek() { 
	//////	return top.treeNode;
	//////}


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
	

	/** Parse a nested parenthetical infix expression passed in,
	 * and store the resulting parsed expression in this ExpressionTree.
	 */
	public void parse(String expression) { 

		// Initialize input queue
		Queue<Character> qin = new LinkedList<Character>();
		for(char c : expression.toCharArray()) { 
			Character mychar = new Character(c);
			qin.add(mychar);
		}

		// Expression stack
		Stack<TreeNode> st = new Stack<TreeNode>();

		boolean startNewExpression = true;
		while(!qin.isEmpty()){ 

			// Examine input queue 
			// one char at a time
			Character c = qin.remove();

			if(isNumeric(c)) {
				// Character is numeric - make single node expression tree.
				TreeNode node = new TreeNode(c);

				// If we have opened a new set of parentheses,
				if(startNewExpression) { 
					st.push(node);
					startNewExpression = false;

				} else {
					// If we have an expression on the stack already,
					if(st.size()>0){ 
						// If the expression on the stack is an operator 
						// and it has 1 child,
						TreeNode peek = st.peek();
						if(peek.is_operator()) { 
							if(peek.nChildren()==1) { 
								peek.setRight(node);
								// No need to push - 
								// peek stays on the stack
							} else {
								throw new IllegalStateException("improperly formatted operator");
							}
						} else {
							// peek is not operator
							throw new IllegalStateException("number followed by number");
						}
					}
				}
				// end if numeric

			} else if(isOperator(c)) { 
				TreeNode node = new TreeNode(c);
				node.setLeft(st.pop());
				st.push(node);

			} else if(c.equals('(')) { 
				startNewExpression = true;

			} else if(c.equals(')')) { 
				// Close the expression - 
				// and expand the purview of the next operator 
				if(st.size()>1) { 
					TreeNode top = st.pop();
					TreeNode peek = st.peek();
					if(peek.right==null) { 
						peek.right = top;
					}
				}
			}
		}

		// Mop up
		while(st.size()>1) { 
			TreeNode top = st.pop();
			TreeNode peek = st.peek();
			if(peek.right!=null) { 
				throw new IllegalStateException("Two consecutive operators!");
			}
			peek.right = top;
		}

		this.root = st.pop();

		System.out.println("Finished parsing the expression tree.");
	}



	/** Send the expression tree to a String. */
	public String toString() {

		StringBuffer sb1 = new StringBuffer();
		subtreeOneliner(sb1, this.root);

		StringBuffer sb2 = new StringBuffer();
		subtreeDFT(sb2, this.root, 0);

		return sb1.toString() + "\n\n" + sb2.toString();
	}

	/** utility method: spaces */
	private String spaces(int n) { 
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<n; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}



	/** Print a subtree on multiple lines using a recursive depth-first traversal method.
	 * This is a preorder traversal, meaning the visit action is performed on the node
	 * before it is performed on children (if any).
	 */
	private void subtreeDFT(StringBuffer sb, TreeNode subtree, int depth) {
		// Secure your own Node's oxygen mask first, then take care of your children's.
		String mid = subtree.getElement().toString();
		sb.append(spaces(depth*4));
		sb.append(mid);
		sb.append("\n");

		int nchildren = subtree.nChildren();
		if(nchildren>0) { 
			subtreeDFT(sb, subtree.left, depth+1);
			subtreeDFT(sb, subtree.right, depth+1);
		}
	}


	/** Print a subtree on a single line using nested parenthetical notation. 
	 * This is an in-order traversal that wraps each expression in parentheses,
	 * and proceeds from the left element to the center operator to the right element. 
	 * */
	private void subtreeOneliner(StringBuffer sb, TreeNode subtree) {
		String mid = subtree.getElement().toString();

		int nchildren = subtree.nChildren();
		if(nchildren==0) { 
			// Leaf node - return string of node only
			sb.append(mid);

		} else {

			// If not a leaf node, print out:
			// ( left, mid, right )

			sb.append("(");

			subtreeOneliner(sb, subtree.left);

			sb.append(mid);

			subtreeOneliner(sb, subtree.right);

			sb.append(")");
		}
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



