import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The ZipcodesTest tests the methods of Zipcodes.java
 *
 * @author  Steven Thon
 * @version 1.0
 * @since   2017-07-30
 */

public class ZipcodesTest {
    
    private Zipcodes z;

    @Before
    public void setUp() {
        z = new Zipcodes();
    }

    @Test
    public void testEquals() {
        assertEquals(new Zipcodes.Range(0, 0), new Zipcodes.Range(0, 0));
        assertEquals(new Zipcodes.Range(0, 1), new Zipcodes.Range(0, 1));
        assertEquals(new Zipcodes.Range(1, 0), new Zipcodes.Range(1, 0));
        assertEquals(new Zipcodes.Range(99999, 99999), new Zipcodes.Range(99999, 99999));

        assertNotEquals(new Zipcodes.Range(0, 0), new Zipcodes.Range(0, 1));
        assertNotEquals(new Zipcodes.Range(0, 0), new Zipcodes.Range(1, 0));
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, new Zipcodes.Range(0, 0).compareTo(new Zipcodes.Range(0, 0)));
        assertEquals(0, new Zipcodes.Range(0, 1).compareTo(new Zipcodes.Range(0, 1)));
        assertEquals(0, new Zipcodes.Range(1, 0).compareTo(new Zipcodes.Range(1, 0)));
        assertEquals(0, new Zipcodes.Range(99999, 99999).compareTo(new Zipcodes.Range(99999, 99999)));
        
        assertEquals(-1, new Zipcodes.Range(0, 0).compareTo(new Zipcodes.Range(0, 1)));
        assertEquals(-1, new Zipcodes.Range(0, 0).compareTo(new Zipcodes.Range(1, 0)));
        assertEquals(1, new Zipcodes.Range(0, 1).compareTo(new Zipcodes.Range(0, 0)));
        assertEquals(1, new Zipcodes.Range(1, 0).compareTo(new Zipcodes.Range(0, 0)));
        assertEquals(-99999, new Zipcodes.Range(0, 0).compareTo(new Zipcodes.Range(0, 99999)));
        assertEquals(99999, new Zipcodes.Range(0, 99999).compareTo(new Zipcodes.Range(0, 0)));
    }

    @Test
    public void testGetRanges() {
        List<Zipcodes.Range> ranges = new ArrayList<Zipcodes.Range>();
        ranges.add(new Zipcodes.Range(94133, 94133));
        ranges.add(new Zipcodes.Range(94200, 94299));
        ranges.add(new Zipcodes.Range(94600, 94699));
        assertEquals(ranges, z.getRanges("[94133,94133] [94200,94299] [94600,94699]"));

        ranges.clear();
        ranges.add(new Zipcodes.Range(0, 0));
        assertEquals(ranges, z.getRanges("[00000,00000]"));
        ranges.add(new Zipcodes.Range(99999, 99999));
        assertEquals(ranges, z.getRanges("[00000,00000] [99999,99999]"));
        ranges.add(new Zipcodes.Range(11111, 11111));
        assertEquals(ranges, z.getRanges("[00000,00000] [99999,99999] [11111,11111]"));
    }

    @Test
    public void testMerge() {
        List<Zipcodes.Range> ranges = null;
        assertNull(z.merge(ranges));
        ranges = new ArrayList<Zipcodes.Range>();
        assertEquals(ranges, z.merge(ranges));

        ranges.add(new Zipcodes.Range(94133, 94133));
        ranges.add(new Zipcodes.Range(94200, 94299));
        ranges.add(new Zipcodes.Range(94600, 94699));

        List<Zipcodes.Range> tRanges = new ArrayList<Zipcodes.Range>();
        tRanges.add(new Zipcodes.Range(94133, 94133));
        tRanges.add(new Zipcodes.Range(94200, 94299));
        tRanges.add(new Zipcodes.Range(94600, 94699));
        assertEquals(tRanges, z.merge(ranges));

        ranges.clear();
        tRanges.clear();

        ranges.add(new Zipcodes.Range(94133, 94133));
        ranges.add(new Zipcodes.Range(94200, 94299));
        ranges.add(new Zipcodes.Range(94226, 94399));
        tRanges.add(new Zipcodes.Range(94133, 94133));
        tRanges.add(new Zipcodes.Range(94200, 94399));
        assertEquals(tRanges, z.merge(ranges));

        ranges.clear();
        tRanges.clear();

        ranges.add(new Zipcodes.Range(0, 0));
        tRanges.add(new Zipcodes.Range(0, 0));
        assertEquals(tRanges, z.merge(ranges));
        ranges.add(new Zipcodes.Range(99999, 99999));
        tRanges.add(new Zipcodes.Range(99999, 99999));
        assertEquals(tRanges, z.merge(ranges));
        ranges.add(new Zipcodes.Range(11111, 11111));
        tRanges.add(new Zipcodes.Range(11111, 11111));
        assertNotEquals(tRanges, z.merge(ranges));
    }
}