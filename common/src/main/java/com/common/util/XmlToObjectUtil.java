package com.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/16  15:34
 */

public class XmlToObjectUtil {

    //将xml格式转换成object
    public static <T> T getObjectFromXml(String xmlString, Class<T> t) throws JAXBException {
        // 创建 JAXBContext 对象，指定对象所在的包名
        JAXBContext jaxbContext = JAXBContext.newInstance(t);

        // 创建 Unmarshaller 对象
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // 调用 unmarshal() 方法将 XML 字符串解析为 Java 对象
        return t.cast(unmarshaller.unmarshal(new StringReader(xmlString)));
    }

}
