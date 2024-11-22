# Sum Calculation Using Java (With Threads vs Without Threads)

This repository contains two Java programs that calculate the sum of numbers from 1 to 10,000,000. The programs demonstrate the difference between multi-threaded and single-threaded execution.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Implementation](#implementation)
- [Comparison](#comparison)
- [Usage](#usage)
- [Technologies Used](#technologies-used)

---

## Overview
The project aims to:
1. Illustrate the use of threads in Java for parallel processing.
2. Compare the execution time of a threaded approach vs. a single-threaded approach.

---

## Features
- **With Threads**: Divides the range into equal parts and calculates the sum using multiple threads. Each thread processes a portion of the range in parallel.
- **Without Threads**: Calculates the sum in a single thread sequentially.

---

## Implementation

### **With Threads**
- Splits the range into parts based on the number of threads.
- Each thread computes the sum of its designated range.
- Uses `synchronized` to ensure thread-safe access to the shared `sum` variable.
- Measures the execution time.

```java
public class withThreads {
    private static final int NUM_THREADS = 4;
    private static final long RANGE = 10000000;
    private static long sum = 0;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[NUM_THREADS];
        long rangePerThread = RANGE / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                long threadSum = 0;
                long start = threadIndex * rangePerThread + 1;
                long end = (threadIndex + 1) * rangePerThread;
                for (long j = start; j <= end; j++) {
                    threadSum += j;
                }
                synchronized (withThreads.class) {
                    sum += threadSum;
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Sum with threads: " + sum);
        System.out.println("Execution time with threads: " + executionTime + " ms");
    }
}
