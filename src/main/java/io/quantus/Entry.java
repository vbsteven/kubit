package io.quantus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO use Lombok
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {
    private String name;
    private String kubeconfig;

    public Entry() {
    }

    public Entry(String name, String kubeconfig) {
        this.name = name;
        this.kubeconfig = kubeconfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKubeconfig() {
        return kubeconfig;
    }

    public void setKubeconfig(String kubeconfig) {
        this.kubeconfig = kubeconfig;
    }
}
