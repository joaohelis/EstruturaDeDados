package br.ufpb.ed.praticas.pratica06.minimumPath;

import br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric.HeapElement;

public class Vertex implements HeapElement{
	
	private int id;
	private String label;
	private Vertex parent;
	private Integer weight;
	
	public Vertex(int id, String label){
		this(id, label, null, Integer.MAX_VALUE);
	}
	
	public Vertex(int id, String label, Vertex parent, int weight) {
		this.id = id;
		this.label = label;
		this.parent = parent;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public Integer getComparator() {
		return this.weight;
	}

	@Override
	public void setComparator(Integer w) {
		this.weight = w;
	}

	@Override
	public int compareTo(HeapElement o) {
		if (getComparator()  < o.getComparator())
			return -1;
		else if(getComparator() > o.getComparator())
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		String parent = (this.parent == null)? "null": this.parent.getLabel();
		return "Vertex [id=" + id + ", label=" + label + ", parent=" + parent
				+ ", weight=" + weight + "]";
	}
	
	
	
}
