> [Read this page on English on Wiki](wiki)

# AncapFramework

Фреймворк для Bukkit-разработки. 


# Обзор

Фреймворк решает большинство рутинных задач, возникающих при создании плагина для Bukkit - с особым акцентом на высоконагруженные, многофункциональные, многоязыковые плагины с большим количеством кода и логики, а также заставляет разработчика писать код чисто и не пользоваться плохими практиками.

## Модуль AncapMisc
Данный модуль включает в себя набор различных объектов. Модуль не документирован, так как, в основном, код понятен без документации.
### Placeholder
Самая интересная часть AncapMisc. Набор утилит для работы с плейсхолдерами. Активно используется в AncapConfiguration.
### Balance
Набор утилит для работы с экономикой. Включает в себя Balance, Wallet, Currency и их билдеры с фабриками.

## Модуль AncapPluginAPI
Основной модуль фреймворка.
### ConfigurationAPI
API для работы с файлами конфигурации плагина. Поддерживает многоязыковые конфигурации, внутренние плейсходеры, а также имеет множество других маленьких удобных плюшек. Пример файла конфигурации:
![image](https://github.com/ancap-dev/AncapFramework/blob/main/META-INF/config1.png) ![image](https://github.com/ancap-dev/AncapFramework/blob/main/META-INF/config2.png)
### EventAPI
Null-безопасные обёртки для баккитовских ивентов. Больше никаких NullPointerException, instanceof и ((Player) projectile). Как бонус - AncapHeartbeat и три таймера.
### CommandAPI
Устали от неповоротливого баккитовского CommandExecutor? Попробуйте AncapCommandExecutor с разделением onCommand на onPlayerCommand и onConsoleCommand с встроенной поддержкой AncapCommandValidatingAPI!

Класс команды /mygodcommand длится 5000 строк, а 90% кода там - повторения? Попробуйте ООП-решение для обработки команд AncapSubCommandAPI и AncapCommandValidatingAPI (на данный момент в разработке).
### PacketAPI
Сколько можно мучится, придумывая процедурные велосипеды для отправки сообщений и звуков игрокам? Никаких больше статиков и утиль-классов, только полноценные объекты Message, Sound, ActionBar, Title и даже BossBar вместе с удобными билдерами.
### AncapPluginAPI (собственной персоной)
Легчайший способ интегрировать свой плагин с ConfigurationAPI и сделать мейн класс длиной в 50 строк вместо 1000. А как приятная плюшка - напоминание о необходимости подключать bstats сразу же после создания даже самого маленького плагина.

## AncapPlugin
Runtime-часть AncapFramework. Устанавливается прямо на сервер как плагин, использовать её как библиотеку при разработке плагинов не нужно (но надо указать как зависимость в plugin.yml, чтобы Bukkit корректно всё подгружал). 

Бросает ивенты, а также предоставляет общий многоязыковой конфиг для других плагинов. Реализация AncapPlugin в виде AncapLibrary также поддерживает выполнение авто-теста фреймворка через /run-framework-test. 
# Подключение
## Maven
### Repository

     <repository>  
	     <id>AncapFramework-mvn-repo</id>  
	     <url>https://raw.github.com/ancap-dev/AncapFramework/mvn-repo/</url>  
	     <snapshots> 
		     <enabled>true</enabled>  
		     <updatePolicy>always</updatePolicy>  
	     </snapshots>
	 </repository>

### Dependency

     <dependency>  
	     <groupId>ru.ancap</groupId>  
	     <artifactId>AncapPluginAPI</artifactId>  
	     <version>1.0.0</version>  
	     <scope>compile</scope>  
	 </dependency>

# Вики

Другие языки, а также гайд для самых маленьких по использованию фреймворка можно найти на вики.
