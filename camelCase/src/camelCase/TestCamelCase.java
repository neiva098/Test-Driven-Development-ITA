package camelCase;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TestCamelCase {
	
	@Test
	public void converterCaixaBaixa() {
		List<String> res = CamelCase.converterCamelCase("camel");
		
		List<String> expected = new ArrayList<String>();
		expected.add("camel");
		
		assertEquals(res, expected);
	}
	
	@Test
	public void converterSomentePrimeiraLetraMaiuscula() {
		List<String> res = CamelCase.converterCamelCase("Camel");
		
		List<String> expected = new ArrayList<String>();
		expected.add("camel");
		
		assertEquals(res, expected);
	}
	
	@Test
	public void converterNomeComposto() {
		List<String> res = CamelCase.converterCamelCase("camelCase");
		
		List<String> expected = new ArrayList<String>();
		expected.add("camel");
		expected.add("Case");
		
		assertEquals(res, expected);
	}
	
	@Test
	public void getWordsArray() {
		String res[] = CamelCase.getWordsArray("CamelCaseTeste");
		
		assertEquals(res[0], "camel");
		assertEquals(res[1], "Case");
		assertEquals(res[2], "Teste");
	}
	
	@Test
	public void separarPorLetraMaiuscula() {
		String res[] = CamelCase.separarPorLetraMaiuscula("camelCaseTeste");
		
		assertEquals(res[0], "camel");
		assertEquals(res[1], "Case");
		assertEquals(res[2], "Teste");
	}
	
	@Test
	public void handleFirstWord() {
		String words[] = {"TesTe"};

		String res[] = CamelCase.handleFirstWord(words);
		
		assertEquals(res[0], "teste");
	}
	
	@Test
	public void converterPalavraCaixaAlta() {
		List<String> res = CamelCase.converterCamelCase("CPF");
		
		List<String> expected = new ArrayList<String>();
		expected.add("CPF");
		
		assertEquals(res, expected);
	}
	
	@Test
	public void converterPalavraCaixaAltaCaixaBaixa() {
		List<String> res = CamelCase.converterCamelCase("nomeCPF");
		
		List<String> expected = new ArrayList<String>();
		expected.add("nome");
		expected.add("CPF");
		
		assertEquals(res, expected);
	}
	
	@Test
	public void converterPalavraNumeroCPFContribuinte() {
		List<String> res = CamelCase.converterCamelCase("numeroCPFContribuinte");
		
		List<String> expected = new ArrayList<String>();
		expected.add("numero");
		expected.add("CPF");
		expected.add("Contribuinte");
		
		assertEquals(res, expected);
	}
	
	@Test
	public void converterPalavraRecupera10Primeiros() {
		List<String> res = CamelCase.converterCamelCase("recupera10Primeiros");
		
		List<String> expected = new ArrayList<String>();
		expected.add("recupera");
		expected.add("10");
		expected.add("Primeiros");
		
		assertEquals(res, expected);
	}
	
	@Test(expected=FirstCharException.class)
	public void primeiroCaractereNumero() {
		String words[] = {"0Tests"};

		CamelCase.handleFirstWord(words);
	}
	
	@Test(expected=SpecialCharException.class)
	public void contemCaracteresEspeciais() {
		CamelCase.getWordsArray("Tests0Legais&top#xama");
	}
	
}
