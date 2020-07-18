import exceptions.ContaInvalidaException;
import exceptions.HWException;

public interface Hardware {
	public String pegarNumeroDaContaCartao(String numeroConta) throws ContaInvalidaException, HWException;
	
	public void entregarDinheiro() throws HWException;
	
	public void lerEnvelope() throws HWException;
	
	public ServicoRemoto getServicoRemoto() throws HWException;
	
	public void setServicoRemoto(ServicoRemoto servicoRemoto) throws HWException;
}
