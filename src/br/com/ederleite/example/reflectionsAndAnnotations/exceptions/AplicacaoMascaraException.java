package br.com.ederleite.example.reflectionsAndAnnotations.exceptions;

/**
 * Created by eml on 27/01/16.
 */
public class AplicacaoMascaraException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constroi uma <code>AplicacaoMascaraException</code> com a mensagem padrao nula.
     */
    public AplicacaoMascaraException() {
	super();
    }

    /**
     * Constroi uma <code>AplicacaoMascaraException</code> com a mensagem <code>pMensagem</code>.
     * 
     * @param pMensagem
     *            Mensagem de erro.
     */
    public AplicacaoMascaraException(final String pMensagem) {
	super(pMensagem);
    }

    /**
     * 
     * Constroi uma <code>AplicacaoMascaraException</code> com a mensagem <code>pMensagem</code> e a causa <code>pCausa</code>.
     * 
     * @param pMessage
     *            Mensagem de erro.
     * @param pCausa
     *            Causa
     */
    public AplicacaoMascaraException(final String pMessage, final Throwable pCausa) {
	super(pMessage, pCausa);
    }
}