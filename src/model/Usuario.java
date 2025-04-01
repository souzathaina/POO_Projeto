
package model;
import java.util.Date;

public class Usuario {
    private int pkUsuario;
    private String nome;
    private String email;
    private String senha;
    private Date dataNasc;
    private boolean ativo;

    //construtor
    public Usuario(){};
    /*oi*/
    /* to tentando salvar*/
    public Usuario(int pkid, String nome, String email, String senha, Date datanasc, boolean ativo) {
        this.pkUsuario = pkUsuario; //trocar para pkUsuario
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc; //trocar para dataNasc
        this.ativo = ativo;
    }

    public int getPkUsuario() {
        return pkUsuario;
    }

    public void setPkUsuario(int pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
