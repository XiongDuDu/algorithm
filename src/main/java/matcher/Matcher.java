package matcher;

import java.util.regex.Pattern;

/**
 * @author duwei7
 * @time 2020/10/27 14:24
 */
public class Matcher {
    public static void main(String[] args) {
        String digitRegex = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(digitRegex);
        Matcher m = null;
        System.out.println(p.matcher("120").matches());
        System.out.println(p.matcher("compete").matches());

    }
}
