package coders.agenda.dataServices;

import coders.agenda.Enums.TipoContato;
import coders.agenda.Enums.TipoEndereco;
import coders.agenda.Enums.TipoTelefone;
import coders.agenda.Models.Contato;
import coders.agenda.Models.Endereco;
import coders.agenda.Models.Telefone;
import coders.agenda.Utils.Errors.InvalidParams;
import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    static JSONArray myObject = new JSONArray();
    static List<Contato> contatos = new ArrayList<>();
    private static Path dbProduto = Paths.get("src/main/java/coders/agenda/Database/Agenda.txt");

    public static void main(String[] args) throws IOException {
        criarArquivo();


        try {
            mockingContacts("Washington", "Ferreira");
            mockingContacts("Pedro", "Ferreira");
            mockingContacts("Elza", "Santos");
        } catch (InvalidParams e) {
            throw new RuntimeException(e);
        }

//        System.out.println(contatos.get(0).getTipoContato().toString());

        AddToBase();


    }

    private static void mockingContacts(String nome, String Sobrenome) throws InvalidParams {
        List<Telefone> telefones = new ArrayList<>();
        List<Endereco> endereços = new ArrayList<>();

//        Telefone telefone1 = new Telefone(TipoTelefone.Celular, "11", "963642358", "washington Ferreira");
        Telefone telefone2 = new Telefone(TipoTelefone.Celular, "55", "11", "963642358", "254", "washington Ferreira");
//        telefones.add(telefone1);
        telefones.add(telefone2);

        Endereco endereco2 = new Endereco(TipoEndereco.Residencial, "Rua bento de barros", "200", "Bloco 1 ap 82", "Jd Amaralina", "Sâo Paulo", "SP", "05570200", "Brasil");
        endereços.add(endereco2);

        Contato mock = new Contato(TipoContato.Pessoal, nome, Sobrenome, endereços, telefones);
        contatos.add(mock);
    }

    private static void AddToBase() throws IOException {
        List<String> contatosString = new ArrayList<>();

        for (int i = 0; i < contatos.size(); i++) {
            JSONObject myObject = new JSONObject();
            Contato uTemp = contatos.get(i);

            myObject.put("nome", uTemp.getNome());
            myObject.put("sobrenome", uTemp.getSobreNome());
            myObject.put("tipoContato", uTemp.getTipoContato());

            myObject.put("enderecos", uTemp.getEnderecos());
            myObject.put("telefones", uTemp.getTelefones());

            contatosString.add(myObject.toString());


        }

        Files.write(dbProduto, contatosString);
        System.out.print("Dados gravados com sucesso\n\n");
    }

    private static void criarArquivo() {

        try {
            if (Files.exists(dbProduto)) {
                System.out.println("Arquivo ja existe");
            } else {
                dbProduto = Files.createFile(dbProduto);
                System.out.println("Arquivo criado em: " + dbProduto.toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
