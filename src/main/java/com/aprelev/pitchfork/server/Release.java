package com.aprelev.pitchfork.server;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexander on 8/18/13
 */
@XmlRootElement
public class Release {
  public String name;
  public String implementationPlan;
  public String eraPlan;

  public List<DepJIRA> depJIRAs;

  public Release() {}
  public Release(String name, String implementationPlan, String eraPlan, DepJIRA... depJIRAs) {
    this.name = name;
    this.implementationPlan = implementationPlan;
    this.eraPlan = eraPlan;
    this.depJIRAs = new LinkedList<DepJIRA>(Arrays.asList(depJIRAs));
  }
}
