# RecipeBook

### Тестовое задание на позицию Android Developer / Android-разработчик.

### Приложение - книга рецептов.

<p>
  Приложение позволяет загружать имеющиеся рецепты с сервера, просматривать детальную информацию о каждом рецепте, а также добавлять свои рецепты.
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

