package ua.org.gostroy.javacore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by s.panov on 10/7/2015.
 */
public class TestJaxb {

    public static void main(String[] args) {

        Object bean = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer wr = new OutputStreamWriter(out);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            XMLOutputFactory xof = XMLOutputFactory.newFactory();
            XMLStreamWriter xsw = xof.createXMLStreamWriter(wr);
//            jaxbMarshaller.setListener(new JaxbMarshalListenerWithComments(xsw));

//            jaxbMarshaller.marshal(bean, out);
            jaxbMarshaller.marshal(bean, wr);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
;