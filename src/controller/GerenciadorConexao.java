package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GerenciadorConexao {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_conta";
    private static final String USER = "root";
    private static final String PASSAWORD = "root";

    private Connection conexao;

    public GerenciadorConexao() {
        try { //vai tentar executar
            conexao = DriverManager.getConnection(URL, USER, PASSAWORD);
        } catch (SQLException e) {//caso de algum problema executa o que está aqui, caso ocarra um SQLException irá executar o seguinte tratamento
            //aqui nesse exemplo vai aparecer uma mensagem 
            JOptionPane.showMessageDialog(null, e.getMessage().toString());
        }
    }

    //condições de pesquisas seleqt, responsavel por analisar o sql a ser executado ()select, insert, delete, ...
    public PreparedStatement prepararComando(String sql) {
        PreparedStatement comando = null;

        try {
            comando = conexao.prepareStatement(sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao preparar comando: " + erro);
        }
        return comando;
    }

    //3 metados com o mesmo nome mas com paramentros diferentes,
    //1º definição, 1fechar conexao= sem parametrso, 
    public void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException erro) {
            Logger.getLogger(GerenciadorConexao.class.getName())
                    .log(Level.SEVERE, null, erro);
        }
    }

    //2º definição, 2fechar conexao= esmo retorno e o mesno nome, mas vai chamar sem os parametros e depois vai fechar o comnando
    public void fecharConexao(PreparedStatement comando) {
        fecharConexao();

        try {
            if (comando != null) {
                comando.close();
            }
        } catch (SQLException erro) {
            Logger.getLogger(GerenciadorConexao.class.getName())
                    .log(Level.SEVERE, null, erro);
        }
    }

    /*3º definição, 3fechar conexao= vai fechar a conexao recebencdo o comando e o resultado, se chamar a terceira automaticamente chama as duas anteriores
    se ocorrer algum problema quando vai mostrar em qual classe ocorreu o erro
     */
    public void fecharConexao(PreparedStatement comando, ResultSet resultado) {
        fecharConexao(comando);

        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException erro) {
            Logger.getLogger(GerenciadorConexao.class.getName())
                    .log(Level.SEVERE, null, erro);
        }
    }
}
