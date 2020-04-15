package buy.baabaashop.utils;

import buy.baabaashop.entity.client.OrderParam;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanmian on 2020/4/10
 */
public class Utils {

    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 生成18位订单编号:8位日期+2位支付方式+3位随机数+用户id
     */
    public static String generateOrderSn(OrderParam orderParam) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Integer random = (int) (Math.random()*100);
        sb.append(date);
        sb.append(String.format("%02d",orderParam.getPayType()));
        if(random.toString().length() < 3){
            sb.append(String.format("%03d",random));
        }else{
            sb.append(random);
        }
        if(orderParam.getUserId().toString().length() <= 5){
            sb.append(String.format("%05d",orderParam.getUserId()));
        }else{
            sb.append(orderParam.getUserId());
        }
        return sb.toString();
    }

    //从request获取JSON
    public static String getRequestBodyAsString(HttpServletRequest request) throws Exception {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String s = sb.toString();
        System.out.println("请求数据：" + s);
        return s;
    }
}
