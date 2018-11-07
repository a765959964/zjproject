import com.santint.core.page.PageBean;
import com.santint.core.page.PageUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-10-12 14:20
 * @Description:
 **/
public class Demo {

    public static void main(String[] args) {
       String field =  Demo.getPx("global");
        System.out.println(field);
    }

    public  static final String getPx(String px){
        String field = "";
        switch (px){
            case "price":
                field = "price";
                break;
            case "sales":
                field = "sales";
                break;
            case "score":
                field = "score";
                break;
            case "global":
                field = "global";
                break;
        }
        return field;
    }

}
