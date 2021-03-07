import com.sun.jdi.event.StepEvent;

import java.math.BigInteger;
import java.util.ArrayList;

public class SHA1 {
    String h0 = "67452301";
    String h1 = "EFCDAB89";
    String h2 = "98BADCFE";
    String h3 = "10325476";
    String h4 = "C3D2E1F0";

//    public String digest(String mes){
//
//    }

    public ArrayList<String> messageToBlocks(String mes){
        String bin = this.toBinary(mes);
        System.out.println("Message length in bits: " + bin.length());
        StringBuilder sb = new StringBuilder();
        ArrayList<String> blocks = new ArrayList<String>();
        int lastIndex = 0; //last index of added bit of char
        for (int i = 1; i < bin.length(); i++){ //handling bit sequence
            if (i%512 == 0){
                sb.append(bin.substring(lastIndex, i));
                lastIndex = i;
                blocks.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        int sum = 0; //sum of 512 blocks
        for (int i = 0; i < blocks.size(); i ++){
            sum += blocks.get(i).length();
        }
        if (sum != bin.length()){
            if (bin.length() - sum < 448) { //length of the remaining block
                sb.append(bin.substring(lastIndex, lastIndex + (bin.length() - sum)));
                sb.append('1');
                lastIndex++;
                for (int i = lastIndex + (bin.length() - sum); i % 512 != 448; i++) {
                    sb.append('0');
                }
                String mesLen = Integer.toString(bin.length(), 2);
                if (mesLen.length() != 64) { // adding zeros to make size == 64
                    for (int i = 0; i < 64 - mesLen.length(); i++) {
                        sb.append('0');
                    }
                    sb.append(mesLen);
                }
                blocks.add(sb.toString());
                sb = new StringBuilder();
            } else { //if length of last block more the 447
                sb.append(bin.substring(lastIndex, lastIndex + (bin.length() - sum)));
                sb.append('1');
                lastIndex++;
                for (int i = lastIndex + (bin.length() - sum); i % 512 != 0; i++) {
                    sb.append('0');
                }
                blocks.add(sb.toString());
                sb = new StringBuilder();//preparing for new block

                sb.append('1');
                for (int i = 1; i % 512 != 448; i++) {
                    sb.append('0');
                }
                String mesLen = Integer.toString(bin.length(), 2);
                if (mesLen.length() != 64) {
                    for (int i = 0; i < 64 - mesLen.length(); i++) { // adding zeros to make size == 64
                        sb.append('0');
                    }
                    sb.append(mesLen);
                }
                blocks.add(sb.toString());
            }
        }
        return blocks;
    }

    public String strXor(String s1, String s2){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length(); i ++){
            sb.append(s1.charAt(i) ^ s2.charAt(i));
        }
        return sb.toString();
    }

    public String strInversion(String str){
        return str.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
    }

    public String strAnd(String s1, String s2){
        String res = Integer.toBinaryString(Integer.parseInt(s1, 2) & Integer.parseInt(s2, 2));
        if (res.length() < s1.length()){
            int d = s1.length() - res.length();
            StringBuilder sb = new StringBuilder();
            for (int i = d; i != 0; i--){
                sb.append("0");
            }
            sb.append(res);
            res = sb.toString();
        }
        return res;
    }

    public String strOr(String s1, String s2){
        String res = Integer.toBinaryString(Integer.parseInt(s1, 2) | Integer.parseInt(s2, 2));
        if (res.length() < s1.length()){
            int d = s1.length() - res.length();
            StringBuilder sb = new StringBuilder();
            for (int i = d; i != 0; i--){
                sb.append("0");
            }
            sb.append(res);
            res = sb.toString();
        }
        return res;
    }

    public String leftRotate(String str, int d){
        return str.substring(d) + str.substring(0, d);
    }

    public String rightRotate(String str, int d){
        return leftRotate(str, str.length() - d);
    }

    public String toBinary(String mes){
        return new BigInteger(mes.getBytes()).toString(2);
    }

    public String fromBinary(String binary){
        return new String(new BigInteger(binary, 2).toByteArray());
    }
}
