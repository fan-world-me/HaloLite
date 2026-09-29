# HaloLite

Анимация RGB-кольца вокруг камеры на POCO X8 Pro (HyperOS 3) через Shizuku, без root.
Плитка в шторке запускает радугу, долгое нажатие открывает меню с выбором длительности (3 / 5 / 10 / 15 / 30 сек, по умолчанию 5).

Исследование и детали API: https://github.com/fan-world-me/HyperOS-ColorLightManager-Research

## Как пользоваться
1. Установи и запусти Shizuku.
2. Открой HaloLite → «Запросить доступ Shizuku».
3. Добавь плитку HaloLite в шторку.

## Автосборка (GitHub Actions)
Каждый push в `main` собирает подписанный APK и публикует релиз `v1.0.<номер запуска>`.

Нужны секреты репозитория (Settings → Secrets and variables → Actions):

| Секрет | Что это |
|---|---|
| `KEYSTORE_BASE64` | keystore в base64 |
| `KEYSTORE_PASSWORD` | пароль keystore |
| `KEY_ALIAS` | алиас ключа |
| `KEY_PASSWORD` | пароль ключа |

Создать ключ (в Termux нужен `pkg install openjdk-17`):
```
keytool -genkeypair -v -keystore release.jks -alias halolite -keyalg RSA -keysize 2048 -validity 36500
base64 -w0 release.jks
```
Вывод второй команды — значение `KEYSTORE_BASE64`. Файл `release.jks` не коммить и сохрани в надёжном месте: с другим ключом обновление поверх установленной версии не встанет.

## Локальная сборка
```
KEYSTORE_PATH=/path/release.jks KEYSTORE_PASSWORD=... KEY_ALIAS=halolite KEY_PASSWORD=... ./gradlew assembleRelease
```
Без `KEYSTORE_PATH` APK подписывается debug-ключом.
Настройки вроде `android.aapt2FromMavenOverride` для Termux держи в `~/.gradle/gradle.properties`, не в репозитории.
