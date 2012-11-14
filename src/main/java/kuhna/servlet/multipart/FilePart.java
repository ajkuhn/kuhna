package kuhna.servlet.multipart;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletInputStream;

import java.io.FilterInputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;
import kuhna.lang.ByteUtil;
import kuhna.servlet.http.*;

/**
 * Multipart문서의 File형태의 Part객체
 * 
 * @version 0.2.1, 2007/02/20, modified toString method by A.J.Kuhn<BR><!--
 * @version 0.2, 2005/08/15, every implementation was completed by A.J.Kuhn<BR><!--
 * @version -->0.1, 2004/04/07, initial version by A.J.Kuhn
 *
 * @author <a href="http://www.ajkuhn.com" target="_blank">A.J.Kuhn</a>
 */
public class FilePart extends Part {
 
  private byte[] _file;
  
  private static final int DEFAULT_MAX_FILE_SIZE = 1024 * 1024; // 1 mega bytes

  private int _maxFileSize = DEFAULT_MAX_FILE_SIZE;

  public FilePart() {
    super();
    
    setHeader("content-type", "application/octet-stream");
  }

  public FilePart(String name) {
    this();
    
    setHeader("content-disposition", "form-data; name=\"" + name + "\""); // 디폴트로 form-data 사용
  }

  public FilePart(Collection headers) {
    this();
    setHeaders(headers);
  }
 
  public FilePart(Map headers) {
    this();
    setHeaders(headers);
  }
 
  public FilePart(Part part) {
    this();
    setHeaders(part.getHeaders());
  }
  
  /**
   * 생성자
   * 
   */
  public FilePart(Collection headers, InputStream in, String boundary, String encoding) throws IOException {
    this(headers, in, boundary, encoding, DEFAULT_MAX_FILE_SIZE);
  }
  /**
   * 생성자
   * 
   */
  public FilePart(Collection headers, InputStream in, String boundary, String encoding, int maxFileSize) throws IOException {
    super(headers);

    _maxFileSize = maxFileSize;
    
    byte[] buffer = new byte[_maxFileSize];
    PartInputStream partInput = new PartInputStream((ServletInputStream)in, "--" + boundary);
    long size=0;
    int read;
    byte[] buf = new byte[8 * 1024];
    while((read = partInput.read(buf)) != -1) {
      if(size + read > _maxFileSize)
        throw new IOException("file size is over max file size");
      System.arraycopy(buf, 0, buffer, (int)size, read);
      size += read;
    }
  
    _file = new byte[(int)size];
    System.arraycopy(buffer, 0, _file, 0, (int)size);
  }
  
  public void setMaxFileSize(int size) {
    _maxFileSize = size;
  }
  
  public int getMaxFileSize() {
    return _maxFileSize;
  }

  public byte[] getFileBytes() {
    return _file;
  }
  
  public void setFile(File file) throws IOException {
    long fileSize = file.length();
    if(fileSize > getMaxFileSize())
      throw new IOException("file size is over max file size(" + fileSize + ")");
    
    _file = new byte[(int)fileSize];
    
    FileInputStream input = new FileInputStream(file);
    int bufferSize = 0;
    int offset = 0;
    while((bufferSize = input.available()) > 0) {
      int read = input.read(_file, offset, bufferSize);
      if(read <= 0)
        break;
      
      offset = offset + read;
    }
    
    setFileName(file.getAbsolutePath());
  }
  
  
  public void setFile(byte[] file, String fileName) throws IOException {
    if(file.length > getMaxFileSize())
      throw new IOException("file size is over max file size(" + file.length + ")");
    
    setFileName(fileName);
    _file = file;
  }
  
  public void setFileName(String name) throws IOException {
    StringBuffer buffer = new StringBuffer();
    String temp = getHeader("content-disposition");
    
    if(temp == null)
      throw new IOException("content-dispositon header must be set");
    
    int pos1 = temp.indexOf("filename=");
    if(pos1 == -1) {
      buffer.append(temp);
      buffer.append("; filename=\"");
      buffer.append(name);
      buffer.append("\"");
    } else {
      int pos2 = temp.indexOf(";", pos1);
      if(pos2 == -1) {
        buffer.append(temp.substring(0, pos1 + 9));      
        buffer.append("\"" + name + "\"");
      } else {
        buffer.append(temp.substring(0, pos1 + 9));      
        buffer.append("\"" + name + "\"");
        buffer.append(temp.substring(pos2));
      }
    }

    setHeader("content-disposition", buffer.toString());
  }
    
  public boolean isFilePart() {
    return true;
  }

  public void writeTo(OutputStream out, String encoding) throws IOException {
    writeHeaderTo(out, encoding);
    out.write(_file, 0, _file.length);
  }

  public void writeTo(Writer out) throws IOException {
    throw new IOException("FilePart cannot write to Writer");
  }

  /**
   * 헤더에 기록된 파일이름(Path 없음)
   *
   * @return 파일이름
   */
  public String getFileName() {
    return HttpUtil.getFilename(getHeaderString());
  }

  /**
   * 헤더에 기록된 파일이름(Path 포함)
   *
   * @return 파일이름
   */
  public String getPathFileName() {
    return HttpUtil.getPathFilename(getHeaderString());
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(getHeaderString());
    buffer.append("\n");
//    buffer.append("ByteUtil.toHex(_file));
    buffer.append("[File " + _file.length + "bytes]");

    return buffer.toString(); 
  }
  


  /**
   * A <code>PartInputStream</code> filters a <code>ServletInputStream</code>, 
   * providing access to a single MIME part contained with in which ends with 
   * the boundary specified.  It uses buffering to provide maximum performance.
   * <p>
   * Note the <code>readLine</code> method of <code>ServletInputStream</code>
   * has the annoying habit of adding a \r\n to the end of the last line.  Since
   * we want a byte-for-byte transfer, we have to cut those chars. This means 
   * that we must always maintain at least 2 characters in our buffer to allow 
   * us to trim when necessary.
   * 
   * @author Geoff Soutter
   * @author Jason Hunter
   * @version 1.4, 2002/11/01, fix for "unexpected end of part" caused by
   *                           boundary newlines split across buffers
   * @version 1.3, 2001/05/21, fix to handle boundaries crossing 64K mark
   * @version 1.2, 2001/02/07, added read(byte[]) implementation for safety
   * @version 1.1, 2000/11/26, fixed available() to never return negative
   * @version 1.0, 2000/10/27, initial revision
   */
  private class PartInputStream extends FilterInputStream {
    /** boundary which "ends" the stream */
    private String boundary;
    
    /** our buffer */
    private byte [] buf = new byte[64*1024];  // 64k
    
    /** number of bytes we've read into the buffer */
    private int count; 
    
    /** current position in the buffer */
    private int pos;
    
    /** flag that indicates if we have encountered the boundary */
    private boolean eof;
      
    /**
     * Creates a <code>PartInputStream</code> which stops at the specified
     * boundary from a <code>ServletInputStream<code>.
     * 
     * @param in  a servlet input stream.
     * @param boundary the MIME boundary to stop at.
     */
    PartInputStream(ServletInputStream in, 
                    String boundary) throws IOException {
      super(in);
      this.boundary = boundary;
    }

    /**
     * Fill up our buffer from the underlying input stream, and check for the 
     * boundary that signifies end-of-file. Users of this method must ensure 
     * that they leave exactly 2 characters in the buffer before calling this 
     * method (except the first time), so that we may only use these characters
     * if a boundary is not found in the first line read.
     * 
     * @exception  IOException  if an I/O error occurs.
     */
    private void fill() throws IOException
    {
      if (eof)
        return;
      
      // as long as we are not just starting up
      if (count > 0)
      {
        // if the caller left the requisite amount spare in the buffer
        if (count - pos == 2) {
          // copy it back to the start of the buffer
          System.arraycopy(buf, pos, buf, 0, count - pos);
          count -= pos;
          pos = 0;
        } else {
          // should never happen, but just in case
          throw new IllegalStateException("fill() detected illegal buffer state");
        }
      }
      
      // Try and fill the entire buffer, starting at count, line by line
      // but never read so close to the end that we might split a boundary
      // Thanks to Tony Chu, tony.chu@brio.com, for the -2 suggestion.
      int read = 0;
      int boundaryLength = boundary.length();
      int maxRead = buf.length - boundaryLength - 2;  // -2 is for /r/n
      while (count < maxRead) {
        // read a line
        read = ((ServletInputStream)in).readLine(buf, count, buf.length - count);
        // check for eof and boundary
        if (read == -1) {
          throw new IOException("unexpected end of part");
        } else {
          if (read >= boundaryLength) {
            eof = true;
            for (int i=0; i < boundaryLength; i++) {
              if (boundary.charAt(i) != buf[count + i]) {
                // Not the boundary!
                eof = false;
                break;
              }
            }
            if (eof) {
              break;
            }
          }
        }
        // success
        count += read;
      }
    }
    
    /**
     * See the general contract of the <code>read</code>
     * method of <code>InputStream</code>.
     * <p>
     * Returns <code>-1</code> (end of file) when the MIME 
     * boundary of this part is encountered.
     *
     * @return     the next byte of data, or <code>-1</code> if the end of the
     *             stream is reached.
     * @exception  IOException  if an I/O error occurs.
     */
    public int read() throws IOException {
      if (count - pos <= 2) {
        fill();
        if (count - pos <= 2) {
          return -1;
        }
      }
      return buf[pos++] & 0xff;
    }

    /**
     * See the general contract of the <code>read</code>
     * method of <code>InputStream</code>.
     * <p>
     * Returns <code>-1</code> (end of file) when the MIME
     * boundary of this part is encountered.
     *
     * @param      b     the buffer into which the data is read.
     * @return     the total number of bytes read into the buffer, or
     *             <code>-1</code> if there is no more data because the end
     *             of the stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     */
    public int read(byte b[]) throws IOException {
      return read(b, 0, b.length);
    }

    /**
     * See the general contract of the <code>read</code>
     * method of <code>InputStream</code>.
     * <p>
     * Returns <code>-1</code> (end of file) when the MIME 
     * boundary of this part is encountered.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset of the data.
     * @param      len   the maximum number of bytes read.
     * @return     the total number of bytes read into the buffer, or
     *             <code>-1</code> if there is no more data because the end
     *             of the stream has been reached.
     * @exception  IOException  if an I/O error occurs.
     */
    public int read(byte b[], int off, int len) throws IOException
    {
      int total = 0;
      if (len == 0) {
        return 0;
      }

      int avail = count - pos - 2;
      if (avail <= 0) {
        fill();
        avail = count - pos - 2;
        if(avail <= 0) {
          return -1;
        }
      }
      int copy = Math.min(len, avail);
      System.arraycopy(buf, pos, b, off, copy);
      pos += copy;
      total += copy;
        
      while (total < len) {
        fill();
        avail = count - pos - 2;
        if(avail <= 0) {
          return total;
        }
        copy = Math.min(len - total, avail);
        System.arraycopy(buf, pos, b, off + total, copy);
        pos += copy;
        total += copy;
      }
      return total;
    }

    /**
     * Returns the number of bytes that can be read from this input stream
     * without blocking.  This is a standard <code>InputStream</code> idiom
     * to deal with buffering gracefully, and is not same as the length of the
     * part arriving in this stream.
     *
     * @return     the number of bytes that can be read from the input stream
     *             without blocking.
     * @exception  IOException  if an I/O error occurs.
     */
    public int available() throws IOException {
      int avail = (count - pos - 2) + in.available();
      // Never return a negative value
      return (avail < 0 ? 0 : avail);
    }

    /**
     * Closes this input stream and releases any system resources 
     * associated with the stream. 
     * <p>
     * This method will read any unread data in the MIME part so that the next 
     * part starts an an expected place in the parent <code>InputStream</code>.
     * Note that if the client code forgets to call this method on error,
     * <code>MultipartParser</code> will call it automatically if you call 
     * <code>readNextPart()</code>.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    public void close() throws IOException {
      if (!eof) {
        while (read(buf, 0, buf.length) != -1)
          ; // do nothing
      }
    }
  }
}

