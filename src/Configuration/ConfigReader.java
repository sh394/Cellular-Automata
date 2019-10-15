package Configuration;

import javafx.scene.control.TextInputDialog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigReader {
    //Readable error message that can be displayed by the GUI
    public static final String ERROR_MESSAGE ="XML file does not represent%";
    //name of root attribute that notes the type of the file expecting to parse
    private final String TYPE_ATTRIBUTE;
    private final DocumentBuilder DOCUMENT_BUILDER;
    protected static final List<String> SIMULATION_TYPE = List.of(
            "Fire",
            "GameOfLife",
            "Percolation",
            "Segregation",
            "WaTor"
    );

    /**
     * Create parser for XML files of given type.
     */
    public ConfigReader(String type) {
        TYPE_ATTRIBUTE = type;
        DOCUMENT_BUILDER = getDocumentBuilder();
    }

    /**
     * Get data contained in this XML file as a Map
     */

    public Configuration getConfiguration (File xmlFile) {
        Element root  = getRootElement(xmlFile);
        if(! isValidFile(root, Configuration.DATA_TYPE)) {
            throw new ConfigException(ERROR_MESSAGE, Configuration.DATA_TYPE);
        }
        Map<String, String> results = new HashMap<>();
        for(String field: Configuration.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
        return new Configuration(results);
    }

    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        } catch(SAXException | IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new ConfigException(e);
        }
    }

    /**
     * Gets data from user input and writes a xml file
     */
    public void writeXMLFile(String name, String title, String author, double threshold, int row, int col, String initial) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try{

            TextInputDialog td = new TextInputDialog("enter any text");
            td.setHeaderText("enter your name");
            td.showAndWait();
            String fileName = td.getEditor().getText();

            documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("data");
            document.appendChild(root);
            root.setAttribute("rule", "rule");

            root.appendChild(getRule(document, name, title, author, threshold, row, col ,initial));

            // output DOM XML to console
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult console = new StreamResult(new File("src/XMLFiles/"+fileName + ".xml"));
            transformer.transform(source, console);

        } catch (ParserConfigurationException e) {
            throw new ConfigException(ERROR_MESSAGE, Configuration.DATA_TYPE);
        } catch (TransformerException ex) {
            throw new ConfigException(ex);
        }
    }

    /**
     * Append elements to its root node
     */
    private Node getRule(Document doc, String name, String title, String author,
                         double threshold, int row, int col, String initial ) {
        Element simulation = doc.createElement("simulation");
        simulation.appendChild(getRuleElements(doc, "name", name));
        simulation.appendChild(getRuleElements(doc, "title", title));
        simulation.appendChild(getRuleElements(doc, "author", author));
        simulation.appendChild(getRuleElements(doc, "threshold", Double.toString(threshold)));
        simulation.appendChild(getRuleElements(doc, "row", Integer.toString(row)));
        simulation.appendChild(getRuleElements(doc, "col", Integer.toString(col)));
        simulation.appendChild(getRuleElements(doc, "initial", initial));

        return simulation;
    }

    private Node getRuleElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root, String type) {
        return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
    }

    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }

    // Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            return "";
        }
    }

    // boilerplate code needed to make a documentBuilder
    private DocumentBuilder getDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            throw new ConfigException(e);
        }
    }
}
