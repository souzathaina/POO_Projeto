package projetopoooo;

import controller.UsuarioController;
import javax.swing.JOptionPane;
import model.Usuario;
import view.FrLogin;

public class ProjetoPOOoo {

    public static void main(String[] args) {
        new FrLogin().setVisible(true);

        Usuario teste1 = new Usuario();

        teste1.getNome();
        teste1.getEmail();
        teste1.getSenha();
        teste1.getDataNasc();
        teste1.getPkUsuario();

        System.out.println("Thainá" + teste1.getNome());
        System.out.println("thaina.t@gmail.com" + teste1.getEmail());
        System.out.println(""+teste1.getSenha());
        UsuarioController controller = new UsuarioController();
        if (controller.inserirUsuario(usu)) {
            JOptionPane.showMessageDialog(null, "Usuário gravado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "O cadastro não foi gravado ");
        }
    }

}
