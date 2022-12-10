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

            JSONArray getEnderecos = new JSONArray(contato.get("enderecos").toString());
            for (int j = 0; j < getEnderecos.length(); j++) {
                JSONObject endereco = new JSONObject(getEnderecos.get(j).toString());

                Endereco novoEndereco = new Endereco(
                        TipoEndereco.Residencial,
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

            JSONArray myJson = new JSONArray(contato.get("telefones").toString());
            for (int k = 0; k < myJson.length(); k++) {
                JSONObject endereco = new JSONObject(myJson.get(k).toString());
                // TipoTelefone tipoTelefone, String ddi, String ddd, String numero, String ramal, String contato
                Telefone novoTelefone = new Telefone(
                        TipoTelefone.Celular,
                        endereco.get("ddi").toString(),
                        endereco.get("ddd").toString(),
                        endereco.get("numero").toString(),
                        endereco.get("ramal").toString(),
                        endereco.get("contato").toString()
                );

                telefones.add(novoTelefone);
            }

            Contato contatoResponse = new Contato(TipoContato.Pessoal, contato.get("nome").toString(), contato.get("sobrenome").toString());
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
