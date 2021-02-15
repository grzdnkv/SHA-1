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
        //blocks = sb.toString();
//        System.out.println(blocks.get(0) + blocks.get(1) + '\n' + bin);
//        System.out.println(alg.toBinary(hex));
//        System.out.println(alg.fromBinary(alg.toBinary(hex)));
    }
}
