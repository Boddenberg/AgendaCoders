package coders.agenda.Views;

import coders.agenda.Enums.TipoContato;
import coders.agenda.Enums.TipoEndereco;
import coders.agenda.Enums.TipoTelefone;
import coders.agenda.Models.Contato;
import coders.agenda.Models.Endereco;
import coders.agenda.Models.Telefone;
import coders.agenda.Utils.Errors.InvalidParams;
import coders.agenda.Utils.Input;
import coders.agenda.Utils.menuCreator;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    public static void UI() {
        System.out.println("===== BEM VINDO A AGENDA DE CONTATOS =====");

        boolean isWorking = true;


        while (isWorking) {

            try {
                int MainMenu = menuCreator.exec("Menu principal", "Sair", "Cadastrar contato", "Listar todos os contatos", "Buscar contatos por nome", "Remover contato", "Limpar agenda");

                switch (MainMenu) {
                    case 1:
                        cadastrarContato();
                        break;
                    case 2:
                        listarContatos();
                        break;
                    case 3:
                        listarContatosPorNome();
                        break;
                    case 4:
                        removerContato();
                        break;
                    case 5:
                        limparAgenda();
                        break;

                    case 0:
                        System.out.println("Obrigado, volte sempre");
                        isWorking = false;
                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    private static void cadastrarContato() {
        List<Telefone> telefones = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();
        System.out.println(" ====== CADASTRO DE CONTATO ======");

        boolean isInvalid;
        do {
            isInvalid = false;
            try {

                System.out.println("Informe o tipo do contato");

                for (TipoContato tipo : TipoContato.values()) {
                    System.out.printf("-> [%s] %s\n", tipo.ordinal(), tipo.name());
                }

                System.out.print("Escolha uma opção: ");
                int typeOption = Input.integer();
                System.out.print("\n");

                if (typeOption < 0 || typeOption >= TipoContato.values().length)
                    throw new InvalidParams("tipo do contato");


                TipoContato contactType = TipoContato.Pessoal;

                for (TipoContato type : TipoContato.values()) {
                    if (typeOption == type.ordinal()) {
                        contactType = type;
                    }
                }

                String name = Input.stringNotNullable("nome", 3);
                String lastName = Input.string("sobrenome");

                //cadastro de endereço

                int newAddress = menuCreator.exec("Deseja cadastrar um endereço?", "sim", "não");

                switch (newAddress) {
                    case 1:
                        cadastrarEndereco(enderecos);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }

                //cadastro de telefone

                int newContactPhone = menuCreator.exec("Deseja cadastrar um endereço?", "sim", "não");

                switch (newContactPhone) {
                    case 1:
                        cadastrarTelefones(telefones);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }

                Contato contato = new Contato(contactType, name, lastName, enderecos, telefones);
                //chamar controller de cadastro de contato


            } catch (InvalidParams ex) {
                System.out.println(ex.getMessage());
                isInvalid = true;

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                isInvalid = true;
            }

        } while (isInvalid);
    }

    private static void cadastrarTelefones(List<Telefone> telefones) {

        boolean isInvalid;
        do {
            isInvalid = false;
            try {

                System.out.println("Informe o tipo do contato");

                for (TipoTelefone tipo : TipoTelefone.values()) {
                    System.out.printf("-> [%s] %s\n", tipo.ordinal(), tipo.name());
                }

                System.out.print("Escolha uma opção: ");
                int typeOption = Input.integer();
                System.out.print("\n");

                if (typeOption < 0 || typeOption >= TipoTelefone.values().length)
                    throw new InvalidParams("tipo do contato");

                TipoTelefone tipoTelefone = TipoTelefone.Celular;

                for (TipoTelefone type : TipoTelefone.values()) {
                    if (typeOption == type.ordinal()) {
                        tipoTelefone = type;
                    }
                }

                int tipoCadastro = menuCreator.exec("Cadatro rápido ou completo?", "Rápido", "Completo");

                String ddd = Input.stringNotNullable("ddd", 3);
                String numero = Input.stringNotNullable("numero", 3);
                String contato = Input.stringNotNullable("contato", 3);

                switch (tipoCadastro) {
                    case 1:
                        Telefone contatoTelefonicoSimples = new Telefone(tipoTelefone, "", ddd, numero, "", contato);
                        telefones.add(contatoTelefonicoSimples);

                        break;
                    case 2:
                        String ddi = Input.stringNotNullable("ddi", 3);
                        String ramal = Input.stringNotNullable("ramal", 3);


                        Telefone contatoTelefonicoCompleto = new Telefone(tipoTelefone, ddi, ddd, numero, ramal, contato);
                        telefones.add(contatoTelefonicoCompleto);
                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }

                int novoTelefone = menuCreator.exec("Deseja cadastrar outro contato telefonico?", "Não", "Sim");

                switch (novoTelefone) {
                    case 0:
                        break;
                    case 1:
                        isInvalid = true;
                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }


            } catch (InvalidParams ex) {
                System.out.println(ex.getMessage());
                isInvalid = true;

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                isInvalid = true;
            }

        } while (isInvalid);


    }

    private static void cadastrarEndereco(List<Endereco> enderecos) {

        int novoEndereco = 0;
        boolean isInvalid;
        do {
            isInvalid = false;
            try {

                System.out.println("Informe o tipo do contato");

                for (TipoEndereco tipo : TipoEndereco.values()) {
                    System.out.printf("-> [%s] %s\n", tipo.ordinal(), tipo.name());
                }

                System.out.print("Escolha uma opção: ");
                int typeOption = Input.integer();
                System.out.print("\n");

                if (typeOption < 0 || typeOption >= TipoEndereco.values().length)
                    throw new InvalidParams("tipo do contato");


                TipoEndereco tipoEndereco = TipoEndereco.Residencial;

                for (TipoEndereco type : TipoEndereco.values()) {
                    if (typeOption == type.ordinal()) {
                        tipoEndereco = type;
                    }
                }

                if (tipoEndereco.equals(TipoEndereco.Virtual)) {
                    String email = Input.stringNotNullable("email", 3);
                    if (!email.matches("^(.+)@(\\S+)$")) throw new InvalidParams(email);

                    List<String> emails = new ArrayList<>();
                    emails.add(email);

                    Endereco enderecoVirtual = new Endereco(tipoEndereco, email);
                    enderecos.add(enderecoVirtual);

                    novoEndereco = menuCreator.exec("Deseja cadastrar outro endereço?", "Não", "Sim");

                    switch (novoEndereco) {
                        case 1:
                            isInvalid = true;
                            break;
                        case 0:
                            break;
                        default:
                            System.out.print("==== Opção inválida ====\n");
                    }
                    System.out.println("");
                    continue;
                }


                int tipoCadastro = menuCreator.exec("Cadatro rápido ou completo?", "Rápido", "Completo");

                String logradouro = Input.stringNotNullable("logradouro", 3);
                String numero = Input.stringNotNullable("numero", 3);
                String complemento = Input.string("complemento (opcional)");

                switch (tipoCadastro) {
                    case 1:
                        Endereco enderecoSimples = new Endereco(tipoEndereco, logradouro, numero, complemento);
                        enderecos.add(enderecoSimples);

                        break;
                    case 2:
                        String bairro = Input.stringNotNullable("bairro", 3);
                        String cidade = Input.stringNotNullable("cidade", 3);
                        String uf = Input.stringNotNullable("UF", 3);
                        String cep = Input.stringNotNullable("CEP", 3);
                        String pais = Input.stringNotNullable("país", 3);

                        Endereco enderecoCompleto = new Endereco(tipoEndereco, logradouro, numero, complemento, bairro, cidade, uf, cep, pais);
                        enderecos.add(enderecoCompleto);

                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }

                novoEndereco = menuCreator.exec("Deseja cadastrar outro contato telefonico?", "Não", "Sim");

                switch (novoEndereco) {
                    case 0:
                        break;
                    case 1:
                        isInvalid = true;
                        break;
                    default:
                        System.out.print("==== Opção inválida ====\n");
                }


            } catch (InvalidParams ex) {
                System.out.println(ex.getMessage());
                isInvalid = true;

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                isInvalid = true;
            }
        } while (isInvalid);
    }

    private static void listarContatos() {

        boolean isInvalid;
        int requestPool = 0;
        do {
            isInvalid = false;

            if (requestPool >= 3) {
                break;
            }

            try {
                //Chamar contatos. toString
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                requestPool += 1;
                isInvalid = true;
            }
        } while (isInvalid);

    }

    private static void listarContatosPorNome() {

//        c1. Deseja mais informações de um contato? (informe o contato)
        //        c11. Exibir todas as informações de um contato da agenda;
        //        c12. Listar todos os telefones de um contato da agenda;
        //           c121. Exibir todas as informações de um telefone de um contato da agenda;
        //        c13. Listar todos os endereços de um contato da agenda;
        //           c121. Exibir todas as informações de um endereço de um contato da agenda;
//        c2. Deseja editar um contato?
        //        c21. Adicionar um telefone a um contato;
        //        c22. Adicionar um endereço a um contato;
        //        c23. Remover um telefone de um contato da agenda;
        //        c24. Remover todos os contatos da agenda;
    }

    private static void removerContato() {
    }

    private static void limparAgenda() {
    }


}
