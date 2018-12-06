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
    //求出 1，1，2，3,5,8
    public static void main(String[] args) {
        int jieCheng = 1;
        for(int i=1;i<=5;i++){
            jieCheng *= i;
        }
//        System.out.println(jieCheng);
        System.out.println(jieCheng(50000));
    }

    public static int jieCheng(int num){
        return num ==0 ? 0 : num + jieCheng(num-1);
    }


}
