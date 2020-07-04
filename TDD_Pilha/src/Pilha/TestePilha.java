package Pilha;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestePilha {

	@Test
	public void pilhaVazia() {
		Pilha p = new Pilha();
        
        assertTrue(p.estaVazia());
        
        assertEquals(0, p.tamanho());
	}
	
	@Test
	public void empilhaUmElemento() {
		Pilha p = new Pilha();
        
		p.empilha("primeiro");
		
        assertFalse(p.estaVazia());
        
        assertEquals(1, p.tamanho());
        
        assertEquals("primeiro", p.topo());
	}
}
