import java.io.IOException;
import java.io.Reader;

/**
 * Created by bryannguyen on 3/27/15.
 */
public class DecryptingReader extends Reader
{
    private Reader reader;
    final int CAESAR_OFFSET = 3;
    final int ALPHABET_SIZE = 26;
    public DecryptingReader(Reader reader)
    {
        this.reader = reader;
    }
    public int read(char[] buffer, int off, int len) throws IOException
    {
        int result = reader.read(buffer, off, len);
        for(int i = off; i < off + len; i++)
        {
            char c = buffer[i];
            if(Character.isLetter(c))
            {
                c = (char) ((int) c - CAESAR_OFFSET);
            }
            if(!Character.isLetter(c))
            {
                c = (char) ((int) c + ALPHABET_SIZE);
            }
            buffer[i] = c;
        }
        return result;
    }
    public void close() throws IOException
    {
        reader.close();
    }
}
