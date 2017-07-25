package com.example.martin.kanadiasuitetabs;

/**
 * Created by Martin on 22.02.2017.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Bas_Can_Kanadia {
    final int offset_data = 11;
    final int header_node = 1;
    final int header_type = 2;
    final int header_serv = 3;
    final int header_mess = 4;
    final int byte_head_off = 1;
    final int byte_len = 3;
    BA_Kanadia BA_Can_Data = new BA_Kanadia();
    private enum Data_Type {NODATA,ERROR,FLOAT,LONG,ULONG,BLONG,SHORT,USHORT,BSHORT,CHAR,UCHAR,BCHAR,SHORT2,USHORT2}
    static final String NODE_EMP = "value";
    static final String NODE_NAME = "Name";
    static final String NODE_CANID = "CanID";
    static final String NODE_SB = "SB";
    static final String NODE_SE = "SE";
    static final String NODE_Type = "Type";
    static Context contnew;

    public class XML_Data {

        private String Name;
        private String ID;
        private int SB;
        private int SE;
        private String Type;

        public XML_Data(String name, String id, int sb, int se, String type) {
            this.Name = name;
            this.ID = id;
            this.SB = sb;
            this.SE = se;
            this.Type = type;

        }

    }

    public static XML_Data[] xml_d = new XML_Data[100];



    public void init_bas_can(Context myContext) throws NullPointerException{
        get_docxml(myContext);
        contnew = myContext;

    }

    public int get_var_data_i(String Name) throws NullPointerException{
        String sub_can_data = "0";
        String sub_node = "0";
        String sub_type = "0";
        String sub_serv = "0";
        String sub_mess = "0";
        int type=0;
        try {
           int gef_n = 0;
           int gef_id = 0;
           for (int k = 0; k < xml_d.length; k++) {
               if (xml_d[k].Name.equals(Name)) {
                   for (int l = 0; l < BA_Can_Data.can_id.length; l++) {
                       if (xml_d[k].ID.equals(BA_Can_Data.can_id[l])) {

                           int index_startdata = BA_Can_Data.can_data_id[l].indexOf(BA_Can_Data.can_id[l]) + offset_data;

                           sub_node = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_node), get_end_index(index_startdata,header_node)).replace(" ", "");
                           sub_type = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_type), get_end_index(index_startdata,header_type)).replace(" ", "");
                           sub_serv = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_serv), get_end_index(index_startdata,header_serv)).replace(" ", "");
                           sub_mess = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_mess), get_end_index(index_startdata,header_mess)).replace(" ", "");

                           type = Integer.reverseBytes(Integer.parseInt(sub_type));

                           if(type != Data_Type.valueOf(xml_d[k].Type).ordinal())
                           {
                               sub_can_data = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata, xml_d[k].SB), get_end_index(index_startdata, xml_d[k].SE)).replace(" ", "");
                           }
                           else
                           {
                               sub_can_data="0";
                           }
                           gef_id = 1;

                       }
                       if (gef_id == 1) break;
                   }
                   gef_n = 1;
               }
               if (gef_n == 1) break;
           }

       }
       catch (NullPointerException np){

           Toast.makeText(contnew, "Error no Bluetooth Data", Toast.LENGTH_LONG).show();
       }

        return Integer.reverseBytes(Integer.parseInt(sub_can_data, 16));
    }




    public float get_var_data_f(String Name) throws NullPointerException{
        String sub_can_data = "0";
        String sub_node = "0";
        String sub_type = "0";
        String sub_serv = "0";
        String sub_mess = "0";
        int type=0;

        try {
            int gef_n = 0;
            int gef_id = 0;
            for (int k = 0; k < xml_d.length; k++) {
                if (xml_d[k].Name.equals(Name)) {
                    for (int l = 0; l < BA_Can_Data.can_id.length; l++) {
                        if (xml_d[k].ID.equals(BA_Can_Data.can_id[l])) {

                            int index_startdata = BA_Can_Data.can_data_id[l].indexOf(BA_Can_Data.can_id[l]) + offset_data;

                            sub_node = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_node), get_end_index(index_startdata,header_node)).replace(" ", "");
                            sub_type = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_type), get_end_index(index_startdata,header_type)).replace(" ", "");
                            sub_serv = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_serv), get_end_index(index_startdata,header_serv)).replace(" ", "");
                            sub_mess = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata,header_mess), get_end_index(index_startdata,header_mess)).replace(" ", "");

                            type = Integer.parseInt(sub_type,16);

                            if(type == Data_Type.valueOf(xml_d[k].Type).ordinal())
                            {
                                sub_can_data = BA_Can_Data.can_data_id[l].substring(get_start_index(index_startdata, xml_d[k].SB), get_end_index(index_startdata, xml_d[k].SE)).replace(" ", "");
                            }
                            else
                            {
                                sub_can_data="0";
                            }

                            gef_id = 1;

                        }
                        if (gef_id == 1) break;
                    }
                    gef_n = 1;
                }
                if (gef_n == 1) break;
            }

        }
        catch (NullPointerException np){

            Toast.makeText(contnew, "Error no Bluetooth Data", Toast.LENGTH_LONG).show();
        }


        Long t = Long.reverseBytes(Long.parseLong(sub_can_data,16));
        float ret = Float.intBitsToFloat((int) (t>>32));
        return ret;
    }

    private byte[] byteorder_lten(byte[] bytes){
        byte[] byte_ord={0,0,0,0};
        if(bytes.length==8) {
            byte_ord[0] = (byte) (bytes[6]^bytes[7]<<4);
            byte_ord[1] = (byte) (bytes[4]^bytes[5]<<4);
            byte_ord[2] = (byte) (bytes[2]^bytes[3]<<4);
            byte_ord[3] = (byte) (bytes[0]^bytes[1]<<4);
        }

        return byte_ord;
    }

    private int get_start_index(int index_start, int byte_num){

        return (index_start + (byte_num * byte_len) - byte_len);

    }

    private int get_end_index(int index_start, int byte_num){

        return (index_start + (byte_num * byte_len));

    }

    public void get_docxml(Context myContext){
        XMLDOMParser parser = new XMLDOMParser();
        AssetManager manager = myContext.getAssets();
        InputStream stream;
        try {
            stream = manager.open("kanadia_can_dbc.xml");
            Document doc = parser.getDocument(stream);

            // Get Element Value XML Datei
            NodeList nodeList = doc.getElementsByTagName(NODE_EMP);

            /*
             * for each <employee> element get text of name, salary and
             * designation
             */
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element e = (Element) nodeList.item(i);
                xml_d[i] = new XML_Data(parser.getValue(e, NODE_NAME),
                                        parser.getValue(e, NODE_CANID),
                                        Integer.parseInt(parser.getValue(e,NODE_SB),16),
                                        Integer.parseInt(parser.getValue(e,NODE_SE),16),
                                        parser.getValue(e,NODE_Type));

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public class XMLDOMParser {
        //Rückgabe gesamtes XML Dokument
        public Document getDocument(InputStream inputStream) {
            Document document = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder db = factory.newDocumentBuilder();
                InputSource inputSource = new InputSource(inputStream);
                document = db.parse(inputSource);
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
            return document;
        }

        /*
         * I take a XML element and the tag name, look for the tag and get
         * the text content i.e for <employee><name>Kumar</name></employee>
         * XML snippet if the Element points to employee node and tagName
         * is name I will return Kumar. Calls the private method
         * getTextNodeValue(node) which returns the text value, say in our
         * example Kumar. */
        public String getValue(Element item, String name) {
            NodeList nodes = item.getElementsByTagName(name);
            return this.getTextNodeValue(nodes.item(0));
        }

        private final String getTextNodeValue(Node node) {
            Node child;
            if (node != null) {
                if (node.hasChildNodes()) {
                    child = node.getFirstChild();
                    while(child != null) {
                        if (child.getNodeType() == Node.TEXT_NODE) {
                            return child.getNodeValue();
                        }
                        child = child.getNextSibling();
                    }
                }
            }
            return "";
        }
    }

}
