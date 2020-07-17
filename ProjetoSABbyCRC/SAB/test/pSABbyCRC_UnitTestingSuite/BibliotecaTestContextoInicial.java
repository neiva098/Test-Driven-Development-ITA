package pSABbyCRC_UnitTestingSuite;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BibliotecaTestContextoInicial {
	@Before
	public void SetUp() {
		biblioteca = new Biblioteca("ITA");
	}

	/*
	 * @Test public void
	 * whenSituationInicialEntaoListasLivrosDisponiveisAndEmprestadosSaoVazias()
	 * { // T1 // Testa condições de início! assertEquals(0,
	 * biblioteca.sizeRepositorioLivros()); assertEquals(0,
	 * biblioteca.sizeUsuarios()); }
	 */

	@Test
	public void whenAdicionoUmLivroEntaoIncrementaTamListaLivrosDisponiveisDeUm()
			throws AdicionarLivroInexistenteException { // T2--T4
		int tam = biblioteca.sizeRepositorioLivros();
		Livro livro1 = new Livro("Java Design Patterns", "Pankaj Kumar"); // T2
		biblioteca.adicionaLivroCatalogo(livro1);
		assertEquals(tam + 1, biblioteca.sizeRepositorioLivros());

		Livro livro2 = new Livro("Clojure", "Sally Fields"); // T3
		biblioteca.adicionaLivroCatalogo(livro2);
		assertEquals(tam + 2, biblioteca.sizeRepositorioLivros());

		Livro livro3 = new Livro("Using CRC Cards", "Nancy Wilkinson"); // T4
		biblioteca.adicionaLivroCatalogo(livro3);
		assertEquals(tam + 3, biblioteca.sizeRepositorioLivros());
	}

	@Test
	public void whenAdicionoOutroLivroComMesmoTituloEAutorEntaoIncrementaTamListaLivrosDisponiveisDeUm()
			throws AdicionarLivroInexistenteException {
		// T5
		int tam = biblioteca.sizeRepositorioLivros();
		Livro livro4 = new Livro("Using CRC Cards", "Nancy Wilkinson");
		biblioteca.adicionaLivroCatalogo(livro4);
		assertEquals(tam + 1, biblioteca.sizeRepositorioLivros());
	}

	@Test(expected = AdicionarLivroInexistenteException.class)
	public void whenAdicionoLivroNuloEntaoAdicionarLivroInexistenteExceptionEhLancada()
			throws AdicionarLivroInexistenteException {
		// T6
		biblioteca.adicionaLivroCatalogo(null);
	}

	@Test
	public void whenAdicionoUmUsuarioEntaoIncrementaTamListaUsuariosDeUm()
			throws UsuarioJaRegistradoException, UsuarioComNomeVazioException,
			UsuarioInexistenteException { // T7--T9
		// T7: Cria 1 usu‡rio novo
		int tam = biblioteca.sizeUsuarios();
		biblioteca.registraUsuario("Jose");
		assertEquals(tam + 1, biblioteca.sizeUsuarios());

		// T8: Cria segundo usuario novo
		biblioteca.registraUsuario("Joao");
		assertEquals(tam + 2, biblioteca.sizeUsuarios());

		// T9: Cria terceiro usuario novo
		biblioteca.registraUsuario("Joaquim");
		assertEquals(tam + 3, biblioteca.sizeUsuarios());
	}

	@Test(expected = UsuarioJaRegistradoException.class)
	public void whenAdicionoUsuarioJaExistenteEntaoUsuarioJaRegistradoExceptionEhLancada()
			throws UsuarioJaRegistradoException, UsuarioComNomeVazioException,
			UsuarioInexistenteException {
		// T10
		biblioteca.registraUsuario("Joaquim");
		biblioteca.registraUsuario("Joaquim");
	}

	@Test(expected = UsuarioComNomeVazioException.class)
	public void whenAdicionoUsuarioComNomeVazioEntaoUsuarioComNomeVazioExceptionEhLancada()
			throws UsuarioJaRegistradoException, UsuarioComNomeVazioException,
			UsuarioInexistenteException {
		// T11
		biblioteca.registraUsuario("");
	}

	@Test(expected = UsuarioInexistenteException.class)
	public void whenAdicionoUsuarioInexistenteEntaoUsuarioInexistenteExceptionEhLancada()
			throws UsuarioJaRegistradoException, UsuarioComNomeVazioException,
			UsuarioInexistenteException {
		// T12
		biblioteca.registraUsuario(null);
	}

	private static Biblioteca biblioteca;
}
