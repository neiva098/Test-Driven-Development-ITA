package Pilha;

public class Pilha {
	
	private Object[] elementos;
	private int quantidade;
	private int maximo;
	
	public Pilha(int length) {
		this.elementos = new Object[length];
		this.quantidade = 0;
	}

	public boolean estaVazia() {
		// TODO Auto-generated method stub
		return this.quantidade == 0;
	}

	public Integer tamanho() {
		// TODO Auto-generated method stub
		return this.quantidade;
	}

	public void empilha(Object elemento) {
		// TODO Auto-generated method stub
		if(this.elementos.length == quantidade) throw new PilhaCheiaException("Não é possível empilhar");
		
		this.elementos[quantidade] = elemento;
		this.quantidade++;
	}

	public Object topo() {
		// TODO Auto-generated method stub
		return this.elementos[quantidade -1];
	}

	public Object desempilha() {
		if(estaVazia()) throw new PilhaVaziaException("Não é possível desempilhar");
		
		Object desempilhado = this.topo();
		
		this.quantidade--;
		
		return desempilhado;
	}

}
