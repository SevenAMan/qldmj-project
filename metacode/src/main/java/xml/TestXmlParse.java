package xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class TestXmlParse {

    private final static String xmlString = """
            
            <user name="user" age="12" id="id" key="key" value="value"/>
            
            """;

    public static void main(String[] args) throws IOException {

        XmlMapper mapper = new XmlMapper();
        var user = mapper.readValue(xmlString, UserBean.class);
        var usbUser = mapper.readValue(xmlString, SubUserBean.class);

        System.out.println(user);
        System.out.println(usbUser);
    }
}
