package prb1;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {
    public static void main(String[] args)
    {
        String[] ops = new String[(args.length)/2];
        int[] nums = new int[((args.length)/2)+1];
        int j=0,k=0;
        for(int i = 0;i < args.length; ++i)
        {
            if(i%2 == 1)
            {
                ops[j]=args[i];
                j++;
            }
            else
            {
                if(!isDigit(args[i]))
                {
                    System.out.println("NAN");
                    return;
                }
                nums[k] = Integer.parseInt(args[i]);
                k++;
            }
        }
        int cnt=0,ans=nums[0];
        int tmpj=0,tmpk=1;
        while(tmpj < j && tmpk < k)
        {
            switch (ops[tmpj])
            {
                case "+": ans += nums[tmpk];
                    break;
                case "-": ans -= nums[tmpk];
                    break;
                case "*": ans *= nums[tmpk];
                    break;
                case "/": ans /= nums[tmpk];
                    break;
            }
            tmpj++;
            tmpk++;
        }
        System.out.println(ans);
    }
    static boolean isDigit(String str)
    {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if(str == null) return false;
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
