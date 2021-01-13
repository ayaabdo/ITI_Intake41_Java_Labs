package prb2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpCutter {
    public static void main(String[] args)
    {
        if(!isValid(args[0]))
        {
            System.out.println("Error ip pattern");
            return;
        }
        String[] ip = args[0].split("\\.");

        for(String i : ip)
        {
            System.out.println(i);
        }
    }
    static boolean isValid(String ip)
    {
        String validate = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
        String tmp = validate+"\\."+validate
                +"\\."+validate+"\\."+validate;
        Pattern p = Pattern.compile(tmp);
        if(ip == null) return false;
        Matcher m = p.matcher(ip);
        return m.matches();

    }
}
