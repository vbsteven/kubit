package io.quantus.commands;

import io.quantus.Entry;
import io.quantus.KubitCore;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Utility class for iterating over all entries in the current config to generate
 * autocompletion candidates from their names
 */
public class EntryNameCompletionCandidates implements Iterable<String> {

    @Override
    public Iterator<String> iterator() {
        return completionCandidates().iterator();
    }

    private Stream<String> completionCandidates() {
        return KubitCore.get().getEntries().stream().map(Entry::getName);
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        completionCandidates().forEach(action);

    }

    @Override
    public Spliterator<String> spliterator() {
        return completionCandidates().spliterator();
    }
}
