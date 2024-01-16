package com.bosonit.formacion.block12kafkaproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;


@SpringBootApplication
public class Block12KafkaProducerApplication {

    public static void main(String[] args) {

		SpringApplication.run(Block12KafkaProducerApplication.class, args);

		var kafkaProducer = Producer.getInstance();
		var scanner = new Scanner(System.in);

		do {
			var line = scanner.next();
			var isValidLine = validFormat(line);

			if (isValidLine || line.equals("exit")) {
				if (line.equals("exit")) {
					log.info("Finishing");
					kafkaProducer.close();
					break;
				}
				var keyAndMessage = line.split(":");
				kafkaProducer.send(keyAndMessage[0], keyAndMessage[1]);
			} else {
				log.error("The format must be string:string");
			}

		} while (true);
	}

	private static Boolean validFormat(String line) {
		var regex = Pattern.compile("\\w+:\\w+");
		var matcher = regex.matcher(line);
		return matcher.matches();
	}

	private static final Logger log = LogManager.getLogger(Block12KafkaProducerApplication.class);

}
