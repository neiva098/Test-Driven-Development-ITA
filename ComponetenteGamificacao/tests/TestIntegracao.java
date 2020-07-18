import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestIntegracao {

	Placar placar;
	Armazenamento arm;
	
	@Before
	public void setUp(){
		try {
			arm = new Armazenamento("Placar");
		} catch (IOException e) {
			System.out.println("N�o foi poss�vel criar o arquivo!");
		}
		placar = new Placar(arm);
	}

	@After
	public void tearDown() {
		File file = new File("Placar.txt");
		file.delete();
	}

	@Test
	public void testIntegracao() {
		placar.registrarPonto("cristiano", "estrela", 30);
		placar.registrarPonto("cristiano", "estrela", 55);
		placar.registrarPonto("cristiano", "moeda", 20);
		placar.registrarPonto("Jonas", "curtida", 17);
		placar.registrarPonto("Marina", "moeda", 75);
		placar.registrarPonto("Carlos", "urtida", 40);
		
		assertEquals("cristiano possui 20 pontos do tipo moeda e "
				+ "85 pontos do tipo estrela", placar.retornarPonto("cristiano"));
		assertEquals("Jonas possui 17 pontos do tipo curtida", placar.retornarPonto("Jonas"));
		assertEquals("Marina possui 75 pontos do tipo moeda", placar.retornarPonto("Marina"));
		assertEquals("Marina possui 75 pontos do tipo moeda", placar.retornarPonto("Marina"));
		assertEquals("Marina com 75, cristiano com 20", placar.retornarRankPonto("moeda"));
		assertEquals("cristiano possui 85 pontos do tipo estrela"
				, placar.retornarPontoPorTipo("cristiano", "estrela"));
		assertEquals("Jonas possui 17 pontos do tipo curtida"
				, placar.retornarPontoPorTipo("Jonas", "curtida"));
		assertEquals("cristiano, Jonas, Marina, Carlos", placar.retornarUsuarios());
		
		
	}

}