package com.swizbizy.alfa.util;

import com.swizbizy.alfa.model.Box;
import com.swizbizy.alfa.model.Item;
import com.swizbizy.alfa.repository.BoxRepository;
import com.swizbizy.alfa.repository.ItemRepository;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Component
public class XmlParser {
    private final BoxRepository boxRepository;
    private final ItemRepository itemRepository;

    public XmlParser(BoxRepository boxRepository, ItemRepository itemRepository) {
        this.boxRepository = boxRepository;
        this.itemRepository = itemRepository;
    }

    private void parseNode(Node node, Long containerId) {
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            Long id = Long.valueOf(element.getAttribute("id"));

            String nodeName = node.getNodeName();

            if (nodeName.equals("Box")) {
                Box box = new Box();
                box.setId(id);
                box.setContainedIn(containerId);
                boxRepository.save(box);

                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    parseNode(children.item(i), id);
                }
            } else if (nodeName.equals("Item")) {
                Item item = new Item();
                item.setId(id);
                item.setContainedIn(containerId);
                String color = element.getAttribute("color");
                if (!color.isEmpty()) {
                    item.setColor(color);
                }
                itemRepository.save(item);
            }
        }
    }

    public void parseXml(String fileName) {
        try {
            DocumentBuilder xml = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = xml.parse(new File(fileName));

            NodeList storageNodeList = doc.getElementsByTagName("Storage");
            if (storageNodeList.getLength() != 0) {
                NodeList children = storageNodeList.item(0).getChildNodes();

                for (int i = 0; i < children.getLength(); i++) {
                    parseNode(children.item(i), null);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
