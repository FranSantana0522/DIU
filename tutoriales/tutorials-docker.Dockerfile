FROM openjdk:21
#Indica la imagen base sobre la que se construirá la
#aplicación dentro del contenedor.
COPY target/tutoriales-0.0.1-SNAPSHOT.jar /tutorialsapp2.jar
#Como su nombre indica, copia el .jar que generamos
#anteriormente en docker.

# Esta instrucción nos provee valores por defecto a nuestro contenedor,
#es decir, mediante esta podemos definir una serie de comandos que solo
#se ejecutarán una vez que el contenedor se ha inicializado.
CMD ["java", "-jar", "/tutorialsapp2.jar"]