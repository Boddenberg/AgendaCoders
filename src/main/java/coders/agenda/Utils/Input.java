package coders.agenda.Utils;

import coders.agenda.Utils.Errors.NotNullable;

import java.util.Scanner;

public class Input {
    public static String string() {
        return new Scanner(System.in).nextLine().trim();
    }

    public static String stringNotNullable(String nomeDoCampo, int tentativas) throws Exception {
        int tentadas = 0;
        boolean isInvalid;
        String input = "";

        do {
            isInvalid = false;

            try {
                System.out.printf("Por favor informe o %s\n", nomeDoCampo);
                input = new Scanner(System.in).nextLine().trim();

                if (input.isBlank()) throw new NotNullable(nomeDoCampo);


            } catch (NotNullable ex) {
                tentadas += 1;

                System.out.printf("msg: %s, code: %s. tente novamente\n", ex.getMessage(), ex.getCode());
                if (tentadas < tentativas) {
                    isInvalid = true;
                    continue;
                }

                throw new Exception("Número de tentativas excedidas");
            }

        } while (isInvalid);

        return input;
    }

    public static int integer(){
        return new Scanner(System.in).nextInt();
    }

}
