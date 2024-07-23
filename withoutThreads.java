public class withoutThreads {

        public static void main(String[] args) {
            long startTime = System.currentTimeMillis();

            long sum = 0;
            for (int i = 1; i <= 10000000; i++) {
                sum += i;
            }

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("Sum without threads: " + sum);
            System.out.println("Execution time without threads: " + executionTime + " ms");
        }
    }


