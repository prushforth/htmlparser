package nu.validator.htmlparser.sax;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xml.sax.Attributes;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class MapmlSerializer extends HtmlSerializer implements ContentHandler, LexicalHandler {
    private static boolean doctypeWritten = false;


    public MapmlSerializer(OutputStream out) {
        super(out);
    }

    public MapmlSerializer(Writer out) {
        super(out);
    }


    public void startDocument() throws SAXException {
    }
    
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
        try {
          if (name == "mapml") {
              writer.write("<!DOCTYPE mapml>\n");
          } else {
              writer.write("<!DOCTYPE html>\n");
          }
          doctypeWritten = true;
        } catch (IOException e) {
            throw new SAXException(e);
        }

    }
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        if (!doctypeWritten) {
            try {
              if (localName == "mapml") {
                  writer.write("<!DOCTYPE mapml>\n");
              } else {
                  writer.write("<!DOCTYPE html>\n");
              }
              doctypeWritten = true;
            } catch (IOException e) {
                throw new SAXException(e);
            }
        }
        super.startElement("http://www.w3.org/1999/xhtml", localName, qName, atts);
    }

}
