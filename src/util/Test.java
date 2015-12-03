package util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {
  public static void main(String[] args) throws UnsupportedEncodingException {
     String str2 =  "name=%E3%81%9F%E3%81%A1%E3%81%A4%E3%81%91%E3%81%A6&priority";
     System.out.println(URLDecoder.decode("%E3%81%9F%E3%81%A1%E3%81%A4%E3%81%91%E3%81%A6"));

  }
}
