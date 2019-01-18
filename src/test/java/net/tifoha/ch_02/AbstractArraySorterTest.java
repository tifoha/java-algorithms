package net.tifoha.ch_02;

import net.tifoha.ch_02._01.SelectionArraySorter;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author Vitalii Sereda
 */
public class AbstractArraySorterTest {
    private static final String[] DATA = "SORTEXAMPLE".split("");

    @Test
    public void sort() {
        Stream
                .of(
                        new SelectionArraySorter<>(DATA),
                        new InsertionArraySorter<>(DATA)
                )
                .peek(AbstractArraySorter::sort)
                .peek(AbstractArraySorter::print)
                .map(AbstractArraySorter::isSorted)
                .forEach(Assert::assertTrue);
    }
}