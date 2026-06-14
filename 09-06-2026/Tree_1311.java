class  node_1311
{
	int data;
	
	node_1311 left,right;
	
	node_1311(int data)
	{
		
		this.data=data;
	}
	
}
public class Tree_1311 
{
	public static void main(String[] args) {
		
	
	node_1311 root = new node_1311(1);
	
   root.left = new node_1311(2);
   root.right =new node_1311(3);
   
   root.left.left = new node_1311(4);
   root.left.right =new node_1311(5);
   
   root.left.left.left = new node_1311(6);
   
   root.left.left.left.left =new node_1311(7);
   
   root.left.left.left.left.right =new node_1311(8);
   
   System.out.println("height : "+height(root));
}
//a method that receives a node and returns the height of the tree from that node

static int height(node_1311 root) {
	//if no node exist,height is zero
	if(root == null) {
		return 0;
	}
	//recursive call for left subtree
	//calculate height of left subtree 
	int leftHeight=height(root.left);
	
	int rightHeight=height(root.right);
	//take larger height
	//add 1 for current node
	return Math.max(leftHeight, rightHeight)+1;
	
  }
}

//THE MOST IMPORTANT THING IS TO REMEMBER IS THAT
//RECURSION FIRST GOES ALL THE WAY DOWN TO THE 
//DEEPEST NODE(9), and only then Starts calculation
//heights while coming backup ( backtracking phase )