package coders.agenda.Models;

import coders.agenda.Enums.TipoEndereco;
import coders.agenda.Utils.Errors.InvalidParams;

import java.util.List;

public class Endereco {
    private TipoEndereco tipoEndereco;
    private String logradouro = "";
    private String numero = "";
    private String complemento = "";
    private String bairro = "";
    private String cidade = "";
    private String uf = "";
    private String cep = "";
    private String pais = "";

    private List<String> email;


    public Endereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Endereco(TipoEndereco tipoEndereco, String email) throws InvalidParams {
        this(tipoEndereco);
        if (tipoEndereco.name().equals("Virtual")) {
            this.email.add(email);
        } else {
            throw new InvalidParams("tipoEndereco");
        }
    }

    public Endereco(TipoEndereco tipoEndereco, String logradouro, String numero, String complemento) {
        this(tipoEndereco);
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(TipoEndereco tipoEndereco, String logradouro, String numero) {
        this(tipoEndereco, logradouro, numero, "");
    }

    public Endereco(TipoEndereco tipoEndereco, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String cep, String pais) {
        this(tipoEndereco, logradouro, numero, complemento);
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.pais = pais;
    }

    public Endereco(TipoEndereco tipoEndereco, String logradouro, String numero, String bairro, String cidade, String uf, String cep, String pais) {
        this(tipoEndereco, logradouro, numero, "complemento", bairro, cidade, uf, cep, pais);
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

//    public String getFullAddress() {
//        StringBuilder address = new StringBuilder(tipoEndereco.toString() + " " + logradouro + ", " + numero);
//        List<String> atributos = List.of(pais, cep, cidade, bairro, complemento);
//        for (String atributo : atributos) {
//            if (!atributo.isBlank()) address.append(" ").append(atributo);
//        }
//        return address.toString();
//    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) throws InvalidParams {
        if (cep.matches("[0-9]{5}-[0-9]{3}")) {
            this.cep = cep;
        } else {
            throw new InvalidParams("cep");
        }
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.add(email);
    }

    @Override
    public String toString() {
        return "\nEndereco{" +
                " tipoEndereco=" + tipoEndereco +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

}
