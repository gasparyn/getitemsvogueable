import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import us.monoid.web.Resty;
import us.monoid.web.XMLResource;
/**
 * This android app fetches data from the heroku server of our Vogueable app
 * and displays the output as xml to the screen
 *
 *
 * @author gaspar obimba
 *
 */
public class SimpleXmlReader  {
	
	public SimpleXmlReader(){
		
	}

	/**
	 * Used to get a tag value of the xml for Users
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	public String getTagValue(String sTag, Element eElement) {
		NodeList list = eElement.getElementsByTagName(sTag);
		Node el = list.item(0);
		//Log.d(TAG,"error on el");

		if (el != null) {
			NodeList nlList = el.getChildNodes();//get all children of the item node
			Node nValue = (Node) nlList.item(0);
			if (nValue != null){
				return nValue.getNodeValue();
			}
		}
		return null; 
	}
}
