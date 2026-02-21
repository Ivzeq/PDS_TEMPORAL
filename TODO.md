# TODO.md

## Objetivo

Completar el **Trabajo Práctico Obligatorio** cumpliendo **todos los requerimientos funcionales y no funcionales**, aplicando **MVC** y **al menos 4 patrones de diseño**, con documentación clara y código funcional en Java.

---

## 1️⃣ Organización inicial

- [x] Crear repositorio Git
- [x] Definir integrantes del grupo (nombre, apellido, LU)
- [ ] Definir alcance exacto del sistema
- [ ] Acordar deporte/s inicial/es a soportar (mínimo uno)
- [ ] Definir si la aplicación será solo backend o con vista simulada (CLI)

---

## 2️⃣ Arquitectura MVC

### Modelo (Model)

- [ ] Clase `Usuario`
  - username
  - email
  - password
  - deporteFavorito
  - nivelDeJuego

- [ ] Clase `Partido`
  - deporte
  - jugadoresRequeridos
  - jugadoresInscriptos
  - duracion
  - ubicacion
  - fechaHora
  - estadoActual
  - estrategiaEmparejamiento

- [ ] Clase `Equipo` (si se justifica)
- [ ] Enums:
  - [ ] `NivelDeJuego` (PRINCIPIANTE, INTERMEDIO, AVANZADO)
  - [ ] `TipoDeporte`

### Controlador (Controller)

- [ ] Controlador de usuarios
- [ ] Controlador de partidos
- [ ] Controlador de notificaciones
- [ ] Validaciones de negocio

### Vista (View)

- [ ] Vista por consola (CLI)
- [ ] Menú principal
- [ ] Alta de usuarios
- [ ] Creación de partidos
- [ ] Búsqueda de partidos
- [ ] Inscripción a partidos

---

## 3️⃣ Patrón State (Estados del Partido)

- [ ] Interfaz `EstadoPartido`
- [ ] `EstadoNecesitamosJugadores`
- [ ] `EstadoPartidoArmado`
- [ ] `EstadoConfirmado`
- [ ] `EstadoEnJuego`
- [ ] `EstadoFinalizado`
- [ ] `EstadoCancelado`
- [ ] Transiciones automáticas entre estados
- [ ] Validación de acciones según estado

---

## 4️⃣ Patrón Strategy (Emparejamiento)

- [ ] Interfaz `EstrategiaEmparejamiento`
- [ ] `EmparejamientoPorNivel`
- [ ] `EmparejamientoPorCercania` (mock)
- [ ] `EmparejamientoPorHistorial` (mock)
- [ ] Configuración dinámica de estrategia por partido

---

## 5️⃣ Patrón Observer (Notificaciones)

- [ ] Interfaz `Observer`
- [ ] Clase `Notificador`
- [ ] Registro y eliminación de observers
- [ ] `NotificacionEmail` (mock JavaMail)
- [ ] `NotificacionPush` (mock Firebase)
- [ ] Eventos que disparan notificaciones:
  - [ ] Creación de partido
  - [ ] Partido armado
  - [ ] Partido confirmado
  - [ ] Cambio de estado

---

## 6️⃣ Patrón Factory

- [ ] Factory de creación de partidos
- [ ] Factory de estrategias de emparejamiento
- [ ] Factory de estados del partido

---

## 7️⃣ Patrón Facade

- [ ] Clase `SistemaEncuentrosFacade`
- [ ] Métodos simplificados para:
  - [ ] Registrar usuario
  - [ ] Crear partido
  - [ ] Buscar partidos
  - [ ] Inscribirse a partido
  - [ ] Cambiar estado de partido

---

## 8️⃣ Reglas de negocio

- [ ] No permitir inscripción si el partido está completo
- [ ] Validar nivel mínimo/máximo permitido
- [ ] Cambio automático a "Partido armado"
- [ ] Confirmación de jugadores
- [ ] Cancelación solo antes de iniciar

---

## 9️⃣ Datos y persistencia (mock)

- [ ] Listas en memoria
- [ ] Datos de prueba
- [ ] Simulación de usuarios y partidos

---

## 🔟 UML y Documentación

- [ ] Diagrama de clases UML
- [ ] Identificación explícita de patrones
- [ ] Justificación de cada patrón usado
- [ ] Diagrama actualizado según implementación final

---

## 1️⃣1️⃣ Código y calidad

- [ ] Código limpio y legible
- [ ] Nombres correctos de clases y métodos
- [ ] Separación clara de responsabilidades
- [ ] Comentarios solo donde sea necesario

---

## 1️⃣2️⃣ Pruebas

- [ ] Pruebas manuales desde la vista
- [ ] Casos de prueba principales
- [ ] Validación de estados

---

## 1️⃣3️⃣ Entregables

- [ ] Código fuente completo
- [ ] README.md actualizado
- [ ] TODO.md completo
- [ ] Diagrama UML final
- [ ] PDF del trabajo
- [ ] Preparar exposición

---

## 1️⃣4️⃣ Revisión final

- [ ] Cumple MVC
- [ ] Usa al menos 4 patrones
- [ ] Funciona sin errores
- [ ] Cumple todas las consignas

---

> Checklist completa para aprobar el TPO sin sorpresas
