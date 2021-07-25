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

![IntroductionView](./docs/introductionview.png)


## FAQ

En este proyecto encontrarás un proyecto MVVM para Android en Kotlin que podrás seguir con los capítulos de CursoKollin.com Cada capítulo tendrá una rama con los conocimientos explicados y en **Master** tendrás la última versión.
- [MVVM básico](https://cursokotlin.com/mvvm-en-android-con-kotlin-livedata-y-view-binding-android-architecture-components) - Primera versión de MVVM, lo encontrarás en la rama [**simpleMVVM**](https://github.com/ArisGuimera/SimpleAndroidMVVM/tree/simpleMVVM) - [Clean Architecture + Retrofit y corrutinas](https://cursokotlin.com/mvvm-en-android-con-kotlin-implementando-retrofit-corrutinas-y-clean-architecture/) - Segunda versión de MVVM, donde hemos añadido peticiones en red consumiendo un API con Retrofit 2 y corrutinas. También hemos añadido clean architecture. [**CleanRetrofit**](https://github.com/ArisGuimera/SimpleAndroidMVVM/tree/CleanRetrofit) Recuerda que también puedes ver los capítulos por escritos [en mi canal de Youtube](https://youtube.com/c/aristidevs)