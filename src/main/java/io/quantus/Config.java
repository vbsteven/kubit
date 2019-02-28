package io.quantus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quantus.exceptions.EntryAlreadyExistsException;
import io.quantus.exceptions.EntryNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    private List<Entry> entries = new ArrayList<>();

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(Entry entry, boolean overwrite) throws EntryAlreadyExistsException {
        // first lookup to see if the entry exists
        Optional<Entry> preexistingEntry = findEntry(entry.getName());
        if (preexistingEntry.isPresent()) {
            if (overwrite) {
                preexistingEntry.get().setKubeconfig(entry.getKubeconfig());
            } else {
                throw new EntryAlreadyExistsException();
            }
        } else {
            this.entries = new ArrayList<>(entries);
            this.entries.add(entry);
        }
    }

    public void removeEntry(String name) throws EntryNotFoundException {
        Entry entry = findEntry(name).orElseThrow(EntryNotFoundException::new);
        this.entries.remove(entry);
    }

    public Optional<Entry> findEntry(String name) {
        return entries.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }
}
