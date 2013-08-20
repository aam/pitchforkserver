package com.aprelev.pitchfork.server;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexander on 8/19/13
 */
@XmlRootElement
public class DepJIRA {
  public String name;
  public String releaseNotes;
  public List<DevJIRA> devJIRAs;

  public DepJIRA() {}
  public DepJIRA(String name, String releaseNotes, DevJIRA... devJIRAs) {
    this.name = name;
    this.releaseNotes = releaseNotes;
    this.devJIRAs = new LinkedList<DevJIRA>(Arrays.asList(devJIRAs));
  }
}
