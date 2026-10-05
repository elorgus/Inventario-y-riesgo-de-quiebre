# ADR-002: Stack Spring Boot + PostgreSQL + RabbitMQ

## Estado
Aceptada

## Contexto
Necesitamos:
- API REST para frontend y sistemas externos.
- Persistencia transaccional (stock, riesgo).
- Notificación asíncrona de riesgos a otros sistemas.
- Reproducibilidad en Codespaces.

## Decisión
- **Spring Boot 3.4 + Java 21**: ecosistema maduro, JPA, Actuator, starter-amqp.
- **PostgreSQL 16**: constraints, tipos numéricos, JSONB, robusto.
- **RabbitMQ 3.13**: colas simples con DLQ, ya integrado en Compose.
- **HTML/JS vanilla**: sin build step, demo funcional.
- **Docker Compose**: 3 servicios (app + db + broker) reproducibles.

## Consecuencias
**Positivas:**
- Todo el equipo puede arrancar con `docker compose up`.
- Actuator expone health con detalles de DB y broker.
- RabbitMQ permite desacoplar la recomendación.

**Negativas:**
- Spring Boot tiene mayor huella que alternativas (Quarkus, Node).
- Java 21 requiere JDK moderno (resuelto en devcontainer).

**Alternativas consideradas:**
- Node.js + Express: descartado por menor tipado en dominio transaccional.
- Django: descartado por ecosistema JPA maduro en el equipo.