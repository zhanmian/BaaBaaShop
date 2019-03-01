package buy.baabaashop.configurations;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

    @Value("${baseUrl}")
    private static String baseUrl;
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092300577934";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDUEGCbQ6gniyimzPTaOijTcxOXCut6SsY41z8i23qx1on7Ny0aGS7Ry3fuOwAAAQriRWSePyS5uJUsDz0QI7mPEY4egE0hlMfMQ6Ll3lMgJsDR0igIS6FlWsRi+BkUcsIW9WvT5aTrKc29BgrrDVWg4IdXgMBzoa6sE9ByKDAZS1Mbq8DTxWIdFC0+4y6+h2aLM7vKn71BXhnm9tE0yX6G/pwsqR+qX0llamBe0Qnmn3UX1EcPBfN18WEmM3p5UqhP4Cl+2q5FWlB7RPGwLl8VL4xFKxqPdLfOh672osHrX23kKhPCzURTAFBpKg2n2H2lxUDd0vnBclO0bfGlmM3nAgMBAAECggEAflNDOTqUcPn0vWhac6UxrMRgZxmaLL9eVX3jnmAgNtAWYg/8Tg+yVEdgqgl7doUodqHfzmTO+9N5CUYKmhKt9wv+HS1QxTBb1NJ1t3k5C24KbvNB5a8n9bicRFiGmCfEdui70v55v5yncnKnDl5jI/KcrQOFoVwvMVKcWWSXQnkGWH0UUptccp9aAqzsZ+cAaWAgX9cVtvbpFTuGF0T1YuVTSYOZNUmwfmqqvezwugezRCcVNRA7cnK9i1YSvXt2AxXprqTOPP+X7esDbkm45ZrOrSaUPEq8sOiWmIdB/GVSQVmJZg3z43WD0FUMnvCGOKUe3pDOswaxNeeD63EkAQKBgQD4vOXhtdB5MVPjRnM1083uYzCN7akGk651kcbL0x34yFtJ6BgOAulINeLscALNy8JkxW+DqfpNry362SFqzR5p9mry2leJpo/ZQJ3d5XT9qUBgXJXaIJFwstcdQp893Ue88RIGOqX0GFVCQPJVx/xl+U18NSFIhqNWSnypSN44CwKBgQDaQV+Y7c0m8NCSxTEh6H+1vZ87tmju0ojSasU+wNz19Jm7t9ORs2cRf+2VPYx1w58TLox3P95srvVyEIJWDKKzBx+5+FoJHrM/HUGc29gt6Ul6VcSqYazEMvJokWekw/j1hbEppw5h83jbVqNiVxC6PI0EbMm8iSWf2WnE4Wq/FQKBgQCQzh+iVB9mvpHQqS3fM0wasQIOTkJtgsI4zcIG3QQA1ltW3lhKp+U5Va2i6d6oAixMUCiIZQdD/GzvMULkEz/Wfk9qzDPT/fSWjKibCr8eAU9qCdxvXTBuhJ74+NUNxenClDFjEPeV8CiKZpbt23wTvZwfaCrVNxSV4s1KHBLKWwKBgBmStV/W5UXdy6KW5mXhty0VVdYKTotFMfDtv8TVlk5fZbAAHxMgYUBErBLjjlSyIi5+Msk6O5aifXHrC0qH2nx9pjMFLY+KAFix1eFKRS9D+W8TI6MPJM4sZgjomKtOpxolmxeVUnMZsxyIL0azius3cxKEqLKloznyoJkIDJjtAoGAXFYfgW1QY7TEsM5NmMj15C6RChX67FDTxElIMGyJLOu01T5Lfl40AKezGlZiDHFYJUMUHtWXlWbH410uS5YNwQYyirIRXn6cyz6ucMGBOTrTfYfLxCwNAg6MHyaSl9AWdydNChwt9h1skl8hFwiTEd8BUuQP5ms+ekQm5U0kQFM=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs131y0PgeMIYSXckRcMGBISbe3SZ2g2sUkNNMkWD2UR956aLVF6GrTTR8Ciuy9xZQk5KOL2zpCOyXJYMkMsNuYA1Vd2qPXG1btvH5us6eepbd/zMl/Lxq7r3WVt5a/hnQhqJB6MEY+R9Q4DgKvwmRypEEi/vT6eH72LetcsNaww7nighovoPpgGJTHMpygurb8svxdAfwluokmBUhOw3X5bodCuc31t1+R6MbtGhbio1YkLmPBd8r4VqWbwbDz92zqb+h0M1eP6LyjJzsqrEWTc/WXQFUA+6Pk2cgQRipoFt2M6XibRRR9n5TI1mRDvrdTGl805mPywObLZN+J5lXwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = baseUrl +"/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = baseUrl + "/baabaa/alipay_return";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

