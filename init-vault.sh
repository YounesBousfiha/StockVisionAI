#!/bin/bash

sleep 5


curl \
  --header: "X-VAULT-TOKEN: " \
  --request: POST \
  --data '{
      "data": {
          "spring.data.mongodb.uri": "",
          "spring.data.redis.host": "",
          "application.security.jwt.secret": ""
        }
    }' \
    http://localhost:8200/v1/secret/data/stockpro-backend

echo "Vault secrets initialized!"