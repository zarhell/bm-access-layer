#!/bin/bash


REMOTE_REPO_URL="https://github.com/tu-usuario/tu-repo.git"
REMOTE_REPO="origin"
BRANCH="main"

PROJECT_DIR=$(pwd)

LOG_FILE="run_app.log"

function log {
    echo "[$(date +'%Y-%m-%d %H:%M:%S')] $1" | tee -a $LOG_FILE
}

log "Cambiando al directorio del proyecto: $PROJECT_DIR"
cd $PROJECT_DIR

log "Verificando el estado del repositorio"
if ! git diff-index --quiet HEAD --; then
    log "Hay cambios no comprometidos en el repositorio. Por favor, compromételos o guárdalos antes de continuar."
    exit 1
fi

log "Configurando la URL del repositorio remoto a $REMOTE_REPO_URL"
git remote set-url $REMOTE_REPO $REMOTE_REPO_URL

log "Actualizando el repositorio desde $REMOTE_REPO/$BRANCH"
git pull $REMOTE_REPO $BRANCH

if [ $? -ne 0 ]; then
    log "Error al actualizar el repositorio. Abortando."
    exit 1
fi

log "Limpiando el proyecto con Gradle"
./gradlew clean

if [ $? -ne 0 ]; then
    log "Error al limpiar el proyecto. Abortando."
    exit 1
fi

log "Ejecutando el build con Gradle"
./gradlew build

if [ $? -ne 0 ]; then
    log "Error en el build. Abortando."
    exit 1
fi

log "Ejecutando pruebas automatizadas con Gradle"
./gradlew test

if [ $? -ne 0 ]; then
    log "Algunas pruebas fallaron. Abortando."
    exit 1
fi

log "Ejecutando la aplicación con Gradle bootRun"
./gradlew bootRun

if [ $? -ne 0 ]; then
    log "Error al ejecutar la aplicación. Abortando."
    exit 1
fi

log "La aplicación se ha ejecutado exitosamente."
