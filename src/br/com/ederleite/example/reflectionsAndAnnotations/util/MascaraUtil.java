package br.com.ederleite.example.reflectionsAndAnnotations.util;

import br.com.ederleite.example.reflectionsAndAnnotations.domain.annotations.Mascara;
import br.com.ederleite.example.reflectionsAndAnnotations.exceptions.AplicacaoMascaraException;

import javax.swing.text.MaskFormatter;
import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * Created by eml on 27/01/16.
 */
public class MascaraUtil {

    public void aplicarMascara(final Object objeto) {
	// como dito, tudo inicia no class do objeto
	final Class<?> classe = objeto.getClass();

	// Isso já é reflexão
	// Recupero todos os fields (atributos) da class
	final Field[] fields = classe.getDeclaredFields();
	for (final Field field : fields) {
	    // para cada um dos fields da classe, verifico se a anotação @Mascara está presente.
	    if (field.isAnnotationPresent(Mascara.class)) {
		// se estiver presente, recupero a anotação do field para extrair a máscara que deve ser dado ao atributo
		final Mascara annotation = field.getAnnotation(Mascara.class);
		final String mascara = annotation.value();
		try {
		    // para ter acesso a uma atributo privado, primeiramente devo liberar o acesso.
		    field.setAccessible(true);
		    // recupero o valor do atributo por reflexão
		    final String valorDoAtributo = (String) field.get(objeto);
		    try {
			// com a máscara e o valor do atributo em mão, aplico a máscara ao valor.
			final String valorComMascara = getValorComMascara(mascara, valorDoAtributo);
			// depois, seto valor tratado ao field por reflexão.
			field.set(objeto, valorComMascara);
		    } catch (final AplicacaoMascaraException e) {
			// caso algum erro aconteça, apenas logo o erro e continuo aplicar a máscara nos outros atributos
			e.printStackTrace();
		    }
		} catch (IllegalAccessException e) {
		    // caso algum erro aconteça, apenas logo o erro e continuo aplicar a máscara nos outros atributos
		    e.printStackTrace();
		}
	    }
	}
    }

    private String formatarValorMascara(final String pTexto, final String pMascara) throws AplicacaoMascaraException {
	// para formatar o valor, utilizo de um utilitário do swing (javax.swing.text.MaskFormatter)
	MaskFormatter mf;
	try {
	    mf = new MaskFormatter(pMascara);
	    mf.setValueContainsLiteralCharacters(false);
	    return mf.valueToString(pTexto);
	} catch (final ParseException e) {
	    throw new AplicacaoMascaraException("Máscara '" + pMascara + "' não pode ser aplicada ao valor '" + pTexto + "'", e);
	}
    }

    private String getValorComMascara(final String pMascara, final String pValor) throws AplicacaoMascaraException {
	//primeiro passo da aplicação da máscara, é remover qualquer carácter especial que o valor possa ter.
	final String valorSemMascara = removerMascara(pValor);
	// depois checo, que a quantidade de carácteres do valor e da máscara são iguais
	final Integer quantCaracterNaMascara = pMascara.replaceAll("[^A#]", "").length();
	if (valorSemMascara.length() != quantCaracterNaMascara) {
	    // se não faço exception dizendo que a máscara não pode ser aplicada ao valor
	    throw new AplicacaoMascaraException("Valor não é compativel com o tamanho da mascara");
	}
	// retorna valor formatado com a máscara
	return formatarValorMascara(valorSemMascara, pMascara);
    }

    private String removerMascara(final String pValor) throws AplicacaoMascaraException {
	if (pValor == null) {
	    throw new AplicacaoMascaraException("O conteúdo do campo está nulo.");
	}
	//remove caracteres especiais
	return pValor.replaceAll("[^A-Za-z0-9]", "");
    }
}