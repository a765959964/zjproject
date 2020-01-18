package com.example.demo.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 张帆
 * @create: 2019-05-17 9:43
 * @Description:
 **/
public class OrderNoUtils extends  Thread {

    private static long orderNum = 0l;
    private static String date ;

    public static void main(String[] args) throws InterruptedException {
        String str = "19051709515200001";
//        19103014252931730
//        19103016242900010
        for (int i = 0; i < 10; i++) {
            System.out.println(OrderNoUtils.getPartyNo());
//            Thread.sleep(1000);
        }
    }

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 100000;
        orderNo += orderNum;
        return orderNo+"";
    }
    
    
    /**
     * 生成聚餐编号
     * @return
     */
    public static synchronized String getPartyNo() {
        String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 1000;
        orderNo += orderNum;
        return orderNo+"";
    }
    
    
    /**
	 * 描述：四舍五入取整处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @return
	 */
	public static int roundUp(double v) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 0, BigDecimal.ROUND_UP).intValue();
	}
    

}
