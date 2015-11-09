import java.io.IOException;
import java.io.Writer;

/**
 * Created by bryannguyen on 3/27/15.
 */
public class EncryptingWriter extends Writer
{
    private Writer writer;
    final int CAESAR_OFFSET = 3;
    final int ALPHABET_SIZE = 26;

    public EncryptingWriter(Writer writer)
    {
        this.writer = writer;
    }

    public void write(char[] cbuf, int off, int len) throws IOException
    {
        char[] newBuffer = new char[len];
        for(int i = off; i < off + len; i++)
        {
            char c = cbuf[i];
            if(Character.isLetter(c))
            {
                c = (char) ((int) c + CAESAR_OFFSET);

            }
            if(!Character.isLetter(c))
            {
                c = (char)((int) c - ALPHABET_SIZE);
            }
            newBuffer[i - off] = c;
        }
        writer.write(newBuffer, 0, len);
    }
    public void close() throws IOException
    {
        writer.close();
    }
    public void flush() throws IOException
    {
        writer.flush();
    }
}
