# OAuth2 Authorization e Resource Server

In questo progetto sono stati implementati due componenti principali:

### Authorization Server
Gestisce l'autenticazione e la generazione dei token JWT (JSON Web Token). Fornisce un endpoint per ottenere le chiavi pubbliche (JWK - JSON Web Keys) e consente ai client di autenticarsi tramite il protocollo OAuth2.

### Resource Server
Protegge un'API e consente l'accesso solo a client che forniscono un token JWT valido. Il Resource Server verifica il token ricevuto utilizzando le chiavi pubbliche fornite dall'Authorization Server.

## Flusso di autenticazione

1. L'Authorization Server fornisce una chiave pubblica tramite un endpoint `/oauth2/jwks`.
2. Un client può autenticarsi e ottenere un token JWT dall'Authorization Server.
3. Il client invia il token JWT come parte della richiesta per accedere a un endpoint protetto del Resource Server.
4. Il Resource Server verifica la validità del token JWT utilizzando la chiave pubblica dell'Authorization Server e permette o nega l'accesso in base alla validità del token.

Richiesta token → POST http://localhost:8080/oauth2/token
L'Authorization Server genera un JWT firmato con la sua chiave privata e lo restituisce al client
Si usa il token per accedere al Resource Server → GET http://localhost:8081/public/hello
Il Resource Server riceve il token e lo deve verificare per garantire che sia valido
Spring Security, configurato come Resource Server, interroga automaticamente l'Authorization Server per ottenere la chiave pubblica, chiamando http://localhost:8080/oauth2/jwks
Se la firma del token corrisponde alla chiave pubblica ricevuta, l'accesso viene consentito 

## Tecnologie utilizzate

- **Spring Boot**
- **OAuth2**
- **JWT (JSON Web Token)**
- **RSA**
- **Spring Security**
- **Docker**
- **Docker Compose**

# Autore

Andrea Italiano
