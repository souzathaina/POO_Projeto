
package utils;
/*reconhecer button enter, 
Transformar a senha em uma sequência de letras e números aleatórios (hash)
atualização no mysql para reconhecer o sha1
alteração da aplicação para o centro da tela
criação do jframe menu
*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

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
    //
    public static Date converterStringToDate(String texto) {
        
        //string com a formataççao, contru o fomrato que quero transformar o texto
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");//o MM e para não confundir por minutos
        
        //crio a variavel data que sera o retorno do metodo
        Date data = null;
        
        try{
            //tenta converter a String em Date baseado no formato construido anteriormente
            data=formato.parse(texto);
        } catch (ParseException ex){
            JOptionPane.showMessageDialog(null, "Erro ao converter a data");
        }
        //retorna a data convertida
        return data;
    }
    public static String converterDateToString(Date data) {
        SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
        String texto = "";
        
        try {
            //irá formatar a data para o fomrato dd/MM/yyyy
            texto = formato.format(data);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao formatar data");
        }
        return texto;
    }
}
