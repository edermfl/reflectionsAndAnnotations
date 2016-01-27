package br.com.ederleite.example.whitebox.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by eml on 27/01/16.
 */         @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.FIELD)
public @interface Mascara {
    /**
     * define a máscara a ser usada
     * @return mascara.
     */
    public String value();
}
