import java.util.*;

/**
 * The Zipcodes program merges a collection of 5-digit ZIP code ranges
 * (each range includes both their lower and upper bounds), to the minimum
 * number of ranges required to represent the same restrictions as the input.
 *
 * @author  Steven Thon
 * @version 1.0
 * @since   2017-07-30
 */

public class Zipcodes {

    /**
     * Reads in ranges, assuming their lower and upper bounds are comma-delimited,
     * they are enclosed by open and close brackets, and each is space-delimited
     * and puts them into a list of ranges. Will catch if bounds are not numbers
     * @param input The input string
     * @return      The list of ranges
     */
    public List<Range> getRanges(String input) {
        String[] sRanges = input.split(" ");
        List<Range> ranges = new ArrayList<Range>();
        for (int rIndex = 0; rIndex < sRanges.length; ++rIndex) {
            String[] range = sRanges[rIndex].substring(1, sRanges[rIndex].length() - 1).split(",");
            try {
                ranges.add(new Range(Integer.parseInt(range[0]), Integer.parseInt(range[1])));
            } catch (NumberFormatException e) {
                System.out.println("Invalid Range Input: " + sRanges[rIndex]);
            }
        }

        return ranges;
    }

    /**
     * Merges a list of ranges into the minimum number of ranges required
     * to represent the same restrictions as the input
     * @param ranges    The list of ranges
     * @return          The list of merged ranges
     */
    public List<Range> merge(List<Range> ranges) {
        if (ranges == null || ranges.isEmpty()) {
            return ranges;
        }

        Collections.sort(ranges);

        ListIterator<Range> it = ranges.listIterator();
        Range cur = it.next();
        while (it.hasNext()) {
            Range next = it.next();
            if (cur.getEnd() < next.getStart()) {
                cur = next;
            }
            else {
                cur.setEnd(Math.max(cur.getEnd(), next.getEnd()));
                it.remove();
            }
        }

        return ranges;
    }

    /**
     * Range class that contains each range's lower and upper bounds
     */
    static class Range implements Comparable<Range> {
        private int start;
        private int end;

        public Range() {
            this.start = 0;
            this.end = 0;
        }

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        /**
         * Checks if a two ranges have both equal lower and upper bounds
         * @param obj   The range to be compared to
         * @return      equal ranges
         */
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Range) {
                Range r = (Range) obj;
                if (start == r.getStart()) {
                    return end == r.getEnd();
                }
            }
            
            return false;
        }

        @Override
        /**
         * Compares the difference between two ranges' bounds, starting with
         * the lower bounds and then the upper bounds
         * @param r The range to be compared to
         * @return  difference between first different bounds (if any)
         */
        public int compareTo(Range r) {
            int rStart = r.getStart();
            if (start == rStart) {
                return end - r.getEnd();
            }

            return start - rStart;
        }
    }
}