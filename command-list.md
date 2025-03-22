# Archivo docker-compose

### build
Especifica la ruta del archivo dockerfile a usarse en la construcción de una imagen y le aplica el contexto. (En lugar de iamge)

´´´

services:
    service-name:
        build:
            context:   # Es la carpeta que contiene el dockerfile
            dockerfile:   # nombre del archivo (Dockerfile o dockerfile)
´´´

### environments
Define variables de entorno que el contenedor necesita

´´´

services:
    service-name:
        environments:
            - MYSLQ_ROOT_PASSWORD = pass   # tambien pueden colocar variables relacionadas a un archivo. ej: $DB_PASSWORD
            - APP_ENV = production
´´´

### env_file:
Define la ruta a un archivo de variables de entorno.

´´´

services:
    service-name:
        env_file:
            - .env
´´´

### depends_on
Indica que servicio debe iniciarse después de otro.

´´´

services:
    service-name:
        image: nginx
    depends-on:
        - db-service

    db-service:
        image: mysql
´´´

### restart
Define la política de reinicio del contenedor:
- no --> No se reinicia nunca
- always --> Si se detiene se reinicia.
- on-failure --> Solo se reinicia si falla

´´´

services:
    service-name:
        image: nginx
    depends_on:
        - db-service
    restart: on-failure
´´´

### healthcheck
Monitorea la salud del contenedor.
- test --> Prueba el estado a través de comandos
    - CMD
    - CMD-SHELL
- interval --> cada cuanto se verifica.
- timeout --> Tiempo máximo de espera antes de fallar.
- retries --> Número de intentos antes de setear el estado unhealty
- start_period --> Tiempo de gracia antes de que empiece el monitoreo.

services:
    service-name:
        image: nginx
    depends_on:
        - db-service
    restart: on-failure
    healthcheck:
        test: [ "CMD", "curl", "-f", "http://localhost:8080" ]
        interval: 15s
        timeout: 10s
        retries: 3
        start_period: 45s
´´´

###  DOCKER SWARM

# 




### Ejemplo  Docker compose

services:
web:  #  contenedor
image: httpd  # imagen base
ports: # opción publish (-p) en run container
- 8080:80 # host:contenedor
- 443:443
volumes: # opción -v en container run
- web-volume:/var/http/src  # nombre del volumen:ruta de los archivos.
proxy:  # contenedor
image: nginx
volumes:  # lista de volumnes accesibles para las instancias de la red
web-volume:

networks:
web-networks:

# comandos utilizados por docker-compose
# docker compose up
# docker compose up --build
# docker compose down
# docker compose restart
