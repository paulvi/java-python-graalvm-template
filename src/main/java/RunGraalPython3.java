import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunGraalPython3 {
    public static String PYTHON = "python";
    // venv/**/* must be included into resources in pom.xml
    //private static String VENV_EXECUTABLE = RunGraalPython3.class.getClassLoader().getResource(Paths.get("venv", "bin", "graalpython").toString()).getPath();
    private static String SOURCE_FILE_NAME = "health.py";

    public static void log(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        log("Hello Java!");
        log(System.getProperty("java.version"));
        log(System.getProperty("java.runtime.version"));
        //Java 9
        //log(Runtime.version().toString());

        String pyFilename = "./health.py";

        Path path = Paths.get("venv", "bin", "graalpython");
        if (path==null){
            log("venv/ is not yet copied under target/classes/, run `mvn process-resources` or any next maven phase,");
        }
        //String VENV_EXECUTABLE = RunGraalPython3.class.getClassLoader().getResource(path.toString()).getPath();
        String VENV_EXECUTABLE = path.toString();
        log(VENV_EXECUTABLE);

        //try (Context context = Context.create()) {
        //try (Context context = Context.newBuilder(PYTHON).allowAllAccess(true).build()) {
        try (Context context = Context.newBuilder("python").
                allowAllAccess(true).
                option("python.ForceImportSite", "true").
                option("python.Executable", VENV_EXECUTABLE).
                build();) {
            context.eval(PYTHON, "print('Hello Python!')");

            context.eval(PYTHON, "import sys; print(sys.version)");

//            //4
//            try(BufferedReader br = new BufferedReader(new FileReader(pyFilename))) {
//                int i = 0;
//                for(String line; (line = br.readLine()) != null; ) {
//                    i++;
//                    log(""+i+": "+line);
//                    context.eval(PYTHON, line);
//                }
//            }catch ( IOException e){
//                log("IOException "+e);
//            }

            InputStreamReader code = new InputStreamReader(RunPython4.class.getClassLoader().getResourceAsStream(SOURCE_FILE_NAME));
            Source source;
            try {
                source = Source.newBuilder(PYTHON, code, SOURCE_FILE_NAME).build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            context.eval(source);


        }
    }
}