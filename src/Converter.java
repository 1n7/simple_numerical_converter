import java.util.ArrayList;

class Converter {
    protected Integer fromSourceToDecimal(String number, Integer radix) {
        return Integer.parseInt(number, radix);
    }

    protected String fromSourceToDecimalFraction(String fraction, Integer radix, ArrayList<Character> alphabet) {
        Double sum = 0d;
        String result;
        char[] work = fraction.toCharArray();
        int j = 1;
        for (int i = 0; i < fraction.length(); i++) {
            int chislitel = alphabet.indexOf(work[i]);
            sum += chislitel / Math.pow(radix, j);
            j++;
        }
        result = sum.toString().split("\\.")[1];

        // The code below is a dirty hack to improve accuracy. Can be rewritten on BigDecimal.
        if (result.length() < 10) {
            result += "0000000000";
        }
        return result.substring(0, 10);
    }

    protected String fromDecimalToTargetNumber(Integer numb, Integer radix) {
        return Integer.toString(numb, radix);
    }

    protected String fromDecimalToTargetFraction(String fraction, Integer radix, ArrayList<Character> alphabet) {
        String workFraction = "0." + fraction;
        double decimalFraction = Double.parseDouble(workFraction);
        String result = "";

        for (int i = 0; i < 5; i++) {
            decimalFraction *= radix;
            int index = takeIndex(decimalFraction);
            result += alphabet.get(index);
            decimalFraction = takeFraction(decimalFraction);
        }
        return result;
    }

    private Integer takeIndex(double number) {
        return (int) number;
    }

    private double takeFraction(double number) {
        String doubleAsString = String.valueOf(number);
        doubleAsString = doubleAsString.split("\\.")[1];
        return Double.parseDouble("0." + doubleAsString);
    }

    protected Integer fromOneToDecimal(String num) {
        return num.length();
    }

    protected String fromDecimalToOne(Integer num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(1);
        }
        return sb.toString();
    }
}
