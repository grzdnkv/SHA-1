import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SHA1 alg = new SHA1();
//        String hex = "9";
//        int num = Integer.parseInt(hex, 16);
//        System.out.println(num);

        //String mes = "The quick brown fox jumps over the lazy dog";
        String mes = "The binary numeral system, or base-2 number system, " +
                "represents numeric values using two symbols, 0 and 1. More specifically, " +
                "the usual base-2 system is a positional notation with a radix of 2. " +
                "Owing to its straightforward implementation in digital electronic circuitry " +
                "using logic gates, the binary system is used internally by all modern computers.";
        ArrayList<String> blocks = alg.messageToBlocks(mes);
        System.out.println(blocks.size());

        ArrayList<String> w = new ArrayList<>();

        String s1 = "1000";
        String s2 = "1000";

        String tmp = Integer.toBinaryString(Integer.parseInt(s1, 2) + Integer.parseInt(s2, 2));

        System.out.println(tmp); //дополнение наезначащих нулей

        int h0 = "67452301";
        int h1 = "efcdab89";
        int h2 = "98BADCFE";
        String h3 = "10325476";
        String h4 = "C3D2E1F0";

        for (int i = 0; i < blocks.size(); i++){

            int begin = 0;
            int end = 0;
            for (int j = 0; j < blocks.get(i).length(); j++){
                if (j%32 == 0){
                    w.add(blocks.get(i).substring(j, j+32));
                }
            }

            for (int j = 16; j < 80; j++){
                w.add(alg.leftRotate(alg.strXor(alg.strXor(w.get(j-3), w.get(j-8)), alg.strXor(w.get(j-14), w.get(j-16))), 1));
            }

            String a = Integer.toBinaryString(Integer.parseInt(h0, 16));
            String b = Integer.toBinaryString(Integer.parseInt(h1, 16));
            String c = Integer.toBinaryString(Integer.parseInt(h2, 16));
            String d = Integer.toBinaryString(Integer.parseInt(h3, 16));
            String e = Integer.toBinaryString(Integer.parseInt(h4, 16));
            String f = "";
            String k = "";
            String temp = "";

            for (int j = 0; j < 80; j++){
                if (j <= 20){
                    f = alg.strOr(alg.strAnd(b, c), alg.strAnd(alg.strInversion(b), d));
                    k = "5A827999";
                } else if (j <= 40){
                    f = alg.strXor(alg.strXor(b, c), d);
                    k = "6ED9EBA1";
                } else if (j <= 60){
                    f = alg.strOr(alg.strOr(alg.strAnd(b, c), alg.strAnd(b, d)), alg.strAnd(c, d));
                    k = "8F1BBCDC";
                } else {
                    f = alg.strXor(alg.strXor(b, c), d);
                    k = "CA62C1D6";
                }
                //temp =
            }

            break;

        }


        //blocks = sb.toString();
//        System.out.println(blocks.get(0) + blocks.get(1) + '\n' + bin);
//        System.out.println(alg.toBinary(hex));
//        System.out.println(alg.fromBinary(alg.toBinary(hex)));
    }
}
