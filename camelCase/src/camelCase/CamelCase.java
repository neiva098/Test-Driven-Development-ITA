package camelCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CamelCase {
	public static List<String> converterCamelCase(String original) {
		String wordsArray[] = getWordsArray(original);

		ArrayList<String> lista = new ArrayList<String>(Arrays.asList(wordsArray));
		
		return lista;
	}
	
	public static void throwsIfNotValidWords(String[] words) {
		for (String word : words) {
			if (word.matches(".*(?![a-zA-Z]|[0-9])(?!$).*")) {
				throw new SpecialCharException("Não sao aceitos caracteres especiais");
			}
        }
	}
	
	public static void throwsIfNumericFirstChar(String word) {
		if(String.valueOf(word.charAt(0)).matches("[0-9]")) {
			throw new FirstCharException("O primeiro caractere não pode ser numerico");
		}
	}
	
	public static String[] handleFirstWord(String[] words) {
		if (words[0].toUpperCase() != words[0]) {
			words[0] = words[0].toLowerCase();
		}
		
		throwsIfNumericFirstChar(words[0]);
		
		return words;
	}
	
	public static String[] getWordsArray(String original) {
		String words[] = separarPorLetraMaiuscula(original);
		
		throwsIfNotValidWords(words);
		
		return handleFirstWord(words);
	}

	public static String[] separarPorLetraMaiuscula(String original) {
		String splited[] = original.split("(?<=[a-z](?=[A-Z0-10{1,}]))|((?=[A-Z][a-z]))");

		return splited;
	}
}
