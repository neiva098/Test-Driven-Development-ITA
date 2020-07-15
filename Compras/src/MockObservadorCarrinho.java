import static org.junit.Assert.assertEquals;

public class MockObservadorCarrinho implements ObservadorCarrinho {
	
	private String nomeRecebido;
	private int valorRecebido;
	private boolean isToDarPau = false;

	public void verificaRecebimentoProduto(String nomeEsperado, int valorEsperado) {
		// TODO Auto-generated method stub
		if(isToDarPau) {
			throw new RuntimeException("Problema no mock");
		}
		assertEquals(nomeEsperado, nomeRecebido);
		assertEquals(valorEsperado, valorRecebido);
		
	}

	@Override
	public void produtoAdicionado(String nome, int valor) {
		// TODO Auto-generated method stub
		nomeRecebido = nome;
		valorRecebido = valor;
	}

	public void daPauAiZe() {
		// TODO Auto-generated method stub
		isToDarPau = true;
		
	}


}
