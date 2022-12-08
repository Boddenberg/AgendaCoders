package coders.agenda.Utils;

import coders.agenda.Utils.Errors.InvalidParams;

import java.util.Scanner;

public class menuCreator {

    public static int exec(String Message, String... options) throws Exception {
        int chooseYourDestiny = 0;
        int tentadas = 0;
        //fight

        boolean isInvalid;

        do {
            isInvalid = false;
            try {
                System.out.println(Message);
                for (int i = 0; i < options.length; i++) {
                    System.out.printf("[%d] - %s\n", i, options[i]);
                }
                System.out.print("Escolha uma das opções: ");

                chooseYourDestiny = Input.integer();
                System.out.println("");

                if (chooseYourDestiny >= options.length) throw new InvalidParams("menuOption");

            } catch (Exception ex) {
                tentadas += 1;
                System.out.printf("Opção inválida, tente novamente.\n\n", ex.getMessage());
                if (tentadas < 3) {
                    isInvalid = true;
                    continue;
                }
                throw new Exception("Número de tentativas excedidas");
            }

        } while (isInvalid);

        return chooseYourDestiny;
    }

}
