# Video Club 

Sistema para el manejo de préstamos de una tienda de renta de películas.
Permite manejar clientes, películas y los préstamos que hagan los clientes
sobre las películas. Permite ver los clientes morosos así como las películas
que están en mora.
Indíca además el cobro que se debe por el préstamo, permitiendo el cálculo
del cobro por multa.

## Acerca del Proyecto
Para poder compilar y correr este proyecto se debe hacer lo siguiente:

- El proyecto se trabaja con MySql, java, javascript y html.
- Se requiere utilizar un buscador moderno para la correcta visualización de la aplicación.
Se han probado Firefox, Chrome en sus versiones estables más recientes a la fecha de
modificación de este archivo.
- El proyecto se trabaja en netBeans, por lo que se recomienda utilizar este
IDE.
- El proyecto se corre en un servidor tomcat 8, por lo que se recomienda utilizar
este servidor.
- Se necesitará MySQL. Para el proyecto se utilizó la versión 5.7.
- Las siguientes librerias de java: 
  - Driver JDBC para MySQL, netBeans provee ```MySQL JDBC Driver```. Se puede descargar también
  en [este](http://mvnrepository.com/artifact/mysql/mysql-connector-java) enlace.
  - Lang de Apache commons 
  [```commons-lang3-3.4.jar```](http://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.4), 
  se utiliza para escapar ```java.lang.String``` según diferentes estandares.
  - Gson de Google 
  [```gson-2.6.2.jar```](http://mvnrepository.com/artifact/com.google.code.gson/gson/2.6.2), 
  se utiliza para convertir ```java.util.ArrayList``` a json rápidamente.
- Se hace uso de las siguientes librerías, (integradas dentro del proyecto):
  - [Bootstrap](http://getbootstrap.com/) de Twitter con el tema [Cosmo](https://bootswatch.com/cosmo/).
  - [JQuery](https://jquery.com/).
  - [SweetAlert](http://t4t5.github.io/sweetalert/).
