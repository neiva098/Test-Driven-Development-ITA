import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Placar {

	Armazenamento armazenamento;

	public Placar() {
	}

	public Placar(Armazenamento arm) {
		setArmazenamento(arm);
	}

	public void registrarPonto(String usuario, String tipo, int valor) {
		armazenamento.registrarPonto(usuario, tipo, valor);
	}

	public String retornarPonto(String usuario) {
		return armazenamento.retornarPonto(usuario);
	}

	public String retornarRankPonto(String string) {
		Map<String, Integer> mapa = new HashMap<>();
		mapa = armazenamento.retornarRank(string);

		Set<Entry<String, Integer>> set = mapa.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);

		list = sort(mapa);

		String saida = "";
		for (int i = 0; i < list.size(); i++) {
			saida = saida + list.get(i).getKey() + " com " 
					+ Integer.toString(list.get(i).getValue().intValue()) + ", ";

		}

		saida = saida.substring(0, saida.length() - 2);

		return saida;
	}

	public String retornarPontoPorTipo(String usuario, String tipo) {
		return armazenamento.retornarPontoPorTipo(usuario, tipo);
	}

	public String retornarUsuarios() {
		return this.armazenamento.retornarUsuarios();
	}

	private void setArmazenamento(Armazenamento arm) {
		this.armazenamento = arm;
	}

	private List sort(Map<String, Integer> mapa) {
		Set<Entry<String, Integer>> set = mapa.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int result = (o2.getValue()).compareTo(o1.getValue());
				if (result != 0) {
					return result;
				} else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});

		return list;
	}

}