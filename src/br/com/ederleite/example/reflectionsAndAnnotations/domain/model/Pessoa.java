package br.com.ederleite.example.reflectionsAndAnnotations.domain.model;

import br.com.ederleite.example.reflectionsAndAnnotations.domain.annotations.Mascara;

/**
 * Created by eml on 27/01/16.
 */
public class Pessoa {

    @Mascara("###.###.###-##")
    private String cpf;

    public Pessoa() {
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(final String pCpf) {
	cpf = pCpf;
    }
}
