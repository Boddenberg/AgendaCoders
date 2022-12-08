package coders.agenda.Models;

import agenda_contatos.Enums.TipoTelefone;

public class Telefone {
    private TipoTelefone tipoTelefone;
    private String ddi;
    private String ddd;
    private String numero;
    private String ramal;
    private String contato;

    public Telefone(TipoTelefone tipoTelefone, String ddi, String ddd, String numero, String ramal, String contato) {
        this.tipoTelefone = tipoTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.ramal = ramal;
        this.contato = contato;
    }

    public Telefone(TipoTelefone tipoTelefone, String ddd, String numero, String contato) {
        this(tipoTelefone, "", ddd, numero, "", contato);
    }

    public Telefone(TipoTelefone tipoTelefone,String ddi, String ddd, String numero, String contato) {
        this(tipoTelefone, ddi, ddd, numero, "", contato);
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getFullPhoneNumber() {
        String phoneNumber = String.format("( %s ) %s", ddd, numero);

        if (!ddi.isBlank()) phoneNumber = String.format("+%s ( %s ) %s", ddi, ddd, numero);
        if (!ramal.isBlank()) phoneNumber = phoneNumber += " - " + ramal;
        phoneNumber = "Tipo de telefone: " + tipoTelefone + " => " + phoneNumber;

        return phoneNumber;
    }

    @Override
    public String toString() {
        return "\nTelefone{" +
                "tipoTelefone=" + tipoTelefone +
                ", ddi='" + ddi + '\'' +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", ramal='" + ramal + '\'' +
                ", contato='" + contato + '\'' +
                '}';
    }
}
