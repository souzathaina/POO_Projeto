package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioController {
    //classe autenticar vai retornar um booleano, 

    //criar uma conexao com o banco de dados, execução sencivel (try). setar a String na ordem dos parametros.
    //resultado.next . finally( mesmo se der false vai retornar a esse 
    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * from USUARIO"
                + " WHERE email = ? and senha = ? "
                + " and ativo = true";
        //cria uma instancia do gerenciador de coenxao que o banco de dados
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        //declara as variaveis como nulas antes do try para poder utilizar o finally
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            //prepara o sql, analisando o formato e as variaveis
            comando = gerenciador.prepararComando(sql);

            //define o valor de cada variavel (?) pela posição em que aparece no sql
            comando.setString(1, email);
            comando.setString(2, senha);

            //executa o comando e guarda o resultado da consulta, o resultado é semelhante a uma grade
            resultado = comando.executeQuery();

            //resultado.next ()- tenta avançar para a proxima linha, caso consiga retornar true
            if (resultado.next()) {
                //se conseguiu avançar para a proxima linha é pq achou um usuario com aquele nome e senha
                return true;
            }
        } catch (SQLException e) {//caso ocorra um erro relacionado ao banco de dados
            JOptionPane.showMessageDialog(null, e.getMessage()); //exibe popup com o erro
        } finally { //depois de executar o try, dando erro ou não executa o finally
            gerenciador.fecharConexao(comando, resultado);
        }
        return false;
    }

    public boolean inserirUsuario(Usuario usu) {
        //mostrar o comando
        String sql = "INSERT INTO USUARIO(nome, email, senha, dataNasc, ativo)"
                + " VALUES (?,?,?,?,?)";
        //cria uma instancia do gerenciador de coenxao que o banco de dados
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        //declara as variaveis como nulas antes do try para poder utilizar o finally, passei uma string e p prepared analisou
        PreparedStatement comando = null;//não precisa do resultSet pois não tem um select comnado de leitura de dados
        try {
            //prepara o sql, analisando o formato e as variaveis
            comando = gerenciador.prepararComando(sql);
            //define o valor de cada variavel (?) pela posição em que aparece no sql
            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setString(3, usu.getSenha());
            comando.setDate(4, new java.sql.Date(usu.getDataNasc().getTime()));
            comando.setBoolean(5, usu.isAtivo());
            //executa o insert 
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {//caso ocorra um erro relacionado ao banco de dados
            JOptionPane.showMessageDialog(null, e.getMessage()); //exibe popup com o erro
        } finally { //depois de executar o try, dando erro ou não executa o finally
            gerenciador.fecharConexao(comando);
        }

        return false;
    }

    public boolean alterarUsuario(Usuario usu) {
        String sql = "UPDATE USUARIO SET (nome, email, senha, dataNasc, ativo, PkUsuario)"
                + "VALUES (?,?,?,?,?,?)";

        GerenciadorConexao gerenciador = new GerenciadorConexao();

        PreparedStatement comando = null;

        try {
            comando = gerenciador.prepararComando(sql);

            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setString(3, usu.getSenha());
            comando.setDate(4, new java.sql.Date(usu.getDataNasc().getTime()));
            comando.setBoolean(5, usu.isAtivo());
            comando.setInt(6, usu.getPkUsuario());

            // Executa a atualização
            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas > 0; // Retorna verdadeiro se a atualização foi bem-sucedida
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); // Exibe uma mensagem de erro em caso de exceção
        } finally {
            // Fecha a conexão, independente de erro ou sucesso
            gerenciador.fecharConexao(comando);
        }

        return false; // Retorna falso em caso de falha
    }

    public Usuario buscarPorPk(int PkUsuario) {
        //guarda o sql
        String sql = "SELECT * FROM USUARIO "
                + "WHERE PKUSUARIO= ?";

        //cria um gerenciador de conexao
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        //cria as variaveis antes do try pois vao ser usadas no finally
        PreparedStatement comando = null;
        ResultSet resultado = null;//resultado de uma pesquisa qunado é usado o select

        Usuario usu = new Usuario();

        try {
            comando = gerenciador.prepararComando(sql);

            comando.setInt(1, PkUsuario);

            //executa o comando e guardo o resultado
            resultado = comando.executeQuery();

            //irá precorrer os registros do sql
            //a cada next() a variavel resultado aponta para o proximo registro
            //enquanto next() == from quer dizer que tem registros
            if (resultado.next()) {
                
                //leio as informações da variavel resultado
                usu.setPkUsuario(resultado.getInt("pkusuario"));
                usu.setNome(resultado.getString("nome"));
                usu.setEmail(resultado.getString("email"));
                usu.setSenha(resultado.getString("senha"));
                usu.setDataNasc(resultado.getDate("datanasc"));
                usu.setAtivo(resultado.getBoolean("ativo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            gerenciador.fecharConexao(comando, resultado);
        }
        //retorna a usuárop
        return usu;
    }
}
