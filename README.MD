# Setup

## 1 Set Java Version

```
File -> Project Structure... -> 
  - SKD: termurin-17 (If not dowloaded: Add SKD -> Download JDK...)
  - Language Level: 17
```

## 2 Compile
Run the following command
```
mvn clean install
```

## 3 Set Config
In `src/main/resources/application.yml` fill in the following values.


* INITIALS (z.B. `NME`)
* **Kafka Config:**
  * KAFKA_KEY
  * KAFKA_SECRET

* **Schema Registry Config:**
  * SR_API_KEY
  * SR_API_SECRET
* Kafka & Schema Registry Config can be found here: https://docs.google.com/document/d/17sEf1aEZGBLhQKTDrNzBsdtiALWvmRrux2iv9WE_HB8/edit?usp=sharing


## 4 Start App

Start class `ch.ipt.kafka.VisconKafkaApplication`

# Exercises

Attention 007! You have come across this program, but some parts of it seem to be incomplete. If completed, we should be able to see who is making illegal payments to ```REDACTED```. Will you help us find out who it is? As always, this mission does not officially exist and should you accept it, you will be on your own. Best of luck!
## Exercise 1 - Consume from simple topic

Let us start by consuming from a simple string-value topic. In the class KafkaConsumer, start by replacing your initials. Then, print out the key and the value of incoming records.

## Exercise 2 - Consume from structured topic

Next, we want to keep a tally of all the money that comes in. As you might have noticed, the data we receive in exercise 1 appears to be json-formatted. We could now just pass this string into a json parser and extract the relevant field, but kafka offers a better solution.

The topic ```transactions-avro``` produces structured data. The ```Payment.avsc``` file provides a model for the structure of this data. Write a consumer that takes a key-value pair of type ```<String, Payment>``` and sum up the total amount of money flowing through the transactions consumed. Print out this total each time a new transaction comes in.

Make sure to choose a consumer group containing your initials, or you might land in the same consumer group as another student.

## Exercise 3 - Create your own topic and produce data

It is time to dig deeper and understand how ```REDACTED``` is sending money through the network. For this, you will build your own topic and produce data to it. Of course, we don't want to arouse suspicion and instead of Payments, we will be sending Account records.

### 3.1 Register Topic
Start by registering a new topic in the class KafkaTopology. The name of the topic should be ```accounts-avro-<YOUR-INITIALS>```. Configure it with 6 partitions and 3 replicas.

<details>
  <summary>Hint</summary>
  Use TopicBuilder
</details>


### 3.2 Produce Data

The method ```AccountProducer.sendAccounts``` gets called every 2 seconds. Each time it gets called, send a record of key-value-type ```<String, Account>``` to your newly created topic. User the accountId as key.

<details>
  <summary>Hint</summary>
  AccountDataEnum.getRandomAccount() provides you with random account structures.
</details>

### 3.3 Consume the data your produce

Again in the class ```KafkaConsumer```, consume from the topic you registered and started producing to. Log key and value of each record.


# Bonus Exercises

Well done 007! Thanks to your intel we were able to identify the payment flow and discover ```REDACTED```'s accomplice!

You now know how to register a topic as well as produce and consume records. The following exercises do not have a provided solution, but feel free to give them a try.

## Bonus Exercise 1

Right now, your consumers start consuming at the latest offset of their consumer group. Modify the code in such a way that at each startup of the app, the offset gets reset to the start so that you consume all the records again.

## Bonus Exercise 2

Kafka Clusters do not operate on their own, they are always part of a bigger architecture. Write a simple REST endpoint that gets a list of Payments and produce a record for each payment recieved.

Use the following curl command to test your endpoint:

```
curl -d '[{"id":"ID1", "accountId":"1", "cardNumber":12345, "cardType":"Credit", "amount":100},{"id":"ID2", "accountId":"2", "cardNumber":22345, "cardType":"Credit", "amount":200},{"id":"ID3", "accountId":"3", "cardNumber":32345, "cardType":"Credit", "amount":300},{"id":"ID4", "accountId":"4", "cardNumber":42345, "cardType":"Credit", "amount":400},{"id":"ID5", "accountId":"5", "cardNumber":52345, "cardType":"Credit", "amount":500},{"id":"ID6", "accountId":"6", "cardNumber":62345, "cardType":"Credit", "amount"6100}]' -H "Content-Type: application/json" -X POST http://localhost:8080/payments
```

<details>
  <summary>Hint</summary>
  Use Spring Boot @RestController class annotation: https://spring.io/guides/gs/spring-boot/
</details>