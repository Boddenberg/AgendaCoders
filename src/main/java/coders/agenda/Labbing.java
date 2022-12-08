package coders.agenda;

import coders.agenda.Utils.Input;
import coders.agenda.Utils.menuCreator;

public class Labbing {
    public static void main(String[] args) {

        try {
            //testando componente menuCreator
            Integer teste = menuCreator.exec("Por favor escolha uma opção", "voltar", "Criar contato", "editar contato", "excluir contato");
            System.out.println(teste);

            if(teste == 0){
                System.out.println("voltaondo ao menu anterior...");
            }

            String inputStringTeste = Input.string("nome");
            System.out.println(inputStringTeste);

            String inputNotNullableStringTeste = Input.stringNotNullable("teste de string", 5);
            System.out.println(inputNotNullableStringTeste);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(" O animal, não sabe ler não?");
        }
    }
}
