package cn.arrow.brainteasing;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphNode {
	int label;
	List<DirectedGraphNode> neighbors;
	DirectedGraphNode(int x){
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}

}
