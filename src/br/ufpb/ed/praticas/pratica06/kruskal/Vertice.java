package br.ufpb.ed.praticas.pratica06.kruskal;

public class Vertice {
	
	private int id,
				idConjuntoAssociado;
	private String label;

	public Vertice(int id, String label) {
		this(id, 0, label);
	}
	
	public Vertice(int id, int idConjuntoAssociado, String label) {
		this.id = id;
		this.idConjuntoAssociado = idConjuntoAssociado;
		this.label = label;
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

	public int getIdConjuntoAssociado() {
		return idConjuntoAssociado;
	}

	public void setIdConjuntoAssociado(int idConjuntoAssociado) {
		this.idConjuntoAssociado = idConjuntoAssociado;
	}

	@Override
	public String toString() {
		return "Vertice [id=" + id + ", idConjuntoAssociado="
				+ idConjuntoAssociado + ", label=" + label + "]";
	}
	
	

}
