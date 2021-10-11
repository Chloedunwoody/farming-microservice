#!/usr/bin/env bash
mkdir farm
cd farm

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=horse-service \
--package-name=com.dunwoody.farm.core.horse \
--groupId=com.dunwoody.farm.core.horse \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
horse-service

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=barn-service \
--package-name=com.dunwoody.farm.core.barn \
--groupId=com.dunwoody.farm.core.barn \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
barn-service

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=owner-service \
--package-name=com.dunwoody.farm.core.owner \
--groupId=com.dunwoody.farm.core.owner \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
owner-service

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=barn-composite-service \
--package-name=com.dunwoody.farm.composite.barn \
--groupId=com.dunwoody.farm.composite.barn \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
barn-composite-service

cd ..
