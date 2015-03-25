package cn.arrow.brainteasing;

import java.util.List;

public interface Graph {

	public void addNode(DirectedGraphNode node);
	public void removeNode(DirectedGraphNode node);
	public List<DirectedGraphNode> getAllNodes();
}
