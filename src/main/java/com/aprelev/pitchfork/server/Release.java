package com.aprelev.pitchfork.server;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexander on 8/18/13
 */
@XmlRootElement
public class Release {
  public String name;
  public String author;

  public Release() {}
  public Release(String name, String author) {
    this.name = name;
    this.author = author;
  }
}
