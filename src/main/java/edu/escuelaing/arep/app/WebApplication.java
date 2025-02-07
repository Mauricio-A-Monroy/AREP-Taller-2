package edu.escuelaing.arep.app;

import static edu.escuelaing.arep.app.HttpServer.*;

public class WebApplication {
    public static void main(String[] args) throws Exception {
        staticfiles("src/main/resources/static");

        get("/helloWorld", (req, res) -> "hello world!");

        get("/hello", (req, resp) -> "Hello " + req.getValues("name"));

        get("/hello/age", (req, resp) -> "Hello " + req.getValues("name") + ", your age is " + req.getValues("age"));

        get("/pi", (req, resp) -> String.valueOf(Math.PI));

        get("/e", (req, resp) -> String.valueOf(Math.E));

        start();
    }
}