
package utils;
/*reconhecer button enter, 
Transformar a senha em uma sequência de letras e números aleatórios (hash)
atualização no mysql para reconhecer o sha1
alteração da aplicação para o centro da tela
criação do jframe menu
*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String calcularHash (String senha) {
        String hashSHA1 = "";
        try {
            //crie uma instancia do messagedigent cm o algoritimo sha1
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            
            //atualize o digest com os bytes do texto
            sha1.update(senha.getBytes());
            
            //calcule o hash sha1
            byte[] digest = sha1.digest();
            
            //converta o hash de bytes para uma representação hexadecimal
            StringBuilder sb = new StringBuilder ();
            for (byte b: digest){
                sb.append(String.format("%02x", b));
            }
            hashSHA1 = sb.toString();
        } catch (NoSuchAlgorithmException e){
            System.out.println("Algoritimo SHA1 não encontrado");
        }
        return hashSHA1;
    }
}
