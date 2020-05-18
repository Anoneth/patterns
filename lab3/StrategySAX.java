package lab3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StrategySAX implements Strategy {
    @Override
    public void check(String file0, String file1) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        saxParser.parse(new File(file0), xmlHandler);

        double avg = xmlHandler.getAvg();
        List<Integer> marks = xmlHandler.getMarks();
        double trueAvg = 0;
        if (marks.size() == 0)
            trueAvg = 0;

        double res = 0;
        for (int mark : marks)
            res += mark;
        trueAvg = res / marks.size();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(new File(file0));
        NodeList nList = document.getDocumentElement().getElementsByTagName("average");
        if (nList.getLength() == 1) {
            avg = Double.parseDouble(nList.item(0).getTextContent());

            if (avg != trueAvg) {
                nList.item(0).setTextContent(new String("" + trueAvg));
            }
        } else {
            Element average = document.createElement("average");
            average.setTextContent("" + trueAvg);
            document.getDocumentElement().appendChild(average);
        }

        TransformerFactory trFactory = TransformerFactory.newInstance();
        Transformer tr = trFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult sr = new StreamResult(new File(file1));

        tr.transform(domSource, sr);

    }

    private class XMLHandler extends DefaultHandler {
        private List<Integer> marks = new ArrayList<>();
        private double avg;
        private boolean isAvg;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            if (qName.equals("subject")) {
                marks.add(Integer.valueOf(attributes.getValue("mark")));
            }
            if (qName.equals("average")) {
                isAvg = true;
            }
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("average")) {
                isAvg = false;
            }
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (isAvg) {
                String s = String.copyValueOf(ch, start, length);
                avg = Double.parseDouble(s);
            }
            super.characters(ch, start, length);
        }

        public List<Integer> getMarks() {
            return marks;
        }

        public double getAvg() {
            return avg;
        }
    }
}