package cn.arrow.brainteasing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class MaxMin{
	public int max;
	public int min;
	
	MaxMin(int vmax, int vmin){
		max = vmax; min = vmin;
	}
}

public class CareerCup {
	
	/*
	public List<Integer> BPCutRodSol(int[] p, int n){
		int[] r = new int[n];
		List<List<Integer>> sols = new ArrayList<List<Integer>>();
		for (int i = 0; i<n; ++i){
			int q = Integer.MIN_VALUE;
			List<Integer> cur = new ArrayList<Integer>();
			for (j = 0; j<=i; ++j){
				if (i-j>0){
					q = Math.max(q, p[j]+ r[i-j]);
				}else{
					q = Math.max(q, p[j]);
				}
			}
			r[i] = q;
		}
		return r;
	}
	*/
	
	public void recursiveActivitySelector(int[] start, int[] finish, int k, int total, Set<Integer> maxset){
		int m = k + 1;
		while (m<= total && start[m] < finish[k])
			++m;
		if (m <= total){
			maxset.add(m);
			recursiveActivitySelector(start, finish, m, total, maxset);
		}
		else
			return;
	}
	
	public int[] BPCutRod(int[] p, int n){
		int[] r = new int[n];
		//r[0] = 0;
		for (int i=0; i<n; ++i){
			int q = Integer.MIN_VALUE;
			for (int j = 0; j <= i; ++j){
				if (i==j){
					q = Math.max(q, p[j]);
				}else
					q = Math.max(q, p[j] + r[i-j-1]);
			}
			r[i] = q;
		}
		return r;
	}
	
	public int getKthMagicNumber(int k){
		if (k<=0) return 0;
		int value = 1;
		Queue<Integer> Q3 = new LinkedList<Integer>();
		Queue<Integer> Q5 = new LinkedList<Integer>();
		Queue<Integer> Q7 = new LinkedList<Integer>();
		Q3.add(3);
		Q5.add(5);
		Q7.add(7);
		for (--k; k>0; --k){
			value = Math.min(Q3.peek().intValue(), Math.min(Q5.peek().intValue(), Q7.peek().intValue()));
			if (value == Q7.peek()){
				Q7.remove();
			}else{
				if (value == Q5.peek()){
					Q5.remove();
				}else{
					Q3.remove();
					Q3.add(3*value);
				}
				Q5.add(5*value);
			}
			Q7.add(7*value);
		}
		return value;
	}
	
	public int fnDivide(int a, int b){
		int quotient = 0;
		while (a-b >= 0){
			quotient += 1;
			a -= b;
		}
		return quotient;
	}
	
	public int fnTime(int a, int b){
		if (a > b){
			int temp = a;
			a = b;
			b = temp;
		}
		int sum = 0;
		for (int i=0;i<a; ++i)
			sum += b;
		return sum;
	}
	
	public int fnNeg(int a){
		int neg = 0;
		int d = a > 0 ? 1 : -1;
		while (a != 0){
			neg += d;
			a += d;
		}
		return neg;
	}
	
	public int lcm(int a, int b){
		// gcd(a,b) * lcm(a,b) = a * b;
		return a * b / gcd(a,b);
	}
	
	public int gcd2(int a, int b){
		if (b==0) return a;
		return gcd2(b, a%b);
	}
	
	public int gcd(int a, int b){
		while (a != b){
			if (a > b)
				a = a - b;
			else
				b = b - a;
		}
		return a;
	}
	
	public List<List<Integer>> calCents(int cents){
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		List<Integer> record = new ArrayList<Integer>();
		calCents(cents, record, output, cents);
		return output;
	}
	
	public void calCents(int remaining, List<Integer> suboutput, List<List<Integer>> output, int check){
		if (remaining == 0){
			List<Integer> newrecord = new ArrayList<Integer>(suboutput);
			output.add(newrecord);
			return;
		}else{
			if (remaining >= 25 && check >=25) {
				suboutput.add(25);
				calCents(remaining - 25, suboutput, output,25);
				suboutput.remove(suboutput.size()-1);
			}
			
			if (remaining >= 10 && check >=10) {
				suboutput.add(10);
				calCents(remaining - 10, suboutput, output,10);
				suboutput.remove(suboutput.size()-1);
			}
			
			if (remaining >= 5 && check >= 5) {
				suboutput.add(5);
				calCents(remaining - 5, suboutput, output, 5);
				suboutput.remove(suboutput.size()-1);
			}			

			if (remaining >= 1) {
				suboutput.add(1);
				calCents(remaining - 1, suboutput, output, 1);
				suboutput.remove(suboutput.size()-1);
			}			
		}
	}
	
	public void printPar(int left, int right, String output)
	{
		if (left < 0 || right < 0) return; // Invalid state
		if (left ==0 && right == 0){
			System.out.println(output);
		}else{
			if (left > 0){
				printPar(left-1, right, output + "(");
			}
			if (right > left){
				//output += ")";
				printPar(left,right-1,output + ")");
			}
		}
	}
	
	public void printPar(int n){
		printPar(n,n,"");
	}
	public List<String> permuteString(String s){
		if (s == null) return null;
		List<String> output = new ArrayList<String>();
		if (s.length() == 1) {
			output.add(s);
			return output;
		}

		List<String> subpermute = permuteString(s.substring(1));
		char inserted = s.charAt(0);
		for (String sub : subpermute){

			for (int i = 0; i <= sub.length(); ++i){
				StringBuilder sb = new StringBuilder(sub);
				sb.insert(i, inserted);
				output.add(sb.toString());				
			}
		}
		return output;
	}
	
	public List<List<Integer>> subset(int[] s){
		if (s==null) return null;
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		for (int i = 0 ; i < s.length; ++i){
			List<List<Integer>> temp = new ArrayList<List<Integer>>();
			for (List<Integer> e : output)
				temp.add(new ArrayList<Integer>(e));
			for (List<Integer> e : temp)
				e.add(s[i]);
			ArrayList<Integer> cur = new ArrayList<Integer>();
			cur.add(s[i]);
			temp.add(cur);
			output.addAll(temp);
		}
		output.add(new ArrayList<Integer>());
		return output;
	}
	
	public int RobotPath(int n){
		if (n <= 0) 
			return -1;
		else if (n == 1)
			return 0;
		else
			return robotpathhelp(n, n-1) + robotpathhelp(n-1, n);
	}
	
	public int robotpathhelp(int x, int y){
		if (x==1 || y == 1)
			return 1;
		else
			return robotpathhelp(x, y-1) + robotpathhelp(x-1, y);
	}
	
	public int Fibonacci(int n){
		if (n < 0) return -1;
		if (n==0 || n == 1) return n;
		int f_2 = 0, f_1 = 1;
		int t = 2;
		do{
			int fn = f_2 + f_1;
			f_2 = f_1;
			f_1 = fn;
		}while (t++ < n);
		return f_1;
		
	}
	
	public int FibonacciRec(int n){
		if ( n < 0) return -1;
		if (n==0 || n == 1) return n;
		return Fibonacci(n-1) + Fibonacci(n-2);
	}
	
	public int bitsConverted(int x, int y){
		int output = 0;
		
		for (int c = x ^ y; c !=0; c >>=1)
			output += c & 1;
		/*int diff = x ^ y;
		while (diff > 0){
			if (diff % 2 > 0)
				++output;
			diff >>= 1;
		}*/
		return output;
	}
	
	public int setBit(int x, int index, boolean bitval){
		if (bitval){
			return ( x | 1 << index);
		}else{
			int mask = (~0 << index) - 1;
			// Or
			// mask = ~(1<<index);
			return x & mask;
		}
			
	}
	
	public int getBit(int x, int index){
		return (x & (1 << index));
	}
	
	public int getNextSmall(int x){
		int index = 0;
		int oneCounter = 0;
		//int output = 0;
		
		while(getBit(x,index) != 0){
			++index;
			++oneCounter;
		}
		while(getBit(x,index) == 0)
			++index;
		
		x = setBit(x,index,false);
		x = setBit(x,--index,true);
		

		int mask1 = 0;
		for (int i = 0; i< oneCounter-1; ++i)
			mask1 = (mask1 << 1) + 1;
		mask1 <<= index - oneCounter;
		int mask0 = ~0 << index;
		
		return (x & mask0) | mask1;
	}
	
	public int getNextBig(int x){
		int index = 0;
		int oneCounter = 0;
		int output = 0;
		while (getBit(x,index) == 0) ++index;
		while (getBit(x,index) != 0){
			++oneCounter;
			++index;
		}
		x = setBit(x, index, true);		
		//System.out.println(Integer.toBinaryString(x));
		x = setBit(x, --index, false);
		//System.out.println(Integer.toBinaryString(x));

		int mask0 = ~0 << index;
		int mask1 = 0;
		for (int i = 0; i< oneCounter-1; ++i)
			mask1 = (mask1 << 1) + 1;
		
		output = (x & mask0) | mask1;
		//System.out.println("ones:" + oneCounter);
		return output;
	}
	
	public int swapBits(int x){
		int even = 0x55555555;
		int odd = 0xAAAAAAAA;
		
		even = (even & x) << 1;
		odd = (odd & x) >> 1 ;
		
		return even | odd;
	}
	
	public String DecimalToBinary(String num){

		String output = "";
		StringBuffer decimal = new StringBuffer();
		
		try{
			int integral = Integer.parseInt(num.substring(0,num.indexOf('.')));
			while (integral>0){
				output = (integral % 2) + output;
				integral >>= 1;
			}
			double dec = Double.parseDouble("0" + num.substring(num.indexOf('.')));
			while (dec > 0){
				if (decimal.length()>64)
					throw new NumberFormatException("the number can not be represented accurately in binary");
				dec = dec * 2;
				if (dec >= 1){
					decimal.append("1");
					dec -= 1;
				}
				else
					decimal.append("0");				
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
			//output = "ERROR";
		}
		System.out.println("Decimal length is " + decimal.length());
		
		output += "." + decimal.toString();
		return output;
	}
	
	public String DecToBin(double x){
		StringBuffer output = new StringBuffer();
		while (x>0){
			if (output.length() > 32)
				return "ERROR";
			x = x * 2;
			if (x>=1){
				output.append("1");
				x -= 1;
			}
			else
				output.append("0");
		}
		return output.toString();
	}
	
	public String IntToBin(int x){
		int digit;
		String output = "";
		while (x>0){
			digit = x % 2;
			output = digit + output;
			x = x >> 1;
		}
		if (output.equals(""))
			output = "0";
		return output;
	}
	
	public String IntToBin(String dec){
		int x = 0, digit;
		try{
			x = Integer.valueOf(dec);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return "ERROR";
		}
		String output = "";
		while (x>0){
			digit = x % 2;
			output = digit + output;
			x = x >> 1;
		}
		if (output.equals(""))
			output = "0";
		return output;
	}
	
	public int bitSubstring(int m, int n, int i, int j){
		int mapped = (m << i);
		int mask = (~0  << (i+j-1)) + ((1 << i) - 1);
		int result = n & mask | mapped;
		return result;		
	}
	
	/* 
	 *******************************************
	 * To be tested
	 *  */
	
	public boolean containsTree(TreeNode t1, TreeNode t2){
		if (t2 == null)
			return true;
		else
			return subTree(t1,t2);
	}
	
	private boolean subTree(TreeNode t1, TreeNode t2){
		if (t1 == null) return false;
		if (t1.val == t2.val){
			if (matchTree(t1,t2)) return true;
		}
		return subTree(t1.left, t2) || subTree(t1.right,t2);
	}
	
	private boolean matchTree(TreeNode t1, TreeNode t2){
		if (t1==null || t2==null)
			return t1==t2;
		if (t1.val != t2.val) return false;
		return matchTree(t1.left,t2.left) && matchTree(t1.right,t2.right);
	}
	
	/*
	public TreeNode commAncestor(TreeNode root, TreeNode a, TreeNode b){
		
	}*/
	
	public TreeNode inorderSec(TreeNode node){
		if (node==null) return null;
		TreeNode p=null;
		if (node.parent == null || node.right != null){
			p = leftMostChild(node.right);
		} else{
			while ((p=node.parent) != null)
				node = p;
		}
		return p;
	}
	
	private TreeNode leftMostChild(TreeNode n){
		if (n==null) return n;
		while (n.left != null)
			n = n.left;
		return n;
	}
	
	/* ****************************************** */
	
	public List<List<TreeNode>> levelTraversal(TreeNode root){
		List<List<TreeNode>> output = new ArrayList<List<TreeNode>>();
		List<TreeNode> level = new ArrayList<TreeNode>();
		if (root == null) return output;
		TreeNode magic = new TreeNode(0);
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root); q.add(magic);
		
		while (!q.isEmpty()){
			TreeNode tn = q.poll();
			if (tn == magic){
				output.add(level);
				level = new ArrayList<TreeNode>();
			}else{
				level.add(tn);
				if (tn.left != null) q.add(tn.left);
				if (tn.right != null) q.add(tn.right);				
			}				
		}
		return output;
	}
	
	public TreeNode sortedArrayToBST(int[] num) {
		if (num == null) return null;
		return genBSThelper(num, 0, num.length-1);
	}
	
	private TreeNode genBSThelper(int[] A, int s, int e){
		if (s>e)
			return null;
		int mid = (s+e)/2;
		TreeNode root = new TreeNode(A[mid]);
		root.left = genBSThelper(A,s, mid-1);
		root.right = genBSThelper(A,mid+1,e);
		return root;
	}
	
	public boolean isConnected(UndirectedGraphNode a, UndirectedGraphNode b){
		if (a==null || b==null) return false;
		return isRouted(a,b) || isRouted(b,a);
		
	}
	
	public boolean isRouted(UndirectedGraphNode src, UndirectedGraphNode dst){
		if (src == null || dst == null) return false;
		boolean output = false;
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		q.add(src); visited.add(src);
		while(!q.isEmpty()){
			UndirectedGraphNode cur = q.poll();
			for (UndirectedGraphNode neighbor : cur.neighbors){
				if (neighbor == dst) return true;
				if (!visited.contains(neighbor)){
					visited.add(neighbor);
					q.add(neighbor);
				}
			}
		}
		
		return output;
	}
	
	public boolean isBalance(TreeNode root){
		if (root == null) return true;
		MaxMin rootmm = new MaxMin(0,0);
		this.traverse(root, rootmm);
		
		if (rootmm.max - rootmm.min > 1)
			return false;
		else
			return true;
	}
	
	private void traverse(TreeNode root, MaxMin maxmin){
		MaxMin lmaxmin = new MaxMin(Integer.MIN_VALUE,Integer.MAX_VALUE);
		MaxMin rmaxmin = new MaxMin(Integer.MIN_VALUE,Integer.MAX_VALUE);
		if (root.left == null && root.right == null){
			maxmin.max = 0;
			maxmin.min = 0;
			return;
		}
		
		if (root.left != null)
			traverse(root.left,lmaxmin);
		if (root.right != null)
			traverse(root.right,rmaxmin);
		
		maxmin.max = lmaxmin.max > rmaxmin.max ? lmaxmin.max + 1 : rmaxmin.max + 1;  
		maxmin.min = lmaxmin.min < rmaxmin.min ? lmaxmin.min + 1 : rmaxmin.min + 1;
		return;
	}
	
	public boolean isRotation(String s1, String s2){
		if (s1.length() != s2.length())
			return false;
		
		String s2s2 = s2+s2;
		return isSubstring(s1,s2s2);
	}
	
	public boolean isSubstring(String s1, String s2){
		int i,j;
		boolean result = true;
		
		if (s1.length() > s2.length())
			return false;
		
		for (i=0; i<s2.length(); ++i){
			result = true;
			if (i+s1.length() > s2.length()) return false;
			for(j=0; j<s1.length(); ++j)
				if(s1.charAt(j) != s2.charAt(i+j))
				{
					result = false;
					break;
				}
			if (result)
				break;
		}
		return result;
	}
	
	public void setZeros(int[][] matrix){
		int[] row = new int[matrix.length];
		int[] col = new int[matrix[0].length];
		
		for (int i=0; i<matrix.length; ++i)
			for (int j=0; j<matrix[0].length; ++j)
				if (matrix[i][j] == 0){
					row[i] = 1;
					col[j] = 1;
				}
		for (int i=0; i<matrix.length; ++i)
			for (int j=0; j<matrix[0].length; ++j)
				if (row[i]==1 || col[j]==1)
					matrix[i][j] = 0;		
	}
	
	public String replaceSpace(String str){
		int spaceCount = 0, newLength, i = 0;
		for (i = 0; i < str.length(); i++)
			if (str.charAt(i) == ' ')
				spaceCount++;
		
		newLength = str.length() + spaceCount * 2;
		char[] new_str = new char[newLength];
		
		for (i = str.length() - 1; i >= 0; --i) {
			if (str.charAt(i) == ' ') {
				new_str[newLength-1] = '0';
				new_str[newLength - 2] = '2';
				new_str[newLength - 3] = '%';
				newLength -= 3;
			} else {
			new_str[newLength - 1] = str.charAt(i);
			--newLength;
			}
		}
		
		return String.valueOf(new_str);
	}


	public boolean anagram(String s, String t){
		if (s.length() != t.length()) return false;
		int[] letters = new int[256]; // 8-bit ASC II{}
		
		int num_unique_char = 0;
		int num_complete_t = 0;
		
		for (int i=0; i<s.length(); ++i){
			if (letters[s.charAt(i)] == 0)
				++num_unique_char;
			++letters[s.charAt(i)];
		}
		
		for (int i=0; i<t.length(); ++i){
			if (letters[t.charAt(i)] == 0)
				return false;
			if (--letters[t.charAt(i)] == 0){
				++num_complete_t;
				
				if (num_unique_char == num_complete_t)
					return i == t.length()-1;
			}
		}
		
		return false;
	}
	
	public void removeDuplicates2(char[] str){
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		
		boolean[] hit = new boolean[256]; // 8-bit ASCII
		for (int i=0; i<256; ++i)
			hit[i] = false;
		hit[str[0]] = true;
		int tail = 1;
		
		for (int i=1; i<len; ++i){
			if (!hit[str[i]]){ // Unique char
				str[tail] = str[i]; // put the unique char to the end of the char array
				hit[str[i]] = true;
				++tail;
			}
		}
		
		if (tail < len)
			str[tail] = 0;
	}

	public void removeDuplicates(char[] str){
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		
		int tail = 1;
		for (int i=1; i< len; ++i){
			int j;
			for (j=0; j<tail; ++j)
				if (str[i] == str[j]) break;
			if (j == tail){
				str[tail] = str[i];
				++tail;
			}
		}
		
		if (tail < len)
			str[tail] = 0;
	}
	
	
	public void printASCII(){
		for (int i = 0; i< 256; i++){
			char ch= (char)i;
			System.out.println(i + ":" + ch);
		}
	}

	/**
	 * Determine if a string has all unique characters. Can be extended to check all chars.
	 * No boundary is checked.
	 * @param str
	 * @return
	 */
	public boolean checkUniqueLetters(String str){
		int checker = 0;
		
		for(int i = 0; i<str.length(); i++){
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= 1 << val;
		}		
		return true;
	}
}
