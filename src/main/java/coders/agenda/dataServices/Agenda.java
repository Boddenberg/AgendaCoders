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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    static JSONArray myObject = new JSONArray();
    static List<Contato> contatos = new ArrayList<>();
    private static Path dbContato = Paths.get("src/main/java/coders/agenda/Database/Agenda.txt");

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
        //https://ourcodeworld.com/articles/read/836/how-to-work-with-json-easily-in-java

        AddToBase();
        readFromFile();


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

        Files.write(dbContato, contatosString);
        System.out.print("Dados gravados com sucesso\n\n");
    }

    private static List<Contato> readFromFile() throws IOException {
        List<Contato> contatosString = new ArrayList<>();

        try {
            List<String> data = Files.readAllLines(dbContato, StandardCharsets.UTF_8);

            for (int i = 0; i < data.size(); i++) {

                List<Endereco> enderecos = new ArrayList<>();
                List<Telefone> telefones = new ArrayList<>();

                JSONObject contato = new JSONObject(data.get(i));

                JSONArray getEnderecos = new JSONArray(contato.get("enderecos").toString());
                for (int j = 0; j < getEnderecos.length(); j++) {
                    JSONObject endereco = new JSONObject(getEnderecos.get(j).toString());

                    Endereco novoEndereco = new Endereco(
                            TipoEndereco.Residencial,
                            endereco.get("logradouro").toString(),
                            endereco.get("numero").toString(),
                            endereco.get("complemento").toString(),
                            endereco.get("nairro").toString(),
                            endereco.get("cidade").toString(),
                            endereco.get("uf").toString(),
                            endereco.get("cep").toString(),
                            endereco.get("pais").toString()
                    );

                    enderecos.add(novoEndereco);

                    System.out.println(endereco.names());

                }

                JSONArray myJson = new JSONArray(contato.get("telefones").toString());
                for (int k = 0; k < myJson.length(); k++) {
                    JSONObject endereco = new JSONObject(myJson.get(k).toString());
                    // TipoTelefone tipoTelefone, String ddi, String ddd, String numero, String ramal, String contato
                    Telefone novoTelefone = new Telefone (
                            TipoTelefone.Celular,
                            endereco.get("ddi").toString(),
                            endereco.get("ddd").toString(),
                            endereco.get("numero").toString(),
                            endereco.get("ramal").toString(),
                            endereco.get("contato").toString()
                    );

                    telefones.add(novoTelefone);

                    System.out.println(endereco.names());

                }

                Contato contatoResponse = new Contato(TipoContato.Pessoal, contato.get("nome").toString(), contato.get("sobrenome").toString());
                contatosString.add(contatoResponse);

                System.out.println("");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return contatosString;

    }

    private static void criarArquivo() {

        try {
            if (Files.exists(dbContato)) {
                System.out.println("Arquivo ja existe");
            } else {
                dbContato = Files.createFile(dbContato);
                System.out.println("Arquivo criado em: " + dbContato.toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
