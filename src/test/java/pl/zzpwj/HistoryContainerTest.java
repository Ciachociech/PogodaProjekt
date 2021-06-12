package pl.zzpwj;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class HistoryContainerTest {

    @Test
    public void HistoryContainerGenerating() {
        HistoryContainer historyContainer = new HistoryContainer();
        Assert.assertEquals(0, historyContainer.getHistory().size());
    }

    @Test
    public void HistoryContainerAdding() {
        HistoryContainer historyContainer = new HistoryContainer();
    }
}
