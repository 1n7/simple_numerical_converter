import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Data {
    private String stringSourceRadix = "";
    private String stringSourceNumber = "";
    private String stringTargetRadix = "";
    private String stringSourceFraction = "";

    boolean isNumberFractional = false;

    private Integer sourceRadix;

    private Integer decimalNumber;
    private String decimalFractionString = "";

    private Integer targetRadix;
    protected String targetNumber = "";
    protected String targetFraction = "";
    ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

    protected void getDataFromCLI() {
        Scanner scanner = new Scanner(System.in);

        stringSourceRadix = scanner.nextLine().toUpperCase();
        stringSourceNumber = scanner.nextLine().toUpperCase();
        stringTargetRadix = scanner.nextLine().toUpperCase();

        if (stringSourceNumber.contains(".")) {
            stringSourceFraction = stringSourceNumber.split("\\.")[1];
            stringSourceNumber = stringSourceNumber.split("\\.")[0];
            isNumberFractional = true;
        }
    }

    protected void computeRadix() throws Exception {
        try {
            sourceRadix = Integer.parseInt(stringSourceRadix);
            targetRadix = Integer.parseInt(stringTargetRadix);
            if (radixIsNotRight()) {
                throw new Exception("Radix must be between 1 and 36");
            }
        } catch (Exception e) {
            System.out.println("Your input was wrong: " + e);
            throw new Exception("Radix must be between 1 and 36");
        }
    }

    private boolean radixIsNotRight() {
        return sourceRadix < 1 || sourceRadix > 36 || targetRadix < 1 || targetRadix > 36;
    }

    protected void computeDecimalVariables() {
        Converter converter = new Converter();
        switch (sourceRadix) {
            case 10:
                decimalNumber = Integer.parseInt(stringSourceNumber);
                if (isNumberFractional) decimalFractionString = stringSourceFraction;
                break;
            case 1:
                decimalNumber = converter.fromOneToDecimal(stringSourceNumber);
                break;
            default:
                decimalNumber = converter.fromSourceToDecimal(stringSourceNumber, sourceRadix);
                if (isNumberFractional) {
                    decimalFractionString = converter.fromSourceToDecimalFraction(stringSourceFraction, sourceRadix, alphabet);
                }
                break;
        }
    }

    protected void computeTargetVariables() {
        Converter converter = new Converter();
        switch (targetRadix) {
            case 10:
                targetNumber = decimalNumber.toString();
                if (isNumberFractional) targetFraction = decimalFractionString;
                break;
            case 1:
                targetNumber = converter.fromDecimalToOne(decimalNumber);
                break;
            default:
                targetNumber = converter.fromDecimalToTargetNumber(decimalNumber, targetRadix);
                if (isNumberFractional)
                    targetFraction = converter.fromDecimalToTargetFraction(decimalFractionString, targetRadix, alphabet);
                break;
        }
    }
}
