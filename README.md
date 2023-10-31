# RecipeBook

### Тестовое задание на позицию Android Developer / Android-разработчик

Необходимо реализовать приложение, которое получает данные по HTTP с [сервера](https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json) и отображает список рецептов на его основе.

**Требования:**

- Минимум сторонних библиотек (не входящих в Android SDK или Jetpack).
- Должна быть реализована загрузка данных по сети, базовый интерфейс пользователя согласно рекомендациям Google, отображение предварительного просмотра и полноэкранной фотографии, обработка ошибок.
- Код должен быть читаемым, с комментариями. Должна учитываться возможность дальнейшего его развития и поддержки.

**Дополнительные задачи:**

- [x] Кэширование списка рецептов в локальной БД для оффлайн-доступа.
- [x] Кэширование фотографий рецептов.
- [x] Возможность добавления своих рецептов в локальную БД.

### Приложение - книга рецептов

<p>
  Приложение позволяет загружать имеющиеся рецепты с сервера, добавлять пользовательские рецепты, а также просматривать детальную информацию о каждом из них.
</p>

<p>  
    <img src="./screenshots/Screenshot_20230925_190102.png" alt="hotel_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_190126.png" alt="room_data" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_190142.png" alt="booking_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_192000.png" alt="payment_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_192039.png" alt="payment_screen" width="19%" height="auto">
</p>

### Запуск приложения

Клонировать ветку `master` этого репозитория и импортировать в **Android Studio**
```bash
ssh:
git@github.com:tprobius/RecipeBook.git
```
или

```bash
https:
https://github.com/tprobius/RecipeBook.git
```

Запустить на эмуляторе утройства в Android Studio.

### Генерация APK

В Android Studio:
1. ***Build*** menu
2. ***Generate APK...***
3. Установить приложение на телефон.

### Стек

Проект реализован с применением подхода Clean Architecture и MVVM + UDF. 
AdapterDelegates используется на перспективу дальнейшего развития проекта - для поддержки отображения различных вариантов элементов списка.

- Kotlin
- AdapterDelegates
- Clean Architecture
- MVVM + UDF
- View binding
- Coroutine
- Koin
- Room
- Retrofit
- Gson
- Glide

### Backlog

- [x] Добавить DiffUtils в AdatterDelegates.  
- [ ] Реализовать добавление фото в пользовательские рецепты.  
- [ ] Реализовать редактирование / удаление пользовательских рецептов.  
