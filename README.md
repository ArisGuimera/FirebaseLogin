# Login Nuwe - [Reto Semana 1](https://nuwe.io/challenge/reto-mobile-individual-semana-1)

## Definición del proyecto:

El reto consta de cuatro pantallas, en ellas podremos registrarnos, logearnos e incluso validar la cuenta.1
Este proyecto ha sido creado en menos de una semana, siguiendo buenas prácticas y todas las recomendaciones de Google.

## Características del proyecto:

- Arquitectura MVVM.
- *Clean Architecture*
- *SOLID*
- Corrutinas
- Inyección de dependencias con *Dagger Hilt*
- Firebase (Tanto para login como para persistir en base de datos *realTime*)
- Splash optimizado
- LiveData
- Flows
- StateFlow

## Pantalla de introducción
La primera pantalla nos servirá de navegador hacia el login o el registro. Como curiosidad el *splash* está optimizado para evitar el típico *delay* inicial en blanco.
TODAS las navegaciones usar un *wrapper* event para optimizar más el livedata en eventos de una única acción.

<p align="center">
<img align="center" width="300" alt="portfolio_view" src="./docs/introductionview.png">
</p>

## Pantalla de login

Aquí podremos iniciar sesión. Además comprobará si la cuenta ha sido verificada para acceder al supuesto detalle (no pedido en la prueba) o a la pantalla de verificación.

El control de errores se ejecuta de dos formas distintas. En tiempo real, cada vez que uno de los campos pierde el foco o se actualiza. Y una gestión secundaria por si el login no hace *match* en la base de datos.

<p align="center">
<img align="center" width="300" alt="portfolio_view" src="./docs/login_animation.gif">
<img align="center" width="300" alt="portfolio_view" src="./docs/login_validation.gif">
<img align="center" width="300" alt="portfolio_view" src="./docs/login_error.gif">
</p>


## FAQ
