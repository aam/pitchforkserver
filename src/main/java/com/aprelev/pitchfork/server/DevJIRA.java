package com.aprelev.pitchfork.server;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexander on 8/19/13
 */

@XmlRootElement
public class DevJIRA {
  public String name;
  public List<Branch> branches;

  public DevJIRA() {}
  public DevJIRA(String name, Branch... branches) {
    this.name = name;
    this.branches = new LinkedList<Branch>(Arrays.asList(branches));
  }
}
