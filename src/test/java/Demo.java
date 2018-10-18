import java.text.Collator;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-10-12 14:20
 * @Description:
 **/
public class Demo {

    public static void main(String[] args) {
        String[] arr = { "中餐菜系", "东亚其它菜系", "西餐菜系", "清真菜系", "其它" };
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        Arrays.sort(arr, cmp);
        List<String> list = Arrays.asList(arr);
        System.out.println(list);
    }

}
