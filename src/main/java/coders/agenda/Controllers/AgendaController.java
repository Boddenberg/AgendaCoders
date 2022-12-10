package coders.agenda.Controllers;

import coders.agenda.Models.Contato;

import java.util.ArrayList;
import java.util.List;

public class AgendaController {

    private List<Contato> contatos;

    public AgendaController() {
        this.contatos = new ArrayList<>();
    }

    public void adicionar(Contato contato) {
        contatos.add(contato);
    }


    public void excluir(Contato contato) {
        contatos.remove(contato);
    }

    public List<Contato> pesquisarNome(String nome) {
        List<Contato> contatosEncontrados = new ArrayList<>();
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

    public List<Contato> listar(int start, int quantidade) {
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



}
