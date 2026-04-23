# FITSync API

## 🚀 Features

### 🔐 JWT-Based Security
Implemented stateless authentication and authorization using **JSON Web Tokens (JWT)** integrated with **Spring Security** to ensure secure and scalable access control.

### 🏋️ Fitness Activity Tracking
Provides RESTful endpoints to log and manage workouts, including:
- Activity type  
- Duration  
- Calories burned  

### 🎯 Personalized Recommendations
Generates tailored health and fitness suggestions based on user activity data to improve engagement and outcomes.

### 👥 Role-Based Access Control (RBAC)
Implements fine-grained access control with distinct roles:
- **USER** – Access to personal activity and recommendations  
- **ADMIN** – Elevated privileges for system management  

### 🕒 Automated Auditing
Uses Hibernate annotations to automatically manage:
- `created_at` timestamps  
- `updated_at` timestamps  

### 📄 Interactive API Documentation
Integrated **Swagger UI / OpenAPI 3.0** for:
- Real-time API testing  
- Clear and structured endpoint documentation  

### 🐳 Docker Containerization
Fully containerized using Docker to ensure:
- Consistent environments across development and production  
- Easy deployment and scalability  

### ☁️ Cloud Optimized
Designed for seamless deployment on **Render**, using **Neon DB (PostgreSQL)** as a cloud-native database solution.

### 🧱 Clean Architecture
Follows best practices using the **DTO (Data Transfer Object)** pattern to:
- Decouple database and presentation layers  
- Improve maintainability and scalability  
