package coders.agenda.Utils.Errors;

public class NotNullable extends Exception {
    private String code;

    public NotNullable(String param) {
        super(param + " não pode ser vazio. Por favor, preencha corretamente");
    }

    public NotNullable(String param, String code) {
        this(param);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
