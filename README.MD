# Setup

Folgendes ausführen
```
mvn clean install
```


# App starten

## 1. Config setzen
In `src/main/resources/application.yml` folgende Werte befüllen.


* INITIALS (z.B. `PGR`)
* **Kafka Config:**
  * KAFKA_KEY
  * KAFKA_SECRET

* **Schema Registry Config:**
  * SR_API_KEY
  * SR_API_SECRET

Kafka & Schema Registry Config findest du hier: https://docs.google.com/document/d/1aD0TRajzXd44wlis65wSfuIAWp0fm8aFiblbnYj5uIM/

## 2. App starten

Die Klasse `ch.ipt.kafka.VisconKafkaApplication` starten