import java.util.HashMap;
import java.util.Map;

public class Tradutor {
	
	private Map<String, String> tradutor = new HashMap<String, String>();

	public Boolean estaVazio() {
		// TODO Auto-generated method stub
		return tradutor.isEmpty();
	}

	public void adicionaTraducao(String palavra, String traducao) {
		// TODO Auto-generated method stub
		String toAdd = traduzir(palavra);

		if (toAdd != null) {
			toAdd += ", " + traducao;
		} else {
			toAdd = traducao;
		}
		
		tradutor.put(palavra, toAdd);
	}

	public String traduzir(String palavra) {
		// TODO Auto-generated method stub
		return tradutor.get(palavra);
	}

	public String traduzirFrase(String frase) {
		// TODO Auto-generated method stub
		String fraseTraduzida = "";
		String splitedFrase[] = frase.split("\s");
		
		for (String word : splitedFrase) {
			String traducaoPrincipal = traduzir(word).split(",")[0];

			fraseTraduzida += " " + traducaoPrincipal;
        }
		
		return fraseTraduzida.trim();
	}

}
