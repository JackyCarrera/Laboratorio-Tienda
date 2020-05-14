
package modelo;

public class Hash {
    
    /* Retorna un hash a partir de un tipo y un texto */
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            /*crea un algoritmo que resume el mensaje y el parametro hasType regresa como un algoritmo con una firma especifica */
            byte[] array = md.digest(txt.getBytes());
            /*la longitud del algoritmo anterior es capturado como bytes*/
            StringBuffer sb = new StringBuffer();
            /*Captura un dato y lo convierte como una cadena*/
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            /*establece desde 0 la cadena y captura la cantidad de elementos de la matriz determinada,
            cuando haya efectuado el paso anterior el sb es abierto y envuelve el valor primitivo como 
            una String Hexadecimal*/
            return sb.toString();
            /*retorna el valor hexadecimal*/
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return Hash.getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA1 a partir de un texto */
    public static String sha1(String txt) {
        return Hash.getHash(txt, "SHA1");
    }

}
