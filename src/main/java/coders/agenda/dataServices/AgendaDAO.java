package coders.agenda.dataServices;

import coders.agenda.Enums.TipoContato;
import coders.agenda.Enums.TipoEndereco;
import coders.agenda.Enums.TipoTelefone;
import coders.agenda.Models.Contato;
import coders.agenda.Models.Endereco;
import coders.agenda.Models.Telefone;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
    private Path dbContato = Paths.get("src/main/java/coders/agenda/Database/Agenda.txt");

    public void adicionarDados(Contato contato) throws IOException {
        criarArquivo();
        JSONObject data = new JSONObject();
        Files.writeString(dbContato, convertToJson(contato) + "\n", StandardOpenOption.APPEND);
        System.out.print("Dados gravados com sucesso\n\n");
    }

    private List<String> getDataList() throws IOException {
        List<String> listarContatos = Files.readAllLines(dbContato, StandardCharsets.UTF_8);
        return listarContatos;
    }

    public List<Contato> listarDados() throws IOException {
        List<Contato> contatosString = new ArrayList<>();
        List<String> data = getDataList();

        for (int i = 0; i < data.size(); i++) {

            List<Endereco> enderecos = new ArrayList<>();
            List<Telefone> telefones = new ArrayList<>();

            JSONObject contato = new JSONObject(data.get(i));

            JSONArray listaEnderecos = new JSONArray(contato.get("enderecos").toString());
            for (int j = 0; j < listaEnderecos.length(); j++) {
                JSONObject endereco = new JSONObject(listaEnderecos.get(j).toString());

                TipoEndereco tipoEndereco = null;

                for (TipoEndereco tipo : TipoEndereco.values()) {
                    if (endereco.get("tipoEndereco").toString().equals(tipo.name())) {
                        tipoEndereco = tipo;
                    }
                }

                Endereco novoEndereco = new Endereco(
                        tipoEndereco,
                        endereco.get("logradouro").toString(),
                        endereco.get("numero").toString(),
                        endereco.get("complemento").toString(),
                        endereco.get("bairro").toString(),
                        endereco.get("cidade").toString(),
                        endereco.get("uf").toString(),
                        endereco.get("cep").toString(),
                        endereco.get("pais").toString()
                );

                enderecos.add(novoEndereco);
            }

            JSONArray listaTelefones = new JSONArray(contato.get("telefones").toString());
            for (int k = 0; k < listaTelefones.length(); k++) {
                JSONObject telefone = new JSONObject(listaTelefones.get(k).toString());

                TipoTelefone tipoTelefone = null;

                for (TipoTelefone tipo : TipoTelefone.values()) {
                    if (telefone.get("tipoTelefone").toString().equals(tipo.name())) {
                        tipoTelefone = tipo;
                    }
                }

                Telefone novoTelefone = new Telefone(
                        tipoTelefone,
                        telefone.get("ddi").toString(),
                        telefone.get("ddd").toString(),
                        telefone.get("numero").toString(),
                        telefone.get("ramal").toString(),
                        telefone.get("contato").toString()
                );

                telefones.add(novoTelefone);
            }

            TipoContato tipoContato = null;

            for (TipoContato tipo : TipoContato.values()) {
                if (contato.get("tipoContato").toString().equals(tipo.name())) {
                    tipoContato = tipo;
                }
            }

            Contato contatoResponse = new Contato(tipoContato, contato.get("nome").toString(), contato.get("sobrenome").toString(), enderecos, telefones);
            contatosString.add(contatoResponse);

            System.out.println("");
        }

        return contatosString;
    }

    public void editarDados(Contato contato, int posicao) throws IOException {
        List<String> listarOriginais = getDataList();
        listarOriginais.set(posicao, convertToJson(contato)); // passar contato para json
        Files.write(dbContato, listarOriginais);
    }

    private String convertToJson(Contato contato) {
        JSONObject data = new JSONObject();
        data.put("nome", contato.getNome());
        data.put("sobrenome", contato.getSobreNome());
        data.put("tipoContato", contato.getTipoContato());

        data.put("enderecos", contato.getEnderecos());
        data.put("telefones", contato.getTelefones());

        return data.toString();
    }

    public void excluirDados(int posicao) throws IOException {
        List<String> listarOriginais = getDataList();
        listarOriginais.remove(posicao);
        Files.write(dbContato, listarOriginais);
    }

    public void limparAgenda() throws IOException {
        Files.writeString(dbContato, "");
    }


    private void criarArquivo() {

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
