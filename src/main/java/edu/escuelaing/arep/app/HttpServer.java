package edu.escuelaing.arep.app;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class HttpServer {
    private static Map<String,BiFunction<HttpRequest, HttpResponse, String>> servicios = new HashMap();

    public static void start() throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {

            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream());

            String inputLine, outputLine;

            boolean isFirstLine = true;
            String filePath = "";
            String HTTPRequest = "";
            while ((inputLine = in.readLine()) != null) {
                if (isFirstLine){
                    HTTPRequest = inputLine.split(" ")[0];
                    filePath = inputLine.split(" ")[1];
                    isFirstLine = false;
                }

                if (!in.ready()) {
                    break;
                }
            }

            System.out.println("Request: " + HTTPRequest);
            System.out.println("FilePath: " + filePath);

            URI resourceuri = new URI(filePath);
            System.out.println("URI: " + resourceuri);

            if (resourceuri.getPath().startsWith("/app/")) {
                HttpRequest req = new HttpRequest(resourceuri.getPath(), resourceuri.getQuery());
                HttpResponse resp = new HttpResponse();
                outputLine = processRequest(req, resp);
                out.println(outputLine);
            } else {
                getDefaultResponse(filePath, out, dataOut);
            }

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
    public static void get(String route, BiFunction<HttpRequest, HttpResponse, String> s){
        System.out.println("route: " + route);
        servicios.put("/app" + route, s);
    }


    private static String processRequest(HttpRequest req, HttpResponse resp) {
        System.out.println("Query: " + req.getQuery());
        System.out.println("Path: " + req.getPath());
        BiFunction<HttpRequest, HttpResponse, String> s = servicios.get(req.getPath());
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                + "{\"response\":\""+ s.apply(req, resp) +"\"}";
        return response;
    }
    public static void staticfiles(String path){}

    public static void getDefaultResponse(String filePath, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        if (filePath.equals("/")){
            filePath = "/index.html";
        }

        File file = new File("src/main/resources/static" + filePath);

        if (file.exists() && !file.isDirectory()) {
            String contentType = getContentType(filePath);

            // Leer el archivo
            byte[] fileData = readFileData(file);

            // Enviar respuesta HTTP
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType);
            out.println("Content-Length: " + fileData.length);
            out.println();
            out.flush();

            dataOut.write(fileData, 0, fileData.length);
            dataOut.flush();
        } else {
            // Archivo no encontrado
            String errorMessage = "HTTP/1.1 404 Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<h1>404 File Not Found</h1>";
            out.println(errorMessage);
        }
    }

    private static String getContentType(String filePath) {
        if (filePath.endsWith(".html") || filePath.endsWith(".htm")) {
            return "text/html";
        } else if (filePath.endsWith(".css")) {
            return "text/css";
        } else if (filePath.endsWith(".js")) {
            return "application/javascript";
        } else if (filePath.endsWith(".png")) {
            return "image/png";
        } else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
            return "image/jpeg";
        } else {
            return "application/octet-stream"; // Tipo genérico
        }
    }

    private static byte[] readFileData(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] fileData = new byte[(int) file.length()];
        fileInputStream.read(fileData);
        fileInputStream.close();
        return fileData;
    }
}
