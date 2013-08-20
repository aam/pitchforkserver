package com.aprelev.pitchfork.server;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexander on 8/19/13
 */
@XmlRootElement
public class Branch {
  public String name;
  public String author;

  public Branch() {}
  public Branch(String name, String author) { this.name = name; this.author = author; }
}
