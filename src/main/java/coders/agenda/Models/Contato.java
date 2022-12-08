package coders.agenda.Models;

import coders.agenda.Enums.TipoContato;

import java.util.List;

public class Contato {
    private TipoContato tipoContato;
    private String nome = "";
    private String sobreNome = "";
    private List<Endereco> enderecos;
    private List<Telefone> telefones;

    public Contato(TipoContato tipoContato, String nome) {
        this.tipoContato = tipoContato;
        this.nome = nome;
    }

    public Contato(TipoContato tipoContato, String nome, String sobreNome, List<Endereco> enderecos, List<Telefone> telefones) {
        this.tipoContato = tipoContato;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Contato(TipoContato tipoContato, String nome, String sobreNome) {
        this(tipoContato, nome, sobreNome, List.of(), List.of());
    }

    public Contato(TipoContato tipoContato, String nome, List<Endereco> enderecos, List<Telefone> telefones) {
        this(tipoContato, nome, "", enderecos, telefones);
    }

    public String getFullName() {
        if (!sobreNome.isBlank()) {
            return nome + " " + sobreNome;
        }
        return nome;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String toString() {
        return "Contato{" +
                ", tipoContato=" + tipoContato +
                " nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", endereco=" + enderecos +
                ", telefone=" + telefones +
                '}';
    }
}
