import exceptions.ContaInvalidaException;

public interface ServicoRemoto {

	public ContaCorrente recuperarConta(String numeroConta) throws ContaInvalidaException;

	public void persistirConta(ContaCorrente contaCorrente);
}
