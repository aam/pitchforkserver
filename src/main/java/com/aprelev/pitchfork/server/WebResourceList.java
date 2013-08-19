package com.aprelev.pitchfork.server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
public class WebResourceList {
    
    public static class Item {
        public String description;
        public String url;
        
        public Item() {}
        
        public Item(String description, String url) {
            this.description = description;
            this.url = url;
        }
    }
    
    @XmlElement(name = "resources")
    public Collection<Item> items;
}
