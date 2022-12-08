package coders.agenda.Utils.Errors;

public class InvalidParams extends Exception{

    private String code;
    public InvalidParams(String param) {
        super(param + " é inválido, por favor preencha corretamente ");
    }

    public InvalidParams(String param, String code) {
        this(param);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
