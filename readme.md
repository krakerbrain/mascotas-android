# Huachitos - Aplicación para Adopción de Animales

Huachitos es una plataforma colaborativa dedicada a encontrar hogar a animales en situación de
abandono o callejera. Esta aplicación móvil permite a los usuarios ver una lista de animales
disponibles para adopción y enviar correos electrónicos expresando su interés en adoptar un animal
en particular.

## Funcionalidades Principales

### 1. Carga Inicial de Datos

La aplicación realiza una carga inicial de datos desde la API REST proporcionada. Los datos se
obtienen desde el endpoint `/animales` y se almacenan en una base de datos SQLite en el dispositivo
del usuario. Esto permite que la aplicación funcione sin conexión a internet posteriormente.

### 2. Recuperar Datos del Servidor y Base de Datos

La lista de animales disponibles se muestra en un RecyclerView. Los datos se recuperan del servidor
REST en la primera carga de la aplicación. Si no hay conexión a internet, se muestra un mensaje al
usuario informándole de la necesidad de conexión para cargar los datos.

### 3. Detalles del Animal

Al hacer clic en un animal de la lista, se muestra una vista de detalle con información relevante
sobre ese animal, como nombre, descripción física, edad, género, tipo y ubicación. Se utiliza una
vista de detalle simplificada para mostrar solo los atributos más importantes.

### 4. Envío de Correo Electrónico

Los usuarios pueden enviar correos electrónicos expresando su interés en adoptar un animal. Al hacer
clic en el botón "Enviar Correo" en la vista de detalle de un animal, se abre una ventana de correo
electrónico prellenada con la dirección de destino (`ventas_botxcamps@gmail.com`), el asunto (
formato: "Consulta {NOMBRE_ANIMAL} id {ID_ANIMAL}") y un mensaje predefinido.

## Arquitectura y Dependencias

- La aplicación está escrita en Kotlin y utiliza view binding para la gestión de vistas.
- Se sigue una arquitectura MVVM (Model-View-ViewModel) con LiveData y Room para el manejo de datos.
- Los request HTTP se realizan utilizando Retrofit.
- Se emplean Kotlin Coroutines para el trabajo en segundo plano.
- Se utiliza una serie de bibliotecas adicionales según las necesidades del proyecto.

## Requisitos del Desarrollo

- Se espera un código legible y bien estructurado, siguiendo los principios SOLID.
- Se deben seguir las convenciones de nombres en las declaraciones de variables, métodos y clases.
- Se recomienda documentar el código de manera adecuada para facilitar su comprensión.
- Se deben realizar pruebas unitarias y de integración utilizando las bibliotecas apropiadas.

## Uso de la API REST

La API REST proporcionada ofrece dos endpoints principales:

1. `/animales`: Devuelve la lista de animales disponibles para adopción.
2. `/animales/{id}`: Devuelve los detalles de un animal específico, identificado por su ID.

### Creado por Mario Montenegro
