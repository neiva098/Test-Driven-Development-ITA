package pSABbyCRC_UnitTestingSuite;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BibliotecaTest {
	@Before
	public void SetUp() throws AdicionarLivroInexistenteException,
			UsuarioJaRegistradoException, UsuarioComNomeVazioException,
			UsuarioInexistenteException, BuscaUsuarioComNomeVazioException,
			BuscaUsuarioComNomeNuloException {
		biblioteca = new Biblioteca("ITA");
		livro1 = new Livro("Java Design Patterns", "Pankaj Kumar");
		biblioteca.adicionaLivroCatalogo(livro1);

		livro2 = new Livro("Clojure", "Sally Fields");
		biblioteca.adicionaLivroCatalogo(livro2);

		livro3 = new Livro("Using CRC Cards", "Nancy Wilkinson");
		biblioteca.adicionaLivroCatalogo(livro3);

		livro4 = new Livro("Using CRC Cards", "Nancy Wilkinson");
		biblioteca.adicionaLivroCatalogo(livro4);

		biblioteca.registraUsuario("Jose");
		usuario1 = biblioteca.buscaUsuarioPorNome("Jose");
		biblioteca.registraUsuario("Joao");
		usuario2 = biblioteca.buscaUsuarioPorNome("Joao");
		biblioteca.registraUsuario("Joaquim");
		usuario3 = biblioteca.buscaUsuarioPorNome("Joaquim");
	}

	@Test
	public void whenEmprestoUmLivroAUsuarioEntaoLivroFicaIndisponivelParaEmprestimo()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		// T13: Empresta um livro: Livro "Clojure––Sally Fields" para Usuario
		// "José");
		biblioteca.emprestaLivro(livro2, usuario1);
		assertEquals(usuario1, livro2.getUsuario());

		// T14: Empresta outro livro: Livro "Using CRC Cards––Nancy Wilkinson"
		// para Usuario "João");
		biblioteca.emprestaLivro(livro3, usuario2);
		assertEquals(usuario2, livro3.getUsuario());
	}

	@Test
	public void whenDevolvoUmLivroEmprestadoAUsuarioEntaoLivroFicaDisponivelParaEmprestimo()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException,
			DevolveLivroDisponivelParaEmprestimoException,
			DevolveLivroNuloParaEmprestimoException {
		// T15: Devolve um livro: Livro "Using CRC Cards––Nancy Wilkinson" de
		// Usuario "João");
		biblioteca.emprestaLivro(livro3, usuario2);
		biblioteca.devolveLivro(livro3);
		assertEquals(null, livro3.getUsuario());
	}

	@Test
	public void whenEmprestoTresLivrosAUmUnicosUsuarioSemLivrosEmprestadosEntaoListaLivrosdoUsuarioTemTam3()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		// T16: 3 livros emprestados ao mesmo usuario"
		// 3 Livros: Java Design Patterns––Pankaj Kumar" +
		// "\n\t\tUsing CRC Cards––Nancy Wilkinson" +
		// "\n\t\tUsing CRC Cards––Nancy Wilkinson" +
		// "\n\t\tpara Usuario Joaquim");
		biblioteca.emprestaLivro(livro1, usuario3);
		biblioteca.emprestaLivro(livro3, usuario3);
		biblioteca.emprestaLivro(livro4, usuario3);
		assertEquals(3, (usuario3.getLivros()).size());
	}

	@Test(expected = LivroIndisponivelParaEmprestimoException.class)
	public void whenEmprestoLivroJahEmprestadoEntaoLivroIndisponivelParaEmprestimoExceptionEhLancada()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		// T17: Empresta um livro já emprestado"
		biblioteca.emprestaLivro(livro2, usuario1);
		biblioteca.emprestaLivro(livro1, usuario3);
		biblioteca.emprestaLivro(livro3, usuario3);
		biblioteca.emprestaLivro(livro4, usuario3);

		biblioteca.emprestaLivro(livro2, usuario3);
	}

	@Test(expected = LivroOuUsuarioNulosException.class)
	public void whenEmprestoLivroNuloAUsuarioNaoNuloEntaoLivroOuUsuarioNulosExceptionEhLancada()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		// T18a: Empresta livro nulo a usuario não nulo"
		biblioteca.emprestaLivro(null, usuario2);
	}

	@Test(expected = LivroOuUsuarioNulosException.class)
	public void whenEmprestoLivroNaoNuloAUsuarioNuloEntaoLivroOuUsuarioNulosExceptionEhLancada()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		// T18b: Empresta livro não nulo a usuario nulo"
		biblioteca.emprestaLivro(livro4, null);
	}

	@Test(expected = LivroOuUsuarioNulosException.class)
	public void whenEmprestoLivroNuloAUsuarioNuloEntaoLivroOuUsuarioNulosExceptionEhLancada()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException {
		// T18c: Empresta livro nulo a usuario nulo"
		biblioteca.emprestaLivro(null, null);
	}

	@Test(expected = DevolveLivroDisponivelParaEmprestimoException.class)
	public void whenDevolvoLivroDisponivelParaEmprestimoEntaoDevolveLivroDisponivelParaEmprestimoExceptionEhLancada()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException,
			DevolveLivroDisponivelParaEmprestimoException,
			DevolveLivroNuloParaEmprestimoException {
		// T19: Devolve um livro que está disponível para empréstimo:
		// "Java Design Patterns––Pankaj Kumar"
		biblioteca.emprestaLivro(livro2, usuario1);
		biblioteca.emprestaLivro(livro3, usuario3);
		biblioteca.emprestaLivro(livro4, usuario3);
		biblioteca.devolveLivro(livro1);
	}

	@Test(expected = DevolveLivroNuloParaEmprestimoException.class)
	public void whenDevolvoLivroNuloParaEmprestimoEntaoDevolveLivroNuloParaEmprestimoExceptionEhLancada()
			throws LivroIndisponivelParaEmprestimoException,
			LivroOuUsuarioNulosException,
			DevolveLivroDisponivelParaEmprestimoException,
			DevolveLivroNuloParaEmprestimoException {
		// T20: Devolve um livro nulo"
		biblioteca.devolveLivro(null);
	}

	@Test
	public void whenBuscoLivroPeloNrCatalogoEntaoRetornoLivroCujoNrCatalogoConfere() {
		// T21: Busca livro por NrCatalogo existente
		// Livro 2: "Clojure––Sally Fields"
		Livro livro = biblioteca.buscaLivroPorNrCatalogo(2);
		assertEquals(2, livro.getNrCatalogo());
	}

	@Test
	public void whenBuscoLivroPeloNrCatalogoInexistenteEntaoLivroEhNulo() {
		// T22: Busca livro por NrCatalogo inexistente: 0 e 5
		Livro livroHum = biblioteca.buscaLivroPorNrCatalogo(0);
		Livro livroDois = biblioteca.buscaLivroPorNrCatalogo(5);
		assertEquals(null, livroHum);
		assertEquals(null, livroDois);
	}

	@Test
	public void whenBuscoLivroPorTituloEAutorEntaoRetornoLivroCujoTituloEAutorConfere()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T23: Busca livro por Titulo e Autor existente: livros 1 e 3
		Livro livroTres = biblioteca.buscaLivroPorTituloAutor(
				"Using CRC Cards", "Nancy Wilkinson");
		assertEquals(3, livroTres.getNrCatalogo());

		Livro livroHum = biblioteca.buscaLivroPorTituloAutor(
				"Java Design Patterns", "Pankaj Kumar");
		assertEquals(1, livroHum.getNrCatalogo());
	}

	@Test
	public void whenBuscoLivroPorTituloOuAutorENaoEncontroEntaoRetornoLivroNulo()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T24: Busca livro por Titulo e Autor e não encontra
		Livro livro = biblioteca.buscaLivroPorTituloAutor("Using CRC Cards",
				"Pankaj Kumar");
		assertEquals(null, livro);

		livro = biblioteca.buscaLivroPorTituloAutor(
				"Padrões de Projeto em Java", "Pankaj Kumar");
		assertEquals(null, livro);

		livro = biblioteca.buscaLivroPorTituloAutor(
				"Padrões de Projeto em Java", "Eduardo Guerra");
		assertEquals(null, livro);
	}

	@Test(expected = TituloOuAutorVazioException.class)
	public void whenBuscoLivroPorTituloNaoVazioEAutorVazioEntaoTituloOuAutorVazioExceptionEhLancada()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T25a: Busca livro por Titulo não vazio e Autor vazio
		biblioteca.buscaLivroPorTituloAutor("Using CRC Cards", "");
	}

	@Test(expected = TituloOuAutorVazioException.class)
	public void whenBuscoLivroPorTituloVazioEAutorNaoVazioEntaoTituloOuAutorVazioExceptionEhLancada()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T25b: Busca livro por Titulo vazio e Autor não vazio
		biblioteca.buscaLivroPorTituloAutor("", "Nancy Wilkinson");
	}

	@Test(expected = TituloOuAutorVazioException.class)
	public void whenBuscoLivroPorTituloVazioEAutorVazioEntaoTituloOuAutorVazioExceptionEhLancada()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T25c: Busca livro por Titulo vazio e Autor vazio
		biblioteca.buscaLivroPorTituloAutor("", "");
	}

	@Test(expected = TituloOuAutorNuloException.class)
	public void whenBuscoLivroPorTituloNaoNuloEAutorNuloEntaoTituloOuAutorVazioExceptionEhLancada()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T26a: Busca livro por Titulo não nulo e Autor nulo
		biblioteca.buscaLivroPorTituloAutor("Using CRC Cards", null);
	}

	@Test(expected = TituloOuAutorNuloException.class)
	public void whenBuscoLivroPorTituloNuloEAutorNaoNuloEntaoTituloOuAutorVazioExceptionEhLancada()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T26b: Busca livro por Titulo nulo e Autor não nulo
		biblioteca.buscaLivroPorTituloAutor(null, "Nancy Wilkinson");
	}

	@Test(expected = TituloOuAutorNuloException.class)
	public void whenBuscoLivroPorTituloNuloEAutorNuloEntaoTituloOuAutorVazioExceptionEhLancada()
			throws TituloOuAutorVazioException, TituloOuAutorNuloException {
		// T26c: Busca livro por Titulo nulo e Autor nulo
		biblioteca.buscaLivroPorTituloAutor(null, null);
	}

	@Test
	public void whenBuscoUsuarioPorNomeExistenteEntaoRetornoUsuarioValido()
			throws BuscaUsuarioComNomeVazioException,
			BuscaUsuarioComNomeNuloException {
		// T27: Busca usuario por Nome existente
		Usuario usuario = biblioteca.buscaUsuarioPorNome("Jose");
		assertEquals("Jose", usuario.getNome());

		usuario = biblioteca.buscaUsuarioPorNome("Joaquim");
		assertEquals("Joaquim", usuario.getNome());
	}

	@Test
	public void whenBuscoUsuarioPorNomeInexistenteEntaoRetornoUsuarioNulo()
			throws BuscaUsuarioComNomeVazioException,
			BuscaUsuarioComNomeNuloException {
		// T28: Busca usuario por Nome inexistente
		Usuario usuario = biblioteca.buscaUsuarioPorNome("Eduardo");
		assertEquals(null, usuario);

		usuario = biblioteca.buscaUsuarioPorNome("Clovis");
		assertEquals(null, usuario);
	}

	@Test(expected = BuscaUsuarioComNomeVazioException.class)
	public void whenBuscoUsuarioPorNomeVazioEntaoBuscaUsuarioComNomeVazioExceptionEhLancada()
			throws BuscaUsuarioComNomeVazioException,
			BuscaUsuarioComNomeNuloException {
		// T29: Busca usuario por Nome vazio
		biblioteca.buscaUsuarioPorNome("");
	}

	@Test(expected = BuscaUsuarioComNomeNuloException.class)
	public void whenBuscoUsuarioPorNomeNuloEntaoBuscaUsuarioComNomeNuloExceptionEhLancada()
			throws BuscaUsuarioComNomeVazioException,
			BuscaUsuarioComNomeNuloException {
		// T29: Busca usuario por Nome vazio
		biblioteca.buscaUsuarioPorNome(null);
	}

	private Biblioteca biblioteca;
	private Livro livro1, livro2, livro3, livro4;
	private Usuario usuario1, usuario2, usuario3;
}
