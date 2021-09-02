# Exchange Generator
Es una aplicación web que **genera un intercambio de regalos** con n participantes (n > 2), requiere del nombre y correo electrónico de cada uno, cuando todos los participantes han sido agregados se tiene que dar un clic en ** "Generar Intercambio"**, se enviara un correo a cada uno de ellos con el nombre y dirección de correo de la persona a quien le darán un regalo. [Probar Exchange Generator](https://generador-intercambios.herokuapp.com/ "Test Exchange Generator")

## Descripción.
Este es un proyecto de **Spring Boot**, desarrollado en **Spring Tools Suite 4** con **Maven** por lo que, en el **pom.xml** se pueden revisar las dependencias usadas para su funcionamiento: **spring-boot-starter-mail** | **spring-boot-starter-thymeleaf** | **spring-boot-starter-web** |  **spring-boot-devtools** | **spring-boot-starter-validation**.

## Notas importantes.
Es posible descargar el proyecto y correrlo, pero se necesitan agregar algunas propiedades en **application.properties**. El formato es el siguiente:
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=youremail@gmail.com
    spring.mail.password=yourpassword
    spring.mail.properties.mail.smtp.starttls.enable=true
    spring.mail.properties.mail.smtp.starttls.required=true
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.connectiontimeout=5000
    spring.mail.properties.mail.smtp.timeout=5000
    spring.mail.properties.mail.smtp.writetimeout=5000
