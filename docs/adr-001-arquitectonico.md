# ADR-001: Monolito modular (vs microservicios)

## Estado
Aceptada

## Contexto
El sistema debe consultar existencias, estimar riesgo y recomendar transferencias/compras.
Una sola persona (encargado) aprueba antes de ejecutar.
Volumen esperado: cientos de productos, decenas de bodegas, uso interno.

## Decisión
Implementar como **monolito modular** con Spring Boot, separando lógicamente:
- Inventario
- Pronóstico/riesgo
- Recomendaciones
- Aprobación
- Auditoría

## Consecuencias
**Positivas:**
- Despliegue simple (1 contenedor + DB).
- Transacciones ACID directas.
- Menos latencia entre módulos.
- Onboarding rápido.

**Negativas:**
- Escalado vertical únicamente.
- Si un módulo falla, cae todo.
- Acoplamiento de releases.

**Mitigaciones:**
- Módulos como paquetes Java bien delimitados.
- Interfaces claras (services) para permitir extracción futura.
- RabbitMQ ya presente para desacoplar la parte asíncrona.