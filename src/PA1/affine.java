package PA1;

import java.io.File;

public class affine {

  public static void encrypt(String plainFile, String outFile,
                             int a, int b) {

    File plain = new File(plainFile);
    File out = new File(outFile);

    if (isValid(a, b)) {

    } else {
      System.out.println("The key pair " + a + ", "
              + b + " is invalid, please select another key.");
    }

  }

  public static void decrypt(String cipherFile, String outFile,
                      int a, int b){

    File cipher = new File(cipherFile);
    File out = new File(outFile);

    if (isValid(a, b)) {

    } else {
      System.out.println("The key pair " + a + ", "
      + b + " is invalid, please select another key.");
    }

  }

  public static void decipher(String cipherFile, String outFile,
                       String dicFile) {

  }

  public static boolean isValid(int a, int b) {
    return false;
  }

  public static void main(String[] args) {

    if (args[0].equals("encrypt")) {
      encrypt(args[1], args[2], Integer.parseInt(args[3]),
              Integer.parseInt(args[4]));
    }

    if (args[0].equals("decrypt")) {
      decrypt(args[1], args[2], Integer.parseInt(args[3]),
              Integer.parseInt(args[4]));
    }

    if (args[0].equals("decipher")) {
      decipher(args[1], args[2], args[3]);
    }

  }
}
