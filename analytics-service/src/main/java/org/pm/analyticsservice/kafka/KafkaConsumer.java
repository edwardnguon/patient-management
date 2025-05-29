package org.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(
          KafkaConsumer.class);

  // Sets up kafka listener functionality to consumer events from a kafka producer
  // Expects protobuf messages
  @KafkaListener(topics = "patient", groupId = "analytics-service")
  public void consumeEvent(byte[] event) {
    try {
      PatientEvent patientEvent = PatientEvent.parseFrom(event);
      // perform analytics business logic here
      log.info("Received patient event: [PatientId={}, PatientName={}, PatientEmail={}]",
              patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail());
    } catch (InvalidProtocolBufferException e) {
      log.error("Error while parsing patient event", e);
    }
  }
}
