# Shop-ConnectingSQL

[English](#english) | [Русский](#russian)

# English

A Spring Boot application for managing an online shop with PostgreSQL database integration.

## 💫 Core Functionality

The application provides a complete e-commerce solution with the following key functionalities:

### 🛍️ Product Management
- Create, read, update, and delete products
- Product categorization and search
- Image upload and management for products
- Product inventory tracking

### 👥 User Management
- User registration and profile management
- User authentication and authorization
- User role management

### 🛒 Shopping Cart
- Add/remove items to shopping cart
- Update quantities in cart
- Cart item management
- Cart total calculation

### 📦 Order Processing
- Create and manage orders
- Order status tracking
- Order history viewing
- Process order payments

### 🗂️ Category Management
- Create and manage product categories
- Category hierarchy
- Product-category associations

### 🖼️ Image Handling
- Upload product images
- Image storage and retrieval
- Image association with products

### 🛡️ Security
- Secure API endpoints
- User authentication
- Role-based access control

## 🚀 Features

- RESTful API endpoints for shop management
- PostgreSQL database integration using Spring Data JPA
- Model mapping with ModelMapper
- Hot-reload support with Spring DevTools
- Built with Spring Boot 3.4.1

## 📋 Prerequisites

- Java 21
- Maven
- PostgreSQL database

## 🛠️ Tech Stack

- **Framework:** Spring Boot 3.4.1
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA
- **Build Tool:** Maven
- **API Documentation:** Spring Web
- **Development Tools:** Spring Boot DevTools

## 🔧 Configuration

The application uses Spring Boot's standard configuration. Main configuration properties can be set in `application.properties` or `application.yml`.

### Database Configuration

Configure your PostgreSQL database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/shop-connectingsql.git
```

2. Navigate to the project directory:
```bash
cd shop-connectingsql
```

3. Build the project:
```bash
./mvnw clean install
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📁 Project Structure

```
shop-connectingsql/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   └── resources/
│   └── test/
├── pom.xml
├── README.md
└── .gitignore
```

## 🔄 Development

The project includes Spring Boot DevTools for automatic restarts during development. Any changes to the classpath will trigger a restart.

## 🧪 Testing

Run tests using Maven:

```bash
./mvnw test
```

## 📦 Building for Production

Create a production-ready JAR file:

```bash
./mvnw package
```

The JAR file will be created in the `target` directory.

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

## 👥 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

# Russian

# Shop-ConnectingSQL

Приложение на Spring Boot для управления интернет-магазином с интеграцией PostgreSQL.

## 💫 Основной функционал

Приложение предоставляет полное решение для электронной коммерции со следующими ключевыми функциями:

### 🛍️ Управление товарами
- Создание, чтение, обновление и удаление товаров
- Категоризация и поиск товаров
- Загрузка и управление изображениями товаров
- Отслеживание инвентаря

### 👥 Управление пользователями
- Регистрация и управление профилем пользователя
- Аутентификация и авторизация пользователей
- Управление ролями пользователей

### 🛒 Корзина покупок
- Добавление/удаление товаров в корзину
- Обновление количества товаров в корзине
- Управление товарами в корзине
- Расчет общей стоимости корзины

### 📦 Обработка заказов
- Создание и управление заказами
- Отслеживание статуса заказа
- Просмотр истории заказов
- Обработка платежей

### 🗂️ Управление категориями
- Создание и управление категориями товаров
- Иерархия категорий
- Связь товаров с категориями

### 🖼️ Работа с изображениями
- Загрузка изображений товаров
- Хранение и получение изображений
- Привязка изображений к товарам

### 🛡️ Безопасность
- Защищенные API-эндпоинты
- Аутентификация пользователей
- Контроль доступа на основе ролей

## 🚀 Возможности

- RESTful API эндпоинты для управления магазином
- Интеграция с PostgreSQL через Spring Data JPA
- Маппинг моделей с помощью ModelMapper
- Поддержка горячей перезагрузки с Spring DevTools
- Построено на Spring Boot 3.4.1

## 📋 Требования

- Java 21
- Maven
- PostgreSQL база данных

## 🛠️ Технологический стек

- **Фреймворк:** Spring Boot 3.4.1
- **База данных:** PostgreSQL
- **ORM:** Spring Data JPA
- **Сборка:** Maven
- **API Документация:** Spring Web
- **Инструменты разработки:** Spring Boot DevTools

## 🔧 Конфигурация

Приложение использует стандартную конфигурацию Spring Boot. Основные параметры можно настроить в `application.properties` или `application.yml`.

### Настройка базы данных

Настройте подключение к PostgreSQL в `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 🚀 Начало работы

1. Клонируйте репозиторий:
```bash
git clone https://github.com/yourusername/shop-connectingsql.git
```

2. Перейдите в директорию проекта:
```bash
cd shop-connectingsql
```

3. Соберите проект:
```bash
./mvnw clean install
```

4. Запустите приложение:
```bash
./mvnw spring-boot:run
```

Приложение запустится по адресу `http://localhost:8080`

## 📁 Структура проекта

```
shop-connectingsql/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   └── resources/
│   └── test/
├── pom.xml
├── README.md
└── .gitignore
```

## 🔄 Разработка

Проект включает Spring Boot DevTools для автоматической перезагрузки во время разработки. Любые изменения в classpath вызовут перезагрузку.

## 🧪 Тестирование

Запуск тестов с помощью Maven:

```bash
./mvnw test
```

## 📦 Сборка для продакшена

Создание JAR файла для продакшена:

```bash
./mvnw package
```

JAR файл будет создан в директории `target`.

## 📄 Лицензия

Этот проект с открытым исходным кодом доступен под [лицензией MIT](LICENSE).

## 👥 Участие в разработке

1. Форкните репозиторий
2. Создайте ветку для новой функции (`git checkout -b feature/НоваяФункция`)
3. Зафиксируйте изменения (`git commit -m 'Добавлена новая функция'`)
4. Отправьте изменения в репозиторий (`git push origin feature/НоваяФункция`)
5. Создайте Pull Request
