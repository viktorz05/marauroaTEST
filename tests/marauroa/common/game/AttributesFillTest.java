package marauroa.common.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

/** Regression tests for replacing state through Attributes.fill. */
public class AttributesFillTest {

    @Test
    public void copiesSourceClass() {
        RPClass sourceClass = new RPClass();
        Attributes source = new Attributes(sourceClass);
        Attributes destination = new Attributes(new RPClass());

        destination.fill(source);

        assertSame(sourceClass, destination.getRPClass());
    }

    @Test
    public void replacesExistingContent() {
        Attributes source = new Attributes(new RPClass());
        source.put("fresh", "new value");
        Attributes destination = new Attributes(new RPClass());
        destination.put("stale", "old value");

        destination.fill(source);

        assertFalse(destination.has("stale"));
        assertEquals(1, destination.size());
        assertEquals("new value", destination.get("fresh"));
    }

    @Test
    public void clearsStaleAdditionRecords() {
        Attributes source = new Attributes(new RPClass());
        Attributes destination = new Attributes(new RPClass());
        destination.put("stale", "old value");

        destination.fill(source);

        assertTrue(destination.added.isEmpty());
    }

    @Test
    public void copiesSourceAdditionRecords() {
        Attributes source = new Attributes(new RPClass());
        source.put("score", "42");
        Attributes destination = new Attributes(new RPClass());

        destination.fill(source);

        assertEquals(Collections.singletonMap("score", "42"), destination.added);
    }

    @Test
    public void clearsStaleDeletionRecords() {
        Attributes source = new Attributes(new RPClass());
        Attributes destination = new Attributes(new RPClass());
        destination.put("stale", "old value");
        destination.resetAddedAndDeletedAttributes();
        destination.remove("stale");

        destination.fill(source);

        assertTrue(destination.deleted.isEmpty());
    }

    @Test
    public void copiesSourceDeletionRecords() {
        Attributes source = new Attributes(new RPClass());
        source.put("obsolete", "old value");
        source.resetAddedAndDeletedAttributes();
        source.remove("obsolete");
        Attributes destination = new Attributes(new RPClass());

        destination.fill(source);

        assertEquals(Collections.singletonMap("obsolete", "0"), destination.deleted);
    }

    @Test
    public void returnsDestinationObject() {
        Attributes source = new Attributes(new RPClass());
        Attributes destination = new Attributes(new RPClass());

        assertSame(destination, destination.fill(source));
    }
}
