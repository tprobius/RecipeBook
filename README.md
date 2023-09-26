# RecipeBook

### Тестовое задание на позицию Android Developer / Android-разработчик.

Необходимо реализовать приложение, которое получает данные по HTTP с [сервера](https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json) и отображает список рецептов на его основе.

**Требования:**

- Поддержка Android, начиная с версии 6.
- Минимум сторонних библиотек (не входящих в Android SDK или Jetpack).
- Должна быть реализована загрузка данных по сети, базовый интерфейс пользователя согласно рекомендациям Google, отображение предварительного просмотра и полноэкранной фотографии, обработка ошибок.
- Код должен быть читаемым, с комментариями. Должна учитываться возможность дальнейшего его развития и поддержки.
- Обязательно наличие файла README с обоснованием выбранного подхода к решению задачи, а также порядком тестирования приложения.

**Дополнительные задачи:**

- Кэширование списка рецептов в локальной БД для оффлайн-доступа.
- Кэширование фотографий рецептов.
- Возможность добавления своих рецептов в локальную БД.

### Приложение - книга рецептов.

<p>
  Приложение позволяет загружать имеющиеся рецепты с сервера, просматривать детальную информацию о каждом рецепте, а также добавлять пользовательские рецепты.
</p>

<p>  
    <img src="./screenshots/Screenshot_20230925_190102.png" alt="hotel_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_190126.png" alt="room_data" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_190142.png" alt="booking_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_192000.png" alt="payment_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20230925_192039.png" alt="payment_screen" width="19%" height="auto">
</p>

### Запуск приложения.

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

### Генерация APK.

В Android Studio:
1. ***Build*** menu
2. ***Generate APK...***
3. Установить приложение на телефон.

### Стек.

Проект реализован с применением подхода Clean Architecture и MVVM + UDF. 
AdapterDelegates используется на перспективу дальнейшего развития проекта - для поддержки отображения различных вариантов элементов списка.

- Kotlin
- AdapterDelegates
- Clean Architecture
- MVVM + UDF
- Data / View binding
- Coroutine
- Koin
- Room
- Retrofit
- Gson
- Glide

### Backlog.

- Добавление фото в пользовательские рецепты.
- Редактирование / удаление пользовательских рецептов.
- DiffUtils в RcyclerView.

