import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class TestPlacar {
	
	Placar placar;
	MockArmazenamento mock;
	

	@Before
	public void setUP() throws IOException{
		mock = new MockArmazenamento();
		placar = new Placar(mock);
		
	}
	
	@Test
	public void testRegistrar1TipoDePonto(){
		placar.registrarPonto("cristiano","estrela", 10);
		
		assertEquals("cristiano possui 10 pontos do tipo estrela", placar.retornarPonto("cristiano"));
	}
	
	@Test
	public void testRegistrar2TipoDePonto(){
		placar.registrarPonto("cristiano", "estrela", 50);
		placar.registrarPonto("cristiano", "moeda", 30);
		
		assertEquals("cristiano possui 30 pontos do tipo moeda e 50 pontos do tipo estrela"
					, placar.retornarPonto("cristiano"));
	}
	
	@Test
	public void testRegistrar1Ponto2Usuarios(){
		placar.registrarPonto("cristiano", "estrela", 67);
		placar.registrarPonto("Jonas", "estrela", 89);
		
		assertEquals("cristiano possui 67 pontos do tipo estrela", placar.retornarPonto("cristiano"));
		assertEquals("Jonas possui 89 pontos do tipo estrela", placar.retornarPonto("Jonas"));
	}
	
	@Test
	public void testRetornarPontos(){
		placar.registrarPonto("cristiano", "estrela", 44);
		placar.registrarPonto("Jonas", "moeda", 67);
		placar.registrarPonto("Jonas", "estrela", 55);
		
		assertEquals("Jonas possui 67 pontos do tipo moeda e 55 pontos do tipo estrela"
				, placar.retornarPonto("Jonas"));
		assertEquals("cristiano possui 44 pontos do tipo estrela", placar.retornarPonto("cristiano"));
	}
	
	@Test
	public void testRetornarRank(){
		placar.registrarPonto("cristiano", "estrela", 44);
		placar.registrarPonto("Jonas", "moeda", 67);
		placar.registrarPonto("Jonas", "estrela", 55);
		
		assertEquals("Jonas com 55, cristiano com 44", placar.retornarRankPonto("estrela"));
		
	}

}