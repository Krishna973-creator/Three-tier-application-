public class VulnerableCodeExample {

    public static void main(String[] args) {
        // Command Injection Vulnerability
        if (args.length > 0) {
            String userInput = args[0];
            try {
                // WARNING: Directly using user input in a shell command!
                Process process = Runtime.getRuntime().exec("ls -l " + userInput);
                java.io.InputStream inputStream = process.getInputStream();
                java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
                String output = s.hasNext() ? s.next() : "";
                System.out.println("Output: " + output);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

        // Path Traversal Vulnerability
        String filename = "data.txt"; // Imagine this could come from user input
        try {
            java.io.File file = new java.io.File("../../../" + filename); // Trying to access parent directories
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            System.out.println("File found: " + file.getAbsolutePath());
            fis.close();
        } catch (java.io.IOException e) {
            System.out.println("Error accessing file: " + e.getMessage());
        }

        // Hardcoded Secret (though code scanning might not always catch simple strings)
        String apiKey = "YOUR_SUPER_SECRET_API_KEY";
        System.out.println("API Key: " + apiKey);

        // Potential Null Pointer Dereference
        String potentiallyNull = System.getProperty("nonExistentProperty");
        if (potentiallyNull.length() > 5) { // Might trigger a warning
            System.out.println("Length is greater than 5");
        }
    }
}
