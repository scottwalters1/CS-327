package PA1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.Buffer;
import java.util.Scanner;

public class affine {

  public static void encrypt(String plainFile, String outFile,
                             int a, int b) throws FileNotFoundException, IOException {

    // Base logic is good b/c I encrypted all characters into
    // caesar.txt, then compared caesar.txt with provided all_characters_caesar.txt.
    // no difference.  However, may still be edge cases to handle with ab selection

    // try always using char, no bytes

    File plain = new File(plainFile);
    File out = new File(outFile);

    if (isValid(a, b)) {

      out.createNewFile();
      FileWriter writer = new FileWriter(out);

      byte[] bytes = new byte[(int) plain.length()];

      try (FileInputStream inputStream = new FileInputStream(plain)) {
        inputStream.read(bytes);
      }

      for (int i = 0; i < bytes.length; i++) {
        int cout = ((a * bytes[i]) + b) % 128;
        char charout = (char) cout;
        writer.write(charout);
      }

      writer.close();

    } else {
      System.out.println("The key pair " + a + ", "
                                 + b + " is invalid, please select another key.");
    }
  }

  public static void decrypt(String cipherFile, String outFile,
                             int a, int b) throws FileNotFoundException, IOException {

    File cipher = new File(cipherFile);
    File out = new File(outFile);

    if (isValid(a, b)) {

      out.createNewFile();
      FileWriter writer = new FileWriter(out);
      byte[] bytes = new byte[(int) cipher.length()];

      try (FileInputStream inputStream = new FileInputStream(cipher)) {
           inputStream.read(bytes);
      }

      for (int i = 0; i < bytes.length; i++) {
        int cout = ((mod128Inverse(a) * (bytes[i] - b) % 128) + 128) % 128;
        char charout = (char)cout;
        writer.write(charout);
      }
      writer.close();

    } else {
      System.out.println("The key pair " + a + ", "
                                 + b + " is invalid, please select another key.");
    }
  }

  public static void decipher(String cipherFile, String outFile,
                              String dicFile) {

  }

  public static boolean isValid(int a, int b) {

    if (a >= 128 || b >= 128) {
      return false;
    }

    if (!BigInteger.valueOf(a).gcd(BigInteger.valueOf(128)).equals(BigInteger.ONE)) {
      return false;
    }
    return true;
  }

  private static int mod128Inverse(int x) {

    x = x % 128;
    for (int i = 1; i < 128; i++) {
      if ((x * i) % 128 == 1) {
        return i;
      }
    }
    return 1;
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {

    if (args[0].equals("encrypt")) {
      encrypt(args[1], args[2], Integer.parseInt(args[3]),
              Integer.parseInt(args[4]));

    }

    if (args[0].equals("decrypt")) {
      decrypt(args[1], args[2], Integer.parseInt(args[3]),
              Integer.parseInt(args[4]));
    }

    // if (args[0].equals("decipher")) {
    //   decipher(args[1], args[2], args[3]);
    // }

//     Test printing
    Scanner in = new Scanner(new File(args[0]));
    in.useDelimiter("");
    while (in.hasNext()) {
      char c = in.next().charAt(0);
      System.out.println(c);
    }

    System.out.println("BREAK HERE");

//      Test printing
    Scanner sn = new Scanner(new File(args[1]));
    sn.useDelimiter("");
    while (sn.hasNext()) {
      char c = sn.next().charAt(0);
      System.out.println(c);
    }



  }
}
