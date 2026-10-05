# Riesgos y mitigaciones

| # | Riesgo | Impacto | Probabilidad | Mitigación |
|---|---|---|---|---|
| R1 | Consumo diario mal estimado → falsos positivos de quiebre | Alto | Media | Usar promedio móvil 7/14 días; alerta solo si el patrón se repite 2+ días |
| R2 | Rebuild de la app no recoge cambios (caché Docker) | Alto | Alta | `docker compose build --no-cache app`; documentar en README |
| R3 | Pérdida de la cola de eventos si RabbitMQ cae | Medio | Media | Colas durables + `persistent: true` en mensajes; DLQ para mensajes fallidos |

## Detalle R1 — Falsos positivos
**Reducción:** Además del consumo puntual, considerar tendencia (subiendo/bajando) y estacionalidad semanal.

## Detalle R2 — Caché Docker
**Reducción:** Documentar el flujo en README y agregar `--no-cache` al comando de rebuild. Script `./rebuild.sh` opcional.

## Detalle R3 — Pérdida de eventos
**Reducción:** Configurar `x-queue-type: quorum` si el volumen lo requiere. Alertas cuando la DLQ tenga mensajes.