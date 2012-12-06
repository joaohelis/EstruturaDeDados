package br.ufpb.ed.praticas.pratica06.kruskal;

public class Aresta implements Comparable<Aresta>{
	
	private Vertice u, v;
	private int peso;
	
	public Aresta(Vertice u, Vertice v, int peso) {
		this.u = u;
		this.v = v;
		this.peso = peso;
	}

	public Vertice getU() {
		return u;
	}

	public void setU(Vertice u) {
		this.u = u;
	}

	public Vertice getV() {
		return v;
	}

	public void setV(Vertice v) {
		this.v = v;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Aresta a) {
		if (this.peso < a.peso)
			return -1;
		else if(this.peso > a.peso)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		String u = (this.u == null)? "null": this.u.getLabel();
		String v = (this.v == null)? "null": this.v.getLabel();
		return "Aresta [u=" + u + ", v=" + v + ", peso=" + peso + "]";
	}

}
