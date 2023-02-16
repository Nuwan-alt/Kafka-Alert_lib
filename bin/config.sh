#!/bin/bash
# Application info
COMPONENT_TAG=@component.tag@
DBG_PORT=@dbg.port@
MIN_HEAP=@min.heap@
MAX_HEAP=@max.heap@
APP_MAIN=@main.class@
APP_PARAMS=@additional.app.params@
JVM_PARAMS=@additional.jvm.params@
CP_OR_JAR=@cp.or.jar@
# Environment info
JVM=java
NOHUP=nohup