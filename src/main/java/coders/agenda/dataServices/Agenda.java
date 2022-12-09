package coders.agenda.dataServices;

import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    static JSONObject myObject = new JSONObject();
    private static Path dbProduto = Paths.get("src/main/java/coders/agenda/Database/Agenda.txt");

    public static void main(String[] args) throws IOException {
        criarArquivo();

        myObject.put("name", "Carlos");
        myObject.put("last_name", "Carlos");

        // Primitive values
        myObject.put("age", new Integer(21));
        myObject.put("bank_account_balance", new Double(20.2));
        myObject.put("is_developer", new Boolean(true));

        double[] myList = {1.9, 2.9, 3.4, 3.5};
        myObject.put("number_list", myList);

        List<String> objetos = new ArrayList<>();
        objetos.add(myObject.toString());



        Files.write(dbProduto, objetos);
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
