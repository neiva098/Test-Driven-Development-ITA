import exceptions.ContaInvalidaException;
import exceptions.HWException;

public class MockHardware implements Hardware {

	public ServicoRemoto servicoRemoto;
	
	public Boolean estado;
	
	public MockHardware(MockServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
		this.estado = true;
	}

	@Override
	public String pegarNumeroDaContaCartao(String numeroConta) throws ContaInvalidaException, HWException {
		if(!getEstado()) throw new HWException("Nâo foi possível obter o número da conta. Sistema fora do ar");
		ContaCorrente conta = servicoRemoto.recuperarConta(numeroConta);
		return conta.getNumeroConta();
	}

	@Override
	public void entregarDinheiro() throws HWException {
		if(!getEstado()) throw new HWException("Nâo foi possível entregar o dinheiro. Sistema fora do ar");
	}

	@Override
	public void lerEnvelope() throws HWException {
		if(!getEstado()) throw new HWException("Nâo foi possível ler o envelope. Sistema fora do ar");
	}

	public void setServicoRemoto(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}
	
	public ServicoRemoto getServicoRemoto() {
		return servicoRemoto;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
}