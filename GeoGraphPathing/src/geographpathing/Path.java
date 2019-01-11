package geographpathing;

import java.util.List;

public class Path {
	public Path(List<Node> nodes){
		this.nodes = nodes;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		for(Node node : getNodes()){
			if(!str.toString().equals("")){
				str.append(" -> ");
			}
			
			str.append(node.getID());
		}
		
		return str.toString();
	}
	
	public Node last(){
		return nodes.get(nodes.size() - 1);
	}
	
	public int length(){
		return nodes.size();
	}
	
	public List<Node> getNodes(){
		return nodes;
	}
	
	List<Node> nodes;
}
