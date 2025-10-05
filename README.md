# 🛒 ProyectoBD: Sistema de Gestión de Inventario y Ventas

[![Estado del Proyecto](https://img.shields.io/badge/Estado-Finalizado-success)](https://github.com/luismtapia/ProyectoBD)
[![Tecnología Principal](https://img.shields.io/badge/Tecnolog%C3%ADa-Java%20%7C%20JavaFX-blue)](https://www.java.com)
[![Base de Datos](https://img.shields.io/badge/Base%20de%20Datos-SQL%20Server-red)](https://www.microsoft.com/es-es/sql-server)

## 📝 Visión General

**ProyectoBD** es un sistema integral de gestión y control de inventario, ventas y administración interna, desarrollado para proporcionar una solución robusta y eficiente a tiendas y pequeños negocios.

El proyecto se construyó utilizando el lenguaje **Java** junto con el *framework* **JavaFX** para una interfaz de usuario moderna y multiplataforma. La persistencia de datos se gestiona completamente a través de un servidor de **Microsoft SQL Server**, garantizando la integridad, seguridad y concurrencia de la información transaccional.

---

## ✨ Funcionalidades Clave

El sistema está organizado en módulos que cubren las áreas funcionales críticas de un negocio minorista, modeladas directamente desde las entidades de la base de datos:

### 💼 Módulo de Gestión de Inventario y Productos

* **Administración de Productos:** Creación, edición y eliminación de productos (`productos`). Incluye atributos detallados como descripción, marca, modelo, precios de compra y venta.
* **Control de Stock:** Manejo de existencias, incluyendo la gestión de diferentes **tallas** (`tallas_es_de_productos`).
* **Proveedores:** Registro y gestión de la información de los proveedores (`proveedores`) y la relación con los productos que suministran.

### 💰 Módulo de Ventas y Transacciones

* **Ventas y Órdenes:** Creación, seguimiento y procesamiento de nuevas transacciones de venta (`ventas`, `ordenes`).
* **Detalle de Venta:** Registro de los productos y cantidades incluidos en cada transacción (`detalle`).
* **Facturación y Tickets:** Generación de facturas y tickets de venta (`facturas`, `tickets`).
* **Apartados/Reservas:** Sistema para gestionar productos reservados por clientes (`sistema_de_apartados`).

### 🧑‍🤝‍🧑 Módulo de Clientes y Usuarios

* **Gestión de Clientes:** Base de datos completa para la información de contacto y detalle de clientes (`clientes`).
* **Usuarios del Sistema:** Administración de las credenciales de los usuarios que acceden al sistema (`usuarios`), con credenciales seguras para la conexión a la DB.

### 👔 Módulo de Recursos Humanos y Administración

* **Empleados:** Registro y administración de datos de personal (`empleados`), incluyendo la asignación a `departamentos`.
* **Sueldos y Remuneraciones:** Seguimiento de la información salarial (`sueldos`).
* **Capacitación:** Registro de las sesiones de formación recibidas por los empleados (`capacitaciones`).

---

## 🛠️ Tecnologías y Requisitos

| Componente | Versión Recomendada | Rol dentro del Proyecto |
| :--- | :--- | :--- |
| **Java Development Kit (JDK)** | 8 o superior | Lenguaje de desarrollo principal. |
| **JavaFX** | Integrado con el JDK 8 o como módulo externo (OpenJFX) | Interfaz Gráfica de Usuario (GUI). |
| **Microsoft SQL Server** | Cualquier versión estable | Motor de Base de Datos relacional. |
| **JDBC Driver** | Controlador para SQL Server | Conexión Java/SQL Server. |
| **NetBeans IDE** | Cualquier versión moderna | Entorno de desarrollo utilizado. |

---

## 🚀 Instalación y Configuración

Sigue estos pasos para configurar y ejecutar el proyecto en tu entorno local.

### 1. Requisitos Previos

Asegúrate de tener instalados:
1.  **JDK 8+** (con JavaFX configurado o como librería en el proyecto).
2.  Una instancia activa de **Microsoft SQL Server**.
3.  El archivo JAR del **JDBC Driver de SQL Server** en las librerías del proyecto.

### 2. Clonación del Repositorio

Clona este repositorio en tu máquina local:

```bash
git clone [https://github.com/luismtapia/ProyectoBD.git](https://github.com/luismtapia/ProyectoBD.git)
cd ProyectoBD
```
### 3. Configuración de la Base de Datos

El sistema depende de una conexión activa con SQL Server.

1.  **Crear el Esquema:** Ejecuta los scripts SQL necesarios para crear la base de datos y todas las tablas (como `productos`, `ventas`, `clientes`, etc.). *Los scripts de creación no están en este README, pero son necesarios.*
2.  **Configurar la Conexión:**
    * Abre el archivo `src/SQL/SQL.java`.
    * Modifica la URL de conexión, el nombre de la base de datos (`databaseName`), el `usuario` y la `contraseña` para que coincidan con tus credenciales de SQL Server:

    ```java
    // Archivo: src/SQL/SQL.java

    // MODIFICAR SEGÚN SUS CREDENCIALES
    private final static String usuario = "Su_Usuario_DB"; // Ejemplo: "sa" o "luis"
    private final static String contrasena = "Su_Contraseña_DB"; // Ejemplo: "PasswordFuerte"

    // MODIFICAR SEGÚN SU SERVIDOR y PUERTO
    String url = "jdbc:sqlserver://[SERVIDOR_DB]:[PUERTO];databaseName=[NOMBRE_DE_SU_DB]"; 
    // Ejemplo: jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=MiTiendaDB;
    ```
    ⚠️ **Nota de Seguridad:** Las credenciales de la base de datos están codificadas en el archivo `SQL.java` porque no era importante y en su momento no preocupaba mucho. Para producción, se recomienda mover estas credenciales a variables de entorno o archivos de configuración externos.

### 4. Ejecución

1.  Importa el proyecto en tu IDE (NetBeans es el más compatible con la estructura actual).
2.  Asegúrate de que las librerías de JavaFX y el JDBC Driver estén correctamente referenciadas en la configuración del proyecto.
3.  Construye (**Build**) el proyecto.
4.  Ejecuta la clase principal que contiene el método `main` para iniciar la interfaz JavaFX.

---

## 📂 Estructura del Código

El código fuente sigue una estructura orientada a paquetes, donde cada paquete dentro de `src/` representa una entidad del modelo de datos o un módulo de negocio.



---

## 🤝 Contribución

Este proyecto fue desarrollado con fines académicos/personales, pero siempre se agradece la retroalimentación.

1.  Haz un *Fork* (bifurcación) del proyecto.
2.  Crea una rama (`git checkout -b feature/AmazingFeature`).
3.  Realiza tus cambios.
4.  Comitea tus cambios (`git commit -m 'Add some AmazingFeature'`).
5.  Sube la rama (`git push origin feature/AmazingFeature`).
6.  Abre un *Pull Request*.

---


## ⚖️ Licencia

Este proyecto es de código abierto y está disponible bajo la **Licencia MIT**.


<p align="center">
  <sub>© 2025 Appxion · Soluciones de software para tu negocio
  </sub>
  <sub> | Luis Manuel Tapia Bautista 🧑‍💻      </sub>
</p>
