import java.io.*;

/**
 * Created by bryannguyen on 3/27/15.
 */
public class DecoratorTester
{
    public static void main(String[] args) throws IOException
    {
        EncryptingWriter Encryptor = new EncryptingWriter(new FileWriter("test.out"));
        Encryptor.write("abcdefghijklmnopqrstuvwxyz1abcdefghijklmnopqrstuvwxyz", 20, 27);
        Encryptor.write("ABCDEFGHIJKLMNOPQRSTUVWXYZ1ABCDEFGHIJKLMNOPQRSTUVWXYZ", 20, 27);
        PrintWriter newWriter = new PrintWriter(Encryptor, true);
        newWriter.println("abcdefghijkl2mnopqrstuvwxyz");
        newWriter.println("ABCDEFGHIJKL2MNOPQRSTUVWXYZ");
        char inChars[] = "$$$$$$$$$$$$$$$$$$$$$$$$$$ $$$$$$$$$$$$$$$$$$$$$$$$".toCharArray();
        DecryptingReader Decryptor = new DecryptingReader(new FileReader("test.out"));
        Decryptor.read(inChars, 10, 27);
        System.out.println(inChars);
        Decryptor.read(inChars, 10, 27);
        System.out.println(inChars);
        BufferedReader newBuffer = new BufferedReader(Decryptor);
        String x = newBuffer.readLine();
        System.out.println("Decrypted Message:");
        System.out.println(x);
        x = newBuffer.readLine();
        System.out.println(x);
    }
}
