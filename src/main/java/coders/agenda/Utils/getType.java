package coders.agenda.Utils;

import coders.agenda.Enums.TipoContato;
import coders.agenda.Utils.Errors.InvalidParams;

public class getType<T> {

       T tipoObjeto ;

    public T exec() throws InvalidParams {

        System.out.println("Informe o tipo do contato");

        for (T tipo : T.values()) {
            System.out.printf("-> [%s] %s\n", tipo.ordinal(), tipo.name());
        }

        System.out.print("Escolha uma opção: ");
        int typeOption = Input.integer();
        System.out.print("\n");

        if (typeOption < 0 || typeOption >= T.values().length)
            throw new InvalidParams("tipo do contato");


        T contactType = T.Pessoal;

        for (T type : T.values()) {
            if (typeOption == type.ordinal()) {
                contactType = type;
            }
        }

        return contactType;
    }
}
