package org.example.testing.converter;

import org.example.testing.repository.entity.DeviceEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MultipartFileToDeviceEntitiesConverter implements Converter<MultipartFile, List<DeviceEntity>> {

    @Override
    public List<DeviceEntity> convert(MultipartFile source) {
        var result = new ArrayList<DeviceEntity>();

        try {
            var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            var document = documentBuilder.parse(source.getInputStream());

            var deviceInfos = document.getElementsByTagName("deviceInfo");
            var screenInfos = document.getElementsByTagName("screenInfo");
            var newspaperNames = document.getElementsByTagName("newspaperName");

            for (var i = 0; i < deviceInfos.getLength(); i++) {
                var deviceInfo = deviceInfos.item(i);
                var screenInfo = screenInfos.item(i);
                var newspaperInfo = newspaperNames.item(i);

                var deviceId = getAttributeValue(deviceInfo, "id");
                var deviceDpi = getAttributeValue(screenInfo, "dpi");
                var deviceWidth = getAttributeValue(screenInfo, "width");
                var deviceHeight = getAttributeValue(screenInfo, "height");
                var newspaperName = newspaperInfo.getTextContent();

                var device = new DeviceEntity();

                device.setId(deviceId);
                device.setDpi(Integer.decode(deviceDpi));
                device.setWidth(Integer.decode(deviceWidth));
                device.setHeight(Integer.decode(deviceHeight));
                device.setNewspaperName(newspaperName);

                result.add(device);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            return List.of();
        }

        return result;
    }

    private String getAttributeValue(Node node, String attributeName) {
        if (node == null || attributeName == null || attributeName.isBlank()) {
            return null;
        }

        return node.getAttributes().getNamedItem(attributeName).getNodeValue();
    }
}
