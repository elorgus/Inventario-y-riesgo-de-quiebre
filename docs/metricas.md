# Métricas

## Métrica de negocio
**Quiebres por producto al mes**

- **Definición:** Nº de SKUs que llegaron a stock = 0 al menos una vez en el mes.
- **Objetivo:** Reducir < 3% del catálogo.
- **Fuente:** tabla `stocks`, histórico diario.
- **Acción:** si supera el objetivo, revisar umbrales de `stockMinimo`.

## Métrica técnica
**Latencia p95 del endpoint `/api/v1/riesgo`**

- **Definición:** percentil 95 del tiempo de respuesta del cálculo de riesgo.
- **Objetivo:** < 500 ms con hasta 10.000 filas.
- **Fuente:** Actuator + Micrometer (futuro), logs.
- **Acción:** si supera, cachear cálculo y recomputar cada N minutos.