import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MockArmazenamento extends Armazenamento {

	public MockArmazenamento() throws IOException {
		super();
	}

	Map<String, HashMap<String, Integer>> lista = new HashMap<String, HashMap<String, Integer>>();
	
	public void registrarPonto(String usuario, String tipo, int valor) {

		if (lista.containsKey(usuario)) {
			if (lista.get(usuario).containsKey(tipo)) {
				int valorAux = lista.get(usuario).get(tipo);
				lista.get(usuario).put(tipo, valorAux + valor);
			} else {
				lista.get(usuario).put(tipo, valor);
			}
		} else {
			lista.put(usuario, new HashMap<String, Integer>());
			lista.get(usuario).put(tipo, valor);
		}
	}

	public String retornarPonto(String usuario) {
		String output = usuario + " possui ";
		int count = 1;

		if (lista.containsKey(usuario)) {

			Map<String, Integer> outer = new HashMap<String, Integer>();
			outer = lista.get(usuario);

			for (Map.Entry<String, Integer> inner : outer.entrySet()) {
				if (lista.get(usuario).size() == count) {
					output = output + Integer.toString(inner.getValue()) 
					+ " pontos do tipo " + inner.getKey();
				} else {
					output = output + Integer.toString(inner.getValue()) 
					+ " pontos do tipo " + inner.getKey() + " e ";
					count++;
				}
			}

			return output;

		} else {
			return "Usuário inexistente!";
		}
	}

	public HashMap<String, Integer> retornarRank(String tipoPonto) {
		
		HashMap<String, Integer> mapa = new HashMap<>();

		for (Entry<String, HashMap<String, Integer>> outerMap : lista.entrySet()) {
			for (Entry<String, Integer> innerMap : outerMap.getValue().entrySet()) {
				if (tipoPonto.equals(innerMap.getKey())) {
					if (mapa.containsKey(outerMap.getKey()))
						mapa.put(outerMap.getKey(), mapa.get(outerMap.getKey()) 
								+ innerMap.getValue());
					else
						mapa.put(outerMap.getKey(), innerMap.getValue());
				}

			}

		}
		return mapa;
	}

}