
public class Main {

    public static void main(String[] args) {
        Data data = new Data();
        try {
            data.getDataFromCLI();
            data.computeRadix();
            data.computeDecimalVariables();
            data.computeTargetVariables();
            printNumber(data.targetNumber, data.targetFraction);
        } catch (Exception e) {
            System.out.println("Error!! You have mistake in input! " + e);
        }
    }

    public static void printNumber(String number, String fraction) {
        if (fraction.equals("")) {
            System.out.println(number);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(number).append(".").append(fraction);
            System.out.println(sb);
        }
    }
}

