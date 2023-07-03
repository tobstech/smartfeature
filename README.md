# Smart Features

This project uses Quarkus, the Supersonic Subatomic Java Framework.

It is a smart feature which exposes APIs for Smart Features.

## Pre-requisites

- Install Docker
- Install Kubernetes
- Install Kubectl
- Install Minikube
- Install Open JDK 17
- Install Postgres
- H2 Database for Test

## List of available endpoints

- Create smart features http://localhost:9099/smart-features [POST method]
- Get smart features http://localhost:9099/smart-features [GET method]
- Search smart features http://localhost:9099/smart-features/search [GET method]
- Delete an existing smart features http://localhost:9099/smart-features/SERVICE-ID [DELETE method]

## Pull postgres image from docker and start postgres database server on docker

You can pull postgres docker image using the command below:
```shell script
docker pull postgres
```

You can start postgres database server on docker using:
```shell script
docker run --name smart_feature_db -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -e POSTGRES_DB=smart_feat_db -p 5432:5432 postgres:14
```
## Postman Collection With Endpoints & Example Request

The postman collection with example request and response can be found in the dir:

```shell script
src/main/resources/Smart Feature APIs.postman_collection.json
```

## Build & Running the application in dev mode

You can build run your application in dev mode that enables live coding using:
```shell script
mvn compile quarkus:dev
```
## Accessing the List of APIs Via Swagger UI

You can access all api endpoints using the link below (OpenAPI & Swagger UI):

http://localhost:9098/smart-features-api


## Packaging and running the application

The application can be packaged using:
```shell script
mvn package
```

## To deploy the app in minikube

You can deploy the application on minikube using:
```shell script
kubectl apply -f target/kubernetes/minikube.yml
```
After a few seconds or minutes, the application is available on Minikube

## List all Pods

You can get list of all pods using:
```shell script
kubectl get pod
```

## Access the application at the local machine

To access on the local machine, it is required to redirect Minikube address to
localhost, type this command. (Each Pod there is a unique name):

```shell script
kubectl port-forward pod/PODNAME 9099:9099
```
## Access application in browser

Open your browser and type this address: http://localhost:9099
