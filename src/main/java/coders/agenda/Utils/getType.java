package coders.agenda.Utils;

import coders.agenda.Utils.Errors.InvalidParams;

public class getType<T extends Enum<T>> {

    T tipoObjeto;

//    public <T extends Enum<T>> T exec(Class<T> enumType) throws InvalidParams {
//
//        System.out.println("Informe o tipo do contato");
//
//        for (T tipo : enumType.getEnumConstants()) {
//            System.out.printf("-> [%s] %s\n", tipo.ordinal(), tipo.name());
//        }
//
//        System.out.print("Escolha uma opção: ");
//        int typeOption = Input.integer();
//        System.out.print("\n");
//
//        if (typeOption < 0 || typeOption >= enumType.getEnumConstants().length)
//            throw new InvalidParams("tipo do contato");
//
//        for (T type : enumType.getEnumConstants()) {
//            if (typeOption == type.ordinal()) {
//                tipoObjeto = type;
//            }
//        }
//
//        return tipoObjeto;
//    }
}
