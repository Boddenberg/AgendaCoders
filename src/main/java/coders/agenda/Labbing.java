package coders.agenda;

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

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(" O animal, não sabe ler não?");
        }
    }
}
