import java.io.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Armazenamento {

	String fileName;
	File file;

	public Armazenamento() {
	}

	public Armazenamento(String nome) throws IOException {
		fileName = nome;
		file = new File(nome + ".txt");
		file.createNewFile();
	}

	public void registrarPonto(String usuario, String tipo, int valor) {

		try (FileWriter fw = new FileWriter(fileName + ".txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.print(usuario);
			out.print(" " + tipo);
			out.println(" " + Integer.toString(valor));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public String retornarPonto(String usuario) {

		HashMap<String, Integer> mapa = new HashMap<>();
		String saida = usuario + " possui ";

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String readLine = "";
			while ((readLine = buffer.readLine()) != null) {
				String[] splitter = readLine.split(" ");
				if (splitter[0].equals(usuario)) {
					if (mapa.containsKey(splitter[1]))
						mapa.put(splitter[1], mapa.get(splitter[1]) 
								+ Integer.parseInt(splitter[2]));
					else
						mapa.put(splitter[1], Integer.parseInt(splitter[2]));

				}
			}
			
			buffer.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		for (String key : mapa.keySet()) {
			saida = saida + Integer.toString(mapa.get(key)) 
					+ " pontos do tipo " + key + " e ";
		}

		saida = saida.substring(0, saida.length() - 3);

		return saida;

	}

	public String retornarPontoPorTipo(String usuario, String tipo) {
		String saida = "";
		int total = 0;

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String readLine = "";
			while ((readLine = buffer.readLine()) != null) {
				String[] splitter = readLine.split(" ");
				if (splitter[0].equals(usuario) && splitter[1].equals(tipo)) {
					total = total + Integer.parseInt(splitter[2]);
				}
				if (total > 0)
					saida = usuario + " possui " 
							+ Integer.toString(total) + " pontos do tipo " + tipo;
				else
					saida = usuario + " possui 0 pontos do tipo " + tipo;
			}
			buffer.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return saida;
	}

	public String retornarUsuarios() {

		String saida = "";

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String readLine = "";
			while ((readLine = buffer.readLine()) != null) {
				String[] splitter = readLine.split(" ");
				if (!saida.contains(splitter[0]))
					saida = saida + splitter[0] + ", ";
			}
			buffer.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		saida = saida.substring(0, saida.length() - 2);

		return saida;
	}

	public HashMap<String, Integer> retornarRank(String tipoPonto) {
		HashMap<String, Integer> mapa = new HashMap<>();
		mapa = (HashMap<String, Integer>) retornaMapaRank(tipoPonto);

		return mapa;
	}

	private Map<String, Integer> retornaMapaRank(String tipoPonto) {

		HashMap<String, Integer> mapa = new HashMap<>();

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String readLine = "";
			while ((readLine = buffer.readLine()) != null) {
				String[] splitter = readLine.split(" ");
				if (splitter[1].equals(tipoPonto)) {
					if (mapa.containsKey(splitter[0]))
						mapa.put(splitter[0], mapa.get(splitter[0]) 
								+ Integer.parseInt(splitter[2]));
					else
						mapa.put(splitter[0], Integer.parseInt(splitter[2]));

				}
			}
			buffer.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return mapa;

	}
}