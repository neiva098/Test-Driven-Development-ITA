import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;


public class TestAramazenamento {

	Placar placar;
	Armazenamento arm;

	@Before
	public void setUP() throws IOException {
		arm = new Armazenamento("Placar");

		placar = new Placar(arm);
	}
	
	@After
	public void reset(){
		File file = new File("Placar.txt");
		file.delete();
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
		placar.registrarPonto("cristiano", "estrela", 30);
		placar.registrarPonto("Jonas", "moeda", 67);
		placar.registrarPonto("Jonas", "estrela", 55);
		
		assertEquals("Jonas possui 67 pontos do tipo moeda e 55 pontos do tipo estrela"
				, placar.retornarPonto("Jonas"));
		assertEquals("cristiano possui 74 pontos do tipo estrela", placar.retornarPonto("cristiano"));
	}
	
	@Test
	public void testRetornar1PontoPorTipo(){
		placar.registrarPonto("cristiano", "estrela", 44);
		
		assertEquals("cristiano possui 44 pontos do tipo estrela", 
					placar.retornarPontoPorTipo("cristiano", "estrela") );
		
	}
	
	public void testRetornar2PontoPorTipo(){
		placar.registrarPonto("Jonas", "moeda", 67);
		placar.registrarPonto("Jonas", "estrela", 55);
		placar.registrarPonto("Jonas", "moeda", 15);
		placar.registrarPonto("Jonas", "moeda", 44);
		
		assertEquals("Jonas possui 126 pontos do tipo moeda", 
					placar.retornarPontoPorTipo("Jonas", "moeda") );
		
	}
	
	@Test
	public void testRetornarUsuarios(){
		placar.registrarPonto("cristiano", "estrela", 44);
		placar.registrarPonto("Jonas", "moeda", 67);
		placar.registrarPonto("Jonas", "estrela", 55);
		placar.registrarPonto("cristiano", "estrela", 55);
		placar.registrarPonto("Carlos", "estrela", 55);
		placar.registrarPonto("Rejane", "estrela", 55);
		placar.registrarPonto("Jonas", "estrela", 55);
		
		assertEquals("cristiano, Jonas, Carlos, Rejane", placar.retornarUsuarios());
	}
	
	@Test
	public void testRetornarRank(){
		placar.registrarPonto("cristiano", "estrela", 44);
		placar.registrarPonto("Jonas", "moeda", 67);
		placar.registrarPonto("Jonas", "estrela", 55);
		placar.registrarPonto("cristiano", "estrela", 55);
		placar.registrarPonto("Carlos", "estrela", 55);
		placar.registrarPonto("Rejane", "estrela", 55);
		placar.registrarPonto("Jonas", "estrela", 55);
			
		
		assertEquals("Jonas com 110, cristiano com 99, Carlos com 55, Rejane com 55"
					, placar.retornarRankPonto("estrela"));
	}

}