# SauceDemo Test Automation

Автоматизация тестирования сайта **SauceDemo** с использованием **Java**, **Selenium**, **JUnit 5** и **Allure**.

---

## Требования

- Java 17+
- Gradle (используется Gradle Wrapper `gradlew.bat`)
- Allure Commandline ([инструкция по установке](https://docs.qameta.io/allure/#_installing_a-commandline))
- Chrome и соответствующий ChromeDriver

---

## Настройка проекта

1. Клонируйте репозиторий:

```bat
git clone <URL_репозитория>
cd SauceDemo
```

2. Проверьте, что Gradle Wrapper работает:

```bat
gradlew.bat tasks
```
Установите Allure Commandline и добавьте его в PATH.


3. Запуск тестов

```bat
gradlew.bat clean test
```

Через IDE (IntelliJ IDEA)

Найдите класс или пакет с тестами.

Правый клик → Run 'LoginTests' или Run 'All Tests'.

4. Allure отчет
```bat
allure serve build\allure-results
```
Полезные команды
gradlew.bat clean test  ->   Запуск всех тестов

allure serve build\allure-results  ->   Генерация и просмотр отчета

gradlew.bat build  ->   Сборка проекта

gradlew.bat tasks  ->   Список всех Gradle задач

Структура проекта
```bash
SauceDemo/
│
├─ src/
│  └─ test/
│     └─ java/
│        ├─ base/             # Базовые классы
│        │   └─ BaseTest.java # Базовый класс для тестов
│        ├─ pages/            # Страницы
│        │   ├─ InventoryPage.java # Страница с инвентарем
│        │   └─ LoginPage.java # Страница входа
│        └─ tests/            # Тесты
│           └─ LoginTests.java # Тесты для страницы входа
│
├─ build.gradle             # Сборка Gradle
├─ settings.gradle
├─ gradlew / gradlew.bat
├─ build/                   # Сборка и результаты тестов
└─ README.md