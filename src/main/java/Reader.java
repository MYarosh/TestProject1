import java.io.*;

public class Reader {
    private String name = null, job = null, newJob = null;

    /*
    Read the file with data for tests
     */
    public Reader(){
        try{
            File file;
            if (isWindows()){
                file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\configure");
            }else {
                file = new File(System.getProperty("user.dir") + "/src/test/resources/configure");
            }
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split("~");
                if (words[0].equals("name")){
                    if (name == null) {
                        name = words[1];
                    }else{
                        System.out.println("[ERROR] Configure file has two line with field \"name\"!");
                        System.exit(1);
                    }
                } else if (words[0].equals("job")){
                    if (job == null) {
                        job = words[1];
                    }else{
                        System.out.println("[ERROR] Configure file has two line with field \"job\"!");
                        System.exit(1);
                    }
                } else if (words[0].equals("newJob")){
                    if (newJob == null) {
                        newJob = words[1];
                    }else{
                        System.out.println("[ERROR] Configure file has two line with field \"newJob\"!");
                        System.exit(1);
                    }
                } else{
                    System.out.println("[WARNING] Configure file has extra line.");
                }
                line = reader.readLine();
            }
            if (name == null || job == null || newJob ==  null){
                System.out.println("[ERROR] Configure file hasn't got necessary fields!");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File with config not found!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("[ERROR] Can't read file!");
            System.exit(1);
        } catch (Exception e){
            System.exit(-1);
        }
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getNewJob() {
        return newJob;
    }

    public static boolean isWindows(){

        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));

    }
}
