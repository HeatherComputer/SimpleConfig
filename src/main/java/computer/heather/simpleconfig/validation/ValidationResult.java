package computer.heather.simpleconfig.validation;

public enum ValidationResult {

    OUT_OF_RANGE,
    INCORRECT_FORMAT,
    VALID;

    public String getError() {
        switch (this) {
            case OUT_OF_RANGE:
                return "Value not within specified parameters!";
            case INCORRECT_FORMAT:
                return "Value is of an incorrect type!";
            default:
                return "";
        }
    }
}
