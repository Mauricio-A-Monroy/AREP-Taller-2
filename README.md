# Taller 2 - Microframeworks WEB

## Descripción
Este proyecto implementa un microframework web en Java sin el uso de frameworks como Spark o Spring. Se mejoró la arquitectura respecto a la versión anterior al eliminar la clase `HttpServer` y permitir que la aplicación se ejecute directamente desde `WebApplication.java`. Además, se introdujeron funciones lambda para mejorar la manejabilidad del código.

## Características principales
- Servidor HTTP implementado en Java puro.
- Manejo de solicitudes y respuestas HTTP sin dependencias externas.
- Uso de expresiones lambda para definir rutas de manera sencilla.
- Soporte para peticiones GET y POST.
- Pruebas automatizadas para validar la funcionalidad del servidor.
- Aplicación web simple con interacción con el servidor mediante peticiones asíncronas.

## Estructura del proyecto
```
+---src
|   +---main
|   |   +---java
|   |   |   \---edu
|   |   |       \---escuelaing
|   |   |           \---arep
|   |   |               \---app
|   |   |                       HttpRequest.java
|   |   |                       HttpResponse.java
|   |   |                       HttpServer.java
|   |   |                       WebApplication.java
|   |   |
|   |   \---resources
|   |       \---static
|   |           |   index.html
|   |           |   IndexStyles.css
|   |           |   Pwelcome.jpeg
|   |           |
|   |           \---webApp
|   |                   App.html
|   |                   AppStyles.css
|   |
|   \---test
|       \---java
|           \---edu
|               \---escuelaing
|                   \---arep
|                       \---app
|                               AppTest.java
```

## Tecnologías utilizadas
- **Java** (Manejo de servidor y lógica de negocio).
- **HTML, CSS** (Interfaz de usuario en el lado del cliente).
- **JUnit** (Pruebas automatizadas).
- **Maven** (Manejo de dependencias y construcción del proyecto).

## Rutas Disponibles
### Páginas Web
- `/` → Página principal (`index.html`).
- `/webApp/App.html` → Aplicación web.
- `/webApp/AppStyles.css` → Estilos de la aplicación web.
- `/static/Pwelcome.jpeg` → Imagen de bienvenida.
- `/static/IndexStyles.css` → Estilos de la página principal.

### Endpoints de la API
- `/app/hello?name=alejo` → Devuelve un saludo con el nombre proporcionado.
- `/app/helloWorld` → Responde con "Hello, World!".
- `/hello/age` → Devuelve la edad del usuario (ejemplo de endpoint).
- `/pi` → Devuelve el valor de π.
- `/e` → Devuelve el valor de e.

## Ejecución del proyecto
Para ejecutar el servidor:
1. Clonar el repositorio.
    ```sh
   git clone https://github.com/Mauricio-A-Monroy/AREP-Taller-2
   cd /AREP-Taller-2
   ```
3. Compilar y ejecutar la aplicación con Maven:
   ```sh
   mvn clean package
   java -cp target/classes edu.escuelaing.arep.app.WebApplication
   ```
4. Abrir un navegador y acceder a `http://localhost:35000/` o a `http://localhost:35000/index.html`.

## Pruebas automatizadas
El proyecto cuenta con una clase de pruebas `AppTest.java` que valida el correcto funcionamiento del servidor web y de las rutas definidas. Se ejecutan con:
```sh
mvn test
```
![image](https://github.com/user-attachments/assets/85159955-a232-403a-a4b4-b445a958fb88)

## Pruebas de usuario
![image](https://github.com/user-attachments/assets/18609c27-106d-4b48-b654-b51757553dda)


![image](https://github.com/user-attachments/assets/15553805-9616-417f-897c-504683314690)


![image](https://github.com/user-attachments/assets/ea3be24e-2e4e-41a8-90ee-c2c7879851b0)


![image](https://github.com/user-attachments/assets/79bc312e-b55f-4a4c-a041-7bec81959c12)


![image](https://github.com/user-attachments/assets/efdd80a7-d5f1-4a51-88c9-1954b36a8b1d)

![image](https://github.com/user-attachments/assets/ad630f81-b873-4642-a9a4-9aec04184383)


![image](https://github.com/user-attachments/assets/57c8b1a8-35eb-40d4-a85a-07dd3affb5bc)

![image](https://github.com/user-attachments/assets/d4a0d99b-e20f-46ef-a78f-619e0f01bc4b)








## Agradecimientos
Agradecimiento especial al profesor por su apoyo y guía en el desarrollo del proyecto.

## Licencia
Este proyecto es de código abierto y está licenciado bajo la Licencia MIT.

