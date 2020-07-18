import java.util.ArrayList;
import java.util.List;

import exceptions.ContaInvalidaException;

public class MockServicoRemoto implements ServicoRemoto {

	private List<ContaCorrente> contasDoBanco;

	public MockServicoRemoto() {
		contasDoBanco = new ArrayList<ContaCorrente>();
		ContaCorrente conta1 = new ContaCorrente("1", 1000);
		ContaCorrente conta2 = new ContaCorrente("2", 2000);
		ContaCorrente conta3 = new ContaCorrente("3", 3000);

		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		contasDoBanco.add(conta3);
	}

	@Override
	public ContaCorrente recuperarConta(String numeroConta) throws ContaInvalidaException {
		for(ContaCorrente c : contasDoBanco)
		{
			if(c.getNumeroConta().equals(numeroConta)) return c;
		}
		throw new ContaInvalidaException("Não há clientes com esta conta");
	}

	@Override
	public void persistirConta(ContaCorrente contaAtualizada) {
		for(ContaCorrente c : contasDoBanco)
		{
			if(c.getNumeroConta().equals(contaAtualizada.getNumeroConta())) c.setSaldo(contaAtualizada.getSaldo());
		}

	}

	public List<ContaCorrente> getContasDoBanco() {
		return contasDoBanco;
	}

	public void setContasDoBanco(List<ContaCorrente> contasDoBanco) {
		this.contasDoBanco = contasDoBanco;
	}

}