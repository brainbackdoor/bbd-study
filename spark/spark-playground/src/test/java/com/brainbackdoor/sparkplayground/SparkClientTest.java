package com.brainbackdoor.sparkplayground;

import com.brainbackdoor.sparkplayground.common.Spark;
import com.brainbackdoor.sparkplayground.util.PropertyLoader;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import scala.Tuple2;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SparkClientTest {
    static JavaSparkContext ctx;
    SparkConf conf;
    JavaRDD<String> file;
    String path;
    JavaPairRDD<String, String> data;

    @Before
    public void setUp() {
        path = PropertyLoader.getInstance().getPropertyValue("PATH");
        ctx = Spark.getSingletonContext(getClass().getSimpleName());
        file = ctx.textFile(path);
        data = file.mapToPair(row -> new Tuple2<>(row.split("#")[2], row.replace("#", ",")));
    }

    @Test
    public void testArgument() {
        assertThat(path, is("src/main/resources/data/data.txt"));
    }

    @Test
    public void loadFile() {
        assertThat(data.collect().size(), greaterThan(0));
        assertThat(data.collect().stream().findFirst().get()._1, is("51"));
    }

    @Test
    public void distinctTest() {
        long count = data.keys().distinct().count();
        assertThat(count, is(100L)); // After eliminating redundancy, the number of purchasers is 100
    }

    @Test
    public void countByKeyTest() {
        Map<String, Long> result = data.countByKey();
        assertThat(result.get("69"), is(7L));
        assertThat(result.values().stream().mapToInt(i -> Math.toIntExact(i)).sum(), is(1000));
    }

    @Test
    @DisplayName("Count the number of keys and find out what key number is the most.")
    public void sortTest() {
        // JavaPairRDD is the return of countByKey only the map?
        TreeMap<String, Long> result = sortMapByValue(new HashMap<String, Long>(data.countByKey()));
        assertThat(result.firstKey(), is("53"));
    }

    @Test
    public void lookUpTest() {
        assertThat(data.lookup("69").size(), is(7));
    }

    @Test
    @DisplayName("Extraction of the largest sum result")
    public void foldByKeyTest() {
        JavaPairRDD<String, Double> amounts = data.mapValues(v -> Double.valueOf(v.split(",")[5]));
        JavaPairRDD<String, Double> totals = amounts.foldByKey((double) 0, (p1, p2) -> p1 + p2);
        JavaPairRDD<String, Double> result = totals.mapToPair(x -> x.swap()).sortByKey(false).mapToPair(x -> x.swap());
        assertThat(result.first()._2, is(Double.valueOf("100049.0")));
    }

    public static TreeMap<String, Long> sortMapByValue(HashMap<String, Long> map) {
        Comparator<String> comparator = new ValueComparator(map);
        TreeMap<String, Long> result = new TreeMap<String, Long>(comparator);
        result.putAll(map);
        return result;
    }

    static class ValueComparator implements Comparator<String> {
        HashMap<String, Long> map = new HashMap<String, Long>();

        public ValueComparator(HashMap<String, Long> map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(String s1, String s2) {
            if (map.get(s1) >= map.get(s2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}