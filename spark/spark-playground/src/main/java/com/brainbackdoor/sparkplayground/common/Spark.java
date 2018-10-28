package com.brainbackdoor.sparkplayground.common;

import org.apache.http.util.TextUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;

public class Spark {
    public static SparkConf getConf(String appName) {
        SparkConf conf = new SparkConf();

        if (!conf.contains("spark.master")) {
            // the master is not set from the command line.
            // make a conf for the test code.
            conf.set("spark.driver.allowMultipleContexts", "true");
            conf.setMaster("local[*]");
        }

        // Some default settings.
        conf.set("spark.sql.parquet.compression.codec", "snappy");
        conf.setIfMissing("spark.executor.heartbeatInterval", "10s");
        // spark.executor.heartbeatInterval should be significantly less than spark.network.timeout.
        // The downloading report job takes more than 5 minutes usually.
        // the timeout should be increased.
        conf.setIfMissing("spark.network.timeout", "800s");

        if (!TextUtils.isEmpty(appName))
            conf.setAppName(appName);

        return conf;
    }

    public static JavaSparkContext getSingletonContext(String appName) {
        // SparkContext.getOrCreate returns a singleton context.
        // be careful, which is only valid for a single JVM instance,
        // but in case of Spark this singleton class will be invoked
        // from each worker node running on separate JVM instance and
        // hence lead to multiple SparkContext object.
        SparkContext sc = SparkContext.getOrCreate(getConf(appName));
        return JavaSparkContext.fromSparkContext(sc);
    }

    public static JavaSparkContext getContext(String appName) {
        return new JavaSparkContext(getConf(appName));
    }

    private Spark() {
        // not to instantiate.
    }
}
