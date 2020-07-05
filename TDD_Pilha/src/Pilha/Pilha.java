package Pilha;

public class Pilha {
	
	private Object[] elementos = new Object[10];
	private int quantidade = 0;

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
		this.elementos[quantidade] = elemento;
		this.quantidade++;
	}

	public Object topo() {
		// TODO Auto-generated method stub
		return this.elementos[quantidade -1];
	}

	public Object desempilha() {
		Object desempilhado = this.topo();
		
		this.quantidade--;
		
		return desempilhado;
	}

}
