# kafka-producer
Kafka producer sample project by spring boot 2.3.x

## Kafka Setting
zookeeper-1 : localhost:2181
broker-1 : localhost:9092
broker-2 : localhost:9093
broker-3 : localhost:9094

kafka-manager 셋팅 : https://log-laboratory.tistory.com/180#5.-config/application.conf-%ED%8C%8C%EC%9D%BC-%EC%88%98%EC%A0%95
자바 11 이슈 해결 : https://program-error-review.tistory.com/26

# 카프카 브로커 실행
bin/kafka-server-start-1.sh -daemon config/server-1.properties
bin/kafka-server-start-2.sh -daemon config/server-2.properties
bin/kafka-server-start-3.sh -daemon config/server-3.properties

# 카프카 브로커 종료
bin/kafka-server-stop.sh -daemon config/server-1.properties
bin/kafka-server-stop.sh -daemon config/server-2.properties
bin/kafka-server-stop.sh -daemon config/server-3.properties

## 설치 환경 ##

#주키퍼 실행 커멘드 : port - 2181
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties

#주키퍼 종료 커멘드
bin/zookeeper-server-stop.sh -daemon config/zookeeper.properties

#기본 카프카 시작 : port - 9092
bin/kafka-server-start.sh -daemon config/server.properties

#기본 카프카 종료
bin/kafka-server-stop.sh -daemon config/server.properties

#토픽 생성
>> replica 3, partition 3로 생성한것
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 3 --topic sample-topic-1
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 3 --topic time-attack-coupon-1

>> partition 1로 생성한것
bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --topic sample-topic-2

#생성된 토픽 확인
bin/kafka-topics.sh --list --zookeeper localhost:2181

#생성된 consumer group 확인
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

# 메시지 받기
#consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sample-topic-1 —from-beginning
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic sample-topic-1 —from-beginning

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sample-topic-2 —from-beginning

#consumer - 그룹지정
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sample-topic-1 --group sample-group --from-beginning
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sample-topic-2 --group sample-group --from-beginning

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sample-topic-2 --group person_consumer --from-beginning

#consumer - key 노출
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-2 --property print.key=true --property key.separator="-" --from-beginning

# 메시지 보내기
#producer
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic sample-topic-2
bin/kafka-console-producer.sh --broker-list localhost:9093 --topic sample-topic-1 bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic sample-topic-1

# Kafka Rest Proxy 실행
bin/kafka-rest-start etc/kafka-rest/kafka-rest.properties


{“name”:”paden”}

API : Request -> Controller -> Service -> repository -> Insert Row ->  Response

#Producer asks
Asks = 0
속도 빠름. 유실 가능성 높음
Acks = 1
속도 보통, 유실 가능성 있음
Asks = all or -1
속도 가장 느림. 메시지 전달 손실 가능성 없음

#안정적으로 종료 시 - wakeup
Java shutdownhook 메소드에 consumer.wakeup() call 처리
