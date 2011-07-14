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


public class MainClass {
	public static void main(String[] args){

		User user1=null;
		NodeList nList = null;

		Resty r = new Resty();
		XMLResource usr1 = null;
		/* this try catch block prints out all the items in our server as xml
		 * we can then parse the xml generated and extract item attributes as required.
		 * As of now, this is just a simple implementation
		 */
		try {
			usr1 = r.xml("http://vogueable.heroku.com/users.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			String st = ""+usr1;
			InputStream is = new ByteArrayInputStream(st.getBytes());

			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("user");

		} catch (IOException ex) {
			ex.printStackTrace();
			//Log.e("gaspar", "exception on r.xml");
		} catch (ParserConfigurationException ex1) {
			ex1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}


		//Parses through all the taken users from the database
		//looks at e-mails solely
		System.out.println("Name  \t\t\t\tYour ID");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			ArrayList<User> users=new ArrayList<User>();
			//int serverUserID;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//String user="";
				users.add(new User(getTagValue("user-name",eElement),getTagValue("email", eElement),Integer.parseInt(getTagValue("id",eElement))));
				
				user1=users.get(0);
				System.out.println("Mr/Mrs "+user1.getName()+" \t\t"+user1.getID());
			}
		}
	}
	public static String getTagValue(String sTag, Element eElement) {
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
