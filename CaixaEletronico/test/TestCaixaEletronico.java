import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.ContaInvalidaException;
import exceptions.HWException;
import exceptions.SaldoInsuficienteException;

public class TestCaixaEletronico {

	@Before
	public void instanciaCaixaEServicos() {

	}

	@Test
	public void testeLoginUsuarioValido() throws ContaInvalidaException, HWException {
		String contaSolicitada = "1";

		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		String retorno = caixa.logar(contaSolicitada);
		assertEquals("Usuário Autenticado", retorno);
		assertEquals(contaSolicitada, caixa.getContaLogada());
	}

	@Test(expected = ContaInvalidaException.class)
	public void testeLoginUsuarioInvalido() throws ContaInvalidaException, HWException {
		String contaSolicitada = "4";

		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);
		caixa.logar(contaSolicitada);
	}

	@Test
	public void testeVerificaSaldo() throws ContaInvalidaException, HWException {
		String contaSolicitada = "1";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.saldo();
		
		assertEquals("O saldo é R$1000.0", retorno);
	}
	
	@Test
	public void testeSaqueComSucesso() throws ContaInvalidaException, SaldoInsuficienteException, HWException {
		
		double valorSacado = 500;
		String contaSolicitada = "2";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.sacar(valorSacado);
		
		ContaCorrente contaCorrente = mockHardware.getServicoRemoto().recuperarConta(contaSolicitada);
		
		assertEquals("Retire seu dinheiro", retorno);
		Assert.assertEquals(contaCorrente.getSaldo(), 500.0, 0.0f);
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	public void testeSaqueSemSaldo() throws ContaInvalidaException, SaldoInsuficienteException, HWException {
		String contaSolicitada = "2";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		caixa.sacar(5000);
	}
	
	@Test
	public void testeDepositoComSucesso() throws ContaInvalidaException, SaldoInsuficienteException, HWException {
		
		double valorDeposito = 500;
		String contaSolicitada = "3";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.depositar(valorDeposito);
		
		ContaCorrente contaCorrente = mockHardware.getServicoRemoto().recuperarConta(contaSolicitada);
		
		assertEquals("Depósito recebido com sucesso", retorno);
		assertTrue(3500 == contaCorrente.getSaldo());
	}
	
	@Test(expected=HWException.class)
	public void testeDepositoForaDoAr() throws ContaInvalidaException, SaldoInsuficienteException, HWException {
		
		double valorDeposito = 500;
		String contaSolicitada = "3";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);
		CaixaEletronico caixa = new CaixaEletronico(mockHardware);
		
		caixa.logar(contaSolicitada);
		
		mockHardware.setEstado(false);
		caixa.depositar(valorDeposito);
	}
	
	@Test(expected=HWException.class)
	public void testeLoginForaDoAr() throws ContaInvalidaException, HWException {
		String contaSolicitada = "1";

		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		mockHardware.setEstado(false);
		caixa.logar(contaSolicitada);
	}
	
	@Test(expected=HWException.class)
	public void testeSaqueForaDoAr() throws ContaInvalidaException, SaldoInsuficienteException, HWException {
		
		double valorSacado = 500;
		String contaSolicitada = "2";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		mockHardware.setEstado(false);
		caixa.sacar(valorSacado);
	}
}