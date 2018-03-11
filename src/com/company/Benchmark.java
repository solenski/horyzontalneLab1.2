package com.company;

import java.util.concurrent.Callable;

public class Benchmark<T> {



    public BenchmarkResult<T> Test(Callable<T> toCall) throws Exception {
        long before = System.nanoTime();
        T res = toCall.call();
        long after = System.nanoTime();

        return new BenchmarkResult<T>() {{ time = after - before; value = res ; }};
    }
}
