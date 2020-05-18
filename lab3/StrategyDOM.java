package lab3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class StrategyDOM implements Strategy {
    @Override
    public void check(String file0, String file1) throws Exception {
        List<Integer> marks = new ArrayList<Integer>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(new File(file0));
		
		NodeList nList = document.getDocumentElement().getElementsByTagName("subject");
		for (int i = 0; i < nList.getLength(); i++) {
			marks.add(Integer.parseInt(nList.item(i).getAttributes().getNamedItem("mark").getNodeValue()));
		}

		nList = document.getDocumentElement().getElementsByTagName("average");
		double avg = 0;
        double trueAvg = 0;
        if (marks.size() == 0)
			trueAvg = 0;
		
		double res = 0;
		for (int mark : marks)
			res += mark;
		trueAvg = res / marks.size();
		if (nList.getLength() == 1) {
			avg = Double.parseDouble(nList.item(0).getTextContent());

			if (avg != trueAvg) {
				nList.item(0).setTextContent(new String("" + trueAvg));
			}
		} 
		else {
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
}