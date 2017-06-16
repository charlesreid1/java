import java.util.*;

public class PostfixExpressionTree {
	public static void main(String[] args) { 
		//String expression = "a b + c d e + * *";
		String expression = "1 5 + 3 2 4 + * *";
		// 108
		PostfixParser p = new PostfixParser();
		p.parse(expression);
		System.out.println(p);
	}
}

class PostfixParser {
	// This class is a tree.

	private TreeNode root; // Need a pointer to the tree root 
	private String expression; // save the expression

	/** Empty constructor - the magic happens in parse(). */
	public PostfixParser() {}

	/** Expression access method. */
	public String getExpression() { return this.expression; } 

	/** Parse the expression into a tree. */
	public void parse(String expr) {
		this.expression = expr;

		// Populate a queue of Characters with expression
		Queue<Character> q = new LinkedList<Character>();
		char[] expr_arr = expr.toCharArray();
		for(int i=0; i<expr_arr.length; i++) { 
			Character c = new Character(expr_arr[i]);
			q.add(c);
		}

		// Expression builder stack
		Stack<TreeNode> builder = new Stack<TreeNode>();

		while(!q.isEmpty()){ 

			// Examine input queue 
			// one char at a time
			Character c = q.remove();

			if(isNumeric(c)) {
				// Character is numeric - make single node expression tree.
				TreeNode node = new TreeNode(c);

				// Push node onto stack
				builder.push(node);

			} else if(isOperator(c)) { 
				TreeNode node = new TreeNode(c);

				// Order important for subtraction/division
				node.setRight(builder.pop());
				node.setLeft( builder.pop());

				// Left and right are off the builder stack.
				// Add the builder node to the builder stack.
				builder.push(node);
			}
		}
		// queue is empty, tree is built

		if(builder.size()>1) { 
			System.out.println("Error parsing expression: non-empty stack.");
		} else {
			this.root = builder.pop();
		}
	}




	/** Send the expression tree to a String. */
	public String toString() {

		StringBuffer sb = new StringBuffer();
		subtreeDFT(sb, this.root, 0);

		return sb.toString();
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

}
