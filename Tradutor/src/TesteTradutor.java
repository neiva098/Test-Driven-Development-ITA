import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TesteTradutor {
	private static Tradutor t;

	@Before
	public void inicializaTradutor() {
		t = new Tradutor();
	}

	@Test
	public void tradutorSemPalavras() {
		assertTrue(t.estaVazio());
	}
	
	@Test
	public void umaTraducao() {		
		t.adicionaTraducao("bom", "good");
	
		assertFalse(t.estaVazio());

		assertEquals("good", t.traduzir("bom"));
	}
	
	@Test
	public void duasTraducoes() {
		t.adicionaTraducao("bom", "good");
		t.adicionaTraducao("mau", "bad");
	
		assertEquals("good", t.traduzir("bom"));
		assertEquals("bad", t.traduzir("mau"));
	}
	
	@Test
	public void duasTraducoesMesmaPalavrea() {
		t.adicionaTraducao("bom", "good");
		t.adicionaTraducao("bom", "nice");
	
		assertEquals("good, nice", t.traduzir("bom"));
	}
	
	@Test
	public void traduzirFrase() {
		t.adicionaTraducao("guerra", "war");
		t.adicionaTraducao("é", "is");
		t.adicionaTraducao("ruim", "bad");
	
		assertEquals("war is bad", t.traduzirFrase("guerra é ruim"));
	}
	
	@Test
	public void traduzirFrase2Traducoes() {
		t.adicionaTraducao("guerra", "war");
		t.adicionaTraducao("é", "is");
		t.adicionaTraducao("ruim", "bad");
		t.adicionaTraducao("ruim", "perverse");
	
		assertEquals("war is bad", t.traduzirFrase("guerra é ruim"));
	}


}
