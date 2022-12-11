package coders.agenda.Controllers;

import coders.agenda.Enums.TipoContato;
import coders.agenda.Enums.TipoEndereco;
import coders.agenda.Enums.TipoTelefone;
import coders.agenda.Models.Contato;
import coders.agenda.Models.Endereco;
import coders.agenda.Models.Telefone;
import coders.agenda.dataServices.AgendaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgendaController {

    private List<Contato> contatos;

    public AgendaController() {
        this.contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) throws IOException {
        new AgendaDAO().adicionarDados(contato);
    }


    public void excluirContato(int id) throws IOException {
//        new AgendaDAO().excluirContato(id);
    }

    public void editarContato(Contato contato, int id) throws IOException {
        new AgendaDAO().editarDados(contato, id);
    }

    public void excluirTodosContatos() {
//        new AgendaDAO().limparAgenda();
    }

    public void listarContatos(){
//        new AgendaDAO().listarDados();
    }



    public List<Contato> pesquisarNome(String nome) throws IOException {
        List<Contato> contatosEncontrados = new ArrayList<>();
        List<Contato> contatos = new AgendaDAO().listarDados();
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().contains(nome)) {
                contatosEncontrados.add(contatos.get(i));
            }
        }
        return contatosEncontrados;
    }



    public List<Contato> pesquisarEmail(String email) {
        List<Contato> contatosEncontrados = new ArrayList<>();
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().contains(email)) {
                contatosEncontrados.add(contatos.get(i));
            }
        }
        return contatosEncontrados;
    }

    public List<Contato> listarContatos(int start, int quantidade) {
        if (start < 0 || start >= contatos.size()) {
            start = 0;
        }
        if (quantidade < 0) {
            quantidade = 0;
        }
        if (quantidade > contatos.size()) {
            quantidade = contatos.size();
        }

        if (start + quantidade >= contatos.size()) {
            quantidade = contatos.size() - start;
        }
        List<Contato> contatosEncontrados = new ArrayList<>();
        for (int i = start; i < start + quantidade; i++) {
            contatosEncontrados.add(contatos.get(i));
        }
        return contatosEncontrados;
    }

    public Contato cadastrarContato(TipoContato tipoContato, String nome, String sobrenome, List<Endereco> enderecos, List<Telefone> telefones){
        Contato contato = new Contato(tipoContato, nome, sobrenome, enderecos, telefones);
        return contato;
    }

    public Endereco cadastrarEndereco(TipoEndereco tipoEndereco, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String cep, String  pais){
        Endereco endereco = new Endereco(tipoEndereco, logradouro, numero, complemento, bairro, cidade, uf, cep, pais);
        return endereco;
    }

    public Telefone cadastrarTelefone(TipoTelefone tipoTelefone, String ddi, String ddd, String numero, String ramal, String contato){
        Telefone telefone = new Telefone(tipoTelefone, ddi, ddd, numero, ramal, contato);
        return telefone;
    }





}
