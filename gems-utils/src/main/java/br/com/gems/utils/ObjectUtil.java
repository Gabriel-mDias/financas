package br.com.gems.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class ObjectUtil {

    /*
     * Métodos utilitários públicos.
     */

    /**
     * Verifica se o objeto é nulo ou vazio.
     * Validando se ele é "vazio" de acordo com o tipo do objeto:
     * <ul>
     *     <li>String: se a String é nula ou se é apenas espaços em branco;</li>
     *     <li>Collection: se a Collection é nula ou com o seu tamanho igual a zero, ou seja, sem nenhum item;</li>
     * </ul>
     *
     *
     * @param object
     * @return true se o objeto é nulo ou vazio, false caso contrário.
     */
    public static boolean isNullOrEmpty( Object object ) {
        return object == null || isAEmptyString( object ) || isAEmptyCollection( object );
    }

    /*
     * Métodos privados
     */

    private static boolean isAEmptyString( Object object ) {
        return ( object instanceof String && ( ( String ) object ).isBlank() );
    }

    private static boolean isAEmptyCollection( Object object ) {
        return ( object instanceof Collection<?> && ( ( Collection<?> ) object ).isEmpty() );
    }

}
