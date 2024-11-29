package benchmark;

import org.example.Graph;
import org.example.GraphGenerator;
import org.example.GraphTraversal;
import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


public class GraphTraversalBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        @Param({"7500"}) // Число вершин
        public int numVertices;

        @Param({"15000"}) // Число ребер
        public int numEdges;

        public Graph graph;
        public int startVertex;

        @Setup(Level.Trial)
        public void setUp() {
            graph = GraphGenerator.generateRandomGraph(numVertices, numEdges);
            startVertex = 0;
        }
    }

    public static void main(String[] args) throws Exception {

        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(GraphTraversalBenchmark.class.getSimpleName() + ".benchmarkBFS")
                .shouldDoGC(true)
                .jvmArgs("-Xms1G", "-Xmx2G", "-XX:+UnlockDiagnosticVMOptions",
                        //"-XX:+PrintCompilation", "-XX:+PrintInlining",
                        "-Djmh.stack.profiles=true")
                .resultFormat(ResultFormatType.JSON)
                .result("benchmark-result/" + System.currentTimeMillis() + ".json")
                .build();

        new Runner(opt).run();



        org.openjdk.jmh.runner.options.Options optDfsRec = new OptionsBuilder()
                .include(GraphTraversalBenchmark.class.getSimpleName() + ".benchmarkDFSRecursive")
                .shouldDoGC(true)
                .jvmArgs("-Xms1G", "-Xmx2G", "-XX:+UnlockDiagnosticVMOptions",
                        //"-XX:+PrintCompilation", "-XX:+PrintInlining",
                        "-Djmh.stack.profiles=true")
                .resultFormat(ResultFormatType.JSON)
                .result("benchmark-result/" + System.currentTimeMillis() + ".json")
                .build();

        new Runner(optDfsRec).run();



        org.openjdk.jmh.runner.options.Options optDFSIt = new OptionsBuilder()
                .include(GraphTraversalBenchmark.class.getSimpleName() + ".benchmarkDFSIterative")
                .shouldDoGC(true)
                .jvmArgs("-Xms1G", "-Xmx2G", "-XX:+UnlockDiagnosticVMOptions",
                        //"-XX:+PrintCompilation", "-XX:+PrintInlining",
                        "-Djmh.stack.profiles=true")
                .resultFormat(ResultFormatType.JSON)
                .result("benchmark-result/" + System.currentTimeMillis() + ".json")
                .build();

        new Runner(optDFSIt).run();
    }


    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 20, time = 1)
    public void benchmarkBFS(BenchmarkState state) {
        GraphTraversal.bfsOptimized(state.graph, state.startVertex);
    }




    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 20, time = 1)
    public void benchmarkDFSRecursive(BenchmarkState state) {
        GraphTraversal.dfsRecursiveOptimized(state.graph, state.startVertex);
    }



    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 20, time = 1)
    public void benchmarkDFSIterative(BenchmarkState state) {
        GraphTraversal.dfsIterativeOptimized(state.graph, state.startVertex);
    }
}

