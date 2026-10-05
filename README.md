# App 1 — Inventario y riesgo de quiebre

Sistema para una cadena de restaurantes con múltiples bodegas que necesita anticipar quiebres de stock y recomendar transferencias o compras.

## 📑 Entregable

| Sección | Enlace |
|---|---|
| Objetivo, actores y alcance | [§1](#1-objetivo-actores-y-alcance) |
| Requisitos funcionales y de calidad | [§2](#2-requisitos) |
| Diagrama C4 contexto | [docs/c4-contexto.mmd](docs/c4-contexto.mmd) |
| Diagrama C4 contenedores | [docs/c4-contenedores.mmd](docs/c4-contenedores.mmd) |
| Flujo de operación crítica | [docs/flujo-critico.mmd](docs/flujo-critico.mmd) |
| Stack con justificación | [§3](#3-stack-propuesto) |
| ADRs | [ADR-001](docs/adr-001-arquitectonico.md), [ADR-002](docs/adr-002-tecnologico.md) |
| Riesgos | [docs/riesgos.md](docs/riesgos.md) |
| Métricas | [docs/metricas.md](docs/metricas.md) |

---

## 1. Objetivo, actores y alcance

### Objetivo
Detectar productos en riesgo de quiebre por bodega y sugerir acciones (transferencia o compra) que un humano aprueba antes de ejecutarse.

### Actores
- **Encargado de bodega** — consulta existencias, recibe alertas.
- **Supervisor de compras** — aprueba transferencias y órdenes de compra.
- **Sistema de compras** (externo) — recibe órdenes aprobadas.
- **Sistema de inventario** (fuente de datos) — alimenta stock actual.

### Alcance
**Dentro:**
- Consulta de stock por producto y bodega.
- Cálculo de riesgo (días hasta quiebre, nivel).
- Recomendación de transferencia entre bodegas.
- Flujo de aprobación humana.

**Fuera:**
- Ejecución automática de compras.
- Optimización de rutas logísticas.
- Predicción basada en ML.

---

## 2. Requisitos

### Funcionales
| ID | Requisito |
|---|---|
| RF-01 | Listar productos con stock mínimo y consumo diario |
| RF-02 | Calcular días hasta quiebre por (producto, bodega) |
| RF-03 | Clasificar riesgo en `CRITICO`, `ALTO`, `MEDIO`, `OK` |
| RF-04 | Sugerir transferencia desde bodega con excedente |
| RF-05 | Publicar evento `stock.en.riesgo` a RabbitMQ |
| RF-06 | Exponer endpoints REST para todos los listados |

### De calidad
| ID | Requisito |
|---|---|
| RNF-01 | Health check `/actuator/health` con DB y broker |
| RNF-02 | Respuestas REST < 500 ms p95 |
| RNF-03 | Frontend responsive con auto-refresh |
| RNF-04 | Docker Compose multi-servicio reproducible |

---

## 3. Stack propuesto

| Capa | Tecnología | Justificación |
|---|---|---|
| Backend | Spring Boot 3.4 (Java 21) | Ecosistema maduro, JPA, Actuator, RabbitMQ |
| BD | PostgreSQL 16 | Transaccional, JSONB, constrains |
| Mensajería | RabbitMQ 3.13 | Casos de uso simples, DLQ |
| Frontend | HTML/CSS/JS vanilla | Suficiente para demo, sin build step |
| Contenedores | Docker + Compose | Multi-servicio reproducible |
| Dev | Devcontainer + Docker-in-Docker | Onboarding en Codespaces |

Detalle completo en [ADR-002](docs/adr-002-tecnologico.md).

---

## 4. Ejecución rápida

```bash
docker compose up -d --build
# http://localhost:8080
