import exceptions.ContaInvalidaException;
import exceptions.HWException;
import exceptions.SaldoInsuficienteException;

public class CaixaEletronico {

	Hardware hardware;

	private String contaLogada;

	public CaixaEletronico(MockHardware mockHardware) {
		this.hardware = mockHardware;
	}

	public String logar(String numeroConta) throws ContaInvalidaException, HWException {
		hardware.pegarNumeroDaContaCartao(numeroConta);
		setContaLogada(numeroConta);
		return "Usuário Autenticado";

	}

	public String saldo() throws ContaInvalidaException, HWException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getContaLogada());
		double saldo = contaCorrente.getSaldo();
		
		return "O saldo é R$" + saldo;
	}
	

	public String sacar(double valorAsacar) throws ContaInvalidaException, SaldoInsuficienteException, HWException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getContaLogada());
		double saldo = contaCorrente.getSaldo();
		if(valorAsacar < saldo)
		{
			contaCorrente.setSaldo(valorAsacar);
			hardware.entregarDinheiro();
			hardware.getServicoRemoto().persistirConta(contaCorrente);
			return "Retire seu dinheiro";
		}
		else
		{
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
	}
	
	public String depositar(double valorDeposito) throws ContaInvalidaException, HWException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getContaLogada());
		double saldo = contaCorrente.getSaldo();
		hardware.lerEnvelope();
		contaCorrente.setSaldo(saldo + valorDeposito);
		hardware.getServicoRemoto().persistirConta(contaCorrente);
		return "Depósito recebido com sucesso";
	}	
	
	public String getContaLogada() {
		return contaLogada;
	}

	public void setContaLogada(String contaLogada) {
		this.contaLogada = contaLogada;
	}
}