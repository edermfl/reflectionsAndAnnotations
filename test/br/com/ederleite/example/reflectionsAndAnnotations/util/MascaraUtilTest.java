package br.com.ederleite.example.whitebox.util;

import br.com.ederleite.example.whitebox.domain.model.Pessoa;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * Created by eml on 27/01/16.
 */
public class MascaraUtilTest extends TestCase {

    public void testAplicarMascara() throws Exception {
	final String cpf = "01234567890";
	final String cpfMascarado = "012.345.678-90";

	final Pessoa pessoa = new Pessoa();
	pessoa.setCpf(cpf);
	Assert.assertEquals(cpf, pessoa.getCpf());
	System.out.println("Antes : " + pessoa.getCpf());

	new MascaraUtil().aplicarMascara(pessoa);
	Assert.assertEquals(cpfMascarado, pessoa.getCpf());
	System.out.println("Depois: " + pessoa.getCpf());

    }


}