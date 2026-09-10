# Banking + E-Commerce Platform

Reference implementation using:
- Spring Boot 3.x / Java 21
- PostgreSQL
- Spring Security + JWT
- Spring Data JPA
- Redis cache
- React + TypeScript
- Docker Compose

## Major modules
Auth, users/accounts, catalog/products, orders, payments, banking transactions,
delivery, receipts, idempotency, audit logging and caching.

## Architecture
React -> REST API -> Spring Security -> Controllers -> Services -> Repositories
                                  |-> Redis
                                  |-> PostgreSQL

Business flow:
Product -> Order -> Payment -> Ledger transaction -> Delivery -> Receipt

## Run
1. Start PostgreSQL and Redis:
   docker compose up -d postgres redis
2. Start backend:
   cd backend
   mvn spring-boot:run
3. Start frontend:
   cd frontend
   npm install
   npm run dev

Demo users can be created through /api/auth/register.

## Configuration

The backend uses local development defaults for PostgreSQL and Redis. For other
environments, configure these environment variables before starting the backend:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `REDIS_HOST`
- `REDIS_PORT`
- `JWT_SECRET`
- `JWT_EXPIRATION_MS`

Do not commit real credentials or JWT secrets. Store them in GitHub Actions
repository secrets or in a local `.env`/environment configuration.
