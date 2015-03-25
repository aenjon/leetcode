package cn.arrow.brainteasing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import java.util.List;

public class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x){
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
	
	public String BFSTraverse(){
		return BFSTraverse(this);
	}
	/**
	 * Breath-First-Search Traverse
	 * @param node
	 * @return
	 */
	public String BFSTraverse(UndirectedGraphNode node){
		String output = "";
		if (node == null) return "";
		
		Set<UndirectedGraphNode> hs = new HashSet<UndirectedGraphNode>();
		hs.add(node);
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		q.add(node);
		
		while(!q.isEmpty()){
			UndirectedGraphNode cur = q.poll();
			output = output + cur.label + ",";
			for (UndirectedGraphNode neighbor : cur.neighbors){
				if (!hs.contains(neighbor)){
					hs.add(neighbor);
					q.add(neighbor);
				}
			}
		}
		return output;
	}

	public String DFSTraverse(){
		return DFSTraverse(this);
	}
	
	/**
	 * Depth-First-Search
	 * @param node
	 * @return
	 */
	public String DFSTraverse(UndirectedGraphNode node){
		String output = "";
		if (node==null) return output;
		
		Set<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
		set.add(node);
		Stack<UndirectedGraphNode> st = new Stack<UndirectedGraphNode>();
		st.push(node);
		
		while (!st.isEmpty()){
			UndirectedGraphNode cur = st.pop();
			output = output + cur.label + ",";
			for (UndirectedGraphNode neighbor : cur.neighbors){
				if (!set.contains(neighbor)){
					st.push(neighbor);
					set.add(neighbor);
				}
			}
		}
		return output;
	}
}
