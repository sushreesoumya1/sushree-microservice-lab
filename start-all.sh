#!/bin/bash

echo "Starting Docker Compose services..."
docker compose up -d --build

echo "Waiting for containers..."
sleep 5

echo "Deploying Kubernetes services..."
kubectl apply -f k8s/

echo "All services started successfully."