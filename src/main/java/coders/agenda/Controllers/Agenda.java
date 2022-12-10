package coders.agenda.Controllers;

import coders.agenda.Models.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    List<Contato> contatos = new ArrayList<>();

    public void criarContato(Contato contato){
        contatos.add(contato);
    }



}
