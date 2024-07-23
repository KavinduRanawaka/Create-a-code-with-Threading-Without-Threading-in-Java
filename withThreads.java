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


