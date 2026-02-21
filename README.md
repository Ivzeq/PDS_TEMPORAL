# TPO – Gestión de Encuentros Deportivos (Java)

Este repositorio contiene la **solución en Java** del **Trabajo Práctico Obligatorio (TPO)** de la materia **Proceso de Desarrollo de Software (ADOO)**.

El objetivo del trabajo es diseñar e implementar un sistema orientado a objetos para la **gestión de encuentros deportivos**, aplicando **MVC** y **patrones de diseño**.

**Fecha de entrega:** 24/01/2026

## Objetivo

Desarrollar un sistema para la gestión de encuentros deportivos (fútbol, básquet, vóley u otros) que permita a los usuarios registrarse, crear y buscar partidos, emparejar jugadores para completar equipos y gestionar el ciclo de vida de un encuentro (estados, notificaciones y registro de estadísticas).

## Requerimientos funcionales

1. Registro de usuarios
   - Los usuarios deberán registrarse en la aplicación mediante un nombre de usuario, correo electrónico y contraseña. Opcionalmente pueden indicar deporte favorito y nivel de juego (Principiante, Intermedio, Avanzado).

2. Búsqueda de partidos
   - Los usuarios podrán buscar encuentros deportivos en su zona donde falten jugadores.

3. Creación de un partido
   - Un usuario puede crear un partido indicando: tipo de deporte, cantidad de jugadores requeridos, duración, ubicación y horario. El partido comienza en el estado **"Necesitamos jugadores"**.

4. Estado de los partidos (aplicar patrón State)
   - Cuando alcance la cantidad requerida de jugadores pasa a **"Partido armado"**.
   - **Confirmado**: todos los jugadores aceptaron.
   - **En juego**: comienza automáticamente cuando llega la fecha y hora.
   - **Finalizado**: se pueden registrar estadísticas o comentarios.
   - **Cancelado**: el organizador cancela antes del inicio.

5. Estrategia de emparejamiento (aplicar patrón Strategy)
   - Implementar estrategias de emparejamiento por nivel, por cercanía o por historial. Los partidos pueden permitir rangos de nivel o cualquier nivel.

6. Notificaciones (aplicar patrón Observer / Adapter)
   - Notificar a usuarios via Firebase (push) y correo cuando ocurran eventos importantes (nuevo partido, partido armado, confirmado, en juego, finalizado, cancelado).

## Requerimientos no funcionales

- Seguir el patrón arquitectónico **MVC**.
- Aplicar **al menos cuatro** patrones de diseño de los listados en la consigna.
- Proveer un diagrama de clases UML con los patrones identificados.
- Implementación en código y entrega de un ZIP con el código fuente.

## Arquitectura propuesta

- Patrón arquitectónico: **MVC** (Modelo - Vista - Controlador).
- Capas: Presentación (UI), Lógica de negocio (Servicios / Controllers), Persistencia (Repositorios / DAOs).
- Patrones de diseño a aplicar (al menos 4): **Strategy** (emparejamiento), **State** (estados de partido), **Observer/Adapter** (notificaciones), **Factory/Facade** (creación/configuración de objetos o adaptadores de servicios externos).

**Nota:** Documentar en el diagrama de clases dónde se aplican los patrones y justificar las decisiones.

## Clases identificadas (sugeridas)

- `Usuario` (id, nombre, email, nivel, deporteFavorito)
- `Partido` / `Encuentro` (id, deporte, fechaHora, ubicacion, duracion, cupoJugadores, estado)
- `Participacion` o relación `JugadorEnPartido` (usuario, partido, aceptado)
- `Emparejador` (interfaz Strategy) con implementaciones: `EmparejadorPorNivel`, `EmparejadorPorCercania`, etc.
- `EstadoPartido` (implementación State: NecesitamosJugadores, PartidoArmado, Confirmado, EnJuego, Finalizado, Cancelado)
- `NotificacionService` (observer/adapter hacia Firebase/Email)
- `RepositorioUsuarios`, `RepositorioPartidos` (acceso a datos)
- `ServicioPartido` / `PartidoController` (orquesta creación, emparejamiento y cambios de estado)
- DTOs y mappers según necesidad

## Relaciones entre clases (sugeridas)

- `Usuario` 1.._ <--> _ `Partido` : participa (un partido tiene muchos jugadores; un usuario puede participar en muchos partidos)
- `Usuario` 1 -- \* `Partido` : organiza (un usuario crea/organiza partidos)
- `Partido` 1 -- 1 `EstadoPartido` : tiene un estado que cambia según transiciones (State)
- `ServicioPartido` usa `Emparejador` (Strategy) para completar jugadores
- `NotificacionService` observa eventos de `Partido` y notifica a `Usuario`s

## Diagrama de clases

- Archivo PlantUML: `class_diagram.puml`
- Imagen generada: `docs/class_diagram.png`

## Entregables

- Diagrama de clases UML con patrones identificados y explicación de su aplicación.
- Código fuente comprimido (se sugiere Java, pero se puede usar otro lenguaje OOP).
- Documentación breve (README con instrucciones para ejecutar y descripción de la arquitectura).

## Criterios de evaluación

- Aplicación correcta del patrón MVC y de los patrones de diseño solicitados.
- Funcionalidad y calidad del código.
- Claridad de la documentación y del diagrama de clases.
- Implementación de notificaciones y manejo de estados.

## Entregables

- Código fuente en un repositorio Git (branch main)
- Documentación y README
- Diagrama de clases (`class_diagram.puml` y `docs/class_diagram.png`)

## Criterios de evaluación

- Cumplimiento de requisitos funcionales y no funcionales
- Calidad del código y estructura del proyecto
- Documentación y pruebas

## 📂 Estructura del proyecto (sugerida)

```
src/
 ├── model/
 ├── controller/
 ├── view/
 ├── strategy/
 ├── state/
 ├── observer/
 ├── factory/
 └── facade/
```

---

## 📄 Documentación

- Diagrama UML
- Explicación de patrones aplicados
- Código fuente completo
