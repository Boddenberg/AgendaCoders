package coders.agenda;

import coders.agenda.Enums.TipoContato;
import coders.agenda.Enums.TipoEndereco;
import coders.agenda.Enums.TipoTelefone;
import coders.agenda.Models.Contato;
import coders.agenda.Models.Endereco;
import coders.agenda.Models.Telefone;
import coders.agenda.dataServices.AgendaDAO;

import java.util.ArrayList;
import java.util.List;

public class Labbing {
    public static void main(String[] args) {

        try {
//            List<Telefone> telefones = new ArrayList<>();
//            List<Endereco> endereços = new ArrayList<>();
//
//            Telefone telefone2 = new Telefone(TipoTelefone.Celular, "55", "11", "963642358", "254", "washington Ferreira");
//            telefones.add(telefone2);
//
//            Endereco endereco2 = new Endereco(TipoEndereco.Residencial, "Rua bento de barros", "200", "Bloco 1 ap 82", "Jd Amaralina", "Sâo Paulo", "SP", "05570200", "Brasil");
//            endereços.add(endereco2);
//
//            Contato mock = new Contato(TipoContato.Pessoal, "Adolfo", "Pentellis", endereços, telefones);
//            new AgendaDAO().adicionarDados(mock);

//            List<Contato>teste = new AgendaDAO().listarDados();
//            System.out.println(teste.get(0).getFullName());


            new AgendaDAO().excluirDados(1);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(" O animal, não sabe ler não?");
        }
    }
}
