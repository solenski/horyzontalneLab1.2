package com.company;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Benchmark<T> {



    public BenchmarkResult<T> Test(Callable<T> toCall) throws Exception {

        long before = System.nanoTime();
        T res = toCall.call();
        long after = System.nanoTime();

        return new BenchmarkResult<T>() {{ time = after - before; value = res ; }};
    }

    public BenchmarkActionResult TestMean(Callable toCall, int times) throws Exception {
        ArrayList<Long> tt = new ArrayList<Long>();

        for(int i = 0; i < times ; i++ ) {
            long before = System.nanoTime();
            toCall.call();
            long after = System.nanoTime();
            tt.add(after - before);
        }
        return new BenchmarkActionResult() {{ time = ((long) tt.stream().mapToLong(a -> a).average().orElse(0)); }};
    }

    public BenchmarkActionResult TestAction(Callable toCall) throws Exception {
        long before = System.nanoTime();
        toCall.call();
        long after = System.nanoTime();

        return new BenchmarkActionResult() {{ time = after - before; }};
    }
}
