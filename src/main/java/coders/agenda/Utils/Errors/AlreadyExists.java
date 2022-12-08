package coders.agenda.Utils.Errors;

public class AlreadyExists extends Exception {
    private String code;

    public AlreadyExists(String param) {
        super(param + "já existente no contato");
    }

    public AlreadyExists(String param, String code) {
        this(param);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
